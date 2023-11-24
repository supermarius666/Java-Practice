package main;

import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class Download implements Runnable {
	
	private Scanner in;
	private JButton stop;
	private JButton start;
	private JButton disconnect;
	private JTextArea console_log;
	private TicketCell[][] cartella;
	private static int counter;
	
	public Download(Scanner in,JTextArea console_log,TicketCell[][] cartella,JButton start,JButton stop,JButton disconnect) {
		this.in = in;
		this.console_log = console_log;
		this.cartella = cartella;
		this.start = start;
		this.stop = stop;
		this.disconnect = disconnect;
	}
	
	@Override
	public void run() {
		while(in.hasNext()) {
			String msg = in.nextLine();
			counter++;
			if(msg.equals("+") && (counter < 30)) {
				console_log.append("Partita interrota"+"\n");
				stop.setEnabled(false);
				start.setEnabled(true);
				disconnect.setEnabled(true);
				return;
			}
			else if(msg.equals("+") && (counter > 30)) {
				console_log.append("Partita finita"+"\n");
				stop.setEnabled(false);
				start.setEnabled(true);
				disconnect.setEnabled(true);
				return;
			}
			console_log.append("Estratto: "+ msg+"\n");
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 5; j++) {
					if(Integer.parseInt(msg) == cartella[i][j].getValue()) {
						cartella[i][j].setSelected(true);
					}
				}
			}
		}

	}

}