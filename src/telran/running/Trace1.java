package telran.running;

import java.util.HashSet;
import java.util.Set;

public class Trace1 extends Thread {
	int distance;
	int members;
	int winner = 0;
	Set<Runner1> runners = new HashSet<>();

	public Trace1(int distance, int members) {
		this.distance = distance;
		this.members = members;
	}

	@Override
	public void run() {
		for (int i = 0; i < members; i++) {
			runners.add(new Runner1(i, distance));
		}
		runners.stream().forEach(Runner1::start);
		if (winner == 0) {
			runners.stream().forEach(r -> {
				if (!r.isAlive()) {
					winner = r.number;
				}
			});
		}
	}
}
