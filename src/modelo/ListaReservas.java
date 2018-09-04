package modelo;

import java.util.ArrayList;


public class ListaReservas implements Mantenimiento{
    
    private ArrayList<Reserva> reservas;
    
    public ListaReservas()
    {
        reservas = new ArrayList<Reserva>();
        
    }

    @Override
    public void agregar(Object o) {
        Reserva reserva_aux = (Reserva)o;
        reservas.add(reserva_aux);
    }

    @Override
    public void eliminar(Object o) {
        Reserva reserva_aux = (Reserva)o;
        reservas.remove(reserva_aux);
    }

    @Override
    public void editar(Object o, Object new_o) {
        Reserva reserva_aux_new = (Reserva)new_o;
        Reserva reserva_aux = (Reserva)o;
        
        for(Reserva reserva_actual : reservas)
        {
            if(reserva_actual.equals(reserva_aux))
            {
                reserva_actual.setCliente_asociado(reserva_aux_new.getCliente_asociado());
                reserva_actual.setComensales(reserva_aux_new.getComensales());
                reserva_actual.setComentario(reserva_aux_new.getComentario());
                reserva_actual.setFecha(reserva_aux_new.getFecha());
                reserva_actual.setHora(reserva_aux_new.getHora());
                reserva_actual.setNumero_mesa(reserva_aux_new.getNumero_mesa());
                reserva_actual.setPago_efectivo(reserva_aux_new.isPago_efectivo());
            }
        }
    }

    @Override
    public Object buscar(Object o) {
        Reserva reserva_aux = (Reserva)o;
        for(Reserva reserva_actual : reservas)
        {
            if(reserva_actual.equals(reserva_aux))
            {
                return reserva_actual;
            }
        }
        return null;
    }
    
    public ArrayList<Reserva> listaReservas()
    {
    	return reservas;
    }
    
    
}
