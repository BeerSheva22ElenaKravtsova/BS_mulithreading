package telran.multithreading;

import java.time.LocalTime;

public class Deadlock {
	static String womanName = "Mary";
	static String menName = "John";
	static LocalTime timeOfResev = LocalTime.of(18, 0);
	static Integer tableNumber = 5;

	public static void main(String[] args) {
		X x1 = new X();
		X x2  = new X();
		x1.start();
		x2.start();
		

	}

}
