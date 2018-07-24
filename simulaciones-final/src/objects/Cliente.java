package objects;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente implements Cloneable {

    private double hora_regreso_sistema;
    private double tiempo_esperando;

    public enum Regreso {
        SI,
        NO
    };

    private Regreso regreso;

    public enum Estado {
        ESPERANDO_ATENCION,
        SIENDO_ATENDIDO,
        ESPERANDO_PARA_REGRESAR
    };

    private Estado estado;

    public Cliente() {

    }

    public Cliente(double hora_regreso_sistema, double tiempo_esperando, Estado estado, Regreso regreso) {
        this.hora_regreso_sistema = hora_regreso_sistema;
        this.estado = estado;
        this.tiempo_esperando = tiempo_esperando;
        this.regreso = regreso;
    }

    public double getHora_regreso_sistema() {
        return hora_regreso_sistema;
    }

    public void setHora_regreso_sistema(double hora_regreso_sistema) {
        this.hora_regreso_sistema = hora_regreso_sistema;
    }

    public double getTiempo_esperando() {
        return tiempo_esperando;
    }

    public void setTiempo_esperando(double tiempo_esperando) {
        this.tiempo_esperando = tiempo_esperando;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Regreso getRegreso() {
        return regreso;
    }

    public void setRegreso(Regreso regreso) {
        this.regreso = regreso;
    }

    @Override
    public Cliente clone() {
        try {
            Cliente clonado = (Cliente) super.clone();
            clonado.estado = this.estado;
            clonado.regreso = this.regreso;
            clonado.hora_regreso_sistema = this.hora_regreso_sistema;
            clonado.tiempo_esperando = this.tiempo_esperando;

            return clonado;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Cliente();
    }
}
