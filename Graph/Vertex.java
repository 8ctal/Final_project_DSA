package Graph;

import lombok.*;

import java.util.*;

@Getter
@Setter
@RequiredArgsConstructor

public class Vertex<T> implements Comparable<Vertex<T>> {
    private final T data;
    private boolean visited;
    private Set<Vertex<T>> adjacentVertices = new HashSet<>(); // For Kruskal's Algorithm
    private Integer distance = Integer.MAX_VALUE; // For Dijkstra's Algorithm
    private List<Vertex<T>> shortestPath = new LinkedList<>(); // For Dijkstra's Algorithm
    private Map<Vertex<T>, Integer> adjacentVerticesWithWeights = new HashMap<>(); // For Dijkstra's Algorithm


    /*public Vertex(T data) {
        this.data = data;
        this.visited = false;
    }*/

    private List<Edge<T>> edges = new LinkedList<>();

    public void addEdge(Vertex<T> to, Integer weight){
        Edge<T> e = new Edge<T>(this, to, weight);
        edges.add(e);
    }

    public List<Edge<T>> getEdges(){
        return this.edges;
    }

    //Dijkstra's Algorithm
    public void addAdjacentVertexWithWeight(Vertex<T> vertex, Integer weight) {
        adjacentVerticesWithWeights.put(vertex, weight);
    }
    //Kruskal's Algorithm

    public void addAdjacentVertex(Vertex<T> vertex) {
        adjacentVertices.add(vertex);
    }

    /*Preguntas relacionales */

    public List<String> addAdjacentVertexQuestions(Vertex<T> vertex, String question1, 
    String question2, String question3, String question4) {
        adjacentVertices.add(vertex);
       List<String> Answer = new ArrayList<>();
        Answer.add(question1);
        Answer.add(question2);
        Answer.add(question3);
        Answer.add(question4);
        return Answer;
    }
    
    
    //For Dijkstra's Algorithm
    @Override
    //Override the method of Comparable Interface, to sort the vertices by distance
    public int compareTo(Vertex<T> vertex) {
        return Integer.compare(this.distance, vertex.getDistance());
    }
}
