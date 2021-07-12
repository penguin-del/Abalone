package MCTS;

import board.Layer;

public class TreeNode {
	
	protected Layer _layer;
	protected int _T;
	protected int _N;
	
	public TreeNode(Layer layer) {
		_layer = layer;
		_T = 0;
		_N = 0;
	}

}
