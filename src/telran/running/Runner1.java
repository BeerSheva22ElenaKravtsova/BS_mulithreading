package telran.running;

import java.util.Random;

public class Runner1 extends Thread{
	private static final int MIN_SLEEP_TIME = 2;
	private static final int MAX_SLEEP_TIME = 5;
	private static Random random = new Random();
	int number;
	int distance;

	public Runner1(int number, int distance) {
		this.number = number;
		this.distance = distance;
	}

	@Override
	public void run() {
		for (int i = 0; i < distance; i++) {
			try {
				sleep(getRandomTimeOfSleep());
			} catch (InterruptedException e) {
			}
		}
	}

	private long getRandomTimeOfSleep() {
		return random.nextLong(MIN_SLEEP_TIME, MAX_SLEEP_TIME);
	}

}
