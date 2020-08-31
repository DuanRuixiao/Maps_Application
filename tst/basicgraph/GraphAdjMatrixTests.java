package basicgraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import util.GraphLoader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GraphAdjMatrixTests {
    private final GraphAdjMatrix graphAdjMatrix1;
    private final GraphAdjMatrix graphAdjMatrix2;

    public GraphAdjMatrixTests() {
        // Use simpletest.map to construct GraphAdjMatrix
        graphAdjMatrix1 = new GraphAdjMatrix();
        GraphLoader.loadRoadMap("data/testdata/simpletest.map", graphAdjMatrix1);

        // Use routesUA.dat for testing to construct GraphAdjList.
        graphAdjMatrix2 = new GraphAdjMatrix();
        GraphLoader.loadRoutes("data/airports/routesUA.dat", graphAdjMatrix2);
    }

    @Test
    public void getNumVerticesTest() {
        assertEquals(9, graphAdjMatrix1.getNumVertices());
        assertEquals(432, graphAdjMatrix2.getNumVertices());
    }

    @Test
    public void getNumEdgesTest() {
        assertEquals(22, graphAdjMatrix1.getNumEdges());
        assertEquals(2180, graphAdjMatrix2.getNumEdges());
    }

    @Test
    public void addVertexTest() {
        int vertexInd1 = graphAdjMatrix1.addVertex();
        assertEquals(vertexInd1+1, graphAdjMatrix1.getNumVertices());

        int vertexInd2 = graphAdjMatrix2.addVertex();
        assertEquals(vertexInd2+1, graphAdjMatrix2.getNumVertices());
    }

    @Test
    public void addEdgeTest() {
        graphAdjMatrix1.addEdge(0, 2);
        assertEquals(23, graphAdjMatrix1.getNumEdges());

        graphAdjMatrix2.addEdge(0,2);
        assertEquals(2181, graphAdjMatrix2.getNumEdges());
    }

    @Test
    public void getNeighborsTest() {
        List<Integer> graphAdjMatrix1Neighbors = new ArrayList<Integer>(
                Collections.singletonList(1)
        );
        assertEquals(graphAdjMatrix1Neighbors, graphAdjMatrix1.getNeighbors(0));

        List<Integer> graphAdjMatrix2Neighbors = new ArrayList<Integer>(
                Collections.singletonList(1)
        );
        assertEquals(graphAdjMatrix2Neighbors, graphAdjMatrix2.getNeighbors(0));
    }

    @Test
    public void getInNeighborsTest() {
        List<Integer> graphAdjMatrix1Neighbors = new ArrayList<Integer>(
                Collections.singletonList(1)
        );
        assertEquals(graphAdjMatrix1Neighbors, graphAdjMatrix1.getInNeighbors(0));

        List<Integer> graphAdjMatrix2Neighbors = new ArrayList<Integer>(
                Collections.singletonList(1)
        );
        assertEquals(graphAdjMatrix2Neighbors, graphAdjMatrix2.getInNeighbors(0));
    }

    @Test
    public void degreeSequenceTest() {
        List<Integer> graphAdjMatrix1DegreeSequence = new ArrayList<Integer>(
                Arrays.asList(10, 6, 6, 6, 4, 4, 4, 2, 2)
        );
        assertEquals(graphAdjMatrix1DegreeSequence, graphAdjMatrix1.degreeSequence());

        List<Integer> graphAdjMatrix2DegreeSequence = graphAdjMatrix2.degreeSequence();
        int numOfDegreeLargerThan100 = 0;
        for (Integer degree : graphAdjMatrix2DegreeSequence) {
            if (degree > 100) {
                numOfDegreeLargerThan100++;
            }
        }
        assertEquals(7, numOfDegreeLargerThan100);
    }

    @Test
    public void getDistance2Test() {
        Set<Integer> graphAdjMatrix1TwoHop = new HashSet<Integer>(
                Arrays.asList(2, 3, 6, 7, 0)
        );
        assertEquals(graphAdjMatrix1TwoHop, new HashSet<Integer>(graphAdjMatrix1.getDistance2(0)));

        List<Integer> graphAdjMatrix2TwoHop = graphAdjMatrix2.getDistance2(0);
        assertEquals(161, graphAdjMatrix2TwoHop.size());
    }

    @Test
    public void hasVertexTest() {
        assertTrue(graphAdjMatrix1.hasVertex(4));
        assertFalse(graphAdjMatrix2.hasVertex(1000));
    }
}
