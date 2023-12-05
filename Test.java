import Graph.*;
import Population.*;
import Population.Person.InfectionType;

import java.util.*;
import java.io.*;


/*We are going to work with bi-partite graphs, so we are going to have two sets of vertices,
the infected and the healthy ones. So the graph is going to be G = (I,H E), where I and H are the
sets of vertices and E is the set of edges. We are going to use the adjacency list representation
*/
public class Test {
    public static void main(String[] args) throws IOException {
        /* 
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

        

       // Creating the population
List<Person> population = IntStream.range(0, 10).mapToObj(i -> new Person(
    false,
            new Random().nextInt(100),
    List.of(true, false, true, false, true, false, true, false)
)).collect(Collectors.toList());
        population.forEach(System.out::println);


*/

        // Crear 10 personas
        Person person1 = new Person("Alice", 25, InfectionType.BACTERIA);
        Person person2 = new Person("Bob", 30,InfectionType.BACTERIA); //InfectionType.None
        Person person3 = new Person("Charlie", 22, InfectionType.VIRUS);
        Person person4 = new Person("David", 28);
        Person person5 = new Person("Eve", 35, InfectionType.FUNGUS);
        Person person6 = new Person("Frank", 27, InfectionType.BACTERIA);
        Person person7 = new Person("Grace", 79);
        Person person8 = new Person("Hannah", 26, InfectionType.FUNGUS);
        Person person9 = new Person("Ian", 8);
        Person person10 = new Person("Julia", 17);


        // Crear vertices

        Vertex<Person> A = new Vertex<>(person1, 'A'); //1
        Vertex<Person> B = new Vertex<>(person2, 'B'); //2
        Vertex<Person> C = new Vertex<>(person3, 'C'); //3
        Vertex<Person> D = new Vertex<>(person4, 'D'); //4
        Vertex<Person> E = new Vertex<>(person5, 'E'); //5
        Vertex<Person> F = new Vertex<>(person6, 'F'); //6
        Vertex<Person> G = new Vertex<>(person7, 'G'); //7
        Vertex<Person> H = new Vertex<>(person8, 'H'); //8
        Vertex<Person> I = new Vertex<>(person9, 'I'); //9
        Vertex<Person> J = new Vertex<>(person10, 'J');//10

        // Crear relaciones

        A.addAdjacentVertexQuestions(B, "True", "False", "True", 7);
        A.addAdjacentVertexQuestions(C, "False", "True", "False", 5);

        B.addAdjacentVertexQuestions(C, "False", "False", "False", 3);
        B.addAdjacentVertexQuestions(F, "False", "True", "False", 6);
        B.addAdjacentVertexQuestions(H, "True", "False", "True", 8);

        C.addAdjacentVertexQuestions(D, "False", "True", "False", 1);
        D.addAdjacentVertexQuestions(E, "True", "False", "True", 3);
        D.addAdjacentVertexQuestions(F, "False", "True", "False", 2);
        E.addAdjacentVertexQuestions(F, "True", "False", "True", 4);
        F.addAdjacentVertexQuestions(G, "False", "True", "False", 1);
        G.addAdjacentVertexQuestions(H, "True", "False", "True", 3);
        H.addAdjacentVertexQuestions(I, "False", "True", "False", 2);
        I.addAdjacentVertexQuestions(J, "True", "False", "True", 4);
        J.addAdjacentVertexQuestions(A, "False", "True", "False", 1);

        // Crear grafo

        Graph<Person> graph = new Graph<Person>();
/*
        System.out.println("Weights: ");
        graph.printGraphWithWeights(List.of(A, B, C, D, E, F, G, H, I, J));
        System.out.println("Infection type: ");
        graph.printInfectionType(List.of(A, B, C, D, E, F, G, H, I, J));
*/
        System.out.println("Updated weights: ");
        graph.updateWeight(List.of(A, B, C, D, E, F, G, H, I, J));
        graph.printGraphWithWeights(List.of(A, B, C, D, E, F, G, H, I, J));

        System.out.println(" New Infection type: ");
        graph.updateInfectionType(List.of(A, B, C, D, E, F, G, H, I, J));
        //graph.printInfectionType(List.of(A, B, C, D, E, F, G, H, I, J));


        graph.calculateShortestPath(A);
        graph.printPaths(List.of(A, B, C, D, E, F, G, H, I, J));
        System.out.println(C.getPersonInfectionType());
    }

}

/* 
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

        //Printing the questions
        Questions.print(Questions.readQuestions());
    }
}

*/