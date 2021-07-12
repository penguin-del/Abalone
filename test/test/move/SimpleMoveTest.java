package test.move;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import board.Layer;
import board.Marble.MarbleColor;
import graph.AbaloneGraph;
import graph.Node;
import move.representation.SimpleMove;

class SimpleMoveTest {

	@Test
	 void Test_Black_Marble_Creation()  {
		Layer layer = Layer.getDefaultBoard();

		//testing black marbles
		assertEquals(layer.contains('A', 1), MarbleColor.BLACK);
		assertNotEquals(layer.contains('A', 1), MarbleColor.WHITE);
		assertNotEquals(layer.contains('A', 1), MarbleColor.EMPTY);

		assertEquals(layer.contains('E', 2), MarbleColor.BLACK);
		assertNotEquals(layer.contains('E', 2), MarbleColor.WHITE);
		assertNotEquals(layer.contains('E', 2), MarbleColor.EMPTY);

		assertEquals(layer.contains('E', 3), MarbleColor.BLACK);
		assertNotEquals(layer.contains('E', 3), MarbleColor.WHITE);
		assertNotEquals(layer.contains('E', 3), MarbleColor.EMPTY);

		assertEquals(layer.contains('B', 3), MarbleColor.EMPTY);
		assertNotEquals(layer.contains('B', 3), MarbleColor.WHITE);
		assertNotEquals(layer.contains('B', 3), MarbleColor.BLACK);

	}
	
	@Test
	 void Test_Default_Empty_Spaces()  {
		Layer layer = new Layer();
		assertEquals(layer.contains('A', 1), MarbleColor.EMPTY);
		assertNotEquals(layer.contains('A', 1), MarbleColor.WHITE);
		assertNotEquals(layer.contains('A', 1), MarbleColor.BLACK);
	}

	@Test
	void Test_Moving_Simple_Move()  {
		Layer layer = new Layer();

		Node node= AbaloneGraph.get().getVertex('A',2);
		layer.addBlack('A', 2);
		Node node2= AbaloneGraph.get().getVertex('A',3);
		SimpleMove simple= new SimpleMove(node, node2);
		
		Layer newLayer = layer.getClone();
		simple.makeMoveOnOriginalBoard(newLayer);

		assertTrue(newLayer.contains('A', 3)==MarbleColor.BLACK);
		assertTrue(newLayer.contains('A', 2)==MarbleColor.EMPTY);

		node= AbaloneGraph.get().getVertex('F',7);
		layer.addBlack('F', 7);
		node2= AbaloneGraph.get().getVertex('F',6);
		simple= new SimpleMove(node, node2);
		
		Layer newLayer2 = layer.getClone();
		simple.makeMoveOnOriginalBoard(newLayer2);
		assertTrue(newLayer2.contains('F', 6)==MarbleColor.BLACK);
		assertTrue(newLayer2.contains('F', 7)==MarbleColor.EMPTY);
	}
	
	@Test
	void Test_Moving_SimpleMove_OnCopy() {
		Layer layer = new Layer();

		Node node= AbaloneGraph.get().getVertex('A',2);
		layer.addBlack('A', 2);
		Node node2= AbaloneGraph.get().getVertex('A',3);
		SimpleMove simple= new SimpleMove(node, node2);
		
		Layer newLayer = layer.getClone();
		simple.makeMoveOnOriginalBoard(newLayer);

		assertTrue(newLayer.contains('A', 3)==MarbleColor.BLACK);
		assertTrue(newLayer.contains('A', 2)==MarbleColor.EMPTY);

		node= AbaloneGraph.get().getVertex('F',7);
		layer.addBlack('F', 7);
		node2= AbaloneGraph.get().getVertex('F',6);
		
		simple= new SimpleMove(node, node2);
		Layer newLayer2 =simple.makeMoveOnCopyBoard(layer);

		assertTrue(newLayer2.contains('F', 6)==MarbleColor.BLACK);
		assertTrue(newLayer2.contains('F', 7)==MarbleColor.EMPTY);
	
	}
}
