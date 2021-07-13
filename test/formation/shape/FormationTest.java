package formation.shape;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import board.Layer;
import graph.AbaloneGraph;
import graph.Node;
import formation.shape.Line.HexagonCardinalDirections;
import move.representation.LinePushLine;
import move.representation.LinePushNode;
import move.representation.ShiftLine;
import move.representation.SideStep;
import move.representation.SimpleMove;
import utilities.Equals;

class FormationTest
{
	@Test
	void Test_Decompose_Triangle()  {
		Layer layer = new Layer();

		Triangle tri= new Triangle();

		//creates empty nodes
		layer.addBlack('B',2);
		tri.addToTriangle(AbaloneGraph.get().getVertex('B',2));

		layer.addBlack('B',3);
		tri.addToTriangle(AbaloneGraph.get().getVertex('B',3));

		layer.addBlack('C',3);
		tri.addToTriangle(AbaloneGraph.get().getVertex('C',3));

		ArrayList<Node> decomposed= tri.decompose();
		ArrayList<Node> testList = new ArrayList<Node>();
		testList.add(AbaloneGraph.get().getVertex('B',2));
		testList.add(AbaloneGraph.get().getVertex('B',3));
		testList.add(AbaloneGraph.get().getVertex('C',3));

		assertTrue(Equals.subsetEq(decomposed, testList));

	}
	
	@Test
	void Test_Decompose_Parallelogram_Size_2()  {
		Layer layer = new Layer();

		Parallelogram par = new Parallelogram();
		Line line = new Line();
		Line line2= new Line();

	
		layer.addBlack('B',2);
		line.addToLine(AbaloneGraph.get().getVertex('B',2));

		layer.addBlack('C',2);
		line.addToLine(AbaloneGraph.get().getVertex('C',2));

		layer.addBlack('B',3);
		line2.addToLine(AbaloneGraph.get().getVertex('B',3));

		layer.addBlack('C',3);
		line2.addToLine(AbaloneGraph.get().getVertex('C',3));

		par.addToParallelogram(line);
		par.addToParallelogram(line2);
		
		ArrayList<Node> decomposed= par.decompose();
		ArrayList<Node> testList = new ArrayList<Node>();
		testList.add(AbaloneGraph.get().getVertex('B',2));
		testList.add(AbaloneGraph.get().getVertex('C',2));
		testList.add(AbaloneGraph.get().getVertex('B',3));
		testList.add(AbaloneGraph.get().getVertex('C',3));
		
		assertTrue(Equals.subsetEq(decomposed, testList));

	}
	
	@Test
	void Test_Decompose_Parallelogram_Size_3()  {
		Layer layer = new Layer();

		Parallelogram par = new Parallelogram();
		Line line = new Line();
		Line line2= new Line();
	
		layer.addBlack('B',2);
		line.addToLine(AbaloneGraph.get().getVertex('B',2));

		layer.addBlack('C',2);
		line.addToLine(AbaloneGraph.get().getVertex('C',2));

		layer.addBlack('D',2);
		line.addToLine(AbaloneGraph.get().getVertex('D',2));

		layer.addBlack('B',3);
		line2.addToLine(AbaloneGraph.get().getVertex('B',3));

		layer.addBlack('C',3);
		line2.addToLine(AbaloneGraph.get().getVertex('C',3));

		layer.addBlack('D',3);
		line2.addToLine(AbaloneGraph.get().getVertex('D',3));

		par.addToParallelogram(line);
		par.addToParallelogram(line2);
		
		ArrayList<Node> decomposed= par.decompose();
		ArrayList<Node> testList = new ArrayList<Node>();
		testList.add(AbaloneGraph.get().getVertex('B',2));
		testList.add(AbaloneGraph.get().getVertex('C',2));
		testList.add(AbaloneGraph.get().getVertex('D',2));
		testList.add(AbaloneGraph.get().getVertex('B',3));
		testList.add(AbaloneGraph.get().getVertex('C',3));
		testList.add(AbaloneGraph.get().getVertex('D',3));
		
		assertTrue(Equals.subsetEq(decomposed, testList));

	}
	
	@Test
	void Test_Move_Applies_Parallelogram()  {
		Layer layer = new Layer();

		Parallelogram par = new Parallelogram();
		Line line = new Line();
		Line line2= new Line();
		Line line3= new Line();
		Line falseLine= new Line();
		Line falseLine2= new Line();

		layer.addBlack('B',2);
		line.addToLine(AbaloneGraph.get().getVertex('B',2));
		line3.addToLine(AbaloneGraph.get().getVertex('B',2));

		layer.addBlack('C',2);
		line.addToLine(AbaloneGraph.get().getVertex('C',2));
		line3.addToLine(AbaloneGraph.get().getVertex('C',2));

		layer.addBlack('D',2);
		line.addToLine(AbaloneGraph.get().getVertex('D',2));

		layer.addBlack('B',3);
		line2.addToLine(AbaloneGraph.get().getVertex('B',3));

		layer.addBlack('C',3);
		line2.addToLine(AbaloneGraph.get().getVertex('C',3));

		layer.addBlack('D',3);
		line2.addToLine(AbaloneGraph.get().getVertex('D',3));

		layer.addBlack('G',6);
		falseLine.addToLine(AbaloneGraph.get().getVertex('G',6));

		layer.addBlack('H',6);
		falseLine.addToLine(AbaloneGraph.get().getVertex('H',6));
	
		ShiftLine sL = new ShiftLine(line3, AbaloneGraph.get().getVertex('A',2));		
		
		ShiftLine falseSL= new ShiftLine(falseLine, AbaloneGraph.get().getVertex('I',6));
		
		Node destination = AbaloneGraph.get().getVertex('D',2);
		
		par.addToParallelogram(line);
		par.addToParallelogram(line2);
		
		assertTrue(sL.moveApplies(par));
		
		SimpleMove sm = new SimpleMove(AbaloneGraph.get().getVertex('B',2), destination);
		assertTrue(sm.moveApplies(par));
		
		sL = new ShiftLine(line, AbaloneGraph.get().getVertex('E',2));
		assertTrue(sL.moveApplies(par));
		
		assertFalse(falseSL.moveApplies(par));

		layer.addWhite('E',2);
		layer.addWhite('F',2);
		
		destination = null;
		
		LinePushLine lpl = new LinePushLine(line, falseLine2, destination);
		assertTrue(lpl.moveApplies(par));		
		
	}
	
	@Test
	void Test_Move_Applies_SimpleMoves() {
		Layer layer = Layer.getDefaultBoard();

		Triangle tri= new Triangle();

		layer.addBlack('E',2);
		tri.addToTriangle(AbaloneGraph.get().getVertex('E',2));

		layer.addBlack('E', 3);
		tri.addToTriangle(AbaloneGraph.get().getVertex('E',3));

		layer.addBlack('F',3);
		tri.addToTriangle(AbaloneGraph.get().getVertex('F',3));

		Node toNode= AbaloneGraph.get().getVertex('G', 3);

		Node toNode2= AbaloneGraph.get().getVertex('D', 2);

		Node toNode3 = AbaloneGraph.get().getVertex('D',1);

		SimpleMove sm = new SimpleMove(AbaloneGraph.get().getVertex('F',3), toNode);
		SimpleMove sm2= new SimpleMove(AbaloneGraph.get().getVertex('E',3), toNode2);
		SimpleMove sm3 = new SimpleMove(AbaloneGraph.get().getVertex('E',2), toNode3);

		Node notNode= AbaloneGraph.get().getVertex('A',1);
		Node notNode2= AbaloneGraph.get().getVertex('B',1);
		SimpleMove notPossibleMove = new SimpleMove(notNode, notNode2);

		assertTrue(sm.moveApplies(tri));
		assertTrue(sm2.moveApplies(tri));
		assertTrue(sm3.moveApplies(tri));

		assertFalse(notPossibleMove.moveApplies(tri));
	}

	@Test
	void Test_Move_Applies_ShiftLine() {
		Layer layer = Layer.getDefaultBoard();

		Line line= new Line();
		Line falseLine = new Line();
		Line falseLine2 = new Line();

		Triangle tri= new Triangle();

		layer.addBlack('E',2);
		tri.addToTriangle(AbaloneGraph.get().getVertex('E',2));
		line.addToLine(AbaloneGraph.get().getVertex('E',2));

		layer.addBlack('E',3);
		tri.addToTriangle(AbaloneGraph.get().getVertex('E',3));
		line.addToLine(AbaloneGraph.get().getVertex('E',3));

		layer.addBlack('F',3);
		tri.addToTriangle(AbaloneGraph.get().getVertex('F',3));


		ShiftLine sL = new ShiftLine(line, AbaloneGraph.get().getVertex('E',4));
		assertTrue(sL.moveApplies(tri));

		Node node = AbaloneGraph.get().getVertex('F',7);
		falseLine.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',7);
		falseLine.addToLine(node);

		sL= new ShiftLine(falseLine, AbaloneGraph.get().getVertex('D',7));

		assertFalse(sL.moveApplies(tri));

		node= AbaloneGraph.get().getVertex('E',3);
		falseLine2.addToLine(node);

		node = AbaloneGraph.get().getVertex('E',4);
		falseLine2.addToLine(node);

		sL= new ShiftLine(falseLine2, AbaloneGraph.get().getVertex('E',2));
		assertFalse(sL.moveApplies(tri));

	}

	@Test
	void Test_Move_Applies_SideStep() {
		Layer layer = Layer.getDefaultBoard();

		Line line= new Line();
		Line falseLine = new Line();
		Line falseLine2 = new Line();

		Triangle tri= new Triangle();

		layer.addBlack('E',2);
		tri.addToTriangle(AbaloneGraph.get().getVertex('E',2));
		line.addToLine(AbaloneGraph.get().getVertex('E',2));

		layer.addBlack('E',3);
		tri.addToTriangle(AbaloneGraph.get().getVertex('E',3));
		line.addToLine(AbaloneGraph.get().getVertex('E',3));

		layer.addBlack('F',3);
		tri.addToTriangle(AbaloneGraph.get().getVertex('F',3));

		SideStep ss = new SideStep(line, HexagonCardinalDirections.NORTHWEST);
		assertTrue(ss.moveApplies(tri)); 

		Node node = AbaloneGraph.get().getVertex('F',7);
		falseLine.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',7);
		falseLine.addToLine(node);

		ss = new SideStep(falseLine, HexagonCardinalDirections.NORTHWEST);

		assertFalse(ss.moveApplies(tri));

		node= AbaloneGraph.get().getVertex('D',1);
		falseLine2.addToLine(node);

		node = AbaloneGraph.get().getVertex('D',2);
		falseLine2.addToLine(node);

		ss= new SideStep(falseLine2, HexagonCardinalDirections.SOUTHEAST);
		assertFalse(ss.moveApplies(tri));
	}

	@Test
	void Test_Move_Applies_LinePushNode() {
		Layer layer = Layer.getDefaultBoard();

		Line line= new Line();
		Line falseLine = new Line();
		Line falseLine2 = new Line();

		Triangle tri= new Triangle();

		layer.addBlack('E',2);
		tri.addToTriangle(AbaloneGraph.get().getVertex('E',2));
		line.addToLine(AbaloneGraph.get().getVertex('E',2));

		layer.addBlack('E',3);
		tri.addToTriangle(AbaloneGraph.get().getVertex('E',3));
		line.addToLine(AbaloneGraph.get().getVertex('E',3));

		layer.addBlack('F',3);
		tri.addToTriangle(AbaloneGraph.get().getVertex('F',3));
		
		Node pushNode = AbaloneGraph.get().getVertex('E',4);
		layer.addWhite('E', 4);
		
		Node destination = AbaloneGraph.get().getVertex('E',5);

		LinePushNode lpn = new LinePushNode(line, pushNode, destination);
		assertTrue(lpn.moveApplies(tri));

		Node node = AbaloneGraph.get().getVertex('F',7);
		falseLine.addToLine(node);
		node= AbaloneGraph.get().getVertex('E',7);
		falseLine.addToLine(node);
		
		Node falsePush = AbaloneGraph.get().getVertex('G',7);
		Node falseDestination = AbaloneGraph.get().getVertex('H',7);
		
		lpn = new LinePushNode(falseLine, falsePush, falseDestination);

		assertFalse(lpn.moveApplies(tri));

		node= AbaloneGraph.get().getVertex('D',1);
		falseLine2.addToLine(node);

		node = AbaloneGraph.get().getVertex('D',2);
		falseLine2.addToLine(node);
		
		Node falsePush2 = AbaloneGraph.get().getVertex('G',7);
		Node falseDestination2 = AbaloneGraph.get().getVertex('H',7);

		lpn = new LinePushNode(falseLine2, falsePush2,falseDestination2);
		assertFalse(lpn.moveApplies(tri));
	
	}


}

