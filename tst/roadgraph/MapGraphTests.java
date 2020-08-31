package roadgraph;

import geography.GeographicPoint;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import util.GraphLoader;

public class MapGraphTests {
	private MapGraph mapGraph1;
	private MapGraph mapGraph2;

	public MapGraphTests() {
		mapGraph1 = new MapGraph();
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", mapGraph1);

		mapGraph2 = new MapGraph();
		GraphLoader.loadRoadMap("data/maps/utc.map", mapGraph2);
	}

	@Test
	public void addVertexTest() {
		GeographicPoint pt = new GeographicPoint(1.23, 4.56);
		mapGraph1.addVertex(pt);

		assertTrue(mapGraph1.getVertices().contains(pt));
	}

	@Test
	public void addEdgeTest() {
		int numEdge = mapGraph1.getNumEdges();

		GeographicPoint pt1 = new GeographicPoint(1.23, 4.56);
		GeographicPoint pt2 = new GeographicPoint(7.89, 10.11);
		String roadName = "dummyRoadName";
		String roadType = "dummyRoadType";
		double length = 3.14;

		mapGraph1.addEdge(pt1, pt2, roadName, roadType, length);
		assertEquals(numEdge + 1, mapGraph1.getNumEdges());
	}

	@Test
	public void bfsTest() {
		GeographicPoint testStart = new GeographicPoint(1.0, 1.0);
		GeographicPoint testEnd = new GeographicPoint(8.0, -1.0);

		List<GeographicPoint> pathByBFS = mapGraph1.bfs(testStart, testEnd);

		List<GeographicPoint> expectedBFSPath = new LinkedList<GeographicPoint>(){{
			add(new GeographicPoint(1,1));
			add(new GeographicPoint(4,1));
			add(new GeographicPoint(7,3));
			add(new GeographicPoint(8,-1));
		}};

		assertEquals(expectedBFSPath.size(), pathByBFS.size());

		for (int i = 0; i < expectedBFSPath.size(); i++) {
			assertEquals(expectedBFSPath.get(i).getX(), pathByBFS.get(i).getX(), 0.05);
			assertEquals(expectedBFSPath.get(i).getY(), pathByBFS.get(i).getY(), 0.05);
		}
	}

	@Test
	public void dijkstraTest() {

	}

	@Test
	public void aStarSearchTest() {

	}

    public static void main(String[] args) {
        System.out.print("Making a new map...");
        MapGraph firstMap = new MapGraph();
        System.out.print("DONE. \nLoading the map...");
        GraphLoader.loadRoadMap("data/testdata/simpletest.map", firstMap);
        System.out.println("DONE.");

        // You can use this method for testing.


        /* Here are some test cases you should try before you attempt
         * the Week 3 End of Week Quiz, EVEN IF you score 100% on the
         * programming assignment.
         */

		MapGraph simpleTestMap = new MapGraph();
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", simpleTestMap);

		GeographicPoint testStart = new GeographicPoint(1.0, 1.0);
		GeographicPoint testEnd = new GeographicPoint(8.0, -1.0);

		System.out.println("Test 1 using simpletest: Dijkstra should be 9 and AStar should be 5");
		List<GeographicPoint> testroute = simpleTestMap.dijkstra(testStart,testEnd);
		List<GeographicPoint> testroute2 = simpleTestMap.aStarSearch(testStart,testEnd);


		MapGraph testMap = new MapGraph();
		GraphLoader.loadRoadMap("data/maps/utc.map", testMap);

		// A very simple test using real data
		testStart = new GeographicPoint(32.869423, -117.220917);
		testEnd = new GeographicPoint(32.869255, -117.216927);
		System.out.println("Test 2 using utc: Dijkstra should be 13 and AStar should be 5");
		testroute = testMap.dijkstra(testStart,testEnd);
		testroute2 = testMap.aStarSearch(testStart,testEnd);


		// A slightly more complex test using real data
		testStart = new GeographicPoint(32.8674388, -117.2190213);
		testEnd = new GeographicPoint(32.8697828, -117.2244506);
		System.out.println("Test 3 using utc: Dijkstra should be 37 and AStar should be 10");
		testroute = testMap.dijkstra(testStart,testEnd);
		testroute2 = testMap.aStarSearch(testStart,testEnd);

        /* Use this code in Week 3 End of Week Quiz */
		/*MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);


		List<GeographicPoint> route = theMap.dijkstra(start,end);
		List<GeographicPoint> route2 = theMap.aStarSearch(start,end);

		*/
    }
}
