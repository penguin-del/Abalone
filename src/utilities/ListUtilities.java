package utilities;

import java.util.ArrayList;

public class ListUtilities
{

	public static <T extends Comparable<T>> void ListInsertInOrder (ArrayList<T> list , T item){
		//loops through every item in a generic array list  
		//and adds a new item in such a way that the list is sorted
		for (int i = 0; i < list.size(); i++) {

			//the if statement orders the itmes from least to greatest by our definition of least to greatest (See Line.java)
			if (item.compareTo(list.get(i)) == -1) {
				list.add(i, item);
				return;
			}
		}

		//Adds the item onto the end of the list if it is greater than every other item
		list.add(item);
		
	}

	public static <T> String toStringCommaSeparated(ArrayList<T> list)
	{
		String str = "";
		for (T item : list)
		{
			str += item.toString() + ", ";
		}

		return str.substring(0, str.length() - 2);
	}
	

	public static <T extends Comparable<T>> int ListCompareTo(ArrayList<T> left, ArrayList<T> right)
	{
		// Returns based on size if not equal left < right => -1 ; left > right => 1
		if (left.size() != right.size()) return left.size() > right.size() ? 1 : -1;

		for (int index = 0; index < left.size(); index++)
		{
			int cmp = left.get(index).compareTo(right.get(index));

			if (cmp != 0) return cmp;
		}

		// Two sets of items are exactly equal
		return 0;
	}
}
