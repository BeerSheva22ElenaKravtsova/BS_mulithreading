package telran.multithreading;

import java.io.PrintWriter;
import java.util.Scanner;

public class PrinterSchedulerController {
	static int amountNumbers;
	static int portion;
	static int numberOfPrinters;
	static PrinterScheduler[] printers;

	public static void main(String[] args) throws InterruptedException {

		try (PrintWriter writer = new PrintWriter(System.out, true); Scanner scanner = new Scanner(System.in)) {
			writer.println("Write amount of printers");
			numberOfPrinters = scanner.nextInt();
			printers = new PrinterScheduler[numberOfPrinters];

			writer.println("Write amount of numbers");
			amountNumbers = scanner.nextInt();

			writer.println("Write number of numbers which will be printed in each string");
			portion = scanner.nextInt();
			
			printers[numberOfPrinters - 1] = new PrinterScheduler(numberOfPrinters, amountNumbers, portion, null);
			for (int i = numberOfPrinters - 2; i >= 0; i--) {
				printers[i] = new PrinterScheduler(i + 1, amountNumbers, portion, printers[i + 1]);
			}
			printers[numberOfPrinters - 1].setNextPrinter(printers[0]);

			for (PrinterScheduler pr : printers) {
				pr.start();
			}

			printers[0].interrupt();
			printers[numberOfPrinters - 1].join(); //waiting for the last Thread will be dead
		}

		
	}

}
