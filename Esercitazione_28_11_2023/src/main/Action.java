package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Action implements ActionListener {

	private JButton connect;
	private JButton disconnect;
	private JButton get;
	private JButton reset;
	private JTextArea address;
	private JTextArea port;
	private JTextArea number;
	private JOptionPane error;
	private JTextArea console_input;
	private ArrayList<JButton> buttons;
	private static Socket client;
	private static PrintWriter out;
	private static Scanner in;
	private Download d;
	private Thread t;
	
	public Action(JButton connect,JButton disconnect,JButton get,JButton reset,JTextArea address,JTextArea port,JTextArea number, ArrayList<JButton> buttons,JTextArea console_input) {
		this.connect = connect;
		this.disconnect = disconnect;
		this.get = get;
		this.reset = reset;
		this.address = address;
		this.port = port;
		this.number = number;
		this.buttons = buttons;
		this.console_input = console_input;
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			if(e.getActionCommand().equals("Connect")) {
				client = new Socket(this.address.getText(),Integer.parseInt(this.port.getText()));
				connect.setEnabled(false);
				get.setEnabled(true);
				reset.setEnabled(true);
				disconnect.setEnabled(true);
			}
			if(e.getActionCommand().equals("Get")) {
				get.setEnabled(false);
				disconnect.setEnabled(false);
				out = new PrintWriter(client.getOutputStream());
				in = new Scanner(client.getInputStream());
				
				for(int i = 0; i < 25; i++) {
					buttons.get(i).setBackground(Color.WHITE);
				}
				if(number.getText().length() > 1 || (number.getText().length() == 1 && !(Character.isDigit(number.getText().charAt(0))))) {
					error =  new JOptionPane("Non puoi");
					error.showMessageDialog(error, "BRUH");
					get.setEnabled(true);
				}
				else {
					out.println("GET:"+number.getText());
					out.flush();
					
					d = new Download(in,buttons,console_input,disconnect,get);
					t = new Thread(d);
					t.start();
				}
			}
			if(e.getActionCommand().equals("Reset")) {
				for(int i = 0; i < 25; i++) {
					buttons.get(i).setBackground(Color.WHITE);
				}
				console_input.setText(null);
			}
			if(e.getActionCommand().equals("Disconnect")) {
				out.println("DISCONNECT");
				out.flush();
				client.close();
				in.close();
				out.close();
			}
		} catch(IOException e2) {
			System.out.println("error");
		}

	}

}