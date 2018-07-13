/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import control.eventos.Evento;
import eventos.FinAtecion;
import eventos.LlegadaCliente;
import java.util.List;
import model.VectorEstadoUI;
import objects.Cliente;
import objects.ColaClientes;
import objects.Servidor;


/**
 *
 * @author heftyn
 */
public class VectorEstado implements VectorEstadoUI
{

    private double reloj;
    
    private Evento evento;
    
    private LlegadaCliente llegadaCliente;
    
    //private InicioMantenimiento inicioMantenimiento;
    
    //private FinMantenimiento finMantenimiento;
    
    private ColaClientes colaClientes;
    
    private FinAtecion finAtencion;
    
    //private List<Maquina> maquinas;
    
    private int acumuladoAtendidos;
    
    private int acumuladoClientesPerdidos;
    
    private int acumuladoClientesQueLleganYSeVan;
    
    private Servidor servidor;
    
    private List<Cliente> clientes;
    
    //private List<Cliente> alumnosInscribiendose;
    
    //private List<Cliente> alumnosEnCola;
    
    @Override
    public double getReloj() {
        return reloj;
    }

    @Override
    public Evento getEvento() {
        return evento;
    }

    @Override
    public LlegadaCliente getLlegadaCliente() {
        return llegadaCliente;
    }

   
    @Override
    public ColaClientes getColaClientes() {
        return colaClientes;
    }

    @Override
    public FinAtecion getFinAtencion() {
        return finAtencion;
    }
   

    @Override
    public int getAcumuladoAtendidos() {
        return acumuladoAtendidos;
    }

    @Override
    public int getAcumuladoClientesPerdidos() {
        return acumuladoClientesPerdidos;
    }

    @Override
    public int getAcumuladoClientesQueLleganYSeVan() {
        return acumuladoClientesQueLleganYSeVan;
    }

    @Override
    public Servidor getServidor() {
        return servidor;
    }

    @Override
    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setReloj(double reloj) {
        this.reloj = reloj;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public void setLlegadaCliente(LlegadaCliente llegadaCliente) {
        this.llegadaCliente = llegadaCliente;
    }
   

    public void setColaClientes(ColaClientes colaClientes) {
        this.colaClientes = colaClientes;
    }

    public void setFinAtencion(FinAtecion finAtencion) {
        this.finAtencion = finAtencion;
    }
   

    public void setAcumuladoAtendidos(int acumuladoAtendidos) {
        this.acumuladoAtendidos = acumuladoAtendidos;
    }

    public void setAcumuladoClientesPerdidos(int acumuladoClientesPerdidos) {
        this.acumuladoClientesPerdidos = acumuladoClientesPerdidos;
    }

    public void setAcumuladoClientesQueLleganYSeVan(int acumuladoClientesQueLleganYSeVan) {
        this.acumuladoClientesQueLleganYSeVan = acumuladoClientesQueLleganYSeVan;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}
