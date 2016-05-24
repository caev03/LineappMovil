package Mundo;

/**
 * Created by cami on 4/25/16.
 */
public class Estudiante
{
    String id;
    String correo;
    static Estudiante instancia;
    String turno;

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

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getTurno() {
        return turno;
    }
    public void setCorreo(String abc)
    {
        correo = abc;
    }
    public String getCorreo()
    {
        return correo;
    }
}
