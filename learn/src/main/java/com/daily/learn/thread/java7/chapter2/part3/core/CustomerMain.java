package com.daily.learn.thread.java7.chapter2.part3.core;

import com.daily.learn.thread.java7.chapter2.part3.task.Consumer;
import com.daily.learn.thread.java7.chapter2.part3.task.EventStorage;
import com.daily.learn.thread.java7.chapter2.part3.task.Producer;

/**
 * CustomerMain class of the example
 */
public class CustomerMain {

	/**
	 * CustomerMain method of the example
	 */
	public static void main(String[] args) {
		
		// Creates an event storage
		EventStorage storage=new EventStorage();
		
		// Creates a Producer and a Thread to run it
		Producer producer=new Producer(storage);
		Thread thread1=new Thread(producer);

		// Creates a Consumer and a Thread to run it
		Consumer consumer=new Consumer(storage);
		Thread thread2=new Thread(consumer);
		
		// Starts the thread
		thread2.start();
		thread1.start();
	}

}
