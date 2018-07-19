/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import model.VectorEstadoConstants;
import model.VectorEstadoUI;


public final class VectorEstadoTableModel extends DefaultTableModel implements VectorEstadoConstants {

    private List<VectorEstadoUI> datos;
    private List<Columna> columns;

    public VectorEstadoTableModel() 
    {
        datos = new ArrayList<>();
        createColumnList();
    }
    
    
    @Override
    public int getRowCount() {
        return datos != null ? datos.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns.get(columnIndex).getName();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columns.get(columnIndex).getTipo();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int row, int col) 
    {
        Object value = null;
        switch (col)
        {
            //Por cada columna de la tabla
            case COL_RELOJ:
            {
                //Aca podemos formatear la salida
                value = datos.get(row).getReloj();
                break;//No olvidar
            }
            case COL_EVENTO:
            {
                value = datos.get(row).getEvento().getNombre();
                break;
            }
            case COL_LLEGADA_CLIENTE_RND:
            {
                value = datos.get(row).getLlegadaCliente().getRnd() ;
                break;
            }
            case COL_LLEGADA_CLIENTE_TIEMPO:
            {
                value = datos.get(row).getLlegadaCliente().getTiempo_entre_llegadas() ;
                break;
            }
            case COL_LLEGADA_CLIENTE_PROXIMA:
            {
                value = datos.get(row).getLlegadaCliente().getProx_llegada() ;
                break;
            }
            case COL_FIN_ATENCION_RND:
            {
                value = datos.get(row).getFinAtencion().getRnd() ;
                break;
            }
            case COL_FIN_ATENCION_TIEMPO:
            {
                value = datos.get(row).getFinAtencion().gettAtencion() ;
                break;
            }
            case COL_FIN_ATENCION_PROXIMA:
            {
                value = datos.get(row).getFinAtencion().getFinAtencion();
                break;
            }
            case COL_SERVIDOR_ESTADO:
            {
                value = datos.get(row).getServidor().getEstado().name() ;
                break;
            }
           
            case COL_SERVIDOR_COLA:
            {
                value = datos.get(row).getColaClientes().getColaClientes();
                break;
            }                               
            case COL_AC_CLIENTES_ATENDIDOS:
            {
                value = datos.get(row).getAcumuladoAtendidos();
                break;
            }
            case COL_AC_CLIENTES_QUE_LLEGAN:
            {
                value = datos.get(row).getAcumuladoClientesPerdidos() ;
                break;
            }
            case COL_AC_CLIENTES_QUE_SE_VAN:
            {
                value = datos.get(row).getAcumuladoClientesQueLleganYSeVan() ;
                break;
            }
            
        }
        //TODO: Antes de devolver estos hay que parsear para que se vea bien en la tabla
        // Por ejemplo: 1.2232345435 -> 1.22323 o algo asi (de ser posible que sea en el 
        // mismo tipo de datos, sino hay q cambiar el createColumnList)
        if (value instanceof Double)
        {
            Double val = (Double) value;
            if (val == Double.MAX_VALUE)
            {
                value = 0;
            }
            //value = value; //Acá. Ojo con reloj y eso.
        }
        return value;
    }

    private void createColumnList() 
    {
        columns = new ArrayList<>();
        
        //Agregar cada objeto de columna a la lista

        //https://coderanch.com/t/333340/java/set-Line-wrap-Column-JTable 
        columns.add(new Columna("Reloj", Double.class, 75));
        columns.add(new Columna("Evento", String.class, 150));
        columns.add(new Columna("RND: LLegada Cliente", Double.class, 150));
        columns.add(new Columna("Tiempo: Llegada Cliente", Double.class, 150));
        columns.add(new Columna("Proxima Llegada Cliente", Double.class, 160));
        columns.add(new Columna("RND: Fin Atencion: RND", Double.class, 150));
        columns.add(new Columna("Tiempo Fin Atencion", Double.class, 150));
        columns.add(new Columna("Proximo Fin de Atencion", Double.class, 160));
        columns.add(new Columna("Estado Encargado", String.class, 300));
        columns.add(new Columna("Cola", Integer.class, 50));
        columns.add(new Columna("Acum. Fin Atencion", String.class, 125));
        columns.add(new Columna("Acum. Clientes que se pierden", String.class, 200));
        columns.add(new Columna("Acum. Clientes que llegan y se van", String.class, 200));
        

        
    }
    


    public void setDatos(List<VectorEstadoUI> modelo) {
        datos = modelo;
    }

    public VectorEstadoUI getDato(int selectedRow) {
        return datos.get(selectedRow);
    }

    public void setColumnsWidth(TableColumnModel columnModel) {
        for (int i = 0; i < columnModel.getColumnCount(); i++)
        {
            columnModel.getColumn(i).setPreferredWidth(columns.get(i).getSize());
        }
    }
    
}
