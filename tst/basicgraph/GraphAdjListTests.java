package basicgraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import util.GraphLoader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GraphAdjListTests {
    private final GraphAdjList graphAdjList1;
    private final GraphAdjList graphAdjList2;

    public GraphAdjListTests() {
        // Use simpletest.map for testing to construct GraphAdjList.
        graphAdjList1 = new GraphAdjList();
        GraphLoader.loadRoadMap("data/testdata/simpletest.map", graphAdjList1);

        // Use routesUA.dat for testing to construct GraphAdjList.
        graphAdjList2 = new GraphAdjList();
        GraphLoader.loadRoutes("data/airports/routesUA.dat", graphAdjList2);
    }

    @Test
    public void getNumVerticesTest() {
        assertEquals(9, graphAdjList1.getNumVertices());
        assertEquals(432, graphAdjList2.getNumVertices());
    }

    @Test
    public void getNumEdgesTest() {
        assertEquals(22, graphAdjList1.getNumEdges());
        assertEquals(2180, graphAdjList2.getNumEdges());
    }

    @Test
    public void addVertexTest() {
        int vertexInd1 = graphAdjList1.addVertex();
        assertEquals(vertexInd1+1, graphAdjList1.getNumVertices());

        int vertexInd2 = graphAdjList2.addVertex();
        assertEquals(vertexInd2+1, graphAdjList2.getNumVertices());
    }

    @Test
    public void addEdgeTest() {
        graphAdjList1.addEdge(0, 2);
        assertEquals(23, graphAdjList1.getNumEdges());

        graphAdjList2.addEdge(0,2);
        assertEquals(2181, graphAdjList2.getNumEdges());
    }

    @Test
    public void getNeighborsTest() {
        List<Integer> graphAdjList1Neighbors = new ArrayList<Integer>(
                Collections.singletonList(1)
        );
        assertEquals(graphAdjList1Neighbors, graphAdjList1.getNeighbors(0));

        List<Integer> graphAdjList2Neighbors = new ArrayList<Integer>(
                Collections.singletonList(1)
        );
        assertEquals(graphAdjList2Neighbors, graphAdjList2.getNeighbors(0));
    }

    @Test
    public void getInNeighborsTest() {
        List<Integer> graphAdjList1Neighbors = new ArrayList<Integer>(
                Collections.singletonList(1)
        );
        assertEquals(graphAdjList1Neighbors, graphAdjList1.getInNeighbors(0));

        List<Integer> graphAdjList2Neighbors = new ArrayList<Integer>(
                Collections.singletonList(1)
        );
        assertEquals(graphAdjList2Neighbors, graphAdjList2.getInNeighbors(0));
    }

    @Test
    public void degreeSequenceTest() {
        List<Integer> graphAdjList1DegreeSequence = new ArrayList<Integer>(
                Arrays.asList(10, 6, 6, 6, 4, 4, 4, 2, 2)
        );
        assertEquals(graphAdjList1DegreeSequence, graphAdjList1.degreeSequence());

        List<Integer> graphAdjList2DegreeSequence = graphAdjList2.degreeSequence();
        int numOfDegreeLargerThan100 = 0;
        for (Integer degree : graphAdjList2DegreeSequence) {
            if (degree > 100) {
                numOfDegreeLargerThan100++;
            }
        }
        assertEquals(7, numOfDegreeLargerThan100);
    }

    @Test
    public void getDistance2Test() {
        List<Integer> graphAdjList1TwoHop = new ArrayList<Integer>(
                Arrays.asList(2, 3, 6, 7, 0)
        );
        assertEquals(graphAdjList1TwoHop, graphAdjList1.getDistance2(0));

        List<Integer> graphAdjList2TwoHop = graphAdjList2.getDistance2(0);
        assertEquals(161, graphAdjList2TwoHop.size());
    }

    @Test
    public void hasVertexTest() {
        assertTrue(graphAdjList1.hasVertex(4));
        assertFalse(graphAdjList2.hasVertex(10000));
    }
}
