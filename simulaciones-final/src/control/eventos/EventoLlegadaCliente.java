package control.eventos;

import control.ControladorSimulacion;
import control.VectorEstado;
import eventos.FinAtecion;
import eventos.LlegadaCliente;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import model.Configuracion;
import objects.Cliente;
import objects.Distribuciones;
import objects.Servidor;

public class EventoLlegadaCliente extends Evento {

    public EventoLlegadaCliente(String nombre) {
        super(nombre);
    }

    @Override
    public void actualizarEstadoVector() {
        
        /**
         * si hay tengo q calcular fin inscripcion, si no hay se tiene que ir 
         * a la cola (setear estados)
         * Si en la cola hay mas de 4, se va y vuelve 30 mins mas tarde
         * En ese caso sumar 1 al AC de alumnos que llegan y se van.
         * Sumar 1 al AC de alumnos que llegan sin importar que hizo.
         */
        
        VectorEstado actual = ControladorSimulacion.getVectorActual();
        VectorEstado anterior = ControladorSimulacion.getVectorAnterior();
        
        actual.setAcumuladoAtendidos(anterior.getAcumuladoAtendidos());
        actual.setAcumuladoClientesPerdidos(anterior.getAcumuladoClientesPerdidos());
        actual.setAcumuladoClientesQueLleganYSeVan(anterior.getAcumuladoClientesQueLleganYSeVan());
        actual.setClientes(clonarClientes(anterior.getClientes()));
        actual.setColaClientes(anterior.getColaClientes().clone());
        actual.setServidor(anterior.getServidor().clone());
        actual.setFinAtencion(anterior.getFinAtencion().clone());
        actual.setLlegadaCliente(anterior.getLlegadaCliente().clone());
        
        Random randomObject = new Random();
        LlegadaCliente newLlegada = new LlegadaCliente();
        FinAtecion newFinAtencion = new FinAtecion();
        
        Cliente newCliente = new Cliente();
        actual.getClientes().add(newCliente);
        newCliente.setRegreso(Cliente.Regreso.NO);
        //Variables para fin inscripcion
        double rndAtencion = 0.0;
        double tAtencion = 0.0;
        double finAtencion = 0.0;
        
        /**
         * Si maquinasLibres es true, significa que encontro al menos una y esta siendo inscripto,
         * sino se queda en false y tiene que ir a la cola
         */
        //boolean maquinasLibres = false;
        
        // Calcular proxima llegada alumno
        double rndProxLlegada = randomObject.nextDouble();
        double tEntreLlegada = Distribuciones.calcular_exponencial(
                Configuracion.getConfiguracionPorDefecto().getMediaLlegadaClientes(),
                rndProxLlegada);
        double proxLlegada = tEntreLlegada + actual.getReloj();
        
        
        // Se fija primero si hay alumnos en la cola y luego en base a eso calcula los tiempos de las inscripciones o acumula en la cola
        if(anterior.getColaClientes().getColaClientes()== 0) 
        {
            Servidor servidor = actual.getServidor();
            
                if(servidor.getEstado().equals(Servidor.Estado.LIBRE))
                {
                    // Calcula el fin inscripcion y actualiza el estado del alumno y de la maquina correspondiente
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
                    servidor.setEstado(Servidor.Estado.OCUPADO);
                    //m.setFinInscripcion(actual.getReloj() + tAtencion);
                    //newCliente.setRegreso(Cliente.Regreso.NO);
                    newCliente.setEstado(Cliente.Estado.SIENDO_ATENDIDO);
                    
                    //newCliente.setMaquinaInscripcion(m.getId());
                    
                    //maquinasLibres = true;                   
                }             
                else 
                {
                    newCliente.setEstado(Cliente.Estado.ESPERANDO_ATENCION);
                    actual.getColaClientes().agregarAlumnoCola();
                    double espe = 0.0;
                    newCliente.setTiempo_esperando(espe);
                    //calculoTiempoEspera(actual,anterior);
                }             
        } 
        else if (actual.getColaClientes().getColaClientes()>= 3)                
        {
            //CLIENTE SE DESTRUYE
            // Logica para cuando la cola es mayor a 4
            
            actual.setAcumuladoClientesPerdidos(actual.getAcumuladoClientesPerdidos()+ 1);
            newCliente.setEstado(Cliente.Estado.ESPERANDO_ATENCION);
            //actual.getColaClientes().agregarAlumnoCola();
            //newCliente.setTiempo_esperando(Double.MAX_VALUE);
            //calculoTiempoEspera(actual,anterior);
            double espe = 0.0;
            for (Cliente cli: actual.getClientes()){
                if(cli.equals(newCliente)){
                    newCliente.setTiempo_esperando(espe);
                }
                else{
                    calculoTiempoEspera(cli,actual, anterior);
                }
            }
            
            actual.getClientes().remove(newCliente);
            
//            for (Cliente cli: actual.getClientes()){
//                if(cli.equals(newCliente)){
//                    actual.getClientes().remove(cli);
//                    actual.getColaClientes().setCantidad(actual.getColaClientes().getColaClientes() - 1);
//                }
//            }
        }
        else
        {
            newCliente.setEstado(Cliente.Estado.ESPERANDO_ATENCION);
            actual.getColaClientes().agregarAlumnoCola();
            //newCliente.setTiempo_esperando(Double.MAX_VALUE);
            //calculoTiempoEspera(actual,anterior);
            double espe = 0.0;
            for (Cliente cli: actual.getClientes()){
                if(cli.equals(newCliente)){
                    newCliente.setTiempo_esperando(espe);
                }
                else{
                    calculoTiempoEspera(cli,actual, anterior);
                }
            }
        }
        
        //Seteo de la proxima llegada
        newLlegada.setRnd(rndProxLlegada);
        newLlegada.setTiempo_entre_llegadas(tEntreLlegada);
        newLlegada.setProx_llegada(proxLlegada);
        actual.setLlegadaCliente(newLlegada);
    }
    
    public void calculoTiempoEspera(Cliente cli , VectorEstado actual, VectorEstado anterior){
        double tiempoEspera = 0.0;
        double espe = 0.0;
        //for(Cliente cli: actual.getClientes()){
            if((cli.getEstado().equals(Cliente.Estado.ESPERANDO_ATENCION)) && (cli.getRegreso().equals(Cliente.Regreso.NO))){
                tiempoEspera = cli.getTiempo_esperando()+(actual.getReloj()- anterior.getReloj());
                cli.setTiempo_esperando(tiempoEspera);
                
                if (cli.getTiempo_esperando() >= 6.0){
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
                
                if (cli.getTiempo_esperando() >= 6.0){
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
