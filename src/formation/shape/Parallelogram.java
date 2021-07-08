package formation.shape;

import java.util.ArrayList;

import graph.Node;
import utilities.ListUtilities;

//Three types of parallelograms we are interested in:	
//	
// Type1: two lines of two        Type2: two lines of 3	     Type3: three lines of 3
//	    N N                             N N N                     N N N
//	     N N                             N N N                     N N N 
//	                                                                N N N 

public class Parallelogram extends GeometricShape implements Comparable <Parallelogram> {

	private ArrayList<Line> _lines;

	public Parallelogram(ArrayList<Line> parallelogram) {
		_lines = parallelogram;

	}

	public Parallelogram() {
		this(new ArrayList<Line>());
	}

	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof Parallelogram)) {
			return false;
		}
		Parallelogram that = (Parallelogram) o;
		return ParallelogramEquals(that);
	}

	public boolean ParallelogramEquals(Parallelogram that) {
		//These are not true lines. Using the type line allows us to make a list of sorted nodes
		Line ListOfThisNodes = new Line();
		Line ListOfThatNodes = new Line();

		//Adds nodes to 'line' from all lines in the first parallelogram.
		for (int i = 0; i < this.size(); i++) {
			for (int j = 0; j < this.get(i).size(); j++) {
				ListOfThisNodes.addToLine(this.get(i).get(j));
			}
		}

		//Adds nodes to 'line' from all lines in the second parallelogram.
		for (int i = 0; i < that.size(); i++) {
			for (int j = 0; j < that.get(i).size(); j++) {
				ListOfThatNodes.addToLine(that.get(i).get(j));
			}
		}

		if (ListOfThisNodes.size() != ListOfThatNodes.size()) {
			return false;
		}

		for (int i = 0; i < ListOfThisNodes.size(); i++) {
			if (ListOfThisNodes.get(i) != ListOfThatNodes.get(i)) return false;
		}

		return true;
	}

	public ArrayList<Line> addToParallelogram (Line line) {
		//loops through all nodes in the line to compare to new node
		int end = this._lines.size();
		boolean isAdded = false;
		for (int i = 0; i < end; i++) {
			// this if statement orders the line from least to greatest
			if (line.compareTo(this._lines.get(i)) == -1 && isAdded ==false) {
				this._lines.add(i, line);
				isAdded = true;
			}
		}
		//if the node is the largest node, add it to the end of the line
		if (isAdded == false) this._lines.add(line);

		//return modified line
		return this._lines;
	}


	// CTA: This is garbage....we changed this out already...a different class.
	// Why is it not calling the ListUtility?
	public ArrayList<Parallelogram> addToList (ArrayList<Parallelogram> parallelograms)
	{
		//loops through every parallelogram in an array list of parallelograms 
		//and adds a new parallelogram in such a way that the list is sorted
		int end = parallelograms.size();
		boolean isAdded = false;
		for (int i = 0; i < end; i++) {

			//the if statement orders the parallelograms from least to greatest by our definition of least to greatest (See Line.java)
			if (isAdded == false && this.compareTo(parallelograms.get(i))== -1) {
				parallelograms.add(i, this);
				isAdded = true;
			}
		}

		//Adds the parallelogram onto the end of the list if it is greater than every other parallelogram
		if (isAdded == false) parallelograms.add(this);

		return parallelograms;
	}

	@Override
	//compares two parallelograms by comparing the lines at each index until one is greater than the other.
	public int compareTo(Parallelogram that)
	{
		return ListUtilities.ListCompareTo(_lines, that._lines);
	}

	//gets Line at given index in parallelogram
	public Line get(int index) {
		return _lines.get(index);
	}


	//returns size of parallelogram
	private int size() {
		return _lines.size();
	}

	//returns if parallelogram contains line
	public boolean contains(Line line) {
		return _lines.contains(line);
	}
	
	@Override
	public ArrayList<Node> decompose()
	{
		ArrayList<Node> decomposed = new ArrayList<Node>();
		//For every line, decompose the Line then return the nodes
		for(Line line : _lines) {
			decomposed.addAll(line.decompose());
		}
		return decomposed;
	}

	public String toString()
	{
		return ListUtilities.toStringCommaSeparated(_lines);
	}
}
