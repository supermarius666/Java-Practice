package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class Action implements ActionListener {

	private JButton connect;
	private JButton disconnect;
	private JButton start;
	private JButton stop;
	private JTextArea address;
	private JTextArea port;
	private JTextArea console_log;
	private TicketCell[][] cartella;
	private static Socket client;
	private static PrintWriter out;
	private static Scanner in;
	private Download d;
	private Random_cell r;
	private Thread t1;
	private Thread t2;
	
	public Action(JButton connect,JButton disconnect,JButton start,JButton stop,JTextArea address,JTextArea port,TicketCell[][] cartella,JTextArea console_log) {
		this.connect = connect;
		this.disconnect = disconnect;
		this.start = start;
		this.stop = stop;
		this.address = address;
		this.port = port;
		this.cartella = cartella;
		this.console_log = console_log;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(e.getActionCommand().equals("Connect")) {
				client = new Socket(address.getText(),Integer.parseInt(port.getText()));
				connect.setEnabled(false);
				disconnect.setEnabled(true);
				start.setEnabled(true);
			}
			if(e.getActionCommand().equals("Start")) {
				connect.setEnabled(false);
				disconnect.setEnabled(false);
				start.setEnabled(false);
				stop.setEnabled(true);
				out = new PrintWriter(client.getOutputStream());
				in = new Scanner(client.getInputStream());
				
				for(int i = 0; i < 3; i++) {
					for(int j = 0; j < 5; j++) {
						cartella[i][j].setSelected(false);
					}
				}
				
				r = new Random_cell(cartella);
				t2 = new Thread(r);
				t2.start();
				
				out.println("start");
				out.flush();
				
				d = new Download(in, console_log, cartella,start,stop,disconnect);
				t1 = new Thread(d);
				t1.start();
			}
			if(e.getActionCommand().equals("Stop")) {
				out.println("stop");
				out.flush();
				stop.setEnabled(false);
				disconnect.setEnabled(true);
				start.setEnabled(true);
			}
			if(e.getActionCommand().equals("Disconnect")){
				connect.setEnabled(true);
				disconnect.setEnabled(false);
				start.setEnabled(false);
				stop.setEnabled(false);
				out.println("disconnect");
				out.flush();
				
				client.close();
				out.close();
				in.close();
			}
		} catch(IOException e2) {
			
		}

	}

}








