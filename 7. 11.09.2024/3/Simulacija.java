import java.util.*;
import java.io.*;

public class Simulacija extends Thread {
	Stek stek = new Stek();
	Dodavanje d = new Dodavanje(stek);
	Uklanjanje u = new Uklanjanje(stek);
	boolean jeKraj = false;
	Timer timer;
	
	@Override
	public void run() {
		System.out.println("Simulacija je pocela\n");
		okoncajSimulaciju(this);
		d.start();
		u.start();
		while(!jeKraj) {
			try {
				synchronized(this) {
					wait();
				}
			} catch(InterruptedException e) {
				System.err.println(e);
			}
			jeKraj = true;
		}
		timer.cancel();		//TIMER OSTAJE, pa zato se ne zavrsi program ako nema ove linije!
		ispisKnjigaUFajl();
		System.out.println("\nSimulacija je zavrsena");
	}
	
	private void okoncajSimulaciju(Simulacija sim) {
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				d.jeKraj = true;
				u.jeKraj = true;
				synchronized(sim) {
					sim.notify();
				}
			}
		}, 1000 * 60);
	}
	private void ispisKnjigaUFajl() {
		try {
			FileWriter fw = new FileWriter("knjige.txt");
			PrintWriter pw = new PrintWriter(fw);
			while(!stek.knjige.empty()) {
				try {
					pw.println(stek.ukloni());
				} catch(StekPrazanException e) {}
			}
			pw.close();
		} catch(IOException e) {}
	}
}