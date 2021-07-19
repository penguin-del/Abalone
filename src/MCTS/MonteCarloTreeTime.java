package MCTS;

import java.util.ArrayList;

import board.Layer;
import board.Marble.MarbleColor;
import globals.Constants;
import move.generator.MoveGenerator;
import move.representation.Move;

//only has access to the root
//create root node with a layer. 
public class MonteCarloTreeTime {

	protected TreeNode _root;
	protected MarbleColor _startingColor;
	protected int _size;
	protected int _numRollout;

	public MonteCarloTreeTime(Layer layer, MarbleColor color) {
		_root = new TreeNode(layer, null);
		_startingColor = color;
		_size = 0;
		_numRollout = 0;
	}

	//
	//       S1
	//      /
	//     /
	//   S0 ----> S2
	//     \
	//      \
	//       S3
	//
	//This is a very basic example of what we are doing. S0 represents our original layer or board state
	// S1, S2, and S3 are child nodes or board states that are one move away.
	// In this function we are populating an array list of TreeNodes (like S1) to later expand upon.
	public void expandBranches(TreeNode node, MarbleColor color) {
		
		//node._layer.getNodes(color);
		MoveGenerator allMoves = new MoveGenerator(node._layer);
		ArrayList<Move> moveList = allMoves.computeAllMoves(color);

		//We want to copy the original layer and make a move to get a new layer.
		//Then we want to make that new layer into a tree Node for the initial layer (S0)
		//If we wanted to order the moves for priority, this is the injection point.
		for (Move move: moveList) {
			Layer newLayer = move.makeMoveOnCopyBoard(node._layer);
			node.addChild(new TreeNode(newLayer, move));	
			_size += 1;
		}
	}

	public double UCB1(TreeNode parent, TreeNode child) {

		//Since we are dividing by times the child node has been visited, if that value is zero then the whole equation evaluates to infinity.
		if (child._timesVisited == 0) return Double.POSITIVE_INFINITY;

		//UCS1(Si) = (average value of node) + C* sqrt(lnN / ni)
		//C is some constant the we define in the constants class
		//ni is the number of visits to the child node
		//N is the number of visits to the parent node
		//average value is defined as T/N where T is the total score of a node (determined by rollout).

		return child._totalScore / child._timesVisited +
			   Constants.UCB1_CONSTANT * (Math.sqrt((Math.log(parent._timesVisited)) / child._timesVisited));
	}

	//	public TreeNode choseRollout(TreeNode node)
	//	{
	//		if (node.isLeaf()) return node;
	//
	//		//Sets initial UCB1 to negative infinity since we know any other UCB1 will be greater
	//		double greatestUCB1 = Double.NEGATIVE_INFINITY;
	//		TreeNode greatestNode = null;
	//		
	//		//loops through all the children for a tree node calculates the UCB1 
	//		for (TreeNode child : node._children) {
	//			double childUCB = UCB1(child);
	//			// child._UCB1 = UCB1(child);
	//			
	//			//checks to see if the child has the greatest UCB1
	//			if (childUCB > greatestUCB1) {
	//				greatestUCB1 = childUCB;
	//				greatestNode = child;
	//			}
	//		}	
	//
	//		return choseRollout(greatestNode);
	//	}

	// TO MOVE TO TreeNODE
	public TreeNode highestScore(TreeNode node) {

		//Sets greatest score to negative infinity because we know all scores will be greater than that
		TreeNode highestScorer = null;
		double greatestScore = Double.NEGATIVE_INFINITY;

		//loops through all children of a given tree node and finds the one with the greatest total score (T in UCB1 equation)
		for (TreeNode child : node._children) {
			if(child._totalScore > greatestScore) {
				greatestScore = child._totalScore;
				highestScorer = child;
			}
		}

		return highestScorer;
	}

	//	public void treeTraversal(TreeNode node) {
	//		
	//		//for a given tree node we will expand the branches and traverse the tree 100 times.
	//		int numIterations = 0;
	//		expandBranches(_root);
	//		while (numIterations <= Constants.NUM_ITERATION_ON_TREE) {
	//			
	//			//Each time we look through the tree we will chose which node to rollout and then roll it out.
	//			TreeNode chosenNode = choseRollout(node);
	//			rollout();
	//			numIterations++;
	//		}
	//		
	//		//We then look for the highest total score and chose that tree node as our next move
	//		TreeNode move = highestScore(node);
	//		//makeMoveOnOriginalBoard()
	//		
	//		
	//		
	//	}

	public Move run()
	{
		// Expansion of the root (one-time operation)
		expandBranches(_root, _startingColor);
		
		boolean toFinish = false;
		long startTime = System.currentTimeMillis();
		
		
		while (!toFinish) {
			
			run(_root, _startingColor);
				
			toFinish = (System.currentTimeMillis()- startTime >= Constants.TIME_LIMIT_FOR_RUN);
		}

		// Chose highest score node
		Move chosenMove =  _root.greatestScoreChild().getMove();
		System.out.println("Height: "+_root.height());
		System.out.println("Width: "+_root.width());
		System.out.println("Size: "+_size);
		System.out.println("Rollout: "+_numRollout);
		return chosenMove;
	}

	private TreeNode greatest(TreeNode parent)
	{
		//Sets initial UCB1 to negative infinity since we know any other UCB1 will be greater
		double greatestUCB1 = Double.NEGATIVE_INFINITY;
		TreeNode greatestNode = null;

		//loops through all the children for a tree node calculates the UCB1 
		for (TreeNode child : parent._children)
		{
			double childUCB = UCB1(parent, child);
			// child._UCB1 = UCB1(child);

			//checks to see if the child has the greatest UCB1
			if (childUCB > greatestUCB1) {
				greatestUCB1 = childUCB;
				greatestNode = child;
			}
		}

		return greatestNode;
	}

	private float run(TreeNode node, MarbleColor color)
	{
		// (1) Rollout has been identified
		// (2) Roll out acquiring the score
		// (3) Save the score in this node
		// (4) Pass the score up the tree for Total Score accumulation
		if (node.isLeaf())
		{
			if (node._timesVisited == 0)
			{
				Rollout rolled = new Rollout();
				node._totalScore = rolled.rollout(node.getLayer(), color, _startingColor);
				_numRollout += 1;
				node._timesVisited++;
				return node._totalScore;
			}
			else
			{
				expandBranches(node, color);
				TreeNode randChild = node.randomChild();
				if (randChild == null) System.err.println("Random Child is null ; MCTS::run");
				Rollout rolled = new Rollout();
				float rolloutScore = rolled.rollout(randChild.getLayer(), color, _startingColor);
				_numRollout += 1;
				randChild._totalScore += rolloutScore;
				randChild._timesVisited++;
				node._totalScore += rolloutScore;
				node._timesVisited++;
				return rolloutScore;
			}
		}

		// Choose the child (of node) with greatest UCB score
		TreeNode greatestChild = greatest(node);

		// Recursively pursue a leaf node to roll out
		// The score that is returned is the score from the path explored in the tree
		float rolloutScore = run(greatestChild, color.flipColor());

		// Update this node's score with the path-explored resulting score
		node._totalScore += rolloutScore;
		node._timesVisited++;

		return rolloutScore;

	}
	
	public int getSize() {
		return _size;
	}
	
	public String toString() {
		return "Root TreeNode: "+ _root;
	}
}
