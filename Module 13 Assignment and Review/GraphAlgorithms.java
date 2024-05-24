import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Your implementation of various graph traversal algorithms.
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * You may import/use java.util.Set, java.util.List, java.util.Queue, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you should use is the adjacency
     * list from graph. DO NOT create new instances of Map for BFS
     * (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * You may assume that the passed in start vertex and graph will not be null.
     * You may assume that the start vertex exists in the graph.
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin the bfs on.
     * @param graph The graph to search through.
     * @return List of vertices in visited order.
     */
    public static <T> List<Vertex<T>> bfs(Vertex<T> start, Graph<T> graph) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();
        // queue to enqueue the vertex in order
        Queue<Vertex<T>> queue = new LinkedList<>();
        // the return arrayList
        List<Vertex<T>> res = new ArrayList<>();
        // store the vertices we have visited already
        Set<Vertex<T>> visited = new HashSet<>();
        int size = graph.getVertices().size();
        queue.add(start);
        visited.add(start);
        while (queue.size() > 0) {
            Vertex<T> curr = queue.poll();
            res.add(curr);
            for (VertexDistance<T> destinationVertex : adjList.get(curr)) {
                Vertex<T> neighbor = destinationVertex.getVertex();
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }

        }
        return res;
    }

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * NOTE: This method should be implemented recursively. You may need to
     * create a helper method.
     *
     * You may import/use java.util.Set, java.util.List, and any classes that
     * implement the aforementioned interfaces, as long as they are efficient.
     *
     * The only instance of java.util.Map that you may use is the adjacency list
     * from graph. DO NOT create new instances of Map for DFS
     * (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * You may assume that the passed in start vertex and graph will not be null.
     * You may assume that the start vertex exists in the graph.
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin the dfs on.
     * @param graph The graph to search through.
     * @return List of vertices in visited order.
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();
        List<Vertex<T>> res = new ArrayList<>();
        Set<Vertex<T>> visited = new HashSet<>();
        dfsHelper(start, adjList, res, visited);
        return res;

    }
    private static <T> void dfsHelper(Vertex<T> curr, Map<Vertex<T>, List<VertexDistance<T>>> adjList, List<Vertex<T>> res, Set<Vertex<T>> visited) {
        if (visited.size() >= adjList.size()) {
            return;
        }
        visited.add(curr);
        res.add(curr);
        for (VertexDistance<T> vertexDistance : adjList.get(curr)) {
            Vertex<T> neighbor = vertexDistance.getVertex();
            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, adjList, res, visited);
            }
        }

    }
}
