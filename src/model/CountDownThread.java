package model;

import javafx.application.Platform;

/*
 * Thread that starts a countdown
 * 
 * @author Stefanie S.
 */
public class CountDownThread extends Thread {
	
	Arena orb1, orb2; 
	int sec;
		
	public CountDownThread() {}
	
	/*
	 * Count down the seconds
	 */
	private int decrementCount() {
		sec--;
		while (sec <= 0) {
			sec = 20;
			System.out.println("Du kannst einen neuen Angriff starten.");
			break;
		}
		
		return sec;
	}
	
	@Override
	public void run() {
		
		while(true) {
			try {
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// UI update			
			Platform.runLater(() -> {
				//...
			});
			
			decrementCount();
			System.out.println("Pause " + sec + " Sekunden");
		}
		
	}
}
