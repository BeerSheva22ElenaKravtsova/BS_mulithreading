package telran.multithreading;

import java.util.Scanner;

public class SymbolsPrinterController {
	static String printingWord = "Hello";
	static String quit = "q";
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws InterruptedException {
		SymbolsPrinter printer = new SymbolsPrinter(printingWord);
		printer.start();
		while (!scanner.nextLine().equals(quit)) {
			printer.interrupt();
		}
	}
}
