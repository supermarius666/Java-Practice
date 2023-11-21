package main;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.TitledBorder;
import java.net.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


class Download implements Runnable{

	Scanner in;
	JTextArea console_log;
	JButton execute;
	JButton disconnect;
	JButton interrupt;
	
	public Download(Scanner in, JTextArea console_log, JButton execute, JButton disconnect, JButton interrupt) {
		this.in = in;
		this.console_log = console_log;
		this.execute = execute;
		this.interrupt = interrupt;
		this.disconnect = disconnect;
	}
	
	@Override
	public void run() {
		
		while(in.hasNext()) {
			String msg = in.nextLine();
			if(msg.equals("INTERRUPTED")) {
				console_log.append("==========Download interrotto==========\n");
				return;
			}
			if(msg.equals("END")) {
				console_log.append("==========Download completato==========\n");
				interrupt.setEnabled(false);
				disconnect.setEnabled(true);
				execute.setEnabled(true);
			}
			if(msg.equals("ERROR")) {
				console_log.append("==========Commando errato==========\n");
				interrupt.setEnabled(false);
				disconnect.setEnabled(true);
				execute.setEnabled(true);
			}
			else {
				console_log.append(msg+"\n");
			}
		}
	}
	
}

class Action implements ActionListener{

	JButton connect;
	JButton disconnect;
	JButton execute;
	JButton interrupt;
	JTextArea address;
	JTextArea port;
	JTextArea command;
	JTextArea console_log;
	Thread t;
	Download d;
	static Socket client;
	static PrintWriter out;
	static Scanner in;
	
	
	public Action(JButton connect, JButton disconnect, JButton execute, JButton interrupt, JTextArea address, JTextArea port, JTextArea command, JTextArea console_log){
		this.connect = connect;
		this.disconnect = disconnect;
		this.execute = execute;
		this.interrupt = interrupt;
		this.address = address;
		this.port = port;
		this.command = command;
		this.console_log = console_log;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(e.getActionCommand().equals("Connect")) {
				client = new Socket(this.address.getText(), Integer.parseInt(this.port.getText()));
				connect.setEnabled(false);
				disconnect.setEnabled(true);
				execute.setEnabled(true);
			}
			if(e.getActionCommand().equals("Execute")) {
				interrupt.setEnabled(true);
				disconnect.setEnabled(false);
				execute.setEnabled(false);
				out = new PrintWriter(client.getOutputStream());
				in = new Scanner(client.getInputStream());
		
				out.println(this.command.getText());
				out.flush();
				
				d = new Download(in, console_log,execute, disconnect, interrupt);
				t = new Thread(d);
				t.start();
			}
			if(e.getActionCommand().equals("Disconnect")) {
				out.println("DISCONNECT");
				out.flush();
				interrupt.setEnabled(false);
				disconnect.setEnabled(false);
				execute.setEnabled(false);
				connect.setEnabled(true);
				client.close();
				in.close();
				out.close();
			}
			if(e.getActionCommand().equals("Interrupt")) {
				out.println("INTERRUPT");
				out.flush();
				interrupt.setEnabled(false);
				disconnect.setEnabled(true);
				execute.setEnabled(true);
			}
			
		} catch(IOException e1) {
			
		}
		
	}
}


@SuppressWarnings("serial")
public class Window extends JFrame{
	
	JPanel sopra = new JPanel();
	JLabel address = new JLabel("Server Address");
	JTextArea address_input = new JTextArea("192.168.51.90", 1,8);
	JLabel port = new JLabel("Port");
	JTextArea port_input = new JTextArea("4400", 1,8);
	JButton connect = new JButton("Connect");
	JButton disconnect = new JButton("Disconnect");
	
	LayoutManager layout1= new FlowLayout();
	
	JPanel sotto = new JPanel();
	JLabel command = new JLabel("Command");
	JTextArea command_input = new JTextArea(1,20);
	JButton execute = new JButton("Execute");
	JButton interrupt = new JButton("Interrupt");
	
	JPanel centro = new JPanel();
	JTextArea console_log = new JTextArea(25,80);
	TitledBorder border = new TitledBorder("Console"); 
	JScrollPane scroll = new JScrollPane();
	
	
	public Window() {
		super("Ciao Mondo");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(900,500);
		this.setResizable(false);
		
		sopra.add(address);
		sopra.add(address_input);
		sopra.add(port);
		sopra.add(port_input);
		sopra.add(connect);
		sopra.add(disconnect);
		sopra.setLayout(layout1);
		this.add(sopra, BorderLayout.NORTH);
		
		centro.setBorder(border);
		centro.add(console_log);
		this.add(centro);
		
		sotto.add(command);
		sotto.add(command_input);
		sotto.add(execute);
		sotto.add(interrupt);
		sotto.setLayout(layout1);
		this.add(sotto, BorderLayout.SOUTH);
		
		disconnect.setEnabled(false);
		execute.setEnabled(false);
		interrupt.setEnabled(false);
		
		console_log.setEditable(false);
		connect.addActionListener(new Action(connect,disconnect,execute,interrupt,address_input,port_input,command_input,console_log));
		disconnect.addActionListener(new Action(connect,disconnect,execute,interrupt,address_input,port_input,command_input,console_log));
		execute.addActionListener(new Action(connect,disconnect,execute,interrupt,address_input,port_input,command_input,console_log));
		interrupt.addActionListener(new Action(connect,disconnect,execute,interrupt,address_input,port_input,command_input,console_log));
		
		
		this.setVisible(true);
		
	}

}
