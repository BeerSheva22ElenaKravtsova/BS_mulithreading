package telran.multithreading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MessageBox {
	String message;
	Lock lock = new ReentrantLock();
	Condition waitingConsumer = lock.newCondition();// условие ожидания консьюмера
	Condition waitingProducer = lock.newCondition();// условие ожидания продюссера

	public void put(String message) throws InterruptedException {
		lock.lock();
		try {
			while (this.message != null) {
				waitingProducer.await(); // if wait() - it'd be objects functionality used
				// вместо wait - если бы был здесь wait - он войдет в ожидание и там вечно
				// останется потому что по этому монитору не будет сигнала
			}
			this.message = message;
			waitingConsumer.signal(); // instead of notifyAll
			// notifyAll потому что может быть несколько сендеров, signal вместо notifyAll()
			// и будим и Консьюмера одного следующего
		} finally {
			lock.unlock();
		}
	}

	public String take() throws InterruptedException {
		lock.lock();
		try {
			while (message == null) {
				waitingConsumer.await();
			}
			String res = message;
			message = null;
			waitingProducer.signal();
			return res;
		} finally {
			lock.unlock();
		}
	}

	public String get() {
		lock.lock();
		try {
			String res = message;
			message = null;
			if (res != null) {
				waitingProducer.signal();
			}
			return res;
		} finally {
			lock.unlock();
		}
	}
}