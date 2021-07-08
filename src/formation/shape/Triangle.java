package formation.shape;

import java.util.ArrayList;

import graph.Node;


//   N           Curently the only one programed                 
//  N N   
//
//Small triangle

public class Triangle extends GeometricShape implements Comparable <Triangle> {

	private ArrayList<Node> _points;

	public Triangle(ArrayList<Node> triangle) {
		_points = triangle;
	}

	public Triangle() {
		this(new ArrayList<Node>());
	}

	public ArrayList<Node> addToTriangle(Node node) {
		//loops through all nodes in the line to compare to new node
		int end = this._points.size();
		boolean isAdded = false;
		for (int i = 0; i < end; i++) {
			// this if statement orders the line from least to greatest
			if (node.compareTo(this._points.get(i)) == -1 && isAdded ==false) {
				this._points.add(i, node);
				isAdded = true;
			}
		}
		//if the node is the largest node, add it to the end of the line
		if (isAdded == false) this._points.add(node);

		//return modified line
		return this._points;
	}

	public ArrayList<Triangle> addToList(ArrayList<Triangle> triangles){
		//loops through every line in an array list of lines and adds a new line in such a way that the list is sorted
		int end = triangles.size();
		boolean isAdded = false;
		for (int i = 0; i < end; i++) {

			//the if statement orders the lines from least to greatest by our definition of least to greatest (See Line.java)
			if (isAdded == false && this.compareTo(triangles.get(i))== -1) {
				triangles.add(i, this);
				isAdded = true;
			}
		}

		//Adds the line onto the end if it is greater than every other line
		if (isAdded == false) triangles.add(this);

		//returns the new array list of lines
		return triangles;

	}
	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof Triangle)) {
			return false;
		}
		Triangle that = (Triangle) o;
		return lineEquals(that);
	}
	//method for equality
	public boolean lineEquals(Triangle that) { //Rewrite to be index based

		if (this._points.size() != that._points.size()) return false;
		for (int i = 0; i < this.size(); i++) {
			if (this._points.get(i) != that._points.get(i)) {
				return false;
			}
		}
		return true;
	}


	private Node get(int index) {
		return _points.get(index);
	}

	private int size() {
		return _points.size();
	}

	@Override
	public int compareTo(Triangle that) {
		//initializes an index to be zero. 
		//This will only matter if the first node of both lines is the same
		int index = 0;

		//loops through the index until it either returns a value or gets through the whole line.
		while (index < that.size()) {

			// adds onto the index if the current node in both lines are the same
			if (this.get(index).compareTo(that.get(index)) == 0) index++;


			// this code makes comparisons when the two nodes are not the same
			else {
				//if the rows are the same then we check the columns
				if (this.get(index)._row == that.get(index)._row) {

					//if the column of the first node is greater, we say the line is greater
					if ((int) this.get(index)._col > (int) that.get(index)._col) {
						return 1;
					}
					//Since we've already checked for equality, if the column of the first node is less, then the line is less
					return -1;
				}

				//if the row of the current node is greater, we say that that line is greater
				if (this.get(index)._row > that.get(index)._row) return 1;

				//if the row of the current node is less, we say that the line is less
				if (this.get(index)._row < that.get(index)._row) return -1;


			}
		}
		//Will only return if every node is equal
		return 0;
	}
	
	@Override
	public ArrayList<Node> decompose() {
		return _points;
	}
	
	public String toString() {

		String triString = "";
		for (int i = 0; i < this.size(); i++) {
			triString += this.get(i).toString() + ", ";
		}
		
		triString = triString.substring(0, triString.length() - 2);
		return triString;
	}

	
}
