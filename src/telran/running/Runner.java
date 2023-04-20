package telran.running;

import java.time.Instant;
import java.util.Random;

public class Runner extends Thread {
	private static final int MIN_SLEEP_TIME = 2;
	private static final int MAX_SLEEP_TIME = 5;
	private static Random random = new Random();
	int number;
	int distance;
	private Trace trace;
	Instant timeOfFinish = null;

	public Runner(int number, int distance, Trace trace) {
		this.number = number;
		this.distance = distance;
		this.trace = trace;
	}

	@Override
	public void run() {
		for (int i = 0; i < distance; i++) {
			try {
				sleep(getSleepTime());
			} catch (InterruptedException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		trace.lock.lock();// Thread захватил
		try {
			timeOfFinish = Instant.now();
			trace.results.add(this);
		} finally {// нужно чтобы в любом случае была разблокировка (в блоке try в каких-то случаях
					// мб исключения)
			trace.lock.unlock();// Thread освободил
		}
	}

	private long getSleepTime() {
		return random.nextLong(MIN_SLEEP_TIME, MAX_SLEEP_TIME);
	}
}
