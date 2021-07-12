package test.move;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import board.Layer;
import board.Marble.MarbleColor;
import formation.computer.LineComputer;
import formation.shape.Line;
import formation.shape.Line.HexagonCardinalDirections;
import graph.AbaloneGraph;
import graph.Node;
import move.generator.NonPushMoveGenerator;
import move.representation.ShiftLine;
import move.representation.SideStep;
import move.representation.SimpleMove;
import utilities.Equals;

class NonPushMoveGeneratorTest {

	@Test
	void Test_Create_Singlenode()  {
		Layer layer = Layer.getDefaultBoard();
		assertEquals(layer.contains('I', 9),MarbleColor.WHITE);
	}

	@Test
	void Test_Possible_Simple_Moves()  {
		Layer layer = new Layer();
		NonPushMoveGenerator mg= new NonPushMoveGenerator(layer);

		layer.addBlack('A', 1);

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
		Node node2= AbaloneGraph.get().getVertex('A',3);
		SimpleMove simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('A',2);

		node2= AbaloneGraph.get().getVertex('B',3);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('B',2);

		node2= AbaloneGraph.get().getVertex('B',3);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('C',3);

		node2= AbaloneGraph.get().getVertex('B',3);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('C',3);

		node2= AbaloneGraph.get().getVertex('C',4);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('C',3);

		node2= AbaloneGraph.get().getVertex('D',4);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('D',3);

		node2= AbaloneGraph.get().getVertex('D',4);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('D',3);

		node2= AbaloneGraph.get().getVertex('E',4);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('E',3);

		node2= AbaloneGraph.get().getVertex('E',4);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('E',3);

		node2= AbaloneGraph.get().getVertex('F',4);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('E',3);

		node2= AbaloneGraph.get().getVertex('F',3);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('E',2);

		node2= AbaloneGraph.get().getVertex('F',3);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('F',2);

		node2= AbaloneGraph.get().getVertex('F',3);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);

		node= AbaloneGraph.get().getVertex('F',2);

		node2= AbaloneGraph.get().getVertex('G',3);
		simple= new SimpleMove(node, node2);
		blackList.add(simple);


		node= AbaloneGraph.get().getVertex('D',8);

		node2= AbaloneGraph.get().getVertex('C',7);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('D',8);

		node2= AbaloneGraph.get().getVertex('D',7);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('E',8);

		node2= AbaloneGraph.get().getVertex('D',7);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('E',7);

		node2= AbaloneGraph.get().getVertex('D',7);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('E',7);

		node2= AbaloneGraph.get().getVertex('D',6);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('E',7);

		node2= AbaloneGraph.get().getVertex('E',6);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('F',7);

		node2= AbaloneGraph.get().getVertex('E',6);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('F',7);

		node2= AbaloneGraph.get().getVertex('F',6);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('G',7);

		node2= AbaloneGraph.get().getVertex('F',6);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('G',7);

		node2= AbaloneGraph.get().getVertex('G',6);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('G',7);

		node2= AbaloneGraph.get().getVertex('H',7);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('H',8);

		node2= AbaloneGraph.get().getVertex('H',7);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('I',8);

		node2= AbaloneGraph.get().getVertex('H',7);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);

		node= AbaloneGraph.get().getVertex('I',8);

		node2= AbaloneGraph.get().getVertex('I',7);
		simple= new SimpleMove(node, node2);
		whiteList.add(simple);


		assertTrue(whiteList.size()==14);
		assertTrue(blackList.size()==14);



		ArrayList<SimpleMove> whiteMoveList = mg.computeAllSimple(MarbleColor.WHITE);

		ArrayList<SimpleMove> blackMoveList = mg.computeAllSimple(MarbleColor.BLACK);



		assertTrue(Equals.subsetEq(whiteMoveList, whiteList));
		assertTrue(Equals.subsetEq(blackMoveList, blackList));

	}

	@Test
	void Test_Default_Board_Shift_Line_Moves() {
		Layer layer = new Layer();
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

		ArrayList<ShiftLine> shiftBlack = mg.computeAllShifts(MarbleColor.BLACK);
		ArrayList<ShiftLine> shiftWhite = mg.computeAllShifts(MarbleColor.WHITE);
		
		assertTrue(Equals.subsetEq(shiftBlack, shiftBlackList));
		assertTrue(Equals.subset(shiftWhite, shiftWhiteList));
		
		
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


		assertEquals(mg.computeSideStep(blackLineList3.get(0)),blackSideList3);
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
}
