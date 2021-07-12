package MCTS;

import java.util.ArrayList;

import board.Layer;
import board.Marble.MarbleColor;
import move.generator.MoveGenerator;
import move.representation.Move;

public class MonteCarloTree {
	
	protected Layer _layer;
	protected MarbleColor _color;
	protected ArrayList<TreeNode> _branches;
	
	public MonteCarloTree(Layer layer, MarbleColor color) {
		_layer = layer;
		_color = color;
		_branches = createBranches();
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
	public ArrayList<TreeNode> createBranches() {
		
		MoveGenerator allMoves = new MoveGenerator(_layer);
		ArrayList<Move> moveList = allMoves.computeAllMoves(_color);
		ArrayList<TreeNode> branches = new ArrayList<TreeNode>();
		
		//We want to copy the original layer and make a move to get a new layer.
		//Then we want to make that new layer into a tree Node for the initial layer (S0)
		for (Move move: moveList) {
			Layer newLayer = _layer.getClone();
			move.makeMove(newLayer);
			TreeNode temp = new TreeNode(newLayer);
			branches.add(temp);			
		}
		return branches;
	}
}
