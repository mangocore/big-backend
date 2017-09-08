package com.mangocore.biz.util;

import java.io.File;

/**
 * Created by notreami on 16/5/7.
 */
public class SysUtils {
    public static String getProjectName() {
        String projectName = System.getProperty("user.dir");
        return projectName.substring(projectName.lastIndexOf(File.separator) + 1, projectName.length());
    }
}
