package main;

import java.util.Stack;

@SuppressWarnings("unused")
public class Evaluator {
	
	private int getPrecedence(char ch) {
		if(ch == '+' || ch == '-') return 1;
		else if(ch == '/' || ch == 'x') return 2;
		else if(ch == '(' || ch == ')') return 3;
		else return 0;
	}
	
	private int calculate(int a, int b, char op) {
		switch(op) {
		case '+': return a + b;
		case '-': return b - a;
		case 'x': return a * b;
		case '/': 
			if(b == 0)
				return 0;
			else if(a == 0)
				return  0;
			else
				return b / a;
		default: return 0;
		}
	}
	
	public int eval(String infix) {
		Stack<Character> operators = new Stack<Character>();
		Stack<Integer> numbers = new Stack<Integer>();
		
		for(int i = 0; i < infix.length(); i++) {
			char ch = infix.charAt(i);
			if(Character.isDigit(ch)) {
				String value = "";
				while(i < infix.length()) {
					if(Character.isDigit(infix.charAt(i))) {
						value += infix.charAt(i++);
					} else {
						break;
					}
				}
				i--;
				numbers.push(Integer.parseInt(value));
			} else if(ch == '(') {
				operators.push(ch);
			} else if(ch == '+' || ch == '-' || ch == 'x' || ch == '/') {
				//first operator then number 
				if(operators.isEmpty()) {
					operators.push(ch);
				} else if(operators.peek() == '(') {
					operators.push(ch);
				} else if(getPrecedence(operators.peek()) >= getPrecedence(ch)) {
					char op = operators.pop();
					int a = numbers.pop();
					int b = numbers.pop();
					int result = calculate(a,b,op);
					numbers.push(result);
					operators.push(ch);
				} else {
					operators.push(ch);
				}
			//close 
			} else if(ch == ')') {
				while(operators.peek() != '(') {
					char op = operators.pop();
					int a = numbers.pop();
					int b = numbers.pop();
					int result = calculate(a,b,op);
					numbers.push(result);
				}
				operators.pop();
			}
		}
		
		while(!operators.isEmpty()) {
			char op = operators.pop();
			int a = numbers.pop();
			int b = numbers.pop();
			int result = calculate(a,b,op);
			numbers.push(result);
		}	
		return numbers.pop();
		
	}
}
