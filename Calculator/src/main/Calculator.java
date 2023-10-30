package main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Calculator extends JFrame{
	
	private ImageIcon img = new ImageIcon("icon.png"); 
	private JTextArea input_area = new JTextArea(4, 33);
	private LayoutManager buttons = new GridLayout(4,4);
	private Font font = new Font("Monospaced", Font.BOLD, 16);
	private JPanel input_panel = new JPanel();
	private JPanel buttons_panel = new JPanel();
	private JButton one = new JButton("1");
	private JButton two = new JButton("2");
	private JButton three = new JButton("3");
	private JButton four = new JButton("4");
	private JButton five = new JButton("5");
	private JButton six = new JButton("6");
	private JButton seven = new JButton("7");
	private JButton eight = new JButton("8");
	private JButton nine = new JButton("9");
	private JButton zero = new JButton("0");
	private JButton plus = new JButton("+");
	private JButton minus = new JButton("-");
	private JButton times = new JButton("x");
	private JButton divide = new JButton("/");
	private JButton equal = new JButton("=");
	private JButton del = new JButton("C");
	
	public Calculator() {
		super("Calculator");
		this.setIconImage(img.getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.input_area.setFont(font);
		this.input_panel.add(input_area);
		this.add(input_panel, BorderLayout.NORTH);
		this.buttons_panel.add(seven);
		this.seven.addActionListener(new Action(this.input_area));
		this.buttons_panel.add(eight);
		this.eight.addActionListener(new Action(this.input_area));
		this.buttons_panel.add(nine);
		this.nine.addActionListener(new Action(this.input_area));
		this.buttons_panel.add(times);
		this.times.addActionListener(new Action(this.input_area));
		this.buttons_panel.add(four);
		this.four.addActionListener(new Action(this.input_area));
		this.buttons_panel.add(five);
		this.five.addActionListener(new Action(this.input_area));
		this.buttons_panel.add(six);
		this.six.addActionListener(new Action(this.input_area));
		this.buttons_panel.add(minus);
		this.minus.addActionListener(new Action(this.input_area));
		this.buttons_panel.add(one);
		this.one.addActionListener(new Action(this.input_area));
		this.buttons_panel.add(two);
		this.two.addActionListener(new Action(this.input_area));
		this.buttons_panel.add(three);
		this.three.addActionListener(new Action(this.input_area));
		this.buttons_panel.add(plus);
		this.plus.addActionListener(new Action(this.input_area));
		this.buttons_panel.add(del);
		this.del.addActionListener(new Action(this.input_area));
		this.buttons_panel.add(zero);
		this.zero.addActionListener(new Action(this.input_area));
		this.buttons_panel.add(divide);
		this.divide.addActionListener(new Action(this.input_area));
		this.buttons_panel.add(equal);
		this.equal.addActionListener(new Action(this.input_area));
		this.add(buttons_panel, BorderLayout.CENTER);
		this.buttons_panel.setLayout(buttons);
		this.setSize(350,400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}














