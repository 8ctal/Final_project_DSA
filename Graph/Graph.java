package Graph;

import java.util.*;
import java.util.stream.*;

public class Graph<T> {


    //Disktra's Algorithm
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

  




    //Print the shortest path from the source vertex to all other vertices
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

    // Evaluate the shortest path to the adjacent vertex

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


    public void printGraph(List<Vertex<T>> nodes) {
        for (Vertex<T> vertex : nodes) {
            System.out.print(vertex.getLetter() + " -> "); 

            Set<Vertex<T>> edge = vertex.getAdjacentVertices();

            if (!edge.isEmpty()) {
                for (Vertex<T> edges : edge) {
                    System.out.print(edges.getLetter() + " "); 
                }
            }

            System.out.println();
        }
    }

    /*Actualización de pesos preguntas relaciones
     * 1. ¿Vive con el enfermo?
     * 2. ¿Ha tenido contacto físico con el enfermo?
     * 3. ¿Estudia o trabaja con el enfermo?
     * 4. ¿Cuántos días ha estado en contacto con el enfermo?
     */
    public void updateWeight(List<Vertex<T>> nodes) {
        for (Vertex<T> vertex : nodes) {
            
            for (Edge<T> edge : vertex.getEdges()) {
                List<String> addAdjacentVertexQuestions = vertex.addAdjacentVertexQuestions(edge.getTo(), "pregunta 1", "pregunta 2", "pregunta 3", 4);

                if (addAdjacentVertexQuestions.get(0).equalsIgnoreCase("True")) {
                    int weight = edge.getWeight() - 10;
                    edge.setWeight(weight);
                }
                if (addAdjacentVertexQuestions.get(1).equalsIgnoreCase("True")) {
                    int weight = edge.getWeight() - 2;
                    edge.setWeight(weight);
                }
                if (addAdjacentVertexQuestions.get(2).equalsIgnoreCase("True")) {
                    int weight = edge.getWeight() - 5;
                    edge.setWeight(weight);
                }
                if (addAdjacentVertexQuestions.get(3).equalsIgnoreCase("True")) {
                    int dias = Integer.parseInt(addAdjacentVertexQuestions.get(3));
                    if (dias > 0 && dias < 5) {
                        int weight = edge.getWeight() - 2;
                        edge.setWeight(weight);
                    }
                    if (dias > 5 && dias < 10) {
                        int weight = edge.getWeight() - 4;
                        edge.setWeight(weight);
                    }
                    if (dias > 10 && dias < 15) {
                        int weight = edge.getWeight() - 6;
                        edge.setWeight(weight);
                    }
                    if (dias > 15) {
                        int weight = edge.getWeight() - 8;
                        edge.setWeight(weight);

                    }
                }
            }
        }
    }







}