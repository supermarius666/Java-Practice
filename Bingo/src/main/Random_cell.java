package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Random_cell implements Runnable{

	private TicketCell[][] cartella;
	private ArrayList<Integer> numbers = new ArrayList<Integer>();
	
	
	public Random_cell(TicketCell[][] cartella){
		this.cartella = cartella;
	}
	
	@Override
	public void run() {
		Random random = new Random();
		while(numbers.size() != 15) {
			int num = random.nextInt(90-1)+1;
			if(!numbers.contains(num)) {
				numbers.add(num);
			} else {
				continue;
			}
		}
		Collections.sort(numbers);
		int z = 0;
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 3; j++) {
				cartella[j][i].setValue(numbers.get(z++));
				
			}
		}
	
	}

}
