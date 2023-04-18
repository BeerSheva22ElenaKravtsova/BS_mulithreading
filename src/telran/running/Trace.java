package telran.running;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Trace extends Thread {
	int distance;
	int members;
	int winner = 0;
	Set<Runner> runners = new HashSet<>();
	ArrayList<Runner> results = new ArrayList<>();

	public Trace(int distance, int members) {
		this.distance = distance;
		this.members = members;
	}

	@Override
	public void run() {
		for (int i = 1; i <= members; i++) {
			runners.add(new Runner(i, distance));
		}
		runners.stream().forEach(Runner::start);
		while (winner == 0) {
			runners.stream().forEach(r -> {
				if (r.finished.get() > 0) {
					winner = r.number;
				}
			});

		}
	}
}

//общий ArrayList потому что надо сохр порядок - этот лист явл объектом синхронизации - он общий
//в этот лист записать время выполнения этого цикла

//нюанс - вопрос где измерять это время (время завершения Среда) - в критической секции или не в ней. Он вр завершения отнять время старта
//ПОдумать где надо зафикс и опр время пробега этого Среда 
//если в ней то это мб после блокировки
//если не в ней - это перед блокировкой - но может получиться что мы ждем пока...?

//точка перехвата в Ранэйбл может быть...
//ответ - не в ней
//как бегуны бегут, как измеряется их время
//время начала у всех дб одинаково

//таблица:
//место - номерСреда - время его выполнения
//Place - Thread Number Running Time

//у всех общее время начала (это статическое поле или в классе Рэйс)