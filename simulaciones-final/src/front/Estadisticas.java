package front;


public class Estadisticas {
    private int clientes_atendidos;
    private int clientes_que_volvieron;
    private int clientes_perdidos;
    

    public Estadisticas(int clientes_atendidos, int clientes_que_volvieron, int clientes_perdidos) {
        this.clientes_atendidos = clientes_atendidos;
        this.clientes_que_volvieron = clientes_que_volvieron;
        this.clientes_perdidos = clientes_perdidos;
    }

    public Estadisticas() {
    }

    public int getClientes_atendidos() {
        return clientes_atendidos;
    }

    public void setClientes_atendidos(int clientes_atendidos) {
        this.clientes_atendidos = clientes_atendidos;
    }

    public int getClientes_que_volvieron() {
        return clientes_que_volvieron;
    }

    public void setClientes_que_volvieron(int clientes_que_volvieron) {
        this.clientes_que_volvieron = clientes_que_volvieron;
    }

    public int getClientes_perdidos() {
        return clientes_perdidos;
    }

    public void setClientes_perdidos(int clientes_perdidos) {
        this.clientes_perdidos = clientes_perdidos;
    }
      
    
    
}
