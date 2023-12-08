package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;

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
	LayoutManager l1 = new FlowLayout(); 
	
	JPanel image = new JPanel();
	TitledBorder border = new TitledBorder("Image");
	JTextArea print = new JTextArea(25,80);
	JScrollPane scroll = new JScrollPane(print);
	
	JPanel down = new JPanel();
	JButton im1 = new JButton("Image 1");
	JButton im2 = new JButton("Image 2");
	JButton im3 = new JButton("Image 3");
	JButton im4 = new JButton("Image 4");
	JButton im5 = new JButton("Image 5");
	JButton stop = new JButton("Stop");
	
	Action action = new Action(this);
	
	public Window() {
		super("Nome Cognome 111111");
		this.setDefaultCloseOperation(3);
		//this.setSize(950,550);	
		up.add(address);
		up.add(address_input);
		up.add(port);
		up.add(port_input);
		up.add(connect);
		up.add(disconnect);
		up.setLayout(l1);
		this.add(up, BorderLayout.NORTH);
		
		print.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
		image.add(scroll);
		image.setBorder(border);
		this.add(image, BorderLayout.CENTER);
		
		down.add(im1);
		down.add(im2);
		down.add(im3);
		down.add(im4);
		down.add(im5);
		down.add(stop);
		down.setLayout(l1);
		this.add(down, BorderLayout.SOUTH);
		
		disconnect.setEnabled(false);
		stop.setEnabled(false);
		im1.setEnabled(false);
		im2.setEnabled(false);
		im3.setEnabled(false);
		im4.setEnabled(false);
		im5.setEnabled(false);
		print.setEditable(false);
		
		connect.addActionListener(action);
		disconnect.addActionListener(action);
		stop.addActionListener(action);
		im1.addActionListener(action);
		im2.addActionListener(action);
		im3.addActionListener(action);
		im4.addActionListener(action);
		im5.addActionListener(action);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	
}
