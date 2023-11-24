package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class Window extends JFrame {
	
	private JPanel sopra = new JPanel();
	private JLabel address = new JLabel("Server Address");
	private JTextArea address_input = new JTextArea("localhost",1,15);
	private JLabel port = new JLabel("Port");
	private JTextArea port_input = new JTextArea("4400",1,8);
	private JButton connect = new JButton("Connect");
	private JButton disconnect = new JButton("Disconnect");
	private LayoutManager layout1 = new FlowLayout();
	
	private JPanel centro = new JPanel();
	private TitledBorder border_cartella = new TitledBorder("Cartella");
	private TicketCell[][] cartella  = new TicketCell[3][5];
	private LayoutManager layout2 = new GridLayout(3,5,2,2);
	
	
	private JPanel log = new JPanel();
	private TitledBorder border_log = new TitledBorder("Log");
	private JTextArea console_log = new JTextArea(28,30);
	private JScrollPane scroll = new JScrollPane(console_log);
	
	
	private JPanel sotto = new JPanel();
	private JButton start = new JButton("Start");
	private JButton stop = new JButton("Stop");
	
	private Action action;
	
	
	public Window() {
		super("Super Bingo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900, 600);
		//this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		sopra.add(address);
		sopra.add(address_input);
		sopra.add(port);
		sopra.add(port_input);
		sopra.add(connect);
		sopra.add(disconnect);
		sopra.setLayout(layout1);
		this.add(sopra, BorderLayout.NORTH);
		
		centro.setBorder(border_cartella);
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 5; j++) {
				cartella[i][j] = new TicketCell(0);
				centro.add(cartella[i][j]);
			}
		}
		centro.setLayout(layout2);
		this.add(centro, BorderLayout.CENTER);
		
		log.setBorder(border_log);
		log.add(scroll);
		this.add(log, BorderLayout.EAST);
		
		sotto.add(start);
		sotto.add(stop);
		this.add(sotto, BorderLayout.SOUTH);
		
		connect.setEnabled(true);
		disconnect.setEnabled(false);
		start.setEnabled(false);
		stop.setEnabled(false);
		
		action = new Action(connect,disconnect,start,stop, address_input, port_input, cartella,console_log);
		
		connect.addActionListener(action);
		disconnect.addActionListener(action);
		start.addActionListener(action);
		stop.addActionListener(action);
		
		this.setVisible(true);
	}
}


















