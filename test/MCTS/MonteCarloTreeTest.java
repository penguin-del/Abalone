package MCTS;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import board.Layer;
import board.Marble.MarbleColor;
import graph.AbaloneGraph;
import move.representation.SimpleMove;

class MonteCarloTreeTest {

	public void almost_won_node_adder(Layer layer) {
		layer.addBlack('D',8);
		layer.addBlack('D',8);
		layer.addBlack('D',8);
		layer.addBlack('D',8);
		layer.addBlack('D',8);
		layer.addBlack('D',8);
		layer.addBlack('D',8);
		layer.addBlack('D',8);
		layer.addBlack('D',8);
		layer.addWhite('F', 9);
		layer.addWhite('F', 8);
		layer.addWhite('G', 9);
		layer.addWhite('F', 6);
		layer.addWhite('G', 7);
		layer.addWhite('H', 8);
		layer.addWhite('H', 7);
		layer.addWhite('I', 7);
		layer.addWhite('I', 6);
	}

	@Test
	void test_UCB1_Calculation() {
		Layer layer = new Layer();
		layer.addBlack('A', 1);

		Layer layerV2 = new Layer();
		layerV2.addBlack('B', 1);

		Layer layerV3 = new Layer();
		layerV3.addBlack('A', 2);

		Layer layerV4 = new Layer();
		layerV4.addBlack('B', 2);

		TreeNode node1 = new TreeNode(layer, null);
		node1._timesVisited = 3;
		node1._totalScore = 35;

		TreeNode node2 = new TreeNode(layerV2, new SimpleMove(AbaloneGraph.get().getVertex('A', 1), AbaloneGraph.get().getVertex('B' , 1)));
		node2._totalScore = 0;
		node2._timesVisited = 0;

		TreeNode node3 = new TreeNode(layerV2, new SimpleMove(AbaloneGraph.get().getVertex('A', 1), AbaloneGraph.get().getVertex('A' , 2)));
		node3._totalScore = 15;
		node3._timesVisited = 1;

		TreeNode node4 = new TreeNode(layerV2, new SimpleMove(AbaloneGraph.get().getVertex('A', 1), AbaloneGraph.get().getVertex('B' , 2)));
		node4._totalScore = 20;
		node4._timesVisited = 2;

		node1.addChild(node2);
		node1.addChild(node3);
		node1.addChild(node4);

		MonteCarloTree tree = new MonteCarloTree(node2.getLayer(), MarbleColor.BLACK);
		assertEquals(tree.UCB1(node1, node2), Double.POSITIVE_INFINITY );
		
		MonteCarloTree secondTree = new MonteCarloTree(node3.getLayer(), MarbleColor.BLACK);
		//assertEquals(secondTree.UCB1(node1, node3),  );
		
	//	MonteCarloTree bigtree = new MonteCarloTree(Layer.getDefaultBoard(), MarbleColor.BLACK);
		//System.out.println(bigtree.run());

	}

	@Test
	void test_terminal_UCB1() {
		Layer layer = new Layer();
		almost_won_node_adder(layer);

		//		Line pusher = new Line();
		//		pusher.addToLine(AbaloneGraph.get().getVertex('F', 6));
		//		pusher.addToLine(AbaloneGraph.get().getVertex('G', 7));
		//		pusher.addToLine(AbaloneGraph.get().getVertex('H', 8));

		//	MonteCarloTree tree = new MonteCarloTree(layer, MarbleColor.WHITE);
	}

}
