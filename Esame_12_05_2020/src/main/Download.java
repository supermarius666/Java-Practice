package main;

import java.awt.Color;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Download implements Runnable{

	Scanner in;
	Window w;
	int count = 0;
	
	public Download(Window w,Scanner in) {
		this.w = w;
		this.in = in;
	}
	
	@Override
	public void run() {
		while(in.hasNext()) {
			String msg = in.nextLine();
			
			String[] msgs = msg.split(";");
			
			if(msgs[0].equals("-1") && msgs[1].equals("-1")) {
				JOptionPane.showMessageDialog(new JOptionPane(),"Hai perso!");
			}
			
			if(msgs[0].equals("0")) {
				if(w.uno.getDigit().equals(msgs[1])) {
					w.uno.changeColor(Color.GREEN);
					count += 1;
				}
				else
					w.uno.changeColor(Color.RED);
			}
			if(msgs[0].equals("1")) {
				if(w.due.getDigit().equals(msgs[1])) {
					w.due.changeColor(Color.GREEN);
					count += 1;
				}
				else
					w.due.changeColor(Color.RED);
			}
			if(msgs[0].equals("2")) {
				if(w.tre.getDigit().equals(msgs[1])) {
					w.tre.changeColor(Color.GREEN);
					count += 1;
				}
				else
					w.tre.changeColor(Color.RED);
			}
			if(msgs[0].equals("3")) {
				if(w.quattro.getDigit().equals(msgs[1])) {
					w.quattro.changeColor(Color.GREEN);
					count += 1;
				}
				else
					w.quattro.changeColor(Color.RED);
			}
			if(msgs[0].equals("4")) {
				if(w.cinque.getDigit().equals(msgs[1])) {
					w.cinque.changeColor(Color.GREEN);
					count += 1;
				}
				else
					w.cinque.changeColor(Color.RED);
			}
			
			if(msgs[0].equals("*") && msgs[1].equals("*")) {
				if(count > 0) {
					JOptionPane.showMessageDialog(new JOptionPane(),"Hai vinto!");
				}
				else {
					JOptionPane.showMessageDialog(new JOptionPane(),"Hai perso!");
				}
				w.start.setEnabled(true);
				w.disconnect.setEnabled(true);
			}
		}
		
	}

}
