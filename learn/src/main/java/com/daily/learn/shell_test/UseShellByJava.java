package com.daily.learn.shell_test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by SunGuiyong
 * on 2018/1/8.
 */
public class UseShellByJava {

    public static void main(String[] args) {

        try {
            String shpath = "learn\\sh_test\\sh.sh";
            String para = "test\" \"parameter";
            Process ps = Runtime.getRuntime().exec("cmd /c " + shpath + " " + para);
            ps.waitFor();
            System.out.println(System.getProperty("MESSAGE"));

            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String result = sb.toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
