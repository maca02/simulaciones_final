/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.eventos;

import control.ControladorSimulacion;
import control.VectorEstado;
import eventos.FinAtecion;
//import eventos.FinMantenimiento;
//import eventos.InicioMantenimiento;
import eventos.LlegadaCliente;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.Configuracion;
import objects.ColaClientes;
import objects.Distribuciones;
import objects.Servidor;
//import objects.Maquina;

/**
 *
 * @author heftyn
 */
public class EventoInicial extends Evento
{

    public EventoInicial(String nombre) {
        super(nombre);
    }

    @Override
    public void actualizarEstadoVector() 
    {
        VectorEstado actual = ControladorSimulacion.getVectorActual();
        VectorEstado anterior = ControladorSimulacion.getVectorAnterior();
        
        //DONDE SE DEFINE EL RELOJ DE INICIO????
        actual.setAcumuladoClientesPerdidos(0);
        actual.setAcumuladoClientesQueLleganYSeVan(0);
        actual.setAcumuladoAtendidos(0);
        actual.setClientes(new ArrayList<>());
        actual.setColaClientes(new ColaClientes(0));
        actual.setServidor(new Servidor(Servidor.Estado.LIBRE));
        actual.setFinAtencion(new FinAtecion());
        //actual.setFinMantenimiento(new FinMantenimiento());
        //actual.setMaquinas(getMaquinasList());
        
        //Seteo de la proxima llegada
        LlegadaCliente proximaLlegada = new LlegadaCliente();
        proximaLlegada.setRnd(new Random().nextDouble());
        double tiempoEntreLlegadas = Distribuciones.calcular_exponencial(
                Configuracion.getConfiguracion().getMediaLlegadaClientes(),
                proximaLlegada.getRnd());
        proximaLlegada.setTiempo_entre_llegadas(tiempoEntreLlegadas);
        proximaLlegada.setProx_llegada(actual.getReloj() + tiempoEntreLlegadas);
                
        actual.setLlegadaCliente(proximaLlegada);
    }

   
    
}
