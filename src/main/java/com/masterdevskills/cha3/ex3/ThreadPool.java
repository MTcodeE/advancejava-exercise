package com.masterdevskills.cha3.ex3;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

//TODO: Implement this thread pool using ExecutorService
public class ThreadPool {

	ExecutorService executorService;
	ThreadPoolExecutor pool = (ThreadPoolExecutor) executorService;

	public ThreadPool(int poolSize) {
		executorService= Executors.newFixedThreadPool(poolSize);
	}

	private Runnable take() throws InterruptedException {

		throw new UnsupportedOperationException("not implemented");
	}

	public void submit(Runnable job) {
		//executorService.submit(job);
		pool.submit(job);
	}

	public int getRunQueueLength() {
		return pool.getPoolSize();

		//throw new UnsupportedOperationException("not implemented");
	}

	public void shutdown() {
		executorService.shutdown();
	}

}


