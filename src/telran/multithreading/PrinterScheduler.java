package telran.multithreading;

public class PrinterScheduler extends Thread {
	int printerNumber;
	int amountNumbers;
	int portion;
	PrinterScheduler nextPrinter;

	public PrinterScheduler(int printerNumber, int amountNumbers, int portion, PrinterScheduler nextPrinter) {
		this.printerNumber = printerNumber;
		this.amountNumbers = amountNumbers;
		this.portion = portion;
		this.nextPrinter = nextPrinter;
	}

	public void setNextPrinter(PrinterScheduler nextPrinter) {
		this.nextPrinter = nextPrinter;
	}

	@Override
	public void run() {
		while (amountNumbers > 0) {
			try {
				join();// stop and wait for the end of current Thread, while it will be interrupted
			} catch (InterruptedException e) {
				System.out.println(String.valueOf(printerNumber).repeat(portion));
				amountNumbers -= portion;
				nextPrinter.interrupt();
			}
		}
	}
}