package maze;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class MazeNodeTests {
    private MazeNode mazeNode;

    public MazeNodeTests() {
        mazeNode = new MazeNode(0, 0);
    }

    @Test
    public void displayCharTest() {
        mazeNode.setDisplayChar('S');

        assertEquals('S', mazeNode.getDisplayChar());
    }

    @Test
    public void neighborTest() {
        MazeNode neighbor = new MazeNode(1, 1);
        mazeNode.addNeighbor(neighbor);

        assertEquals(mazeNode.getNeighbors(), new LinkedList<MazeNode>(){{
            add(neighbor);
        }});
    }
}
