package controladores;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.controlsfx.control.Notifications;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import modelo.Usuario;
import principal.Altamira;
import reportes.Ficheros;
import ventanas.Ventanas;

public class RegistroPanelControlador {

    @FXML
    private JFXTextField txtUsuario;
    @FXML
    private JFXTextField txtCorreo;
    @FXML
    private JFXPasswordField txtPass;
    @FXML
    private JFXPasswordField txtPass2;
    @FXML
    private AnchorPane panelRegistro;

    
    
    /**
     * Metodo para agregar un usuario a Altamira
     * @param event Evento click
     * @throws FileNotFoundException 
     */
    @FXML
    private void agregarUsuarioRegistro(MouseEvent event) throws FileNotFoundException {
    	Altamira altamira = (Altamira)Ventanas.getData();
    	
    	String nombre = txtUsuario.getText();
    	if(nombre.length()>0)
    	{
    		String correo = txtCorreo.getText();
    		
    		String pass = txtPass.getText();
    		String pass2 = txtPass2.getText();
    		
    		if(pass.equals(pass2))
    			
    		{
    			altamira.agregarUsuario(new Usuario(nombre,pass,correo,1));
    			Notifications.create().title("Usuario creado con exito!").text("Nuevo usuario creado").darkStyle().showInformation();
    			
    			//Salida de excepcion
    			Ficheros.guardarUsuariosCSV(altamira);
    		}
    		
    	}
    	
    }

    /**
     * Metodo para volver al panel del login
     * @param event Evento click
     * @throws IOException Ruta del panel invalida
     */
    @FXML
    private void volverLogin(MouseEvent event) throws IOException {
       
        //Obtener el contralador de otra ventana.
        LoginControlador controlador = (LoginControlador)Ventanas.getControlador("Login");
        controlador.cargarPanel(panelRegistro,"/ventanas/LoginPanel.fxml");
        
    }

    /**
     * Metodo para salir de la aplicacion
     * @param event Evento click
     */
    @FXML
    private void salir(MouseEvent event) {
        Platform.exit();
    }
    
}
