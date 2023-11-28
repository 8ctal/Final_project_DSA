import Graph.*;
import Population.*;
import Questions.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.*;

/*We are going to work with bi-partite graphs, so we are going to have two sets of vertices,
the infected and the healthy ones. So the graph is going to be G = (I,H E), where I and H are the
sets of vertices and E is the set of edges. We are going to use the adjacency list representation
*/
public class Test {
    public static void main(String[] args) throws IOException 
    {
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

        //Creating the population
        List<Person> population = IntStream.range(0, 10).mapToObj(i -> new Person(
                false,
                false,
                new Random().nextInt(100),
                0,
                List.of(true, false, true, false, true, false, true, false)
        )).collect(Collectors.toList());
        population.forEach(System.out::println);


        //Creating the questions
        List<Questions> questions = population.stream()
                .map(person -> {
                    try {
                        return person.addAnswer();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    return null;
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());
        //questions.forEach(System.out::println);
        Questions.print(Questions.readQuestions());
    }
}