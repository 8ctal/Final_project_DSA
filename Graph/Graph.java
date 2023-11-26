package Graph;

public class Graph {
    private int V;
    private int A;
    private int[][] matrizAdyacencia;

    public Graph(int nodos)
    {
        this.V = nodos;
        this.A = 0;
        this.matrizAdyacencia = new int[nodos][nodos];
    }
    
    public void addArista(int u, int v)
    {
        matrizAdyacencia[u][v] = 30;
        A++;
    }
    
}