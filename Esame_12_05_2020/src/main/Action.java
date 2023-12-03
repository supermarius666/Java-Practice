package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Action implements ActionListener{

	Window window;
	static Socket client;
	static Scanner in;
	static PrintWriter out;
	Download d;
	Thread t;
	
	public Action(Window window) {
		this.window = window;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(e.getActionCommand().equals("Connect")) {
				client = new Socket(window.address_input.getText(),Integer.parseInt(window.port_input.getText()));
				window.connect.setEnabled(false);
				window.disconnect.setEnabled(true);
				window.start.setEnabled(true);
				window.clear.setEnabled(true);
			}
			if(e.getActionCommand().equals("Start")) {
				window.start.setEnabled(false);
				window.interrompi.setEnabled(true);
				out = new PrintWriter(client.getOutputStream());
				in = new Scanner(client.getInputStream());
				
				out.println("start");
				out.flush();
				
				d = new Download(window,in);
				t = new Thread(d);
				t.start();
			}
			if(e.getActionCommand().equals("Clear")) {
				window.uno.changeColor(Color.LIGHT_GRAY);
				window.uno.setTextDigit("");
				window.due.changeColor(Color.LIGHT_GRAY);
				window.due.setTextDigit("");
				window.tre.changeColor(Color.LIGHT_GRAY);
				window.tre.setTextDigit("");
				window.quattro.changeColor(Color.LIGHT_GRAY);
				window.quattro.setTextDigit("");
				window.cinque.changeColor(Color.LIGHT_GRAY);
				window.cinque.setTextDigit("");
			}
			if(e.getActionCommand().equals("Interrompi")) {
				window.interrompi.setEnabled(false);
				window.disconnect.setEnabled(true);
				window.start.setEnabled(true);
				window.clear.setEnabled(true);
				
				out.println("interrompi");
				out.flush();
			}
			if(e.getActionCommand().equals("Disconnect")) {
				window.disconnect.setEnabled(false);
				window.clear.setEnabled(false);
				window.start.setEnabled(false);
				window.interrompi.setEnabled(false);
				window.connect.setEnabled(true);
				
				out.println("disconnect");
				out.flush();
				
				client.close();
				in.close();
				out.close();
			}
		} catch(IOException e2) {
			
		}
		
	}

}
