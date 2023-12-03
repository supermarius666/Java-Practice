package main;

import java.util.Scanner;

public class Download implements Runnable {

	Scanner in;
	Window w;
	
	public Download(Window w, Scanner in) {
		this.w = w;
		this.in = in;
	}
	
	@Override
	public void run() {
		while(in.hasNext()) {
			String msg = in.nextLine();
			
			if(msg.equals("Not Found")) {
				return;
			}
			
			if(msg.equals("*")) {
				w.stop.setEnabled(false);
				w.start.setEnabled(true);
				w.disconnect.setEnabled(true);
				w.v1.setEnabled(true);
				w.v2.setEnabled(true);
				w.v3.setEnabled(true);
				return;
			}
			
			String [] u = msg.split(":");
			
			w.p1.setValue(Integer.parseInt(u[0]));
			w.p2.setValue(Integer.parseInt(u[1]));
			w.p3.setValue(Integer.parseInt(u[2]));
			w.p4.setValue(Integer.parseInt(u[3]));
			
			System.out.println(msg);
		}

	}

}
