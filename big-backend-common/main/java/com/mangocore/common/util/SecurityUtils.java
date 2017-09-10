package com.mangocore.common.util;

import lombok.Getter;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

/**
 * support BA authentication.
 */
public class SecurityUtils {
    private static final String MWS = "MWS";
    private static final String ENCRYPT_FORMAT = "{0} {1}\n{2}";

    public static SecurityHeader getBAHeader(String clientKey, String secret, String method, String uri) throws Exception {
        Date sysdate = new Date();
        SimpleDateFormat df = new SimpleDateFormat("EEE', 'dd' 'MMM' 'yyyy' 'HH:mm:ss' 'z", Locale.US);
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date = df.format(sysdate);
        String encryptStr = MessageFormat.format(ENCRYPT_FORMAT, method, uri, date);
        String sign = hmacSHA1(secret, encryptStr);
        return new SecurityHeader(date, String.format("%s %s:%s", MWS, clientKey, sign));
    }

    public static boolean authenticateBA(SecurityHeader securityHeader, String secret, String method, String uri) throws Exception {
        if (!MWS.equals(getBAMethod(securityHeader.getAuthorization()))) {
            return false;
        }
        String dateStr = securityHeader.getDate();
        String signature = getSignature(securityHeader.getAuthorization());
        String encryptStr = MessageFormat.format(ENCRYPT_FORMAT, method, uri, dateStr);
        String mySignature = hmacSHA1(secret, encryptStr);
        return Objects.equals(mySignature, signature);
    }

    public static String getBAMethod(String authStr) {
        return authStr.split(" ")[0];
    }

    public static String getClientKey(String authStr) {
        return authStr.split(" ")[1].split(":")[0];
    }

    public static String getSignature(String authStr) {
        return authStr.split(":")[1];
    }

    private static String hmacSHA1(String key, String data) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(Charset.forName("UTF-8")), "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(data.getBytes("utf-8"));
        return Base64.encodeBase64String(rawHmac);
    }

    @Getter
    public static class SecurityHeader {
        private String date;
        private String authorization;

        public SecurityHeader(String date, String authorization) {
            this.date = date;
            this.authorization = authorization;
        }
    }

//    public static void main(String[] args) throws Exception {
//        SecurityHeader securityHeader = getBAHeader("isearchapiflight", "b8b857333da27e1c5635111f3ad3ec0c", "GET", "/inter/city/all");
//        System.out.println(securityHeader.getDate());
//        System.out.println(securityHeader.getAuthorization());
//        boolean result = authenticateBA(securityHeader, "b8b857333da27e1c5635111f3ad3ec0c", "GET", "/inter/city/all");
//        System.out.println(result);
//    }
}
