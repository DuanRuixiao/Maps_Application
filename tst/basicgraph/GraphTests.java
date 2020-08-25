package basicgraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import util.GraphLoader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GraphTests {
    private final GraphAdjList graphAdjList1;
    private final GraphAdjMatrix graphAdjMatrix1;
    private final GraphAdjList graphAdjList2;
    private final GraphAdjMatrix graphAdjMatrix2;

    public GraphTests() {
        // Use simpletest.map and routesUA.dat for testing.
        // Construct GraphAdjList and GraphAdjMatrix respectively.
        graphAdjList1 = new GraphAdjList();
        graphAdjMatrix1 = new GraphAdjMatrix();
        GraphLoader.loadRoadMap("data/testdata/simpletest.map", graphAdjList1);
        GraphLoader.loadRoutes("data/airports/routesUA.dat", graphAdjMatrix1);

        // Construct two simpler test cases.
        graphAdjList2 = new GraphAdjList();
        for (int i = 0; i < 5; i++) {
            graphAdjList2.addVertex();
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 3; j < 5; j++) {
                graphAdjList2.addEdge(i, j);
            }
        }

        graphAdjMatrix2 = new GraphAdjMatrix();
        for (int i = 0; i < 5; i++) {
            graphAdjMatrix2.addVertex();
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 3; j < 5; j++) {
                graphAdjMatrix2.addEdge(i, j);
            }
        }
    }

    @Test
    public void getNumVerticesTest() {
        assertEquals(9, graphAdjList1.getNumVertices());
        assertEquals(432, graphAdjMatrix1.getNumVertices());
    }

    @Test
    public void getNumEdgesTest() {
        assertEquals(22, graphAdjList1.getNumEdges());
        assertEquals(2180, graphAdjMatrix1.getNumEdges());
    }
    
    @Test
    public void addVertexTest() {
        assertEquals(5, graphAdjList2.getNumVertices());
        assertEquals(5, graphAdjMatrix2.getNumVertices());
    }
    
    @Test
    public void addEdgeTest() {
        assertEquals(6, graphAdjList2.getNumEdges());
        assertEquals(6, graphAdjMatrix2.getNumEdges());
    }
    
    @Test
    public void getNeighborsTest() {
        List<Integer> graphAdjList1Neighbors = new ArrayList<Integer>(
                Arrays.asList(1)
        );
        assertEquals(graphAdjList1Neighbors, graphAdjList1.getNeighbors(0));

        List<Integer> graphAdjMatrix1Neighbors = new ArrayList<Integer>(
                Arrays.asList(1)
        );
        assertEquals(graphAdjMatrix1Neighbors, graphAdjMatrix1.getNeighbors(0));
    }

    @Test
    public void getInNeighborsTest() {
        List<Integer> graphAdjList1Neighbors = new ArrayList<Integer>(
                Arrays.asList(1)
        );
        assertEquals(graphAdjList1Neighbors, graphAdjList1.getInNeighbors(0));

        List<Integer> graphAdjMatrix1Neighbors = new ArrayList<Integer>(
                Arrays.asList(1)
        );
        assertEquals(graphAdjMatrix1Neighbors, graphAdjMatrix1.getInNeighbors(0));
    }

    @Test
    public void degreeSequenceTest() {
        List<Integer> graphAdjListDegreeSequence = new ArrayList<Integer>(
                Arrays.asList(10, 6, 6, 6, 4, 4, 4, 2, 2)
        );
        assertEquals(graphAdjListDegreeSequence, graphAdjList1.degreeSequence());

        List<Integer> graphAdjMatrixDegreeSequence = graphAdjMatrix1.degreeSequence();
        int numOfDegreeLargerThan100 = 0;
        for (Integer degree : graphAdjMatrixDegreeSequence) {
            if (degree > 100) {
                numOfDegreeLargerThan100++;
            }
        }
        assertEquals(7, numOfDegreeLargerThan100);
    }

    @Test
    public void getDistance2Test() {
        List<Integer> graphAdjListTwoHop = new ArrayList<Integer>(
                Arrays.asList(2, 3, 6, 7, 0)
        );
        assertEquals(graphAdjListTwoHop, graphAdjList1.getDistance2(0));

        List<Integer> graphAdjMatrixTwoHop = graphAdjMatrix1.getDistance2(0);
        assertEquals(161, graphAdjMatrixTwoHop.size());
    }

    @Test
    public void hasVertexTest() {
        assertTrue(graphAdjList1.hasVertex(4));
        assertFalse(graphAdjList1.hasVertex(100));
    }
}
