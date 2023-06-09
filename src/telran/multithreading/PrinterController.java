package telran.multithreading;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class PrinterController {

	public static void main(String[] args) throws InterruptedException {

		Printer printer1 = new Printer("#", 100);
		Printer printer2 = new Printer("*", 100);
		Instant start = Instant.now();
		printer1.start();
		printer2.start();// Старт запускает run в другом Стеке, если Ран - то ждем пока он выполнится и
							// выходим из Мейна?
		printer1.join();//waiting for printer1 termination
		printer2.join();//waiting for printer2 termination
		System.out.printf("Running time is %d milliseconds", ChronoUnit.MILLIS.between(start, Instant.now()));

	}

}

