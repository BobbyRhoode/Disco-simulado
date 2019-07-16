package disco;

import java.io.File;
import java.util.ArrayList;

/*
 * @author Joaquin
 */

public class Directorio 
{
    private ArrayList<String> nombresArchivos;
    private ArrayList<Integer> sectoresOcupados;
    private int largoDirectorio;

    public Directorio() 
    {
        this.nombresArchivos= new ArrayList<>();
        this.sectoresOcupados= new ArrayList<>();
        //this.largoDirectorio= 0;
    }
    
    public void rellenadoArrayList(int largoDir)
    {
        this.largoDirectorio= largoDir;
        for(int i=0; i<largoDirectorio-1; i++)
        {
            if(i==0 || i==1)
            {
                this.nombresArchivos.add("");
                this.sectoresOcupados.add(1);
            }
            
            else
            {
                this.nombresArchivos.add("");
                this.sectoresOcupados.add(0);
            
            }
        }
        System.out.println("Sectores ocupados (desde directorio): " + sectoresOcupados.size());
    }
    
    public void borrandoArray()
    {
        for(int i=0; i<largoDirectorio-1; i++)
        {
            if(i==0)
            {
                this.nombresArchivos.add("");
                this.sectoresOcupados.add(0);
            }
            
            else
            {
                this.nombresArchivos.add("");
                this.sectoresOcupados.add(0);
            
            }
        }
    }
    
    
    public String getNombresArchivos(int v) 
    {
        return this.nombresArchivos.get(v);
    }

    public void setNombresArchivos(int v, String nombre) 
    {
        this.nombresArchivos.set(v, nombre);
    }

    public int getSectoresOcupados(int v) 
    {
        return this.sectoresOcupados.get(v);
    }
    
    public int largoSectoresOcupados()
    {
        return this.sectoresOcupados.size();
    }
    
    public int getSectorLibreProximo()
    {
        
        for(int auxiliar=0; auxiliar<this.sectoresOcupados.size(); auxiliar++)
        {
            if(this.sectoresOcupados.get(auxiliar)==1)
            {}
            else
            {
               
                if(this.sectoresOcupados.get(auxiliar)==0)
                {
                    this.sectoresOcupados.set(auxiliar,1);
                    return auxiliar;
                }
            }
        }
        
        return -1;
    }

    public void setSectoresOcupados(int v, int cambioEstado) 
    {
        this.sectoresOcupados.set(v, cambioEstado);
    }

    public int getLargoDirectorio() 
    {
        return this.largoDirectorio;
    }

    public void setLargoDirectorio(int largoDirectorio) 
    {
        this.largoDirectorio = largoDirectorio;
    }
    
    
    
}
