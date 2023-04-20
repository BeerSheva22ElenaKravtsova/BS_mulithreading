package telran.multithreading;

import java.time.LocalTime;

public class DeadLock_BookRestaurant {
	static String womanName = "Mary";
	static String menName = "John";
	static LocalTime timeOfResev = LocalTime.of(18, 0);
	static Integer tableNumber = 5;

	public static void main(String[] args) {
		DeadlockObj__BookRestaurant men = new DeadlockObj__BookRestaurant(menName, womanName, timeOfResev, tableNumber);
		DeadlockObjSecond__BookRestaurant woman = new DeadlockObjSecond__BookRestaurant(womanName, menName, timeOfResev, tableNumber);
		men.start();
		woman.start();

	}

}