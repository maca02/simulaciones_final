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
        
//        List<Cliente> clientesQueVuelven = new ArrayList<>();
//        for (Cliente cli : actual.getClientes()){
//            encontrarClienteQueVuelve(cli);
//        }
        List<Cliente> clienteQueVuelve = encontrarClienteQueVuelve(actual);
        //clienteQueVuelve.setRegreso(Cliente.Regreso.SI);
        //clienteQueVuelve.setTiempo_esperando(Double.MAX_VALUE); // OJO
        
        if (clienteQueVuelve.size() == 1){
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
                clienteQueVuelve.get(0).setEstado(Cliente.Estado.SIENDO_ATENDIDO);
                clienteQueVuelve.get(0).setHora_regreso_sistema(Double.MAX_VALUE);
                servidor.setEstado(Servidor.Estado.OCUPADO);     
                //calculoTiempoEspera(actual,anterior);

                    
            }
            else{
                clienteQueVuelve.get(0).setEstado(Cliente.Estado.ESPERANDO_ATENCION);
                actual.getColaClientes().agregarAlumnoCola();
                double espe = 0.0;
                clienteQueVuelve.get(0).setTiempo_esperando(espe);
                clienteQueVuelve.get(0).setHora_regreso_sistema(espe);
            }
        }
        else if (actual.getColaClientes().getColaClientes()>= 3 )
        {
            //Se va de nuevo el...
            //clienteQueVuelve.setEstado(Cliente.Estado.ESPERANDO_PARA_REGRESAR);
            //clienteQueVuelve.setHora_regreso_sistema(actual.getReloj() + 30);
            actual.setAcumuladoClientesPerdidos(actual.getAcumuladoClientesPerdidos() + 1);
            clienteQueVuelve.get(0).setEstado(Cliente.Estado.ESPERANDO_ATENCION);
            double espe = 0.0;
            for (Cliente cli: actual.getClientes()){
                if(cli.equals(clienteQueVuelve.get(0))){
                    clienteQueVuelve.get(0).setTiempo_esperando(espe);
                }
                else{
                    calculoTiempoEspera(cli,actual, anterior);
                }
            }
            
            actual.getClientes().remove(clienteQueVuelve.get(0));

        }
        else 
        {
            //A la cola
            clienteQueVuelve.get(0).setEstado(Cliente.Estado.ESPERANDO_ATENCION);
            //clienteQueVuelve.setHora_regreso_sistema(Double.MAX_VALUE);
            //calculoTiempoEspera(actual,anterior);
            actual.getColaClientes().agregarAlumnoCola();
                        double espe = 0.0;
            for (Cliente cli: actual.getClientes()){
                if(cli.equals(clienteQueVuelve.get(0))){
                    clienteQueVuelve.get(0).setTiempo_esperando(espe);
                    clienteQueVuelve.get(0).setHora_regreso_sistema(espe);

                }
                else{
                    calculoTiempoEspera(cli,actual, anterior);
                }
            }
        }  
        }
        else if (clienteQueVuelve.size() > 1){
            for (Cliente cli: clienteQueVuelve){
                if (cli.equals(clienteQueVuelve.get(0))){
                    
                    
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
                        cli.setEstado(Cliente.Estado.SIENDO_ATENDIDO);
                        cli.setHora_regreso_sistema(Double.MAX_VALUE);
                        servidor.setEstado(Servidor.Estado.OCUPADO);     
                        //calculoTiempoEspera(actual,anterior);


                    }
                    else{
                            cli.setEstado(Cliente.Estado.ESPERANDO_ATENCION);
                            actual.getColaClientes().agregarAlumnoCola();
                            double espe = 0.0;
                            cli.setTiempo_esperando(espe);
                            cli.setHora_regreso_sistema(espe);
                       }
                }
                else if (actual.getColaClientes().getColaClientes()>= 3 )
                {
                        //Se va de nuevo el...
                        //clienteQueVuelve.setEstado(Cliente.Estado.ESPERANDO_PARA_REGRESAR);
                        //clienteQueVuelve.setHora_regreso_sistema(actual.getReloj() + 30);
                        actual.setAcumuladoClientesPerdidos(actual.getAcumuladoClientesPerdidos() + 1);
                        cli.setEstado(Cliente.Estado.ESPERANDO_ATENCION);
                        double espe = 0.0;
                        for (Cliente cliente: actual.getClientes()){
                            if(cliente.equals(clienteQueVuelve.get(0))){
                                cliente.setTiempo_esperando(espe);
                            }
                            else{
                                calculoTiempoEspera(cliente,actual, anterior);
                            }
                        }

                        actual.getClientes().remove(cli);

                    }
        else 
        {
            //A la cola
            cli.setEstado(Cliente.Estado.ESPERANDO_ATENCION);
            //clienteQueVuelve.setHora_regreso_sistema(Double.MAX_VALUE);
            //calculoTiempoEspera(actual,anterior);
            actual.getColaClientes().agregarAlumnoCola();
                        double espe = 0.0;
            for (Cliente cliente: actual.getClientes()){
                if(cliente.equals(clienteQueVuelve.get(0))){
                    cliente.setTiempo_esperando(espe);
                    cliente.setHora_regreso_sistema(espe);

                }
                else{
                    calculoTiempoEspera(cliente,actual, anterior);
                }
            }
        } 
                }
                //para los demas clientes en clieteQueVuelve
                else{
                    if (actual.getColaClientes().getColaClientes() == 0){
                    Servidor servidor = actual.getServidor();
                    // ME PARECE QUE NUNCA VA A ESTR LIBRE EL SERVIDOR 
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
                        cli.setEstado(Cliente.Estado.SIENDO_ATENDIDO);
                        cli.setHora_regreso_sistema(Double.MAX_VALUE);
                        servidor.setEstado(Servidor.Estado.OCUPADO);     
                        //calculoTiempoEspera(actual,anterior);


                    }
                    else{
                            cli.setEstado(Cliente.Estado.ESPERANDO_ATENCION);
                            actual.getColaClientes().agregarAlumnoCola();
                            double espe = 0.0;
                            cli.setTiempo_esperando(espe);
                            cli.setHora_regreso_sistema(espe);
                       }
                }
                else if (actual.getColaClientes().getColaClientes()>= 3 )
                {
                        //Se va de nuevo el...
                        //clienteQueVuelve.setEstado(Cliente.Estado.ESPERANDO_PARA_REGRESAR);
                        //clienteQueVuelve.setHora_regreso_sistema(actual.getReloj() + 30);
                        actual.setAcumuladoClientesPerdidos(actual.getAcumuladoClientesPerdidos() + 1);
                        cli.setEstado(Cliente.Estado.ESPERANDO_ATENCION);
                        double espe = 0.0;
                        for (Cliente cliente: actual.getClientes()){
                            if(cliente.equals(cli)){
                                cliente.setTiempo_esperando(espe);
                                cli.setHora_regreso_sistema(espe);
                            }
//                            else{
//                                calculoTiempoEspera(cliente,actual, anterior);
//                            }
                        }

                        actual.getClientes().remove(cli);

                    }
        else 
        {
            //A la cola
            cli.setEstado(Cliente.Estado.ESPERANDO_ATENCION);
            //clienteQueVuelve.setHora_regreso_sistema(Double.MAX_VALUE);
            //calculoTiempoEspera(actual,anterior);
            actual.getColaClientes().agregarAlumnoCola();
                        double espe = 0.0;
            for (Cliente cliente: actual.getClientes()){
                if(cli.equals(cli)){
                    cli.setTiempo_esperando(espe);
                    cli.setHora_regreso_sistema(espe);

                }
//                else{
//                    calculoTiempoEspera(cliente,actual, anterior);
//                }
            }
        } 
                }
            }
        }
        
                 
            
        
        /**
         * Borrar atributo de regreso del alumno.
         * Hacer lo mismo q en LlegadaAlumno pero sin incrementar ACs
         */
    }


    private List<Cliente> encontrarClienteQueVuelve(VectorEstado actual) {
        List<Cliente> clientesQueVuelven = new ArrayList<>();
        for (Cliente cliente : actual.getClientes())
        {
            if ((cliente.getEstado().equals(Cliente.Estado.ESPERANDO_PARA_REGRESAR))
                    && (actual.getReloj() == cliente.getHora_regreso_sistema()))
            {
                cliente.setRegreso(Cliente.Regreso.SI);
                cliente.setTiempo_esperando(Double.MAX_VALUE);
                clientesQueVuelven.add(cliente);
                
            }
            
            
        }
        return clientesQueVuelven;
        //throw new NullPointerException("No se encontro el alumno que regresaba a los : " + actual.getReloj());
    }
    
    public void calculoTiempoEspera(Cliente cli , VectorEstado actual, VectorEstado anterior){
        double tiempoEspera = 0.0;
         double espe = 0.0;
        //for(Cliente cli: actual.getClientes()){
            if((cli.getEstado().equals(Cliente.Estado.ESPERANDO_ATENCION)) && (cli.getRegreso().equals(Cliente.Regreso.NO))){
                tiempoEspera = cli.getTiempo_esperando()+(actual.getReloj()- anterior.getReloj());
                cli.setTiempo_esperando(tiempoEspera);
                
                if ((cli.getTiempo_esperando() >= 6.0)){
                    double minutosQueRegresa = 5.0;
                    cli.setHora_regreso_sistema(actual.getReloj()+ minutosQueRegresa);
                    cli.setTiempo_esperando(espe);
                    cli.setEstado(Cliente.Estado.ESPERANDO_PARA_REGRESAR);       
                    actual.getColaClientes().setCantidad(actual.getColaClientes().getColaClientes() - 1);

                    
                }                    
            }
            else if ((cli.getEstado().equals(Cliente.Estado.ESPERANDO_ATENCION)) && (cli.getRegreso().equals(Cliente.Regreso.SI))){
                tiempoEspera = cli.getTiempo_esperando()+(actual.getReloj()- anterior.getReloj());
                cli.setTiempo_esperando(tiempoEspera);
                if (tiempoEspera >= 6.0){
                    actual.setAcumuladoClientesQueLleganYSeVan(actual.getAcumuladoClientesQueLleganYSeVan() + 1);
                    //List<Cliente> nuevaLista= actual.getClientes().remove(cli);
                    //actual.setClientes(); //Funcionara???
                    //List<Cliente> clientesActuales = clonarClientes(anterior.getClientes());
                    Cliente clienteQueEsperoDemasiado = cli; 
                    actual.getClientes().remove(clienteQueEsperoDemasiado); //Si dios quiere nadie mas lo referenciaba jaja
                    //clienteQueEsperoDemasiado = null;
                    //actual.setClientes(clientesActuales);
                }
        }
            else{
                cli.setTiempo_esperando(cli.getTiempo_esperando());                        
            }
        
    }
}
