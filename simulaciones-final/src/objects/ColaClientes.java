package objects;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ColaClientes implements Cloneable {

    private double cantidad;

    public ColaClientes(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getColaClientes() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public void agregarAlumnoCola() {
        cantidad++;
    }

    @Override
    public ColaClientes clone() {
        try {
            ColaClientes clonada = (ColaClientes) super.clone();
            clonada.cantidad = this.cantidad;
            return clonada;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(ColaClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ColaClientes(this.cantidad);
    }
}
