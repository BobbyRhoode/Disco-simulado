package disco;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * @author Joaquin
 */
public class Interaccion 
{

    Scanner escaner;

    public Interaccion() 
    {
        this.escaner= new Scanner(System.in);
    }
    
    /**
     *Esta función, como dice su nombre, interactúa con el usuario. 
     * @return valor. es un entero con el tamaño del volumen
     */
    
    
    //Aquí comienza todo, Muestra la lista de opciones que puede necesitar el usuario. Aquí comienza la compilación.
    public void comienzo()
    {
        int eleccionInicial= -1;
        
        
        System.out.println("Para saber que comandos puede ocupar, ingrese 0");
        System.out.println("Si no, ingrese 1");
        
        while(eleccionInicial!= 0 && eleccionInicial!=1)
        {
            System.out.println(">>> ");
            eleccionInicial= escaner.nextInt();
        }
        
        if(eleccionInicial==0)
            menu();
        
        
        
        
        else
        {
            eleccionPalabraClave();
        }
    }
    
    /**
     *
     */
    public void eleccionPalabraClave()
    {
        String palabra= "";
    
        System.out.println(">>");
        
        
        switch(palabra){
            case "Format":
                crearDirectorio();
            default: comienzo();
        }
        
    }
    
    public void menu()
    {
        System.out.println("Format: Para crear un volumen y su sistema de archivos asociado.");
        System.out.println("Create: Para crear un archivo, con su correspondiente entrada en el directorio, especificandosu nombre y tamaño en byte");
        System.out.println("Remove: Para borrar un archivo, eliminando su entrada del directorio y liberando el espacio en disco que ocupaba");
        System.out.println("Open: Para abrir un archivo de lectura y escritura, a partir de su nombre.");
        System.out.println("ReadAt: Para leer un archivo previamente abierto en una determinada posición.");
        System.out.println("WriteAt: Para escribir un archivo previamente abierto en una determinada posición.");
        System.out.println("PrintFile: Para mostrar en la pantalla el contenido de un archivo.");
        System.out.println("List: Para mostrar en pantalla las entradas del directorio, incluyendo el tamaño en bytes de cada archivo.");
        System.out.println("");
        
        comienzo();
    
    
    }
    public int interaccionCliente()
    {
        
        int valor=-1;
        System.out.println("Buen día");
        System.out.println("El disco puede tener 128 sectores. Parte en el 0 y termina en 127.");
        System.out.println("El tamaño será definido por usted");
        
        while(valor<0 || valor>127)
        {
            System.out.println("¿Cuántos sectores tendrá esta simulación?");
            valor= this.escaner.nextInt();
        }
        return valor;
    }

    private void crearDirectorio() 
    {
        System.out.println("Creando...");
        File directorio;
        FileWriter escritor;
        
    }
}
