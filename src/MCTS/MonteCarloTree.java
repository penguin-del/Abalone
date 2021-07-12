package MCTS;

import java.util.ArrayList;

import board.Layer;
import board.Marble.MarbleColor;
import move.generator.MoveGenerator;
import move.representation.Move;
//only has access to the root
//create root node with a layer. 
public class MonteCarloTree {
	
	protected TreeNode _root;
	protected MarbleColor _color;
	protected ArrayList<TreeNode> _branches;
	
	public MonteCarloTree(Layer layer, MarbleColor color) {
		_root = new TreeNode(layer);
		_color = color;
		_branches = expandBranches(_root);
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
	public void expandBranches(TreeNode node) {
		
		MoveGenerator allMoves = new MoveGenerator(node._layer);
		ArrayList<Move> moveList = allMoves.computeAllMoves(_color);
		ArrayList<TreeNode> branches = new ArrayList<TreeNode>();
		
		//We want to copy the original layer and make a move to get a new layer.
		//Then we want to make that new layer into a tree Node for the initial layer (S0)
		//If we wanted to order the moves for priority, this is the injection point.
		for (Move move: moveList) {
			Layer newLayer = move.makeMoveOnCopy(node._layer);
			node.addChild(new TreeNode(newLayer));			
		}
	}
	
	//Expand that root node
}
