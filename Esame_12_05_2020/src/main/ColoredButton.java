package main;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.swing.JButton;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class ColoredButton extends JButton{
	
	//stringa che mantiene l'informazione sulla posizione della casella nella griglia
	private String position;
	//colore della casella
	private Color color;
	//cifra visualizzata all'interno della casella
	private String digit;
	
	public ColoredButton(String position, Color color) {
		this.position = position;
		this.color = color;
		
		this.digit = "";
		
		DigitListener listener = new DigitListener();
		this.addActionListener(listener);

		changeColor(color);
	}
	
	public void setTextDigit(String digit) {
		// metodo per gestire l'informazione riguardo la cifra visualizzata all'interno della casella
		// passare come parametro la stringa vuota "" per ripristinare il contenuto di una casella
		this.digit=digit;
		super.setText(digit);
	}

	public void changeColor(Color c) {
		// metodo per gestire l'informazione riguardo ai cambi di colore della casella
		this.color = c;
		super.setBackground(c);
	}

	public boolean isGreen() {
		// metodo che indica se la casella in questione contiene un numero vincente
		return color.equals(Color.GREEN);
	}
	
	public String getDigit() {
		//metodo utile per verificare a-posteriori se la casella in questione contiene una cifra o una stringa vuota ""
		return digit;
	}
	
	@Override
	public void setBackground(Color bg) {
		// NON UTILIZZARE setBackground per cambiare colore ad un'istanza di ColoredButton. 
		// Usare invece il metodo changeColor(Color color) 
		return;
	}
	
	@Override
	public void setText(String str) {
		// NON UTILIZZARE setText per cambiare il testo visualizzato ad un'istanza di ColoredButton. 
		// Usare invece il metodo setTextDigit(String n) 
		return;
	}

}

class NumberInput extends Reader {
    private InputStreamReader reader;

    public NumberInput() {
        reader = new InputStreamReader(System.in);
    }

    @Override
    public int read() throws IOException {
        int digit = -1;
        while (digit < 0 || digit > 9) {
            System.out.print("Inserisci un numero da 0 a 9: ");
            digit = reader.read() - '0';
            reader.skip(1); // Salta il carattere di nuova riga
            if (digit < 0 || digit > 9) {
                System.out.println("Il numero deve essere compreso tra 0 e 9. Riprova.");
            }
        }
        return digit;
    }
    
	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}
}

final class DigitListener implements ActionListener {
	
	//listener per cambio cifra con il click
	protected DigitListener(){
		super();
	}
	
	public void actionPerformed(ActionEvent e) {
		ColoredButton cb = (ColoredButton) e.getSource();
		Reader r = new NumberInput();
		String digit = null;
		try {
			digit = String.valueOf(r.read());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cb.setTextDigit(""+digit);
		cb.changeColor(Color.LIGHT_GRAY);
		try {
			r.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}