package test.move;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import board.Layer;
import board.Marble.MarbleColor;
import formation.shape.Line;
import formation.shape.Line.HexagonCardinalDirections;
import graph.AbaloneGraph;
import graph.Node;
import move.generator.MoveGenerator;
import move.representation.LinePushLine;
import move.representation.LinePushNode;
import move.representation.Move;
import move.representation.ShiftLine;
import move.representation.SideStep;
import move.representation.SimpleMove;
import utilities.Equals;

class MoveGeneratorTest {

	@Test
	void Test_Compute_All_Simple_Moves() {
		Layer layer = new Layer();
		MoveGenerator mg =new MoveGenerator(layer);

		Node node1= AbaloneGraph.get().getVertex('A',1);
		layer.addBlack('A', 1);
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
		Layer layer = new Layer();
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


		ArrayList<Move> shiftLine = mg.computeAllShiftLine(MarbleColor.BLACK);


		assertTrue(Equals.subsetEq(shiftLine, shiftBlackList));
	}

	@Test
	void Test_Compute_All_SideStep_Moves() {
		
		Layer layer = new Layer();
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

		ArrayList<Move> sideStepBlackLine =mg.computeAllSideStep(MarbleColor.BLACK);
		assertTrue(Equals.subsetEq(sideStepBlackLine, sideStepBlackList));

		Line line3= new Line();
		Line line4 = new Line();

		node= AbaloneGraph.get().getVertex('A',4);
		layer.addWhite('A', 4);
		line3.addToLine(node);
		node= AbaloneGraph.get().getVertex('A',5);
		layer.addWhite('A', 5);
		line3.addToLine(node);

		ss= new SideStep(line3, HexagonCardinalDirections.SOUTHEAST);
		sideStepWhiteList.add(ss);
		ss= new SideStep(line3, HexagonCardinalDirections.EAST);
		sideStepWhiteList.add(ss);

		node= AbaloneGraph.get().getVertex('D',1);
		layer.addWhite('D', 1);
		line4.addToLine(node);
		node= AbaloneGraph.get().getVertex('D',2);
		layer.addWhite('D', 2);
		line4.addToLine(node);

		ss= new SideStep(line4, HexagonCardinalDirections.EAST);
		sideStepWhiteList.add(ss);
		ss= new SideStep(line4, HexagonCardinalDirections.SOUTHEAST);
		sideStepWhiteList.add(ss);

		ArrayList<Move> sideStepWhiteLine =mg.computeAllSideStep(MarbleColor.WHITE);
		assertTrue(Equals.subsetEq(sideStepWhiteLine, sideStepWhiteList));

	}

	@Test
	void Test_Compute_All_LinePush_Moves() {
		Layer layer = new Layer();
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
		layer.addWhite('A',3);		

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
		layer.addWhite('G', 8);
		pushedLine.addToLine(pushNode);

		Node pushNode2= AbaloneGraph.get().getVertex('H',8);
		layer.addWhite('H', 8);
		pushedLine.addToLine(pushNode2);

		Node pushDestination = AbaloneGraph.get().getVertex('I',8);



		LinePushLine lpl =new LinePushLine(pushLine, pushedLine, pushDestination);
		linePush.add(lpl);


		ArrayList<Move> allPushMoves = mg.computeAllPushMoves(MarbleColor.BLACK);
		assertTrue(Equals.subset(allPushMoves, linePush));


	}

	@Test
	void Test_Compute_All_Moves() {
		Layer layer = new Layer();
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
		layer.addBlack('E', 5);
		line.addToLine(node10);
		line2.addToLine(node10);
		line3.addToLine(node10);

		Node node11 = AbaloneGraph.get().getVertex('F',5);
		layer.addBlack('F', 5);
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
		layer.addWhite('C', 5);

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
	void Number_Of_Default_Board_Moves() {
		Layer layer = Layer.getDefaultBoard();
		
		MoveGenerator mg = new MoveGenerator(layer);
		ArrayList<Move> blackMoves = mg.computeAllMoves(MarbleColor.BLACK);
		ArrayList<Move> whiteMoves = mg.computeAllMoves(MarbleColor.WHITE);
		
		
		assertTrue(blackMoves.size()==44);
		assertTrue(whiteMoves.size()==44);
		
		
	}

}
