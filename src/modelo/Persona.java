package modelo;

import javafx.beans.property.SimpleStringProperty;

public abstract class Persona
{
	protected SimpleStringProperty nombre;
	protected SimpleStringProperty correo;
	
    
	public abstract String getNombre();
	public abstract void setNombre(String nombre);
	public abstract String getCorreo();
	public abstract void setCorreo(String correo);
	
	

}
