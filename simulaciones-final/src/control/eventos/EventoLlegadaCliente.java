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
         * si hay tengo q calcular fin inscripcion, si no hay se tiene que ir a
         * la cola (setear estados) Si en la cola hay mas de 4, se va y vuelve
         * 30 mins mas tarde En ese caso sumar 1 al AC de alumnos que llegan y
         * se van. Sumar 1 al AC de alumnos que llegan sin importar que hizo.
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

        //Variables para fin inscripcion
        double rndAtencion = 0.0;
        double tAtencion = 0.0;
        double finAtencion = 0.0;

        // Calcular proxima llegada alumno
        double rndProxLlegada = randomObject.nextDouble();
        double tEntreLlegada = Distribuciones.calcular_exponencial(
                Configuracion.getConfiguracion().getMediaLlegadaClientes(),
                rndProxLlegada);
        double proxLlegada = tEntreLlegada + actual.getReloj();
        
        Cliente newCliente = new Cliente();
        List<Cliente> ClientesABorrar = new ArrayList<Cliente>();
//        
        actual.getClientes().add(newCliente); 
        newCliente.setRegreso(Cliente.Regreso.NO);
        

        // Se fija primero si hay alumnos en la cola y luego en base a eso calcula los tiempos de las inscripciones o acumula en la cola
        if (anterior.getColaClientes().getColaClientes() == 0) {
            Servidor servidor = actual.getServidor();

            if (servidor.getEstado().equals(Servidor.Estado.LIBRE)) {
                // Calcula el fin inscripcion y actualiza el estado del alumno y de la maquina correspondiente
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
                servidor.setEstado(Servidor.Estado.OCUPADO);

                newCliente.setEstado(Cliente.Estado.SIENDO_ATENDIDO);

            } else {
                newCliente.setEstado(Cliente.Estado.ESPERANDO_ATENCION);
                actual.getColaClientes().agregarAlumnoCola();
                double espe = 0.0;
                newCliente.setTiempo_esperando(espe);
            }
        } else if (actual.getColaClientes().getColaClientes() >= 10) {
            //CLIENTE SE DESTRUYE
            // Logica para cuando la cola es mayor a 4
            newCliente.setEstado(Cliente.Estado.ESPERANDO_ATENCION);

            double espe = 0.0;
            for (Cliente cli : actual.getClientes()) {
                if (cli.equals(newCliente)) {
                    newCliente.setTiempo_esperando(espe);
                } else {
                    Cliente clienteQueSeFue = calculoTiempoEspera(cli, actual, anterior);
                    ClientesABorrar.add(clienteQueSeFue);
                }
            }
            // Borra los ctes que ya han vuelto y su tiempo de espera supera los 20 min
            actual.getClientes().removeAll(ClientesABorrar);
            //Si luego del borrado lo cola es = a 10 entonces se borra al cte nuevo
            // sino lo agrega a la cola
            if (actual.getColaClientes().getColaClientes() == 10){
            actual.setAcumuladoClientesPerdidos(actual.getAcumuladoClientesPerdidos() + 1);
            actual.getClientes().remove(newCliente);
            }
            else{
            actual.getColaClientes().agregarAlumnoCola();

            }
            

        } else {
            newCliente.setEstado(Cliente.Estado.ESPERANDO_ATENCION);
            actual.getColaClientes().agregarAlumnoCola();
            double espe = 0.0;
            for (Cliente cli : actual.getClientes()) {
                if (cli.equals(newCliente)) {
                    newCliente.setTiempo_esperando(espe);
                } else {
                    Cliente clienteQueSeFue = calculoTiempoEspera(cli, actual, anterior);
                    ClientesABorrar.add(clienteQueSeFue);
                    }
                }
                actual.getClientes().removeAll(ClientesABorrar);
        }

        //Seteo de la proxima llegada
        newLlegada.setRnd(rndProxLlegada);
        newLlegada.setTiempo_entre_llegadas(tEntreLlegada);
        newLlegada.setProx_llegada(proxLlegada);
        actual.setLlegadaCliente(newLlegada);
    }

    public Cliente calculoTiempoEspera(Cliente cli, VectorEstado actual, VectorEstado anterior) {
        double tiempoEspera = 0.0;
        double espe = 0.0;
        Cliente clienteQueEsperoDemasiado = null;
        if ((cli.getEstado().equals(Cliente.Estado.ESPERANDO_ATENCION)) && (cli.getRegreso().equals(Cliente.Regreso.NO))) {
            tiempoEspera = cli.getTiempo_esperando() + (actual.getReloj() - anterior.getReloj());
            cli.setTiempo_esperando(tiempoEspera);

            if (cli.getTiempo_esperando() >= 20.0) {
                double minutosQueRegresa = 60.0;
                cli.setHora_regreso_sistema(actual.getReloj() + minutosQueRegresa);
                cli.setTiempo_esperando(espe);
                cli.setEstado(Cliente.Estado.ESPERANDO_PARA_REGRESAR);
                actual.getColaClientes().setCantidad(actual.getColaClientes().getColaClientes() - 1);

            }
            
        } else if ((cli.getEstado().equals(Cliente.Estado.ESPERANDO_ATENCION)) && (cli.getRegreso().equals(Cliente.Regreso.SI))) {
            tiempoEspera = cli.getTiempo_esperando() + (actual.getReloj() - anterior.getReloj());
            cli.setTiempo_esperando(tiempoEspera);

            if (cli.getTiempo_esperando() >= 20.0) {
                actual.setAcumuladoClientesQueLleganYSeVan(actual.getAcumuladoClientesQueLleganYSeVan() + 1);
                actual.getColaClientes().setCantidad(actual.getColaClientes().getColaClientes() - 1);

                clienteQueEsperoDemasiado = cli;
                //actual.getClientes().remove(clienteQueEsperoDemasiado); //Si dios quiere nadie mas lo referenciaba jaja
                //return clienteQueEsperoDemasiado;
            }
        } else {
            cli.setTiempo_esperando(cli.getTiempo_esperando());
            
        }
        
        return clienteQueEsperoDemasiado;
    }
}
