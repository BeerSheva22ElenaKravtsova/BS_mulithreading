package telran.running;

import telran.view.Item;
import telran.view.StandardInputOutput;

public class RunningAppl1 {

	public static void main(String[] args) {
		RunningControllerItems gitController = new RunningControllerItems();
		Item menu = gitController.menu();
		menu.perform(new StandardInputOutput());

	}

}
