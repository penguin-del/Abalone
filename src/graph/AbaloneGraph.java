package graph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import board.Marble;
import board.Marble.MarbleColor;
import globals.Constants;
import utilities.SeparateChainingHashST;

//
// Singleton class reflecting / enforcing the fact that there is one board
// and MANY layers.
//
public class AbaloneGraph
{
	//An Abalone Graph is an undirected graph of nodes.
	private SeparateChainingHashST <Node, Marble.MarbleColor> _vertices;
	private static AbaloneGraph _instance = null;

	// Initialization of static elements
	static
	{
		FileReader fr = null;
		try {
			fr = new FileReader(Constants.STANDARD_BOARD_FILE);
		}
        catch (FileNotFoundException e)
		{
			System.err.println("Standard board configuration file not found: " +
                               Constants.STANDARD_BOARD_FILE);
			System.exit(1);
		}
		
		try {
			_instance = new AbaloneGraph();
			AbaloneGraphReader.parse(_instance, new BufferedReader(fr));
		}
        catch (IOException e)
		{
			System.err.println("Failed to read standard board configuration file:" +
                    Constants.STANDARD_BOARD_FILE);
			System.exit(1);
		}
	}

	//
	// private constructor enforcing Singleton
	//
	private AbaloneGraph()
	{
		_vertices = new SeparateChainingHashST<Node, Marble.MarbleColor>();
	}
	
	// Singleton get method
	public static AbaloneGraph get() { return _instance; }	
	
	//Adding an undirected edge between two nodes
	public void addEdge(Node to, Node from)
	{
		to.addNeighbor(from);
		from.addNeighbor(to);
	}

//	public SeparateChainingHashST <Node, MarbleColor> getVertices()
//	{
//		return _vertices;
//	}

	//Provides lookup method in the hash map.
	public Node getVertex(char col, int row)
    {
		return _vertices.getKey(new Node(col, row));
	}

	public Node getVertex(Node node)
    {
		return getVertex(node._col,node._row);
	}

	//We can make another version that would take a node and marble color.
	public boolean addNode(Node node)
	{
		//if Hash map doesn't contain, then add
		if (hasVertex(node._col, node._row)) return false;

		//assigns node to hash map with key and empty value
		_vertices.put(node, MarbleColor.EMPTY);

		return true;
	}
	
	//
	// Neighbor acquisition for a given node
	//
	public ArrayList<Node> neighbors(char c, int i)
	{
		Node node = getVertex(c, i);
		if (node == null)
		{
			System.err.println("Neighbors requested for non-existent node: (" + c + ", " + i + ")");
		}
		
		return node.getNeighbors();
	}

//	public int numVerticies()
//    {
//		//returns size of Hash map
//		return _vertices.size();
//	}

	//returns true if vertex exists
	public boolean hasVertex(char column, int row)
	{
		return hasVertex(new Node(column, row));
	}
	
	public boolean hasVertex(Node node)
	{
		return _vertices.contains(node);
	}

//	// returns value(marble color) of node
//	public MarbleColor getValue(Node node)
//    {
//		return _vertices.getValue(node);
//	}
//	
//	public void changeValue(Node key, MarbleColor val)
//    {
//		_vertices.put(key, val);
//	}



	//   A1 B1 C1 D1 E1                B B B B B 
	//  A2 B2 C2 D2 E2 F2    ----->   B B B B B B 
	// A3 B3 C3 D3 E3 F3 G3          E E B B B E E
	//         .
	//         .                                      This picture displays the default board setting
	//         .
	// C7 D7 E7 F7 G7 H7 I7          E E W W W E E
	//   D8 E8 F8 G8 H8 I8   ----->   W W W W W W
	//     E9 F9 G9 H9 I9              W W W W W
	//
	// B- Black, W-White, E-Empty
	//
	//
	//
	public void setDefaultBoard()
    {
		//adds node 'F2' that can't be accessed via for loop. (Condense lines)
		Node nodeF2= new Node('F', 2);
		_vertices.put(nodeF2, MarbleColor.BLACK);

		//loops through first 2 rows
		for (int i = 1 ; i < 3; i++) {

			//loops through rows and puts node at that coordinate
			for (char c = 'A'; c < 'F'; c++) {
				Node node= new Node(c,i);
				_vertices.put(node, MarbleColor.BLACK);

			}
		}
		//loops through just row 3 and adds top three marbles
		for (char c = 'C'; c < 'F'; c++) {
			Node node = new Node(c,3);
			_vertices.put(node, MarbleColor.BLACK);

		}

		//adds 'D8' that can't be accessed via for loop
		Node nodeD= new Node('D', 8);
		_vertices.put(nodeD, MarbleColor.WHITE);


		//loops through bottom 2 rows
		for (int i = 8 ; i < 10 ; i++) {

			//loops through columns and adds nodes at those positions
			for(char c = 'E'; c < 'J'; c++) {
				Node node= new Node(c,i);
				_vertices.put(node, MarbleColor.WHITE);

			}
		}
		//loops through just row 7 and adds 3 marbles
		for(char c = 'E'; c < 'H'; c++) {
			Node node = new Node(c,7);
			_vertices.put(node, MarbleColor.WHITE);
		}
	}

	//sets all node values to empty
	public void emptyBoard()
    {
		final int FIRST_ROW = 1;
		final int LAST_ROW = 9;
		final char FIRST_COLUMN = 'A';
		final char LAST_COLUMN = 'I';
		for (int r = FIRST_ROW; r <= LAST_ROW; r++) {
			//Loops through the columns on the board
			for (char c = FIRST_COLUMN ; c <= LAST_COLUMN; c++) {
				//If the node doesn't exist in the hash map, disregard it, otherwise set it to EMPTY.
				Node node= new Node(c,r);
				if (!_vertices.contains(node)) _vertices.put(node, null);
				else _vertices.put(node, MarbleColor.EMPTY);
			}
		}
	}

	public ArrayList<Node> getPopulatedNodes(MarbleColor color){

		//Gets a set of all the nodes in the graph and initializes an empty array list
		Iterable<Node> nodeList= _vertices.keys();
		ArrayList <Node> populatedNodes = new ArrayList<Node>();

		//for all the nodes, checks to see if the node is populated with a piece. 
		//If so then we add that node to the array list
		for (Node space : nodeList) {
			//Node vertex = getVertex(space._col, space._row);
			if (_vertices.getValue(space) == color) {
				populatedNodes.add(space);
			}
		}
		return populatedNodes;
	}

//	//flips color of given Node
//	public MarbleColor flipColor(Node node)
//    {
//		if (getValue(node)==MarbleColor.BLACK) return MarbleColor.WHITE;
//		if(getValue(node)==MarbleColor.WHITE) return MarbleColor.BLACK;
//		return MarbleColor.EMPTY;
//	}

	public String toString()
	{
		Iterable<Node> nodeList= _vertices.keys();
		String nodeString= "";

		for(Node n : nodeList)
        {
			nodeString += n + "\n";
		}
		nodeString= nodeString.substring(0, nodeString.length()-1);

		return nodeString;
	}

}
