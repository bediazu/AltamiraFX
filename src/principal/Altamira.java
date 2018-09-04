package principal;

import java.util.ArrayList;

import modelo.Cliente;
import modelo.ListaReservas;
import modelo.MapaClientes;
import modelo.MapaUsuarios;
import modelo.Reserva;
import modelo.Usuario;

public class Altamira {
    
    private MapaUsuarios usuarios;
    private MapaClientes clientes;
    private ListaReservas reservas;
    
    public Altamira()
    {
        usuarios = new MapaUsuarios();
        clientes = new MapaClientes();
        reservas = new ListaReservas();
    }
    
    public void agregarUsuario(Usuario usuario)
    {
    	usuarios.agregar(usuario);
    }
    
    
    public void agregarCliente(Cliente cliente)
    {
        clientes.agregar(cliente);
    }
    
    public void agregarReserva(Reserva reserva)
    {
        reservas.agregar(reserva);
        
        Cliente cliente = reserva.getCliente_asociado();
        cliente.agregarReserva(reserva);
        
        System.out.println("Reserva agregada con exito");
    }
    public ArrayList<Cliente> getClientes()
    {
        ArrayList<Cliente> listaClientes = clientes.listaClientes();
        
        return listaClientes;
        
    }
    
    public ArrayList<Usuario> getUsuarios()
    {
        ArrayList<Usuario> listaUsuarios = usuarios.listaUsuarios();
        
        return listaUsuarios;
    }
    
    public Usuario getUsuario(String nombreUsuario)
    {
    	return (Usuario) usuarios.buscar(nombreUsuario);
    }
    
    public ArrayList<Reserva> getReservas()
    {
    	ArrayList<Reserva> listaReservas = reservas.listaReservas();
    	return listaReservas;
    }
    
    public ArrayList<Reserva> getReservasByCliente(Cliente cliente)
    {
    	return cliente.getReservas();
    }
    
    public void editarCliente(Cliente cliente,Cliente cliente_nuevo)
    {
        clientes.editar(cliente, cliente_nuevo);
    }
    
    public void editarReserva(Reserva reserva,Reserva reserva_nuevo)
    {
    	reservas.editar(reserva, reserva_nuevo);
    }
    public void mostrarClientes()
    {
        ArrayList<Cliente> listaClientes = clientes.listaClientes();
        
        System.out.println("CLIENTES: ");
        for(Cliente cliente_actual : listaClientes)
        {
            System.out.println(cliente_actual.getNombre());
        }
    }
    
    public Cliente getCliente(String correo)
    {
        
        return (Cliente)clientes.buscar(correo);
    }

    public void eliminarCliente(String text) {
        clientes.eliminar(text);
    }
    
    public void eliminarReserva(Reserva reserva)
    {
    	reservas.eliminar(reserva);
    }
}
