
//	Date: 		March 9, 2020
//	Description:	
//				This is the class Expression which includes the class methods:
//					convertToPostfix and evaluatePostfix
//

import java.lang.Math;

public class Expression {
	
	/**Converting the infix to a postfix expression.
	 * @param infixExpression - the expression to convert to postfix.
	 * @return a String array of the postfix expression. 
	 */
	public static String[] convertToPostfix(String[] infixExpression) {
		
		String postfix = "";
		String topOperator = "";
		
		StackInterface<String> operatorStack = new ArrayStack<>();
		
		int index = 0;
						
		if(checkBalance(infixExpression)) {
									
			while(index < infixExpression.length) {
				
				String nextCharacter = infixExpression[index];
												
				switch(nextCharacter) {
								
					case "^": 
						operatorStack.push(nextCharacter);
						break;
				
					case "+": case "-": case "*": case "/":	
						while(!operatorStack.isEmpty() && precedence(nextCharacter) <= precedence(operatorStack.peek()) ) { //checks precedence
							
							if(operatorStack.peek().equals("(")) {
								break;
							}
							else {
								postfix+=operatorStack.pop();
							}
						}
						operatorStack.push(nextCharacter);
						break;
						
					case "{": case "(": case "[": 
						operatorStack.push(nextCharacter);
						break;
						
					case "}": case ")": case "]": 
						topOperator = operatorStack.pop();
						while ((!operatorStack.isEmpty()) && ( !(topOperator.equals("{") || topOperator.equals("(") || topOperator.equals("[")) )) { 

							postfix+=topOperator;
							topOperator = operatorStack.pop();
						}
						break;
						
					default:
						if(nextCharacter.matches("[a-z]")) //checks if it is a variable (name).
							postfix+=nextCharacter;	
						else if (nextCharacter.matches("[0-9]")) //checks if it is an integer literal.
							postfix+=nextCharacter;
						break;
				}
				
				++index;
			}
			
			while(!operatorStack.isEmpty()) {
				
				postfix+=operatorStack.pop();
			}
	
		} else 
			throw new RuntimeException("Infix expression is not balanced or well-formed.");
		
		
		String[] postfixEquation = postfix.split(""); //converts the String to String[].
		
		return postfixEquation; 
		
	}
	
	
	/**Evaluates the postfix expression.
	 * @param postfixExpression - the expression to evaluate
	 * @param nameBag - the bag that contains all the values of the names.
	 * @return The integer value of the answer to the expression.
	 */
	public static int evaluatePostfix(String[] postfixExpression, BagInterface<Name> nameBag) {
		
		StackInterface<Integer> equation = new ArrayStack<>();
						
		int i = 0;
		int value = 0;
		int answer = 0;
						
			
		while (i < postfixExpression.length) {
			
			String nextElement = postfixExpression[i];
			
			switch(nextElement) {
				case "+": case "-": case "/": case "*": case "^": //performs the operation
					if (!equation.isEmpty()) { 
						
						int num1 = equation.pop();
						int num2 = equation.pop();
					
						switch(nextElement) { 
						 
						 	case"+":
						 		equation.push(num1 + num2);
						 		break;
						 	case"-":
						 		equation.push(num2 - num1);
						 		break;
						 	case"/":
						 		equation.push(num2 / num1);
						 		break;
						 	case"*":
						 		equation.push(num1 * num2);
						 		break;
						 	case"^":
						 		equation.push( (int)Math.pow(num2, num1) );
						 		break;
						}
					}
					break;
					
				default: //pushes values into the equation (stack) to be solved
					if(nextElement.matches("[a-z]")) {
						
						value = getNameValue(nextElement, nameBag);
						
						if (value == -1) //name is not found
							throw new RuntimeException("Name is not found.");
						else 
							equation.push(value);
						
					} else if(nextElement.matches("[0-9]")) {
						
						value = Integer.parseInt(nextElement);
						equation.push(value);
					}
					break;
			}
			
			i++;
		} 
			
		if(!equation.isEmpty())
			answer = equation.pop();
		
		return answer;
	}
	
	
	/**Checks the operator's precedence. This is a helper method.
	 * @param operator the operator to check.
	 * @return the int value of precedence.
	 */
	private static int precedence(String operator) {
		
		int value = 0;
		
		switch(operator) {
		
			case "+": case "-":
				value = 1;
				break;
			case "*": case "/":
				value = 2;
				break;
			case "^": 
				value = 3;
				break;
			default:
				value = -1;
				break;
		}
		
		return value;
	}
	

	/** Retrieves the value of the name(variable). 
	 * This is a helper method.
	 * @param name the token to check
	 * @param nameBag the list of values
	 * @return -1 if name is not found and the value of the name if the name is found
	 */
	private static int getNameValue(String name, BagInterface<Name> nameBag) {
						
		Object[] values = nameBag.toArray(); //convert bag to array
		
		int value = -1;
		int i = 0;
				
		while(i < values.length) {
						
			if( (((Name)values[i]).getName()).equals(name)) { //variable
				
				value = ((Name) values[i]).getValue();
				break;
				
			} else 
				i++;
		}
		
		return value;
	}


	/**Checks whether the expression is well formed.
	 * @param expression the array to check 
	 * @return true if balanced, false if not.
	 */
	public static boolean checkBalance(String[] expression) {
		
		StackInterface<String> openStack = new ArrayStack<>(); //Stack for (, {, [
		
		boolean isBalanced = true;
		int index = 0;
		String nextCharacter = "";
		
		while(isBalanced && (index<expression.length)) {
			
			nextCharacter = expression[index];
		
			switch(nextCharacter) {
				
				case "(": case "[": case "{":
					openStack.push(nextCharacter);
					break;
					
				case ")": case "]": case "}":
					if(openStack.isEmpty()) 
						isBalanced = false;	
					
					else {
						
						String openDelimiter = openStack.pop();
						isBalanced = isPaired(openDelimiter, nextCharacter); //checks if (), [], {} form a pair (helper method)
					}
					break;
					
				default:
					if(nextCharacter.matches("[a-z]") && index < expression.length-1) {
						
						//checks if an operand is missing after a name (variable)
						int index2 = index + 1;
						isBalanced = isOperandPresent(expression[index2]); //helper method	
					}
					break;
			}
			index++;
			
		}
		
		if(!openStack.isEmpty())
			isBalanced = false;
		
		return isBalanced;
	}
	
	//Returns true if the given strings, open and close, form a pair of (), [], or {}. 
	//This is a helper method.
	private static boolean isPaired(String open, String close) {
		return ((open.equals("(") && close.equals(")")) ||
				(open.equals("[") && close.equals("]")) ||
				(open.equals("{") && close.equals("}")));
	}
	
	//Returns true if the given string is an operator and ")", "]", "}". 
	//This is a helper method.
	private static boolean isOperandPresent(String next) {
		
		boolean present = false;
				
		switch(next) {
			case "+": case "-": case "/": case "*": case "^":
				present = true;
				break;
			case ")": case "]": case "}":
				present = true;
				break;
		}
		
		return present;
	}
		
}