/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import control.eventos.Evento;
import front.Estadisticas;
import front.VentanaPrincipal;
//import front.tablemodel.SetearValores;
import java.util.*;
import model.Configuracion;
import model.VectorEstadoUI;
import objects.*;

/**
 *
 * @author heftyn
 */
public class ControladorSimulacion 
{
    VentanaPrincipal vistaAplicacion;
    Estadisticas estadisticas;
    
    private static VectorEstado actual;
    private static VectorEstado anterior;
    
    private List<VectorEstadoUI> modelo;
    
    ControladorSimulacion()
    {
        vistaAplicacion = new VentanaPrincipal(this);
        estadisticas = new Estadisticas();
        
    }
    public void mostrarVentanaPrincipal()
    {
        vistaAplicacion.setVisible(true);        
    }
    
    public Estadisticas mostrarEstadisticas() {
        return this.estadisticas;
    }

    public void simular() {
        /**
         * Ademas de simular aca vamos a actualizar la vista...
         * onda la SetarValores tiene que proveer un metodo para 
         * obtener el TableModel (VectorEstadoTableModel) asi 
         * nosotros le pasamos todos los datos y le informamos que cambio la data
         */
        int iteracionActual = 0;
        int iteracionesMostrando = 0;
        inicializar();
        double minutosASimular = new Double(Configuracion.getConfiguracion().getMinutosASimular());
        do
        {
            //Mover vector "actual" a "anterior"

            rotacionVector();
            
            Evento nuevoEvento = determinarProximoEvento();
            
            
            //Actualizar reloj a la hora de este evento.
            actual.setReloj(nuevoEvento.getHoraEvento());
           
            //Setear este evento dentro del vector actual y llamar al metodo polimorfico:
             
            actual.setEvento(nuevoEvento);
//           for (Cliente cli:actual.getClientes()){
//                if ((cli.getEstado().equals(Cliente.Estado.ESPERANDO_ATENCION)) && (cli.getRegreso().equals(Cliente.Regreso.SI))){
//                    if (nuevoEvento.getHoraEvento() - actual.getReloj() > 6){
//                        actual.getClientes().remove(cli);
//                        actual.getColaClientes().setCantidad(actual.getColaClientes().getColaClientes() - 1);
//                        actual.getColaClientes().setCantidad(actual.getColaClientes().getColaClientes() - 1);
//                    }
//                }
//            }
            actual.getEvento().actualizarEstadoVector();
            
            
            //Ademas de la validacion general, tmb se agrega cuando es la ultima fila
            if (seMuestra(iteracionesMostrando) || 
                    !(iteracionActual + 1 < 1000000 && actual.getReloj() < minutosASimular))
            {
                //Guardar en la lista a devolver
                guardarVectorParaVista();
                iteracionesMostrando++;
            }
            iteracionActual++;
            
        }while (iteracionActual < 1000000 && actual.getReloj() < minutosASimular);
        //Actualizar Vista
        vistaAplicacion.setearModelo(modelo);
        //Calculo de los estadisticos
        calculoEstadisticos();
        //Limpiamos el modelo
        modelo = new ArrayList<>();
    }
    
    public static VectorEstado getVectorActual()
    {
        return actual;
    }
    
    public static VectorEstado getVectorAnterior()
    {
        return anterior;
    }
    
    public Estadisticas getEstadisticas() {
        return estadisticas;
    }
    
    private void calculoEstadisticos() {
        estadisticas.setClientes_atendidos(actual.getAcumuladoAtendidos());
        estadisticas.setClientes_perdidos(actual.getAcumuladoClientesPerdidos());
        estadisticas.setClientes_que_volvieron(actual.getAcumuladoClientesQueLleganYSeVan());
    }
    
    private void inicializar() 
    {
        actual = new VectorEstado();
        actual.setReloj(0);
        actual.setEvento(Evento.Inicial);
        actual.getEvento().actualizarEstadoVector();
        
        if (seMuestra(0))
        {
            //Guardar en la lista a devolver
            guardarVectorParaVista();
        }
        actual = actual;
    }

    private Evento determinarProximoEvento() {
        //Aca lo que hacemos es obtener todas las horas que hay en el sistema que pueden generar el proximo evento
        //Elegir la menor, crear un objeto evento de ese, setearle la hora y devolverlo.
        HashMap<Double,Evento> mapaDeTiempos = new HashMap<>();
        
        if (anterior.getLlegadaCliente() != null && 
                anterior.getLlegadaCliente().getProx_llegada() > 0 && 
                anterior.getLlegadaCliente().getProx_llegada() < Double.MAX_VALUE)
        {
            mapaDeTiempos.put(anterior.getLlegadaCliente().getProx_llegada(), Evento.LlegadaCliente);
        }
        if (anterior.getFinAtencion() != null &&
                anterior.getFinAtencion().getFinAtencion()> 0 &&
                anterior.getFinAtencion().getFinAtencion()< Double.MAX_VALUE)
        {
            mapaDeTiempos.put(anterior.getFinAtencion().getFinAtencion(), Evento.FinAtencion);
        }
        if (anterior.getClientes() != null && anterior.getClientes().size() > 0)
        {
            for (Cliente cliente : anterior.getClientes())
            {
                if (cliente.getHora_regreso_sistema() > 0 &&
                        cliente.getHora_regreso_sistema() < Double.MAX_VALUE)
                {
                    mapaDeTiempos.put(cliente.getHora_regreso_sistema(), Evento.RegresoCliente);
                }
            }
        }
        
        Set<Double> tiempos = mapaDeTiempos.keySet();
        Double tiempoSiguiente = Collections.min(tiempos);
        
        Evento returnValue = mapaDeTiempos.get(tiempoSiguiente);
        returnValue.setHoraEvento(tiempoSiguiente);
        
        return returnValue;
    }

    private boolean seMuestra(int iteracionActual) {
        //actual.getReloj() > Config &&
        // iteraciones < Config
        boolean seMuestra = false;
        if (actual != null)
        {
            seMuestra = actual.getReloj() >= Configuracion.getConfiguracion().getMinutoDesde();
            
        }
        if (seMuestra)
        {
            seMuestra = seMuestra && iteracionActual <= Configuracion.getConfiguracion().getIteracionesAMostrar();
        }
        return seMuestra;
    }

    private void rotacionVector() {
        anterior = actual;
        actual = new VectorEstado();
    }

    private void guardarVectorParaVista() {
        if (modelo == null)
        {
            modelo = new ArrayList<>();
        }
        
        modelo.add(actual);
        
    }
}
