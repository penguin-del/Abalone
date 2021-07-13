package move.representation;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import board.Layer;
import board.Marble.MarbleColor;
import formation.shape.Line;
import formation.shape.Line.HexagonCardinalDirections;
import graph.AbaloneGraph;
import graph.Node;

class SideStepMoveTest {

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
		Layer newLayer = layer.getClone();
		sS.makeMoveOnOriginalBoard(newLayer);

		assertTrue(newLayer.contains('B',2)==MarbleColor.BLACK);
		assertTrue(newLayer.contains('C',2)==MarbleColor.BLACK);
		assertTrue(newLayer.contains('D',2)==MarbleColor.BLACK);

		assertTrue(newLayer.contains('C',3)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('D',3)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('E',3)==MarbleColor.EMPTY);

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
		Layer newLayer = layer.getClone();
		sS.makeMoveOnOriginalBoard(newLayer);

		assertTrue(newLayer.contains('B',3)==MarbleColor.BLACK);
		assertTrue(newLayer.contains('B',4)==MarbleColor.BLACK);
		assertTrue(newLayer.contains('B',5)==MarbleColor.BLACK);

		assertTrue(newLayer.contains('C',3)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('C',4)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('C',5)==MarbleColor.EMPTY);

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
		Layer newLayer = layer.getClone();
		sS.makeMoveOnOriginalBoard(newLayer);

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
		Layer newLayer = layer.getClone();
		sS.makeMoveOnOriginalBoard(newLayer);

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
		Layer newLayer = layer.getClone();
		sS.makeMoveOnOriginalBoard(newLayer);

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
		Layer newLayer = layer.getClone();
		sS.makeMoveOnOriginalBoard(newLayer);

		assertTrue(newLayer.contains('D',5)==MarbleColor.BLACK);
		assertTrue(newLayer.contains('E',6)==MarbleColor.BLACK);
		assertFalse(newLayer.contains('F',8)==MarbleColor.BLACK);

		assertTrue(newLayer.contains('D',4)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('E',5)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('C',5)==MarbleColor.EMPTY);

	}
	
	@Test
	void Test_SideStep_OnCopy() {
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
		
		Layer newLayer = sS.makeMoveOnCopyBoard(layer);

		assertTrue(newLayer.contains('B',2)==MarbleColor.BLACK);
		assertTrue(newLayer.contains('C',2)==MarbleColor.BLACK);
		assertTrue(newLayer.contains('D',2)==MarbleColor.BLACK);

		assertTrue(newLayer.contains('C',3)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('D',3)==MarbleColor.EMPTY);
		assertTrue(newLayer.contains('E',3)==MarbleColor.EMPTY);

	}

}
