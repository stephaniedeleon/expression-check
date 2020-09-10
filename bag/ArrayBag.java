
//	Date: 		March 9, 2020
//	Description:	
//				This the class ArrayBag that implements BagInterface.
//

import java.util.Arrays;

public final class ArrayBag<T> implements BagInterface<T> {
	
	private T[] bag;	
	private int numberOfEntries;	
	private static final int DEFAULT_CAPACITY = 25;
	
	
	//default constructor
	public ArrayBag()
	{
		this(DEFAULT_CAPACITY);
	}
	
	//constructor
	public ArrayBag(int capacity) {
		
		@SuppressWarnings("unchecked")
		T[] tempBag = (T[]) new Object[capacity];
		bag = tempBag;
		numberOfEntries = 0;
		
	}
	

	//reports the current number of objects in the bag
	@Override
	public int getCurrentSize() {
		
		return numberOfEntries;			
	}


	//checks whether the bag is empty
	@Override
	public boolean isEmpty() {
		
		return numberOfEntries == 0;	
	}


	//adds a given object to the bag
	@Override
	public boolean add(T newEntry) {
		
		boolean result = true;
		
		if (isArrayFull()) {
			
			bag = Arrays.copyOf(bag, 2*bag.length);
			
		} else {
			
			bag[numberOfEntries] = newEntry;
			numberOfEntries++;
			
		}
		
		return result;
	}
	
	
	//returns true if the array bag is full, or false if not
	private boolean isArrayFull() {
		
		return numberOfEntries >= bag.length;	
	}
	

	//removes one particular object from the bag, if possible
	@Override
	public boolean remove(T anEntry) {
		
		int index = getIndexOf(anEntry);
		T result = removeEntry(index);
		return anEntry.equals(result);
	} 
	

	//removes and returns the entry at a given index within the array bag
	//if entry does not exist returns null
	public T removeEntry(int givenIndex) {
		
		T entry = null;
		
		if (numberOfEntries>0){
			
			numberOfEntries--;
			entry = bag[givenIndex]; //entry that needs to be removed
			bag[givenIndex] = bag[numberOfEntries]; //replaces with last entry
			bag[numberOfEntries] = null; //last entry becomes null
			
		} 
		
		return entry;
	}
	

	//locates a given entry in array bag
	//returns the index of the entry, or -1 if not located
	public int getIndexOf(T anEntry) {
		
		int where = -1;
		boolean found = false;
		int index = 0;
		while (!found && index<numberOfEntries) {
			if(anEntry .equals(bag[index])) {
				found = true;
				where = index;
			}
			else 
				index++;
		}
		
		return where;	
	}
	
	
	//counts the number of times an object occurs in the bag
	@Override
	public int getFrequencyOf(T anEntry) {
		
		int counter = 0;
		for (int index = 0; index < numberOfEntries; index++) {
			
			if (anEntry.equals(bag[index]))
				counter++;
			
		}
		
		return counter;	
	}


	//tests whether the bag contains a particular object
	@Override
	public boolean contains(T anEntry) {
		
		return getFrequencyOf(anEntry) > 0;	
	}
	
	
	//retrieves all object in bag and outputs a new array of those entries(objects)
	@Override
	public T[] toArray() {
		
		@SuppressWarnings("unchecked")
		T[] actualBag = (T[]) new Object[numberOfEntries];
		for (int index = 0; index<numberOfEntries; index++) {
			actualBag[index] = bag[index];
		}
		return actualBag;
	}

}
