package disco;

import java.util.ArrayList;

/*
 * @author Joaquin
 */

public class Volumen 
{
    private ArrayList <Sector> sectores;
    private ArrayList <Integer> estaAbierto;
    private ArrayList <Integer> estaDesocupado;

    public Volumen(int valor) 
    {
        this.sectores= new ArrayList<>(valor);
        this.estaAbierto= new ArrayList<>(valor);
        this.estaDesocupado= new ArrayList<>(valor);
        for(int i=0; i<valor; i++)
        {
            this.sectores.add(new Sector(512, 0));
            this.estaAbierto.add(0);
            this.estaDesocupado.add(0);
        }
    }
    
    
    public boolean getEstaDesocupado(int i)
    {
        if(this.estaAbierto.get(i)==0)
            return true;
        return false;
    }
    
    public void setEstaDesocupado(int i)
    {
        if(this.estaDesocupado.get(i)==1)
            this.estaDesocupado.set(i, 0);
        
        else if(this.estaDesocupado.get(i)==0)
            this.estaDesocupado.set(i, 1);
    }
    
    public boolean getEstaAbierto(int i)
    {
        if(this.estaAbierto.get(i)==0)
            return true;
        return false;
    }
    
    public void setEstaAbierto(int i)
    {
        if(this.estaAbierto.get(i)==1)
            this.estaAbierto.set(i, 0);
        
        else if(this.estaAbierto.get(i)==0)
            this.estaAbierto.set(i, 1);
    }
    
    public void agregarSector(Sector sector)
    {
        this.sectores.add(sector);
    }
    
    public void configurarSector(int i, Sector element)
    {
        this.sectores.set(i, element);
    }
    
    public Sector obtenerSector(int i)
    {
        return this.sectores.get(i);
    }
    
    public void eliminarSector(int i)
    {
        this.sectores.remove(i);
    }
    
    public int tamanoSector()
    {
        return this.sectores.size();
    }
    
}
