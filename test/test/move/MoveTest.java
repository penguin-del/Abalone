package test.move;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import board.BoardGame;
import board.Layer;
import board.Marble.MarbleColor;
import formation.computer.LineComputer;
import formation.shape.Line;
import formation.shape.Line.HexagonCardinalDirections;
import graph.AbaloneGraph;
import move.generator.LinePushLineMoveGenerator;
import move.generator.LinePushNodeMoveGenerator;
import move.generator.MoveGenerator;
import move.generator.NonPushMoveGenerator;
import move.representation.LinePushLine;
import move.representation.LinePushNode;
import move.representation.Move;
import move.representation.ShiftLine;
import move.representation.SideStep;
import move.representation.SimpleMove;
import graph.Node;
import utilities.Equals;

class MoveTest
{


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
		Layer layer = Layer.getDefaultBoard();
		assertEquals(layer.contains('A', 1), MarbleColor.EMPTY);
		assertNotEquals(layer.contains('A', 1), MarbleColor.WHITE);
		assertNotEquals(layer.contains('A', 1), MarbleColor.BLACK);
	}

	@Test
	 void Test_Possible_Simple_Moves()  {
		Layer layer = new Layer();
		NonPushMoveGenerator mg= new NonPushMoveGenerator(layer);

		//creates empty nodes
//		Node node1= AbaloneGraph.get().getVertex('A',1);
		layer.addBlack('A', 1);
//		Node node2= AbaloneGraph.get().getVertex('A',2);
//		Node node3= AbaloneGraph.get().getVertex('B',2);
//		Node node4= AbaloneGraph.get().getVertex('B',1);

		//adds simplemoves to testList
		ArrayList<SimpleMove> testList = new ArrayList<SimpleMove>();
		SimpleMove simple= new SimpleMove(AbaloneGraph.get().getVertex('A',1), AbaloneGraph.get().getVertex('A',2));
		testList.add(simple);
		simple= new SimpleMove(AbaloneGraph.get().getVertex('A',1), AbaloneGraph.get().getVertex('B',1));
		testList.add(simple);
		simple= new SimpleMove(AbaloneGraph.get().getVertex('A',1), AbaloneGraph.get().getVertex('B',2));
		testList.add(simple);

		ArrayList<SimpleMove> moveList= mg.computeAllSimple(MarbleColor.BLACK);

		assertTrue(Equals.subsetEq(testList, moveList));
		assertTrue(moveList.contains(simple));

	}
	
	
	

	@Test
	 void Test_AllPossible_Simple_Moves()  {

		Layer layer = Layer.getDefaultBoard();
		NonPushMoveGenerator mg= new NonPushMoveGenerator(layer);
		ArrayList<SimpleMove> blackList = new ArrayList<SimpleMove>();
		ArrayList<SimpleMove> whiteList = new ArrayList<SimpleMove>();

		Node node= AbaloneGraph.get().getVertex('A',2);
		layer.addBlack('A', 2);
		Node node2= AbaloneGraph.get().getVertex('A',3);
		SimpleMove simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('A',2);
		layer.addBlack('A', 2);
		node2= AbaloneGraph.get().getVertex('B',3);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('B',2);
		layer.addBlack('B', 2);
		node2= AbaloneGraph.get().getVertex('B',3);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('C',3);
		layer.addBlack('C', 3);
		node2= AbaloneGraph.get().getVertex('B',3);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('C',3);
		layer.addBlack('C', 3);
		node2= AbaloneGraph.get().getVertex('C',4);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('C',3);
		layer.addBlack('C', 3);
		node2= AbaloneGraph.get().getVertex('D',4);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('D',3);
		layer.addBlack('D',3);
		node2= AbaloneGraph.get().getVertex('D',4);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('D',3);
		layer.addBlack('D', 3);
		node2= AbaloneGraph.get().getVertex('E',4);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('E',3);
		layer.addBlack('E', 3);
		node2= AbaloneGraph.get().getVertex('E',4);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('E',3);
		layer.addBlack('E', 3);
		node2= AbaloneGraph.get().getVertex('F',4);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('E',3);
		layer.addBlack('E',3);
		node2= AbaloneGraph.get().getVertex('F',3);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('E',2);
		layer.addBlack('E', 2);
		node2= AbaloneGraph.get().getVertex('F',3);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('F',2);
		layer.addBlack('F', 2);
		node2= AbaloneGraph.get().getVertex('F',3);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('F',2);
		layer.addBlack('F', 2);
		node2= AbaloneGraph.get().getVertex('G',3);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);





		node= AbaloneGraph.get().getVertex('D',8);
		layer.addWhite('D', 8);
		node2= AbaloneGraph.get().getVertex('C',7);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('D',8);
		layer.addWhite('D', 8);
		node2= AbaloneGraph.get().getVertex('D',7);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('E',8);
		layer.addWhite('E', 8);
		node2= AbaloneGraph.get().getVertex('D',7);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('E',7);
		layer.addWhite('E', 7);
		node2= AbaloneGraph.get().getVertex('D',7);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('E',7);
		layer.addWhite('E', 7);
		node2= AbaloneGraph.get().getVertex('D',6);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('E',7);
		layer.addWhite('E', 7);
		node2= AbaloneGraph.get().getVertex('E',6);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('F',7);
		layer.addWhite('F', 7);
		node2= AbaloneGraph.get().getVertex('E',6);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('F',7);
		layer.addWhite('F', 7);
		node2= AbaloneGraph.get().getVertex('F',6);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('G',7);
		layer.addWhite('G', 7);
		node2= AbaloneGraph.get().getVertex('F',6);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('G',7);
		layer.addWhite('G', 7);
		node2= AbaloneGraph.get().getVertex('G',6);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('G',7);
		layer.addWhite('G', 7);
		node2= AbaloneGraph.get().getVertex('H',7);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('H',8);
		layer.addWhite('H', 8);
		node2= AbaloneGraph.get().getVertex('H',7);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('I',8);
		layer.addWhite('I', 8);
		node2= AbaloneGraph.get().getVertex('H',7);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('I',8);
		layer.addWhite('I', 8);
		node2= AbaloneGraph.get().getVertex('I',7);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		//		ArrayList<SimpleMove> blackMoveList= mg.computeAllSimple(MarbleColor.BLACK);
		//		ArrayList<SimpleMove> whiteMoveList = mg.computeAllSimple(MarbleColor.WHITE);

		assertTrue(whiteList.size()==14);
		assertTrue(blackList.size()==14);



		ArrayList<SimpleMove> whiteMoveList = mg.computeAllSimple(MarbleColor.WHITE);

		ArrayList<SimpleMove> blackMoveList = mg.computeAllSimple(MarbleColor.BLACK);



		assertTrue(Equals.subsetEq(whiteMoveList, whiteList));
		assertTrue(Equals.subsetEq(blackMoveList, blackList));

	}

	@Test
	 void Test_Default_Board_Shift_Line_Moves() {
		Layer layer = Layer.getDefaultBoard();
		NonPushMoveGenerator mg= new NonPushMoveGenerator(layer);

		ArrayList<ShiftLine> shiftWhiteList = new ArrayList<ShiftLine>();

		ArrayList<ShiftLine> shiftBlackList = new ArrayList<ShiftLine>();

		Line line = new Line();
		Line line2= new Line();
		Line line3= new Line();
		Line line4 = new Line();
		Line line5= new Line();
		Line line6 = new Line();
		Line line7 = new Line();
		Line line8 = new Line();
		Line line9 = new Line();
		Line line10 = new Line();
		Line line11= new Line();
		Line line12= new Line();

		Line line13 = new Line();
		Line line14= new Line();
		Line line15= new Line();
		Line line16 = new Line();
		Line line17= new Line();
		Line line18 = new Line();
		Line line19 = new Line();
		Line line20 = new Line();
		Line line21 = new Line();
		Line line22 = new Line();
		Line line23= new Line();
		Line line24= new Line();

		Line line25 = new Line();
		Line line26= new Line();
		Line line27 = new Line();
		Line line28= new Line();
		Line line29 = new Line();
		Line line30= new Line();
		Line line31 = new Line();
		Line line32= new Line();

		Line line33 = new Line();
		Line line34= new Line();
		Line line35 = new Line();

		Line line37 = new Line();
		Line line38= new Line();
		Line line39 = new Line();





		Node node= AbaloneGraph.get().getVertex('A',1);
		layer.addBlack('A', 1);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('A',2);
		layer.addBlack('A', 2);
		line.addToLine(node);

		ShiftLine sL= new ShiftLine(line, AbaloneGraph.get().getVertex('A',3));
		shiftBlackList.add(sL);

		node= AbaloneGraph.get().getVertex('B',1);
		layer.addBlack('B', 1);
		line2.addToLine(node);
		node= AbaloneGraph.get().getVertex('B',2);
		layer.addBlack('B', 2);
		line2.addToLine(node);

		sL= new ShiftLine(line2, AbaloneGraph.get().getVertex('B',3));
		shiftBlackList.add(sL);

		node= AbaloneGraph.get().getVertex('C',2);
		layer.addBlack('C', 2);
		line3.addToLine(node);
		node= AbaloneGraph.get().getVertex('C',3);
		layer.addBlack('C', 3);
		line3.addToLine(node);

		sL= new ShiftLine(line3, AbaloneGraph.get().getVertex('C',4));
		shiftBlackList.add(sL);

		node= AbaloneGraph.get().getVertex('D',2);
		layer.addBlack('D', 2);
		line4.addToLine(node);
		node= AbaloneGraph.get().getVertex('D',3);
		layer.addBlack('D', 3);
		line4.addToLine(node);

		sL= new ShiftLine(line4, AbaloneGraph.get().getVertex('D',4));
		shiftBlackList.add(sL);

		node= AbaloneGraph.get().getVertex('E',2);
		layer.addBlack('E', 2);
		line12.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',3);
		layer.addBlack('E', 3);
		line12.addToLine(node);

		sL= new ShiftLine(line12, AbaloneGraph.get().getVertex('E',4));
		shiftBlackList.add(sL);

		node= AbaloneGraph.get().getVertex('B',2);
		layer.addBlack('B', 2);
		line5.addToLine(node);
		node= AbaloneGraph.get().getVertex('C',3);
		layer.addBlack('C', 3);
		line5.addToLine(node);

		sL= new ShiftLine(line5, AbaloneGraph.get().getVertex('D',4));
		shiftBlackList.add(sL);

		node= AbaloneGraph.get().getVertex('C',2);
		layer.addBlack('C', 2);
		line6.addToLine(node);
		node= AbaloneGraph.get().getVertex('D',3);
		layer.addBlack('D', 3);
		line6.addToLine(node);

		sL= new ShiftLine(line6, AbaloneGraph.get().getVertex('E',4));
		shiftBlackList.add(sL);

		node= AbaloneGraph.get().getVertex('D',2);
		layer.addBlack('D', 2);
		line7.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',3);
		layer.addBlack('E', 3);
		line7.addToLine(node);

		sL= new ShiftLine(line7, AbaloneGraph.get().getVertex('F',4));
		shiftBlackList.add(sL);

		node= AbaloneGraph.get().getVertex('D',1);
		layer.addBlack('D', 1);
		line8.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',2);
		layer.addBlack('E', 2);
		line8.addToLine(node);

		sL= new ShiftLine(line8, AbaloneGraph.get().getVertex('F',3));
		shiftBlackList.add(sL);

		node= AbaloneGraph.get().getVertex('E',1);
		layer.addBlack('E', 1);
		line9.addToLine(node);
		node= AbaloneGraph.get().getVertex('F',2);
		layer.addBlack('F', 2);
		line9.addToLine(node);

		sL= new ShiftLine(line9, AbaloneGraph.get().getVertex('G',3));
		shiftBlackList.add(sL);

		node= AbaloneGraph.get().getVertex('C',3);
		layer.addBlack('C', 3);
		line10.addToLine(node);
		node= AbaloneGraph.get().getVertex('D',3);
		layer.addBlack('D', 3);
		line10.addToLine(node);

		sL= new ShiftLine(line10, AbaloneGraph.get().getVertex('B',3));
		shiftBlackList.add(sL);

		node= AbaloneGraph.get().getVertex('D',3);
		layer.addBlack('D', 3);
		line11.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',3);
		layer.addBlack('E', 3);
		line11.addToLine(node);

		sL= new ShiftLine(line11, AbaloneGraph.get().getVertex('F',3));
		shiftBlackList.add(sL);



		//white marbles




		node= AbaloneGraph.get().getVertex('E',7);
		layer.addWhite('E', 7);
		line13.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',8);
		layer.addWhite('E', 8);
		line13.addToLine(node);

		sL= new ShiftLine(line13, AbaloneGraph.get().getVertex('E',6));
		shiftWhiteList.add(sL);

		node= AbaloneGraph.get().getVertex('F',7);
		layer.addWhite('F', 7);
		line14.addToLine(node);
		node= AbaloneGraph.get().getVertex('F',8);
		layer.addWhite('F', 8);
		line14.addToLine(node);

		sL= new ShiftLine(line14, AbaloneGraph.get().getVertex('F',6));
		shiftWhiteList.add(sL);

		node= AbaloneGraph.get().getVertex('G',7);
		layer.addWhite('G', 7);
		line15.addToLine(node);
		node= AbaloneGraph.get().getVertex('G',8);
		layer.addWhite('G', 8);
		line15.addToLine(node);

		sL= new ShiftLine(line15, AbaloneGraph.get().getVertex('G',6));
		shiftWhiteList.add(sL);

		node= AbaloneGraph.get().getVertex('H',8);
		layer.addWhite('H', 8);
		line16.addToLine(node);
		node= AbaloneGraph.get().getVertex('H',9);
		layer.addWhite('H', 9);
		line16.addToLine(node);

		sL= new ShiftLine(line16, AbaloneGraph.get().getVertex('H',7));
		shiftWhiteList.add(sL);

		node= AbaloneGraph.get().getVertex('I',8);
		layer.addWhite('I', 8);
		line17.addToLine(node);
		node= AbaloneGraph.get().getVertex('I',9);
		layer.addWhite('I', 9);
		line17.addToLine(node);

		sL= new ShiftLine(line17, AbaloneGraph.get().getVertex('I',7));
		shiftWhiteList.add(sL);

		node= AbaloneGraph.get().getVertex('D',8);
		layer.addWhite('D', 8);
		line18.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',9);
		layer.addWhite('E', 9);
		line18.addToLine(node);

		sL= new ShiftLine(line18, AbaloneGraph.get().getVertex('C',7));
		shiftWhiteList.add(sL);

		node= AbaloneGraph.get().getVertex('E',8);
		layer.addWhite('E', 8);
		line19.addToLine(node);
		node= AbaloneGraph.get().getVertex('F',9);
		layer.addWhite('F', 9);
		line19.addToLine(node);

		sL= new ShiftLine(line19, AbaloneGraph.get().getVertex('D',7));
		shiftWhiteList.add(sL);

		node= AbaloneGraph.get().getVertex('E',7);
		layer.addWhite('E', 7);
		line20.addToLine(node);
		node= AbaloneGraph.get().getVertex('F',8);
		layer.addWhite('F', 8);
		line20.addToLine(node);

		sL= new ShiftLine(line20, AbaloneGraph.get().getVertex('D',6));
		shiftWhiteList.add(sL);


		node= AbaloneGraph.get().getVertex('F',7);
		layer.addWhite('F', 7);
		line21.addToLine(node);
		node= AbaloneGraph.get().getVertex('G',8);
		layer.addWhite('G', 8);
		line21.addToLine(node);

		sL= new ShiftLine(line21, AbaloneGraph.get().getVertex('E',6));
		shiftWhiteList.add(sL);

		node= AbaloneGraph.get().getVertex('G',7);
		layer.addWhite('G', 7);
		line22.addToLine(node);
		node= AbaloneGraph.get().getVertex('H',8);
		layer.addWhite('H', 8);
		line22.addToLine(node);

		sL= new ShiftLine(line22, AbaloneGraph.get().getVertex('F',6));
		shiftWhiteList.add(sL);

		node= AbaloneGraph.get().getVertex('E',7);
		layer.addWhite('E', 7);
		line23.addToLine(node);
		node= AbaloneGraph.get().getVertex('F',7);
		layer.addWhite('F', 7);
		line23.addToLine(node);

		sL= new ShiftLine(line23, AbaloneGraph.get().getVertex('D',7));
		shiftWhiteList.add(sL);

		node= AbaloneGraph.get().getVertex('F',7);
		layer.addWhite('F', 7);
		line24.addToLine(node);
		node= AbaloneGraph.get().getVertex('G',7);
		layer.addWhite('G', 7);
		line24.addToLine(node);

		sL= new ShiftLine(line24, AbaloneGraph.get().getVertex('H',7));
		shiftWhiteList.add(sL);


		//lines of 3

		node= AbaloneGraph.get().getVertex('A',1);
		layer.addBlack('A', 1);
		line25.addToLine(node);
		node= AbaloneGraph.get().getVertex('B',2);
		layer.addBlack('B', 2);
		line25.addToLine(node);
		node= AbaloneGraph.get().getVertex('C',3);
		layer.addBlack('C', 3);
		line25.addToLine(node);

		sL= new ShiftLine(line25, AbaloneGraph.get().getVertex('D',4));
		shiftBlackList.add(sL);

		node= AbaloneGraph.get().getVertex('B',1);
		layer.addBlack('B', 1);
		line26.addToLine(node);
		node= AbaloneGraph.get().getVertex('C',2);
		layer.addBlack('C', 2);
		line26.addToLine(node);
		node= AbaloneGraph.get().getVertex('D',3);
		layer.addBlack('D', 3);
		line26.addToLine(node);

		sL= new ShiftLine(line26, AbaloneGraph.get().getVertex('E',4));
		shiftBlackList.add(sL);

		node= AbaloneGraph.get().getVertex('C',1);
		layer.addBlack('C', 1);
		line27.addToLine(node);
		node= AbaloneGraph.get().getVertex('D',2);
		layer.addBlack('D', 2);
		line27.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',3);
		layer.addBlack('E', 3);
		line27.addToLine(node);

		sL= new ShiftLine(line27, AbaloneGraph.get().getVertex('F',4));
		shiftBlackList.add(sL);

		node= AbaloneGraph.get().getVertex('C',1);
		layer.addBlack('C', 1);
		line37.addToLine(node);
		node= AbaloneGraph.get().getVertex('C',2);
		layer.addBlack('C', 2);
		line37.addToLine(node);
		node= AbaloneGraph.get().getVertex('C',3);
		layer.addBlack('C', 3);
		line37.addToLine(node);

		sL= new ShiftLine(line37, AbaloneGraph.get().getVertex('C',4));
		shiftBlackList.add(sL);

		node= AbaloneGraph.get().getVertex('D',1);
		layer.addBlack('D', 1);
		line38.addToLine(node);
		node= AbaloneGraph.get().getVertex('D',2);
		layer.addBlack('D', 2);
		line38.addToLine(node);
		node= AbaloneGraph.get().getVertex('D',3);
		layer.addBlack('D', 3);
		line38.addToLine(node);

		sL= new ShiftLine(line38, AbaloneGraph.get().getVertex('D',4));
		shiftBlackList.add(sL);

		node= AbaloneGraph.get().getVertex('E',1);
		layer.addBlack('E', 1);
		line39.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',2);
		layer.addBlack('E', 2);
		line39.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',3);
		layer.addBlack('E', 3);
		line39.addToLine(node);

		sL= new ShiftLine(line39, AbaloneGraph.get().getVertex('E',4));
		shiftBlackList.add(sL);



		node= AbaloneGraph.get().getVertex('C',3);
		layer.addBlack('C', 3);
		line28.addToLine(node);
		node= AbaloneGraph.get().getVertex('D',3);
		layer.addBlack('D', 3);
		line28.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',3);
		layer.addBlack('E', 3);
		line28.addToLine(node);

		sL= new ShiftLine(line28, AbaloneGraph.get().getVertex('B',3));
		shiftBlackList.add(sL);
		sL= new ShiftLine(line28, AbaloneGraph.get().getVertex('F',3));
		shiftBlackList.add(sL);


		node= AbaloneGraph.get().getVertex('E',7);
		layer.addWhite('E', 7);
		line29.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',8);
		layer.addWhite('E', 8);
		line29.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',9);
		layer.addWhite('E', 9);
		line29.addToLine(node);

		sL= new ShiftLine(line29, AbaloneGraph.get().getVertex('E',6));
		shiftWhiteList.add(sL);

		node= AbaloneGraph.get().getVertex('F',7);
		layer.addWhite('F', 7);
		line30.addToLine(node);
		node= AbaloneGraph.get().getVertex('F',8);
		layer.addWhite('F', 8);
		line30.addToLine(node);
		node= AbaloneGraph.get().getVertex('F',9);
		layer.addWhite('F', 9);
		line30.addToLine(node);

		sL= new ShiftLine(line30, AbaloneGraph.get().getVertex('F',6));
		shiftWhiteList.add(sL);

		node= AbaloneGraph.get().getVertex('G',7);
		layer.addWhite('G', 7);
		line31.addToLine(node);
		node= AbaloneGraph.get().getVertex('G',8);
		layer.addWhite('G', 8);
		line31.addToLine(node);
		node= AbaloneGraph.get().getVertex('G',9);
		layer.addWhite('G', 9);
		line31.addToLine(node);

		sL= new ShiftLine(line31, AbaloneGraph.get().getVertex('G',6));
		shiftWhiteList.add(sL);

		node= AbaloneGraph.get().getVertex('G',9);
		layer.addWhite('G', 9);
		line32.addToLine(node);
		node= AbaloneGraph.get().getVertex('F',8);
		layer.addWhite('F', 8);
		line32.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',7);
		layer.addWhite('E', 7);
		line32.addToLine(node);

		sL= new ShiftLine(line32, AbaloneGraph.get().getVertex('D',6));
		shiftWhiteList.add(sL);

		node= AbaloneGraph.get().getVertex('H',9);
		layer.addWhite('H', 9);
		line33.addToLine(node);
		node= AbaloneGraph.get().getVertex('G',8);
		layer.addWhite('G', 8);
		line33.addToLine(node);
		node= AbaloneGraph.get().getVertex('F',7);
		layer.addWhite('F', 7);
		line33.addToLine(node);

		sL= new ShiftLine(line33, AbaloneGraph.get().getVertex('E',6));
		shiftWhiteList.add(sL);

		node= AbaloneGraph.get().getVertex('I',9);
		layer.addWhite('I', 9);
		line34.addToLine(node);
		node= AbaloneGraph.get().getVertex('H',8);
		layer.addWhite('H', 8);
		line34.addToLine(node);
		node= AbaloneGraph.get().getVertex('G',7);
		layer.addWhite('G', 7);
		line34.addToLine(node);

		sL= new ShiftLine(line34, AbaloneGraph.get().getVertex('F',6));
		shiftWhiteList.add(sL);

		node= AbaloneGraph.get().getVertex('E',7);
		layer.addWhite('E', 7);
		line35.addToLine(node);
		node= AbaloneGraph.get().getVertex('F',7);
		layer.addWhite('F', 7);
		line35.addToLine(node);
		node= AbaloneGraph.get().getVertex('G',7);
		layer.addWhite('G', 7);
		line35.addToLine(node);

		sL= new ShiftLine(line35, AbaloneGraph.get().getVertex('D',7));
		shiftWhiteList.add(sL);
		sL= new ShiftLine(line35, AbaloneGraph.get().getVertex('H',7));
		shiftWhiteList.add(sL);


		assertTrue(Equals.subset(mg.computeAllShifts(MarbleColor.BLACK), shiftBlackList));

		assertTrue(Equals.subset(mg.computeAllShifts(MarbleColor.WHITE), shiftWhiteList));

	}




	@Test
	 void Test_Compute_NorthEast_Move()  {
		Layer layer = new Layer();
		NonPushMoveGenerator mg= new NonPushMoveGenerator(layer);
		LineComputer lc= new LineComputer(layer);

		Line line= new Line();

		Node node= AbaloneGraph.get().getVertex('E',3);
		layer.addBlack('E', 3);
		line.addToLine(node);

		node= AbaloneGraph.get().getVertex('F',4);
		layer.addBlack('F', 4);
		line.addToLine(node);

		node= AbaloneGraph.get().getVertex('G',5);
		layer.addBlack('G', 5);
		line.addToLine(node);


		ArrayList<Line> lineList=lc.getLines(3, MarbleColor.BLACK);
		Line lines= lineList.get(0);

		SideStep sS= new SideStep(line, HexagonCardinalDirections.NORTHEAST);


		assertEquals(mg.computeNorthEastSideStep(lines),sS);

	}
	@Test
	 void Test_Compute_East_Move()  {
		Layer layer = new Layer();
		NonPushMoveGenerator mg= new NonPushMoveGenerator(layer);
		LineComputer lc= new LineComputer(layer);

		Line line= new Line();

		Node node= AbaloneGraph.get().getVertex('D',3);
		layer.addBlack('D', 3);
		line.addToLine(node);

		node= AbaloneGraph.get().getVertex('E',4);
		layer.addBlack('E', 4);
		line.addToLine(node);

		node= AbaloneGraph.get().getVertex('F',5);
		layer.addBlack('F', 5);
		line.addToLine(node);


		ArrayList<Line> lineList=lc.getLines(3,MarbleColor.BLACK);
		Line lines= lineList.get(0);

		SideStep sS= new SideStep(line, HexagonCardinalDirections.EAST);
		assertEquals(mg.computeEastSideStep(lines),sS);
	}
	@Test
	 void Test_Compute_SouthEast_Move()  {
		Layer layer = new Layer();
		NonPushMoveGenerator mg= new NonPushMoveGenerator(layer);
		LineComputer lc= new LineComputer(layer);

		Line line= new Line();

		Node node= AbaloneGraph.get().getVertex('D',3);
		layer.addBlack('D', 3);
		line.addToLine(node);

		node= AbaloneGraph.get().getVertex('E',4);
		layer.addBlack('E', 4);
		line.addToLine(node);

		node= AbaloneGraph.get().getVertex('F',5);
		layer.addBlack('F', 5);
		line.addToLine(node);


		ArrayList<Line> lineList=lc.getLines(3,MarbleColor.BLACK);
		Line lines= lineList.get(0);

		assertEquals(mg.computeSouthEastSideStep(lines),null);
	}

	@Test
	 void Test_Compute_SouthWest_Move()  {
		Layer layer = new Layer();
		NonPushMoveGenerator mg= new NonPushMoveGenerator(layer);
		LineComputer lc= new LineComputer(layer);

		Line line= new Line();

		Node node= AbaloneGraph.get().getVertex('D',3);
		layer.addBlack('D', 3);
		line.addToLine(node);

		node= AbaloneGraph.get().getVertex('E',4);
		layer.addBlack('E', 4);
		line.addToLine(node);

		node= AbaloneGraph.get().getVertex('F',5);
		layer.addBlack('F', 5);
		line.addToLine(node);


		ArrayList<Line> lineList=lc.getLines(3,MarbleColor.BLACK);
		Line lines= lineList.get(0);

		SideStep sS= new SideStep(line, HexagonCardinalDirections.SOUTHWEST);
		assertEquals(mg.computeSouthWestSideStep(lines),sS);
	}

	@Test
	 void Test_Compute_West_Move()  {
		Layer layer = new Layer();
		NonPushMoveGenerator mg= new NonPushMoveGenerator(layer);
		LineComputer lc= new LineComputer(layer);

		Line line= new Line();

		Node node= AbaloneGraph.get().getVertex('D',3);
		layer.addBlack('D', 3);
		line.addToLine(node);

		node= AbaloneGraph.get().getVertex('E',4);
		layer.addBlack('E', 4);
		line.addToLine(node);

		node= AbaloneGraph.get().getVertex('F',5);
		layer.addBlack('F', 5);
		line.addToLine(node);


		ArrayList<Line> lineList=lc.getLines(3,MarbleColor.BLACK);
		Line lines= lineList.get(0);

		SideStep sS= new SideStep(line, HexagonCardinalDirections.WEST);
		assertEquals(mg.computeWestSideStep(lines),sS);
	}

	@Test
	 void Test_Compute_NorthWest_Move()  {
		Layer layer = new Layer();
		NonPushMoveGenerator mg= new NonPushMoveGenerator(layer);
		LineComputer lc= new LineComputer(layer);

		Line line = new Line();



		Node node= AbaloneGraph.get().getVertex('D',3);
		layer.addBlack('D', 3);
		line.addToLine(node);

		node= AbaloneGraph.get().getVertex('E',4);
		layer.addBlack('E', 4);
		line.addToLine(node);

		node= AbaloneGraph.get().getVertex('F',5);
		layer.addBlack('F', 5);
		line.addToLine(node);


		ArrayList<Line> lineList=lc.getLines(3,MarbleColor.BLACK);
		Line lines= lineList.get(0);


		assertEquals(mg.computeNorthWestSideStep(lines),null);

	}

	@Test
	 void Test_Compute_SideStep()  {
		Layer layer = new Layer();
		NonPushMoveGenerator mg= new NonPushMoveGenerator(layer);
		LineComputer lc= new LineComputer(layer);
		
		Line line= new Line();
		Line line2= new Line();


		Node node= AbaloneGraph.get().getVertex('D',3);
		layer.addBlack('D', 3);
		line.addToLine(node);
		line2.addToLine(node);


		node= AbaloneGraph.get().getVertex('E',4);
		layer.addBlack('E', 4);
		line.addToLine(node);
		line2.addToLine(node);


		node= AbaloneGraph.get().getVertex('F',5);
		layer.addBlack('F', 5);
		line.addToLine(node);


		ArrayList<Line> blackLineList3 = lc.getLines(3,MarbleColor.BLACK);
		ArrayList<Line> blackLineList2 = lc.getLines(2,MarbleColor.BLACK);




		ArrayList<SideStep> blackSideList3= new ArrayList<SideStep>();

		ArrayList<SideStep> blackSideList2 = new ArrayList<SideStep>();





		SideStep sS= new SideStep(line, HexagonCardinalDirections.NORTHEAST);
		blackSideList3.add(sS);

		sS= new SideStep(line, HexagonCardinalDirections.EAST);
		blackSideList3.add(sS);

		sS= new SideStep(line, HexagonCardinalDirections.SOUTHWEST);
		blackSideList3.add(sS);

		sS= new SideStep(line, HexagonCardinalDirections.WEST);
		blackSideList3.add(sS);


		sS= new SideStep(line2, HexagonCardinalDirections.NORTHEAST);
		blackSideList2.add(sS);

		sS= new SideStep(line2, HexagonCardinalDirections.EAST);
		blackSideList2.add(sS);

		sS= new SideStep(line2, HexagonCardinalDirections.SOUTHWEST);
		blackSideList2.add(sS);

		sS= new SideStep(line2, HexagonCardinalDirections.WEST);
		blackSideList2.add(sS);


		assertEquals(mg.computeSideStep(blackLineList3.get(0)), blackSideList3);
		assertEquals(mg.computeSideStep(blackLineList2.get(0)),blackSideList2);
	}




	@Test
	 void Test_Compute_All_Default_SideStep()  {
		Layer layer = Layer.getDefaultBoard();
		NonPushMoveGenerator mg= new NonPushMoveGenerator(layer);

		ArrayList<SideStep> blackSideList = new ArrayList<SideStep>();
		ArrayList<SideStep> whiteSideList = new ArrayList<SideStep>();

		Line line = new Line();
		Line line2= new Line();
		Line line3= new Line();
		Line line4 = new Line();
		Line line5= new Line();

		Line line11= new Line();

		Line line6 = new Line();
		Line line7 = new Line();
		Line line8 = new Line();
		Line line9 = new Line();
		Line line10 = new Line();

		Line line12= new Line();

		Line line13 = new Line();
		Line line14= new Line();


		Node node= AbaloneGraph.get().getVertex('A',2);
		layer.addBlack('A', 2);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('B',2);
		layer.addBlack('B', 2);
		line.addToLine(node);

		SideStep sS= new SideStep(line, HexagonCardinalDirections.SOUTHWEST);
		blackSideList.add(sS);

		node= AbaloneGraph.get().getVertex('B',2);
		layer.addBlack('B', 2);
		line4.addToLine(node);
		node= AbaloneGraph.get().getVertex('C',3);
		layer.addBlack('C', 3);
		line4.addToLine(node);

		sS= new SideStep(line4, HexagonCardinalDirections.SOUTHWEST);
		blackSideList.add(sS);

		node= AbaloneGraph.get().getVertex('E',2);
		layer.addBlack('E', 2);
		line2.addToLine(node);
		node= AbaloneGraph.get().getVertex('F',2);
		layer.addBlack('F', 2);
		line2.addToLine(node);

		sS= new SideStep(line2, HexagonCardinalDirections.SOUTHEAST);
		blackSideList.add(sS);

		node= AbaloneGraph.get().getVertex('E',2);
		layer.addBlack('E', 2);
		line5.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',3);
		layer.addBlack('E', 3);
		line5.addToLine(node);

		sS= new SideStep(line5, HexagonCardinalDirections.SOUTHEAST);
		blackSideList.add(sS);


		node= AbaloneGraph.get().getVertex('C',3);
		layer.addBlack('C', 3);
		line3.addToLine(node);
		node= AbaloneGraph.get().getVertex('D',3);
		layer.addBlack('D', 3);
		line3.addToLine(node);


		sS= new SideStep(line3, HexagonCardinalDirections.SOUTHEAST);
		blackSideList.add(sS);
		sS= new SideStep(line3, HexagonCardinalDirections.SOUTHWEST);
		blackSideList.add(sS);


		node= AbaloneGraph.get().getVertex('D',3);
		layer.addBlack('D', 3);
		line11.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',3);
		layer.addBlack('E', 3);
		line11.addToLine(node);


		sS= new SideStep(line11, HexagonCardinalDirections.SOUTHEAST);
		blackSideList.add(sS);
		sS= new SideStep(line11, HexagonCardinalDirections.SOUTHWEST);
		blackSideList.add(sS);



		//white Marbles


		node= AbaloneGraph.get().getVertex('E',7);
		layer.addWhite('E', 7);
		line8.addToLine(node);
		node= AbaloneGraph.get().getVertex('F',7);
		layer.addWhite('F', 7);
		line8.addToLine(node);

		sS= new SideStep(line8, HexagonCardinalDirections.NORTHEAST);
		whiteSideList.add(sS);
		sS= new SideStep(line8, HexagonCardinalDirections.NORTHWEST);
		whiteSideList.add(sS);


		node= AbaloneGraph.get().getVertex('E',7);
		layer.addWhite('E', 7);
		line9.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',8);
		layer.addWhite('E', 8);
		line9.addToLine(node);

		sS= new SideStep(line9, HexagonCardinalDirections.NORTHWEST);
		whiteSideList.add(sS);

		node= AbaloneGraph.get().getVertex('F',7);
		layer.addWhite('F', 7);
		line12.addToLine(node);
		node= AbaloneGraph.get().getVertex('G',7);
		layer.addWhite('G', 7);
		line12.addToLine(node);

		sS= new SideStep(line12, HexagonCardinalDirections.NORTHEAST);
		whiteSideList.add(sS);
		sS= new SideStep(line12, HexagonCardinalDirections.NORTHWEST);
		whiteSideList.add(sS);

		node= AbaloneGraph.get().getVertex('G',7);
		layer.addBlack('G', 7);
		line10.addToLine(node);
		node= AbaloneGraph.get().getVertex('H',8);
		layer.addBlack('H', 8);
		line10.addToLine(node);

		sS= new SideStep(line10, HexagonCardinalDirections.NORTHEAST);
		whiteSideList.add(sS);

		node= AbaloneGraph.get().getVertex('D',8);
		layer.addWhite('D', 8);
		line6.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',8);
		layer.addWhite('E', 8);
		line6.addToLine(node);

		sS= new SideStep(line6, HexagonCardinalDirections.NORTHWEST);
		whiteSideList.add(sS);

		node= AbaloneGraph.get().getVertex('H',8);
		layer.addWhite('H', 8);
		line7.addToLine(node);
		node= AbaloneGraph.get().getVertex('I',8);
		layer.addWhite('I', 8);
		line7.addToLine(node);

		sS= new SideStep(line7, HexagonCardinalDirections.NORTHEAST);
		whiteSideList.add(sS);



		node= AbaloneGraph.get().getVertex('C',3);
		layer.addBlack('C', 3);
		line13.addToLine(node);

		node= AbaloneGraph.get().getVertex('D',3);
		layer.addBlack('D', 3);
		line13.addToLine(node);

		node= AbaloneGraph.get().getVertex('E',3);
		layer.addBlack('E', 3);
		line13.addToLine(node);

		sS= new SideStep(line13, HexagonCardinalDirections.SOUTHEAST);
		blackSideList.add(sS);
		sS= new SideStep(line13, HexagonCardinalDirections.SOUTHWEST);
		blackSideList.add(sS);

		node= AbaloneGraph.get().getVertex('E',7);
		layer.addWhite('E', 7);
		line14.addToLine(node);

		node= AbaloneGraph.get().getVertex('F',7);
		layer.addWhite('F', 7);
		line14.addToLine(node);

		node= AbaloneGraph.get().getVertex('G',7);
		layer.addWhite('G', 7);
		line14.addToLine(node);

		sS= new SideStep(line14, HexagonCardinalDirections.NORTHEAST);
		whiteSideList.add(sS);
		sS= new SideStep(line14, HexagonCardinalDirections.NORTHWEST);
		whiteSideList.add(sS);

		//		
		//		ArrayList<SideStep> whiteList = mg.computeAllSideSteps(MarbleColor.WHITE);
		//		
		//		ArrayList<SideStep> blackSideStepList = mg.computeAllSideSteps(MarbleColor.BLACK);


		assertTrue(Equals.subsetEq(mg.computeAllSideSteps(MarbleColor.WHITE), whiteSideList));

		assertTrue(Equals.subsetEq(mg.computeAllSideSteps(MarbleColor.BLACK), blackSideList));

	}


	@Test
	void Test_Moving_Simple_Move()  {
		Layer layer = Layer.getDefaultBoard();

		Node node= AbaloneGraph.get().getVertex('A',2);
		layer.addBlack('A', 2);
		Node node2= AbaloneGraph.get().getVertex('A',3);
		SimpleMove simple= new SimpleMove(node, node2);
		simple.makeMove(layer);

		assertTrue(layer.contains('A', 3)==MarbleColor.BLACK);
		assertTrue(layer.contains('A', 2)==MarbleColor.EMPTY);

		node= AbaloneGraph.get().getVertex('F',7);
		layer.addBlack('F', 7);
		node2= AbaloneGraph.get().getVertex('F',6);
		simple= new SimpleMove(node, node2);
		simple.makeMove(layer);

		assertTrue(layer.contains('F', 6)==MarbleColor.BLACK);
		assertTrue(layer.contains('F', 7)==MarbleColor.EMPTY);


	}

	@Test
	void Test_Horizontal_ShiftLine_Move_Right()  {
		Layer layer = Layer.getDefaultBoard();
		Line line= new Line();
		Node node= AbaloneGraph.get().getVertex('B',2);
		layer.addBlack('B', 2);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('C',2);
		layer.addBlack('C', 2);
		line.addToLine(node);


		node= AbaloneGraph.get().getVertex('D',2);

		ShiftLine sL= new ShiftLine(line, node);
		sL.makeMove(layer);

		assertTrue(layer.contains('B', 2)==MarbleColor.EMPTY);
		assertTrue(layer.contains('D', 2)==MarbleColor.BLACK);




	}

	@Test
	void Test_Horizontal_ShiftLine_Move_Left()  {
		Layer layer = Layer.getDefaultBoard();
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
		sL.makeMove(layer);

		assertTrue(layer.contains('D',2)==MarbleColor.EMPTY);
		assertTrue(layer.contains('A', 2)==MarbleColor.BLACK);
	}

	@Test
	void Test_RightToLeft_ShiftLine_Move_Left()  {
		Layer layer = Layer.getDefaultBoard();
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
		sL.makeMove(layer);

		assertTrue(layer.contains('C',2) ==MarbleColor.EMPTY);
		assertTrue(layer.contains('C',5)==MarbleColor.BLACK);
	}

	@Test
	void Test_RightToLeft_ShiftLine_Move_Right()  {
		Layer layer = Layer.getDefaultBoard();
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
		sL.makeMove(layer);

		assertTrue(layer.contains('C',4)==MarbleColor.EMPTY);
		assertTrue(layer.contains('C',1)==MarbleColor.BLACK);

	}
	@Test
	void Test_LeftToRight_ShiftLine_Move_Left()  {
		Layer layer = Layer.getDefaultBoard();
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
		sL.makeMove(layer);

		assertTrue(layer.contains('G', 8)==MarbleColor.EMPTY);
		assertTrue(layer.contains('D', 5)==MarbleColor.BLACK);

	}
	@Test
	void Test_LeftToRight_ShiftLine_Move_Right()  {
		Layer layer = Layer.getDefaultBoard();
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
		sL.makeMove(layer);

		assertTrue(layer.contains('E',6)==MarbleColor.EMPTY);
		assertTrue(layer.contains('H', 9)==MarbleColor.BLACK);
	}


	@Test
	void Test_NorthEast_SideStep_Move()  {
		Layer layer = Layer.getDefaultBoard();
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
		sS.makeMove(layer);

		assertTrue(layer.contains('C',2)==MarbleColor.BLACK);
		assertTrue(layer.contains('D',2)==MarbleColor.BLACK);
		assertTrue(layer.contains('E',2)==MarbleColor.BLACK);

		assertTrue(layer.contains('C',3)==MarbleColor.EMPTY);
		assertTrue(layer.contains('D',3)==MarbleColor.EMPTY);
		assertTrue(layer.contains('E',3)==MarbleColor.EMPTY);

	}

	@Test
	void Test_East_SideStep_Move()  {
		Layer layer = Layer.getDefaultBoard();
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
		sS.makeMove(layer);

		assertTrue(layer.contains('C',2)==MarbleColor.BLACK);
		assertTrue(layer.contains('C',3)==MarbleColor.BLACK);
		assertTrue(layer.contains('C',4)==MarbleColor.BLACK);

		assertTrue(layer.contains('B',2)==MarbleColor.EMPTY);
		assertTrue(layer.contains('B',3)==MarbleColor.EMPTY);
		assertTrue(layer.contains('B',4)==MarbleColor.EMPTY);

	}

	@Test
	void Test_SouthEast_SideStep_Move()  {
		Layer layer = Layer.getDefaultBoard();

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
		sS.makeMove(layer);

		assertTrue(layer.contains('D',4)==MarbleColor.BLACK);
		assertTrue(layer.contains('E',4)==MarbleColor.BLACK);
		assertTrue(layer.contains('F',4)==MarbleColor.BLACK);

		assertTrue(layer.contains('C',3)==MarbleColor.EMPTY);
		assertTrue(layer.contains('D',3)==MarbleColor.EMPTY);
		assertTrue(layer.contains('E',3)==MarbleColor.EMPTY);

	}
	@Test
	void Test_SouthWest_SideStep_Move()  {
		Layer layer = Layer.getDefaultBoard();

		Line line= new Line();

		Node node= AbaloneGraph.get().getVertex('D',4);
		layer.addBlack('D', 4);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',5);
		layer.addBlack('E', 5);
		line.addToLine(node);

		SideStep sS= new SideStep(line, HexagonCardinalDirections.SOUTHWEST);
		sS.makeMove(layer);

		assertTrue(layer.contains('D',5)==MarbleColor.BLACK);
		assertTrue(layer.contains('E',6)==MarbleColor.BLACK);
		assertFalse(layer.contains('F',8)==MarbleColor.BLACK);

		assertTrue(layer.contains('D',4)==MarbleColor.EMPTY);
		assertTrue(layer.contains('E',5)==MarbleColor.EMPTY);
		assertTrue(layer.contains('C',5)==MarbleColor.EMPTY);

	}

	@Test
	void Test_West_SideStep_Move()  {
		Layer layer = Layer.getDefaultBoard();

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
		sS.makeMove(layer);

		assertTrue(layer.contains('B',3)==MarbleColor.BLACK);
		assertTrue(layer.contains('B',4)==MarbleColor.BLACK);
		assertTrue(layer.contains('B',5)==MarbleColor.BLACK);

		assertTrue(layer.contains('C',3)==MarbleColor.EMPTY);
		assertTrue(layer.contains('C',4)==MarbleColor.EMPTY);
		assertTrue(layer.contains('C',5)==MarbleColor.EMPTY);

	}
	@Test
	void Test_NorthWest_SideStep_Move()  {
		Layer layer = Layer.getDefaultBoard();

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
		sS.makeMove(layer);

		assertTrue(layer.contains('B',2)==MarbleColor.BLACK);
		assertTrue(layer.contains('C',2)==MarbleColor.BLACK);
		assertTrue(layer.contains('D',2)==MarbleColor.BLACK);

		assertTrue(layer.contains('C',3)==MarbleColor.EMPTY);
		assertTrue(layer.contains('D',3)==MarbleColor.EMPTY);
		assertTrue(layer.contains('E',3)==MarbleColor.EMPTY);

	}

	@Test
	void Test_Compute_Line_Push_Node_Horizontal()  {
		Layer layer = Layer.getDefaultBoard();
		Line line= new Line();
		Line line2 = new Line();
		Line line3= new Line();

		LinePushNodeMoveGenerator pmg= new LinePushNodeMoveGenerator(layer);


		Node node= AbaloneGraph.get().getVertex('C',4);
		layer.addBlack('C', 4);
		line.addToLine(node);
		line2.addToLine(node);

		node= AbaloneGraph.get().getVertex('D',4);
		layer.addBlack('D', 4);
		line.addToLine(node);
		line2.addToLine(node);
		line3.addToLine(node);

		node= AbaloneGraph.get().getVertex('E',4);
		layer.addBlack('E', 4);
		line.addToLine(node);
		line3.addToLine(node);

		ArrayList<LinePushNode> linePushTest = new ArrayList<LinePushNode>();


		Node pushNode= AbaloneGraph.get().getVertex('B',4);
		Node pushNode2= AbaloneGraph.get().getVertex('F',4);

		Node destination= AbaloneGraph.get().getVertex('A',4);
		Node destination2= AbaloneGraph.get().getVertex('G',4);

		layer.remove('A', 4);
		layer.remove('G', 4);
		
		layer.addWhite('B', 4);
		layer.addWhite('F', 4);


		ArrayList<LinePushNode> pushList = pmg.computeAllLinePushNodes(MarbleColor.BLACK);

		LinePushNode lpn= new LinePushNode(line, pushNode, destination);
		linePushTest.add(lpn);

		LinePushNode lpn2= new LinePushNode(line, pushNode2,destination2);
		linePushTest.add(lpn2);

		lpn= new LinePushNode(line2, pushNode, destination);
		linePushTest.add(lpn);

		lpn = new LinePushNode(line3, pushNode2, destination2);
		linePushTest.add(lpn);

		assertTrue(Equals.subsetEq(pushList, linePushTest));


	}


	@Test
	void Test_Compute_Line_Push_Node_RightToLeft()  {
		Layer layer = Layer.getDefaultBoard();
		Line line= new Line();

		LinePushNodeMoveGenerator pmg= new LinePushNodeMoveGenerator(layer);


		Node node= AbaloneGraph.get().getVertex('D',3);
		layer.addWhite('D', 3);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('D',4);
		layer.addWhite('D', 4);
		line.addToLine(node);



		ArrayList<LinePushNode> linePushTest = new ArrayList<LinePushNode>();

		Node destination= AbaloneGraph.get().getVertex('D',1);
		Node destination2= AbaloneGraph.get().getVertex('D',6);

		layer.remove('D', 1);
		layer.remove('D', 6);


		Node pushNode= AbaloneGraph.get().getVertex('D',2);
		layer.addBlack('D', 2);
		LinePushNode lpn= new LinePushNode(line, pushNode,destination);
		linePushTest.add(lpn);

		Node pushNode2= AbaloneGraph.get().getVertex('D',5);
		layer.addBlack('D', 5);
		lpn= new LinePushNode(line, pushNode2,destination2);
		linePushTest.add(lpn);


		ArrayList<LinePushNode> pushList = pmg.computeAllLinePushNodes(MarbleColor.WHITE);



		assertTrue(Equals.subsetEq(pushList, linePushTest));

	}

	@Test
	void Test_Compute_Line_Push_Node_LeftToRight()  {
		Layer layer = Layer.getDefaultBoard();
		Line line= new Line();
		Line line2= new Line();
		Line line3= new Line();

		LinePushNodeMoveGenerator pmg= new LinePushNodeMoveGenerator(layer);

		Node node= AbaloneGraph.get().getVertex('C',4);
		layer.addWhite('C', 4);
		line.addToLine(node);
		line2.addToLine(node);

		node= AbaloneGraph.get().getVertex('D',5);
		layer.addWhite('D', 5);
		line.addToLine(node);
		line2.addToLine(node);
		line3.addToLine(node);

		node= AbaloneGraph.get().getVertex('E',6);
		layer.addWhite('E', 6);
		line.addToLine(node);
		line3.addToLine(node);

		ArrayList<LinePushNode> linePushTest= new ArrayList<LinePushNode>();

		Node destination= AbaloneGraph.get().getVertex('A',2);
		Node destination2= AbaloneGraph.get().getVertex('G',8);

		Node pushNode= AbaloneGraph.get().getVertex('B',3);
		layer.addBlack('B', 3);
		LinePushNode lpn= new LinePushNode(line, pushNode,destination);
		linePushTest.add(lpn);

		Node pushNode2= AbaloneGraph.get().getVertex('F',7);
		layer.addBlack('F', 7);
		LinePushNode lpn2= new LinePushNode(line, pushNode2,destination2);
		linePushTest.add(lpn2);


		lpn= new LinePushNode(line2, pushNode, destination);
		linePushTest.add(lpn);

		lpn = new LinePushNode(line3, pushNode2, destination2);
		linePushTest.add(lpn);

		layer.remove('A', 2);
		layer.remove('G', 8);

		ArrayList<LinePushNode> pushList = pmg.computeAllLinePushNodes(MarbleColor.WHITE);

		assertTrue(Equals.subsetEq(pushList, linePushTest));

	}

	@Test
	void Test_Compute_Line_Push_Node_Horizontal_Off_Board()  {
		Layer layer = Layer.getDefaultBoard();
		Line line= new Line();
		Line line2= new Line();
		Line line3 = new Line();
		LinePushNodeMoveGenerator pmg= new LinePushNodeMoveGenerator(layer);


		Node node= AbaloneGraph.get().getVertex('B',1);
		layer.addBlack('B', 1);
		line.addToLine(node);
		line2.addToLine(node);

		node= AbaloneGraph.get().getVertex('C',1);
		layer.addBlack('C', 1);
		line.addToLine(node);
		line2.addToLine(node);
		line3.addToLine(node);

		node= AbaloneGraph.get().getVertex('D',1);
		layer.addBlack('D', 1);
		line.addToLine(node);
		line3.addToLine(node);


		ArrayList<LinePushNode> linePushTest = new ArrayList<LinePushNode>();


		Node pushNode= AbaloneGraph.get().getVertex('A',1);
		Node pushNode2= AbaloneGraph.get().getVertex('E',1);

		layer.addWhite('A', 1);
		layer.addWhite('E', 1);

		Node destination= AbaloneGraph.get().getVertex('@',1);
		Node destination2= AbaloneGraph.get().getVertex('F',1);


		graph.changeValue(destination, null);
		graph.changeValue(destination2, null);


		ArrayList<LinePushNode> pushList = pmg.computeAllLinePushNodes(MarbleColor.BLACK);


		LinePushNode lpn= new LinePushNode(line, pushNode,destination);
		linePushTest.add(lpn);

		LinePushNode lpn2= new LinePushNode(line, pushNode2,destination2);
		linePushTest.add(lpn2);

		lpn = new LinePushNode(line2, pushNode, destination);
		linePushTest.add(lpn);

		lpn = new LinePushNode(line3, pushNode2, destination2);
		linePushTest.add(lpn);

		assertTrue(Equals.subsetEq(pushList, linePushTest));


	}

	@Test
	void Test_Line_Push_Node_Move_Horizontal() {
		Layer layer = Layer.getDefaultBoard();
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

		lpn.makeMove(layer);

		assertTrue(layer.contains('B',4)==MarbleColor.BLACK);
		assertTrue(layer.contains('A',4)==MarbleColor.WHITE);
		assertTrue(layer.contains('E',4)==MarbleColor.EMPTY);
		assertTrue(layer.contains('F',4)==MarbleColor.WHITE);

		LinePushNode lpn2= new LinePushNode(line, pushNode2,destination2);

		lpn2.makeMove(layer);

		assertTrue(layer.contains('F',4)==MarbleColor.BLACK);
		assertTrue(layer.contains('G',4)==MarbleColor.WHITE);
		assertTrue(layer.contains('C',4)==MarbleColor.EMPTY);



	}
	@Test
	void Test_Line_Push_Node_Move_OffBoard() {
		Layer layer = Layer.getDefaultBoard();
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

		Node destination = AbaloneGraph.get().getVertex('F',1);
		graph.changeValue(destination, null);

		layer.addWhite('E',1);

		LinePushNode lpn= new LinePushNode(line, pushNode, destination);
		
		//May be an irrelevant test now
		//assertTrue(lpn.makeMove(layer));

		assertTrue(layer.contains('E',1)==MarbleColor.BLACK);

		assertTrue(layer.contains('B',1)==MarbleColor.EMPTY);

	}


	@Test
	void Test_Line_Push_Node_Move_RightToLeft() {
		Layer layer = Layer.getDefaultBoard();
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
		
		lpn.makeMove(layer);

		assertTrue(layer.contains('E',3)==MarbleColor.WHITE);
		assertTrue(layer.contains('E',2)==MarbleColor.BLACK);
		assertTrue(layer.contains('E',6)==MarbleColor.EMPTY);


		LinePushNode lpn2= new LinePushNode(line, pushNode2,destination2);

		lpn2.makeMove(layer);

		assertTrue(layer.contains('E',7)==MarbleColor.WHITE);
		assertTrue(layer.contains('E',8)==MarbleColor.BLACK);
		assertTrue(layer.contains('E',4)==MarbleColor.EMPTY);
	}

	@Test
	void Test_Line_Push_Node_Move_LeftToRight() {
		Layer layer = Layer.getDefaultBoard();
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

		Node destination= AbaloneGraph.get().getVertex('A',2);
		Node destination2= AbaloneGraph.get().getVertex('G',8);

		graph.changeValue(destination, MarbleColor.EMPTY);
		graph.changeValue(destination2, MarbleColor.EMPTY);

		graph.changeValue(pushNode, MarbleColor.BLACK);
		graph.changeValue(pushNode2, MarbleColor.BLACK);


		pmg.computeAllLinePushNodes(MarbleColor.WHITE);


		LinePushNode lpn= new LinePushNode(line, pushNode, destination);

		lpn.makeMove(layer);

		assertTrue(graph.getValue(AbaloneGraph.get().getVertex('B',3))==MarbleColor.WHITE);
		assertTrue(graph.getValue(AbaloneGraph.get().getVertex('A',2))==MarbleColor.BLACK);
		assertTrue(graph.getValue(AbaloneGraph.get().getVertex('E',6))==MarbleColor.EMPTY);


		LinePushNode lpn2= new LinePushNode(line, pushNode2,destination2);

		lpn2.makeMove(layer);

		assertTrue(graph.getValue(AbaloneGraph.get().getVertex('F',7))==MarbleColor.WHITE);
		assertTrue(graph.getValue(AbaloneGraph.get().getVertex('G',8))==MarbleColor.BLACK);
		assertTrue(graph.getValue(AbaloneGraph.get().getVertex('C',4))==MarbleColor.EMPTY);
	}

	@Test
	void Test_Line_Push_Line_Horizontal() {
		Layer layer = Layer.getDefaultBoard();
		Line line= new Line();
		Line pushLine2 = new Line();
		Line pushLine3 = new Line();

		LinePushLineMoveGenerator lpm= new LinePushLineMoveGenerator(layer);

		Node node= AbaloneGraph.get().getVertex('D',5);
		layer.addBlack('D', 5);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',5);
		layer.addBlack('E', 5);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('F',5);
		layer.addBlack('F', 5);
		line.addToLine(node);

		node= AbaloneGraph.get().getVertex('G',5);
		layer.addWhite('G', 5);
		pushLine3.addToLine(node);
		node= AbaloneGraph.get().getVertex('H',5);
		layer.addWhite('H', 5);
		pushLine3.addToLine(node);

		node = AbaloneGraph.get().getVertex('C',5);
		layer.addWhite('C', 5);
		pushLine2.addToLine(node);
		node= AbaloneGraph.get().getVertex('B',5);
		layer.addWhite('B', 5);
		pushLine2.addToLine(node);



		ArrayList<LinePushLine> linePushTest = new ArrayList<LinePushLine>();


		Node destination2= AbaloneGraph.get().getVertex('A',5);
		Node destination3= AbaloneGraph.get().getVertex('I',5);

		graph.changeValue(destination2, MarbleColor.EMPTY);
		graph.changeValue(destination3, MarbleColor.EMPTY);


		ArrayList<LinePushLine> pushList = lpm.computeAllLinePushLine(MarbleColor.BLACK);


		LinePushLine lpl= new LinePushLine(line, pushLine2, destination2);
		linePushTest.add(lpl);

		lpl= new LinePushLine(line, pushLine3, destination3);
		linePushTest.add(lpl);


		assertTrue(Equals.subsetEq(pushList, linePushTest));

	}

	@Test
	void Test_Compute_2Push_Line2() {
		Layer layer = Layer.getDefaultBoard();
		Line line= new Line();
		Line pushLine3 = new Line();
		LinePushLineMoveGenerator lpm= new LinePushLineMoveGenerator(layer);

		Node node= AbaloneGraph.get().getVertex('E',5);
		layer.addBlack('E', 5);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('F',5);
		layer.addBlack('F', 5);
		line.addToLine(node);


		node= AbaloneGraph.get().getVertex('G',5);
		layer.addWhite('G', 5);
		pushLine3.addToLine(node);
		node= AbaloneGraph.get().getVertex('H',5);
		layer.addWhite('H', 5);
		pushLine3.addToLine(node);

		ArrayList<Line> lines = new ArrayList<Line>();
		lines.add(line);

		assertTrue(lpm.computeAllLinePushLine(MarbleColor.BLACK).isEmpty());
		assertTrue(lpm.computeAllLinePushLine(MarbleColor.WHITE).isEmpty());

	}

	@Test
	void Test_Line_Push_Not_Line() {

		Layer layer = Layer.getDefaultBoard();
		Line line= new Line();
		Line pushLine2 = new Line();
		Line pushLine3 = new Line();

		LinePushLineMoveGenerator lpm= new LinePushLineMoveGenerator(layer);

		Node node= AbaloneGraph.get().getVertex('D',5);
		layer.addBlack('D', 5);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',5);
		layer.addBlack('E', 5);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('F',5);
		layer.addBlack('F', 5);
		line.addToLine(node);

		node= AbaloneGraph.get().getVertex('G',5);
		layer.addWhite('G', 5);
		pushLine3.addToLine(node);
		node= AbaloneGraph.get().getVertex('H',5);
		layer.addBlack('H', 5);
		pushLine3.addToLine(node);

		node = AbaloneGraph.get().getVertex('C',5);
		layer.addBlack('C', 5);
		pushLine2.addToLine(node);
		node= AbaloneGraph.get().getVertex('B',5);
		layer.addWhite('B', 5);
		pushLine2.addToLine(node);




		ArrayList<LinePushLine> pushList = lpm.computeAllLinePushLine(MarbleColor.BLACK);


		assertTrue(pushList.isEmpty());


	}

	@Test
	void Test_Line_Push_Line_Off_Board()  {
		Layer layer = Layer.getDefaultBoard();
		Line line= new Line();
		Line pushLine2 = new Line();
		Line pushLine3 = new Line();

		LinePushLineMoveGenerator lpm= new LinePushLineMoveGenerator(layer);

		Node node= AbaloneGraph.get().getVertex('C',3);
		layer.addBlack('C', 3);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('D',3);
		layer.addBlack('D', 3);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',3);
		layer.addBlack('E', 3);
		line.addToLine(node);

		node= AbaloneGraph.get().getVertex('B',3);
		layer.addWhite('B', 3);
		pushLine3.addToLine(node);
		node= AbaloneGraph.get().getVertex('A',3);
		layer.addWhite('A', 3);
		pushLine3.addToLine(node);

		node = AbaloneGraph.get().getVertex('F',3);
		layer.addWhite('F', 3);
		pushLine2.addToLine(node);
		node= AbaloneGraph.get().getVertex('G',3);
		layer.addWhite('G', 3);
		pushLine2.addToLine(node);



		ArrayList<LinePushLine> linePushTest = new ArrayList<LinePushLine>();


		Node destination2= null;
		Node destination3= null;



		ArrayList<LinePushLine> pushList = lpm.computeAllLinePushLine(MarbleColor.BLACK);


		LinePushLine lpl= new LinePushLine(line, pushLine2, destination2);
		linePushTest.add(lpl);

		lpl= new LinePushLine(line, pushLine3, destination3);
		linePushTest.add(lpl);

		assertTrue(Equals.subsetEq(pushList, linePushTest));

	}

	@Test
	void Test_Line_Push_Line_RightToLeft()  {
		Layer layer = Layer.getDefaultBoard();
		Line line= new Line();
		Line pushLine2 = new Line();
		Line pushLine3 = new Line();

		LinePushLineMoveGenerator lpm= new LinePushLineMoveGenerator(layer);

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



		ArrayList<LinePushLine> linePushTest = new ArrayList<LinePushLine>();

		Node destination2= AbaloneGraph.get().getVertex('E',1);
		Node destination3= AbaloneGraph.get().getVertex('E',9);

		graph.changeValue(destination2, MarbleColor.EMPTY);
		graph.changeValue(destination3, MarbleColor.EMPTY);


		ArrayList<LinePushLine> pushList = lpm.computeAllLinePushLine(MarbleColor.BLACK);


		LinePushLine lpl= new LinePushLine(line, pushLine2, destination2);
		linePushTest.add(lpl);

		lpl= new LinePushLine(line, pushLine3, destination3);
		linePushTest.add(lpl);

		assertTrue(Equals.subsetEq(pushList, linePushTest));

	}
	@Test
	void Test_Line_Push_Line_LeftToRight()  {
		Layer layer = Layer.getDefaultBoard();
		Line line= new Line();
		Line pushLine2 = new Line();
		Line pushLine3 = new Line();

		LinePushLineMoveGenerator lpm= new LinePushLineMoveGenerator(layer);

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



		ArrayList<LinePushLine> linePushTest = new ArrayList<LinePushLine>();


		Node destination2= AbaloneGraph.get().getVertex('A',1);
		Node destination3= AbaloneGraph.get().getVertex('I',9);

		graph.changeValue(destination2, MarbleColor.EMPTY);
		graph.changeValue(destination3, MarbleColor.EMPTY);


		ArrayList<LinePushLine> pushList = lpm.computeAllLinePushLine(MarbleColor.WHITE);


		LinePushLine lpl= new LinePushLine(line, pushLine2, destination2);
		linePushTest.add(lpl);

		lpl= new LinePushLine(line, pushLine3, destination3);
		linePushTest.add(lpl);

		assertTrue(Equals.subsetEq(pushList, linePushTest));
	}

	@Test
	void Test_Move_LinePushLine_Horizontal() {
		Layer layer = Layer.getDefaultBoard();
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

		graph.changeValue(destination2, MarbleColor.EMPTY);
		graph.changeValue(destination3, MarbleColor.EMPTY);

		LinePushLine lplMove= new LinePushLine(line, pushLine2, destination2);

		lplMove.makeMove(layer);

		assertTrue(graph.getValue(AbaloneGraph.get().getVertex('C',5))==MarbleColor.BLACK);
		assertTrue(graph.getValue(AbaloneGraph.get().getVertex('A',5))==MarbleColor.WHITE);
		assertTrue(graph.getValue(AbaloneGraph.get().getVertex('F',5))==MarbleColor.EMPTY);
		assertTrue(graph.getValue(AbaloneGraph.get().getVertex('B',5))==MarbleColor.WHITE);

		LinePushLine lplMove2= new LinePushLine(line, pushLine3, destination3);
		lplMove2.makeMove(layer);

		assertTrue(graph.getValue(AbaloneGraph.get().getVertex('G',5))==MarbleColor.BLACK);
		assertTrue(graph.getValue(AbaloneGraph.get().getVertex('H',5))==MarbleColor.WHITE);
		assertTrue(graph.getValue(AbaloneGraph.get().getVertex('D',5))==MarbleColor.EMPTY);
		assertTrue(graph.getValue(AbaloneGraph.get().getVertex('I',5))==MarbleColor.WHITE);
	}

	@Test
	void Test_Move_LinePushLine_RightToLeft()  {
		Layer layer = Layer.getDefaultBoard();
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

		graph.changeValue(destination2, MarbleColor.EMPTY);
		graph.changeValue(destination3, MarbleColor.EMPTY);

		LinePushLine lplMove= new LinePushLine(line, pushLine2, destination2);

		lplMove.makeMove(layer);

		assertTrue(graph.getValue(AbaloneGraph.get().getVertex('E',3))==MarbleColor.BLACK);
		assertTrue(graph.getValue(AbaloneGraph.get().getVertex('E',1))==MarbleColor.WHITE);
		assertTrue(graph.getValue(AbaloneGraph.get().getVertex('E',6))==MarbleColor.EMPTY);
		assertTrue(graph.getValue(AbaloneGraph.get().getVertex('E',2))==MarbleColor.WHITE);

		LinePushLine lplMove2= new LinePushLine(line, pushLine3, destination3);
		lplMove2.makeMove(layer);

		assertTrue(graph.getValue(AbaloneGraph.get().getVertex('E',7))==MarbleColor.BLACK);
		assertTrue(graph.getValue(AbaloneGraph.get().getVertex('E',9))==MarbleColor.WHITE);
		assertTrue(graph.getValue(AbaloneGraph.get().getVertex('E',4))==MarbleColor.EMPTY);
		assertTrue(graph.getValue(AbaloneGraph.get().getVertex('E',8))==MarbleColor.WHITE);
	}
	@Test
	void Test_Move_LinePushLine_LeftToRight() {
		Layer layer = Layer.getDefaultBoard();
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

		graph.changeValue(destination2, MarbleColor.EMPTY);
		graph.changeValue(destination3, MarbleColor.EMPTY);

		LinePushLine lplMove= new LinePushLine(line, pushLine2, destination2);

		assertFalse(lplMove.makeMove(layer));

		assertTrue(graph.getValue(AbaloneGraph.get().getVertex('C',3))==MarbleColor.WHITE);
		assertTrue(graph.getValue(AbaloneGraph.get().getVertex('A',1))==MarbleColor.BLACK);
		assertTrue(graph.getValue(AbaloneGraph.get().getVertex('F',6))==MarbleColor.EMPTY);
		assertTrue(graph.getValue(AbaloneGraph.get().getVertex('B',2))==MarbleColor.BLACK);

		LinePushLine lplMove2= new LinePushLine(line, pushLine3, destination3);
		lplMove2.makeMove(layer);

		assertTrue(graph.getValue(AbaloneGraph.get().getVertex('G',7))==MarbleColor.WHITE);
		assertTrue(graph.getValue(AbaloneGraph.get().getVertex('I',9))==MarbleColor.BLACK);
		assertTrue(graph.getValue(AbaloneGraph.get().getVertex('D',4))==MarbleColor.EMPTY);
		assertTrue(graph.getValue(AbaloneGraph.get().getVertex('H',8))==MarbleColor.BLACK);
	}
	@Test
	void Test_Move_LinePushLine_Off_Board()  {
		Layer layer = Layer.getDefaultBoard();
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

		Node destination2= AbaloneGraph.get().getVertex('F',1);
		graph.changeValue(destination2, null);

		LinePushLine lplMove= new LinePushLine(line, pushLine2, destination2);

		assertTrue(lplMove.makeMove(layer));

		assertTrue(graph.getValue(AbaloneGraph.get().getVertex('D',1))==MarbleColor.BLACK);
		assertTrue(graph.getValue(AbaloneGraph.get().getVertex('E',1))==MarbleColor.WHITE);
		assertTrue(graph.getValue(AbaloneGraph.get().getVertex('A',1))==MarbleColor.EMPTY);

	}
	@Test
	void Test_Compute_All_Simple_Moves() {
		Layer layer = Layer.getDefaultBoard();
		MoveGenerator mg =new MoveGenerator(layer);

		Node node1= AbaloneGraph.get().getVertex('A',1);
		graph.changeValue(node1, MarbleColor.BLACK);
		Node node2= AbaloneGraph.get().getVertex('A',2);
		Node node3= AbaloneGraph.get().getVertex('B',2);
		Node node4= AbaloneGraph.get().getVertex('B',1);

		//adds simplemoves to testList
		ArrayList<Move> testList = new ArrayList<Move>();
		SimpleMove simple= new SimpleMove(node1, node2);
		testList.add(simple);
		simple= new SimpleMove(node1, node4);
		testList.add(simple);
		simple= new SimpleMove(node1, node3);
		testList.add(simple);

		ArrayList<Move> simpleMoves = mg.computeAllSimpleMove(MarbleColor.BLACK);
		assertTrue(Equals.subsetEq(testList, simpleMoves));

	}
	
	@Test
	void Test_Compute_All_ShiftLine_Moves() {
		Layer layer = Layer.getDefaultBoard();
		MoveGenerator mg =new MoveGenerator(layer);
		Line line= new Line();
		Line line2= new Line();
		
		ArrayList<Move> shiftBlackList = new ArrayList<Move>();
		
		Node node= AbaloneGraph.get().getVertex('A',1);
		layer.addBlack('A', 1);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('A',2);
		layer.addBlack('A', 2);
		line.addToLine(node);

		ShiftLine sL= new ShiftLine(line, AbaloneGraph.get().getVertex('A',3));
		shiftBlackList.add(sL);

		node= AbaloneGraph.get().getVertex('C',1);
		layer.addBlack('C', 1);
		line2.addToLine(node);
		node= AbaloneGraph.get().getVertex('C',2);
		layer.addBlack('C', 2);
		line2.addToLine(node);

		sL= new ShiftLine(line2, AbaloneGraph.get().getVertex('C',3));
		shiftBlackList.add(sL);
		
		
		ArrayList<Move> shiftLine =mg.computeAllShiftLine(MarbleColor.BLACK);
		

		assertTrue(Equals.subsetEq(shiftLine, shiftBlackList));
	}

	@Test
	void Test_Compute_All_SideStep_Moves() {
		Layer layer = Layer.getDefaultBoard();
		MoveGenerator mg =new MoveGenerator(layer);
		Line line= new Line();
		Line line2= new Line();
		
		ArrayList<Move> sideStepBlackList = new ArrayList<Move>();
		ArrayList<Move> sideStepWhiteList = new ArrayList<Move>();
		
		Node node= AbaloneGraph.get().getVertex('A',1);
		layer.addBlack('A', 1);
		line.addToLine(node);
		node= AbaloneGraph.get().getVertex('A',2);
		layer.addBlack('A', 2);
		line.addToLine(node);

		SideStep ss= new SideStep(line, HexagonCardinalDirections.EAST);
		sideStepBlackList.add(ss);
		ss= new SideStep(line, HexagonCardinalDirections.SOUTHEAST);
		sideStepBlackList.add(ss);

		node= AbaloneGraph.get().getVertex('C',1);
		layer.addBlack('C', 1);
		line2.addToLine(node);
		node= AbaloneGraph.get().getVertex('C',2);
		layer.addBlack('C', 2);
		line2.addToLine(node);
		
		ss= new SideStep(line2, HexagonCardinalDirections.WEST);
		sideStepBlackList.add(ss);
		ss= new SideStep(line2, HexagonCardinalDirections.EAST);
		sideStepBlackList.add(ss);
		ss= new SideStep(line2, HexagonCardinalDirections.SOUTHEAST);
		sideStepBlackList.add(ss);

		ArrayList<Move> sideStepLine =mg.computeAllSideStep(MarbleColor.BLACK);
		assertTrue(Equals.subsetEq(sideStepLine, sideStepBlackList));
		
		Line line3= new Line();
		Line line4 = new Line();
		
		node= AbaloneGraph.get().getVertex('A',4);
		layer.addWhite('A', 4);
		line3.addToLine(node);
		node= AbaloneGraph.get().getVertex('A',5);
		layer.addWhite('A', 5);
		line3.addToLine(node);

		ss= new SideStep(line, HexagonCardinalDirections.SOUTHEAST);
		sideStepWhiteList.add(ss);
		ss= new SideStep(line, HexagonCardinalDirections.EAST);
		sideStepWhiteList.add(ss);

		node= AbaloneGraph.get().getVertex('D',1);
		layer.addWhite('D', 1);
		line4.addToLine(node);
		node= AbaloneGraph.get().getVertex('D',2);
		layer.addWhite('D', 2);
		line4.addToLine(node);
		
		ss= new SideStep(line2, HexagonCardinalDirections.WEST);
		sideStepWhiteList.add(ss);
		ss= new SideStep(line2, HexagonCardinalDirections.EAST);
		sideStepWhiteList.add(ss);
		ss= new SideStep(line2, HexagonCardinalDirections.SOUTHEAST);
		sideStepWhiteList.add(ss);

		ArrayList<Move> sideStepWhiteLine =mg.computeAllSideStep(MarbleColor.WHITE);
		assertTrue(Equals.subsetEq(sideStepWhiteLine, sideStepWhiteList));
		
	}
	
	@Test
	void Test_Compute_All_LinePush_Moves() {
		Layer layer = Layer.getDefaultBoard();
		MoveGenerator mg =new MoveGenerator(layer);
		Line line= new Line();
		Line pushLine = new Line();
		Line pushedLine = new Line();
		
		ArrayList<Move> linePush = new ArrayList<Move>();
		
		Node node= AbaloneGraph.get().getVertex('A',1);
		layer.addBlack('A', 1);
		line.addToLine(node);
		
		node= AbaloneGraph.get().getVertex('A',2);
		layer.addBlack('A', 2);
		line.addToLine(node);
		
		Node whiteNode= AbaloneGraph.get().getVertex('A',3);
		graph.changeValue(whiteNode, MarbleColor.WHITE);
		
		
		Node destination = AbaloneGraph.get().getVertex('A',4);
		
		LinePushNode lpn =new LinePushNode(line, whiteNode, destination);
		linePush.add(lpn);
		
		
		
		node= AbaloneGraph.get().getVertex('D',8);
		layer.addBlack('D', 8);
		pushLine.addToLine(node);
		
		node= AbaloneGraph.get().getVertex('E',8);
		layer.addBlack('E', 8);
		pushLine.addToLine(node);
		
		node = AbaloneGraph.get().getVertex('F',8);
		layer.addBlack('F', 8);
		pushLine.addToLine(node);
		
		
		Node pushNode= AbaloneGraph.get().getVertex('G',8);
		graph.changeValue(pushNode, MarbleColor.WHITE);
		pushedLine.addToLine(pushNode);
		
		Node pushNode2= AbaloneGraph.get().getVertex('H',8);
		graph.changeValue(pushNode2, MarbleColor.WHITE);
		pushedLine.addToLine(pushNode2);
		
		Node pushDestination = AbaloneGraph.get().getVertex('I',8);
		
		
		
		LinePushLine lpl =new LinePushLine(pushLine, pushedLine, pushDestination);
		linePush.add(lpl);
		
		
		ArrayList<Move> allPushMoves = mg.computeAllPushMoves(MarbleColor.BLACK);
		assertTrue(Equals.subset(allPushMoves, linePush));
		
		
	}
	
	@Test
	void Test_Compute_All_Moves() {
		Layer layer = Layer.getDefaultBoard();
		MoveGenerator mg =new MoveGenerator(layer);
		Line line= new Line();
		Line line2= new Line();
		Line line3= new Line();
		
		Line pushedLine = new Line();
		
		ArrayList<Move> allMoves = new ArrayList<Move>();
		
		
		Node node = AbaloneGraph.get().getVertex('D',5);
		layer.addBlack('D', 5);
		line.addToLine(node);
		line2.addToLine(node);
		
		Node node10 = AbaloneGraph.get().getVertex('E',5);
		graph.changeValue(node10, MarbleColor.BLACK);
		line.addToLine(node10);
		line2.addToLine(node10);
		line3.addToLine(node10);
		
		Node node11 = AbaloneGraph.get().getVertex('F',5);
		graph.changeValue(node11, MarbleColor.BLACK);
		line.addToLine(node11);
		line3.addToLine(node11);
		
		
		
		Node node2 = AbaloneGraph.get().getVertex('C',4);
		Node node3 = AbaloneGraph.get().getVertex('D',4);
		Node node4 = AbaloneGraph.get().getVertex('E',4);
		Node node5 = AbaloneGraph.get().getVertex('F',4);
		
		Node node6 = AbaloneGraph.get().getVertex('D',6);
		Node node7 = AbaloneGraph.get().getVertex('E',6);
		Node node8 = AbaloneGraph.get().getVertex('F',6);
		Node node9 = AbaloneGraph.get().getVertex('G',6);
		
		SimpleMove sm = new SimpleMove(node, node2);
		allMoves.add(sm);
		
		sm = new SimpleMove(node, node3);
		allMoves.add(sm);
		
		sm = new SimpleMove(node, node6);
		allMoves.add(sm);
		
		sm = new SimpleMove(node, node7);
		allMoves.add(sm);
		
		sm = new SimpleMove(node10, node3);
		allMoves.add(sm);
		
		sm = new SimpleMove(node10, node4);
		allMoves.add(sm);
		
		sm = new SimpleMove(node10, node7);
		allMoves.add(sm);
		
		sm = new SimpleMove(node10, node8);
		allMoves.add(sm);
		
		sm = new SimpleMove(node11, node4);
		allMoves.add(sm);
		
		sm = new SimpleMove(node11, node5);
		allMoves.add(sm);
		
		sm = new SimpleMove(node11, node8);
		allMoves.add(sm);
		
		sm = new SimpleMove(node11, node9);
		allMoves.add(sm);
		
		node = AbaloneGraph.get().getVertex('G',5);
		layer.addWhite('G', 5);
		pushedLine.addToLine(node);
		
		node = AbaloneGraph.get().getVertex('H',5);
		layer.addWhite('H', 5);
		pushedLine.addToLine(node);
		
		Node lineDestination = AbaloneGraph.get().getVertex('I',5);
		
		LinePushLine lpl =new LinePushLine(line, pushedLine, lineDestination);
		allMoves.add(lpl);
		
		Node pushNode = AbaloneGraph.get().getVertex('C',5);
		graph.changeValue(pushNode, MarbleColor.WHITE);
		
		Node pushNodeDestination = AbaloneGraph.get().getVertex('B',5);
		
		LinePushNode lpn = new LinePushNode(line, pushNode, pushNodeDestination);
		allMoves.add(lpn);
		
		lpn = new LinePushNode(line2, pushNode, pushNodeDestination);
		allMoves.add(lpn);
		
		
		
		
		
		SideStep ss = new SideStep(line, HexagonCardinalDirections.NORTHEAST);
		allMoves.add(ss);
		
		ss = new SideStep(line, HexagonCardinalDirections.NORTHWEST);
		allMoves.add(ss);
		
		ss = new SideStep(line, HexagonCardinalDirections.SOUTHWEST);
		allMoves.add(ss);
		
		ss = new SideStep(line, HexagonCardinalDirections.SOUTHEAST);
		allMoves.add(ss);
		
		
		
		ss = new SideStep(line2, HexagonCardinalDirections.SOUTHWEST);
		allMoves.add(ss);
		
		ss = new SideStep(line2, HexagonCardinalDirections.SOUTHEAST);
		allMoves.add(ss);
		
		ss = new SideStep(line2, HexagonCardinalDirections.NORTHEAST);
		allMoves.add(ss);
		
		ss = new SideStep(line2, HexagonCardinalDirections.NORTHWEST);
		allMoves.add(ss);
		
		
		ss = new SideStep(line3, HexagonCardinalDirections.SOUTHWEST);
		allMoves.add(ss);
		
		ss = new SideStep(line3, HexagonCardinalDirections.SOUTHEAST);
		allMoves.add(ss);
		
		ss = new SideStep(line3, HexagonCardinalDirections.NORTHEAST);
		allMoves.add(ss);
		
		ss = new SideStep(line3, HexagonCardinalDirections.NORTHWEST);
		allMoves.add(ss);
		
		ArrayList<Move> actualMoves = mg.computeAllMoves(MarbleColor.BLACK);

		assertTrue(Equals.subsetEq(actualMoves, allMoves));
	}
	

	@Test
	void Test_Create_Singlenode()  {
		Layer layer = Layer.getDefaultBoard();
		graph.changeValue(AbaloneGraph.get().getVertex('I',9), MarbleColor.WHITE);
		assertEquals(graph.getValue(AbaloneGraph.get().getVertex('I',9)),MarbleColor.WHITE);
	}
}
