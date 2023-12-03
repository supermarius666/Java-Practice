package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class Window extends JFrame {

	
	JPanel up = new JPanel();
	JLabel address = new JLabel("Server Adress");
	JTextArea address_input = new JTextArea("localhost",1,15);
	JLabel port = new JLabel("Port");
	JTextArea port_input = new JTextArea("4400",1,8);
	JButton connect = new JButton("Connect"); 
	JButton disconnect = new JButton("Disconnect"); 
	LayoutManager l1 = new FlowLayout();
	
	JPanel usage = new JPanel();
	JLabel cpu = new JLabel("CPU Usage");
	JProgressBar p1 = new JProgressBar(0,100);
	JLabel memory = new JLabel("Memory Usage");
	JProgressBar p2 = new JProgressBar(0,100);
	JLabel disk = new JLabel("Disk Usage");
	JProgressBar p3 = new JProgressBar(0,100);
	JLabel network = new JLabel("Network Usage");
	JProgressBar p4 = new JProgressBar(0,100);
	LayoutManager l2 = new GridLayout(4,2);
	TitledBorder border = new TitledBorder("VM Status");
	
	JPanel buttons = new JPanel();
	ButtonGroup gruppo = new ButtonGroup();
	JRadioButton v1 = new JRadioButton("VM 1");
	JRadioButton v2 = new JRadioButton("VM 2");
	JRadioButton v3 = new JRadioButton("VM 3");
	TitledBorder border2 = new TitledBorder("VM");
	
	JPanel down = new JPanel();
	JButton start = new JButton("Start");
	JButton stop = new JButton("Stop");
	
	LayoutManager l3 = new GridLayout(4,1);
	
	Action action = new Action(this);
	
	public Window() {
		super("Nome Cognome matricola");
		this.setDefaultCloseOperation(3);
		this.setSize(600,500);
		this.setLocationRelativeTo(null);
		
		
		up.add(address);
		up.add(address_input);
		up.add(port);
		up.add(port_input);
		up.add(connect);
		up.add(disconnect);
		up.setLayout(l1);
		this.add(up);
		
		p1.setStringPainted(true);
		usage.add(cpu);
		usage.add(p1);
		p2.setStringPainted(true);
		usage.add(memory);
		usage.add(p2);
		p3.setStringPainted(true);
		usage.add(disk);
		usage.add(p3);
		p4.setStringPainted(true);
		usage.add(network);
		usage.add(p4);
		usage.setLayout(l2);
		usage.setBorder(border);
		this.add(usage, BorderLayout.CENTER);
		
		gruppo.add(v1);
		gruppo.add(v2);
		gruppo.add(v3);
		buttons.add(v1);
		buttons.add(v2);
		buttons.add(v3);
		buttons.setLayout(l1);
		buttons.setBorder(border2);
		this.add(buttons, BorderLayout.CENTER);
		
		down.add(start);
		down.add(stop);
		down.setLayout(l1);
		this.add(down, BorderLayout.SOUTH);

		disconnect.setEnabled(false);
		start.setEnabled(false);
		stop.setEnabled(false);
		
		v1.setEnabled(false);
		v2.setEnabled(false);
		v3.setEnabled(false);
		
		connect.addActionListener(action);
		disconnect.addActionListener(action);
		start.addActionListener(action);
		stop.addActionListener(action);
		
		this.setLayout(l3);
		this.setVisible(true);
	}
	
}
