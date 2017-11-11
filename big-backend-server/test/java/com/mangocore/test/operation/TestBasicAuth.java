package com.mangocore.test.operation;

import com.mangocore.common.util.BasicAuth;

/**
 * Created by notreami on 17/9/15.
 */
public class TestBasicAuth {
    public static void main(String[] args) throws Exception {
        BasicAuth.Data data = BasicAuth.createBasicAuthData("isearchapiflight", "b8b857333da27e1c5635111f3ad3ec0c", "GET", "/inter/city/all");
        System.out.println("Date: " + data.getDate());
        System.out.println("Authorization：" + data.getAuth());
        boolean result = BasicAuth.checkBasicAuthData(data, "b8b857333da27e1c5635111f3ad3ec0c", "GET", "/inter/city/all");
        System.out.println(result);
    }
}
