package telran.running;

import telran.view.Item;
import telran.view.StandardInputOutput;

public class RunningAppl {
	
	public static void main(String[] args) {
		RunningControllerItems runningController = new RunningControllerItems();
		Item menu = runningController.menu();
		menu.perform(new StandardInputOutput());
	}
}
