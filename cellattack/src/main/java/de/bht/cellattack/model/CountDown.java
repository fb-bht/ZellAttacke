package de.bht.cellattack.model;


public class CountDown implements Runnable {

	@Override
	public void run() {
		for (int i = 100; i > 0; i = i - 10) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(i);
		}
	}
}
