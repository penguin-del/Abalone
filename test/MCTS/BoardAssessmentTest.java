package MCTS;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import board.Layer;
import board.Marble.MarbleColor;
import graph.AbaloneGraph;

class BoardAssessmentTest {
	
	public void Dominance_Node_Adder(Layer layer) {
		layer.addBlack('B', 2);
		layer.addBlack('E', 2);
		layer.addBlack('B', 5);
		layer.addBlack('E', 5);
		layer.addBlack('H', 5);
		layer.addBlack('E', 8);
		layer.addBlack('H', 8);
	}

	@Test
	void test_calculate_distance() {
		BoardStateAssessment bsa = new BoardStateAssessment(Layer.getDefaultBoard(), MarbleColor.BLACK);
		assertEquals(bsa.calculateDistance(AbaloneGraph.get().getVertex('B', 1),AbaloneGraph.get().getVertex('E', 3)), Math.sqrt(7));
		assertEquals(bsa.calculateDistance(AbaloneGraph.get().getVertex('B', 2),AbaloneGraph.get().getVertex('E', 2)), 3);
		assertEquals(bsa.calculateDistance(AbaloneGraph.get().getVertex('E', 2),AbaloneGraph.get().getVertex('E', 3)), 1);
		assertEquals(bsa.calculateDistance(AbaloneGraph.get().getVertex('B', 1),AbaloneGraph.get().getVertex('A', 3)), Math.sqrt(7));
	}
	
	@Test
	void test_Area_Dominance_and_Blob_Metric() {
		Layer layer = new Layer();
		Dominance_Node_Adder(layer);
		BoardStateAssessment board = new BoardStateAssessment(layer, MarbleColor.BLACK);
		
		assertEquals(board.determineScore(), (float) 9/(float) 35);
		
		layer.addBlack('E', 4);
		layer.addBlack('E', 6);
		board = new BoardStateAssessment(layer, MarbleColor.BLACK);
		
		assertEquals(board.determineScore(),((float)34/(float)35));
		
		layer.addBlack('A', 1);
		layer.addBlack('A', 2);
		board = new BoardStateAssessment(layer, MarbleColor.BLACK);
		
		assertEquals(board.determineScore(), (float) 18/(float) 35);
		
	}
	@Test
	void test_getScore_Hybrid() {
	
		Layer layer = Layer.getDefaultBoard();
		
		
		HybridScoring hs = new HybridScoring();
		BoardStateAssessment bsa = new BoardStateAssessment(layer, MarbleColor.BLACK);
		System.out.println(bsa.determineScore());
		
		System.out.println(hs.getScore(layer, MarbleColor.WHITE));
		//assertTrue(hs.getScore(layer, MarbleColor.BLACK)==0);
	}
	
	
//	@Test
//	void second_test_for_area_dominance_and Blobs() {
//		Layer layer = new Layer();
//		Dominance
//	}

}
