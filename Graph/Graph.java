package Graph;

import java.util.*;
import java.util.stream.*;

public class Graph<T> {
    public void calculateShortestPath(Vertex<T> source) {
        source.setDistance(0);
        // Set of vertices whose shortest path is already found
        Set<Vertex<T>> settledVertices = new HashSet<>();

        // Set of vertices whose shortest path is not yet found
        Queue<Vertex<T>> unsettledVertices = new PriorityQueue<>(Collections.singletonList(source)); // Source added to the queue

        // While there are vertices whose shortest path is not yet found
        while (!unsettledVertices.isEmpty()) {
            Vertex<T> currentVertex = unsettledVertices.poll();// Get the vertex with the minimum distance from the queue
            currentVertex.getAdjacentVerticesWithWeights()
                    .entrySet().stream()
                    .filter(entry -> !settledVertices.contains(entry.getKey()))
                    .forEach(entry -> { // For each adjacent vertex that is not yet settled
                        evaluateShortestPath(entry.getKey(), entry.getValue(), currentVertex); // Evaluate the shortest path
                        unsettledVertices.add(entry.getKey()); //And add it to the unsettled vertices
                    });
            // Add the vertex to the settled vertices when its shortest path is found
            settledVertices.add(currentVertex);
        }
    }

    public void printPaths(List<Vertex<T>> nodes) {
        nodes.forEach(node -> {
            String path = node.getShortestPath().stream()
                    .map(Vertex::getData).map(Objects::toString)
                    .collect(Collectors.joining(" -> "));// Get the shortest path as a String
            System.out.println((path.isBlank() // If the shortest path is blank, print only the vertex and its distance
                    ? "%s : %s".formatted(node.getData(), node.getDistance())
                    : "%s -> %s : %s".formatted(path, node.getData(), node.getDistance()))
            );
        });
    }

    private void evaluateShortestPath(Vertex<T> ajdacentVertex, Integer edgeWeight, Vertex<T> sourceVertex) {
        // If the new distance is less than the current distance, update the distance and the shortest path
        Integer newDistance = sourceVertex.getDistance() + edgeWeight;
        if (newDistance < ajdacentVertex.getDistance()) {
            ajdacentVertex.setDistance(newDistance);
            ajdacentVertex.setShortestPath(
                    //Concatenate the shortest path of the source vertex with the source vertex
                    Stream.concat(sourceVertex.getShortestPath().stream(), Stream.of(sourceVertex)).toList()
            );
        }
    }

}