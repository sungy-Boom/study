package com.daily.learn.shell_test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by SunGuiyong
 * on 2018/1/8.
 */
public class UseShellByJava {

    public static void main(String[] args) throws Exception {
        try {
            String shpath = "learn\\sh_test\\sh.sh";
            Process ps = Runtime.getRuntime().exec("cmd /c " + shpath);
            ps.waitFor();

            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String result = sb.toString();
            System.out.println(result);
            System.out.println("exit value " + ps.exitValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
