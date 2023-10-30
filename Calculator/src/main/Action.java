package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;


public class Action implements ActionListener {

	private JTextArea input_area;
	private String infix = "";
	private int result = 0;
	private Evaluator evaluation = new Evaluator();
	
	public Action(JTextArea input_area) {
		this.input_area = input_area;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("1")) {
			this.input_area.append(e.getActionCommand());
		}
		if(e.getActionCommand().equals("2")) {
			this.input_area.append(e.getActionCommand());
		}
		if(e.getActionCommand().equals("3")) {
			this.input_area.append(e.getActionCommand());
		}
		if(e.getActionCommand().equals("4")) {
			this.input_area.append(e.getActionCommand());
		}
		if(e.getActionCommand().equals("5")) {
			this.input_area.append(e.getActionCommand());
		}
		if(e.getActionCommand().equals("6")) {
			this.input_area.append(e.getActionCommand());
		}
		if(e.getActionCommand().equals("7")) {
			this.input_area.append(e.getActionCommand());
		}
		if(e.getActionCommand().equals("8")) {
			this.input_area.append(e.getActionCommand());
		}
		if(e.getActionCommand().equals("9")) {
			this.input_area.append(e.getActionCommand());
		}
		if(e.getActionCommand().equals("0")) {
			this.input_area.append(e.getActionCommand());
		}
		if(e.getActionCommand().equals("+")) {
			this.input_area.append(e.getActionCommand());
		}
		if(e.getActionCommand().equals("-")) {
			this.input_area.append(e.getActionCommand());
		}
		if(e.getActionCommand().equals("x")) {
			this.input_area.append(e.getActionCommand());
		}
		if(e.getActionCommand().equals("/")) {
			this.input_area.append(e.getActionCommand());
		}
		if(e.getActionCommand().equals("=")) {
			this.infix = this.input_area.getText();
			this.input_area.setText(null);
			//new 
			this.result = this.evaluation.eval(this.infix);
			this.input_area.setText(String.valueOf(result));
			
		}
		if(e.getActionCommand().equals("C")) {
			this.input_area.setText(null);
		}
		
	}

}










