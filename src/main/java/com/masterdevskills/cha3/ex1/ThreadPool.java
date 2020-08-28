package com.masterdevskills.cha3.ex1;


import java.util.LinkedList;
import java.util.Queue;

//TODO: Create a thread group field
// Create a LinkedList field containing Runnable
// Hint: Since LinkedList is not thread-safe, we need to synchronize it
public class ThreadPool {
	private Queue<Runnable> jobs=new LinkedList<>();
	private int LIMIT;
	private Object lock = new Object();
	private volatile boolean running = true;
	private ThreadGroup threadGroup = new ThreadGroup("MyThreadGroup");


	public ThreadPool(int poolSize) {
		for (int i = 0; i < poolSize; i++) {
			Worker worker = new Worker(threadGroup, "Worker " + i);
			worker.start();
		}
		this.LIMIT=poolSize;

	}

	private Runnable take() throws InterruptedException {
		//TODO if the LinkedList is empty, we wait
		// remove the first job from the LinkedList and return it
		synchronized (lock) {
			if (jobs.isEmpty()) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			var remove = jobs.poll();
			lock.notifyAll();
			return remove;
		}
	}

	public void submit(Runnable job) {
		synchronized (lock) {
			while (jobs.size() == LIMIT) {
				try {
					lock.wait(); //
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			jobs.add(job);
			lock.notifyAll();
		}

		//TODO Add the job to the LinkedList and notifyAll
	}

	public synchronized int getRunQueueLength() {
		//TODO return the length of the LinkedList
		// remember to also synchronize!
		return jobs.size();
	}

	public   void shutdown() {
		//TODO this should call stop() on the ThreadGroup.
		this.running = false;
		this.threadGroup.interrupt();

	}

	private class Worker extends Thread {

		public Worker(ThreadGroup group, String name) {

			super(group, name);

		}


		public void run() {

			while (running && !interrupted()) {
				try {
					final Runnable job = take();
					job.run();
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


