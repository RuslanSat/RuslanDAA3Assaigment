import java.util.*;
public class Main {

    public static void main(String[] args) {
        // Graph Test
        List<Graph> graphs = new ArrayList<>();

        // Graph 1: 5 vertices, 7 edges
        Graph graph1 = new Graph();
        graph1.id = 1;
        graph1.nodes = Arrays.asList("A", "B", "C", "D", "E");
        graph1.edges = Arrays.asList(
                new Edge("A", "B", 4),
                new Edge("A", "C", 3),
                new Edge("B", "C", 2),
                new Edge("B", "D", 5),
                new Edge("C", "D", 7),
                new Edge("C", "E", 8),
                new Edge("D", "E", 6)
        );

        // Graph 2: 4 vertices, 5 edges
        Graph graph2 = new Graph();
        graph2.id = 2;
        graph2.nodes = Arrays.asList("A", "B", "C", "D");
        graph2.edges = Arrays.asList(
                new Edge("A", "B", 1),
                new Edge("A", "C", 4),
                new Edge("B", "C", 2),
                new Edge("C", "D", 3),
                new Edge("B", "D", 5)
        );

        graphs.add(graph1);
        graphs.add(graph2);

        // Process each graph
        for (Graph graph : graphs) {
            System.out.println("Processing graph " + graph.id + "...");

            // Run Prim's algorithm
            long primStartTime = System.nanoTime();
            MSTResult primResult = PrimsAlgorithm.findMST(graph);
            long primEndTime = System.nanoTime();
            double primTimeMs = (primEndTime - primStartTime) / 1_000_000.0;

            // Run Kruskal's algorithm
            long kruskalStartTime = System.nanoTime();
            MSTResult kruskalResult = KruskalsAlgorithm.findMST(graph);
            long kruskalEndTime = System.nanoTime();
            double kruskalTimeMs = (kruskalEndTime - kruskalStartTime) / 1_000_000.0;

            System.out.println("\nGraph " + graph.id + " Results:");
            System.out.println("  Vertices: " + graph.nodes.size() + ", Edges: " + graph.edges.size());
            System.out.println("\n  Prim's Algorithm:");
            System.out.println("    MST Cost: " + primResult.totalCost);
            System.out.println("    Operations: " + primResult.operationsCount);
            System.out.println("    Time: " + String.format("%.2f", primTimeMs) + " ms");
            System.out.println("    MST Edges: " + primResult.edges);
            System.out.println("\n  Kruskal's Algorithm:");
            System.out.println("    MST Cost: " + kruskalResult.totalCost);
            System.out.println("    Operations: " + kruskalResult.operationsCount);
            System.out.println("    Time: " + String.format("%.2f", kruskalTimeMs) + " ms");
            System.out.println("    MST Edges: " + kruskalResult.edges);
            System.out.println("\n" + "=".repeat(60) + "\n");
        }
    }

    // Data classes
    static class Graph {
        int id;
        List<String> nodes;
        List<Edge> edges;
    }

    static class Edge {
        String from;
        String to;
        int weight;

        Edge(String from, String to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return from + "-" + to + "(" + weight + ")";
        }
    }
}