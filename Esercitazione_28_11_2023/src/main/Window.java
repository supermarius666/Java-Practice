package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class Window extends JFrame{
	
	private JPanel up = new JPanel();
	private JLabel address = new JLabel("Server Address");
	private JTextArea address_input = new JTextArea("localhost",1,15);
	private JLabel port = new JLabel("Server Address");
	private JTextArea port_input = new JTextArea("4400",1,8);
	private JButton connect = new JButton("Connect");
	private JButton disconnect = new JButton("Disconnect");
	private LayoutManager layout1 = new FlowLayout();
	
	private JPanel down = new JPanel();
	private JLabel number = new JLabel("Number");
	private JTextArea number_input = new JTextArea("1", 1, 10);
	private JButton get = new JButton("Get");
	private JButton reset = new JButton("Reset");
	
	private JPanel center = new JPanel();
	private ArrayList<JButton> buttons = new ArrayList<JButton>();
	private LayoutManager layout2 = new GridLayout(5,5);
	private JPanel console_log = new JPanel();
	private JTextArea console_input = new JTextArea(22,30);
	private JScrollPane scroll = new JScrollPane(console_input);
	private TitledBorder border = new TitledBorder("Log");
	
	private ActionListener action  = new Action(connect,disconnect,get,reset,address_input,port_input,number_input,buttons,console_input);
	
	public Window() {
		super("Pixel Word");
		this.setSize(700,500);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		up.add(address);
		up.add(address_input);
		up.add(port);
		up.add(port_input);
		up.add(connect);
		up.add(disconnect);
		up.setLayout(layout1);
		this.add(up, BorderLayout.NORTH);
		
		for(int i = 0; i < 25; i++) {
			buttons.add(new JButton());
			buttons.get(i).setPreferredSize(new Dimension(30,30));
			buttons.get(i).setBackground(Color.WHITE);
			buttons.get(i).setEnabled(false);
			center.add(buttons.get(i));
		}
		center.setLayout(layout2);
		this.add(center, BorderLayout.CENTER);
		console_log.add(scroll);
		console_log.setBorder(border);
		this.add(console_log, BorderLayout.EAST);
		
		down.add(number);
		down.add(number_input);
		down.add(get);
		down.add(reset);
		down.setLayout(layout1);
		this.add(down, BorderLayout.SOUTH);
		
		
		connect.addActionListener(action);
		disconnect.addActionListener(action);
		get.addActionListener(action);
		reset.addActionListener(action);
		
		disconnect.setEnabled(false);
		get.setEnabled(false);
		reset.setEnabled(false);
		
		this.setVisible(true);
	}
}





