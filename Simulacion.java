package disco;

import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
 * @author Joaquin
 */
public class Simulacion 
{
    
    private ArrayList<Disco> discoNuevo;
    Directorio directorio= new Directorio();
    Archivo archer = new Archivo();

    
    Scanner escaner;
    int opcion=0;

    
    //constructor
    public Simulacion() 
    {
        this.escaner= new Scanner(System.in);
        this.discoNuevo= new ArrayList<>(1);
    }
    
    
    //Menú de inicio
    public void menuInicial() throws InterruptedException
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

    //menú donde se hacen las llamadas de sistema
    private void menunormal() throws InterruptedException 
    {
        
        System.out.println("1.- Format");
        System.out.println("2.- Create");
        System.out.println("3.- Remove");
        System.out.println("4.- Open");
        System.out.println("5.- ReadAt");
        System.out.println("6.- WriteAt");
        System.out.println("7.- PrintFile");
        System.out.println("8.- List");
        System.out.println("9.- Más Información");
        System.out.println("0.- Volver");
        System.out.println(">>>");
        opcion= escaner.nextInt();
        
        
        
        switch(opcion)
        {
            case 1: archer.creacionDisco();
                    break;
                    
            case 2: archer.crearArchivo();
                    break;
                    //System.out.println("Aún no está implementado");
                    //menuInicial();
            
            case 3: System.out.println("Aún no está implementado");
                    menuInicial();
            
            case 4: System.out.println("Aún no está implementado");
                    menuInicial();
                    
            case 5: System.out.println("Aún no está implementado");
                    menuInicial();
            
            case 6: System.out.println("Aún no está implementado");
                    menuInicial();        
                    
            case 7: System.out.println("Aún no está implementado");
                    menuInicial();        
                    
            case 8: listarDirectorio();
                    //System.out.println("Aún no está implementado");
                    menunormal();        
                    
            case 9: mostrarExplicacionFunciones();
                    menunormal();
            
            case 10: menuInicial();
            
            case 11:
        }
    }
    
    //Main
    public static void main(String[] args) throws InterruptedException 
    {
        Simulacion simulacion= new Simulacion();
        simulacion.menuInicial();
        
    }
    
}
