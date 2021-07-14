package MCTS;

import java.util.ArrayList;

import board.Layer;
import move.representation.Move;
import utilities.LocalRandom;

public class TreeNode {
	
	//UCS1(Si) = (average value of node) + C* sqrt(lnN / ni)
	//C is some constant the we define in the constants class
	//ni is the number of visits to the child node
	//N is the number of visits to the parent node
	//average value is defined as T/N where T is the total score of a node (determined by rollout).
	
	protected Layer _layer;
	//predecessorMove  is the move made to get to the layer (ergo _layer is the board state after the move has been made)
	//The parent treenode contains the beforehand information.
	protected Move _predecessorMove;
	//represents T in our UCB1 equation	
	protected float _totalScore;
	//represents N in our UCB1 equation 
	protected int _timesVisited;
	protected ArrayList<TreeNode> _children;
	protected double _UCB1 = 0;
	
	public TreeNode(Layer layer, Move move) {
		_layer = layer;
		_totalScore = 0;
		_timesVisited = 0;
		_children = new ArrayList<TreeNode>();
		_predecessorMove = move;
	}
	
	public TreeNode greatestScoreChild() {
		//Sets greatest score to negative infinity because we know all scores will be greater than that
		TreeNode highestScorer = null;
		double greatestScore = Double.NEGATIVE_INFINITY;

		//loops through all children of a given tree node and finds the one with the greatest total score (T in UCB1 equation)
		for (TreeNode child : this._children) {
			if(child._totalScore > greatestScore) {
				greatestScore = child._totalScore;
				highestScorer = child;
			}
		}

		return highestScorer;
	}

	public void addChild(TreeNode child) {
		_children.add(child);
	}
	
	public boolean isLeaf()
	{
		return _children.isEmpty();
	}
	
	public Layer getLayer() {
		return _layer;
	}
	
	public TreeNode randomChild() {
		return _children.get(LocalRandom.nextInt(_children.size()));
	}
	
	public Move getMove() {
		return _predecessorMove;
	}
	
	public String toString() {
		String TreeNodeString = "";
		TreeNodeString += "The move made here was " +_predecessorMove+ "\n";
		TreeNodeString += _layer +"\n";
		TreeNodeString += "I have "+ _children.size()+" children. \n";
		TreeNodeString += "I have been visited "+_timesVisited+" times. \n";
		TreeNodeString += "I have a score of "+ _totalScore;
		
		return TreeNodeString;
	}
}
