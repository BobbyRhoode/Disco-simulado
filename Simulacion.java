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
    int opcion;

    public static void main(String[] args) {
        Simulacion simula = new Simulacion();
    }
    
    public Simulacion() {
        this.escaner= new Scanner(System.in);
        this.discoNuevo= new ArrayList<>(1);
    }
    
    public void MenuInicial() {
        
        System.out.println("Que desea hacer?");
        System.out.println("1.- Ejecutar Script");
        System.out.println("2.- Realizar simulacion");
        System.out.println("3.- Salir");
        System.out.println(">>> ");
        opcion= Integer.parseInt(escaner.next());
        
        while (opcion < 0 && opcion > 2) {
            System.out.println("Opcion incorrecta, que desea hacer");
            System.out.println("1.- Ejecutar Script");
            System.out.println("2.- Realizar simulacion");
            System.out.println("3.- Salir");
            System.out.println(">>> ");
            opcion= Integer.parseInt(escaner.next());
        }
        
        switch(opcion) {
            case 1: script();
            case 2: menunormal();
            case 3: break;
        }
    }
    
    public void script() {
        
    }

    private void menunormal() {
        
        System.out.println("1.- Format");
        System.out.println("2.- Create");
        System.out.println("3.- Remove");
        System.out.println("4.- Open");
        System.out.println("5.- ReadAt");
        System.out.println("6.- WriteAt");
        System.out.println("7.- PrintFile");
        System.out.println("8.- List");
        System.out.println("9.- Help");
        System.out.println("0.- Volver");
        
        opcion= escaner.nextInt();
        
        switch(opcion) {
            case 1: creacionDisco();
            case 2: creacionArchivo();
            case 3: System.out.print("3");
            case 4: System.out.print("4");
            case 5: System.out.print("5");
            case 6: System.out.print("6");
            case 7: System.out.print("7");
            case 8: System.out.print("8");
            case 9: System.out.print("9");
            case 0: MenuInicial();
        }
    }

    private void creacionDisco() 
    {
        
        if(discoNuevo.size()==0) {
            System.out.println("No hay disco existente para fomatear");
            System.out.println(" ");
            
        }
        
        if(discoNuevo.size()==1) {
            System.out.println("Formateando disco");
        }
        
        System.out.println("Indique el numero de sectores que tendrá el disco: ");
        int sectores = escaner.nextInt();
        
        
        
        
        
    }
    
}
