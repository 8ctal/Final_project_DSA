package grafo;

import java.util.ArrayList;

public class preguntas {
    private ArrayList<String> preguntas = new ArrayList<String>();
    private String respuesta;

    public void pregunta(String respuesta)
    {
        for(int i = 0; i < preguntas.size(); i++)
        {
            System.out.println(preguntas.get(i));
        }
        
    }

    private void listPreguntas()
    {
        preguntas.add("¿Es familiar del infectado?");
        preguntas.add("¿Es amgio del infectado?");
        preguntas.add("¿Ha estado en contacto con el infectado?");
        preguntas.add("¿Ha trabaja con el infectado?");
        preguntas.add("¿Estudia con el infectado?");
    }
}
