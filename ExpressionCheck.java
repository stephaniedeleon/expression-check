
//	Date: 		March 9, 2020
//	Description:	
//				This is the main method. 
//

public class ExpressionCheck {

	public static void main(String[] args) {
				
		BagInterface<Name> names = new ArrayBag<>();
				
		//assigning the names to values
		names.add(new Name("a", 10)); 
		names.add(new Name("b", 5));
		names.add(new Name("c", 2));
		names.add(new Name("d", 7));
		names.add(new Name("e", 4));
		names.add(new Name("a", 5)); 
		
		Object[] values = names.toArray();

		System.out.println("S. De Leon's Stack ADT \n");
	
	//Values
		System.out.println("Values:");
		for (int i = 0; i < values.length; i++) {
			System.out.println("  " + values[i].toString());
		}
	
		
	//Scenario 1: correct input
		System.out.print("\nScenario 1 - correct input");
		String[] infix1 = "a / b * (c + [d - e])".replace(" ", "").split(""); 
		System.out.print("\nInfix Expression: ");
		for(String element : infix1) 
			System.out.print(element);
		
		String[] postfix1 = Expression.convertToPostfix(infix1);
		System.out.print("\nPostfix Expression: ");
		for(String element : postfix1) 
			System.out.print(element);
		
		int value1 = Expression.evaluatePostfix(postfix1, names);
		System.out.println("\nAnswer: " + value1);

			
	//Scenario 2: without parenthesis 
		System.out.print("\nScenario 2 - without parenthesis");
		String[] infix2 = "d + a * c".replace(" ", "").split("");
		System.out.print("\nInfix Expression: ");
		for(String element : infix2) 
			System.out.print(element);
		
		String[] postfix2 = Expression.convertToPostfix(infix2);
		System.out.print("\nPostfix Expression: ");
		for(String element : postfix2) 
			System.out.print(element);
		
		int value2 = Expression.evaluatePostfix(postfix2, names);
		System.out.println("\nAnswer: " + value2);
		
		
	//Scenario 3: matched parenthesis
		System.out.print("\nScenario 3 - matched parenthesis");
		String[] infix3 = "{b ^ 3}".replace(" ", "").split("");
		System.out.print("\nInfix Expression: ");
		for(String element : infix3) 
			System.out.print(element);
		
		String[] postfix3 = Expression.convertToPostfix(infix3);
		System.out.print("\nPostfix Expression: ");
		for(String element : postfix3) 
			System.out.print(element);
		
		int value3 = Expression.evaluatePostfix(postfix3, names);	
		System.out.println("\nAnswer: " + value3);

		
	//Scenario 4: with integer literals
		System.out.print("\nScenario 4 - with integer literals");
		String[] infix4 = "7 + e + 9".replace(" ", "").split(""); //
		System.out.print("\nInfix Expression: ");
		for(String element : infix4) 
			System.out.print(element);
		
		String[] postfix4 = Expression.convertToPostfix(infix4);
		System.out.print("\nPostfix Expression: ");
		for(String element : postfix4) 
			System.out.print(element);
		
		int value4 = Expression.evaluatePostfix(postfix4, names);
		System.out.println("\nAnswer: " + value4);

		
	//Scenario 5: incorrect(name does not exist)
		System.out.print("\nScenario 5 - incorrect(name does not exist)");
		String[] infix5 = "(c + a) * [e - f]".replace(" ", "").split("");
		System.out.print("\nInfix Expression: ");
		for(String element : infix5) 
			System.out.print(element);
		
		String[] postfix5 = Expression.convertToPostfix(infix5);
		System.out.print("\nPostfix Expression: ");
		for(String element : postfix5) 
			System.out.print(element);
		
		int value5 = Expression.evaluatePostfix(postfix5, names);
		System.out.println("\nAnswer: " + value5);


	//Scenario 6: incorrect(unmatched parenthesis (missing closing))
		System.out.print("\nScenario 6 - incorrect(unmatched parenthesis)");
		String[] infix6 = "(a * [e - b]".replace(" ", "").split("");
		System.out.print("\nInfix Expression: ");
		for(String element : infix6) 
			System.out.print(element);
		
		String[] postfix6 = Expression.convertToPostfix(infix6);
		System.out.print("\nPostfix Expression: ");
		for(String element : postfix6) 
			System.out.print(element);
		
		int value6 = Expression.evaluatePostfix(postfix6, names);
		System.out.println("\nAnswer: " + value6); 


	//Scenario 7: incorrect(operand is missing)
		System.out.print("\nScenario 7 - incorrect(operand is missing)");
		String[] infix7 = "a b * {b - d}".replace(" ", "").split("");
		System.out.print("\nInfix Expression: ");
		for(String element : infix7) 
			System.out.print(element);
		
		String[] postfix7 = Expression.convertToPostfix(infix7);
		System.out.print("\nPostfix Expression: ");
		for(String element : postfix7) 
			System.out.print(element);
		
		int value7 = Expression.evaluatePostfix(postfix7, names);
		System.out.println("\nAnswer: " + value7);


	//Scenario 8: incorrect(unmatched parenthesis (missing opening))
		System.out.print("\nScenario 8 - incorrect(unmatched parenthesis (missing opening))");
		String[] infix8 = "c * e - a]".replace(" ", "").split("");
		System.out.print("\nInfix Expression: ");
		for(String element : infix8) 
			System.out.print(element);
		
		String[] postfix8 = Expression.convertToPostfix(infix8);
		System.out.print("\nPostfix Expression: ");
		for(String element : postfix8) 
			System.out.print(element);
		
		int value8 = Expression.evaluatePostfix(postfix8, names);
		System.out.println("\nAnswer: " + value8); 

	}
	
}


