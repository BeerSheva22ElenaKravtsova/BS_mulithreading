package telran.multithreading;

import java.util.concurrent.atomic.AtomicLong;

public class Truck extends Thread {
	private int load; // вместимость грузовика

	private static AtomicLong elevator1 = new AtomicLong(); // куда складывается зерно //сколько зерна
	private static AtomicLong elevator2 = new AtomicLong();

	private int nRuns;// сколько раз он едет

	public Truck(int load, int nRuns) {
		this.load = load;
		this.nRuns = nRuns;
	}

	@Override
	public void run() {
		for (int i = 0; i < nRuns; i++) {
			loadElevator1(load);
			loadElevator2(load);
		}
	}

	static private void loadElevator1(int load) {
		// += перехватываемая операция
		elevator1.addAndGet(load);// это неперехватываемая операция
	}

	static private void loadElevator2(int load) {
		elevator2.addAndGet(load);
	}

	public static long getElevator1() {
		return elevator1.get();// возвращает long
	}

	public static long getElevator2() {
		return elevator2.get();
	}

}
