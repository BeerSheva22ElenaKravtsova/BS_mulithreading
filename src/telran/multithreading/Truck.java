package telran.multithreading;

public class Truck extends Thread {
	private int load; // вместимость грузовика

	private static long elevator1; // куда складывается зерно //сколько зерна
	private static long elevator2;
	private final static Object mutex = new Object();

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

	synchronized static private void loadElevator1(int load) {
		elevator1 += load;
	}

	static private void loadElevator2(int load) {
		synchronized (mutex) {//mutex - здесь разные критические секции (объекты синхронизации) МОЖНО ЗАМЕНИТЬ НА Track.class - здесь 1 критическая секция
			elevator2 += load;
		}
	}

	public static long getElevator1() {
		return elevator1;
	}

	public static long getElevator2() {
		return elevator2;
	}

}
