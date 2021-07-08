package test.graph;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import board.Layer;
import board.Marble.MarbleColor;
import formation.computer.LineComputer;
import formation.shape.Line;
import graph.AbaloneGraph;
import graph.Node;

class LineComputerTest {

	public void test1NodeAdder(Layer layer)
	{
		layer.addBlack('A', 1);
		layer.addBlack('B', 1);
		layer.addWhite('A', 2);
		layer.addWhite('B', 3);
	}

	
	public void test2NodeAdder(Layer layer)
	{
		layer.addBlack('C', 4);
		layer.addBlack('D', 4);
		layer.addBlack('E', 4);
		layer.addBlack('B', 3);
	}

	public void test3NodeAdder(Layer layer) {
		layer.addWhite('D', 7);
		layer.addWhite('D', 8);
	}

	public void test4NodeAdder(Layer layer) {
		layer.addBlack('D', 7);
		layer.addBlack('D', 8);
	}
	
	@Test
	public void Test_2_line_of_2()
	{
        Layer layer = new Layer();

		test1NodeAdder(layer);
        
        // (A 1):(B 1)\n(A 2):(B 3)
		LineComputer lc = new LineComputer(layer);

		Node node1 = AbaloneGraph.get().getVertex('A', 1);
		Node node2 = AbaloneGraph.get().getVertex('B', 1);
		Node node3 = AbaloneGraph.get().getVertex('A', 2);
		Node node4 = AbaloneGraph.get().getVertex('B', 3);
		Line line1 = new Line();
		Line line2 = new Line();
		ArrayList <Line> testList = new ArrayList<Line>();
		ArrayList <Line> secondTestList = new ArrayList<Line>();

		line1.addToLine(node1);
		line1.addToLine(node2);
		line2.addToLine(node3);
		line2.addToLine(node4);
		testList.add(line1);
		secondTestList.add(line2);

		assertEquals (lc.getLines(2, MarbleColor.BLACK), testList);
		assertEquals (lc.getLines(2, MarbleColor.WHITE), secondTestList);
	}
	
	@Test
	public void Test_Line_of_2_and_3() throws IOException {
		//(C 4):(D 4) (B 3)\n(D 4):(E 4) (C 4)
		Layer layer = new Layer();
		test2NodeAdder(layer);
		LineComputer lc = new LineComputer(layer);
	
		Node node1 = AbaloneGraph.get().getVertex('C', 4);
		Node node2 = AbaloneGraph.get().getVertex('D', 4);
		Node node3 = AbaloneGraph.get().getVertex('B', 3);
		Node node4 = AbaloneGraph.get().getVertex('E', 4);
		Line line1 = new Line();
		Line line2 = new Line();
		Line line3 = new Line();
		ArrayList<Line> testList = new ArrayList<Line>();
		ArrayList <Line> testListOf3 = new ArrayList<Line>();
		
		assertEquals(lc.getLines(2, MarbleColor.WHITE), testList);
		assertEquals(lc.getLines(3, MarbleColor.WHITE), testListOf3);
		
		line1.addToLine(node1);
		line1.addToLine(node3);
		line2.addToLine(node1);
		line2.addToLine(node2);
		line3.addToLine(node2);
		line3.addToLine(node4);
		testList.add(line1);
		testList.add(line2);
		testList.add(line3);

		assertEquals(lc.getLines(2, MarbleColor.BLACK), testList);

		line2.addToLine(node4);
		testListOf3.add(line2);
		assertEquals(lc.getLines(3, MarbleColor.BLACK), testListOf3);		
	}
	
	@Test
	public void Test_1_Line_of_2() throws IOException {
		//(D 8):(D 7)
		Layer layer = new Layer();
		test3NodeAdder(layer);
		LineComputer lc = new LineComputer(layer);
		
		Node node1 = AbaloneGraph.get().getVertex('D', 8);
		Node node2 = AbaloneGraph.get().getVertex('D', 7);
		Line line1 = new Line();
		ArrayList<Line> testList = new ArrayList<Line>();
		
		assertEquals(lc.getLines(2, MarbleColor.BLACK), testList);
		
		line1.addToLine(node1);
		line1.addToLine(node2);
		testList.add(line1);
		assertEquals(lc.getLines(2, MarbleColor.WHITE), testList);
	}

	@Test
	public void Test_Mirror_Line() throws IOException {
		//(D 8):(D 7)\n(D 7):(D 8)
		Layer layer = new Layer();
		test4NodeAdder(layer);
		LineComputer lc = new LineComputer(layer);

		
		Node node1= AbaloneGraph.get().getVertex('D', 8);
		Node node2 = AbaloneGraph.get().getVertex('D', 7);
		Line line1 = new Line();
		ArrayList<Line> testList = new ArrayList<Line>();
		line1.addToLine(node1);
		line1.addToLine(node2);
		testList.add(line1);

		assertEquals(lc.getLines(2, MarbleColor.BLACK), testList);
	}
	@Test
	public void Test_Standard_Lines_On_Graph() throws IOException {

		Layer layer = Layer.getDefaultBoard();
		LineComputer lc = new LineComputer(layer);

		Node node1 = AbaloneGraph.get().getVertex('A', 2);
		Node node2 = AbaloneGraph.get().getVertex('B', 2);
		Node node3 = AbaloneGraph.get().getVertex('C', 2);
		Node node4 = AbaloneGraph.get().getVertex('D', 2);
		Node node5 = AbaloneGraph.get().getVertex('E', 2);
		Node node6 = AbaloneGraph.get().getVertex('F', 2);
		Node node7 = AbaloneGraph.get().getVertex('D', 8);
		Node node8 = AbaloneGraph.get().getVertex('E', 8);
		Node node9 = AbaloneGraph.get().getVertex('F', 8);
		Node node10 = AbaloneGraph.get().getVertex('G', 8);
		Node node11 = AbaloneGraph.get().getVertex('H', 8);
		Node node12 = AbaloneGraph.get().getVertex('I', 8);

		Line line1 = new Line();
		Line line2 = new Line();

		line1.addToLine(node1);
		line1.addToLine(node2);
		line1.addToLine(node3);
		line1.addToLine(node4);
		line1.addToLine(node5);
		line1.addToLine(node6);

		line2.addToLine(node7);
		line2.addToLine(node8);
		line2.addToLine(node9);
		line2.addToLine(node10);
		line2.addToLine(node11);
		line2.addToLine(node12);

		ArrayList<Line> testList = new ArrayList<Line>();
		ArrayList<Line> secondTestList = new ArrayList<Line>();
		testList.add(line1);
		secondTestList.add(line2);

		assertEquals (lc.getLines(6, MarbleColor.BLACK), testList);
		assertEquals (lc.getLines(6, MarbleColor.WHITE), secondTestList);
	}

}
