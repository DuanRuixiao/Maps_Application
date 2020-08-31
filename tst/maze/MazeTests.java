package maze;

import org.junit.Test;

import util.MazeLoader;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MazeTests {
    private final String mazeFile = "data/mazes/maze1.maze";
    private final Maze maze;

    public MazeTests() {
        maze = new Maze();
        MazeLoader.loadMaze(mazeFile, maze);
    }

    @Test
    public void dfsTest() {
        List<MazeNode> path = maze.dfs(3, 3, 2, 0);

        List<MazeNode> expectedPath = new LinkedList<MazeNode>(){{
            add(new MazeNode(3, 3));
            add(new MazeNode(2, 3));
            add(new MazeNode(1, 3));
            add(new MazeNode(0, 3));
            add(new MazeNode(0, 2));
            add(new MazeNode(0, 1));
            add(new MazeNode(0, 0));
            add(new MazeNode(1, 0));
            add(new MazeNode(2, 0));
        }};

        assertEquals(expectedPath.size(), path.size());
        for (int i = 0; i < expectedPath.size(); i++) {
            assertEquals(expectedPath.get(i).getRow(), path.get(i).getRow());
            assertEquals(expectedPath.get(i).getColumn(), path.get(i).getColumn());
        }
    }

    @Test
    public void bfsTest() {
        List<MazeNode> path = maze.bfs(3, 3, 2, 0);

        List<MazeNode> expectedPath = new LinkedList<MazeNode>(){{
            add(new MazeNode(3, 3));
            add(new MazeNode(3, 2));
            add(new MazeNode(3, 1));
            add(new MazeNode(3, 0));
            add(new MazeNode(2, 0));
        }};

        assertEquals(expectedPath.size(), path.size());
        for (int i = 0; i < expectedPath.size(); i++) {
            assertEquals(expectedPath.get(i).getRow(), path.get(i).getRow());
            assertEquals(expectedPath.get(i).getColumn(), path.get(i).getColumn());
        }
    }
}
