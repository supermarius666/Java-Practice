package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Window{
	
	JFrame window;
	
	JPanel up;
	JLabel address;
	JTextArea address_input;
	JLabel port;
	JTextArea port_input;
	JButton connect;
	JButton disconnect;
	LayoutManager layout1 = new FlowLayout();
	
	JPanel game_area;
	BoardButton[][] tiles;
	LayoutManager layout2 = new GridLayout(10,10);
	
	JPanel down;
	JButton start;
	JButton stop;
	JButton rivela;
	
	Action action;
	
	public Window() {
		window = new JFrame("MineSweeper");
		window.setDefaultCloseOperation(3);
		window.setSize(700,500);
		window.setLocationRelativeTo(null);
		
		up = new JPanel();
		address = new JLabel("Server Adress");
		address_input = new JTextArea("localhost",1,15);
		port = new JLabel("Port");
		port_input = new JTextArea("4400",1,8);
		connect = new JButton("Connect");
		disconnect = new JButton("Disconnect");
		down = new JPanel();
		start = new JButton("Start");
		stop = new JButton("Stop");
		rivela = new JButton("Rivela");
		game_area = new JPanel();
		
		up.add(address);
		up.add(address_input);
		up.add(port);
		up.add(port_input);
		up.add(connect);
		up.add(disconnect);
		up.setLayout(layout1);
		window.add(up,BorderLayout.NORTH);
		
		tiles = new BoardButton[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				tiles[i][j] = new BoardButton();
				game_area.add(tiles[i][j]);
			}
		}
		game_area.setLayout(layout2);
		window.add(game_area,BorderLayout.CENTER);
		
		down.add(start);
		down.add(stop);
		down.add(rivela);
		down.setLayout(layout1);
		window.add(down,BorderLayout.SOUTH);
		
		action = new Action(this);
		connect.addActionListener(action);
		disconnect.addActionListener(action);
		start.addActionListener(action);
		stop.addActionListener(action);
		rivela.addActionListener(action);
				
		disconnect.setEnabled(false);
		start.setEnabled(false);
		stop.setEnabled(false);
		rivela.setEnabled(false);
		
		window.setVisible(true);
	}
	
	
}
