
//	Date: 		March 9, 2020
//	Description:	
//				This is the Bag Interface.
//

public interface BagInterface<T> {

	/** Gets the current number of entries in the bag.
		@return The int number of entries currently in bag. */
	public int getCurrentSize();
	

	/** Sees whether the bag is empty or not
		@return True if the bag is empty, false if not. */
	public boolean isEmpty();
	

	/** Adds a new entry to the bag
		@param newEntry the object to be added as a new entry
		@return True if adding successful, false if not. */
	public boolean add(T newEntry);
	
	
	/**Removes one specified entry from bag
		@param anEntry the entry to be removed 
		@return True if removal successful and false if not.*/
	public boolean remove(T anEntry);		

	
	/**Counts the number of times a given entry appears in the bag.
		@param anEntry entry to be counted
		@return The number of times the entry appears in the bag. */
	public int getFrequencyOf(T anEntry);
	

	/**Tests whether the bag contains a given entry.
		@param anEntry the entry to find
		@return True whether it is found, false if not.*/
	public boolean contains(T anEntry);
	

	/**Retrieves all entries that are in the bag.
		@return A newly allocated array of all entries in the bag. */
	public T[] toArray();


}