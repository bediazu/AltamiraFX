package modelo;

import java.sql.Time;
import java.time.LocalDate;


public class Reserva {
    
    private int id;
    
    private Cliente cliente_asociado; 
    private int comensales;
    private String numero_mesa;
    private String comentario;

    private LocalDate fecha;
    private Time hora;
    
    private boolean pago_efectivo;
    

    public Reserva(Cliente cliente_asociado, int comensales, String numero_mesa, String comentario, LocalDate fecha, Time hora, boolean pago_efectivo) {
        this.cliente_asociado = cliente_asociado;
        this.comensales = comensales;
        this.numero_mesa = numero_mesa;
        this.comentario = comentario;
        this.fecha = fecha;
        this.hora = hora;
        this.pago_efectivo = pago_efectivo;
    }
    
    /*
    @Override
    public boolean equals(Object obj) {
        Reserva reserva_aux = (Reserva)obj;
        return id==reserva_aux.getId();
    }
	
	*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Reserva(int id, Cliente cliente_asociado, int comensales, String numero_mesa, String comentario, boolean pago_efectivo) {
        this.id = id;
        this.cliente_asociado = cliente_asociado;
        this.comensales = comensales;
        this.numero_mesa = numero_mesa;
        this.comentario = comentario;
        this.pago_efectivo = pago_efectivo;
    }
    
    
    
    public Reserva(int id, Cliente cliente_asociado, int comensales, String numero_mesa, String comentario, LocalDate fecha, Time hora, boolean pago_efectivo) {
        this.id = id;
        this.cliente_asociado = cliente_asociado;
        this.comensales = comensales;
        this.numero_mesa = numero_mesa;
        this.comentario = comentario;
        this.fecha = fecha;
        this.hora = hora;
        this.pago_efectivo = pago_efectivo;
    }

    public Cliente getCliente_asociado() {
        return cliente_asociado;
    }

    public void setCliente_asociado(Cliente cliente_asociado) {
        this.cliente_asociado = cliente_asociado;
    }

    public int getComensales() {
        return comensales;
    }

    public void setComensales(int comensales) {
        this.comensales = comensales;
    }

    public String getNumero_mesa() {
        return numero_mesa;
    }

    public void setNumero_mesa(String numero_mesa) {
        this.numero_mesa = numero_mesa;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }


    public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public boolean isPago_efectivo() {
        return pago_efectivo;
    }

    public void setPago_efectivo(boolean pago_efectivo) {
        this.pago_efectivo = pago_efectivo;
    }
    
    
    
}
