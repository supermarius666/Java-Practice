package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

class Download implements Runnable{

	Socket client;
	JPanel sx;
	JPanel cx;
	JPanel dx;
	Scanner in;
	HashMap<Integer,Color> map;
	
	public Download(Socket client, Scanner in, JPanel sx, JPanel cx, JPanel dx) {
		this.client = client;
		this.in = in;
		this.sx = sx;
		this.cx = cx;
		this.dx = dx;
		map = new HashMap<>();
		map.put(0,Color.WHITE);
		map.put(1, Color.BLACK);
		map.put(2, Color.GREEN);
		map.put(3, Color.RED);
		map.put(4, Color.YELLOW);
		map.put(5, Color.BLUE);
		map.put(6, Color.ORANGE);
	}
	
	@Override
	public void run() {
		
		while(in.hasNext()) {
			String msg = in.nextLine();
			String[] parts = msg.split(";");
		
			if(parts[1].equals("SX")) {
				sx.setBackground(map.get(Integer.parseInt(parts[0])));
			}

			if(parts[1].equals("CX")) {
				cx.setBackground(map.get(Integer.parseInt(parts[0])));
			}

			if(parts[1].equals("DX")) {
				dx.setBackground(map.get(Integer.parseInt(parts[0])));
			}
			
			if(parts[1].equals(String.valueOf(-1)) && parts[1].equals(String.valueOf(-1))) {
				System.out.println("Connessione interrota");
				return;
			}
			System.out.println(msg);
		}
		System.out.println("fine");
		
	}
	
}
class Action implements ActionListener{

	Thread t;
	static PrintWriter out;
	Scanner in;
	static Socket client;
	JTextArea address; 
	JTextArea port;
	JButton connect;
	JButton disconnect; 
	JButton start;
	JButton stop;
	Download d;
	JPanel sx;
	JPanel cx;
	JPanel dx;
	
	
	public Action(JTextArea address, JTextArea port, JButton connect, JButton disconnect, JButton start, JButton stop, JPanel sx, JPanel cx, JPanel dx) {
		this.address = address;
		this.port = port;
		this.connect = connect;
		this.disconnect = disconnect;
		this.start = start;
		this.stop = stop;
		this.sx = sx;
		this.cx = cx;
		this.dx = dx;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(e.getActionCommand().equals("Connect")){
				connect.setEnabled(false);
				disconnect.setEnabled(true);
				start.setEnabled(true);
				client = new Socket(this.address.getText(), Integer.parseInt(this.port.getText()));
			}
			if(e.getActionCommand().equals("Start")) {
				sx.setBackground(Color.LIGHT_GRAY);
				cx.setBackground(Color.LIGHT_GRAY);
				dx.setBackground(Color.LIGHT_GRAY);
				start.setEnabled(false);
				stop.setEnabled(true);
				disconnect.setEnabled(false);
				out = new PrintWriter(client.getOutputStream());
				in = new Scanner(client.getInputStream());
				d = new Download(client, in, sx, cx, dx);
				
				out.println("start");
				out.flush();
				
				t = new Thread(d);
				t.start();
				
			}
			if(e.getActionCommand().equals("Stop")){
				start.setEnabled(true);
				stop.setEnabled(false);
				disconnect.setEnabled(true);
				
				out.println("stop");
				out.flush();
			}
			if(e.getActionCommand().equals("Disconnect")) {
				sx.setBackground(Color.LIGHT_GRAY);
				cx.setBackground(Color.LIGHT_GRAY);
				dx.setBackground(Color.LIGHT_GRAY);
				out.println("disconnect");
				out.flush();
				connect.setEnabled(true);
				client.close();
			}
			
		} catch(IOException e1) {
			
		}
	}
	
}



@SuppressWarnings("serial")
public class Window extends JFrame{
	
	JPanel sopra = new JPanel();
	JLabel address = new JLabel("IP Address");
	JTextArea address_input = new JTextArea("192.168.51.90",1,8);
	JLabel port = new JLabel("Port");
	JTextArea port_input = new JTextArea("4400",1,8);
	JButton connect = new JButton("Connect");
	JButton disconnect = new JButton("Disconnect");
	JPanel sotto = new JPanel();
	JButton start = new JButton("Start");
	JButton stop = new JButton("Stop");
	
	JPanel centro = new JPanel();
	JPanel sx  = new JPanel();
	JPanel cx  = new JPanel();
	JPanel dx  = new JPanel();
	
	LayoutManager layout_1= new FlowLayout();
	LayoutManager layout_2 = new GridLayout(1, 3);
	
	public Window() {
		super("Ciao Mondo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700,500);
		
		sopra.add(address);
		sopra.add(address_input);
		sopra.add(port);
		sopra.add(port_input);
		sopra.add(connect);
		sopra.add(disconnect);
		sopra.setLayout(layout_1);
		this.add(sopra, BorderLayout.NORTH);
		
		sotto.add(start);
		sotto.add(stop);
		sotto.setLayout(layout_1);
		this.add(sotto, BorderLayout.SOUTH);
		
		
		sx.setBackground(Color.LIGHT_GRAY);
		sx.setBorder(BorderFactory.createLineBorder(Color.black));
		cx.setBackground(Color.LIGHT_GRAY);
		cx.setBorder(BorderFactory.createLineBorder(Color.black));
		dx.setBackground(Color.LIGHT_GRAY);
		dx.setBorder(BorderFactory.createLineBorder(Color.black));
		centro.add(sx);
		centro.add(cx);
		centro.add(dx);
		centro.setLayout(layout_2);
		this.add(centro);
		
		disconnect.setEnabled(false);
		start.setEnabled(false);
		stop.setEnabled(false);
		
		connect.addActionListener(new Action(address_input, port_input, connect, disconnect, start, stop, sx, cx, dx));
		disconnect.addActionListener(new Action(address_input, port_input, connect, disconnect, start, stop, sx, cx, dx));
		start.addActionListener(new Action(address_input, port_input, connect, disconnect, start, stop, sx, cx, dx));
		stop.addActionListener(new Action(address_input, port_input, connect, disconnect, start, stop, sx, cx, dx));
		
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
