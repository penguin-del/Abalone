package test.move;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

import board.Layer;
import board.Marble.MarbleColor;
import formation.shape.Line;
import graph.AbaloneGraph;
import graph.Node;
import move.representation.LinePushLine;

class LinePushLineMoveTest {
	
	
	private static final Node INVALID_NODE = new Node('$' ,-10);
	@Test
	
	void Test_Move_LinePushLine_Off_Board()  {
		Layer layer = new Layer();
		Line line= new Line();
		Line pushLine2 = new Line();

		Node node= AbaloneGraph.get().getVertex('A',1);
		layer.addBlack('A', 1);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('B',1);
		layer.addBlack('B', 1);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('C',1);
		layer.addBlack('C', 1);
		line.addToLine(node);

		node= AbaloneGraph.get().getVertex('D',1);
		layer.addWhite('D', 1);
		pushLine2.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',1);
		layer.addWhite('E', 1);
		pushLine2.addToLine(node);

		LinePushLine lplMove= new LinePushLine(line, pushLine2, INVALID_NODE);

		lplMove.makeMoveOnOriginalBoard(layer);
//		assertTrue(lplMove.makeMove(layer));

		assertTrue(layer.contains('D',1)==MarbleColor.BLACK);
		assertTrue(layer.contains('E',1)==MarbleColor.WHITE);
		assertTrue(layer.contains('A',1)==MarbleColor.EMPTY);

	}
	
	@Test
	void Test_Move_LinePushLine_LeftToRight() {
		Layer layer = new Layer();
		Line line= new Line();

		Line pushLine2 = new Line();
		Line pushLine3 = new Line();

		Node node= AbaloneGraph.get().getVertex('D',4);
		layer.addWhite('D', 4);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',5);
		layer.addWhite('E', 5);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('F',6);
		layer.addWhite('F', 6);
		line.addToLine(node);

		node= AbaloneGraph.get().getVertex('C',3);
		layer.addBlack('C', 3);
		pushLine2.addToLine(node);
		node= AbaloneGraph.get().getVertex('B',2);
		layer.addBlack('B', 2);
		pushLine2.addToLine(node);

		node = AbaloneGraph.get().getVertex('G',7);
		layer.addBlack('G', 7);
		pushLine3.addToLine(node);
		node= AbaloneGraph.get().getVertex('H',8);
		layer.addBlack('H', 8);
		pushLine3.addToLine(node);

		Node destination2= AbaloneGraph.get().getVertex('A',1);
		Node destination3= AbaloneGraph.get().getVertex('I',9);
	
		layer.remove('A', 1);
		layer.remove('I', 9);

		LinePushLine lplMove= new LinePushLine(line, pushLine2, destination2);

		
		lplMove.makeMoveOnOriginalBoard(layer);

		assertTrue(layer.contains('C',3)==MarbleColor.WHITE);
		assertTrue(layer.contains('A',1)==MarbleColor.BLACK);
		assertTrue(layer.contains('F',6)==MarbleColor.EMPTY);
		assertTrue(layer.contains('B',2)==MarbleColor.BLACK);

		LinePushLine lplMove2= new LinePushLine(line, pushLine3, destination3);
		lplMove2.makeMoveOnOriginalBoard(layer);

		assertTrue(layer.contains('G',7)==MarbleColor.WHITE);
		assertTrue(layer.contains('I',9)==MarbleColor.BLACK);
		assertTrue(layer.contains('D',4)==MarbleColor.EMPTY);
		assertTrue(layer.contains('H',8)==MarbleColor.BLACK);
	}
	
	@Test
	void Test_Move_LinePushLine_RightToLeft()  {
		Layer layer = new Layer();
		Line line= new Line();
		Line pushLine2 = new Line();
		Line pushLine3 = new Line();

		Node node= AbaloneGraph.get().getVertex('E',4);
		layer.addBlack('E', 4);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',5);
		layer.addBlack('E', 5);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',6);
		layer.addBlack('E', 6);
		line.addToLine(node);

		node= AbaloneGraph.get().getVertex('E',3);
		layer.addWhite('E', 3);
		pushLine2.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',2);
		layer.addWhite('E', 2);
		pushLine2.addToLine(node);

		node = AbaloneGraph.get().getVertex('E',7);
		layer.addWhite('E', 7);
		pushLine3.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',8);
		layer.addWhite('E', 8);
		pushLine3.addToLine(node);

		Node destination2= AbaloneGraph.get().getVertex('E',1);
		Node destination3= AbaloneGraph.get().getVertex('E',9);

		layer.remove('E', 1);
		layer.remove('E', 9);
		
		LinePushLine lplMove= new LinePushLine(line, pushLine2, destination2);

		lplMove.makeMoveOnOriginalBoard(layer);

		assertTrue(layer.contains('E',3)==MarbleColor.BLACK);
		assertTrue(layer.contains('E',1)==MarbleColor.WHITE);
		assertTrue(layer.contains('E',6)==MarbleColor.EMPTY);
		assertTrue(layer.contains('E',2)==MarbleColor.WHITE);

		LinePushLine lplMove2= new LinePushLine(line, pushLine3, destination3);
		lplMove2.makeMoveOnOriginalBoard(layer);

		assertTrue(layer.contains('E',7)==MarbleColor.BLACK);
		assertTrue(layer.contains('E',9)==MarbleColor.WHITE);
		assertTrue(layer.contains('E',4)==MarbleColor.EMPTY);
		assertTrue(layer.contains('E',8)==MarbleColor.WHITE);
	}
	
	@Test
	void Test_Move_LinePushLine_Horizontal() {
		Layer layer = new Layer();
		Line line= new Line();

		Line pushLine2 = new Line();
		Line pushLine3 = new Line();

		Node node= AbaloneGraph.get().getVertex('D',5);
		layer.addBlack('D', 5);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',5);
		layer.addBlack('E', 5);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('F',5);
		layer.addBlack('F', 5);
		line.addToLine(node);

		node= AbaloneGraph.get().getVertex('B',5);
		layer.addWhite('B', 5);
		pushLine2.addToLine(node);
		node= AbaloneGraph.get().getVertex('C',5);
		layer.addWhite('C', 5);
		pushLine2.addToLine(node);

		node = AbaloneGraph.get().getVertex('G',5);
		layer.addWhite('G', 5);
		pushLine3.addToLine(node);
		node= AbaloneGraph.get().getVertex('H',5);
		layer.addWhite('H', 5);
		pushLine3.addToLine(node);

		Node destination2= AbaloneGraph.get().getVertex('A',5);
		Node destination3= AbaloneGraph.get().getVertex('I',5);

		layer.remove('A', 5);
		layer.remove('I', 5);

		LinePushLine lplMove= new LinePushLine(line, pushLine2, destination2);

		lplMove.makeMoveOnOriginalBoard(layer);

		assertTrue(layer.contains('C',5)==MarbleColor.BLACK);
		assertTrue(layer.contains('A',5)==MarbleColor.WHITE);
		assertTrue(layer.contains('F',5)==MarbleColor.EMPTY);
		assertTrue(layer.contains('B',5)==MarbleColor.WHITE);

		LinePushLine lplMove2= new LinePushLine(line, pushLine3, destination3);
		lplMove2.makeMoveOnOriginalBoard(layer);

		assertTrue(layer.contains('G',5)==MarbleColor.BLACK);
		assertTrue(layer.contains('H',5)==MarbleColor.WHITE);
		assertTrue(layer.contains('D',5)==MarbleColor.EMPTY);
		assertTrue(layer.contains('I',5)==MarbleColor.WHITE);
	}
	
	@Test
	void Test_Move_LinePushLine_OnCopy() {
		Layer layer = new Layer();
		Line line= new Line();

		Line pushLine2 = new Line();
		Line pushLine3 = new Line();

		Node node= AbaloneGraph.get().getVertex('D',5);
		layer.addBlack('D', 5);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',5);
		layer.addBlack('E', 5);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('F',5);
		layer.addBlack('F', 5);
		line.addToLine(node);

		node= AbaloneGraph.get().getVertex('B',5);
		layer.addWhite('B', 5);
		pushLine2.addToLine(node);
		node= AbaloneGraph.get().getVertex('C',5);
		layer.addWhite('C', 5);
		pushLine2.addToLine(node);

		node = AbaloneGraph.get().getVertex('G',5);
		layer.addWhite('G', 5);
		pushLine3.addToLine(node);
		node= AbaloneGraph.get().getVertex('H',5);
		layer.addWhite('H', 5);
		pushLine3.addToLine(node);

		Node destination2= AbaloneGraph.get().getVertex('A',5);
		Node destination3= AbaloneGraph.get().getVertex('I',5);

		layer.remove('A', 5);
		layer.remove('I', 5);

		LinePushLine lplMove= new LinePushLine(line, pushLine2, destination2);

		Layer newLayer = lplMove.makeMoveOnCopyBoard(layer);

		assertTrue(newLayer.contains('C',5)==MarbleColor.BLACK);
		assertTrue(newLayer.contains('A',5)==MarbleColor.WHITE);
		assertTrue(newLayer.contains('F',5)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('B',5)==MarbleColor.WHITE);

		LinePushLine lplMove2= new LinePushLine(line, pushLine3, destination3);
		Layer newLayer2 = lplMove2.makeMoveOnCopyBoard(newLayer);

		assertTrue(newLayer2.contains('G',5)==MarbleColor.BLACK);
		assertTrue(newLayer2.contains('H',5)==MarbleColor.WHITE);
		assertTrue(newLayer2.contains('D',5)==MarbleColor.EMPTY);
		assertTrue(newLayer2.contains('I',5)==MarbleColor.WHITE);
	}

}
