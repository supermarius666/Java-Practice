package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Action implements ActionListener {

	Window w;
	static Socket client;
	static Scanner in;
	static PrintWriter out;
	Download d;
	Thread t;
	
	public Action(Window w) {
		this.w = w;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(e.getActionCommand().equals("Connect")) {
				client = new Socket(w.address_input.getText(), Integer.parseInt(w.port_input.getText()));
				w.connect.setEnabled(false);
				w.disconnect.setEnabled(true);
				w.start.setEnabled(true);
				w.v1.setEnabled(true);
				w.v2.setEnabled(true);
				w.v3.setEnabled(true);
			}
			if(e.getActionCommand().equals("Start")) {
				String selected = null;
				if(w.v1.isSelected())
					selected = "vm1";
				else if(w.v2.isSelected())
					selected = "vm2";
				else if(w.v3.isSelected())
					selected = "vm3";
				
				if(selected == null) {
					JOptionPane.showMessageDialog(new JOptionPane(),"Seleziona una VM!");
				}
				else {
				
					w.start.setEnabled(false);
					w.v1.setEnabled(false);
					w.v2.setEnabled(false);
					w.v3.setEnabled(false);
					w.disconnect.setEnabled(false);
					w.stop.setEnabled(true);
					
					out = new PrintWriter(client.getOutputStream());
					in = new Scanner(client.getInputStream());
				
					out.println("start:"+selected);
					out.flush();
							
					d = new Download(w,in);
					t = new Thread(d);
					t.start();
				}
			}
			if(e.getActionCommand().equals("Stop")) {
				out.println("stop");
				out.flush();
				w.stop.setEnabled(false);
				w.start.setEnabled(true);
				w.disconnect.setEnabled(true);
				w.v1.setEnabled(true);
				w.v2.setEnabled(true);
				w.v3.setEnabled(true);
			}
			if(e.getActionCommand().equals("Disconnect")) {
				out.println("disconnect");
				out.flush();
				client.close();
				in.close();
				out.close();
				w.connect.setEnabled(true);
				w.disconnect.setEnabled(false);
				w.stop.setEnabled(false);
				w.start.setEnabled(false);
				w.v1.setEnabled(false);
				w.v2.setEnabled(false);
				w.v3.setEnabled(false);
				w.p1.setValue(0);
				w.p2.setValue(0);
				w.p3.setValue(0);
				w.p4.setValue(0);
				w.gruppo.clearSelection();
			}

		} catch(IOException e2) {
			
		}
	}

}
