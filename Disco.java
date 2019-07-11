package disco;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @author Joaquin
 */

public class Disco 
{
    private File archivoDeDisco;
    private int NroTotalSectores;
    
    public Disco(int NroTotalSectores)
    {
        this.NroTotalSectores = NroTotalSectores;
        this.archivoDeDisco = new File("DISCO");
    }
    
    public void montarDisco(int n)
    {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(this.archivoDeDisco);
            pw = new PrintWriter(fichero);
            
            for(int i=0; i<this.NroTotalSectores; i++)
            {
                //pw.println(new String((new Sector()).getContenido()));               
                
                pw.println(new Sector(512, n));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {  
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }    
    
    public Sector leerSector(int numSector)
    {		
        if(this.NroTotalSectores >= numSector && numSector >= 0) //condición que valida que el número ingresado a esta función esté en
        {                                                   //en el rango definido incialmente
            
            Scanner lector;
            try 
            {
                lector = new Scanner(this.archivoDeDisco);
                                        
                for (int i=0; i<NroTotalSectores && lector.hasNextLine(); i++) 
                {
                    Sector sector = new Sector(512, i);
                    String linea = lector.nextLine();
                    if(i == numSector)
                    {
                        sector.setContenido(linea.getBytes());
                        lector.close();
                        return sector;
                    }                                             
                                                         
                }                        
                lector.close();
            } 
            catch (FileNotFoundException e) 
            {
                e.printStackTrace();
            }
			
            return null;
        }
        return null;
    }
	
    public void escribirSector(int numSector, Sector sector)
    {
        try {
            RandomAccessFile fichero = new RandomAccessFile("DISCO", "rw");
            
            long posicionPuntero = 512*numSector + numSector*2;
            
            fichero.seek(posicionPuntero);
            String s = new String(sector.getContenido());
            
            fichero.writeBytes(s);

        } catch (IOException ex) {
            Logger.getLogger(Disco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    
    public int getNumSectores() {
        return this.NroTotalSectores;
    }
    
    
    
}
