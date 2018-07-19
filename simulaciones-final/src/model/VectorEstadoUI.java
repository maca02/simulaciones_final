/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import control.eventos.Evento;
import java.util.List;
import objects.Cliente;
import objects.Servidor;
import eventos.*;
import objects.ColaClientes;




public interface VectorEstadoUI {
    
    double getReloj();
    
    Evento getEvento();
    
    LlegadaCliente getLlegadaCliente();    
    
    FinAtecion getFinAtencion();
    
    Servidor getServidor();
    
    ColaClientes getColaClientes();     
    
    int getAcumuladoAtendidos();
    
    int getAcumuladoClientesPerdidos();
    
    int getAcumuladoClientesQueLleganYSeVan();    
    
    List<Cliente> getClientes();
}
