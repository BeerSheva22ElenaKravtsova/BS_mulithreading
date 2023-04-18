package telran.multithreading;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

//какое мин и какое макс колво зерна
//читаем результат (какое-то число)
//макс threads*increments
//мин 2
//он ушел в runnable а не в sleep

//конкуренция
//синхронизация

public class Kolhoz extends Thread {

	private static final int N_TRUCKS = 100_000;
	private static final int LOAD = 1;// тонна
	private static final int N_RUNS = 1000;

	public static void main(String[] args) {
		Truck[] trucks = new Truck[N_TRUCKS];
		startTrucks(trucks);
		Instant start = Instant.now();
		waitTrucks(trucks);// ждем пока урожай не будет полностью собран
		displayResult(start);

	}

	private static void startTrucks(Truck[] trucks) {
		for (int i = 0; i < N_TRUCKS; i++) {
			trucks[i] = new Truck(LOAD, N_RUNS);
			trucks[i].start();
		}
	}

	private static void waitTrucks(Truck[] trucks) {
		Arrays.stream(trucks).forEach(t -> {
			try {
				t.join();
			} catch (InterruptedException e) {
			}
		});
	}

	private static void displayResult(Instant start) {
		System.out.printf("Running time %d, elevator1 contains %d tons, elevator2 contains %d tons\n",
				ChronoUnit.MILLIS.between(start, Instant.now()), Truck.getElevator1(), Truck.getElevator2());

	}
}
