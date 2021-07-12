package test.move;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import board.Layer;
import board.Marble.MarbleColor;
import formation.shape.Line;
import formation.shape.Line.HexagonCardinalDirections;
import graph.AbaloneGraph;
import move.generator.LinePushNodeMoveGenerator;
import move.representation.LinePushLine;
import move.representation.LinePushNode;
import move.representation.ShiftLine;
import move.representation.SideStep;
import move.representation.SimpleMove;
import graph.Node;

class MoveTest
{

	private static final Node INVALID_NODE = new Node('$' ,-10);
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
		
		Layer newLayer = simple.makeMove(layer);

		assertTrue(newLayer.contains('A', 3)==MarbleColor.BLACK);
		assertTrue(newLayer.contains('A', 2)==MarbleColor.EMPTY);

		node= AbaloneGraph.get().getVertex('F',7);
		layer.addBlack('F', 7);
		node2= AbaloneGraph.get().getVertex('F',6);
		simple= new SimpleMove(node, node2);
		Layer newLayer2 =simple.makeMove(layer);

		assertTrue(newLayer2.contains('F', 6)==MarbleColor.BLACK);
		assertTrue(newLayer2.contains('F', 7)==MarbleColor.EMPTY);


	}

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
		Layer newLayer =sL.makeMove(layer);

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
		Layer newLayer = sL.makeMove(layer);

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
		Layer newLayer =sL.makeMove(layer);

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
		Layer newLayer =sL.makeMove(layer);

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
		Layer newLayer =sL.makeMove(layer);

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
		Layer newLayer = sL.makeMove(layer);

		assertTrue(newLayer.contains('E',6)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('H', 9)==MarbleColor.BLACK);
	}


	@Test
	void Test_NorthEast_SideStep_Move()  {
		Layer layer = new Layer();
		Line line= new Line();

		Node node= AbaloneGraph.get().getVertex('C',3);
		layer.addBlack('C', 3);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('D',3);
		layer.addBlack('D', 3);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',3);
		layer.addBlack('E', 3);
		line.addToLine(node);

		SideStep sS= new SideStep(line, HexagonCardinalDirections.NORTHEAST);
		Layer newLayer = sS.makeMove(layer);

		assertTrue(newLayer.contains('C',2)==MarbleColor.BLACK);
		assertTrue(newLayer.contains('D',2)==MarbleColor.BLACK);
		assertTrue(newLayer.contains('E',2)==MarbleColor.BLACK);

		assertTrue(newLayer.contains('C',3)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('D',3)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('E',3)==MarbleColor.EMPTY);

	}

	@Test
	void Test_East_SideStep_Move()  {
		Layer layer = new Layer();
		Line line= new Line();

		Node node= AbaloneGraph.get().getVertex('B',2);
		layer.addBlack('B', 2);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('B',3);
		layer.addBlack('B', 3);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('B',4);
		layer.addBlack('B', 4);
		line.addToLine(node);

		SideStep sS= new SideStep(line, HexagonCardinalDirections.EAST);
		Layer newLayer = sS.makeMove(layer);

		assertTrue(newLayer.contains('C',2)==MarbleColor.BLACK);
		assertTrue(newLayer.contains('C',3)==MarbleColor.BLACK);
		assertTrue(newLayer.contains('C',4)==MarbleColor.BLACK);

		assertTrue(newLayer.contains('B',2)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('B',3)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('B',4)==MarbleColor.EMPTY);

	}

	@Test
	void Test_SouthEast_SideStep_Move()  {
		Layer layer = new Layer();

		Line line= new Line();

		Node node= AbaloneGraph.get().getVertex('C',3);
		layer.addBlack('C', 3);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('D',3);
		layer.addBlack('D', 3);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',3);
		layer.addBlack('E', 3);
		line.addToLine(node);

		SideStep sS= new SideStep(line, HexagonCardinalDirections.SOUTHEAST);
		Layer newLayer = sS.makeMove(layer);

		assertTrue(newLayer.contains('D',4)==MarbleColor.BLACK);
		assertTrue(newLayer.contains('E',4)==MarbleColor.BLACK);
		assertTrue(newLayer.contains('F',4)==MarbleColor.BLACK);

		assertTrue(newLayer.contains('C',3)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('D',3)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('E',3)==MarbleColor.EMPTY);

	}
	@Test
	void Test_SouthWest_SideStep_Move()  {
		Layer layer = new Layer();

		Line line= new Line();

		Node node= AbaloneGraph.get().getVertex('D',4);
		layer.addBlack('D', 4);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',5);
		layer.addBlack('E', 5);
		line.addToLine(node);

		SideStep sS= new SideStep(line, HexagonCardinalDirections.SOUTHWEST);
		Layer newLayer = sS.makeMove(layer);

		assertTrue(newLayer.contains('D',5)==MarbleColor.BLACK);
		assertTrue(newLayer.contains('E',6)==MarbleColor.BLACK);
		assertFalse(newLayer.contains('F',8)==MarbleColor.BLACK);

		assertTrue(newLayer.contains('D',4)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('E',5)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('C',5)==MarbleColor.EMPTY);

	}

	@Test
	void Test_West_SideStep_Move()  {
		Layer layer = new Layer();

		Line line= new Line();

		Node node= AbaloneGraph.get().getVertex('C',3);
		layer.addBlack('C', 3);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('C',4);
		layer.addBlack('C', 4);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('C',5);
		layer.addBlack('C', 5);
		line.addToLine(node);

		SideStep sS= new SideStep(line, HexagonCardinalDirections.WEST);
		Layer newLayer = sS.makeMove(layer);

		assertTrue(newLayer.contains('B',3)==MarbleColor.BLACK);
		assertTrue(newLayer.contains('B',4)==MarbleColor.BLACK);
		assertTrue(newLayer.contains('B',5)==MarbleColor.BLACK);

		assertTrue(newLayer.contains('C',3)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('C',4)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('C',5)==MarbleColor.EMPTY);

	}
	@Test
	void Test_NorthWest_SideStep_Move()  {
		Layer layer = new Layer();

		Line line= new Line();

		Node node= AbaloneGraph.get().getVertex('C',3);
		layer.addBlack('C', 3);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('D',3);
		layer.addBlack('D', 3);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',3);
		layer.addBlack('E', 3);
		line.addToLine(node);

		SideStep sS= new SideStep(line, HexagonCardinalDirections.NORTHWEST);
		Layer newLayer = sS.makeMove(layer);

		assertTrue(newLayer.contains('B',2)==MarbleColor.BLACK);
		assertTrue(newLayer.contains('C',2)==MarbleColor.BLACK);
		assertTrue(newLayer.contains('D',2)==MarbleColor.BLACK);

		assertTrue(newLayer.contains('C',3)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('D',3)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('E',3)==MarbleColor.EMPTY);

	}

	

	

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

		Layer newLayer = lpn.makeMove(layer);

		assertTrue(newLayer.contains('B',4)==MarbleColor.BLACK);
		assertTrue(newLayer.contains('A',4)==MarbleColor.WHITE);
		assertTrue(newLayer.contains('E',4)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('F',4)==MarbleColor.WHITE);

		LinePushNode lpn2= new LinePushNode(line, pushNode2,destination2);

		Layer newLayer2 =lpn2.makeMove(layer);

		assertTrue(newLayer2.contains('F',4)==MarbleColor.BLACK);
		assertTrue(newLayer2.contains('G',4)==MarbleColor.WHITE);
		assertTrue(newLayer2.contains('C',4)==MarbleColor.EMPTY);



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
		
		Layer newLayer = lpn.makeMove(layer);
		//May be an irrelevant test now
		//assertTrue(lpn.makeMove(layer));

		assertTrue(newLayer.contains('E',1)==MarbleColor.BLACK);

		assertTrue(newLayer.contains('B',1)==MarbleColor.EMPTY);

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
		
		Layer newLayer = lpn.makeMove(layer);

		assertTrue(newLayer.contains('E',3)==MarbleColor.WHITE);
		assertTrue(newLayer.contains('E',2)==MarbleColor.BLACK);
		assertTrue(newLayer.contains('E',6)==MarbleColor.EMPTY);


		LinePushNode lpn2= new LinePushNode(line, pushNode2,destination2);

		Layer newLayer2 =lpn2.makeMove(layer);

		assertTrue(newLayer2.contains('E',7)==MarbleColor.WHITE);
		assertTrue(newLayer2.contains('E',8)==MarbleColor.BLACK);
		assertTrue(newLayer2.contains('E',4)==MarbleColor.EMPTY);
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

		Layer newLayer = lpn.makeMove(layer);

		assertTrue(newLayer.contains('B',3)==MarbleColor.WHITE);
		assertTrue(newLayer.contains('A',2)==MarbleColor.BLACK);
		assertTrue(newLayer.contains('E',6)==MarbleColor.EMPTY);


		LinePushNode lpn2= new LinePushNode(line, pushNode2,destination2);

		Layer newLayer2 =lpn2.makeMove(layer);

		assertTrue(newLayer2.contains('F',7)==MarbleColor.WHITE);
		assertTrue(newLayer2.contains('G',8)==MarbleColor.BLACK);
		assertTrue(newLayer2.contains('C',4)==MarbleColor.EMPTY);
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

		Layer newLayer = lplMove.makeMove(layer);

		assertTrue(newLayer.contains('C',5)==MarbleColor.BLACK);
		assertTrue(newLayer.contains('A',5)==MarbleColor.WHITE);
		assertTrue(newLayer.contains('F',5)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('B',5)==MarbleColor.WHITE);

		LinePushLine lplMove2= new LinePushLine(line, pushLine3, destination3);
		Layer newLayer2 =lplMove2.makeMove(layer);

		assertTrue(newLayer2.contains('G',5)==MarbleColor.BLACK);
		assertTrue(newLayer2.contains('H',5)==MarbleColor.WHITE);
		assertTrue(newLayer2.contains('D',5)==MarbleColor.EMPTY);
		assertTrue(newLayer2.contains('I',5)==MarbleColor.WHITE);
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

		Layer newLayer =lplMove.makeMove(layer);

		assertTrue(newLayer.contains('E',3)==MarbleColor.BLACK);
		assertTrue(newLayer.contains('E',1)==MarbleColor.WHITE);
		assertTrue(newLayer.contains('E',6)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('E',2)==MarbleColor.WHITE);

		LinePushLine lplMove2= new LinePushLine(line, pushLine3, destination3);
		Layer newLayer2 =lplMove2.makeMove(layer);

		assertTrue(newLayer2.contains('E',7)==MarbleColor.BLACK);
		assertTrue(newLayer2.contains('E',9)==MarbleColor.WHITE);
		assertTrue(newLayer2.contains('E',4)==MarbleColor.EMPTY);
		assertTrue(newLayer2.contains('E',8)==MarbleColor.WHITE);
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

		
		Layer newLayer =lplMove.makeMove(layer);

		assertTrue(newLayer.contains('C',3)==MarbleColor.WHITE);
		assertTrue(newLayer.contains('A',1)==MarbleColor.BLACK);
		assertTrue(newLayer.contains('F',6)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('B',2)==MarbleColor.BLACK);

		LinePushLine lplMove2= new LinePushLine(line, pushLine3, destination3);
		Layer newLayer2 =lplMove2.makeMove(layer);

		assertTrue(newLayer2.contains('G',7)==MarbleColor.WHITE);
		assertTrue(newLayer2.contains('I',9)==MarbleColor.BLACK);
		assertTrue(newLayer2.contains('D',4)==MarbleColor.EMPTY);
		assertTrue(newLayer2.contains('H',8)==MarbleColor.BLACK);
	}
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

		Layer newLayer =lplMove.makeMove(layer);
//		assertTrue(lplMove.makeMove(layer));

		assertTrue(newLayer.contains('D',1)==MarbleColor.BLACK);
		assertTrue(newLayer.contains('E',1)==MarbleColor.WHITE);
		assertTrue(newLayer.contains('A',1)==MarbleColor.EMPTY);

	}
}
