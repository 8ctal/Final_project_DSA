package Graph;
import lombok.*;

import java.util.*;

import Population.Person;

@Getter
@Setter
@AllArgsConstructor 

public class Vertex<T> implements Comparable<Vertex<T>> {
    private final T data;
    private boolean visited;
    private Set<Vertex<T>> adjacentVertices = new HashSet<>(); // For Kruskal's Algorithm
    private Integer distance = Integer.MAX_VALUE; // For Dijkstra's Algorithm
    private List<Vertex<T>> shortestPath = new LinkedList<>(); // For Dijkstra's Algorithm
    private Map<Vertex<T>, Integer> adjacentVerticesWithWeights = new HashMap<>(); // For Dijkstra's Algorithm
    private char letter;
    private List<String> answers = new ArrayList<>(); 

    /*public Vertex(T data) {
        this.data = data;
        this.visited = false;
    }*/

    public Vertex(T data, char letter) {
        this.data = data;
        this.letter = letter;
   
    }

    private List<Edge<T>> edges = new LinkedList<>();


    public List<Edge<T>> getEdges(){
        return this.edges;
    }

    public char getLetter() {
        return letter;
    }


    //Dijkstra's Algorithm
    public void addAdjacentVertexWithWeight(Vertex<T> vertex, Integer weight) {
        adjacentVerticesWithWeights.put(vertex, weight);
    }

    
    //Kruskal's Algorithm

    public void addAdjacentVertex(Vertex<T> vertex) {
        adjacentVertices.add(vertex);
    }

    /* preguntas relaciones
     * 1. ¿Vive con la Persona?
     * 2. ¿Ha tenido contacto físico con la Persona?
     * 3. ¿Estudia o trabaja con la Persona?
     * 4. ¿Cuántos días ha estado en contacto con la Persona?
     */


     public void addAdjacentVertexQuestions(Vertex<T> vertex, String question1, String question2, String question3, int question4) {
        adjacentVertices.add(vertex);
        adjacentVerticesWithWeights.put(vertex, 40);
        answers.add(question1);
        answers.add(question2);
        answers.add(question3);
        answers.add(String.valueOf(question4)); // Convertir la variable numérica a String y agregarla como respuesta

    }
    

    public void informationPerson(Vertex<Person> nodo) {
        System.out.println("Name: " + nodo.getData().getInfectionType());
    }

    //For Dijkstra's Algorithm
    @Override
    //Override the method of Comparable Interface, to sort the vertices by distance
    public int compareTo(Vertex<T> vertex) {
        return Integer.compare(this.distance, vertex.getDistance());
    }

}