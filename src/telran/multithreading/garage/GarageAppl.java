package telran.multithreading.garage;

public class GarageAppl {
	private static final int NUMBER_OF_EMPLOYEES = 10;
	private static final int LENGTH_OF_QUEUE = 10;
	private static final long TIME_OUT_FOR_QUEUE = 30;
	private static final long MODELING_TIME = 480 * 21 * 6;// = 60_480

	public static void main(String[] args) {
		new Garage(LENGTH_OF_QUEUE, NUMBER_OF_EMPLOYEES, TIME_OUT_FOR_QUEUE, MODELING_TIME).start();
	}
}
