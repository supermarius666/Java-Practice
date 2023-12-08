package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Action implements ActionListener {

	Window w;
	static Socket client;
	static PrintWriter out;
	static Scanner in;
	Download d;
	Thread t;
	
	public Action(Window w) {
		this.w = w;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(e.getActionCommand().equals("Connect")) {
				client = new Socket(w.address_input.getText(),Integer.parseInt(w.port_input.getText()));
				w.connect.setEnabled(false);
				w.disconnect.setEnabled(true);
				w.im1.setEnabled(true);
				w.im2.setEnabled(true);
				w.im3.setEnabled(true);
				w.im4.setEnabled(true);
				w.im5.setEnabled(true);
			}
			if(e.getActionCommand().equals("Image 1")) {
				out = new PrintWriter(client.getOutputStream());
				in = new Scanner(client.getInputStream());
				
				w.im1.setEnabled(false);
				w.im2.setEnabled(false);
				w.im3.setEnabled(false);
				w.im4.setEnabled(false);
				w.im5.setEnabled(false);
				w.disconnect.setEnabled(false);
				w.stop.setEnabled(true);
				
				out.println("start:image1");
				out.flush();
				
				w.print.append("==========Download iniziato==========\n");

				
				d = new Download(w,in);
				t = new Thread(d);
				t.start();
				
				
			}
			if(e.getActionCommand().equals("Image 2")) {
				out = new PrintWriter(client.getOutputStream());
				in = new Scanner(client.getInputStream());
				
				w.im1.setEnabled(false);
				w.im2.setEnabled(false);
				w.im3.setEnabled(false);
				w.im4.setEnabled(false);
				w.im5.setEnabled(false);
				w.disconnect.setEnabled(false);
				w.stop.setEnabled(true);
				

				w.print.append("==========Download iniziato==========\n");

				
				d = new Download(w,in);
				t = new Thread(d);
				t.start();
				
				
				
				out.println("start:image2");
				out.flush();
			}
			if(e.getActionCommand().equals("Image 3")) {
				out = new PrintWriter(client.getOutputStream());
				in = new Scanner(client.getInputStream());
				
				w.im1.setEnabled(false);
				w.im2.setEnabled(false);
				w.im3.setEnabled(false);
				w.im4.setEnabled(false);
				w.im5.setEnabled(false);
				w.disconnect.setEnabled(false);
				w.stop.setEnabled(true);
				
				w.print.append("==========Download iniziato==========\n");

				
				d = new Download(w,in);
				t = new Thread(d);
				t.start();
				
				
				
				out.println("start:image3");
				out.flush();
			}
			if(e.getActionCommand().equals("Image 4")) {
				out = new PrintWriter(client.getOutputStream());
				in = new Scanner(client.getInputStream());
				
				w.im1.setEnabled(false);
				w.im2.setEnabled(false);
				w.im3.setEnabled(false);
				w.im4.setEnabled(false);
				w.im5.setEnabled(false);
				w.disconnect.setEnabled(false);
				w.stop.setEnabled(true);

				w.print.append("==========Download iniziato==========\n");

				
				d = new Download(w,in);
				t = new Thread(d);
				t.start();
				
				
				out.println("start:image4");
				out.flush();
			}
			if(e.getActionCommand().equals("Image 5")) {
				out = new PrintWriter(client.getOutputStream());
				in = new Scanner(client.getInputStream());
				
				w.im1.setEnabled(false);
				w.im2.setEnabled(false);
				w.im3.setEnabled(false);
				w.im4.setEnabled(false);
				w.im5.setEnabled(false);
				w.disconnect.setEnabled(false);
				w.stop.setEnabled(true);
				
				w.print.append("==========Download iniziato==========\n");


				d = new Download(w,in);
				t = new Thread(d);
				t.start();
				
				
				out.println("start:image5");
				out.flush();
			}
			if(e.getActionCommand().equals("Stop")) {
				out.println("stop");
				out.flush();
			}
			if(e.getActionCommand().equals("Disconnect")) {
				out = new PrintWriter(client.getOutputStream());
				in = new Scanner(client.getInputStream());
				w.connect.setEnabled(true);
				w.disconnect.setEnabled(false);
				w.im1.setEnabled(false);
				w.im2.setEnabled(false);
				w.im3.setEnabled(false);
				w.im4.setEnabled(false);
				w.im5.setEnabled(false);
				w.stop.setEnabled(false);
				
				out.println("disconnect");
				out.flush();
				client.close();
				in.close();
				out.close();
			}
			
			
		}catch(IOException ex) {
			
		}

	}

}
