package telran.multithreading.garage;

import java.util.Random;

public class Car {
	private static final int MIN_RECOVERY_TIME = 30;
	private static final int MAX_RECOVERY_TIME = 480;
	public final int recoveryTime;
	private static Random random = new Random();

	public Car() {
		this.recoveryTime = getRecoveryTime();
	}

	private int getRecoveryTime() {
		return random.nextInt(MIN_RECOVERY_TIME, MAX_RECOVERY_TIME + 1);
	}

}
