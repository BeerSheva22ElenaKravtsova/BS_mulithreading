package telran.multithreading;

import java.util.ArrayList;
import java.util.List;

import telran.multithreading.consumers.ReceiverEvenOdd;
import telran.multithreading.producers.Sender;

public class SenderReceiversAppl {

	private static final int N_MESSAGES = 21;
	private static final int N_RECEIVERS = 10;

	public static void main(String[] args) throws InterruptedException {
		MessageBox messageBox = new MessageBox();
		Sender sender = new Sender(messageBox, N_MESSAGES);
		sender.start();
		List<ReceiverEvenOdd> receivers = new ArrayList<>();
		for (int i = 0; i < N_RECEIVERS; i++) {
			ReceiverEvenOdd receiver = new ReceiverEvenOdd(messageBox);
			receivers.add(receiver);
			receiver.start();
		}
		sender.join();
		receivers.forEach(r -> r.interrupt());
		receivers.forEach(r -> {
			try {
				r.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		System.out.println(ReceiverEvenOdd.counter);
	}

}
