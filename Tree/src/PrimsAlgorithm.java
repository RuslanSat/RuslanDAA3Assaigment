import java.util.*;
/**
 * Implementation of Prim's algorithm to find the Minimum Spanning Tree.
 * Prim's algorithm starts from an arbitrary vertex and grows the MST by
 * adding the minimum weight edge connecting a vertex in the MST to a vertex
 * not yet in the MST.
 * Time Complexity: O(E log V) with binary heap
 * Space Complexity: O(V)
 */
public class PrimsAlgorithm {
    public static MSTResult findMST(Main.Graph graph) {
        int operationsCount = 0;
        List<Main.Edge> mstEdges = new ArrayList<>();

        // Build adjacency list
        Map<String, List<Main.Edge>> adjacencyList = buildAdjacencyList(graph);
        operationsCount += graph.edges.size();

        // Priority queue for edges
        PriorityQueue<Main.Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        Set<String> visited = new HashSet<>();

        // Start first note
        String startNode = graph.nodes.get(0);
        visited.add(startNode);
        operationsCount++;

        // Add all edges from start node to priority queue
        List<Main.Edge> startEdges = adjacencyList.getOrDefault(startNode, new ArrayList<>());
        pq.addAll(startEdges);
        operationsCount += startEdges.size();

        // Process while we haven't connected all nodes
        while (!pq.isEmpty() && visited.size() < graph.nodes.size()) {
            operationsCount++;

            Main.Edge currentEdge = pq.poll();
            operationsCount++;

            String nextNode = null;
            if (visited.contains(currentEdge.from) && !visited.contains(currentEdge.to)) {
                nextNode = currentEdge.to;
            } else if (visited.contains(currentEdge.to) && !visited.contains(currentEdge.from)) {
                nextNode = currentEdge.from;
            }

            if (nextNode != null) {
                visited.add(nextNode);
                mstEdges.add(currentEdge);
                operationsCount += 2;

                // Add edges from newly added node
                List<Main.Edge> nodeEdges = adjacencyList.getOrDefault(nextNode, new ArrayList<>());
                for (Main.Edge edge : nodeEdges) {
                    if (!visited.contains(edge.from) || !visited.contains(edge.to)) {
                        pq.offer(edge);
                        operationsCount += 2;
                    }
                }
            }

            operationsCount += 3;
        }

        // Calculate total cost
        int totalCost = 0;
        for (Main.Edge edge : mstEdges) {
            totalCost += edge.weight;
            operationsCount++;
        }

        return new MSTResult(mstEdges, totalCost, operationsCount);
    }

    /**
     * Builds an adjacency list representation of the graph.
     */
    private static Map<String, List<Main.Edge>> buildAdjacencyList(Main.Graph graph) {
        Map<String, List<Main.Edge>> adjacencyList = new HashMap<>();

        for (Main.Edge edge : graph.edges) {
            adjacencyList.putIfAbsent(edge.from, new ArrayList<>());
            adjacencyList.putIfAbsent(edge.to, new ArrayList<>());
            adjacencyList.get(edge.from).add(edge);
            adjacencyList.get(edge.to).add(edge);
        }
        return adjacencyList;
    }
}