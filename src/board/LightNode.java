package board;

import subgraph.BFSMarkingColor.MarkingColor;

public final class LightNode implements Comparable<LightNode>
{
	public char _col;
	public int _row;
	public MarkingColor _BFScolor;

	public LightNode(char col, int row)
	{
		_col = col;
		_row = row;
		_BFScolor = MarkingColor.MARKINGWHITE;
	}

	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof LightNode)) return false;

		LightNode that = (LightNode) o;
		return this._col == that._col && 
			   this._row == that._row;
	}

	//		Three cases for compareTo function to operate
	//		Very similar to the actual node class. 
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

	@Override
	public int compareTo(LightNode that)
    {
		// If the light nodes are equal return that they are equal
		if (this.equals(that))return 0;

		//If the light nodes aren't equal but the columns are, we are looking at the Right to Left Diagonal case
		if (this._col == that._col) {
			//the light node with the larger row in this case will be considered greater
			if (this._row > that._row) return 1;
			return -1;
		}

		// If the light nodes aren't equal but the rows are, we are looking at the Horizontal case.
		if (this._row == that._row) {

			//In this case the light node with the larger column is considered greater
			if ((int) this._col > (int) that._col) return 1;
			return -1;
		}
		//By default the last case is Left to Right Diagonal. In this case one node has a larger row and column than the other
		//Therefore we only need to compare the rows to figure out which node is greater
		if (this._row > that._row) return 1;
		return -1;
	}

	public String toString()
	{
		return "(" + _col + ", "+ _row + ")";
	}
}
