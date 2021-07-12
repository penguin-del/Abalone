package test.move;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import board.Layer;
import board.Marble.MarbleColor;
import formation.shape.Line;
import graph.AbaloneGraph;
import graph.Node;
import move.generator.LinePushLineMoveGenerator;
import move.representation.LinePushLine;
import utilities.Equals;

class LinePushLineGeneratorTest {

	@Test
	void Test_Line_Push_Not_Line() {

		Layer layer = new Layer();
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
		layer.addWhite('C', 5);
		pushLine2.addToLine(node);
		node= AbaloneGraph.get().getVertex('B',5);
		layer.addBlack('B', 5);
		pushLine2.addToLine(node);

		ArrayList<LinePushLine> pushList = lpm.computeAllLinePushLine(MarbleColor.BLACK);
		assertTrue(pushList.isEmpty());
	}

	@Test
	void Test_Line_Push_Line_Off_Board()  {
		Layer layer = new Layer();
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
		Layer layer = new Layer();
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

		
		layer.remove('E', 1);
		layer.remove('E', 9);

		ArrayList<LinePushLine> pushList = lpm.computeAllLinePushLine(MarbleColor.BLACK);


		LinePushLine lpl= new LinePushLine(line, pushLine2, destination2);
		linePushTest.add(lpl);

		lpl= new LinePushLine(line, pushLine3, destination3);
		linePushTest.add(lpl);

		assertTrue(Equals.subsetEq(pushList, linePushTest));

	}
	@Test
	void Test_Line_Push_Line_LeftToRight()  {
		Layer layer = new Layer();
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

		layer.remove('A', 1);
		layer.remove('I',9);
		
		ArrayList<LinePushLine> pushList = lpm.computeAllLinePushLine(MarbleColor.WHITE);


		LinePushLine lpl= new LinePushLine(line, pushLine2, destination2);
		linePushTest.add(lpl);

		lpl= new LinePushLine(line, pushLine3, destination3);
		linePushTest.add(lpl);

		assertTrue(Equals.subsetEq(pushList, linePushTest));
	}
	
	@Test
	void Test_Compute_2Push_Line2() {
		Layer layer = new Layer();
		Line line= new Line();
		Line pushLine = new Line();
		LinePushLineMoveGenerator lpm= new LinePushLineMoveGenerator(layer);

		Node node= AbaloneGraph.get().getVertex('E',5);
		layer.addBlack('E', 5);
		line.addToLine(node);
		
		node= AbaloneGraph.get().getVertex('F',5);
		layer.addBlack('F', 5);
		line.addToLine(node);


		node= AbaloneGraph.get().getVertex('G',5);
		layer.addWhite('G', 5);
		pushLine.addToLine(node);
		
		node= AbaloneGraph.get().getVertex('H',5);
		layer.addWhite('H', 5);
		pushLine.addToLine(node);

		
		assertTrue(lpm.computeAllLinePushLine(MarbleColor.BLACK).isEmpty());
		assertTrue(lpm.computeAllLinePushLine(MarbleColor.WHITE).isEmpty());

	}
	
	@Test
	void Test_Line_Push_Line_Horizontal() {
		Layer layer = new Layer();
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
		
		layer.remove('A', 5);
		layer.remove('I', 5);

		ArrayList<LinePushLine> pushList = lpm.computeAllLinePushLine(MarbleColor.BLACK);


		LinePushLine lpl= new LinePushLine(line, pushLine2, destination2);
		linePushTest.add(lpl);

		lpl= new LinePushLine(line, pushLine3, destination3);
		linePushTest.add(lpl);


		assertTrue(Equals.subsetEq(pushList, linePushTest));

	}

}
