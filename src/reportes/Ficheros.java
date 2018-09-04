package reportes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;

import modelo.Cliente;
import modelo.Reserva;
import modelo.Usuario;
import principal.Altamira;
import ventanas.Ventanas;

public class Ficheros {
    
	
	/**
	 * Metodo que guarda los clientes de altamira en un fichero CSV
	 * @param altamira La clase principal que es ingresada como parametro
	 * @throws FileNotFoundException Retorna una excepcion en caso de no poder manipular el fichero
	 */
	private static void guardarDatosCSVClientes(Altamira altamira) throws FileNotFoundException
	{
		//Manejo de excepcion
    	PrintWriter pw_clientes = new PrintWriter(new File("Clientes.csv"));
        StringBuilder sb_clientes;
        
        
        ArrayList<Cliente> listaClientes = altamira.getClientes();
        
        for(Cliente cliente_actual : listaClientes)
        {
        	sb_clientes= new StringBuilder();
        	
        	sb_clientes.append(cliente_actual.getNombre());
        	sb_clientes.append(",");
        	
            sb_clientes.append(cliente_actual.getTelefono_contacto());
            sb_clientes.append(",");
            
            sb_clientes.append(cliente_actual.getRut());
        	sb_clientes.append(",");
        	
        	sb_clientes.append(cliente_actual.getEdad());
        	
        	sb_clientes.append(System.getProperty("line.separator"));
        	pw_clientes.write(sb_clientes.toString());
        }
        pw_clientes.close();
        System.out.println("<Archivo Clientes.csv guardado con exito>");
        
	}
	
	/**
	 * Metodo que guarda las reservas de altamira en un fichero CSV
	 * @param altamira La clase principal que es ingresada como parametro
	 * @throws FileNotFoundException Retorna una excepcion en caso de no poder manipular el fichero
	 */
	private static void guardarDatosCSVReservas(Altamira altamira) throws FileNotFoundException
	{
		PrintWriter pw_reservas = new PrintWriter(new File("Reservas.csv"));
        StringBuilder sb_reservas;
        
        ArrayList<Reserva> listaReservas = altamira.getReservas();
        
        for(Reserva reserva_actual : listaReservas)
        {
        	sb_reservas= new StringBuilder();
        	
        	sb_reservas.append(reserva_actual.getId());
        	sb_reservas.append(",");
        	
            sb_reservas.append(reserva_actual.getCliente_asociado().getTelefono_contacto());
            sb_reservas.append(",");
            
            sb_reservas.append(reserva_actual.getNumero_mesa());
        	sb_reservas.append(",");
        	
        	sb_reservas.append(reserva_actual.getFecha().getDayOfMonth());
        	sb_reservas.append(",");
        	
        	sb_reservas.append(reserva_actual.getFecha().getMonthValue());
        	sb_reservas.append(",");
        	
        	sb_reservas.append(reserva_actual.getFecha().getYear());
        	sb_reservas.append(",");
        	
        	sb_reservas.append(reserva_actual.getComensales());
        	sb_reservas.append(",");
        	
        	sb_reservas.append(reserva_actual.getComentario());
        	sb_reservas.append(",");
        	
        	sb_reservas.append(reserva_actual.isPago_efectivo());
        	
        	sb_reservas.append(System.getProperty("line.separator"));
        	pw_reservas.write(sb_reservas.toString());
        }
        pw_reservas.close();
        System.out.println("<Archivo Reservas.csv guardado con exito>");
	}
	
	/**
	 * Metodo que guarda los datos de la aplicacion en archivos CSV.
	 * Este metodo es llamado al terminar la aplicacion y puede ser ejecutado en cualquier momento
	 * @throws FileNotFoundException Retorna la expecion a un archivo imposible de guardar
	 */
    public static void guardarDatosCSV() throws FileNotFoundException
    {
        //Escribir en archivo de reservas
    	Altamira altamira = (Altamira)Ventanas.getData();
    	
    	//Guardar los clientes en un archivo CSV
    	guardarDatosCSVClientes(altamira);
    	//Guardar las reservas en un archivo CSV
    	guardarDatosCSVReservas(altamira);
    	
    }
    
    public static void guardarUsuariosCSV(Altamira altamira) throws FileNotFoundException
    {
    	//Manejo de excepcion
    	PrintWriter pw_usuarios = new PrintWriter(new File("Usuarios.csv"));
        StringBuilder sb_usuarios;
        
        
        ArrayList<Usuario> listaUsuarios = altamira.getUsuarios();
        
        for(Usuario usuario_actual : listaUsuarios)
        {
        	sb_usuarios= new StringBuilder();
        	
        	sb_usuarios.append(usuario_actual.getNombre());
        	sb_usuarios.append(",");
        	
        	sb_usuarios.append(usuario_actual.getClave());
        	sb_usuarios.append(",");
            
        	sb_usuarios.append(usuario_actual.getCorreo());
        	sb_usuarios.append(",");
        	
        	sb_usuarios.append(usuario_actual.getPermiso());
        	
        	sb_usuarios.append(System.getProperty("line.separator"));
        	pw_usuarios.write(sb_usuarios.toString());
        }
        pw_usuarios.close();
        System.out.println("<Archivo Usuarios.csv guardado con exito>");
    }
    
    /**
     * Metodo que carga los datos de la aplicacion mediante archivos CSV
     * @param altamira La clase altamira donde seran guardado los datos cargados
     * @throws IOException Retorna una excepcion en caso de no poder manipular algun fichero
     */
    public static void leerDatosCSV(Altamira altamira) throws IOException
    {
        leerDatosClientes(altamira);
        leerDatosReservas(altamira);
        leerDatosUsuarios(altamira);       
    }

	private static void leerDatosUsuarios(Altamira altamira)
	{
		String archivoCSV = "Usuarios.csv";
        BufferedReader br = null;
        String line = "";
        String separador = ",";


            try {
				br = new BufferedReader(new FileReader(archivoCSV));
				while ((line = br.readLine()) != null) {

	                // use comma as separator
	                String[] lector = line.split(separador);
	                
	                System.out.println("\n<USUARIO CARGADO>");
	                System.out.println("Nombre:"+lector[0]);
	                System.out.println("Pass: ");
	                System.out.println("Correo:"+lector[2]);
	                System.out.println("Permiso:"+lector[3]);
	                
	                altamira.agregarUsuario(new Usuario(lector[0],lector[1],lector[2],Integer.parseInt(lector[3])));
	                
	            }
			} catch (FileNotFoundException e) {
				System.out.println("Archivo no encontrado");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
            
	}
    /**
     * Metodo que lee los clientes registrados mediante el archivo csv de entrada
     * @param altamira La clase altamira donde guardar los clientes
     * @throws IOException En caso de no poder manipular el fichero
     */
    public static void leerDatosClientes(Altamira altamira) throws IOException
    {
    	String archivoCSV = "Clientes.csv";
        BufferedReader br = null;
        String line = "";
        String separador = ",";
        
        try {

            br = new BufferedReader(new FileReader(archivoCSV));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] lector = line.split(separador);
                
                System.out.println("\n<CLIENTE CARGADO>");
                System.out.println("Nombre:"+lector[0]);
                System.out.println("Telefono:"+lector[1]);
                System.out.println("Rut:"+lector[2]);
                System.out.println("Edad:"+lector[3]);
                
                altamira.agregarCliente(new Cliente(lector[0],lector[1], lector[2], Integer.parseInt(lector[3])));
            }
            
        } catch (FileNotFoundException e) {
        		System.out.println("Archivo no encontrado");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * Metodo que lee las reservas registradas mediante el archivo csv de entrada
     * @param altamira La clase altamira donde guardar los clientes
     * @throws IOException En caso de no poder manipular el fichero
     */
    public static void leerDatosReservas(Altamira altamira)
    {
    	String archivoCSV = "Reservas.csv";
        BufferedReader br = null;
        String line = "";
        String separador = ",";
            try {
				br = new BufferedReader(new FileReader(archivoCSV));
				while ((line = br.readLine()) != null) {

	                // use comma as separator
	                String[] lector = line.split(separador);
	                
	                System.out.println("\n<RESERVA CARGADA>");
	                System.out.println("Id:"+lector[0]);
	                System.out.println("Telefono Cliente:"+lector[1]);
	                System.out.println("Mesa Asignada:"+lector[2]);
	                System.out.println("Dia de la reserva:"+lector[3]);
	                System.out.println("Mes de la reserva:"+lector[4]);
	                System.out.println("Año de la reserva:"+lector[5]);
	                
	                System.out.println("Comensales:"+lector[6]);
	                System.out.println("Comentario:"+lector[7]);
	                System.out.println("Medio de pago:"+lector[8]);
	                
	                int id = Integer.parseInt(lector[0]);
	                String telefono_cliente = lector[1];
	                String mesaAsignada = lector[2];
	                int dia = Integer.parseInt(lector[3]);
	                int mes = Integer.parseInt(lector[4]);
	                int anio = Integer.parseInt(lector[5]);
	                int comensales = Integer.parseInt(lector[6]);
	                String comentario = lector[7];
	                boolean tipoPago = Boolean.parseBoolean(lector[8]);
	                
	                
	                Cliente cliente_asociado = altamira.getCliente(telefono_cliente);
	                LocalDate fecha_asociada = LocalDate.of(anio, mes, dia);
	                
	                Reserva nueva_reserva = new Reserva(id,cliente_asociado,comensales,mesaAsignada,comentario,fecha_asociada,null,tipoPago);
	                altamira.agregarReserva(nueva_reserva);
	            }
			} catch (FileNotFoundException e) {
				System.out.println("Archivo no encontrado");
			} catch (IOException e) {
				e.printStackTrace();
			}
            finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
    }
    
   
}
