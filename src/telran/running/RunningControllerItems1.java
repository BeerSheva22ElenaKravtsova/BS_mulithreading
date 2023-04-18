package telran.running;

import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class RunningControllerItems1 {
	
	public class RunningControllerItems {
		private static final int MIN_DISTANCE = 100;
		private static final int MAX_DISTANCE = 1000;
		private static final int MIN_NUMBER_MEMBERS = 2;
		private static final int MAX_NUMBER_MEMBERS = 10;
		private String wrongInput = "Wrong input";

		public Menu menu() {
			Item start = Item.of("Start", io -> start(io));
			return new Menu("Menu", start, Item.exit());
		}

		private void start(InputOutput io) {
			int distance = io.readInt("Write distanse", wrongInput, MIN_DISTANCE, MAX_DISTANCE);
			int members = io.readInt("Write members", wrongInput, MIN_NUMBER_MEMBERS, MAX_NUMBER_MEMBERS);
			Trace1 game = new Trace1(distance, members);
			game.start();
			try {
				game.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// TODO Auto-generated method stub
		}

}
}
