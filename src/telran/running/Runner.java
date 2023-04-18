package telran.running;

import java.util.Random;

public class Runner extends Thread {
	private static final int MIN_SLEEP_TIME = 2;
	private static final int MAX_SLEEP_TIME = 5;
	private static Random random = new Random();
	int number;
	int distance;

	public Runner(int number, int distance) {
		this.number = number;
		this.distance = distance;
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
	}

	private long getSleepTime() {
		return random.nextLong(MIN_SLEEP_TIME, MAX_SLEEP_TIME);
	}
}
