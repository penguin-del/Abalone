package utilities;

import java.util.ArrayList;

public class Equals {
	
	public static <T> boolean subsetEq(ArrayList<T> largeSet, ArrayList<T> smallSet){

		if (largeSet.size() != smallSet.size()) return false;
		return subset(largeSet,smallSet);
	}

	public static <T> boolean subset(ArrayList<T> larger, ArrayList<T> smaller) {
		for (T obj: smaller) {
			if(!(larger.contains(obj))) return false;
		}
		return true;
	}
}

