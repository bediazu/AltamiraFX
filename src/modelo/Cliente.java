package modelo;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;

public class Cliente extends Persona {
    
	/* ===============================================================
     * ATRIBUTOS DE CLASE CLIENTE, EXTIENDE DE CLASE ABSTRACTA PERSONA
     * ===============================================================*/
    
    private SimpleStringProperty telefono_contacto;
    private SimpleStringProperty rut;
    
    private int edad;
    
    private final ListaReservas reservas; //Lista no modificable
    
    /* ===============================================================
     * CONSTRUTORES SOBRECARGADOS DE LA CLASE CLIENTE
     * ===============================================================*/
    
    public Cliente()
    {
    	reservas = new ListaReservas();
    }

    public void setTelefono_contacto(SimpleStringProperty telefono_contacto) {
		this.telefono_contacto = telefono_contacto;
	}

	public void setRut(SimpleStringProperty rut) {
		this.rut = rut;
	}

	public Cliente(String nombre, String telefono_contacto) {
    	
        this.nombre = new SimpleStringProperty(nombre);
        this.telefono_contacto = new SimpleStringProperty(telefono_contacto);
        reservas = new ListaReservas();
    }
    
    public Cliente(String nombre,String telefono_contacto,String correo,String rut,int edad)
    {
    	this.nombre= new SimpleStringProperty(nombre);
    	this.telefono_contacto = new SimpleStringProperty(telefono_contacto);
    	this.correo = new SimpleStringProperty(correo);
    	this.rut = new SimpleStringProperty(rut);
    	this.edad=edad;
    	
    	reservas = new ListaReservas();
    }
    
    public Cliente(String nombre,String telefono_contacto,String rut , int edad) {
        this.rut = new SimpleStringProperty(rut);
        this.nombre = new SimpleStringProperty(nombre);
        this.telefono_contacto = new SimpleStringProperty(telefono_contacto);
        this.edad = edad;
        reservas = new ListaReservas();
    }
    
    /* ===============================================================
     * METODOS PROPIOS DE LA CLASE. RESPETANDO NORMAS DE ENCAPSULACION
     * ===============================================================*/
    
    public void agregarReserva(Reserva reserva)
    {
        reservas.agregar(reserva);
    }
    
    /* ===============================================================
     * METODOS PROPIOS DE LA CLASE. RESPETANDO NORMAS DE ENCAPSULACION
     * METODOS SOBREESCRITOS DE LA INTERFAZ PERSONA @OVERRRIDE
     * ===============================================================*/
    
    @Override
    public String getNombre() {
        return nombre.get();
    }
    
    @Override
    public void setNombre(String nombre)
    {
    	this.nombre.set(nombre);
    }
    
    @Override
	public String getCorreo() {
		return correo.get();
	}
    
    @Override
	public void setCorreo(String correo) {
		this.correo.set(correo);
	}
    
    /////////////////////////////////////////////////////////////////////

    public String getRut() {
        return rut.get();
    }

    public void setRut(String rut) {
        this.rut.set(rut);
    }

    public String getTelefono_contacto() {
        return telefono_contacto.get();
    }

    public void setTelefono_contacto(String telefono_contacto) {
        this.telefono_contacto.set(telefono_contacto);
    }
    
    public int getEdad() {
        return edad;
    }
    

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    public ArrayList<Reserva> getReservas()
    {
    	return reservas.listaReservas();
    }
    
    
}
