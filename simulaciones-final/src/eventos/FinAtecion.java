package eventos;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FinAtecion implements Cloneable {

    private double rnd;
    private double tAtencion;
    private double finAtencion;

    public FinAtecion() {
        rnd = tAtencion = finAtencion = Double.MAX_VALUE;
    }

    public FinAtecion(double rnd, double tInscripcion, double finAtencion) {
        this.rnd = rnd;
        this.tAtencion = tInscripcion;
        this.finAtencion = finAtencion;
    }

    public double getRnd() {
        return rnd;
    }

    public void setRnd(double rnd) {
        this.rnd = rnd;
    }

    public double gettAtencion() {
        return tAtencion;
    }

    public void settAtencion(double tAtencion) {
        this.tAtencion = tAtencion;
    }

    public double getFinAtencion() {
        return finAtencion;
    }

    public void setFinAtencion(double finAtencion) {
        this.finAtencion = finAtencion;
    }

    @Override
    public FinAtecion clone() {
        try {
            FinAtecion clon = (FinAtecion) super.clone();
            clon.rnd = Double.MAX_VALUE;
            clon.tAtencion = Double.MAX_VALUE;
            return clon;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(FinAtecion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new FinAtecion();
    }
}
