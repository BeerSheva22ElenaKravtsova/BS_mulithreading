package telran.multithreading;

public class TimerTestAppl {

	public static void main(String[] args) throws InterruptedException {
		Timer timer = new Timer();
		timer.start();
		// running imitation
		Thread.sleep(5_000);// 5 секунд

		timer.interrupt();
		Thread.sleep(5_000);
		Thread mainThread = Thread.currentThread();
		mainThread.interrupt();
		//mainThread.join();//for get Exception
		
		Thread.sleep(100000001);//for get Exception

	}

}



