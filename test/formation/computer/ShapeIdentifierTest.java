package formation.computer;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import board.Layer;
import board.Marble.MarbleColor;
import formation.shape.Line;
import formation.shape.Parallelogram;
import formation.shape.Triangle;
import graph.AbaloneGraph;
import graph.Node;

class ShapeIdentifierTest {

	public void test2NodeAdder(Layer layer){
		
		layer.addWhite('A', 1);
		layer.addWhite('A', 2);
		layer.addWhite('A', 3);
		layer.addWhite('B', 1);
		layer.addWhite('B', 2);
		layer.addWhite('B', 3);
		layer.addWhite('C', 1);
		layer.addWhite('C', 2);
		layer.addWhite('C', 3);
	}

	public void test3NodeAdder(Layer layer) {
		
		layer.addBlack('A', 1);
		layer.addBlack('A', 2);
		layer.addBlack('A', 3);
		layer.addBlack('B', 1);
		layer.addBlack('B', 3);
		layer.addBlack('C', 1);
		layer.addBlack('C', 2);
		layer.addBlack('C', 3);
	}
	
	public void hexNodeAdder (Layer layer) {
		test2NodeAdder(layer);
		layer.remove('A', 3);
		layer.remove('C', 1);
	}

	public void multicolorNodeAdder(Layer layer) {
		
		layer.addWhite('A', 1);
		layer.addWhite('A', 2);
		layer.addWhite('A', 3);
		layer.addBlack('B', 1);
		layer.addBlack('B', 2);
		layer.addWhite('B', 3);
		layer.addBlack('C', 1);
		layer.addBlack('C', 2);
		layer.addWhite('C', 3);
	}
	
	void BigTriangleTrapNodeSet(Layer layer) {
		multicolorNodeAdder(layer);
		layer.remove('B', 2);
		layer.addWhite('B', 2);
	}

	@Test
	void test_isHex() throws IOException {
		
		//tests default board with pieces		
		Layer layer = Layer.getDefaultBoard();
		ShapeIdentifier si = new ShapeIdentifier(layer);

		Node diamondNode = new Node ('A', 1);
		assertFalse(si.isHex((diamondNode)));

		Node triNode = new Node ('B', 1);
		assertFalse(si.isHex((triNode)));

		Node trapNode = new Node ('C', 4);
		assertFalse(si.isHex((trapNode)));

		Node hexNode = new Node ('C', 2);
		assertTrue(si.isHex((hexNode)));
	}

	@Test
	void test_getVs_in_a_parallelogram () throws IOException {
		Layer layer = new Layer();
		test2NodeAdder(layer);
		ShapeIdentifier si = new ShapeIdentifier(layer);

		Node node1 = AbaloneGraph.get().getVertex('A', 1);
		Node node2 = AbaloneGraph.get().getVertex('B', 1);
		Node node3 = AbaloneGraph.get().getVertex('C', 1);
		Node node4 = AbaloneGraph.get().getVertex('A', 2);
		Node node5 = AbaloneGraph.get().getVertex('B', 2);
		Node node6 = AbaloneGraph.get().getVertex('C', 2);
		Node node7 = AbaloneGraph.get().getVertex('A', 3);
		Node node8 = AbaloneGraph.get().getVertex('B', 3);
		Node node9 = AbaloneGraph.get().getVertex('C', 3);

		Line line1 = new Line();
		Line line2 = new Line();
		Line line3 = new Line();
		Line line4 = new Line();
		Line line5 = new Line();

		ArrayList<Line> V1 = new ArrayList<Line>();
		ArrayList<Line> V2 = new ArrayList<Line>();
		ArrayList<Line> V3 = new ArrayList<Line>();
		ArrayList<Line> V4 = new ArrayList<Line>();
		ArrayList<Line> V5 = new ArrayList<Line>();
		ArrayList<Line> V6 = new ArrayList<Line>();

		ArrayList<ArrayList<Line>> testList = new ArrayList<ArrayList<Line>>();

		line1.addToLine(node1);
		line1.addToLine(node4);
		line1.addToLine(node7);
		line2.addToLine(node1);
		line2.addToLine(node2);
		line2.addToLine(node3);
		line3.addToLine(node3);
		line3.addToLine(node6);
		line3.addToLine(node9);
		line4.addToLine(node7);
		line4.addToLine(node8);
		line4.addToLine(node9);
		line5.addToLine(node1);
		line5.addToLine(node5);
		line5.addToLine(node9);

		V1.add(line2);
		V1.add(line5);
		V2.add(line2);
		V2.add(line3);
		V3.add(line1);
		V3.add(line5);
		V4.add(line1);
		V4.add(line4);
		V5.add(line5);
		V5.add(line3);
		V6.add(line5);
		V6.add(line4);
		
		assertEquals(si.getVs(MarbleColor.BLACK), testList);

		testList.add(V1);
		testList.add(V2);
		testList.add(V3);
		testList.add(V4);
		testList.add(V5);
		testList.add(V6);

		assertEquals(si.getVs(MarbleColor.WHITE), testList);
	}

	@Test
	void test_2_Vs_Connected() throws IOException {
		Layer layer = new Layer();
		test3NodeAdder(layer);
		ShapeIdentifier si = new ShapeIdentifier(layer);

		Node node1 = AbaloneGraph.get().getVertex('A', 1);
		Node node2 = AbaloneGraph.get().getVertex('B', 1);
		Node node3 = AbaloneGraph.get().getVertex('C', 1);
		Node node4 = AbaloneGraph.get().getVertex('A', 2);
		Node node5 = AbaloneGraph.get().getVertex('C', 2);
		Node node6 = AbaloneGraph.get().getVertex('A', 3);
		Node node7 = AbaloneGraph.get().getVertex('B', 3);
		Node node8 = AbaloneGraph.get().getVertex('C', 3);

		Line line1 = new Line();
		Line line2 = new Line();
		Line line3 = new Line();
		Line line4 = new Line();

		ArrayList<Line> V1 = new ArrayList<Line>();
		ArrayList<Line> V2 = new ArrayList<Line>();

		ArrayList<ArrayList<Line>> testList = new ArrayList<ArrayList<Line>>();

		line1.addToLine(node1);
		line1.addToLine(node2);
		line1.addToLine(node3);
		line2.addToLine(node1);
		line2.addToLine(node4);
		line2.addToLine(node6);
		line3.addToLine(node6);
		line3.addToLine(node7);
		line3.addToLine(node8);
		line4.addToLine(node3);
		line4.addToLine(node5);
		line4.addToLine(node8);

		V1.add(line1);
		V1.add(line4);
		V2.add(line2);
		V2.add(line3);

		testList.add(V1);
		testList.add(V2);

		assertEquals(si.getVs(MarbleColor.BLACK), testList);
	}

	@Test
	void test_Multicolor_parallelogram() throws IOException {
		Layer layer = new Layer();
		multicolorNodeAdder(layer);
		ShapeIdentifier si = new ShapeIdentifier(layer);
		

		Node node1 = AbaloneGraph.get().getVertex('A', 1);
		Node node2 = AbaloneGraph.get().getVertex('A', 2);
		Node node3 = AbaloneGraph.get().getVertex('A', 3);
		Node node4 = AbaloneGraph.get().getVertex('B', 3);
		Node node5 = AbaloneGraph.get().getVertex('C', 3);

		Line line1 = new Line();
		Line line2 = new Line();

		line1.addToLine(node1);
		line1.addToLine(node2);
		line1.addToLine(node3);
		line2.addToLine(node3);
		line2.addToLine(node4);
		line2.addToLine(node5);

		ArrayList<Line> V1 = new ArrayList<Line>();
		ArrayList<ArrayList<Line>> testList = new ArrayList<ArrayList<Line>>();
		
		assertEquals(si.getVs(MarbleColor.BLACK), testList);
		
		V1.add(line1);
		V1.add(line2);
		testList.add(V1);

		assertEquals(si.getVs(MarbleColor.WHITE), testList);
	}

	@Test
	void small_Triangles_in_a_Parallelogram() throws IOException {
		Layer layer = new Layer();
		test2NodeAdder(layer);
		ShapeIdentifier si = new ShapeIdentifier(layer);
		
		Node node1 = AbaloneGraph.get().getVertex('A', 1);
		Node node2 = AbaloneGraph.get().getVertex('B', 1);
		Node node3 = AbaloneGraph.get().getVertex('C', 1);
		Node node4 = AbaloneGraph.get().getVertex('A', 2);
		Node node5 = AbaloneGraph.get().getVertex('B', 2);
		Node node6 = AbaloneGraph.get().getVertex('C', 2);
		Node node7 = AbaloneGraph.get().getVertex('A', 3);
		Node node8 = AbaloneGraph.get().getVertex('B', 3);
		Node node9 = AbaloneGraph.get().getVertex('C', 3);

		Triangle tri1 = new Triangle();
		Triangle tri2 = new Triangle();
		Triangle tri3 = new Triangle();
		Triangle tri4 = new Triangle();
		Triangle tri5 = new Triangle();
		Triangle tri6 = new Triangle();
		Triangle tri7 = new Triangle();
		Triangle tri8 = new Triangle();

		tri1.addToTriangle(node1);
		tri1.addToTriangle(node2);
		tri1.addToTriangle(node5);
		tri2.addToTriangle(node1);
		tri2.addToTriangle(node4);
		tri2.addToTriangle(node5);
		tri3.addToTriangle(node2);
		tri3.addToTriangle(node3);
		tri3.addToTriangle(node6);
		tri4.addToTriangle(node2);
		tri4.addToTriangle(node5);
		tri4.addToTriangle(node6);
		tri5.addToTriangle(node4);
		tri5.addToTriangle(node5);
		tri5.addToTriangle(node8);
		tri6.addToTriangle(node4);
		tri6.addToTriangle(node7);
		tri6.addToTriangle(node8);
		tri7.addToTriangle(node5);
		tri7.addToTriangle(node6);
		tri7.addToTriangle(node9);
		tri8.addToTriangle(node5);
		tri8.addToTriangle(node8);
		tri8.addToTriangle(node9);

		ArrayList<Triangle> testList = new ArrayList<Triangle>();
		
		assertEquals(si.getSmallTriangles(MarbleColor.BLACK), testList);

		testList.add(tri1);
		testList.add(tri2);
		testList.add(tri3);
		testList.add(tri4);
		testList.add(tri5);
		testList.add(tri6);
		testList.add(tri7);
		testList.add(tri8);

		assertEquals(si.getSmallTriangles(MarbleColor.WHITE), testList);
	}

	@Test
	void test_Multicolor_Traingles() throws IOException {
		Layer layer = new Layer();
		multicolorNodeAdder(layer);
		ShapeIdentifier si = new ShapeIdentifier(layer);

		Node node1 = AbaloneGraph.get().getVertex('A', 2);
		Node node2 = AbaloneGraph.get().getVertex('A', 3);
		Node node3 = AbaloneGraph.get().getVertex('B', 3);
		Node node4 = AbaloneGraph.get().getVertex('B', 1);
		Node node5 = AbaloneGraph.get().getVertex('B', 2);
		Node node6 = AbaloneGraph.get().getVertex('C', 1);
		Node node7 = AbaloneGraph.get().getVertex('C', 2);

		Triangle tri1 = new Triangle();
		Triangle tri2 = new Triangle();
		Triangle tri3 = new Triangle();

		tri1.addToTriangle(node4);
		tri1.addToTriangle(node6);
		tri1.addToTriangle(node7);
		tri2.addToTriangle(node4);
		tri2.addToTriangle(node5);
		tri2.addToTriangle(node7);
		tri3.addToTriangle(node1);
		tri3.addToTriangle(node2);
		tri3.addToTriangle(node3);

		ArrayList<Triangle> testList = new ArrayList<Triangle>();
		ArrayList<Triangle> secondTestList = new ArrayList<Triangle>();


		testList.add(tri1);
		testList.add(tri2);
		secondTestList.add(tri3);
		
		assertEquals(si.getSmallTriangles(MarbleColor.BLACK), testList);
		assertEquals(si.getSmallTriangles(MarbleColor.WHITE), secondTestList);
		
	}
	
	@Test
	void test_traps_in_a_hexagon() throws IOException{
		Layer layer = new Layer();
		hexNodeAdder(layer);
		ShapeIdentifier si = new ShapeIdentifier(layer);

		Node node1 = AbaloneGraph.get().getVertex('A', 1);
		Node node2 = AbaloneGraph.get().getVertex('B', 1);
		Node node3 = AbaloneGraph.get().getVertex('A', 2);
		Node node4 = AbaloneGraph.get().getVertex('B', 2);
		Node node5 = AbaloneGraph.get().getVertex('C', 2);
		Node node6 = AbaloneGraph.get().getVertex('B', 3);
		Node node7 = AbaloneGraph.get().getVertex('C', 3);
		
		Line line1 = new Line();
		Line line2 = new Line();
		Line line3 = new Line();
		Line line4 = new Line();
		Line line5 = new Line();
		Line line6 = new Line();
		Line line7 = new Line();
		Line line8 = new Line();
		Line line9 = new Line();
		
		line1.addToLine(node1);
		line1.addToLine(node2);
		line2.addToLine(node2);
		line2.addToLine(node5);
		line3.addToLine(node5);
		line3.addToLine(node7);
		line4.addToLine(node7);
		line4.addToLine(node6);
		line5.addToLine(node6);
		line5.addToLine(node3);
		line6.addToLine(node3);
		line6.addToLine(node1);
		line7.addToLine(node1);
		line7.addToLine(node4);
		line7.addToLine(node7);
		line8.addToLine(node2);
		line8.addToLine(node4);
		line8.addToLine(node6);
		line9.addToLine(node3);
		line9.addToLine(node4);
		line9.addToLine(node5);
		
		ArrayList<Line> Trap1 = new ArrayList<Line>();
		ArrayList<Line> Trap2 = new ArrayList<Line>();
		ArrayList<Line> Trap3 = new ArrayList<Line>();
		ArrayList<Line> Trap4 = new ArrayList<Line>();
		ArrayList<Line> Trap5 = new ArrayList<Line>();
		ArrayList<Line> Trap6 = new ArrayList<Line>();
		ArrayList<ArrayList<Line>> testTraps = new ArrayList<ArrayList<Line>>();
		
		Trap1.add(line1);
		Trap1.add(line9);
		Trap2.add(line6);
		Trap2.add(line8);
		Trap3.add(line7);
		Trap3.add(line2);
		Trap4.add(line7);
		Trap4.add(line5);
		Trap5.add(line8);
		Trap5.add(line3);
		Trap6.add(line9);
		Trap6.add(line4);
		
		testTraps.add(Trap1);
		testTraps.add(Trap2);
		testTraps.add(Trap3);
		testTraps.add(Trap4);
		testTraps.add(Trap5);
		testTraps.add(Trap6);
		
		assertEquals(si.getTrapizoids(MarbleColor.WHITE), testTraps);
		
	}
	@Test
	void test_MulitColorNodeAdder_Trap() throws IOException {
		Layer layer = new Layer();
		multicolorNodeAdder(layer);
		ShapeIdentifier si = new ShapeIdentifier(layer);
		
		ArrayList<ArrayList<Line>> testTraps = new ArrayList<ArrayList<Line>>();
		
		assertEquals(si.getTrapizoids(MarbleColor.BLACK), testTraps);
		assertEquals(si.getTrapizoids(MarbleColor.WHITE), testTraps);
	}
	
	@Test
	void test_single_Black_Trap() throws IOException {
		Layer layer = new Layer();
		BigTriangleTrapNodeSet(layer);
		ShapeIdentifier si = new ShapeIdentifier(layer);

		Node node1 = AbaloneGraph.get().getVertex('A', 1);
		Node node2 = AbaloneGraph.get().getVertex('B', 2);
		Node node3 = AbaloneGraph.get().getVertex('C', 3);
		Node node4 = AbaloneGraph.get().getVertex('A', 2);
		Node node5 = AbaloneGraph.get().getVertex('B', 3);
		Node node6 = AbaloneGraph.get().getVertex('A', 3);
		
		Line line1 = new Line();
		Line line2 = new Line();
		Line line3 = new Line();
		Line line4 = new Line();
		Line line5 = new Line();
		Line line6 = new Line();
		
		line1.addToLine(node1);
		line1.addToLine(node2);
		line1.addToLine(node3);
		line2.addToLine(node4);
		line2.addToLine(node5);
		line3.addToLine(node2);
		line3.addToLine(node5);
		line4.addToLine(node1);
		line4.addToLine(node4);
		line4.addToLine(node6);
		line5.addToLine(node4);
		line5.addToLine(node2);
		line6.addToLine(node3);
		line6.addToLine(node5);
		line6.addToLine(node6);
		
		ArrayList<Line> Trap1 = new ArrayList<Line>();
		ArrayList<Line> Trap2 = new ArrayList<Line>();
		ArrayList<Line> Trap3 = new ArrayList<Line>();
		
		Trap1.add(line5);
		Trap1.add(line6);
		Trap2.add(line1);
		Trap2.add(line2);
		Trap3.add(line4);
		Trap3.add(line3);
		
		ArrayList<ArrayList<Line>> testTraps = new ArrayList<ArrayList<Line>>();
		
		assertEquals(si.getTrapizoids(MarbleColor.BLACK), testTraps);
		
		testTraps.add(Trap1);
		testTraps.add(Trap2);
		testTraps.add(Trap3);
		
		assertEquals(si.getTrapizoids(MarbleColor.WHITE), testTraps);
		
	}
	
	@Test
	void All_Parallelograms_Test() throws IOException {
		Layer layer = new Layer();
		test2NodeAdder(layer);
		ShapeIdentifier si = new ShapeIdentifier(layer);
		
		
		Node node1 = AbaloneGraph.get().getVertex('A', 1);
		Node node2 = AbaloneGraph.get().getVertex('A', 2);
		Node node3 = AbaloneGraph.get().getVertex('A', 3);
		Node node4 = AbaloneGraph.get().getVertex('B', 1);
		Node node5 = AbaloneGraph.get().getVertex('B', 2);
		Node node6 = AbaloneGraph.get().getVertex('B', 3);
		Node node7 = AbaloneGraph.get().getVertex('C', 1);
		Node node8 = AbaloneGraph.get().getVertex('C', 2);
		Node node9 = AbaloneGraph.get().getVertex('C', 3);
		
		Line line1 = new Line();
		Line line2 = new Line();
		Line line3 = new Line();
		Line line4 = new Line();
		Line line5 = new Line();
		Line line6 = new Line();
		Line line7 = new Line();
		Line line8 = new Line();
		Line line9 = new Line();
		Line line10 = new Line();
		
		line1.addToLine(node1);
		line1.addToLine(node4);
		line2.addToLine(node2);
		line2.addToLine(node5);
		line3.addToLine(node3);
		line3.addToLine(node6);
		line4.addToLine(node4);
		line4.addToLine(node7);
		line5.addToLine(node5);
		line5.addToLine(node8);
		line6.addToLine(node6);
		line6.addToLine(node9);
		line7.addToLine(node1);
		line7.addToLine(node2);
		line8.addToLine(node5);
		line8.addToLine(node6);
		line9.addToLine(node4);
		line9.addToLine(node5);
		line10.addToLine(node8);
		line10.addToLine(node9);
		
		Parallelogram para1 = new Parallelogram();
		Parallelogram para2 = new Parallelogram();
		Parallelogram para3 = new Parallelogram();
		Parallelogram para4 = new Parallelogram();
		Parallelogram para5 = new Parallelogram();
		Parallelogram para6 = new Parallelogram();
		Parallelogram para7 = new Parallelogram();
		Parallelogram para8 = new Parallelogram();
		ArrayList<Parallelogram> testParas = new ArrayList<Parallelogram>();
		
		assertEquals(si.getParallelograms(1, MarbleColor.BLACK), testParas);

		para1.addToParallelogram(line1);
		para1.addToParallelogram(line2);
		para2.addToParallelogram(line1);
		para2.addToParallelogram(line5);
		para3.addToParallelogram(line4);
		para3.addToParallelogram(line5);
		para4.addToParallelogram(line2);
		para4.addToParallelogram(line3);
		para5.addToParallelogram(line2);
		para5.addToParallelogram(line6);
		para6.addToParallelogram(line5);
		para6.addToParallelogram(line6);
		para7.addToParallelogram(line7);
		para7.addToParallelogram(line8);
		para8.addToParallelogram(line9);
		para8.addToParallelogram(line10);
		
		testParas.add(para1);
		testParas.add(para2);
		testParas.add(para7);
		testParas.add(para3);
		testParas.add(para8);
		testParas.add(para4);
		testParas.add(para5);
		testParas.add(para6);
		
		assertEquals(si.getParallelograms(1, MarbleColor.WHITE), testParas);
		
		Line line11 = new Line();
		
		line1.addToLine(node7);
		line2.addToLine(node8);
		line3.addToLine(node9);
		line7.addToLine(node3);
		line9.addToLine(node6);
		line11.addToLine(node7);
		line11.addToLine(node8);
		line11.addToLine(node9);
		
		Parallelogram para9 = new Parallelogram();
		Parallelogram para10 = new Parallelogram();
		Parallelogram para11 = new Parallelogram();
		Parallelogram para12 = new Parallelogram();
		
		para9.addToParallelogram(line1);
		para9.addToParallelogram(line2);
		para10.addToParallelogram(line7);
		para10.addToParallelogram(line9);
		para11.addToParallelogram(line9);
		para11.addToParallelogram(line11);
		para12.addToParallelogram(line2);
		para12.addToParallelogram(line3);
		
		ArrayList<Parallelogram> testParasType2 = new ArrayList<Parallelogram>();
		
		testParasType2.add(para9);
		testParasType2.add(para10);
		testParasType2.add(para11);
		testParasType2.add(para12);
		
		assertEquals(si.getParallelograms(2, MarbleColor.WHITE), testParasType2);
		
		ArrayList<Parallelogram> testParasType3 = new ArrayList<Parallelogram>();
		
		para9.addToParallelogram(line3);
		testParasType3.add(para9);
		
		assertEquals(si.getParallelograms(3,MarbleColor.WHITE), testParasType3);
	}
	
	@Test
	void test_MultiColor_Parallelogram() throws IOException {
		Layer layer = new Layer();
		multicolorNodeAdder(layer);
		ShapeIdentifier si = new ShapeIdentifier(layer);
		
		Node node1 = AbaloneGraph.get().getVertex('B', 1);
		Node node2 = AbaloneGraph.get().getVertex('B', 2);
		Node node3 = AbaloneGraph.get().getVertex('C', 1);
		Node node4 = AbaloneGraph.get().getVertex('C', 2);
		
		Line line1 = new Line();
		Line line2 = new Line();
		
		line1.addToLine(node1);
		line1.addToLine(node3);
		line2.addToLine(node2);
		line2.addToLine(node4);
		
		Parallelogram para = new Parallelogram();
		ArrayList<Parallelogram> testParas = new ArrayList<Parallelogram>();
		
		assertEquals(si.getParallelograms(1, MarbleColor.WHITE), testParas);
		
		para.addToParallelogram(line1);
		para.addToParallelogram(line2);
		testParas.add(para);
		
		assertEquals(si.getParallelograms(1, MarbleColor.BLACK), testParas);
	}
}
