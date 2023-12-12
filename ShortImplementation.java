import java.util.*;
import java.util.stream.Collectors;

public class ShortImplementation {

    public static void main(String[] args) {
        int start_vertex = 2;
        int end_vertex = 3;

        Map<Integer, List<Integer>> G = new HashMap<>();
        List<int[]> edges = new ArrayList<>();
        edges.add(new int[]{9, 3});
        edges.add(new int[]{3, 14});
        edges.add(new int[]{3, 0});
        edges.add(new int[]{0, 8});
        edges.add(new int[]{0, 10});
        edges.add(new int[]{8, 10});
        edges.add(new int[]{0, 14});
        edges.add(new int[]{14, 11});
        edges.add(new int[]{11, 13});
        edges.add(new int[]{13, 7});
        edges.add(new int[]{10, 5});
        edges.add(new int[]{7, 6});
        edges.add(new int[]{14, 12});
        edges.add(new int[]{0, 6});
        edges.add(new int[]{0, 11});
        edges.add(new int[]{10, 12});
        edges.add(new int[]{12, 6});
        edges.add(new int[]{5, 1});
        edges.add(new int[]{6, 1});
        edges.add(new int[]{12, 4});
        edges.add(new int[]{12, 2});
        edges.add(new int[]{4, 7});

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            G.putIfAbsent(u, new ArrayList<>());
            G.putIfAbsent(v, new ArrayList<>());
            G.get(u).add(v);
            G.get(v).add(u);
        }

        Map<Integer, Map<Integer, Double>> weights = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            weights.putIfAbsent(u, new HashMap<>());
            weights.putIfAbsent(v, new HashMap<>());
            weights.get(u).put(v, 0.0);
            weights.get(v).put(u, 0.0);
        }

        for (int node : G.keySet()) {
            List<Integer> neighbors = G.get(node);
            int degree_sum = 0;
            for (int neighbor : neighbors) {
                degree_sum += G.get(neighbor).size();
            }

            double prob_transmision = 0.5;
            for (int neighbor : neighbors) {
                int ki = G.get(neighbor).size();
                double peso = calcular_peso(neighbors.size(), ki, prob_transmision);
                weights.get(node).put(neighbor, peso);
                weights.get(neighbor).put(node, peso);
            }
        }

        List<Integer> shortest_path = prim_shortest_path(G, weights, start_vertex, end_vertex);
        Set<int[]> tree_edges = new HashSet<>();
        for (int i = 0; i < shortest_path.size() - 1; i++) {
            int u = shortest_path.get(i);
            int v = shortest_path.get(i + 1);
            double weight = weights.get(u).get(v);
            tree_edges.add(new int[]{u, v, (int) weight});
        }

        System.out.println("Shortest Path: " + shortest_path);
        System.out.println("Tree Edges: " + tree_edges.stream().map(Arrays::toString).collect(Collectors.toList()));

        printGraph(G, weights);
        List<Integer> shortestPath = prim_shortest_path(G, weights, start_vertex, end_vertex);
        calculateTotalWeight(shortestPath, weights);
    }

    public static double calcular_peso(int n, int ki, double rho) {
        return ((n - 1) + ki) / rho;
    }

    public static List<Integer> prim_shortest_path(Map<Integer, List<Integer>> graph, Map<Integer, Map<Integer, Double>> weights, int start, int end) {
        List<Integer> shortest_path = new ArrayList<>();
        shortest_path.add(start);

        while (shortest_path.get(shortest_path.size() - 1) != end) {
            int current_node = shortest_path.get(shortest_path.size() - 1);
            double min_weight = Double.MAX_VALUE;
            int next_node = -1;

            for (int neighbor : graph.get(current_node)) {
                if (!shortest_path.contains(neighbor) && weights.get(current_node).get(neighbor) < min_weight) {
                    min_weight = weights.get(current_node).get(neighbor);
                    next_node = neighbor;
                }
            }

            shortest_path.add(next_node);
        }

        return shortest_path;
    }

    public static void printGraph(Map<Integer, List<Integer>> graph, Map<Integer, Map<Integer, Double>> weights) {
        for (Integer vertex : graph.keySet()) {
            System.out.println("Vertex " + vertex + ":");
            for (Integer neighbor : graph.get(vertex)) {
                System.out.println("  Edge to " + neighbor + ", weight: " + weights.get(vertex).get(neighbor));
            }
        }
    }
    public static void calculateTotalWeight(List<Integer> shortestPath, Map<Integer, Map<Integer, Double>> weights) {
        double totalWeight = 0.0;
        for (int i = 0; i < shortestPath.size() - 1; i++) {
            int currentVertex = shortestPath.get(i);
            int nextVertex = shortestPath.get(i + 1);
            totalWeight += weights.get(currentVertex).get(nextVertex);
        }
        System.out.println("Total weight of the shortest path: " + totalWeight);
    }
}


