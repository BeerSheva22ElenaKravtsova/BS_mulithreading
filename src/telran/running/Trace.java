package telran.running;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Trace extends Thread {
	int distance;
	int members;
	Set<Runner> runners = new HashSet<>();
	ArrayList<Runner> results = new ArrayList<>();
	Instant timeOfStart = null;

	public Trace(int distance, int members) {
		this.distance = distance;
		this.members = members;
	}

	@Override
	public void run() {
		for (int i = 1; i <= members; i++) {
			runners.add(new Runner(i, distance, results));
		}
		runners.stream().forEach(Runner::start);
		timeOfStart = Instant.now();
		runners.stream().forEach(t -> {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}
}