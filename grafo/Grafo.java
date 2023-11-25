package grafo;

public class Grafo {
    private int V;
    private int A;
    private int[][] matrizAdyacencia;

    public Grafo(int nodos)
    {
        this.V = nodos;
        this.A = 0;
        this.matrizAdyacencia = new int[nodos][nodos];
    }
    
    public void addArista(int u, int v)
    {
        matrizAdyacencia[u][v] = 1;
        matrizAdyacencia[v][u] = 1;
        A++;
    }
}