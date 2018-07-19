/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.eventos;

import java.util.ArrayList;
import java.util.List;
import objects.Cliente;

public abstract class Evento 
{
    public static final Evento Inicial = new EventoInicial("Inicial");
    public static final Evento FinAtencion = new EventoFinAtencion("Fin atencion");
    public static final Evento LlegadaCliente = new EventoLlegadaCliente("Llegada Cliente");
    public static final Evento RegresoCliente = new EventoRegresoCliente("Regreso Cliente");
    
    private double horaEvento;
    private String nombre;
    
    public Evento(String nombre)
    {
        this.nombre = nombre;
    }
    
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
    
    public String getNombre()
    {
        return nombre;
    }
    public void setHoraEvento(double hora)
    {
        horaEvento = hora;
    }
    
    public double getHoraEvento()
    {
        return horaEvento;
    }
    
    public static List<Cliente> clonarClientes(List<Cliente> listaAClonar)
    {
        List<Cliente> listaClonada = new ArrayList<>(listaAClonar.size());
        for (Cliente alumno : listaAClonar)
        {
            listaClonada.add(alumno.clone());
        }
        return listaClonada;
    }
    
    /**
     *dfgfd
     * @param listaAClonar
     * @return
     */
   
        
    public abstract void actualizarEstadoVector();
}
