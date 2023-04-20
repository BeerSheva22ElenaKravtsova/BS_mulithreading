package telran.multithreading;

import java.time.LocalTime;

public class DeadlockObjSecond__BookRestaurant extends Thread {
	String name;
	String friendname;
	LocalTime timeOfResev;
	Integer tableNumber;

	public DeadlockObjSecond__BookRestaurant(String name, String friendname, LocalTime dinnerTime, Integer tableNumber) {
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
		synchronized (tableNumber) {
			System.out.printf("%s invite to Dinner %s and asked to reserve table number %d close to window\n", name,
					friendname, tableNumber);
			synchronized (timeOfResev) {
				System.out.printf("%s agreed to go to Dinner with %s and asked to meet at %s \n", friendname, name,
						timeOfResev.toString());

			}
		}
	}

}
