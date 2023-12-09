package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class Window extends JFrame {

	JPanel up = new JPanel();
	JLabel address = new JLabel("Server Address");
	JTextArea address_input = new JTextArea("localhost",1,15);	
	JLabel port = new JLabel("Port");
	JTextArea port_input = new JTextArea("4400",1,8);
	JButton connect = new JButton("Connect");
	JButton disconnect = new JButton("Disconnect");
	
	JPanel west = new JPanel();
	JTextArea console_log = new JTextArea(20,35);
	JScrollPane scroll1 = new JScrollPane(console_log);
	
	JPanel center = new JPanel();
	
	JPanel east = new JPanel();
	JTextArea artist = new JTextArea(20,35);
	JScrollPane scroll2 = new JScrollPane(artist);
	
	JPanel down = new JPanel();
	JButton artists = new JButton("Artisti");
	JButton songs = new JButton("Canzoni");
	JButton stop = new JButton("Stop");
	
	TitledBorder log = new TitledBorder("Log");
	TitledBorder music = new TitledBorder("Artisti e Canzoni");
	
	LayoutManager l = new FlowLayout();
	LayoutManager g = new GridLayout(1,2);
	
	Action action = new Action(this);
	boolean ok = false;
	ArrayList<String> temp = new ArrayList<String>();
	
	
	public Window() {
		super("Nome Cognome matricola");
		this.setDefaultCloseOperation(3);
		
		up.add(address);
		up.add(address_input);
		up.add(port);
		up.add(port_input);
		up.add(connect);
		up.add(disconnect);
		up.setLayout(l);
		this.add(up,BorderLayout.NORTH);
		
		west.add(scroll1);	
		west.setBorder(log);
		east.add(scroll2);
		east.setBorder(music);
		center.add(west);
		center.add(east);
		this.add(center,BorderLayout.CENTER);
		
		down.add(artists);
		down.add(songs);
		down.add(stop);
		this.add(down,BorderLayout.SOUTH);
	
		disconnect.setEnabled(false);
		stop.setEnabled(false);
		artists.setEnabled(false);
		songs.setEnabled(false);
		
		connect.addActionListener(action);
		disconnect.addActionListener(action);
		stop.addActionListener(action);
		artists.addActionListener(action);
		songs.addActionListener(action);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
