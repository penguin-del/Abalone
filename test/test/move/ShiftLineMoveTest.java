package test.move;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

import board.Layer;
import board.Marble.MarbleColor;
import formation.shape.Line;
import graph.AbaloneGraph;
import graph.Node;
import move.representation.ShiftLine;

class ShiftLineMoveTest {

	@Test
	void Test_Horizontal_ShiftLine_Move_Right()  {
		Layer layer = new Layer();
		Line line= new Line();
		Node node= AbaloneGraph.get().getVertex('B',2);
		layer.addBlack('B', 2);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('C',2);
		layer.addBlack('C', 2);
		line.addToLine(node);


		node= AbaloneGraph.get().getVertex('D',2);

		ShiftLine sL= new ShiftLine(line, node);
		Layer newLayer= layer.getClone();

		sL.makeMoveOnOriginalBoard(newLayer);

		assertTrue(newLayer.contains('B', 2)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('D', 2)==MarbleColor.BLACK);
	}

	@Test
	void Test_Horizontal_ShiftLine_Move_Left()  {
		Layer layer = new Layer();
		Line line= new Line();


		Node node= AbaloneGraph.get().getVertex('B',2);
		layer.addBlack('B', 2);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('C',2);
		layer.addBlack('C', 2);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('D',2);
		layer.addBlack('D', 2);
		line.addToLine(node);


		node= AbaloneGraph.get().getVertex('A',2);

		ShiftLine sL= new ShiftLine(line, node);
		Layer newLayer= layer.getClone();

		sL.makeMoveOnOriginalBoard(newLayer);

		assertTrue(newLayer.contains('D',2)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('A', 2)==MarbleColor.BLACK);
	}

	@Test
	void Test_RightToLeft_ShiftLine_Move_Left()  {
		Layer layer = new Layer();
		Line line= new Line();


		Node node= AbaloneGraph.get().getVertex('C',2);
		layer.addBlack('C', 2);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('C',3);
		layer.addBlack('C', 3);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('C',4);
		layer.addBlack('C', 4);
		line.addToLine(node);


		node= AbaloneGraph.get().getVertex('C',5);

		ShiftLine sL= new ShiftLine(line, node);
		Layer newLayer= layer.getClone();

		sL.makeMoveOnOriginalBoard(newLayer);

		assertTrue(newLayer.contains('C',2) ==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('C',5)==MarbleColor.BLACK);
	}

	@Test
	void Test_RightToLeft_ShiftLine_Move_Right()  {
		Layer layer = new Layer();
		Line line= new Line();


		Node node= AbaloneGraph.get().getVertex('C',2);
		layer.addBlack('C', 2);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('C',3);
		layer.addBlack('C', 3);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('C',4);
		layer.addBlack('C', 4);
		line.addToLine(node);


		node= AbaloneGraph.get().getVertex('C',1);

		ShiftLine sL= new ShiftLine(line, node);
		Layer newLayer= layer.getClone();

		sL.makeMoveOnOriginalBoard(newLayer);

		assertTrue(newLayer.contains('C',4)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('C',1)==MarbleColor.BLACK);

	}
	@Test
	void Test_LeftToRight_ShiftLine_Move_Left()  {
		Layer layer = new Layer();
		Line line= new Line();


		Node node= AbaloneGraph.get().getVertex('E',6);
		layer.addBlack('E', 6);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('F',7);
		layer.addBlack('F', 7);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('G',8);
		layer.addBlack('G', 8);
		line.addToLine(node);


		node= AbaloneGraph.get().getVertex('D',5);

		ShiftLine sL= new ShiftLine(line, node);
		Layer newLayer= layer.getClone();

		sL.makeMoveOnOriginalBoard(newLayer);

		assertTrue(newLayer.contains('G', 8)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('D', 5)==MarbleColor.BLACK);

	}
	@Test
	void Test_LeftToRight_ShiftLine_Move_Right()  {
		Layer layer = new Layer();
		Line line= new Line();


		Node node= AbaloneGraph.get().getVertex('E',6);
		layer.addBlack('E', 6);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('F',7);
		layer.addBlack('F', 7);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('G',8);
		layer.addBlack('G', 8);
		line.addToLine(node);


		node= AbaloneGraph.get().getVertex('H',9);

		ShiftLine sL= new ShiftLine(line, node);
		Layer newLayer= layer.getClone();

		sL.makeMoveOnOriginalBoard(newLayer);

		assertTrue(newLayer.contains('E',6)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('H', 9)==MarbleColor.BLACK);
	}


	@Test
	void Test_ShiftLine_Move_OnCopy() {
		Layer layer = new Layer();
		Line line= new Line();


		Node node= AbaloneGraph.get().getVertex('B',2);
		layer.addBlack('B', 2);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('C',2);
		layer.addBlack('C', 2);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('D',2);
		layer.addBlack('D', 2);
		line.addToLine(node);


		node= AbaloneGraph.get().getVertex('A',2);

		ShiftLine sL= new ShiftLine(line, node);
	

		Layer newLayer = sL.makeMoveOnCopyBoard(layer);

		assertTrue(newLayer.contains('D',2)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('A', 2)==MarbleColor.BLACK);
	}


}
