package roadgraph;

import java.util.*;
import java.util.function.Consumer;

import geography.GeographicEdge;
import geography.GeographicPoint;

/**
 * @author UCSD MOOC development team and Ruixiao Duan.
 * 
 * A class which represents a graph of geographic locations Nodes in the graph are intersections between.
 */
public class MapGraph {
	int numVertices;
	int numEdges;
	List<GeographicPoint> mapPoints;
	HashMap<GeographicPoint, HashMap<GeographicPoint, LinkedList<GeographicEdge>>> mapEdges;
	
	/** 
	 * Create a new empty MapGraph .
	 */
	public MapGraph() {
		numVertices = 0;
		numEdges = 0;
		mapPoints = new LinkedList<GeographicPoint>();
		mapEdges = new HashMap<GeographicPoint, HashMap<GeographicPoint, LinkedList<GeographicEdge>>>();
	}
	
	/**
	 * Get the number of vertices (road intersections) in the graph.
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices() {
		return numVertices;
	}
	
	/**
	 * Return the intersections, which are the vertices in this graph.
	 * @return The vertices in this graph as GeographicPoints.
	 */
	public Set<GeographicPoint> getVertices() {
		return new HashSet<GeographicPoint>(mapPoints);
	}
	
	/**
	 * Get the number of road segments in the graph.
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges() {
		return numEdges;
	}

	/** Add a node corresponding to an intersection at a Geographic Point.
	 * If the location is already in the graph or null, this method does not change the graph.
	 * @param location  The location of the intersection.
	 * @return true if a node was added, false if it was not (the node was already in the graph,
	 * or the parameter is null).
	 */
	public boolean addVertex(GeographicPoint location) {
		if (location == null || getVertices().contains(location)) {
			return false;
		}

		numVertices++;
		mapPoints.add(location);
		return true;
	}
	
	/**
	 * Adds a directed edge to the graph from pt1 to pt2.  
	 * Precondition: Both GeographicPoints have already been added to the graph.
	 * @param from The starting point of the edge.
	 * @param to The ending point of the edge.
	 * @param roadName The name of the road.
	 * @param roadType The type of the road.
	 * @param length The length of the road, in km.
	 * @throws IllegalArgumentException If the points have not already been added as nodes to the graph,
	 * if any of the arguments is null, or if the length is less than 0.
	 */
	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName, String roadType, double length)
			throws IllegalArgumentException {
		if (from == null || to == null || length < 0.0) {
			throw new IllegalArgumentException("Invalid edge to add. Please check points and length.");
		}

		numEdges++;
		GeographicEdge edge = new GeographicEdge(from, to, roadName, roadType, length);

		if (mapEdges.containsKey(from)) {
			LinkedList<GeographicEdge> neighbors;
			if (mapEdges.get(from).containsKey(to)) {
				neighbors = mapEdges.get(from).get(to);
				neighbors.add(edge);
			} else {
				neighbors = new LinkedList<GeographicEdge>(){{
					add(edge);
				}};
			}
			mapEdges.get(from).put(to, neighbors);
		} else {
			mapEdges.put(from, new HashMap<GeographicPoint, LinkedList<GeographicEdge>>(){{
				put(to, new LinkedList<GeographicEdge>(){{
					add(edge);
				}});
			}});
		}
	}

	/** Find the path from start to goal using breadth first search.
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest (unweighted) path from start to goal
	 * (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms.
        Consumer<GeographicPoint> temp = (x) -> {};
        return bfs(start, goal, temp);
	}
	
	/** Find the path from start to goal using breadth first search.
	 * 
	 * @param start The starting location.
	 * @param goal The goal location.
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest (unweighted) path from start to goal
	 * (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal,
									 Consumer<GeographicPoint> nodeSearched) {
		Queue<LinkedList<GeographicPoint>> queue = new LinkedList<LinkedList<GeographicPoint>>();
		LinkedList<GeographicPoint> initialPath = new LinkedList<GeographicPoint>(){{
			add(start);
		}};
		queue.add(initialPath);

		// do BFS search.
		while (!queue.isEmpty()) {
			LinkedList<GeographicPoint> path = queue.poll();
			GeographicPoint pt = path.getLast();

			if (pt.equals(goal)) {
				return path;
			}

			if (mapEdges.containsKey(pt)) {
				for (Map.Entry<GeographicPoint, LinkedList<GeographicEdge>> entry : mapEdges.get(pt).entrySet()) {
					LinkedList<GeographicPoint> updatedPath = new LinkedList<GeographicPoint>(path);
					updatedPath.add(entry.getKey());
					queue.add(updatedPath);
				}
			} else {
				return null;
			}
		}
		
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());

		return null;
	}

	/** Find the path from start to goal using Dijkstra's algorithm.
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		// You do not need to change this method.
        Consumer<GeographicPoint> temp = (x) -> {};
        return dijkstra(start, goal, temp);
	}
	
	/** Find the path from start to goal using Dijkstra's algorithm.
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.
	 * @return The list of intersections that form the shortest path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, 
										  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched) {
		// TODO: Implement this method in WEEK 4

		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		
		return null;
	}

	/** Find the path from start to goal using A-Star search.
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return aStarSearch(start, goal, temp);
	}
	
	/** Find the path from start to goal using A-Star search.
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, 
											 GeographicPoint goal, Consumer<GeographicPoint> nodeSearched) {
		// TODO: Implement this method in WEEK 4
		
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		
		return null;
	}
}
