package formation.computer;

import java.util.ArrayList;

import board.Layer;
import board.Layer.LightNode;
import board.Marble.MarbleColor;
import formation.shape.Line;
import globals.Constants;
import graph.AbaloneGraph;
import graph.Node;
import utilities.ListUtilities;

//  N N <--- Line of 2 (created in computeBaseCase)
//  N N N <--- Line of 3 (created in computeGeneralCase)
//
//  This algorithm takes in an integer representing the desired length of the lines and a desired color.
//  We compute all lines of size 2 (pictured above) then we use lines of size two to create lines of size 3 (also pictured above).
//  Each line generator for line of length n uses the lines of size n-1 to generate.
//
//
public class LineComputer
{
	protected ArrayList<Line> [] _lengthsBlack;
	protected ArrayList<Line> [] _lengthsWhite;
	protected Layer _layer;

	@SuppressWarnings("unchecked")
	public LineComputer (Layer layer)
	{
		_lengthsBlack = new ArrayList[Constants.MAX_LINE_LENGTH + 1];
		_lengthsWhite = new ArrayList[Constants.MAX_LINE_LENGTH + 1];

		// We have Constants.MAX_LINE_LENGTH + 1 because we are constructing an array where the index
		// coresponds to line length. Since Constants.MAX_LINE_LENGTH  is initialized to 9 and we need an 
		// index for 9, we add 1.
		for (int i =0; i< Constants.MAX_LINE_LENGTH + 1; i++)
		{
			_lengthsBlack[i] = new ArrayList<Line>();
			_lengthsWhite[i] = new ArrayList<Line>();
		}

		_layer = layer;
	}

	// constructs the array of lines for the specified color
	public ArrayList<Line> getLines(int n, MarbleColor color)
	{
		if (color == MarbleColor.BLACK) {
			_lengthsBlack = constructLineList(n, color);
			return _lengthsBlack[n];
		}
		else {
			_lengthsWhite = constructLineList(n, color);
			return _lengthsWhite[n];
		}
	}


	public void addToList (ArrayList<Line> lines, Line line)
	{
		ListUtilities.ListInsertInOrder(lines, line);
	}

	private ArrayList<Line>[] constructLineList (int n, MarbleColor color)
	{
		if (color == MarbleColor.BLACK) {
			//compute black line segments of length 2 
			if(_lengthsBlack[2].isEmpty()) computeBaseCase(_lengthsBlack, color);

			//compute the remaining black cases
			for (int i =3; i <= n; i++) {
				if(_lengthsBlack[i].isEmpty()) computeGeneralCase(_lengthsBlack, i);
			}
			return _lengthsBlack;
		}
		else {
			//compute white line segments of length 2r
			if(_lengthsWhite[2].isEmpty()) computeBaseCase(_lengthsWhite, color);

			//compute the remaining white cases
			for (int i =3; i <= n; i++) {
				if(_lengthsWhite[i].isEmpty()) computeGeneralCase(_lengthsWhite, i);
			}
			return _lengthsWhite;
		}
	}


	//this method creates a sorted list of lines of length 2
	public void computeBaseCase(ArrayList<Line>[] lengths, MarbleColor color)
	{
		//this code should compute all of the line segments of length 2 and order them from least to greatest
		//gets the populated nodes for the present board state.
		ArrayList<LightNode> populated = _layer.getNodes(color);

		//Sets lengths[2] to be a new array list
		lengths[2] = new ArrayList<Line>();

		//loops through all of the marbles on the board
		for (LightNode marble : populated)
		{
			//loops through the list of neighbors for the current marble
			for (Node neighbor : AbaloneGraph.get().neighbors(marble._col, marble._row))
			{
				Line lineOf2 = new Line();

				// if the values of the marble and its neighbor are the same, add both to a sorted line 
				if (_layer.containsSameColor(neighbor._col, neighbor._row, color))
				{
					lineOf2.addToLine(AbaloneGraph.get().getVertex(marble._col, marble._row));
					lineOf2.addToLine(neighbor);

					//if the array doesn't already contain the line then add it
					if (!(lengths[2].contains(lineOf2))) addToList(lengths[2], lineOf2);
				} 
			}
		}
	}

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
	//this method creates a sorted list of lines of length n
	public void computeGeneralCase (ArrayList<Line>[] lengths, int n)
	{
		//Initializes a new array list of lines and assigns it to lengths
		lengths[n] = new ArrayList<Line> ();

		//this code will compute all of the remaining cases above two until n
		for (Line line : lengths[n-1]) {

			switch(line.getDirection())
			{
				case RIGHTTOLEFTDIAG:
					addRightToLeftDiag(line, n, lengths);
					break;
				case HORIZONTAL:
					addHorizontal(line, n, lengths);
					break;
				case LEFTTORIGHTDIAG:
					addLeftToRightDiag(line, n, lengths);
					break;
				case UNKNOWN:
					System.out.print("Unknown value for" + line);
					break;
				default:
					System.out.print("No value assigned for" + line);
					break;

			}

		}
	}

	public void addRightToLeftDiag (Line line, int n, ArrayList<Line>[] lengths)
	{
		//Initializes the end points of the current line
		Node least = AbaloneGraph.get().getVertex(line.getLowerEndpoint());
		Node greatest = AbaloneGraph.get().getVertex(line.getUpperEndpoint());

		//Initializes the vertex values of potential extensions onto the shorter line
		Node nodeC = AbaloneGraph.get().getVertex(line.get(0)._col, line.get(0)._row - 1);
		Node nodeD = AbaloneGraph.get().getVertex (line.get(line.size()-1)._col, line.get(line.size()-1)._row + 1);

		//Initializes neighbor lists for the two end points
		ArrayList<Node> leastNeighbors = least.getNeighbors();
		ArrayList<Node> greatestNeighbors = greatest.getNeighbors();

		//Checks that Node C is inside the neighbor list for lower end point and that the lower end point has the same marble color
		if (leastNeighbors.contains(nodeC) && _layer.contains(nodeC._col, nodeC._row) == _layer.contains(least._col, least._row)){
			appendLine (nodeC, line, lengths, n);
		}
		//Checks that Node D is inside the neighbor list for upper end point and that the upper end point has the same marble color
		else if (greatestNeighbors.contains(nodeD) && _layer.contains(nodeD._col, nodeD._row) == _layer.contains(greatest._col, greatest._row)) {
			appendLine (nodeD, line, lengths, n);
		}
	}

	public void addHorizontal(Line line, int n, ArrayList<Line>[] lengths) {

		// Initializes the end points of the current line
		Node least = AbaloneGraph.get().getVertex(line.getLowerEndpoint());
		Node greatest = AbaloneGraph.get().getVertex(line.getUpperEndpoint());

		//Initializes the vertex values of potential extensions onto the shorter line
		Node nodeC = AbaloneGraph.get().getVertex((char)((int)(least._col -1)), least._row);
		Node nodeD = AbaloneGraph.get().getVertex((char)((int)(greatest._col + 1)), greatest._row);

		//Initializes neighbor lists for the two end points
		ArrayList<Node> leastNeighbors = least.getNeighbors();
		ArrayList<Node> greatestNeighbors = greatest.getNeighbors();

		//Checks that Node C is inside the neighbor list for lower end point and that the lower end point has the same marble color
		if (leastNeighbors.contains(nodeC) && _layer.contains(nodeC._col, nodeC._row) == _layer.contains(least._col, least._row)){
			appendLine (nodeC, line, lengths, n);
		}
		//Checks that Node D is inside the neighbor list for upper end point and that the upper end point has the same marble color
		else if (greatestNeighbors.contains(nodeD) && _layer.contains(nodeD._col, nodeD._row) == _layer.contains(greatest._col, greatest._row)) {
			appendLine (nodeD, line, lengths, n);
		}
	}

	public void addLeftToRightDiag(Line line, int n, ArrayList<Line>[] lengths) {

		//Initializes the end points of the current line
		Node least = AbaloneGraph.get().getVertex(line.getLowerEndpoint());
		Node greatest = AbaloneGraph.get().getVertex(line.getUpperEndpoint());

		//Initializes the vertex values of potential extensions onto the shorter line
		Node nodeC = AbaloneGraph.get().getVertex((char)((int)(least._col -1)), least._row -1);
		Node nodeD = AbaloneGraph.get().getVertex((char)((int)(greatest._col+1)), greatest._row + 1);

		//Initializes neighbor lists for the two end points
		ArrayList<Node> leastNeighbors = least.getNeighbors();
		ArrayList<Node> greatestNeighbors = greatest.getNeighbors();

		//Checks that Node C is inside the neighbor list for lower end point and that the lower end point has the same marble color
		if (leastNeighbors.contains(nodeC) && _layer.contains(nodeC._col, nodeC._row) == _layer.contains(least._col, least._row)){
			appendLine (nodeC, line, lengths, n);
		}
		//Checks that Node D is inside the neighbor list for upper end point and that the upper end point has the same marble color
		else if (greatestNeighbors.contains(nodeD) && _layer.contains(nodeD._col, nodeD._row) == _layer.contains(greatest._col, greatest._row)) {
			appendLine (nodeD, line, lengths, n);
		}
	}

	public void appendLine (Node nodeX, Line line, ArrayList<Line>[] lengths, int n)
	{
		//creates a deep copy of line using a for loop
		Line copyline = line.copyLine();

		//adds a new node to the deep copy
		copyline.addToLine(nodeX);

		//adds the line to the list of lines as long as the line isn't already there
		if(!(lengths[n].contains(copyline))) {
			addToList(lengths[n], copyline);
		}

	}
}
