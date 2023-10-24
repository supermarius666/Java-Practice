package main;

import java.awt.*;
import java.awt.event.*;
//import java.io.*;
import java.net.UnknownHostException;

import javax.swing.*;

class Action implements ActionListener {

	private JLabel state;
	private JTextArea file_name;
	
	public Action(JLabel state, JTextArea file_name) {
		this.state = state;
		this.file_name = file_name;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println(e.getActionCommand());
		Client client = new Client(e.getActionCommand(), this.file_name.getText());
		try {
			if(client.answer() == 0) {
				state.setText("SUCCESS");			
			} else if(client.answer() == -1){
				state.setText("ERROR");
			}
		} catch (UnknownHostException e1) {
			state.setText("ERROR");
		}
	}
	
}


public class Finestra extends JFrame{

	//variables
	private static String titolo = "ServerApp";
	//panels
	private JPanel up = new JPanel();
	private JPanel buttons = new JPanel();
	private JPanel down = new JPanel();
	private LayoutManager layout = new GridLayout(1,4);
	//components
	private JTextArea text_area = new JTextArea(10,60);
	private JLabel state = new JLabel("Waiting...");
	private JButton b1 = new JButton("www.google.it");
	private JButton b2 = new JButton("www.exemple.com");
	private JButton b3 = new JButton("www.w3.org");
	private JButton b4 = new JButton("www.x.org");
	
	public Finestra() {
		super(titolo);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.up.add(text_area);
		this.add(up, BorderLayout.NORTH);
		this.buttons.add(b1);
		this.b1.addActionListener(new Action(state,text_area));
		this.buttons.add(b2);
		this.b2.addActionListener(new Action(state,text_area));
		this.buttons.add(b3);
		this.b3.addActionListener(new Action(state,text_area));
		this.buttons.add(b4);
		this.b4.addActionListener(new Action(state,text_area));
		this.add(buttons, BorderLayout.CENTER);
		this.buttons.setLayout(layout);
		this.down.add(state);
		this.add(down, BorderLayout.SOUTH);
		this.pack();
		this.setVisible(true);
	}
}
