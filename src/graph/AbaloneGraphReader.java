package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import globals.Constants;
import utilities.Pair;

public class AbaloneGraphReader {

	public static void parse(AbaloneGraph graph, BufferedReader br) throws IOException
	{
		try
		{
			String line;
			while ((line = br.readLine()) != null)
			{
				// Parse a single line of the file
				Pair<Node, ArrayList<Node>> pair = parseLine(line);

				handleLine(graph, pair);
			}
		}
		catch(IOException ioe)
		{
            System.err.println("Unexpected exception in constructing graph.");
		}
		finally { br.close(); }

		if (Constants.DEBUG) System.out.print(graph);
	}
	
	


	// Adding a node and neighbors to the graph (as vertices and edges)
	private static void handleLine(AbaloneGraph graph, Pair<Node, ArrayList<Node>> pair)
	{
		Node node = pair.getFirst();
		ArrayList<Node> neighbors = pair.getSecond();

		//
		// Handling a set of undirected edges
		// (1) Add node to the graph....
		if (!graph.addNode(node))
		{
			//the node exists; grabing copy of the object
			node = graph.getVertex(node._col, node._row);
			if (Constants.DEBUG) 
			{
				System.out.println(node + " already existed in the graph.");
			}
		}

		// (2a) Add each RHS neighbor to the graph
		// (2b) Add each RHS neighbor to the lhs node
		for (Node neighbor : neighbors)
		{
			//the node exists so we grab the copy of the object
			Node neighborNode = neighbor;
			if(graph.hasVertex(neighbor)) {
				neighborNode = graph.getVertex(neighbor._col, neighbor._row);
			}
			else {
				graph.addNode(neighborNode);
			}
			node.addNeighbor(neighborNode);

			neighborNode.addNeighbor(node);

		}
	}


	// "(A 1)" --> Node('A', 1)
	private static Node parseNode(String strNode)
	{

		//strNode.replace(""," ");
		strNode=strNode.substring(strNode.indexOf("(")+1, strNode.indexOf(")")); //Grabs string between parentheses
		String nodewoutspaces = "";
		for (int i = 0; i < strNode.length(); i++) { //removes spaces
			if (strNode.charAt(i) != ' ') {
				nodewoutspaces += strNode.charAt(i); 
			}
		}

		//column is letter
		char col=nodewoutspaces.charAt(0); 

		// replaces all chars and only has number left
		int row=Integer.parseInt(nodewoutspaces.replaceAll("[\\D]", ""));
		Node node= new Node(col, row); //creates node with row and column
		return node;
	}

	private static Pair<Node, ArrayList<Node>> parseLine(String line)
	{
		StringTokenizer stk = new StringTokenizer(line, ":");

		// Grab the (left) vertex
		Node left = parseNode(stk.nextToken());

		// Grab the right side
		ArrayList<Node> rhs = parseRightSide(stk.nextToken()); 

		return new Pair<Node, ArrayList<Node>>(left, rhs);
	}

	public static ArrayList<Node> parseRightSide(String strRHS)
	{
		ArrayList<Node> nodes = new ArrayList<Node>();
		//takes everything on right side of '('
		StringTokenizer stk = new StringTokenizer(strRHS, "(");
		//while coordinates remain, search through right side, add to arraylist
		while(stk.hasMoreTokens())
		{
			nodes.add(parseNode(stk.nextToken()));
		}
		return nodes;
	}
}
