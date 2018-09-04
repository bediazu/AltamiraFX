package modelo;

import java.util.ArrayList;
import java.util.HashMap;

public class MapaUsuarios implements Mantenimiento{
    
    private HashMap<String,Usuario> usuarios;
    
    public MapaUsuarios()
    {
        usuarios = new HashMap<>();
        
    }

    @Override
    public void agregar(Object o) {
        Usuario usuario_aux = (Usuario)o;
        usuarios.put(usuario_aux.getNombre(), usuario_aux);
    }

    @Override
    public void eliminar(Object o) {
        Usuario usuario_aux = (Usuario)o;
        if(usuarios.get(usuario_aux.getNombre())!=null)
        {
            usuarios.remove(usuario_aux.getNombre());
        }
    }
    
    public void eliminar(String nick)
    {
        Usuario usuario_aux = usuarios.get(nick);
        usuarios.remove(usuario_aux.getNombre());
    }

    @Override
    public void editar(Object o, Object new_o) {
        Usuario usuario_aux = (Usuario)o;
        Usuario usuario_aux_2 = (Usuario)new_o;
    
        //Si ambos clientes mantienen un nick identico,
        //se permite la edicion.
        if(usuario_aux.getNombre().equals(usuario_aux.getNombre()))
        {
            usuario_aux.setClave(usuario_aux_2.getClave());
            usuario_aux.setCorreo(usuario_aux_2.getCorreo());
            usuario_aux.setPermiso(usuario_aux_2.getPermiso());
        }
        else
        {
            usuarios.remove(usuario_aux.getNombre());
            usuario_aux.setClave(usuario_aux_2.getClave());
            usuario_aux.setCorreo(usuario_aux_2.getCorreo());
            usuario_aux.setPermiso(usuario_aux_2.getPermiso());
            
            usuario_aux.setNombre(usuario_aux_2.getCorreo());
            
            usuarios.put(usuario_aux.getNombre(), usuario_aux);
        }
        
    }

    @Override
    public Object buscar(Object o) {
        return usuarios.get(o);
    }
    
    public ArrayList<Usuario> listaUsuarios()
    {
        ArrayList<Usuario> lista= new ArrayList<>(usuarios.values());
        return lista;
    }
    
}
