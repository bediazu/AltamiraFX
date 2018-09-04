
package controladores;

import com.jfoenix.controls.JFXButton;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.JRException;
import reportes.Ficheros;
import ventanas.Ventanas;


public class HomeControlador implements Initializable {

	/* ===============================================================
   	* Atributos de la vista Home (Esquema principal de las demas vistas)
   	* ===============================================================*/
    @FXML
    private AnchorPane panelHome;
    @FXML
    private JFXButton btnReservas;
    @FXML
    private JFXButton btnClientes;
    @FXML
    private JFXButton btnEstadisticas;
    @FXML
    private JFXButton btnInformes;
    
    //Variable temporal para determinar el ultimo boton presionado
    private JFXButton botonSeleccionado;

 
    
    /**
     * Metodo de inicializacion de la ventana
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
        	//Cargar la ventana principal
            cargarPanel(panelHome,"/ventanas/Reservas.fxml",btnReservas);
            irReservas(null);
            cargarPanel(panelHome,"/ventanas/Reservas.fxml",btnReservas);
        } catch (IOException ex) {
            System.out.println("La ventana principal no pudo ser creada");
        }
    }    

    /**
     * Metodo utilizado para sobreescribir paneles sobre la vista principal
     * @param panel panel a reemplazar
     * @param loc Localizacion del nuevo panel
     * @param boton_presionado Ultimo boton presionado
     * @throws IOException En caso de no tener una ruta valida
     */
    public void cargarPanel(AnchorPane panel,String loc,JFXButton boton_presionado) throws IOException
    {
    	//Cargar el panel mediante su localizacion
        panel = FXMLLoader.load(Ventanas.class.getResource(loc));
        
        //Agregar una animacion a la ventana de escalado repetitivo
        Ventanas.setAnimacionObjeto("scale_repeat", panel,400);
       
        //Cargar el ultimo boton presionado para su futuro uso
        seleccionarBoton(boton_presionado);
       
        //Sobreescribir el panel seleccionado en la vista HOME
        panelHome.getChildren().clear();
        panelHome.getChildren().add(panel);
        
        //Agregar una animacion a la ventana de difuminado
        Ventanas.setAnimacionObjeto("fade", panel);
        
    }
    
    /**
     * Metodo visual al momento de apretar un boton del menu lateral
     * @param boton Ultimo boton presionado
     */
    private void seleccionarBoton(JFXButton boton)
    {
        if(botonSeleccionado!=null)
        {
            botonSeleccionado.setStyle("-fx-background-color: transparent");
        }
        
        boton.setStyle("-fx-background-color: #202332");
        botonSeleccionado=boton;
    }

    /* ===============================================================
   	* Metodos para acceder a los diversos paneles de la aplicacion
   	* Inicio
   	* Reservas
   	* Clientes
   	* Estadisticas
   	* Reportes
   	* Configuraciones
   	* (Sujeto a modificaciones futuras)
   	* ===============================================================*/
    
    
    /**
     * Metodos para cargar el panel de Reservas
     * @param event Evento click
     * @throws IOException Ruta no valida
     */
    @FXML
    private void irReservas(MouseEvent event) throws IOException {
        cargarPanel(panelHome,"/ventanas/Reservas.fxml",btnReservas);
    }
    
    /**
     * Metodos para cargar el panel de Clientes
     * @param event Evento click
     * @throws IOException Ruta no valida
     */
    @FXML
    private void irClientes(MouseEvent event) throws IOException {
        cargarPanel(panelHome,"/ventanas/Clientes.fxml",btnClientes);
    }

    /**
     * Metodos para cargar el panel de Estadisticas
     * @param event Evento click
     * @throws IOException Ruta no valida
     */
    @FXML
    private void irEstadisticas(MouseEvent event) throws IOException {
        cargarPanel(panelHome,"/ventanas/Estadisticas.fxml",btnEstadisticas);
    }

    /**
     * Metodos para cargar el panel de Informes
     * @param event Evento click
     * @throws IOException Ruta no valida
     * @throws JRException 
     */
    @FXML
    private void irInformes(MouseEvent event) throws IOException, JRException {
        cargarPanel(panelHome,"/ventanas/Informes.fxml",btnInformes);
    }

	/**
	 * Salir de la aplicacion
	 * @param event Evento click
	 * @throws FileNotFoundException Error en los archivo csv de guardado
	 */
    @FXML
    private void salir(MouseEvent event) throws FileNotFoundException {
        Ficheros.guardarDatosCSV();
        Platform.exit();
    }

    
    
}
