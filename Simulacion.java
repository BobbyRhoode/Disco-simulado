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
    int opcion;

    
    //constructor
    public Simulacion() 
    {
        this.escaner= new Scanner(System.in);
        this.discoNuevo= new ArrayList<>(1);
    }
    
    
    //Menú de inicio
    public void menuInicial() throws InterruptedException {
        
        System.out.println("Que desea hacer?");
        System.out.println("1.- Iniciar simulación");
        System.out.println("2.- Salir");
        System.out.println(">>> ");
        opcion= escaner.nextInt();
        
        while (opcion < 1 && opcion > 2) {
            System.out.println("Numero erroneo, ingreselo nuevamente\n>>> ");
            opcion = escaner.nextInt();
        }
        
        switch(opcion) {
            case 1: menunormal();
            case 2: break;
        }
    }

    //menú donde se hacen las llamadas de sistema
    private void menunormal() throws InterruptedException {
        
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
        System.out.println(">>>");
        opcion= escaner.nextInt();
        
        while (opcion < 0 && opcion > 9) {
            System.out.println("Numero erroneo, ingreselo nuevamente\n>>> ");
            opcion = escaner.nextInt();
        }
        
        switch(opcion) {
            case 1: creacionDisco();
                    break;
                    
            case 2: crearArchivo();
                    break;
                    //menuInicial();
            
            case 3: System.out.println("Aún no está implementado");
                    break;
            
            case 4: System.out.println("Aún no está implementado");
                    break;
                    
            case 5: System.out.println("Aún no está implementado");
                    break;
            
            case 6: System.out.println("Aún no está implementado");
                    break;        
                    
            case 7: System.out.println("Aún no está implementado");
                    break;        
                    
            case 8: System.out.println("Aún no está implementado");
                    break;        
                    
            case 9: System.out.println("Aún no está implementado");
                    break;
            
            case 10: menuInicial();
        }
    }

    
    //inicialización del disco
    private void creacionDisco() throws InterruptedException {
             
        if(discoNuevo.isEmpty()) {
            System.out.println("No hay disco existente para fomatear");
            System.out.println(" ");    
        }
        
        if(discoNuevo.size()==1) {
            System.out.println("Formateando disco");
            directorio.borrandoArray();
            discoNuevo.remove(0);
        }
        
        
        System.out.println("Indique el numero de sectores que tendrá el disco: ");
        int largoDisco= escaner.nextInt();
        
        while (largoDisco < 0 && largoDisco > 128) {
            System.out.println("Numero erroneo, ingreselo nuevamente\n>>> ");
            largoDisco = escaner.nextInt();
        }
        
        this.discoNuevo.add(new Disco(largoDisco));
        this.discoNuevo.get(0).montarDisco();
        
        
        this.discoNuevo.get(0).getVolumen().agregarSector(new Sector(512, 0));
        for(int auxSectores=1; auxSectores<largoDisco; auxSectores++) {
            this.discoNuevo.get(0).getVolumen().agregarSector(new Sector(512, auxSectores));
            
        }
        
    
    
       
        this.directorio.rellenadoArrayList(largoDisco);
        System.out.println("Tamaño de sectores: " + this.directorio.largoSectoresOcupados());
        menunormal();
        
    }
    
    //evidentemente se está creando un nuevo archivo, con un nombre no mayor a los 8 dígitos y señalandos el tamaño de éste
    //para saber en cuantos sectores de 512 bytes se debe dividir
    private void crearArchivo() throws InterruptedException {
        
        System.out.println("El nombre del archivo no puede tener más de ocho carácteres");
        
        System.out.println("ingrese el nombre del archivo: ");
        String nombre= escaner.nextLine();
        
        while(nombre.equals("") || nombre.length()>8) {
            System.out.println("El nombre posee mas de caracteres de los permitidos o no coloco nombre, porfavor hagalo nuevamente: ");
            nombre= escaner.nextLine();
        }
        
        System.out.println("ingrese el tamaño del archivo: ");
        int largoArchivo= escaner.nextInt();
        
        while (largoArchivo < 0 && largoArchivo > 512) {
            System.out.println("Numero erroneo, ingreselo nuevamente\n>>> ");
            largoArchivo = escaner.nextInt();
        }
        
        int numeroDeSectores= (int)(largoArchivo/512)+1;
        
        ArrayList<Integer> sectoresDisp= buscarSectoresDisponibles(numeroDeSectores);
        if(sectoresDisp==null) {
            System.out.println("No hay espacio disponible para agregar nuevos archivos");
        }
        
        else {
            System.out.println("posicion de disco disponibles: " + sectoresDisp.size());
            for(int variableAux=0; variableAux<sectoresDisp.size(); variableAux++) {    
                String archivoNuevo =  "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
                + "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
                + "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
                + "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
                + "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
                + "XXXXXXXXXXXX";
                this.discoNuevo.get(0).getVolumen().obtenerSector(sectoresDisp.get(variableAux)).setPalabra(archivoNuevo);
                this.discoNuevo.get(0).getVolumen().obtenerSector(sectoresDisp.get(variableAux)).setContenido(archivoNuevo.getBytes());
            }
        }
        
        menuInicial();
    }
    
    //busca espacios libres en el directorio, por simplicidad se está haciendo con asignación contigua
    private ArrayList buscarSectoresDisponibles(int numeroDeSectores) { 
        int bloque=-1;
        
        ArrayList<Integer> bloquesDisponibles= new ArrayList<>();
        
        for(int variable=0; variable<numeroDeSectores; variable++) {
           
            bloque= this.directorio.getSectorLibreProximo();
            bloquesDisponibles.add(bloque);
            
        }
        
        if(bloquesDisponibles.size()==numeroDeSectores) {
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
