/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author heftyn
 */
public class Configuracion {
    
    private static Configuracion instancia;

    //Demora inscripcion desde
    //hasta
    //UNIFORME
    private double tiempoAtencionDesde;
    private double tiempoAtencionHasta;
    
    //Llegada alumnos media
    //Exponencial Negativa
    private double mediaLlegadaClientes;
    
    //Incio Mantenimiento desde
    //hasta
    //UNIFORME
   // private double inicioMantenimientoDesde;
    //private double inicioMantenimientoHasta;
    
    //Tiempo Mantenimiento desde
    //hasta
    //NORMAL
   // private double tiempoMantenimientoMedio;
    //private double tiempoMantenimientoDesviacion;
    
    private int minutosASimular;   
    private int minutoDesde;
    private int iteracionesAMostrar;
    
    
    private Configuracion()
    {
        //this.inicioMantenimientoDesde = 57;
        //this.inicioMantenimientoHasta = 63;
        this.mediaLlegadaClientes = 5;
        this.tiempoAtencionDesde = 7.5;
        this.tiempoAtencionHasta = 10.5;
        //this.tiempoMantenimientoDesviacion = 0.0027;
        //this.tiempoMantenimientoMedio = 3;
        //////////////////////////////////////////////
        this.minutosASimular = 600;
        this.minutoDesde = 0;
        this.iteracionesAMostrar = 1000000;
        
    }

    

    public double getTiempoAtencionDesde() {
        return tiempoAtencionDesde;
    }

    public void setTiempoAtencionDesde(double tiempoAtencionDesde) {
        this.tiempoAtencionDesde = tiempoAtencionDesde;
    }

    public double getTiempoAtencionHasta() {
        return tiempoAtencionHasta;
    }

    public void setTiempoAtencionHasta(double tiempoAtencionHasta) {
        this.tiempoAtencionHasta = tiempoAtencionHasta;
    }

    public double getMediaLlegadaClientes() {
        return mediaLlegadaClientes;
    }
    public void setMediaLlegadaClientes(double mediaLlegadaClientes) {
        this.mediaLlegadaClientes = mediaLlegadaClientes;
    }
    public static Configuracion getConfiguracionPorDefecto() 
    {
        instancia = new Configuracion();
        return instancia;
    }

    public int getMinutosASimular() {
        return this.minutosASimular;
    }
    
    public void setMinutosASimular(int minutosASimular)
    {
        this.minutosASimular = minutosASimular;
    }
    
    public void setMinutoDesde(int minutoDesde){
        this.minutoDesde = minutoDesde;
    }
    
    public int getMinutoDesde(){
       return this.minutoDesde;
    }
    
    public int getIteracionesAMostrar(){
        return this.iteracionesAMostrar;
    }
    
    public void setIteracionesAMostrar(int iteracionesAMostrar){
        this.iteracionesAMostrar = iteracionesAMostrar;
    }
}
