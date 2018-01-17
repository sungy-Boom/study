package com.daily.learn.Python_Java;

import org.python.util.PythonInterpreter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * Created by SunGuiyong
 * on 2018/1/8.
 */
public class UsePythonByJava {

    public static void main(String[] args) {

        // 不可以导入包
       /* PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("D:/self/study/pythonLearn/first.py");*/

        try {
            String text = "太差了";
            Process pr = Runtime.getRuntime().exec("python ./pythonLearn/first.py " + text);

            pr.waitFor();
            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line;
            LineNumberReader input = new LineNumberReader(in);
            line = input.readLine();

            in.close();
            System.out.println("result " + line);
        } catch (Exception e) {
            e.printStackTrace();
        }

      /*  try {
            List<String> command=new LinkedList<String>();
            command.add("python");
            command.add(edgeBuilderPath);
            command.add(appPathName);
            //ProcessBuilder need to separate string into list string not one long string
            ProcessBuilder pb = new ProcessBuilder(command);
            Process p = pb.start(); // Start the process.
            int result=p.waitFor(); // Wait for the process to finish.
            System.out.println("Script executed successfully"+result);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
