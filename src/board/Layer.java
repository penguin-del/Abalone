package board;
import java.util.ArrayList;
import java.util.BitSet;

import board.Marble.MarbleColor;
import graph.AbaloneGraph;

/**
 * We encode a layer as a set of bits.
 * 
 * Each pair of bits represents:
 *    a position on the board.
 *    the value of the marble at that position
 *    
 *  Marble Positions:
 * 
 * 	//   A1 B1 C1 D1 E1                B B B B B 
	//  A2 B2 C2 D2 E2 F2    ----->   B B B B B B 
	// A3 B3 C3 D3 E3 F3 G3          E E B B B E E
	//         .
	//         .       This picture displays the default board setting
	//         .
	// C7 D7 E7 F7 G7 H7 I7          E E W W W E E
	//   D8 E8 F8 G8 H8 I8   ----->   W W W W W W
	//     E9 F9 G9 H9 I9              W W W W W
 * 
 * Encoding positions in BitSet:
 *   [A1, B1, C1, D1, E1, | A2, B2, C2, ..., I8 | E9, F9, G9, H9, I9]
 *   
 * How to map a (char, int) to bit position:
 *   (char _col, int _row) -> [ROW_INDICES[row - 1] * 2 + (col - COL_OFFSET[row - 1]) * 2,
 *                             ROW_INDICES[row - 1] * 2 + (col - COL_OFFSET[row - 1]) * 2 + 1]
 *
 *  Examples:
 *    (A, 1) : 0 * 2 + ('A' - 'A') * 2 => [0, 1]
 *    (E, 1) : 0 * 2 + ('E' - 'A') * 2 = 0 + 8 => [8, 9]
 *
 *    (A, 2) : 5 * 2 + ('A' - 'A') * 2 = 10 => [10, 11]
 *    (E, 2) : 5 * 2 + ('E' - 'A') * 2 = 10 + 8 => [18, 19]
 *
 *    (I, 9) : 56 * 2 + ('I' - 'E') * 2 = 112 + 8 => [120, 121]
 *  
 * Encoding of marbles at positions:
 *   Each position uses 2 bits:
 *     00 - EMPTY
 *     10 - WHITE
 *     01 - BLACK
 *     11 - unused
 *  
 * @author calvin
 *
 */
public class Layer implements Cloneable
{
	public final static int _NUM_BOARD_POSITIONS = 61; // AbaloneGraph.get().size();
	protected BitSet _board;
	protected boolean _hasBeenChanged;
	protected ArrayList<LightNode> _whiteNodes;
	protected ArrayList<LightNode> _blackNodes;
	
	// The 1-bit-based starting position of each row.
	private final int[] ROW_INDICES = new int[]{0, 5, 11, 18, 26, 35, 43, 50, 56, 61};

	// The beginning character of each row.
	private final char[] COL_OFFSET = new char[]{'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E'};

	public Layer()
	{
		_board = new BitSet(_NUM_BOARD_POSITIONS * 2);
		_hasBeenChanged = true;
	}

	protected Layer(BitSet copy)
	{
		_board = copy;
	}

	private int indexOf(char col, int row)
	{
		return (ROW_INDICES[row - 1] + col - COL_OFFSET[row - 1]) * 2;
	}

	private Marble.MarbleColor contains(int index)
	{
		//     00 - EMPTY
		//     10 - WHITE
		//     01 - BLACK
		//     11 - unused
		if (_board.get(index) && !_board.get(index + 1)) return MarbleColor.WHITE;
		else if (!_board.get(index) && _board.get(index + 1)) return MarbleColor.BLACK;
		else if (!_board.get(index) && !_board.get(index + 1)) return MarbleColor.EMPTY;
		else if (_board.get(index) && _board.get(index + 1))
		{
			System.err.println("Invalid bits in contains.");
			return MarbleColor.INVALID;
		}
		else
		{
			System.err.println("Impossible case in contains.");
		}

		return MarbleColor.INVALID;
	}

	public void add(char c, int i, MarbleColor color)
	{
		if (!isValid(c, i)) return;

		switch(color)
		{
			case BLACK:
				addBlack(c, i);
				break;

			case WHITE:
				addWhite(c, i);
				break;

			case EMPTY:
			case INVALID:
				System.err.println("Attempting to add an empty or invalid color Layer::add"
						+ c + " " + i);
		}
	}

	/**
	 * Return the positions where there are marbles of the given color.
	 * 
	 * @param color -- BLACK, WHITE, (perhaps EMPTY)
	 * @return a list of LightNodes containing the positions of the desired color marbles
	 */
	public ArrayList<LightNode> getNodes(MarbleColor color)
	{
		if (_hasBeenChanged == false) {
			if (color == MarbleColor.WHITE) return _whiteNodes;
			if (color == MarbleColor.BLACK) return _blackNodes;
		}
		ArrayList<LightNode> nodes = new ArrayList<LightNode>();
		
        //
		// Look at all board positions as loop terminator
		//
		int pos = 0;
		for (int row = 1; pos < _NUM_BOARD_POSITIONS; row++)
		{
			// Columns
			for (int c = 1; c <= ROW_INDICES[row] - ROW_INDICES[row - 1]; c++)
			{
				// Mapping position to the (char, int) positional information. 
				char co = (char)(COL_OFFSET[row - 1] + c - 1);
				int ro = row;

				// Check the bit-based position 
				if (contains(pos * 2) == color) nodes.add(new LightNode(co, ro));

				pos++; // Advance to next board positions
			}
		}
		 if (color == MarbleColor.WHITE) _whiteNodes = nodes;
		 if (color == MarbleColor.BLACK) _blackNodes = nodes;
		_hasBeenChanged = false;
		return nodes;
	}
	
	//This function should only take in the colors white and black.
	//It should not be used for empty or invalid nodes.
	public int numMarblesLeft (MarbleColor color) {
		
		ArrayList<LightNode> marbleList = getNodes(color);
		return marbleList.size();
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
		if (!isValid(c, i)) return MarbleColor.INVALID;

		return contains(indexOf(c, i));
	}

	public boolean isValid(char c, int i)
	{
		return AbaloneGraph.get().hasVertex(c,i);
	}

	//
	// WHITE is 10 
	//
	public boolean isWhite(char col, int row)
	{
		int index = indexOf(col, row);
		return _board.get(index) && !_board.get(index + 1);
	}

	public void addWhite(char col, int row)
	{
		int index = indexOf(col, row);

		// 10
		_board.set(index);
		_board.clear(index + 1);
		_hasBeenChanged = true;
	}

	// BLACK is 01
	public boolean isBlack(char col, int row)
	{
		int index = indexOf(col, row);
		return !_board.get(index) && _board.get(index + 1);
	}

	public void addBlack(char col, int row)
	{
		int index = indexOf(col, row);

		// 01
		_board.clear(index);
		_board.set(index + 1);
		_hasBeenChanged = true;
	}

	// EMPTY is 00
	public boolean isEmpty(char col, int row)
	{
		int index = indexOf(col, row);
		return !_board.get(index) && !_board.get(index + 1);
	}

	public void makeEmpty(char col, int row)
	{
		int index = indexOf(col, row);

		// 00
		_board.clear(index);
		_board.clear(index + 1);
		_hasBeenChanged = true;
	}

	public MarbleColor remove(char col, int row)
	{
		MarbleColor color = contains(col , row);
		makeEmpty(col, row);	
		return color;
	}

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

	@Override
	public Layer clone() throws CloneNotSupportedException
	{
		return new Layer((BitSet)this._board.clone());
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

		//   A1 B1 C1 D1 E1                B B B B B 
		//  A2 B2 C2 D2 E2 F2    ----->   B B B B B B 
		// A3 B3 C3 D3 E3 F3 G3          E E B B B E E
		// First 2 rows for Black
		// A1 through E1
		// A2 through E2
		for (int i = 1 ; i < 3; i++)
		{
			for (char c = 'A'; c < 'F'; c++)
			{
				layer.addBlack(c, i);
			}
		}

		// add node 'F2' that is not be accessed via for loop.
		layer.addBlack('F', 2);

		// Row 3 and adds top three marbles
		// C3 through F3
		for (char c = 'C'; c < 'F'; c++)
		{
			layer.addBlack(c , 3);
		}

		// C7 D7 E7 F7 G7 H7 I7          E E W W W E E
		//   D8 E8 F8 G8 H8 I8   ----->   W W W W W W
		//     E9 F9 G9 H9 I9              W W W W W		
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

	public String toString()
	{
		System.out.println(_board);
		String board = "";

		int pos = 0; // Track total board positions
		for (int row = 1; row <= ROW_INDICES.length-1; row++)
		{
			if (row == 1 || row == 9) board += "    ";
			else if (row == 2 || row == 8) board += "   ";
			else if (row == 3 || row == 7) board += "  ";
			else if (row == 4 || row == 6) board += " ";

			// Columns
			for (int c = 0; c < ROW_INDICES[row] - ROW_INDICES[row - 1]; c++)
			{
				switch (contains(pos * 2)) // 2 bits per board position
				{
					case BLACK:
						board += "B";
						break;
					case WHITE:
						board += "W";
						break;
					case EMPTY:
						board += "E";
						break;
					case INVALID:
						System.err.println("Unexpected INVALID in toString; should not be possible");
						break;
					default:
						System.err.println("This should not be possible");
				}

				board += " ";
				pos++; // Advance to next board positions
			}

			board += "\n";
		}
		return board;
	}
}