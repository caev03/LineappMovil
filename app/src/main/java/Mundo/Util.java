package Mundo;

/**
 * Created by cami on 4/30/16.
 */
public class Util
{

    public static String pedirTurno(int id)
    {
        return ""+(id%120)+";"+((id+1)%120)+";"+((id+2)%120);
    }
}
