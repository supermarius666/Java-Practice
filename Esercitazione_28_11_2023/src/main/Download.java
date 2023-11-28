package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class Download implements Runnable {

	private Scanner in;
	private ArrayList<JButton> buttons;
	private JTextArea console_input;
	private JButton disconnect;
	private JButton get;
	
	public Download(Scanner in, ArrayList<JButton> buttons,JTextArea console_input,JButton disconnect,JButton get) {
		this.in = in;
		this.buttons = buttons;
		this.console_input = console_input;
		this.disconnect = disconnect;
		this.get = get;
	}
	
	@Override
	public void run() {
		while(in.hasNext()) {
			String msg = in.nextLine();
			String[] pixels = msg.split(":");
			if(msg.equals("END")) {
				get.setEnabled(true);
				disconnect.setEnabled(true);
				return;
			}
			if(msg.equals("ERROR")) {
				return;
			}
			console_input.append(msg+"\n");
			for(int i = 0; i < 25; i++) {
				if(pixels[1].charAt(i) == '1') {
					buttons.get(i).setBackground(Color.BLACK);
				}
			}
		}
	}

}

