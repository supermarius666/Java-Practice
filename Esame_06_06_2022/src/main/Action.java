package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Action implements ActionListener {

	Window w;
	static Socket client;
	static PrintWriter out;
	static Scanner in;
	Download d;
	Thread t;

	
	
	public Action(Window w) {
		this.w = w;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(e.getActionCommand().equals("Connect")) {
				client = new Socket(w.address_input.getText(),Integer.parseInt(w.port_input.getText()));
				w.connect.setEnabled(false);
				w.disconnect.setEnabled(true);
				w.artists.setEnabled(true);
				w.console_log.setText(null);
				w.artist.setText(null);
			}
			if(e.getActionCommand().equals("Artisti")) {
				out = new PrintWriter(client.getOutputStream());
				in = new Scanner(client.getInputStream());
				
				out.println("artisti");
				out.flush();
				w.artists.setEnabled(false);
				w.disconnect.setEnabled(false);
				w.stop.setEnabled(true);
				
				d = new Download(w,in);
				t = new Thread(d);
				t.start();
			}
			if(e.getActionCommand().equals("Canzoni")) {
				out.println("canzoni");
				out.flush();
				w.artist.setText(null);
				w.songs.setEnabled(false);
				w.disconnect.setEnabled(false);
				w.stop.setEnabled(true);
				
				d = new Download(w,in);
				t = new Thread(d);
				t.start();
			}
			if(e.getActionCommand().equals("Stop")) {
				out.println("stop");
				out.flush();
				w.stop.setEnabled(false);
				w.disconnect.setEnabled(true);
			}
			if(e.getActionCommand().equals("Disconnect")) {
				out = new PrintWriter(client.getOutputStream());
				in = new Scanner(client.getInputStream());
				
				out.println("disconnect");
				out.flush();
				
				client.close();
				in.close();
				out.close();
				
				w.connect.setEnabled(true);
				w.disconnect.setEnabled(false);
				w.artists.setEnabled(false);
				w.songs.setEnabled(false);
			}
			
			
		} catch(IOException ex) {
			
		}

	}

}
