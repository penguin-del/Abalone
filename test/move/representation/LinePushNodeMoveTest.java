package move.representation;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

import board.Layer;
import board.Marble.MarbleColor;
import formation.shape.Line;
import graph.AbaloneGraph;
import graph.Node;
import move.generator.LinePushNodeMoveGenerator;

class LinePushNodeMoveTest {
	private static final Node INVALID_NODE = new Node('$' ,-10);
	
	
	@Test
	void Test_Line_Push_Node_Move_Horizontal() {
		Layer layer = new Layer();
		Line line= new Line();

		LinePushNodeMoveGenerator pmg= new LinePushNodeMoveGenerator(layer);

		Node node= AbaloneGraph.get().getVertex('C',4);
		layer.addBlack('C', 4);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('D',4);
		layer.addBlack('D', 4);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',4);
		layer.addBlack('E', 4);
		line.addToLine(node);

		Node pushNode= AbaloneGraph.get().getVertex('B',4);
		Node pushNode2= AbaloneGraph.get().getVertex('F',4);

		Node destination= AbaloneGraph.get().getVertex('A',4);
		Node destination2= AbaloneGraph.get().getVertex('G',4);

		layer.remove('A', 4);
		layer.remove('G', 4);

		layer.addWhite('B', 4);
		layer.addWhite('F', 4);


		pmg.computeAllLinePushNodes(MarbleColor.BLACK);


		LinePushNode lpn= new LinePushNode(line, pushNode, destination);
		Layer newLayer= layer.getClone();	

		lpn.makeMoveOnOriginalBoard(newLayer);

		assertTrue(newLayer.contains('B',4)==MarbleColor.BLACK);
		assertTrue(newLayer.contains('A',4)==MarbleColor.WHITE);
		assertTrue(newLayer.contains('E',4)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('F',4)==MarbleColor.WHITE);

		LinePushNode lpn2= new LinePushNode(line, pushNode2,destination2);

		Layer newLayer2 =lpn2.makeMoveOnCopyBoard(layer);

		assertTrue(newLayer2.contains('F',4)==MarbleColor.BLACK);
		assertTrue(newLayer2.contains('G',4)==MarbleColor.WHITE);
		assertTrue(newLayer2.contains('C',4)==MarbleColor.EMPTY);

	}


	@Test
	void Test_Line_Push_Node_Move_LeftToRight() {
		Layer layer = new Layer();
		Line line= new Line();

		LinePushNodeMoveGenerator pmg= new LinePushNodeMoveGenerator(layer);

		Node node= AbaloneGraph.get().getVertex('C',4);
		layer.addWhite('C', 4);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('D',5);
		layer.addWhite('D', 5);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',6);
		layer.addWhite('E', 6);
		line.addToLine(node);

		Node pushNode= AbaloneGraph.get().getVertex('B',3);
		Node pushNode2= AbaloneGraph.get().getVertex('F',7);

		layer.addBlack('B', 3);
		layer.addBlack('F', 7);

		Node destination= AbaloneGraph.get().getVertex('A',2);
		Node destination2= AbaloneGraph.get().getVertex('G',8);

		layer.remove('A', 2);
		layer.remove('G', 8);

		pmg.computeAllLinePushNodes(MarbleColor.WHITE);


		LinePushNode lpn= new LinePushNode(line, pushNode, destination);

		lpn.makeMoveOnOriginalBoard(layer);

		assertTrue(layer.contains('B',3)==MarbleColor.WHITE);
		assertTrue(layer.contains('A',2)==MarbleColor.BLACK);
		assertTrue(layer.contains('E',6)==MarbleColor.EMPTY);


		LinePushNode lpn2= new LinePushNode(line, pushNode2,destination2);

		lpn2.makeMoveOnOriginalBoard(layer);

		assertTrue(layer.contains('F',7)==MarbleColor.WHITE);
		assertTrue(layer.contains('G',8)==MarbleColor.BLACK);
		assertTrue(layer.contains('C',4)==MarbleColor.EMPTY);
	}

	@Test
	void Test_Line_Push_Node_Move_RightToLeft() {
		Layer layer = new Layer();
		Line line= new Line();

		LinePushNodeMoveGenerator pmg= new LinePushNodeMoveGenerator(layer);


		Node node= AbaloneGraph.get().getVertex('E',4);
		layer.addWhite('E', 4);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',5);
		layer.addWhite('E', 5);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',6);
		layer.addWhite('E', 6);
		line.addToLine(node);

		Node pushNode= AbaloneGraph.get().getVertex('E',3);
		Node pushNode2= AbaloneGraph.get().getVertex('E',7);

		Node destination= AbaloneGraph.get().getVertex('E',2);
		Node destination2= AbaloneGraph.get().getVertex('E',8);

		layer.remove('E', 2);
		layer.remove('E', 8);

		layer.addBlack('E', 3);
		layer.addBlack('E', 7);

		pmg.computeAllLinePushNodes(MarbleColor.WHITE);


		LinePushNode lpn= new LinePushNode(line, pushNode, destination);

		lpn.makeMoveOnOriginalBoard(layer);

		assertTrue(layer.contains('E',3)==MarbleColor.WHITE);
		assertTrue(layer.contains('E',2)==MarbleColor.BLACK);
		assertTrue(layer.contains('E',6)==MarbleColor.EMPTY);


		LinePushNode lpn2= new LinePushNode(line, pushNode2,destination2);

		lpn2.makeMoveOnOriginalBoard(layer);

		assertTrue(layer.contains('E',7)==MarbleColor.WHITE);
		assertTrue(layer.contains('E',8)==MarbleColor.BLACK);
		assertTrue(layer.contains('E',4)==MarbleColor.EMPTY);
	}

	@Test
	void Test_Line_Push_Node_Move_OffBoard() {
		Layer layer = new Layer();
		Line line= new Line();

		Node node= AbaloneGraph.get().getVertex('B',1);
		layer.addBlack('B', 1);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('C',1);
		layer.addBlack('C', 1);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('D',1);
		layer.addBlack('D', 1);
		line.addToLine(node);

		Node pushNode= AbaloneGraph.get().getVertex('E',1);

		layer.addWhite('E',1);

		LinePushNode lpn= new LinePushNode(line, pushNode, INVALID_NODE);

		Layer newLayer= layer.getClone();	

		lpn.makeMoveOnOriginalBoard(newLayer);

		assertTrue(newLayer.contains('E',1)==MarbleColor.BLACK);

		assertTrue(newLayer.contains('B',1)==MarbleColor.EMPTY);

	}
	
	@Test
	void Test_Line_Push_Node_Move_OnCopy() {
		Layer layer = new Layer();
		Line line= new Line();


		Node node= AbaloneGraph.get().getVertex('C',4);
		layer.addBlack('C', 4);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('D',4);
		layer.addBlack('D', 4);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',4);
		layer.addBlack('E', 4);
		line.addToLine(node);

		Node pushNode= AbaloneGraph.get().getVertex('B',4);
		Node pushNode2= AbaloneGraph.get().getVertex('F',4);

		Node destination= AbaloneGraph.get().getVertex('A',4);
		Node destination2= AbaloneGraph.get().getVertex('G',4);

		layer.remove('A', 4);
		layer.remove('G', 4);

		layer.addWhite('B', 4);
		layer.addWhite('F', 4);

		LinePushNode lpn= new LinePushNode(line, pushNode, destination);

		Layer newLayer=lpn.makeMoveOnCopyBoard(layer);

		assertTrue(newLayer.contains('B',4)==MarbleColor.BLACK);
		assertTrue(newLayer.contains('A',4)==MarbleColor.WHITE);
		assertTrue(newLayer.contains('E',4)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('F',4)==MarbleColor.WHITE);

		LinePushNode lpn2= new LinePushNode(line, pushNode2,destination2);

		Layer newLayer2 =lpn2.makeMoveOnCopyBoard(layer);

		assertTrue(newLayer2.contains('F',4)==MarbleColor.BLACK);
		assertTrue(newLayer2.contains('G',4)==MarbleColor.WHITE);
		assertTrue(newLayer2.contains('C',4)==MarbleColor.EMPTY);

	}
	


}
