package modelo;

import java.util.ArrayList;
import java.util.HashMap;


public class MapaClientes implements Mantenimiento																			{

    private HashMap<String,Cliente> clientes;
    
    public MapaClientes()
    {
        clientes = new HashMap<>();
        
    }
   
    @Override
    public void agregar(Object o) {
        Cliente cliente_aux = (Cliente)o;
        clientes.put(cliente_aux.getTelefono_contacto(),cliente_aux);
    }
    
    @Override
    public void eliminar(Object o) {
        Cliente cliente_aux = (Cliente)o;
        if(clientes.get(cliente_aux.getTelefono_contacto())!=null)
        {
            clientes.remove(cliente_aux.getTelefono_contacto());
        }
    }
    
    public void eliminar(String telefono_contacto)
    {
        Cliente cliente_aux = clientes.get(telefono_contacto);
        clientes.remove(cliente_aux.getTelefono_contacto());
    }

    @Override
    public void editar(Object o,Object new_o) {
        Cliente cliente_aux = (Cliente)o;
        Cliente cliente_aux_2 = (Cliente)new_o;
        
        //Si ambos clientes mantienen un numero de contacto identico,
        //se permite la edicion.
        if(cliente_aux.getTelefono_contacto().equals(cliente_aux_2.getTelefono_contacto()))
        {
            cliente_aux.setEdad(cliente_aux_2.getEdad());
            cliente_aux.setNombre(cliente_aux_2.getNombre());
            cliente_aux.setRut(cliente_aux_2.getRut());
        }
        else
        {
            clientes.remove(cliente_aux.getTelefono_contacto());
            cliente_aux.setEdad(cliente_aux_2.getEdad());
            cliente_aux.setNombre(cliente_aux_2.getNombre());
            cliente_aux.setRut(cliente_aux_2.getRut());
            
            cliente_aux.setTelefono_contacto(cliente_aux_2.getTelefono_contacto());
            
            clientes.put(cliente_aux.getTelefono_contacto(), cliente_aux);
        }
        
    }
    
    @Override
    public Object buscar(Object o) {
        Cliente cliente_aux = (Cliente)o;
        
        return clientes.get(cliente_aux.getTelefono_contacto());
    }
    
    public Object buscar(String telefono_contacto)
    {
        return clientes.get(telefono_contacto);
    }
    
    public ArrayList<Cliente> listaClientes()
    {
        ArrayList<Cliente> lista= new ArrayList<>(clientes.values());
        return lista;
    }
    

    
    
}
