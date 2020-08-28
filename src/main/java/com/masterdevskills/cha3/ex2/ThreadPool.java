package com.masterdevskills.cha3.ex2;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

//TODO: Implement this thread pool using BlockingQueue
public class ThreadPool {

	private BlockingQueue<Runnable> blockingQueueQueue = new LinkedBlockingDeque<>();

	private volatile boolean running = true;
	private ThreadGroup threadGroup = new ThreadGroup("MyThreadGroup");

	public ThreadPool(int poolSize) {

		for (int i = 0; i < poolSize; i++) {
			Job worker = new Job(threadGroup, "Worker " + i);
			worker.start();
		}
	}

	private Runnable take() throws InterruptedException {
		return blockingQueueQueue.take();
	}

	public void submit(Runnable job) {
		blockingQueueQueue.add(job);
	}

	public int getRunQueueLength() {

		return blockingQueueQueue.size();
	}

	public void shutdown() {
		this.running = false;
		this.threadGroup.interrupt();
	}

	private class Job extends Thread {
		public Job(ThreadGroup group, String name) {

			super(group, name);
		}

		public void run() {

			while (running && !interrupted()) {
				try {
					//final Runnable job = take();
					take().run();
				} catch (InterruptedException e) {
					this.interrupt();
				}
			}
			//TODO
			// we run in an infinite loop:
			// remove the next job from the linked list using take()
			// we then call the run() method on the job
		}
	}
}


