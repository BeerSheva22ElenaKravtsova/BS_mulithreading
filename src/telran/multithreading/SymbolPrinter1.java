package telran.multithreading;

public class SymbolPrinter1 extends Thread {
	private static final long SLEEP_TIME = 1_000;
	String symbols;
	long timeout;
	int printingLetter = 0;

	public SymbolPrinter1(String symbols) {
		this.symbols = symbols;
		timeout = SLEEP_TIME;
		setDaemon(true);
	}

	@Override
	public void run() {
		while (true) {
			System.out.println(symbols.charAt(printingLetter));
			try {
				sleep(timeout);
			} catch (InterruptedException e) {
				printingLetter++;
				if (printingLetter >= symbols.length()) {
					printingLetter = 0;
				}
			}
		}
	}
}
