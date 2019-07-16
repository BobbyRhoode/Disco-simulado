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
    Directorio directorio= new Directorio();;

    
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
        System.out.println(">>>");
        opcion= escaner.nextInt();
        
        switch(opcion)
        {
            case 1: creacionDisco();
                    break;
                    
            case 2: crearArchivo();
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
                    
            case 8: System.out.println("Aún no está implementado");
                    menuInicial();        
                    
            case 9: System.out.println("Aún no está implementado");
                    menuInicial();
            
            case 10: menuInicial();
        }
    }

    
    //inicialización del disco
    private void creacionDisco() throws InterruptedException 
    {
             
        if(discoNuevo.isEmpty())
        {
            System.out.println("No hay disco existente para fomatear");
            System.out.println(" ");    
        }
        
        if(discoNuevo.size()==1)
        {
            System.out.println("Formateando disco");
            directorio.borrandoArray();
            discoNuevo.remove(0);
        }
        
        
        System.out.println("Indique el numero de sectores que tendrá el disco: ");
        int largoDisco= escaner.nextInt();
        
        this.discoNuevo.add(new Disco(largoDisco));
        this.discoNuevo.get(0).montarDisco();
        
        
        this.discoNuevo.get(0).getVolumen().agregarSector(new Sector(512, 0));
        for(int auxSectores=1; auxSectores<largoDisco; auxSectores++)
        {
            this.discoNuevo.get(0).getVolumen().agregarSector(new Sector(512, auxSectores));
            
        }
        
    
    
        this.directorio.setLargoDirectorio(largoDisco);
        this.directorio.rellenadoArrayList();
        System.out.println("Tamaño de sectores: " + this.directorio.largoSectoresOcupados());
        menunormal();
        
    }
    
    //evidentemente se está creando un nuevo archivo, con un nombre no mayor a los 8 dígitos y señalandos el tamaño de éste
    //para saber en cuantos sectores de 512 bytes se debe dividir
    private void crearArchivo() throws InterruptedException 
    {
        
        System.out.println("El nombre del archivo no puede tener más de ocho carácteres");
        
        System.out.println("ingrese el nombre del archivo: ");
        String nombre= escaner.nextLine();
        
        while(nombre.equals("") || nombre.length()>8)
        {
            System.out.println("ingrese el nombre del archivo: ");
            nombre= escaner.nextLine();
        }
        
        System.out.println("ingrese el tamaño del archivo: ");
        int largoArchivo= escaner.nextInt();
        int numeroDeSectores= (int)(largoArchivo/512)+1;
        
        ArrayList<Integer> sectoresDisp= buscarSectoresDisponibles(numeroDeSectores);
        if(sectoresDisp==null)
        {
            System.out.println("No hay espacio disponible para agregar nuevos archivos");
        }
        
        else
        {
            System.out.println("sectores disponibles: " + sectoresDisp.size());
            for(int variableAux=0; variableAux<sectoresDisp.size(); variableAux++)
            {    
                String algo =  "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
                + "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
                + "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
                + "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
                + "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
                + "XXXXXXXXXXXX";
                this.discoNuevo.get(0).getVolumen().obtenerSector(sectoresDisp.get(variableAux)).setPalabra(algo);
                this.discoNuevo.get(0).getVolumen().obtenerSector(sectoresDisp.get(variableAux)).setContenido(algo.getBytes());
            }
        }
        
        menuInicial();
    }
    
    //busca espacios libres en el directorio, por simplicidad se está haciendo con asignación contigua
    private ArrayList buscarSectoresDisponibles(int numeroDeSectores) 
    { 
        int bloque=-1;
        ArrayList<Integer> bloquesDisponibles= new ArrayList<>();
        System.out.println("jolaperra");
        System.out.println("For peligroso");
        for(int variable=0; variable<discoNuevo.get(0).getDirectorio().getLargoDirectorio(); variable++)
        {
            //System.out.println(discoNuevo.get(1).getDirectorio().getSectoresOcupados(variable);
            //System.out.println("tamaño" + discoNuevo.size());
            bloque=  discoNuevo.get(0).getDirectorio().getSectorLibreProximo();
                    System.out.println("}jolaperra");
            bloquesDisponibles.add(bloque);
      //      for (Integer bloquesDisponible : bloquesDisponibles) 
      //      {
      //         if(bloque!=bloquesDisponible)
      //         {
      //         }
                    
      //      }
            
        }
        System.out.println("bloques disponibles: " + bloquesDisponibles.size());
        
        if(bloquesDisponibles.size()==numeroDeSectores)
        {
            return bloquesDisponibles;
        }
        
        return null;
    }
    
    //Main
    public static void main(String[] args) throws InterruptedException 
    {
        Simulacion simulacion= new Simulacion();
        simulacion.menuInicial();
        
    }
    
}