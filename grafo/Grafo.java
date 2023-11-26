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
    
    public void addArista(int u, int v, int peso)
    {
        matrizAdyacencia[u][v] = peso;
        A++;
    }
    
    public int getPeso(int u, int v)
    {
        return matrizAdyacencia[u][v];
    }
    
    public void setPeso(int u, int v, int peso)
    {
        matrizAdyacencia[u][v] = peso;
    }
}


    

