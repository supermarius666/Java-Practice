package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Window extends JFrame {
	
	JPanel up = new JPanel();
	JLabel address = new JLabel("IP Address");
	JTextArea address_input = new JTextArea("localhost",1,15);
	JLabel port = new JLabel("Port");
	JTextArea port_input = new JTextArea("4400",1,8);
	JButton start = new JButton("Start");
	JButton interrompi = new JButton("Interrompi");
	LayoutManager l1 = new FlowLayout();
	
	JPanel down = new JPanel();
	JButton connect = new JButton("Connect");
	JButton disconnect = new JButton("Disconnect");
	JButton clear = new JButton("Clear");
	
	JPanel game_area = new JPanel();
	ColoredButton uno = new ColoredButton("uno",Color.LIGHT_GRAY);
	ColoredButton due = new ColoredButton("due",Color.LIGHT_GRAY);
	ColoredButton tre = new ColoredButton("tre",Color.LIGHT_GRAY);
	ColoredButton quattro = new ColoredButton("quattro",Color.LIGHT_GRAY);
	ColoredButton cinque = new ColoredButton("cinque",Color.LIGHT_GRAY);
	LayoutManager l2 = new GridLayout(1,5);
	
	Action action= new Action(this);
	
	public Window() {
		super("Bingo");
		this.setDefaultCloseOperation(3);
		this.setSize(700,200);
		this.setLocationRelativeTo(null);
		
		up.add(start);
		up.add(address);
		up.add(address_input);
		up.add(port);
		up.add(port_input);
		up.add(interrompi);
		up.setLayout(l1);
		this.add(up, BorderLayout.NORTH);
		
		game_area.add(uno);
		game_area.add(due);
		game_area.add(tre);
		game_area.add(quattro);
		game_area.add(cinque);
		game_area.setLayout(l2);
		this.add(game_area, BorderLayout.CENTER);
		
		down.add(connect);
		down.add(disconnect);
		down.add(clear);
		down.setLayout(l1);
		this.add(down, BorderLayout.SOUTH);
		
		disconnect.setEnabled(false);
		start.setEnabled(false);
		interrompi.setEnabled(false);
		clear.setEnabled(false);
		
		
		connect.addActionListener(action);
		disconnect.addActionListener(action);
		start.addActionListener(action);
		interrompi.addActionListener(action);
		clear.addActionListener(action);
		
		this.setVisible(true);
	}
	
}
