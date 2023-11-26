import Graph.*;
import Questions.*;

import java.io.IOException;
import java.util.*;

public class Test {
    public static void main(String[] args) throws IOException {
        Vertex<String> a = new Vertex<>("A");
        Vertex<String> b = new Vertex<>("B");
        Vertex<String> c = new Vertex<>("C");
        Vertex<String> d = new Vertex<>("D");
        Vertex<String> e = new Vertex<>("E");
        Vertex<String> f = new Vertex<>("F");

        a.addAdjacentVertexWithWeight(b, 2);
        a.addAdjacentVertexWithWeight(c, 4);
        b.addAdjacentVertexWithWeight(c, 3);
        b.addAdjacentVertexWithWeight(d, 1);
        b.addAdjacentVertexWithWeight(e, 5);
        c.addAdjacentVertexWithWeight(d, 2);
        d.addAdjacentVertexWithWeight(e, 1);
        d.addAdjacentVertexWithWeight(f, 4);
        e.addAdjacentVertexWithWeight(f, 2);
        Graph<String> dijkstra = new Graph<String>();
        dijkstra.calculateShortestPath(a);
        dijkstra.printPaths(List.of(a, b, d, e, f));

        Questions.print(Questions.readQuestions());
    }
}
