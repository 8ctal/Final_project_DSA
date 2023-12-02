package Graph;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Edge<T> implements Comparable<Edge<T>> {
    private final Vertex<T> from;
    private final Vertex<T> to;
    private  int weight = 40; // For Kruskal's Algorithm
    private boolean included; // For Prim's Algorithm

    //Constructor for Kruskal's Algorithm
    public Edge(Vertex<T> from, Vertex<T> to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public int compareTo(Edge<T> edge) {
        //Override the method of Comparable Interface, to sort the edges by weight
        return Integer.compare(this.weight, edge.getWeight());
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from.getData() +
                ", to=" + to.getData() +
                ", weight=" + weight +
                '}';
    }
}