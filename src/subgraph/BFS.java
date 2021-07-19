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
import utilities.Pair;


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

	public int BFSDiameter(ArrayList <Node> formation) {
		
		//Creates a new queue for a breath first search and adds the first Pair.
		Queue<Pair<Node, Integer>> queue = new LinkedList<Pair<Node, Integer>>(); 
		Pair<Node, Integer> startNode = new Pair<Node, Integer>(formation.get(0), 1);
		queue.add(startNode);
		ArrayList<Pair<Node, Integer>> checker = new ArrayList<Pair<Node, Integer>>();
		
		//Goes through the queue and marks each unvisited node and keeps track of depth.
		while (!queue.isEmpty()) {
			Pair<Node, Integer> check = queue.poll();
			Node node = check.getFirst();
			int depth = check.getSecond();
			node._color = MarkingColor.MARKINGBLACK;
			checker.add(check);
			
			//figures out the neighbors and marks them as visited
			for (Node neighbor: node.getNeighbors()) {
				if(neighbor._color == MarkingColor.MARKINGWHITE) {
					neighbor._color = MarkingColor.MARKINGBLACK;
					Pair<Node, Integer> next = new Pair<Node, Integer>(neighbor, depth+1);
					queue.add(next);
				}
			}
		}
			//determines the longest path and calls that the diameter.
			int diameter = -1;
			
			for (Pair<Node, Integer> candidate: checker) {
				if (candidate.getSecond() > diameter) {
					diameter = candidate.getSecond();
				}
			}
			
			//wipes away the markings from our function so we can use the graph again
			for(Node erased: formation) {
				erased._color = MarkingColor.MARKINGWHITE;
			}
			
		return diameter;		
	}
}


