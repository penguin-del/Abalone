package subgraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import board.Layer;
import board.LightNode;
import board.Marble.MarbleColor;
import graph.AbaloneGraph;
import graph.Node;
import subgraph.BFSMarkingColor.MarkingColor;
import utilities.ListUtilities;


public class BFS
{

	//attribute not necessary because we have a method in AbaloneGraph that gets the board for us 
	//protected BoardGame _bg;
	protected Layer _layer;
	protected ArrayList<LightNode> _adjacency;
	protected final int _MIN_FORMATION_SIZE = 3;

	public BFS(Layer layer, MarbleColor color)
	{
		//_bg = bg;
		_layer = layer;
		ArrayList<LightNode> populatedNodes =  _layer.getNodes(color); //_bg.getBoard().getPopulatedNodes(color);
		_adjacency = new ArrayList<LightNode>();

		//for our purposes now, we will use the enum MarbleColor to determine visited nodes and completed nodes.
		//Empty will represent undiscovered nodes, WHITE will represent discovered nodes, and Black will represent fully visited nodes
		for (LightNode node : populatedNodes) {
			_adjacency.add(node);
		}

	}


	public ArrayList<ArrayList<Node>> BFSOnMarbleFormations (MarbleColor color) {

		//creates an arraylist of all formations on the board. A formation is defined as a grouping of interconnected nodes. 
		//A line of 5 could count as a formation but so could a zig-zag line.
		ArrayList<ArrayList<Node>> marbleFormations = new ArrayList<ArrayList<Node>>();

		for (LightNode node : _adjacency){

			//creates a new formation and queue for each node
			ArrayList<Node> formation = new ArrayList<Node>();
			Queue<Node> queue = new LinkedList<Node>();

			//encodes the starting node and marks it visited (aka black)
			queue.add(AbaloneGraph.get().getVertex(node._col, node._row ));
			AbaloneGraph.get().getVertex(node._col, node._row )._color = MarkingColor.MARKINGBLACK;

			//while the queue still has values we will loop through and check for adjacent nodes
			while (!queue.isEmpty())
			{

				//for (LightNode neighbor : queue.peek().getNeighbors())
				for (Node neighbor: queue.peek().getNeighbors())
				{
					if(_layer.contains(neighbor._col, neighbor._row) == color &&
							neighbor._color == MarkingColor.MARKINGWHITE )
					{
						neighbor._color = MarkingColor.MARKINGPURPLE;
						queue.add(neighbor);
					}
				}

				ListUtilities.ListInsertInOrder(formation, queue.poll());
				//addToSortedList(formation, queue.poll());
			}

			if(formation.size() >= _MIN_FORMATION_SIZE) marbleFormations.add(formation);

		}

		for (LightNode node: _adjacency) {
			AbaloneGraph.get().getVertex(node._col, node._row)._color = MarkingColor.MARKINGWHITE; 				
		}
		
	return marbleFormations;
}
}
//this addToSortedList function works for adding a node to an array list of nodes
//	public void addToSortedList(ArrayList<Node> formation, Node node){
//		int end = formation.size();
//		boolean isAdded = false;
//		for (int i = 0; i < end; i++) {
//			// this if statement orders the line from least to greatest
//			if (node.compareTo(formation.get(i)) == -1 && isAdded ==false) {
//				formation.add(i, node);
//				isAdded = true;
//			}
//		}
//
//		if (isAdded == false) {
//			formation.add(node);
//		}

//	}
//this addToSortedList method works for adding an arraylist of nodes to and arraylist of arraylists of nodes
//	public void addToSortedList(ArrayList<ArrayList<Node>> formations, ArrayList<Node> newFormation) {
//		int end = formations.size();
//		boolean isAdded = false;
//		for (int i = 0; i < end; i++) {
//			// this if statement orders the line from least to greatest
//			if (newFormation.get(0).compareTo(formations.get(i).get(0)) == -1 && isAdded ==false) {
//				formations.add(i, newFormation);
//				isAdded = true;
//			}
//		}
//
//		if (isAdded == false) {
//			formations.add(newFormation);
//		}
//	}
//}


