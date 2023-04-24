package telran.multithreading;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

public class ListOperations extends Thread {
	int probUpdate;
	List<Integer> list;
	int nRuns;
	public Lock readLock;
	public Lock writeLock;
	AtomicInteger counter;// будет считать сколько раз Thread находился в состоянии блокировки

	public ListOperations(int probUpdate, List<Integer> list, int nRuns, Lock readLock, Lock writeLock,
			AtomicInteger counter) {
		this.probUpdate = probUpdate;
		this.list = list;
		this.nRuns = nRuns;
		this.readLock = readLock;
		this.writeLock = writeLock;
		this.counter = counter;
	}

	@Override
	public void run() {
		ThreadLocalRandom tlr;
		for (int i = 0; i < nRuns; i++) {
			tlr = ThreadLocalRandom.current();
			if (tlr.nextInt(10, 100) < probUpdate) {// если это число в заданных границах меньше Апейта - вызываем
													// Апдейт
				updateList();
			} else {
				readList();
			}
		}
	}

	private void updateList() {
		lock(writeLock);
		try {
			list.remove(0);
			list.remove(0);
			list.remove(0);
			list.add(100);
			list.add(100);
			list.add(100);
		} finally {
			writeLock.unlock();
		}
	}

	private void readList() {
		lock(readLock);
		try {
			int size = list.size();
			for (int i = 0; i < 100; i++) {
				list.get(size - 1);
			}
		} finally {
			readLock.unlock();
		}
	}

	private void lock(Lock lock) {
		while (!lock.tryLock()) {// tryLock - пытается захватить Lock (если он возвращает true - то он захватывает и Lock закрывается) или если он закрыт то Thread не уходит в
									// блокировку, но метод выдает False - что вход не удался
			counter.incrementAndGet();// увеличиваем пока что Lock закрыт
		}
	}

}
