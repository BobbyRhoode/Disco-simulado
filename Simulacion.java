package disco;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * @author Joaquin
 */
public class Simulacion 
{
    
    private ArrayList<Disco> discoNuevo;
    
    Scanner escaner;
    int opcion=0;

    public Simulacion() 
    {
        this.escaner= new Scanner(System.in);
        this.discoNuevo= new ArrayList<>(1);
    }
    
    public void MenuInicial()
    {
        
        System.out.println("Que desea hacer?");
        System.out.println("1.- Iniciar simulación");
        System.out.println("2.- Salir");
        System.out.println(">>> ");
        opcion= escaner.nextInt();
        
        switch(opcion)
        {
            case 1: menunormal();
            
            
            case 2: break;
        }
    }

    private void menunormal() 
    {
        
        opcion=0;
        
        System.out.println("1.- Format");
        System.out.println("2.- Create");
        System.out.println("3.- Remove");
        System.out.println("4.- Open");
        System.out.println("5.- ReadAt");
        System.out.println("6.- WriteAt");
        System.out.println("7.- PrintFile");
        System.out.println("8.- List");
        System.out.println("9.- Más Información");
        System.out.println("10.- Volver");
        opcion= escaner.nextInt();
        
        switch(opcion)
        {
            case 1: creacionDisco();
            case 2: 
            
        }
    }

    private void creacionDisco() 
    {
        
        if(discoNuevo.size()==0)
        {
            System.out.println("No hay disco existente para fomatear");
            System.out.println(" ");
            
        }
        
        if(discoNuevo.size()==1)
        {
            System.out.println("Formateando disco");
        }
        
        System.out.println("Indique el numero de sectores que tendrá el disco: ");
        opcion= escaner.nextInt();
        
        
        
        
        
    }
    
}
