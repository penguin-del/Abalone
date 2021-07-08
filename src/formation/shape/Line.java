package formation.shape;

import java.util.ArrayList;
import java.util.Iterator;

import graph.Node;

public class Line extends Formation implements Iterable<Node>, Comparable <Line>
{
    protected ArrayList<Node> _nodes;

	@Override
	public Iterator<Node> iterator() {return _nodes.iterator();}
	
	//	Three cases for direction
	//	  \
	//	   \   Left to Right Diagonal            On the graph:
	//	    \                                     \      /
	//	                                           \    /
	//	  ---------- Horizontal                     \  /
	//	                                     -------------------  
	//	     /                                      /  \
	//	    /  Right to Left Diagonal              /    \ 
	//	   /                                      /      \
	//	
	public enum Direction{

		UNKNOWN(0), LEFTTORIGHTDIAG(1), HORIZONTAL(2),RIGHTTOLEFTDIAG(3);

		private Direction(int value) {

		}
	}
	
	public enum HexagonCardinalDirections{
		
		NORTHEAST(0), EAST(1), SOUTHEAST(2), SOUTHWEST(3), WEST(4), NORTHWEST(5);

		
		HexagonCardinalDirections(int n){
		}
		
	}

	private Direction _direction;


	public Line (ArrayList<Node> line) {

		_nodes = line;
	}

	public Line () {
		this(new ArrayList<Node>());
	}


	public ArrayList<Node> addToLine (Node node) { 
		//loops through all nodes in the line to compare to new node
		int end = this._nodes.size();
		boolean isAdded = false;
		for (int i = 0; i < end; i++) {
			// this if statement orders the line from least to greatest
			if (node.compareTo(this._nodes.get(i)) == -1 && isAdded ==false) {
				this._nodes.add(i, node);
				isAdded = true;
			}
		}
		//if the node is the largest node, add it to the end of the line
		if (isAdded == false) {
			this._nodes.add(node);
		}
		//Gets the direction of the new line if the line now has 2 pieces.
		//Since two pieces is the base case, all other lines will carry directional info as they are built
		if (this.size() == 2) {
			this.determineDirection();
		}

		//return modified line
		return this._nodes;
	}

	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof Line)) {
			return false;
		}
		Line that = (Line) o;
		return lineEquals(that);
	}
	//method for equality
	public boolean lineEquals(Line that) { //Rewrite to be index based

		if (this._nodes.size() != that._nodes.size()) return false;
		for (int i = 0; i < this.size(); i++) {
			if (!(this._nodes.get(i).equals(that._nodes.get(i)))) {
				return false;
			}
		}
		return true;
	}
	
	//line must be of length 2 or more
	//	Three cases for compareTo function to operate
	//	  \
	//	   \   Left to Right Diagonal            On the graph:
	//	    \                                     \      /
	//	                                           \    /
	//	  ---------- Horizontal                     \  /
	//	                                     -------------------  
	//	     /                                      /  \
	//	    /  Right to Left Diagonal              /    \ 
	//	   /                                      /      \
	//	
	// Given our naming convention, each case has a different pattern to follow.
	public void determineDirection() {
		Node compareNode1 = this._nodes.get(0);
		Node compareNode2 = this._nodes.get(1);

		//If the columns are the same in every node, then the direction is Right to Left Diagonal
		if ((int)compareNode1._col == (int)compareNode2._col ) this._direction = Direction.RIGHTTOLEFTDIAG;


		//If the rows are the same in every node, then the direction is Horizontal
		else if (compareNode1._row == compareNode2._row) this._direction = Direction.HORIZONTAL;

		// The only remaining case would be one node having a bigger row and column by a factor of 1 than the other. 
		// the direction is Left to right Diagonal in this case.
		else this._direction = Direction.LEFTTORIGHTDIAG;
	}

	public Direction getDirection () {

		return this._direction;

	}

	public Node get (int i) {
		return _nodes.get(i);
	}

	public int size () {
		return _nodes.size();
	}

	public String toString() {
		String LineString = "";
		for (int i =0; i < this.size(); i++) {
			LineString += _nodes.get(i).toString() + ", ";
		}

		LineString = LineString.substring(0, LineString.length()-2);
		LineString += "/";

		return LineString;
	}

	@Override
	//Should only be used to compare two lines of the same size
	// We are comparing lines to sort them in the array list of lines
	//
	//We want to order by row first and then by column.
	//
	//So if we had two lines: [(A, 1),(B, 1)] and [(A, 1), (A, 2)] then,
	// [(A, 1),(B, 1)] would come before  [(A, 1), (A, 2)]
	//
	public int compareTo(Line that) {

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
					if ((int) this.get(index)._col > (int) that.get(index)._col) return 1;

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

	public Node getLowerEndpoint() { return this.get(0); }

	public Node getUpperEndpoint() { return this.get(this.size() - 1); }

	public boolean isParallel(Line that) { return this.getDirection() == that.getDirection(); }

	public boolean contains(Node node) { return _nodes.contains(node); }
	
	@Override
	public ArrayList<Node> decompose() { return _nodes; }
	
	public Line copyLine() {
		Line copyline = new Line();
		for (int i = 0 ; i < this.size(); i++) {
			copyline.addToLine(this.get(i));
		}
		
		return copyline;
	}
}
