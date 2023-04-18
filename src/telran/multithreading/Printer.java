package telran.multithreading;

public class Printer extends Thread {
	private String str;
	private int nRuns;

	public Printer(String str, int nRuns) {
		this.str = str;
		this.nRuns = nRuns;
	}

	// checked / unchecked exception

	@Override
	public void run() {
		for (int i = 0; i < nRuns; i++) {
			System.out.println(str);
			try {
				sleep(10);
				// он является статик потому что он переводит текущий объект в состояние
				// ожидания на какое-то время
				// в скобках время (на сколько милисекунд)
			} catch (InterruptedException e) {
//				throw new RuntimeException(e.getMessage());//он выполняется здесь если его разбудили
			}
		}
	}

}
