package Mundo;

/**
 * Created by cami on 4/25/16.
 */
public class Estudiante
{
    String id;
    static Estudiante instancia;

    public static Estudiante darEstudiante()
    {
        if(instancia==null)
        {
            instancia = new Estudiante();
        }
        return instancia;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
