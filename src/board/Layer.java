package board;

import java.util.ArrayList;

import board.Marble.MarbleColor;
import graph.AbaloneGraph;
import subgraph.BFSMarkingColor.MarkingColor;

public class Layer implements Cloneable
{
	protected ArrayList<LightNode> _white;
	protected ArrayList<LightNode> _black;

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
		public int compareTo(LightNode that) {
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

	public Layer()
	{
		_white = new ArrayList<LightNode>();
		_black = new ArrayList<LightNode>();
	}

	// Overriding clone() method
	// by simply calling Object class
	// clone() method.
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}

	//	public Layer(Layer that)
	//	{
	//		this();
	//		
	//		_white.addAll(that._white);
	//		_black.addAll(that._black);
	//	}

	public void addBlack(char c, int i) { _black.add(new LightNode(c,i)); }
	public void addWhite(char c, int i) { _white.add(new LightNode(c,i)); }

	public void add(char c, int i, MarbleColor color)
	{
		switch(color)
		{
		case BLACK:
			if (isValid(c,i)) addBlack(c, i);
			return;

		case WHITE:
			if (isValid(c,i)) addWhite(c, i);
			return;

		case EMPTY:
		case INVALID: //Ask Alvin!
			System.err.println("Attempting to add an empty or invalid color Layer::add"+c+" "+i);
		}
	}

	public boolean removeWhite(char c, int i)
	{ 
		return _white.remove(new LightNode(c,i));
	}

	public boolean removeBlack(char c, int i)
	{ 
		return _black.remove(new LightNode(c,i));
	}

	public MarbleColor remove(char c, int i)
	{
		if (removeWhite(c, i)) return MarbleColor.WHITE;

		if (removeBlack(c, i)) return MarbleColor.BLACK;

		return MarbleColor.EMPTY;
	}

	/**
	 * Main lookup routine to identify what color mArble the given position
	 * has in this layer.
	 * This is an O(n)
	 * @param c -- row character
	 * @param i -- column integer
	 * @param color -- Color to check SAME of
	 * @return FALSE if the given color or node in the Layer is EMPTY;
	 *         otherwise check for SAME colors.
	 */
	public Marble.MarbleColor contains(char c, int i)
	{
		if(!isValid(c,i)) return MarbleColor.INVALID;

		if (containsWhite(c, i)) return MarbleColor.WHITE;

		if (containsBlack(c, i)) return MarbleColor.BLACK;		

		return MarbleColor.EMPTY;
	}

	public boolean isEmpty(char c, int i)
	{
		if (containsWhite(c, i)) return false;

		if (containsBlack(c, i)) return false;

		return true;
	}

	public boolean isValid(char c, int i) {
		return (AbaloneGraph.get().hasVertex(c,i));
	}

	public boolean containsWhite(char c, int i)
	{
		return _white.contains(new LightNode(c, i));
	}

	public boolean containsBlack(char c, int i)
	{
		return _black.contains(new LightNode(c, i));
	}

	public ArrayList<LightNode> getNodes(MarbleColor color)
	{
		switch(color)
		{
		case WHITE: return _white;
		case BLACK: return _black;
		default:
			System.err.println("Unexpected color in Layer::getNodes");
		}

		// Return an empty by default
		return new ArrayList<LightNode>();
	}

	/**
	 * Does the given position on the Layer contain the SAME
	 * color of the given Marble?
	 * @param c -- row character
	 * @param i -- column integer
	 * @param color -- Color to check SAME of
	 * @return FALSE if the given color or node in the Layer is EMPTY;
	 *         otherwise check for SAME colors.
	 */
	public boolean containsSameColor(char c, int i, MarbleColor color)
	{
		if (color == MarbleColor.EMPTY) return false;

		return color == contains(c, i);
	}

	/**
	 * Does the given position on the Layer contain the opposite
	 * color of the given Marble?
	 * @param c -- row character
	 * @param i -- column integer
	 * @param color -- COlor to check OPPOSITE of
	 * @return FALSE if the given color or node in the Layer is EMPTY;
	 *         otherwise check for opposing colors.
	 */
	public boolean containsOppositeColor(char c, int i, MarbleColor color)
	{
		if (color == MarbleColor.EMPTY) return false;

		MarbleColor colorOnLayer = contains(c, i);

		if (colorOnLayer == MarbleColor.EMPTY) return false;

		return color != colorOnLayer;
	}

	// Must contain WHITE OR BLACK to possibly return true;
	// if one of the nodes is EMPTY, will return false
	public boolean sameColor(char c1, int i1, char c2, int i2)
	{		
		MarbleColor colorOnLayer1 = contains(c1, i1);

		if (colorOnLayer1 == MarbleColor.EMPTY) return false;

		MarbleColor colorOnLayer2 = contains(c2, i2);

		if (colorOnLayer2 == MarbleColor.EMPTY) return false;

		return colorOnLayer1 == colorOnLayer2;
	}

	// Must contain WHITE OR BLACK to possibly return true;
	// if one of the nodes is EMPTY, will return false
	public boolean oppositeColor(char c1, int i1, char c2, int i2)
	{
		return !sameColor(c1, i1, c2, i2);
	}

	//
	// Wrapping up the exception checking into this method to avoid usage exception pollution
	//
	public Layer getClone()
	{
		Layer newLayer = null;

		try { newLayer = (Layer)this.clone(); }
		catch(CloneNotSupportedException cne)
		{
			System.err.println("Cloning a Layer failed: " + this);
			System.exit(1);
		}
		return newLayer;
	}

	//     B B B B B
	//    B B B B B B
	//   E E B B B E E
	//  E E E E E E E E
	// E E E E E E E E E
	//  E E E E E E E E
	//   E E W W W E E
	//    W W W W W W
	//     W W W W W
	public static Layer getDefaultBoard()
	{
		Layer layer = new Layer();

		// First 2 rows for Black
		for (int i = 1 ; i < 3; i++)
		{
			for (char c = 'A'; c < 'F'; c++)
			{
				layer.addBlack(c, i);
			}
		}

		//adds node 'F2' that can't be accessed via for loop. (Condense lines)
		layer.addBlack('F', 2);

		//loops through just row 3 and adds top three marbles
		for (char c = 'C'; c < 'F'; c++)
		{
			layer.addBlack(c , 3);
		}

		//loops through just row 7 and adds 3 marbles
		for(char c = 'E'; c < 'H'; c++)
		{
			layer.addWhite(c,7);
		}

		//adds 'D8' that can't be accessed via for loop
		layer.addWhite('D', 8);

		//loops through bottom 2 rows
		for (int i = 8 ; i < 10 ; i++)
		{
			//loops through columns and adds nodes at those positions
			for(char c = 'E'; c < 'J'; c++)
			{
				layer.addWhite(c, i);
			}
		}

		return layer;
	}

	public String toString(Layer layer)
	{
		String board = "";

		final int FIRST_ROW = 1;
		final int LAST_ROW = 9;
		final char FIRST_COLUMN = 'A';
		final char LAST_COLUMN = 'I';
		for (int r = FIRST_ROW; r <= LAST_ROW; r++)
		{ 
			String spaceString = "";
			if (r == 1 || r == 9) spaceString += "    ";
			if (r == 2 || r == 8) spaceString += "   ";
			if (r == 3 || r == 7) spaceString += "  ";
			if (r == 4 || r == 6) spaceString += " ";

			board += spaceString;

			for (char c = FIRST_COLUMN ; c <= LAST_COLUMN; c++)
			{
				// If the node does not exist on the board, disregard it,
				// otherwise accumulate Marbles / colors.
				if (AbaloneGraph.get().hasVertex(c, r))
				{
					switch (layer.contains(c,  r))
					{
					case BLACK:
						board += "B ";
						break;
					case WHITE:
						board += "W" +" ";
						break;
					case EMPTY:
						board += "E" +" ";
						break;
					case INVALID:
						System.err.println("Unexpected INVALID; should not be possible");
						break;
					default:
						System.err.println("This should not be possible");
					}
				}
			}
			board += spaceString;
			board += "\n";
		}
		return board;
	}
	//	public static void main(String[] args)
	//    {	
	//		//Dumps both arraylist of white and arraylist of black marbles into the console.
	//		Layer overlay = Layer.getDefaultBoard();
	//		System.out.println(overlay._black);
	//		System.out.println(overlay._white);
	//	}
}
