package telran.multithreading.garage;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Employee extends Thread {
	Instant timeOfStartWaiting = null;
	static AtomicInteger numberOfRecoveryCars = new AtomicInteger();
	static AtomicInteger waitingTime = new AtomicInteger();
	static AtomicInteger recoveryTime = new AtomicInteger();
	BlockingQueue<Car> queue;
	boolean inRecoveringProcess = false;

	public Employee(BlockingQueue<Car> queue) {
		this.queue = queue;
	}

	public boolean isFlag() {
		return inRecoveringProcess;
	}

	@Override
	public void run() {
		Car car;
		while (true) {
			try {
				timeOfStartWaiting = Instant.now();
				car = queue.take();
				waitingTime.addAndGet((int) ChronoUnit.MILLIS.between(timeOfStartWaiting, Instant.now()));
				inRecoveringProcess = true;
				sleep(car.recoveryTime);
				numberOfRecoveryCars.incrementAndGet();
				recoveryTime.addAndGet(car.recoveryTime);
				inRecoveringProcess = false;
			} catch (InterruptedException e) {
				do {
					car = queue.poll();
					if (car != null) {
						try {
							sleep(car.recoveryTime);
							recoveryTime.addAndGet(car.recoveryTime);
							numberOfRecoveryCars.getAndIncrement();
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
					}
				} while (car != null);
				break;
			}
		}
	}
}