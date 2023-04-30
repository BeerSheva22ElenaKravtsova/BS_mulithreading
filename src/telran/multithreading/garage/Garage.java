package telran.multithreading.garage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Garage extends Thread {
	private static final int MIN_PROBABILITY_PERCENT = 0;
	private static final int MAX_PROBABILITY_PERCENT = 100;
	private static final int PROBABILITY_PERCENT = 15;
	BlockingQueue<Car> queue;
	long timeOutForQueue;
	long modelingTime;
	int numberOfRejectedCars;
	Random random;
	List<Employee> employees;

	public Garage(int lenghtOfQueue, int numberOfEmployees, long timeOutForQueue, long modelingTime) {
		this.timeOutForQueue = timeOutForQueue;
		this.modelingTime = modelingTime;
		queue = new LinkedBlockingQueue<>(lenghtOfQueue);
		random = new Random();
		employees = new ArrayList<>();

		for (int i = 0; i < numberOfEmployees; i++) {
			Employee employee = new Employee(queue);
			employees.add(employee);
			employee.start();
		}
	}

	@Override
	public void run() {
		Car car;
		for (int i = 0; i < modelingTime; i++) {
			if (getProbabilityCar() <= PROBABILITY_PERCENT) {
				car = new Car();
				try {
					boolean isAccepted = queue.offer(car, timeOutForQueue, TimeUnit.MILLISECONDS);
					if (!isAccepted) {
						numberOfRejectedCars++;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		while (employees.stream().anyMatch(e -> e.inRecoveringProcess)) {
		}
		employees.forEach(e -> e.interrupt());
		employees.forEach(e -> {
			try {
				e.join();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		});
		displayResults();
	}

	private int getProbabilityCar() {
		return random.nextInt(MIN_PROBABILITY_PERCENT, MAX_PROBABILITY_PERCENT + 1);
	}

	private void displayResults() {
		System.out.printf(
				"Number of recovered cars = %d , Number of rejected cars = %d, Recovering time = %d, Idle time = %d",
				Employee.numberOfRecoveryCars.get(), numberOfRejectedCars, Employee.recoveryTime.get(),
				Employee.waitingTime.get());
	}
}
