package telran.running;

import java.time.Instant;
//import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
//import java.util.List;

import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class RunningControllerItems {
	private static final int MIN_DISTANCE = 100;
	private static final int MAX_DISTANCE = 1000;
	private static final int MIN_NUMBER_MEMBERS = 2;
	private static final int MAX_NUMBER_MEMBERS = 2000;
//	private static final int DISTANCE = 10;
//	private static final int DISTANCE1 = 2;
//	private String dist = " ".repeat(DISTANCE);
//	private String dist1 = " ".repeat(DISTANCE1);
	private String wrongInput = "Wrong input";
	int i = 1;

	public Menu menu() {
		Item start = Item.of("Start", io -> start(io));
		return new Menu("Menu", start, Item.exit());
	}

	private void start(InputOutput io) {
		int distance = io.readInt(String.format("Enter distance in range from %d to %d", MIN_DISTANCE, MAX_DISTANCE),
				wrongInput, MIN_DISTANCE, MAX_DISTANCE);
		int members = io.readInt(
				String.format("Enter number of members in range from %d to %d", MIN_NUMBER_MEMBERS, MAX_NUMBER_MEMBERS),
				wrongInput, MIN_NUMBER_MEMBERS, MAX_NUMBER_MEMBERS);
		Trace trace = new Trace(distance, members);
		trace.start();
		try {
			trace.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ArrayList<Runner> table = trace.results;
		Instant prev = table.get(0).timeOfFinish;
		int size = table.size();
		int count = 0;
		for (int i = 1; i < size; i++) {
			Instant current = table.get(i).timeOfFinish;
			if (current.isBefore(prev)) {
				count++;
			}
			prev = current;
		}

		io.writeLine(String.format("Number of threads is %d, Count of results violations is %d", size, count));

//		io.writeLine(String.format("Place %s Thread Number %s Running Time", dist1, dist1));

//		trace.results.forEach(r -> {
//			io.writeLine(String.format("%s %s %s %s %s", i++, dist, r.number, dist,
//					ChronoUnit.MILLIS.between(trace.timeOfStart, r.timeOfFinish)));
//		});
	}
}
