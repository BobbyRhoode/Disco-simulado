package disco;

import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @author Joaquin
 */
public class Simulacion 
{
    
    private ArrayList<Disco> discoNuevo;
    Directorio directorio= new Directorio();;

    
    Scanner escaner;
    Scanner es;
    int opcion=0;

    
    //constructor
    public Simulacion() 
    {
        this.escaner= new Scanner(System.in);
        this.es= new Scanner(System.in);
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
       // System.out.println("11- número de sectores");
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
            
            case 3: eliminarArchivo();
                    break;
                    //System.out.println("Aún no está implementado");
                    //menuInicial();
            
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
            
            //case 11: System.out.println(this.discoNuevo.get(0).getVolumen().tamanoSector());
              //       menunormal();
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
       
        this.directorio.rellenadoArrayList(largoDisco);
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
            System.out.println("posicion de disco disponibles: " + sectoresDisp.size());
            for(int variableAux=0; variableAux<sectoresDisp.size(); variableAux++)
            {    
                directorio.setNombresArchivos(sectoresDisp.get(variableAux), nombre);
                if(largoArchivo>512)
                {
                    directorio.setLargoArchivo(sectoresDisp.get(variableAux), 512);
                    largoArchivo-=512;
                }
                
                else if (largoArchivo<=512)
                {
                    directorio.setLargoArchivo(sectoresDisp.get(variableAux), largoArchivo);
                }
                
                else if(largoArchivo<=0)
                {
                    System.out.println("DANGER! DANGER! problema con la división de memoria en el directorio");
                }
                
                String algo =  "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
                + "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
                + "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
                + "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
                + "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
                + "XXXXXXXXXXXX";
                this.discoNuevo.get(0).getVolumen().obtenerSector(sectoresDisp.get(variableAux)).setPalabra(algo);
                this.discoNuevo.get(0).getVolumen().obtenerSector(sectoresDisp.get(variableAux)).setContenido(algo.getBytes());
                
                this.discoNuevo.get(0).getVolumen().setEstaDesocupado(sectoresDisp.get(variableAux));
                
            }
        }
        
        menunormal();
    }
    
    //busca espacios libres en el directorio, por simplicidad se está haciendo con asignación contigua
    private ArrayList buscarSectoresDisponibles(int numeroDeSectores) 
    { 
        int bloque=-1;
        ArrayList<Integer> bloquesDisponibles= new ArrayList<>();
        System.out.println("jolaperra");
        System.out.println("For peligroso");
        for(int variable=0; variable<numeroDeSectores; variable++)
        {
           
            bloque= this.directorio.getSectorLibreProximo();
            bloquesDisponibles.add(bloque);
        }
        
        if(bloquesDisponibles.size()==numeroDeSectores)
        {
            return bloquesDisponibles;
        }
        
        return null;
    }
    
    
    //método cuya función es, cómo indica su nombre, explicar que hace cada opción de la administración del disco
    private void mostrarExplicacionFunciones() throws InterruptedException 
    {
        System.out.println("1.- Format: Para crear un volumen y su sistema de archivos asociado.");
        System.out.println("2.- Create: Para crear un archivo, con su correspondiente entrada en el directorio, especificando\n" +
"su nombre y tamaño en bytes.");
        System.out.println("3.- Remove: Para borrar un archivo, eliminando su entrada del directorio y liberando el espacio en\n" +
"disco que ocupaba.");
        System.out.println("4.- Open: Para abrir un archivo de lectura y escritura, a partir de su nombre.");
        System.out.println("5.- ReadAt: Para leer un archivo previamente abierto en una determinada posición.");
        System.out.println("6.- WriteAt: Para escribir un archivo previamente abierto en una determinada posición.");
        System.out.println("7.- PrintFile: Para mostrar en la pantalla el contenido de un archivo.");
        System.out.println("8.- List: Para mostrar en pantalla las entradas del directorio, incluyendo el tamaño en bytes de\n" +
"cada archivo.");
        int escape=-1;
        while(escape!=0)
        {
            System.out.println("Ingrese 0 para regresar atrás");
        }
        
        if(escape==0)
            menunormal();
        else
            mostrarExplicacionFunciones();
    }
    
    
    //método que hace su trabajo cuando se selecciona la función List
    private void listarDirectorio() throws InterruptedException 
    {
        for(int i=0; i<directorio.tamanoNombresArchivos(); i++)
        {
            System.out.println(directorio.getNombresArchivos(i));
            System.out.println(directorio.getSectoresOcupados(i));
            System.out.println(directorio.getLargoArchivo(i));
            
            if(directorio.getSectoresOcupados(i)==0)
                break;
        }
        
        int opcionActual=-1;
        while(opcionActual!=0)
        {
            System.out.println("Ingrese 0 para volver al menú anterior: ");
            opcionActual= escaner.nextInt();
        }
        
        if(opcionActual==0)
            menunormal();
        else
        {
            System.out.println("DANGER! DANGER! No se puede regresar atrás");
            listarDirectorio();
        }
    }
    
    private void eliminarArchivo() 
    {
        int auxiliarPrevio=-1;
        int enteroAuxiliar=-1; //Este entero sólo será negativo si no se encuentra la palabra buscada. Si se queda así, el método se llama de nuevo
        
        String receptorPalabra=" ";
        while(auxiliarPrevio!=0)
        {
            System.out.println("Ingrese el nombre del archivo que desea eliminar: ");
            receptorPalabra= es.nextLine();
            if(receptorPalabra!= " ")
            {
                auxiliarPrevio++;
            }
                
        }
       
        for(int entero=0; entero<directorio.tamanoNombresArchivos(); entero++)
        {
            if(directorio.getNombresArchivos(entero).equals(receptorPalabra))
            {
                enteroAuxiliar++;
                directorio.setNombresArchivos(entero," ");
                directorio.setSectoresOcupados(entero, 0);
                this.discoNuevo.get(0).getVolumen().setEstaDesocupado(entero);
                this.discoNuevo.get(0).getVolumen().configurarSector(entero, new Sector(512, entero));
                
                System.out.println("Archivo " + receptorPalabra + " Eliminado");
            }
        
        }
        if(enteroAuxiliar==-1)
        {
            System.out.println("DANGER! DANGER! la eliminicación del archivo " + receptorPalabra + " no fue encontrado por ningún lado");
            try {
                wait(2000);
            } catch (InterruptedException ex) 
            {
                Logger.getLogger(Simulacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Intente otra vez");
            eliminarArchivo();
        }
        
        
        
    }
    
    
    //Main
    public static void main(String[] args) throws InterruptedException 
    {
        Simulacion simulacion= new Simulacion();
        simulacion.menuInicial();
        
    }

    
}