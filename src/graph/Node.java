package graph;

import java.util.ArrayList;
import subgraph.BFSMarkingColor.MarkingColor;



public class Node implements Comparable <Node> {
	public int _row;
	public char _col;

	public MarkingColor _color;
	public ArrayList<Node> _neighbors;
	
	public Node(char col, int row)
	{
		_row = row;
		_col = col;
		_neighbors = new ArrayList<Node>();
		_color = MarkingColor.MARKINGWHITE;
	}

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
	public int compareTo (Node that) {

		// If the nodes are equal return that they are equal
		if (this.equals(that))return 0;

		//If the nodes aren't equal but the columns are, we are looking at the Right to Left Diagonal case
		if (this._col == that._col) {
			//the node with the larger row in this case will be considered greater
			if (this._row > that._row) return 1;
			return -1;
		}

		// If the nodes aren't equal but the rows are, we are looking at the Horizontal case.
		if (this._row == that._row) {

			//In this case the node with the larger column is considered greater
			if ((int) this._col > (int) that._col) return 1;
			return -1;
		}
		//By default the last case is Left to Right Diagonal. In this case one node has a larger row and column than the other
		//Therefore we only need to compare the rows to figure out which node is greater
		if (this._row > that._row) return 1;
		return -1;
	}



	public boolean addNeighbor(Node neighbor)
	{
		if (_neighbors.contains(neighbor)) {
			return false;
		}

		_neighbors.add(neighbor);
		return true;
	}

	public ArrayList<Node> getNeighbors(){

		return _neighbors;  
	}

	@Override
	public int hashCode()
	{
		return _row * (int)(_col) * (int)(_col) + _row * (int)(_col) + _row; 
	}

	public String toString()
	{
		String NeighborString= toSimpleString()+ ":";
		for(int i=0; i<_neighbors.size(); i++) {
			NeighborString +=  _neighbors.get(i).toSimpleString();
		}
		return NeighborString;
	}

	public String toSimpleString() {
		return "(" + _col + ", "+ _row + ")";
	}

	// Restrictions: n must be a legitimate node from the graph (with neighbors)
	// May be a bottleneck compared to looking up the Node in the graph
	public Node getNeighbor(Node n)
	{
		for (Node neighbor: this._neighbors)
		{
			if(neighbor.equals(n)) return neighbor;
		}
		return null;
	}
	
	// Restrictions: n must be a legitimate node from the graph (with neighbors)
	// -1 row ; same column
	public Node getNorthEast()
	{		
		 // Create a node with -1 row (same column)
		Node node = new Node(this._col, this._row-1);
			
		// Look up that node in the getNeighbor method to acquire the Vertex
		Node neighbor= getNeighbor(node);
	
		return neighbor;
	}
	
	// Restrictions: n must be a legitimate node from the graph (with neighbors)
	// same row ; +1 column
	public Node getEast()
	{
		 // Create a node with +1 column (same row)
		Node node = new Node((char) (this._col+1), this._row);
		
		Node neighbor= getNeighbor(node);
	
		
		// Look up that node in the getNeighbor method to acquire the Vertex
		
		
		return neighbor;
	}
	
	// Restrictions: n must be a legitimate node from the graph (with neighbors)
	// +1 row ; +1 column
	public Node getSouthEast()
	{
		 // Create a node with +1 row +1 column
		Node node = new Node((char) (this._col+1), this._row+1);
		
		Node neighbor= getNeighbor(node);
	
		
		// Look up that node in the getNeighbor method to acquire the Vertex
		
		
		return neighbor;
	}
	
	// Restrictions: n must be a legitimate node from the graph (with neighbors)
	// +1 row ; same column
	public Node getSouthWest()
	{
		 // Create a node with +1 row (same column)
		Node node = new Node(this._col, this._row+1);
		
		Node neighbor= getNeighbor(node);
	
		
		// Look up that node in the getNeighbor method to acquire the Vertex
		
		
		return neighbor;
	}

	// Restrictions: n must be a legitimate node from the graph (with neighbors)
	// same row ; -1 column
	public Node getWest()
	{
		 // Create a node with (same row) -1 column
		Node node = new Node((char) (this._col-1), this._row);
		
		Node neighbor= getNeighbor(node);
	
		
		// Look up that node in the getNeighbor method to acquire the Vertex
		
		
		return neighbor;
	}
	// Restrictions: n must be a legitimate node from the graph (with neighbors)
	// -1 row ; -1 column
	public Node getNorthWest()
	{
		 // Create a node with -1 row, -1 column
		Node node = new Node((char) (this._col-1), this._row-1);
		
		Node neighbor= getNeighbor(node);
	
		
		// Look up that node in the getNeighbor method to acquire the Vertex
		
		
		return neighbor;
	}



	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof Node)) return false;

		Node that = (Node) o;
		return this._col == that._col && 
			   this._row == that._row;
	}
	
//	public MarkingColor getColor() {
//		return _color;
//	}

}
