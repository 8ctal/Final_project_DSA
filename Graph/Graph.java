package Graph;

import java.util.*;
import java.util.stream.*;

import Population.Person;
import Population.Person.InfectionType;


public class Graph<T> {

    //Disktra's Algorithm
   /* public void calculateShortestPath(Vertex<T> source) {
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
    }*/

    public void calculateShortestPath(Vertex<T> source) {
        if (!(source.getData() instanceof Person) || ((Person) source.getData()).getInfectionType() == null) {
            return;
        }

        source.setDistance(0);
        Set<Vertex<T>> settledVertices = new HashSet<>();
        Queue<Vertex<T>> unsettledVertices = new PriorityQueue<>(Collections.singletonList(source));

        while (!unsettledVertices.isEmpty()) {
            Vertex<T> currentVertex = unsettledVertices.poll();
            currentVertex.getAdjacentVerticesWithWeights()
                    .entrySet().stream()
                    .filter(entry -> !settledVertices.contains(entry.getKey()))
                    .filter(entry -> entry.getKey().getData() instanceof Person && ((Person) entry.getKey().getData()).getInfectionType() != null)
                    .forEach(entry -> {
                        evaluateShortestPath(entry.getKey(), entry.getValue(), currentVertex);
                        unsettledVertices.add(entry.getKey());
                    });
            settledVertices.add(currentVertex);
        }
    }

    private void evaluateShortestPath(Vertex<T> ajdacentVertex, Integer edgeWeight, Vertex<T> sourceVertex) {
        if (!(ajdacentVertex.getData() instanceof Person) || ((Person) ajdacentVertex.getData()).getInfectionType() == null) {
            return;
        }

        Integer newDistance = sourceVertex.getDistance() + edgeWeight;
        if (newDistance < ajdacentVertex.getDistance()) {
            ajdacentVertex.setDistance(newDistance);
            ajdacentVertex.setShortestPath(
                    Stream.concat(sourceVertex.getShortestPath().stream(), Stream.of(sourceVertex)).toList()
            );
        }
    }

    //Print the shortest path from the source vertex to all other vertices
    public void printPaths(List<Vertex<T>> nodes) {
        nodes.forEach(node -> {
            String path = node.getShortestPath().stream()
                    .map(Vertex::getLetter).map(Objects::toString)
                    .collect(Collectors.joining(" -> "));// Get the shortest path as a String
            System.out.println((path.isBlank() // If the shortest path is blank, print only the vertex and its distance
                    ? "%s : %s".formatted(node.getLetter(), node.getDistance())
                    : "%s -> %s : %s".formatted(path, node.getLetter(), node.getDistance()))
            );
        });
    }

    // Evaluate the shortest path to the adjacent vertex
    /*private void evaluateShortestPath(Vertex<T> ajdacentVertex, Integer edgeWeight, Vertex<T> sourceVertex) {
        // If the new distance is less than the current distance, update the distance and the shortest path
        Integer newDistance = sourceVertex.getDistance() + edgeWeight;
        if (newDistance < ajdacentVertex.getDistance()) {
            ajdacentVertex.setDistance(newDistance);
            ajdacentVertex.setShortestPath(
                    //Concatenate the shortest path of the source vertex with the source vertex
                    Stream.concat(sourceVertex.getShortestPath().stream(), Stream.of(sourceVertex)).toList()
            );
        }
    }*/

    public void printGraph(List<Vertex<T>> vertices) {
        for (Vertex<T> vertex : vertices) {
            System.out.print(vertex.getLetter() + " -> ");

            Set<Vertex<T>> vertexSet = vertex.getAdjacentVertices();

            if (!vertexSet.isEmpty()) {
                for (Vertex<T> tVertex : vertexSet) {
                    System.out.print(tVertex.getLetter() + " : " + tVertex.getEdges() + " - ");
                }
            }

            System.out.println();
        }
    }

    /*Weight update for relationship questions
     * 1. Does the person live with the infected individual?
     * 2. Has the person had physical contact with the infected individual?
     * 3. Does the person study or work with the infected individual?
     * 4. How many days has the person been in contact with the infected individual?
     */

    public void updateWeight(List<Vertex<Person>> nodes) {
        Set<Vertex<Person>> visitedNodes = new HashSet<>();

        for (Vertex<Person> vertex : nodes) {
            if (!visitedNodes.contains(vertex)) {
                updateWeightHelper(vertex, visitedNodes);
            }
        }
    }

    private void updateWeightHelper(Vertex<Person> vertex, Set<Vertex<Person>> visited) {
        visited.add(vertex);
        int i = 0;
        for (Map.Entry<Vertex<Person>, Integer> entry : vertex.getAdjacentVerticesWithWeights().entrySet()) {
            Vertex<Person> adjacentVertex = entry.getKey();

            Integer weight = entry.getValue();

            // UpdateWeight by No-Relationship Questions

            // UpdateWeight by Age
            int ageVertex = vertex.getData().getAge();
            int ageAdjacentVertex = adjacentVertex.getData().getAge();

            if (ageVertex < 10 || ageAdjacentVertex < 10) {
                weight -= 10;
            } else if ((ageVertex >= 10 && ageVertex < 30) || (ageAdjacentVertex >= 10 && ageAdjacentVertex < 30)) {
                weight = weight - 3;
            } else if ((ageVertex >= 30 && ageVertex < 60) || (ageAdjacentVertex >= 30 && ageAdjacentVertex < 60)) {
                weight = weight - 5;
            } else if ((ageVertex >= 60 && ageVertex < 100) || (ageAdjacentVertex >= 60 && ageAdjacentVertex < 100)) {
                weight = weight - 10;
            }

            // UpdateWeight by InfectionType
            InfectionType infectionVertex = vertex.getData().getInfectionType();
            InfectionType infectionAdjacentVertex = adjacentVertex.getData().getInfectionType();

            if (infectionVertex == InfectionType.VIRUS || infectionAdjacentVertex == InfectionType.VIRUS) {
                weight = weight - 10;
            } else if (infectionVertex == InfectionType.BACTERIA || infectionAdjacentVertex == InfectionType.BACTERIA) {
                weight = weight - 5;
            } else if (infectionVertex == InfectionType.FUNGUS || infectionAdjacentVertex == InfectionType.FUNGUS) {
                weight = weight - 2;
            }

            // UpdateWeight by Relationship Questions
            List<String> addAdjacentVertexQuestions = vertex.getAnswers();
            int size = addAdjacentVertexQuestions.size();

            if (addAdjacentVertexQuestions.get((size + i) - size).equalsIgnoreCase("True")) {
                weight = weight - 10;
            }
            if (addAdjacentVertexQuestions.get((size + i) - (size - 1)).equalsIgnoreCase("True")) {
                weight = weight - 2;
            }
            if (addAdjacentVertexQuestions.get((size + i) - (size - 2)).equalsIgnoreCase("True")) {
                weight = weight - 5;
            }
            int dias = Integer.parseInt(addAdjacentVertexQuestions.get((size + i) - (size - 3)));
            if (dias > 0 && dias < 5) {
                weight = weight - 2;
            } else if (dias >= 5 && dias < 10) {
                weight = weight - 4;
            } else if (dias >= 10 && dias < 15) {
                weight = weight - 6;
            } else {
                weight = weight - 8;
            }
            i += 4;

            entry.setValue(weight);

            // Update the weight of the edge in the adjacent vertex
            if (!visited.contains(adjacentVertex)) {

                updateWeightHelper(adjacentVertex, visited);
            }
        }
    }

    // Print the graph with the weights
    public void printGraphWithWeights(List<Vertex<T>> nodes) {
        for (Vertex<T> vertex : nodes) {
            for (Map.Entry<Vertex<T>, Integer> entry : vertex.getAdjacentVerticesWithWeights().entrySet()) {
                Vertex<T> adjacentVertex = entry.getKey();
                int weight = entry.getValue();

                System.out.println(vertex.getLetter() + " -> " + adjacentVertex.getLetter() + " : " + weight);
            }
            System.out.println();
        }
    }

    //Update infection.Type status
    public void updateInfectionType(List<Vertex<Person>> nodes) {
        Set<Vertex<Person>> visited = new HashSet<>();

        for (Vertex<Person> vertex : nodes) {
            if (!visited.contains(vertex)) {
                updateInfectionTypeHelper(vertex, visited);
            }
        }
    }

    private void updateInfectionTypeHelper(Vertex<Person> vertex, Set<Vertex<Person>> visited) {
        visited.add(vertex);

        Map<InfectionType, Integer> lowestWeightByInfection = getInfectionTypeIntegerMap(vertex);

        InfectionType minWeightInfection = null;
        int minWeight = Integer.MAX_VALUE;

        for (Map.Entry<InfectionType, Integer> entry : lowestWeightByInfection.entrySet()) {
            if (entry.getValue() < minWeight) {
                minWeight = entry.getValue();
                minWeightInfection = entry.getKey();
            }
        }

        if (minWeightInfection != null) {
            vertex.getData().setInfectionType(minWeightInfection);
        }

        for (Map.Entry<Vertex<Person>, Integer> entry : vertex.getAdjacentVerticesWithWeights().entrySet()) {
            Vertex<Person> adjacentVertex = entry.getKey();
            if (!visited.contains(adjacentVertex)) {
                updateInfectionTypeHelper(adjacentVertex, visited);
            }
        }
    }

    private Map<InfectionType, Integer> getInfectionTypeIntegerMap(Vertex<Person> vertex) {
        Map<InfectionType, Integer> lowestWeightByInfection = new HashMap<>();

        for (Map.Entry<Vertex<Person>, Integer> entry : vertex.getAdjacentVerticesWithWeights().entrySet()) {
            Vertex<Person> adjacentVertex = entry.getKey();
            InfectionType infectionAdjacentVertex = adjacentVertex.getData().getInfectionType();
            Integer weight = entry.getValue();

            if (weight <= 20 && infectionAdjacentVertex != null) {
                if (!lowestWeightByInfection.containsKey(infectionAdjacentVertex) || weight < lowestWeightByInfection.get(infectionAdjacentVertex)) {
                    lowestWeightByInfection.put(infectionAdjacentVertex, weight);
                }
            }
        }
        return lowestWeightByInfection;
    }

    // Print the infection type of each person
    public void printInfectionType(List<Vertex<Person>> nodes) {
        for (Vertex<Person> vertex : nodes) {
            System.out.println("Name: " + vertex.getData().getName() + " Infection Type: " + vertex.getData().getInfectionType());
        }
    }
}