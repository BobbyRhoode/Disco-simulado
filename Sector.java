package disco;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Joaquin
 */
public class Sector 
{
    private int identificador;
    private byte[] contenido;
    private String palabra;
    private int tamano;
    private ArrayList<Integer> ubicacionesPosteriores;
    
    public Sector(int tamano, int identificador)
    {
        palabra = "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"
                + "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"
                + "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"
                + "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"
                + "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"
                + "000000000000";
        
        this.contenido = palabra.getBytes();
        this.tamano= tamano;
        this.identificador= identificador;
        this.ubicacionesPosteriores= new ArrayList<>();
    }
    
    public void agregarSectoresSiguientes(int i)
    {
        this.ubicacionesPosteriores.add(i);
    }
    public int obtenerSectoresSiguientes(int i)
    {
        return this.ubicacionesPosteriores.get(i);
    }
    
    public void modificarSectoresSiguientes(int i, int valorCambio)
    {
        this.ubicacionesPosteriores.set(i, valorCambio);
    }
    
    public void eliminarSectoresSiguientes(int algo)
    {
        this.ubicacionesPosteriores.remove(algo);
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public byte[] getContenido() 
    {
        return contenido;
    }

    public void setContenido(byte[] contenido) 
    {
        this.contenido = contenido;
    }
}
