package telran.multithreading.util;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class MyLinkedBlockingQueue<E> implements BlockingQueue<E> {
	LinkedList<E> list = new LinkedList<>();
	int limit = Integer.MAX_VALUE;// очередь ограничена

	ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	ReadLock readLock = lock.readLock();
	WriteLock writeLock = lock.writeLock();

	Condition conditionReadLock = writeLock.newCondition();//только на РайтЛокке потому что только он меняет состояние
	Condition conditionWriteLock = writeLock.newCondition();

	public MyLinkedBlockingQueue(LinkedList<E> list) {
		this.list = list;
	}

	public MyLinkedBlockingQueue(LinkedList<E> list, int limit) {
		this.list = list;
		this.limit = limit;
	}

	@Override
	public E remove() {
		writeLock.lock();
		try {
			return list.remove();
		} finally {
			writeLock.unlock();
		}
	}

	@Override
	public E poll() {
		writeLock.lock();
		try {
			return list.poll();
		} finally {
			writeLock.unlock();
		}
	}

	@Override
	public E element() {
		readLock.lock();
		try {
			return list.element();
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public E peek() {
		readLock.lock();
		try {
			return list.peek();
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public int size() {
		readLock.lock();
		try {
			return list.size();
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public boolean isEmpty() {
		readLock.lock();
		try {
			return list.isEmpty();
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public Iterator<E> iterator() {
		readLock.lock();
		try {
			return list.iterator();
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public Object[] toArray() {
		readLock.lock();
		try {
			return list.toArray();
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public <T> T[] toArray(T[] a) {
		readLock.lock();
		try {
			return list.toArray(a);
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		readLock.lock();
		try {
			return list.containsAll(c);
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		writeLock.lock();
		try {
			c.forEach(e -> {
				try {
					put(e);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			});
			return true;// возвращает тру если коллекция не пустая??
		} finally {
			writeLock.unlock();
		}
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		writeLock.lock();
		try {
			return list.removeAll(c);
		} finally {
			writeLock.unlock();
		}
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		writeLock.lock();
		try {
			return list.retainAll(c);
		} finally {
			writeLock.unlock();
		}
	}

	@Override
	public void clear() {
		writeLock.lock();
		try {
			list.clear();
		} finally {
			writeLock.unlock();
		}
	}

	@Override
	public boolean add(E e) {
		writeLock.lock();
		try {
			if (remainingCapacity() == 0) {
				throw new RuntimeException();
			}
			return list.add(e);
		} finally {
			writeLock.unlock();
		}
	}

	@Override
	public boolean offer(E e) {
		writeLock.lock();
		try {
			return list.size() < limit ? list.offer(e) : false;
		} finally {
			writeLock.unlock();
		}
	}

	@Override
	public void put(E e) throws InterruptedException {
		writeLock.lock();
		try {
			while (remainingCapacity() == 0) {
				conditionWriteLock.await();
			}
			list.add(e);
		} finally {
			writeLock.unlock();
		}
	}

	// вставляет элемент в очередь если нет - ждет сколько-то времени и уезжает
	@Override
	public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
		writeLock.lock();
		boolean res = false;
		try {
			if (remainingCapacity() == 0) {
				res = conditionWriteLock.await(timeout, unit);
			}
			if (res) {
				list.add(e);
			}
			return res;
		} finally {
			writeLock.unlock();
		}
	}

	// wrire
	@Override
	public E take() throws InterruptedException {
		readLock.lock();
		try {
			while (list.isEmpty()) {
				conditionWriteLock.await();
			}
			return list.removeFirst();
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public E poll(long timeout, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
//		writeLock.lock();
//		try {
//			if() {}
//			
//			return list.poll();}
//			
//		} finally {
//			writeLock.unlock();
//		}
	}

	@Override
	public int remainingCapacity() {
		readLock.lock();
		try {
			return limit - list.size();
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public boolean remove(Object o) {
		writeLock.lock();
		boolean res = false;
		try {
			res = list.remove(o);
			if (res) {
				conditionWriteLock.signalAll();
			}
			return res;
		} finally {
			writeLock.unlock();
		}
	}

	@Override
	public boolean contains(Object o) {
		readLock.lock();
		try {
			return list.contains(o);
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public int drainTo(Collection<? super E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int drainTo(Collection<? super E> c, int maxElements) {
		throw new UnsupportedOperationException();
	}
}