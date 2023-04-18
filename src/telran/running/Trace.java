package telran.running;

import java.util.HashSet;
import java.util.Set;

public class Trace extends Thread {
	int distance;
	int members;
	int winner = 0;
	Set<Runner> runners = new HashSet<>();

	public Trace(int distance, int members) {
		this.distance = distance;
		this.members = members;
	}

	@Override
	public void run() {
		for (int i = 1; i <= members; i++) {
			runners.add(new Runner(i, distance));
		}
		runners.stream().forEach(Runner::start);
		while (winner == 0) {
			runners.stream().forEach(r -> {
				if (!r.isAlive()) {
					winner = r.number;
				}
			});

		}
	}
}

