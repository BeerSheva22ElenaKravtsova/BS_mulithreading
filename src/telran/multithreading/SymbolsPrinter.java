package telran.multithreading;

public class SymbolsPrinter extends Thread {
	private static final long SLEEP_TIME = 2_000;
	String symbols;
	long timeout;
	int printingLetter = 0;

	public SymbolsPrinter(String symbols) {
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


//без консольной аппликации
//задаем константы - можно и конольну аппликаци/ где мы задаем
//количество Средов Принтеров (от 2 до 50)
//колво Чисел которые дб напечатаны каждым принтером

//точная сихронизация в плане - допустимм сказано 3 принтера
//колво чисел - 100 чисел
//порция - 5 (сколько каждый принтер печатает) - 5 чисел в линию

//у каждого Среда есть свой номер от 1
//каждый Сред печатает 100 чисел
//печатаются строго по порциям 11111 -Ю 22222 -Ю 333333 -Ю11111ююю
//

//управляет контроллер
//внутри СредаПринтера есть его номер, колво печат чисел( у всех одинак), ссылка на след ПринтерСред.
//у Третьего ссылка на первый

//начинается с того что все спят

//контроллер строит все Среды
//дает каждому среду ссылку на след Сред
//последнему на первый

//запущена цепочка
//при запуске все уходят в сон
//Контроллер будит 1й сред и все

//1й сред на пробуждении печатает порцию -Ю будит второй и уходит спать
//до тех пор пока не будут напечатаны все числа(20 порций)

