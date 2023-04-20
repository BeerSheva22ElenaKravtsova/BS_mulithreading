package telran.multithreading;

import java.time.LocalTime;

public class DeadlockObj__BookRestaurant extends Thread {
	String name;
	String friendname;
	LocalTime timeOfResev;
	Integer tableNumber;

	public DeadlockObj__BookRestaurant(String name, String friendname, LocalTime dinnerTime, Integer tableNumber) {
		this.name = name;
		this.timeOfResev = dinnerTime;
		this.friendname = friendname;
		this.tableNumber = tableNumber;
	}

	@Override
	public void run() {
		goToDinner();
	}

	void goToDinner() {
		synchronized (timeOfResev) {
			System.out.printf("%s invite to Dinner %s at %s\n", this.name, this.friendname, timeOfResev.toString());
			synchronized (tableNumber) {
				System.out.printf("%s agreed to go to Dinner with %s at %s and asked to reserve table number %d close to window\n", friendname, name,
						timeOfResev.toString());

			}
		}
	}

}
