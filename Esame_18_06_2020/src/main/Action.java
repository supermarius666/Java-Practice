package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Action implements ActionListener {

	Window window;
	static Socket client;
	static PrintWriter out;
	static Scanner in;
	Download d;
	Thread t;
	
	public Action(Window window) {
		this.window = window;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(e.getActionCommand().equals("Connect")) {
				window.connect.setEnabled(false);
				window.disconnect.setEnabled(true);
				window.start.setEnabled(true);
				window.stop.setEnabled(true);
				window.rivela.setEnabled(true);
				client = new Socket(window.address_input.getText(),Integer.parseInt(window.port_input.getText()));
			}
			if(e.getActionCommand().equals("Start")) {
				window.disconnect.setEnabled(false);
				window.start.setEnabled(false);
				window.stop.setEnabled(true);
				window.rivela.setEnabled(false);
				
				out = new PrintWriter(client.getOutputStream());
				in = new Scanner(client.getInputStream());
				
				for(int i = 0; i < 10; i++) {
					for(int j = 0; j < 10; j++) {
						window.tiles[i][j].reset();
					}
				}
	
				out.println("start");
				out.flush();
				
				d = new Download(window,in);
				t = new Thread(d);
				t.start();
			}
			if(e.getActionCommand().equals("Rivela")) {
				for(int i = 0; i < 10; i++) {
					for(int j = 0; j < 10; j++) {
						window.tiles[i][j].reveal();
					}
				}
			}
			if(e.getActionCommand().equals("Stop")) {
				out.println("stop");
				out.flush();
			}
			if(e.getActionCommand().equals("Disconnect")) {
				out = new PrintWriter(client.getOutputStream());
				in = new Scanner(client.getInputStream());
				
				out.println("disconnect");
				out.flush();
				window.connect.setEnabled(true);
				window.disconnect.setEnabled(false);
				window.start.setEnabled(false);
				window.stop.setEnabled(false);
				window.rivela.setEnabled(false);
				
				for(int i = 0; i < 10; i++) {
					for(int j = 0; j < 10; j++) {
						window.tiles[i][j].reset();
					}
				}
				
				client.close();
				in.close();
				out.close();
			}
		}catch(IOException e2) {
			System.out.println("Error");
		}

	}

}