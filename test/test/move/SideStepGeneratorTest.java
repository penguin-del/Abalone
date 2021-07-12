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
import move.representation.SideStep;
import utilities.Equals;

class SideStepGeneratorTest {

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



