package main;

import java.util.Scanner;

import javax.swing.JOptionPane;

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
			String[] data = msg.split(":");
			if(msg.equals("END") && w.ok == false) {
				w.stop.setEnabled(false);
				w.disconnect.setEnabled(true);
				w.songs.setEnabled(true);
				w.ok = true;
				return;
			}
			if(msg.equals("END") && w.ok == true) {
				JOptionPane.showMessageDialog(new JOptionPane(), "Fine");
				w.disconnect.setEnabled(true);
				w.stop.setEnabled(false);
				return;
			}
			if(msg.equals("INTERRUPTED")) {
				return;
			}
			if(w.ok == true && !w.songs.isEnabled()) {
				for(String s: w.temp) {
					String[] name = s.split(" ");
					char n = name[0].charAt(0);
					char c = name[1].charAt(0);
					String f = String.valueOf(n)+String.valueOf(c);
					if(f.equals(data[1])) {
						w.console_log.append(msg+"\n");
						w.artist.append(s+"-"+data[2]+"\n");
						
					}
				}
				
			}
			else{
				w.temp.add(data[1]);
				w.console_log.append(msg+"\n");
				w.artist.append(data[1]+"\n");
			}
		}

	}

}
