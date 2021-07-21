package MCTS;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import board.Layer;
import board.Marble.MarbleColor;
import graph.AbaloneGraph;

class BoardAssessmentTest {

	@Test
	void test_calculate_distance() {
		BoardStateAssessment bsa = new BoardStateAssessment(Layer.getDefaultBoard(), MarbleColor.BLACK);
		assertEquals(bsa.calculateDistance(AbaloneGraph.get().getVertex('B', 1),AbaloneGraph.get().getVertex('E', 3)), Math.sqrt(7));
		assertEquals(bsa.calculateDistance(AbaloneGraph.get().getVertex('B', 2),AbaloneGraph.get().getVertex('E', 2)), 3);
		assertEquals(bsa.calculateDistance(AbaloneGraph.get().getVertex('E', 2),AbaloneGraph.get().getVertex('E', 3)), 1);
		assertEquals(bsa.calculateDistance(AbaloneGraph.get().getVertex('B', 1),AbaloneGraph.get().getVertex('A', 3)), Math.sqrt(7));
	}

}
