package model;

public class CountDown extends Thread {
	public void run() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		System.out.println("Pause");
	}
}
