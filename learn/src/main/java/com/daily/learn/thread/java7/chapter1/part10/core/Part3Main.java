package com.daily.learn.thread.java7.chapter1.part10.core;

import com.daily.learn.thread.java7.chapter1.part10.task.UnsafeTask;

import java.util.concurrent.TimeUnit;

/**
 * Part3Main class of the UnsafeTask. Creates a Runnable task and
 * three Thread objects that run it.
 *
 */
public class Part3Main {

	/**
	 * Part3Main method of the UnsafeTaks. Creates a Runnable task and
	 * three Thread objects that run it.
	 * @param args
	 */
	public static void main(String[] args) {
		// Creates the unsafe task
		UnsafeTask task=new UnsafeTask();
		
		// Throw three Thread objects
		for (int i=0; i<3; i++){
			Thread thread=new Thread(task);
			thread.start();
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
