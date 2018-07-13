package objects;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor implements Cloneable{
  
    private Estado estado;

    public boolean estaEsperandoMaquinaLibre() {
        return this.estado.equals(Estado.LIBRE);
    }
    
    public enum Estado{
        LIBRE,
        OCUPADO,        
    };

    public Servidor(Estado estado) {
        this.estado = estado;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }    
    
    @Override
    public Servidor clone() 
    {
        try {
            Servidor clon = (Servidor) super.clone();
            clon.estado = this.estado;
            return clon;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Servidor(this.estado);
    }
}
