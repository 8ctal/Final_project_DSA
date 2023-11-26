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
    
<<<<<<< HEAD
    public void addArista(int u, int v, int peso)
    {
        matrizAdyacencia[u][v] = peso;
=======
    public void addArista(int u, int v, Nodo nodo)
    {
        matrizAdyacencia[u][v] = 0;
        

>>>>>>> 86c9544663138c8e723c89b469a333a8de587ecf
        A++;
    }
}