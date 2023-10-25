package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
class Keyboard implements KeyListener{

	private JTextField input;
	
	public Keyboard(JTextField input) {
		this.input = input;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_D:
			System.out.println("tastiera");
			break;
		case KeyEvent.VK_A:
			System.out.println("tastiera");
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
*/

class Action implements ActionListener{

	private JLabel state;
	private JTextArea show;
	private JTextField input;
	
	public Action(JLabel state, JTextArea show, JTextField input){
		this.show = show;
		this.state = state;
		this.input = input;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println(input.getText());
		if(!input.getText().equals(""))
			show.append(input.getText() + "\n");
		input.setText(null);
		if(show.getLineCount() == 30) {
			show.setText(null);
		}
	}
	
}


public class Window extends JFrame{
	
	private ImageIcon img = new ImageIcon("V.png");
	private JPanel up = new JPanel();
	private JPanel centre = new JPanel();
	private JPanel down = new JPanel();
	private LayoutManager grid = new GridLayout(1,5);
	private JLabel port_label = new JLabel("port", JLabel.CENTER);
	private JLabel adress_label = new JLabel("adress", JLabel.CENTER);
	private JLabel state = new JLabel("Waiting...", JLabel.CENTER);
	private JTextField port = new JTextField(10);
	private JTextField adress = new JTextField(10);
	private JTextField input_text = new JTextField(10);
	private JButton connect = new JButton("connect");
	private JButton send = new JButton("send");
	private JTextArea show = new JTextArea(30,50);
	
	
	
	public Window() {
		super("ServerApp");
		this.show.requestFocus();
		//addKeyListener(new Keyboard(this.input_text));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setIconImage(img.getImage());
		this.up.add(adress_label);
		this.up.add(adress);
		this.up.add(port_label);
		this.up.add(port);
		this.up.add(connect);
		this.up.setLayout(grid);
		this.add(up, BorderLayout.NORTH);
		this.show.setEditable(false);
		this.centre.add(show);
		this.add(centre, BorderLayout.CENTER);
		this.down.add(input_text);
		this.down.add(send);
		this.send.addActionListener(new Action(state,show,input_text));
		this.add(down, BorderLayout.SOUTH);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
