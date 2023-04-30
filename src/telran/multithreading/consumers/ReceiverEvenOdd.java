package telran.multithreading.consumers;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ReceiverEvenOdd extends Thread {
	BlockingQueue<String> messageBox;
	public static AtomicInteger counter = new AtomicInteger(0);

	public ReceiverEvenOdd(BlockingQueue<String> messageBox) {
		this.messageBox = messageBox;
	}

	private void display(String message) {
		System.out.printf("thread: %s; received message: %s\n", getName(), message);
		counter.incrementAndGet();
	}
	@Override
	public void run() {
		while (true) {
			String message;
			try {
				message = messageBox.take();
				display(message);
			} catch (InterruptedException e) {
				do {
					message = messageBox.poll();
					if (message !=null) {
						display(message);
					}
				} while (message != null);
				break;
			}

		}
	}
}