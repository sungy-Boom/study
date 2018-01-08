package com.daily.learn.Python_Java;

import org.python.util.PythonInterpreter;

/**
 * Created by SunGuiyong
 * on 2018/1/8.
 */
public class UsePythonByJava {

    public static void main(String[] args) {

        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("D:/self/study/pythonLearn/first.py");
    }
}
