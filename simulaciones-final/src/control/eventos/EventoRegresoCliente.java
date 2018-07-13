/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.eventos;

import control.ControladorSimulacion;
import control.VectorEstado;
import static control.eventos.Evento.clonarClientes;
import eventos.FinAtecion;
import eventos.LlegadaCliente;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.Configuracion;
import objects.Cliente;
import objects.Distribuciones;
//import objects.Maquina;
import objects.Servidor;

/**
 *
 * @author heftyn
 */
public class EventoRegresoCliente extends Evento
{

    public EventoRegresoCliente(String nombre) {
        super(nombre);
    }
        @Override
    public void actualizarEstadoVector() {
        VectorEstado actual = ControladorSimulacion.getVectorActual();
        VectorEstado anterior = ControladorSimulacion.getVectorAnterior();
        
        actual.setAcumuladoClientesPerdidos(anterior.getAcumuladoClientesPerdidos());
        actual.setAcumuladoClientesQueLleganYSeVan(anterior.getAcumuladoClientesQueLleganYSeVan());
        actual.setAcumuladoAtendidos(anterior.getAcumuladoAtendidos());
        actual.setClientes(clonarClientes(anterior.getClientes()));
        actual.setColaClientes(anterior.getColaClientes().clone());
        actual.setServidor(anterior.getServidor().clone());
        actual.setFinAtencion(anterior.getFinAtencion().clone());
        //actual.setFinMantenimiento(anterior.getFinMantenimiento().clone());
        //actual.setInicioMantenimiento(anterior.getInicioMantenimiento().clone());
        actual.setLlegadaCliente(anterior.getLlegadaCliente().clone());
        //actual.setMaquinas(clonarMaquinas(anterior.getMaquinasList()));
        
        Random randomObject = new Random();
        FinAtecion newFinAtencion = new FinAtecion();
        //Variables para fin inscripcion
        double rndAtencion = 0.0;
        double tAtencion = 0.0;
        double finAtencion = 0.0;
        
        Cliente clienteQueVuelve = encontrarClienteQueVuelve(actual);
        clienteQueVuelve.setRegreso(Cliente.Regreso.SI);
        clienteQueVuelve.setTiempo_esperando(Double.MAX_VALUE);
        
        if (actual.getColaClientes().getColaClientes() == 0){
            Servidor servidor = actual.getServidor();
            
            if(servidor.getEstado().equals(Servidor.Estado.LIBRE)){
                rndAtencion = randomObject.nextDouble();
                tAtencion = Distribuciones.calcular_uniforme(
                        Configuracion.getConfiguracionPorDefecto().getTiempoAtencionDesde(),
                        Configuracion.getConfiguracionPorDefecto().getTiempoAtencionHasta(),
                        rndAtencion);
                finAtencion = tAtencion + actual.getReloj();
                
                 newFinAtencion.setRnd(rndAtencion);
                 newFinAtencion.settAtencion(tAtencion);
                 newFinAtencion.setFinAtencion(finAtencion);
                
                actual.setFinAtencion(newFinAtencion);
                clienteQueVuelve.setEstado(Cliente.Estado.SIENDO_ATENDIDO);
                clienteQueVuelve.setHora_regreso_sistema(Double.MAX_VALUE);
                servidor.setEstado(Servidor.Estado.OCUPADO);     
                calculoTiempoEspera(actual,anterior);

                    
            }
            else{
                clienteQueVuelve.setEstado(Cliente.Estado.ESPERANDO_ATENCION);
                calculoTiempoEspera(actual,anterior);
                actual.getColaClientes().agregarAlumnoCola();
            }
        }
        else if (actual.getColaClientes().getColaClientes()>= 10 )
        {
            //Se va de nuevo el...
            //clienteQueVuelve.setEstado(Cliente.Estado.ESPERANDO_PARA_REGRESAR);
            //clienteQueVuelve.setHora_regreso_sistema(actual.getReloj() + 30);
            actual.setAcumuladoClientesPerdidos(actual.getAcumuladoClientesPerdidos() + 1);
            calculoTiempoEspera(actual,anterior);

        }
        else 
        {
            //A la cola
            clienteQueVuelve.setEstado(Cliente.Estado.ESPERANDO_ATENCION);
            //clienteQueVuelve.setHora_regreso_sistema(Double.MAX_VALUE);
            calculoTiempoEspera(actual,anterior);
            actual.getColaClientes().agregarAlumnoCola();
        }           
            
        
        /**
         * Borrar atributo de regreso del alumno.
         * Hacer lo mismo q en LlegadaAlumno pero sin incrementar ACs
         */
    }


    private Cliente encontrarClienteQueVuelve(VectorEstado actual) {
        
        for (Cliente cliente : actual.getClientes())
        {
            if (cliente.getEstado().equals(Cliente.Estado.ESPERANDO_PARA_REGRESAR)
                    && actual.getReloj() == cliente.getHora_regreso_sistema())
            {
                return cliente;
            }
        }
        throw new NullPointerException("No se encontro el alumno que regresaba a los : " + actual.getReloj());
    }
    
     public void calculoTiempoEspera(VectorEstado actual, VectorEstado anterior){
        double tiempoEspera = 0.0;
        for(Cliente cli: actual.getClientes()){
            if((cli.getEstado().equals(Cliente.Estado.ESPERANDO_ATENCION)) && (cli.getRegreso().equals(Cliente.Regreso.NO))){
                tiempoEspera = cli.getTiempo_esperando()+(actual.getReloj()- anterior.getReloj());
                if (tiempoEspera >= 20){
                    double minutosQueRegresa = 60.0;
                    cli.setHora_regreso_sistema(actual.getReloj()+ minutosQueRegresa);
                    cli.setEstado(Cliente.Estado.ESPERANDO_PARA_REGRESAR);                  
                    
                }                    
            }
            else if ((cli.getEstado().equals(Cliente.Estado.ESPERANDO_ATENCION)) && (cli.getRegreso().equals(Cliente.Regreso.SI))){
                tiempoEspera = cli.getTiempo_esperando()+(actual.getReloj()- anterior.getReloj());
                if (tiempoEspera >= 20){
                    actual.setAcumuladoClientesQueLleganYSeVan(actual.getAcumuladoClientesQueLleganYSeVan() + 1);
                    List<Cliente> clientesActuales = clonarClientes(anterior.getClientes());
                    Cliente clienteQueEsperoDemasiado = cli; 
                    clientesActuales.remove(clienteQueEsperoDemasiado); //Si dios quiere nadie mas lo referenciaba jaja
                    clienteQueEsperoDemasiado = null;
                    actual.setClientes(clientesActuales);
                }
        }
            else{
                cli.setTiempo_esperando(cli.getTiempo_esperando());                        
            }
        }
    }
}
