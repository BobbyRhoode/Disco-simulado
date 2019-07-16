package disco;

import java.util.ArrayList;

/*
 * @author Joaquin
 */

public class Volumen 
{
    private ArrayList <Sector> sectores;

    public Volumen(int valor) 
    {
        this.sectores= new ArrayList<>(valor);
        this.sectores.add(new Sector(512, 0));
    }
    
    public void agregarSector(Sector sector)
    {
        this.sectores.add(sector);
    }
    
    public void configurarSector(Sector element)
    {
        this.sectores.set(0, element);
    }
    
    public Sector obtenerSector(int i)
    {
        return this.sectores.get(i);
    }
    
    public void eliminarSector(int i)
    {
        this.sectores.remove(i);
    }
    
}
