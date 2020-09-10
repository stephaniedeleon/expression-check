
//	Date: 		March 9, 2020
//	Description:	
//				This is the Name class.
//

public class Name {
	
	String name = null;
	int value = 0;
	
	public Name() {
		
		this.name = null;
		this.value = 0;	
	}
	
	public Name(String name, int value) {
		
		this.name = name;
		this.value = value;	
	}
	
	
	public int getValue() {
		
		return value;
	}
	
	public String getName() {
		
		return name;
	}

	@Override
	public String toString() {
		return name + " = " + value;
	}

}
