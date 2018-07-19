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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import model.Configuracion;
import objects.Cliente;
import objects.Distribuciones;
import objects.Servidor;
//import objects.Maquina;

/**
 *
 * @author heftyn
 */
public class EventoFinAtencion extends Evento
{

    public EventoFinAtencion(String nombre) {
        super(nombre);
    }
        @Override
    public void actualizarEstadoVector() {
        VectorEstado actual = ControladorSimulacion.getVectorActual();
        VectorEstado anterior = ControladorSimulacion.getVectorAnterior();
        Random randomObject = new Random();
        
        /*
        Aca lo que tenes que hacer es obtener la hora de este evento, 
        la cual tiene que haberse seteado en el vector actual *antes* de
        llamar a este metodo (siempre).
        VER: ControladorSimulacion.simular()
        */
        double horaActual = actual.getReloj();
        
        /*
        Tambien tenes que buscar cual es el Cliente que se estaba inscribiendo en
        esa maquina y guardar o clonar la referencia a ese objeto.
        Sobre este lo que tenes q hacer es ya borrarlo de la lista porque se
        va del sistema.
        */
        List<Cliente> clientesActuales = clonarClientes(anterior.getClientes());
        Cliente clienteQueSeTerminoDeAtender = obtenerClienteQueSeTerminoDeAtender(clientesActuales);
        clientesActuales.remove(clienteQueSeTerminoDeAtender); //Si dios quiere nadie mas lo referenciaba jaja
        clienteQueSeTerminoDeAtender = null;
        actual.setClientes(clientesActuales);
        
        
        /*
        Copio todo por las duddas
        */
        actual.setAcumuladoClientesPerdidos(anterior.getAcumuladoClientesPerdidos());
        actual.setAcumuladoClientesQueLleganYSeVan(anterior.getAcumuladoClientesQueLleganYSeVan());
        //Actualizo el acumulador de inscripciones
        actual.setAcumuladoAtendidos(anterior.getAcumuladoAtendidos() + 1);
        actual.setColaClientes(anterior.getColaClientes().clone());
        actual.setServidor(anterior.getServidor().clone());
        actual.setFinAtencion(anterior.getFinAtencion().clone()); //Este lo clono asi no mas porque la que tiene la hora es la maquina
        //actual.setFinMantenimiento(anterior.getFinMantenimiento().clone()); //Por las dudas lo clono
        //actual.setInicioMantenimiento(anterior.getInicioMantenimiento().clone());
        actual.setLlegadaCliente(anterior.getLlegadaCliente().clone());
        
        FinAtecion newFinAtencion = new FinAtecion();
        //Variables para fin inscripcion
        double rndAtencion = 0.0;
        double tAtencion = 0.0;
        double finAtencion = 0.0;
        
        
        /*
           1. Vefiicar si tengo cola de clientes y TENGO cola
           2. Hacer nueva lista/coleccion con clientes que hayan vuelto
           3. Verifico que la nueva lista != null y lo ES
           4. Obtengo cliente con hora_regreso menor y le cambio estado, reduzco la cola y calculo nuevo fin atencion
           5. La subLista ES NULL
           6. Obtengo cliente con MAYOR tiempo_espera y le cambio estado, reduzco la cola y calculo nuevo fin atencion
           7. NO tengo cola --> cambio el estado del serviro a LIBRE
        */
        // PASO 1
        if(actual.getColaClientes().getColaClientes() > 0){
            List<Cliente> clientesQueVolvieron = new ArrayList<Cliente>();
            actual.getColaClientes().setCantidad(actual.getColaClientes().getColaClientes() - 1);
            // PASO 2
            //CREO SUBLISTA DE CLIENTES QUE VOLVIERON
            for (Cliente cli : actual.getClientes()){
                    if(cli.getRegreso().equals(Cliente.Regreso.SI)){
                    clientesQueVolvieron.add(cli);
                }
            }
            // PASO 3 ?????
            if(clientesQueVolvieron.size() != 0){
                //PASO 4
                //Collections.sort(clientesQueVolvieron, (Cliente c1, Cliente c2) -> new Double(c1.getHora_regreso_sistema()).compareTo(new Double(c2.getHora_regreso_sistema())));
                Cliente cli = clientesQueVolvieron.get(0);
                cli.setEstado(Cliente.Estado.SIENDO_ATENDIDO);
                double espe = 0.0;
                //cli.setTiempo_esperando(espe);
                cli.setHora_regreso_sistema(espe);
                
                for (Cliente cliente: actual.getClientes()){
                    if(cliente.equals(cli)){
                        cli.setTiempo_esperando(espe);
                    }
                    else{
                        calculoTiempoEspera(cliente,actual, anterior);
                    }
                }
                
                rndAtencion = randomObject.nextDouble();
                    tAtencion = Distribuciones.calcular_uniforme(
                            Configuracion.getConfiguracion().getTiempoAtencionDesde(),
                            Configuracion.getConfiguracion().getTiempoAtencionHasta(),
                            rndAtencion);
                    finAtencion = tAtencion + actual.getReloj();
                    
                newFinAtencion.setRnd(rndAtencion);
                newFinAtencion.settAtencion(tAtencion);
                newFinAtencion.setFinAtencion(finAtencion);    
                actual.setFinAtencion(newFinAtencion);
            }
            else{
                //PASO 6
                List<Cliente> clientesEsperandoAtencion = new ArrayList<>();
                for (Cliente cli : actual.getClientes()){
                    if(cli.getEstado().equals(Cliente.Estado.ESPERANDO_ATENCION)){
                    clientesEsperandoAtencion.add(cli);
                    }
                }
                if (clientesEsperandoAtencion.size() != 0){
                    
                
               Collections.sort(clientesEsperandoAtencion, (Cliente c1, Cliente c2) -> new Double(c2.getTiempo_esperando()).compareTo(new Double(c1.getTiempo_esperando())));
               Cliente elegido = clientesEsperandoAtencion.get(0);
               elegido.setEstado(Cliente.Estado.SIENDO_ATENDIDO);
                
                //elegido.setTiempo_esperando(Double.MAX_VALUE);
                double espe = 0.0;
                for (Cliente cli: clientesEsperandoAtencion){
                    if(cli.equals(elegido)){
                        elegido.setTiempo_esperando(espe);
                    }
                    else{
                        calculoTiempoEspera(cli,actual, anterior);
                    }
                }
                rndAtencion = randomObject.nextDouble();
                    tAtencion = Distribuciones.calcular_uniforme(
                            Configuracion.getConfiguracion().getTiempoAtencionDesde(),
                            Configuracion.getConfiguracion().getTiempoAtencionHasta(),
                            rndAtencion);
                    finAtencion = tAtencion + actual.getReloj();
                    
                newFinAtencion.setRnd(rndAtencion);
                newFinAtencion.settAtencion(tAtencion);
                newFinAtencion.setFinAtencion(finAtencion);    
                actual.setFinAtencion(newFinAtencion);
                
                
                 
            }
                else {
                    actual.getServidor().setEstado(Servidor.Estado.LIBRE);
                    actual.getFinAtencion().setFinAtencion(Double.MAX_VALUE);
                }
        }    
        
        }
        else{
            //PASO 7
            actual.getServidor().setEstado(Servidor.Estado.LIBRE);
            actual.getFinAtencion().setFinAtencion(Double.MAX_VALUE);
            
        }
        
        
    }
    
    

    private Cliente obtenerClienteQueSeTerminoDeAtender(List<Cliente> actual) 
    {
        for (Cliente cli: actual){
            if(cli.getEstado().equals(Cliente.Estado.SIENDO_ATENDIDO)){
            return cli;
        }
        }        
        throw new NullPointerException ( "No habia cliente siendo atendido");
    }
    
    public void calculoTiempoEspera(Cliente cliente , VectorEstado actual, VectorEstado anterior){
        double tiempoEspera = 0.0;
         double espe = 0.0;
        //for(Cliente cli: actual.getClientes()){
            if((cliente.getEstado().equals(Cliente.Estado.ESPERANDO_ATENCION)) && (cliente.getRegreso().equals(Cliente.Regreso.NO))){
                tiempoEspera = cliente.getTiempo_esperando()+(actual.getReloj()- anterior.getReloj());
                cliente.setTiempo_esperando(tiempoEspera);
                
                if ((cliente.getTiempo_esperando() >= 20.0)){
                    double minutosQueRegresa = 60.0;
                    cliente.setHora_regreso_sistema(actual.getReloj()+ minutosQueRegresa);
                    cliente.setTiempo_esperando(espe);
                    cliente.setEstado(Cliente.Estado.ESPERANDO_PARA_REGRESAR);                  
                    actual.getColaClientes().setCantidad(actual.getColaClientes().getColaClientes() - 1);
                }                    
            }
            else if ((cliente.getEstado().equals(Cliente.Estado.ESPERANDO_ATENCION)) && (cliente.getRegreso().equals(Cliente.Regreso.SI))){
                tiempoEspera = cliente.getTiempo_esperando()+(actual.getReloj()- anterior.getReloj());
                cliente.setTiempo_esperando(tiempoEspera);
                if (tiempoEspera >= 20.0){
                    actual.setAcumuladoClientesQueLleganYSeVan(actual.getAcumuladoClientesQueLleganYSeVan() + 1);
                    //List<Cliente> nuevaLista= actual.getClientes().remove(cli);
                    //actual.setClientes(); //Funcionara???
                    //List<Cliente> clientesActuales = clonarClientes(anterior.getClientes());
                    Cliente clienteQueEsperoDemasiado = cliente; 
                    actual.getClientes().remove(clienteQueEsperoDemasiado); //Si dios quiere nadie mas lo referenciaba jaja
                    //clienteQueEsperoDemasiado = null;
                    //actual.setClientes(clientesActuales);
                }
        }
            else{
                cliente.setTiempo_esperando(cliente.getTiempo_esperando());                        
            }
        
    }
   
    

   
}


