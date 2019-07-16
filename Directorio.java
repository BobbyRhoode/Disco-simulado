/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package disco;

/**
 *
 * @author Joaquin
 */
class Directorio {
    private ArrayList<String> nombresArchivos;
    private ArrayList<Integer> sectoresOcupados;
    private int largoDirectorio;

    public Directorio() {
        this.nombresArchivos= new ArrayList<>();
        this.sectoresOcupados= new ArrayList<>();
        this.largoDirectorio= 0;
    }
    
    public void rellenadoArrayList() {
        for(int i=0; i<largoDirectorio-1; i++) {
            if(i==0) {
                this.nombresArchivos.add("");
                this.sectoresOcupados.add(1);
            }
            
            else {
                this.nombresArchivos.add("");
                this.sectoresOcupados.add(0);
            
            }
        }
    }
    
    public void borrandoArray() {
        for(int i=0; i<largoDirectorio-1; i++) {
            if(i==0) {
                this.nombresArchivos.add("");
                this.sectoresOcupados.add(0);
            }
            
            else {
                this.nombresArchivos.add("");
                this.sectoresOcupados.add(0);
            
            }
        }
    }
    
    
    public String getNombresArchivos(int v) {
        return this.nombresArchivos.get(v);
    }

    public void setNombresArchivos(int v, String nombre) {
        this.nombresArchivos.set(v, nombre);
    }

    public int getSectoresOcupados(int v) {
        return this.sectoresOcupados.get(v);
    }

    public void setSectoresOcupados(int v, int cambioEstado) {
        this.sectoresOcupados.set(v, cambioEstado);
    }

    public int getLargoDirectorio() {
        return this.largoDirectorio;
    }

    public void setLargoDirectorio(int largoDirectorio) {
        this.largoDirectorio = largoDirectorio;
    }
}
