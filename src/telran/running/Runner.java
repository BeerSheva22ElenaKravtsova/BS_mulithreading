package telran.running;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Runner extends Thread {
	private static final int MIN_SLEEP_TIME = 2;
	private static final int MAX_SLEEP_TIME = 5;
	private static Random random = new Random();
	int number;
	int distance;
	AtomicInteger finished = new AtomicInteger(-1);

	public Runner(int number, int distance) {
		this.number = number;
		this.distance = distance;
		finished = new AtomicInteger(-1);
	}

	@Override
	public void run() {
		for (int i = 0; i < distance; i++) {
			try {
				sleep(getSleepTime());
			} catch (InterruptedException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		finished.compareAndSet(-1, number);
		
	}

	private long getSleepTime() {
		return random.nextLong(MIN_SLEEP_TIME, MAX_SLEEP_TIME);
	}
}

//задание
//не определение победителя
//должна быть таблица результатов кто на каком месте
//в таблице дб указано время
//общий ArrayList потому что надо сохр порядок - этот лист явл объектом синхронизации - он общий
//в этот лист записать время выполнения этого цикла
//для таблицы результатов

//нюанс - вопрос где измерять это время (время завершения Среда) - в критической секции или не в ней. Он вр завершения отнять время старта
//ПОдумать где надо зафикс и опр время пробега этого Среда 
//если в ней то это мб после блокировки
//если не в ней - это перед блокировкой - но может получиться что мы ждем пока...?

//точка перехвата в Ранэйбл может быть...
//ответ - не в ней
//как бегуны бегут, как измеряется их время
//время начала у всех дб одинаково

//таблица:
// место - номерСреда - время его выполнения
//Place - Thread Number Running Time

//у всех общее время начала (это статическое поле или в классе Рэйс)
//
