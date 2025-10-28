import java.util.*;

/**
 * Implementation of Kruskal's algorithm to find the Minimum Spanning Tree.
 * Kruskal's algorithm sorts all edges by weight and adds them to the MST
 * if they don't form a cycle (using Union-Find data structure).
 * Time Complexity: O(E log E) = O(E log V)
 * Space Complexity: O(V + E)
 */
public class KruskalsAlgorithm {

    /**
     * Finds the MST using Kruskal's algorithm.
     */
    public static MSTResult findMST(Main.Graph graph) {
        int operationsCount = 0;
        List<Main.Edge> mstEdges = new ArrayList<>();

        // Sorting edges
        List<Main.Edge> sortedEdges = new ArrayList<>(graph.edges);
        sortedEdges.sort((a, b) -> a.weight - b.weight);
        operationsCount += (int) (graph.edges.size() * Math.log(graph.edges.size()) / Math.log(2));

        // Initialize Union-Find data structure
        UnionFind uf = new UnionFind(graph.nodes);
        operationsCount += graph.nodes.size();

        // Process edges in sorted order
        for (Main.Edge edge : sortedEdges) {
            operationsCount++;

            if (uf.union(edge.from, edge.to)) {
                mstEdges.add(edge);
                operationsCount += 2;
            }
        }

        // Get operations count from Union-Find
        operationsCount += uf.getOperationsCount();

        // Calculate total cost
        int totalCost = 0;
        for (Main.Edge edge : mstEdges) {
            totalCost += edge.weight;
            operationsCount++;
        }

        return new MSTResult(mstEdges, totalCost, operationsCount);
    }

    /**
     * Union-Find data structure for efficient cycle detection.
     */
    static class UnionFind {
        private Map<String, String> parent;
        private Map<String, Integer> rank;
        private int operationsCount;

        public UnionFind(List<String> nodes) {
            parent = new HashMap<>();
            rank = new HashMap<>();

            for (String node : nodes) {
                parent.put(node, node);
                rank.put(node, 0);
            }
        }

        /**
         * Finds the root of the set containing x (with path compression).
         */
        private String find(String x) {
            operationsCount++;
            if (!parent.get(x).equals(x)) {
                parent.put(x, find(parent.get(x)));
                operationsCount += 2;
            }
            operationsCount++;
            return parent.get(x);
        }

        /**
         * Unions two sets (with union by rank).
         */
        public boolean union(String x, String y) {
            String rootX = find(x);
            String rootY = find(y);
            operationsCount += 2;

            if (rootX.equals(rootY)) {
                operationsCount++;
                return false;
            }

            operationsCount++;

            // Union by rank
            if (rank.get(rootX) < rank.get(rootY)) {
                parent.put(rootX, rootY);
                operationsCount += 3;
            } else if (rank.get(rootX) > rank.get(rootY)) {
                parent.put(rootY, rootX);
                operationsCount += 4;
            } else {
                parent.put(rootY, rootX);
                rank.put(rootX, rank.get(rootX) + 1);
                operationsCount += 5;
            }

            return true;
        }

        public int getOperationsCount() {
            return operationsCount;
        }
    }
}