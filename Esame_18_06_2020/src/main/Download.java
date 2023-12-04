package main;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class Download implements Runnable {

	Window window;
	Scanner in;
	
	public Download(Window window,Scanner in) {
		this.window = window;
		this.in = in;
	}
	
	@Override
	public void run() {
		while(in.hasNext()) {
			String msg = in.nextLine();
			System.out.println(msg);
			String msgs[] = msg.split(":");
			
			int mines = 0;
			int selected = 0;
			
			if(msg.equals("interrupted")) {
				JOptionPane.showMessageDialog(new JOptionPane(), "La partita è stata interrota!");
				
				for(int i = 0; i < 10; i++) {
					for(int j = 0; j < 10; j++) {
						window.tiles[i][j].setSelected(false);
						window.start.setEnabled(true);
						window.stop.setEnabled(false);
						window.disconnect.setEnabled(true);
						window.rivela.setEnabled(true);
						return;
					}
				}
				
			}
			
			if(msg.equals("done")) {
				window.start.setEnabled(true);
				window.stop.setEnabled(false);
				window.disconnect.setEnabled(true);
				window.rivela.setEnabled(true);
				
				JOptionPane.showMessageDialog(new JOptionPane(), "La partita può iniziare!");
			
				
				while(true) {
					for(int i = 0; i < 10; i++) {
						for(int j = 0; j < 10; j++) {
							if(window.tiles[i][j].isSelected() && window.tiles[i][j].hasMine()) {
								
								for(int r = 0; r < 10; r++) {
									for(int c = 0; c < 10; c++) {
										window.tiles[r][c].reveal();
									}
								}
								JOptionPane.showMessageDialog(new JOptionPane(), "Hai perso!");
								return;
							}
							if(window.tiles[i][j].isSelected() && !window.tiles[i][j].hasMine()) {
								selected++;
							}
							if(selected == (100 - mines)) {
								JOptionPane.showMessageDialog(new JOptionPane(), "Hai vinto!");
								return;
							}
						}
					}
				}
			}
			
			Integer r = Integer.parseInt(msgs[0]);
			Integer c = Integer.parseInt(msgs[1]);
			Integer v = Integer.parseInt(msgs[2]);
			
			if(v != -1) {
				window.tiles[r][c].setAdjacentMinesCount(v);
			}
			else {
				window.tiles[r][c].setMine(true);
				mines++;
			}
		}

	}

}