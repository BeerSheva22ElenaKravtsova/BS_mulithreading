package telran.multithreading;

import java.time.LocalTime;

public class Deadlock {
	static String womanName = "Mary";
	static String menName = "John";
	static LocalTime timeOfResev = LocalTime.of(18, 0);
	static Integer tableNumber = 5;

	public static void main(String[] args) {
		DeadlockObj men = new DeadlockObj(menName, womanName, timeOfResev, tableNumber);
		DeadlockObjSecond woman = new DeadlockObjSecond(womanName, menName, timeOfResev, tableNumber);
		men.start();
		woman.start();
		

	}

}
