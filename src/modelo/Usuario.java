package modelo;

import javafx.beans.property.SimpleStringProperty;

public class Usuario extends Persona {
	
    private int permiso;
    private SimpleStringProperty clave;
	

    public Usuario(String nombre, String clave, String correo, int permiso) {
        this.nombre = new SimpleStringProperty(nombre);
        this.clave = new SimpleStringProperty(clave);
        this.correo = new SimpleStringProperty(correo);
        this.permiso = permiso;
    }
    
    @Override
    public String getNombre() {
        return nombre.get();
    }
    
    @Override
    public void setNombre(String nombre) {
        this.nombre = new SimpleStringProperty(nombre);;
    }
    
    @Override
    public String getCorreo() {
        return correo.get();
    }
    
    @Override
    public void setCorreo(String correo) {
        this.correo = new SimpleStringProperty(correo);
    }

    public String getClave() {
        return clave.get();
    }

    public void setClave(String clave) {
        this.clave = new SimpleStringProperty(clave);
        
    }
    
    public int getPermiso() {
        return permiso;
    }

    public void setPermiso(int permiso) {
        this.permiso = permiso;
    }
}
