package telran.multithreading;

import java.util.Scanner;

public class SymbolPrinterController1 {
	static String printingWord = "Hello";
	static String quit = "q";
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws InterruptedException {
		SymbolPrinter1 printer = new SymbolPrinter1(printingWord);
		printer.start();
		while(!scanner.nextLine().equals(quit)) {
			printer.interrupt();
		}
	}

	

}
