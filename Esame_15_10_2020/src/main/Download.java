package main;

import java.util.Scanner;

public class Download implements Runnable {

	Window w;
	Scanner in;
	
	public Download(Window w, Scanner in) {
		this.w = w;
		this.in = in;
	}
	
	@Override
	public void run() {
		while(in.hasNext()) {
			String msg = in.nextLine();
			if(msg.equals("END")) {
				w.print.append("==========Download completo==========\n");
				w.stop.setEnabled(false);
				w.disconnect.setEnabled(true);
				w.im1.setEnabled(true);
				w.im2.setEnabled(true);
				w.im3.setEnabled(true);
				w.im4.setEnabled(true);
				w.im5.setEnabled(true);
				return;
			}
			if(msg.equals("INTERRUPTED")) {
				w.print.append("==========Download interroto==========\n");
				w.stop.setEnabled(false);
				w.disconnect.setEnabled(true);
				w.im1.setEnabled(true);
				w.im2.setEnabled(true);
				w.im3.setEnabled(true);
				w.im4.setEnabled(true);
				w.im5.setEnabled(true);
				return;
			}
			if(msg.equals("ERROR")) {
				w.print.append("==========ERROR==========\n");
				w.stop.setEnabled(false);
				w.disconnect.setEnabled(true);
				w.im1.setEnabled(true);
				w.im2.setEnabled(true);
				w.im3.setEnabled(true);
				w.im4.setEnabled(true);
				w.im5.setEnabled(true);
				return;
			}
			else {
				w.print.append(msg+"\n");
			}
			
		}

	}

}
