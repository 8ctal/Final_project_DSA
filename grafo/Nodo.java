package grafo;

public class Nodo {
    private String nombre;
    private int pesoBase = 30;
    private String respuesta;

    public Nodo(String nombre, String respuesta)
    {
        this.nombre = nombre;
        addRespuesta(respuesta);
    }

    public void addRespuesta(String respuesta)
    {
        System.out.println();
    }
}
