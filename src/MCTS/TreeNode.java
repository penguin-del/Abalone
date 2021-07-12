package MCTS;

import java.util.ArrayList;

import board.Layer;

public class TreeNode {
	
	protected Layer _layer;
	protected int _T;
	protected int _N;
	protected ArrayList<TreeNode> _children;
	
	public TreeNode(Layer layer) {
		_layer = layer;
		_T = 0;
		_N = 0;
		_children = new ArrayList<TreeNode>();
	}

	public void addChild(TreeNode child) {
		_children.add(child);
	}
}
