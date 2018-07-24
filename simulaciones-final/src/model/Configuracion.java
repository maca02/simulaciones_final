/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class Configuracion {

    private static Configuracion instancia;

    //Demora atencion desde
    //hasta
    //UNIFORME
    private double tiempoAtencionDesde;
    private double tiempoAtencionHasta;

    //Llegada alumnos media
    //Exponencial Negativa
    private double mediaLlegadaClientes;

    private int minutosASimular;
    private int minutoDesde;
    private int iteracionesAMostrar;

    private Configuracion() {
        this.mediaLlegadaClientes = 5;
        this.tiempoAtencionDesde = 7.5;
        this.tiempoAtencionHasta = 10.5;
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

    public static Configuracion getConfiguracion() {
        if (instancia == null) {
            instancia = new Configuracion();
        }
        return instancia;
    }

    public static Configuracion getConfiguracionPorDefecto() {
        instancia = new Configuracion();
        return instancia;
    }

    public int getMinutosASimular() {
        return this.minutosASimular;
    }

    public void setMinutosASimular(int minutosASimular) {
        this.minutosASimular = minutosASimular;
    }

    public void setMinutoDesde(int minutoDesde) {
        this.minutoDesde = minutoDesde;
    }

    public int getMinutoDesde() {
        return this.minutoDesde;
    }

    public int getIteracionesAMostrar() {
        return this.iteracionesAMostrar;
    }

    public void setIteracionesAMostrar(int iteracionesAMostrar) {
        this.iteracionesAMostrar = iteracionesAMostrar;
    }
}
