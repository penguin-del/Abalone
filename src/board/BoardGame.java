package board;

import board.Marble.MarbleColor;
import graph.AbaloneGraph;
import graph.Node;

public class BoardGame
{
	public BoardGame() { }

	//	public AbaloneGraph getBoard()
	//    {
	//		return _graph;
	//	}

	//	//is node empty or invalid
	//	public boolean isEmptyorInvalid(Node node)
	//    {
	//		if (_graph.getValue(node) == MarbleColor.BLACK) ||
	//            _graph.getValue(node) == MarbleColor.WHITE) return false;
	//
	//		return true;
	//	}

	// Is this position empty?
	public boolean isEmpty(Node node, Layer layer)
	{
		return layer.contains(node._col, node._row) == MarbleColor.EMPTY;
	}
	
	// Is this position not part of the Board graph?
	public boolean isInvalid(Node node)
	{
		return AbaloneGraph.get().hasVertex(node);
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
}
