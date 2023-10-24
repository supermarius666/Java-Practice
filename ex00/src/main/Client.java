package main;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
	
	private PrintWriter out;
	private PrintWriter write;
	private Scanner in;
	private String adress;
	private String req;
	private String file_name;
	private File file;
	
	public Client(String adress, String file_name)  {
		this.adress = adress;
		this.file_name = file_name;
	}
	
	public int answer() throws UnknownHostException {
		Socket client;
		try {
			client = new Socket(adress, 80);
			out = new PrintWriter(client.getOutputStream());
			file = new File(file_name);
			if(file.exists()) return -1;
			else write = new PrintWriter(file_name);
			in  = new Scanner(client.getInputStream());
			out.print("GET /\r\n\r\n");
			out.flush();
			while(in.hasNext()) {
				req = in.nextLine();
				write.print(req);
			}
			client.close();
			in.close();
			out.close();
			write.close();

		} catch (IOException e) {
			return -1;
		}
		return 0;
	}
}
