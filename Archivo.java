package disco;

import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
 * @author Nicolas
 */

public class Archivo {
    public Archivo() {}
    
    //inicialización del disco
    private void creacionDisco() throws InterruptedException 
    {
             
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
            System.out.println("Dato erroneo, ingrese nuevamente: ");
            largoDisco= Integer.parseInt(escaner.next());
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
    private void crearArchivo() throws InterruptedException  {
        
        System.out.println("El nombre del archivo no puede tener más de ocho carácteres");
        
        System.out.println("ingrese el nombre del archivo: ");
        String nombre= escaner.next();
        
        while(nombre.equals("") || nombre.length()>8) {
            System.out.println("CAmpo vacio o nombre demasiado largo, porfavor ingrese nombre de maximo 8 letras: ");
            nombre= escaner.next();
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
}
