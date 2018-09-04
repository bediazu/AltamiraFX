
package controladores;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;

import org.controlsfx.control.Notifications;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import modelo.Usuario;
import principal.Altamira;
import ventanas.Ventanas;

public class LoginPanelControlador {

	/* ===============================================================
   	* Atributos del panel del Login
   	* ===============================================================*/
    @FXML
    private JFXTextField txtUser;
    @FXML
    private JFXPasswordField txtPass;
    @FXML
    private JFXButton btnRegistro;
    @FXML
    private AnchorPane panelLogin;
    
    /**
     * Presionar el boton enter para entrar a la autenticacion
     * @param event Presionar tecla enter
     */
    @FXML
    void Enter(KeyEvent event) {
    	if(event.getCode() == KeyCode.ENTER)
        {
        	login_usuario(null);
        }
    }

    /**
     * Metodo iniciar la ventana principal del programa en caso de tener las validaciones correctas
     * @param event Evento click
     */
    @FXML
    private void login_usuario(MouseEvent event) {
    	
    	Altamira altamira = (Altamira)Ventanas.getData();
    	String usuario = txtUser.getText();
    	String pass = txtPass.getText();
    	if(usuario.length()>0)
    	{
    		Usuario usuario_asociado = altamira.getUsuario(usuario);
    		if(usuario_asociado!=null)
    		{
    			if(usuario_asociado.getClave().equals(pass))
            	{
            		Notifications.create().title("Usuario Ingresado con Exito! ").text("Bienvenido "+usuario+".").darkStyle().showInformation();
            		Ventanas.ir("Home",Ventanas.getData());
            	}
    		}
    		else
    		{
    			Notifications.create().title("Error en el login").text("Datos invalidos, intente nuevamente").darkStyle().showError();
    		}
        	
    	}
    	else
    	{
    		Notifications.create().title("Error en el login").text("Datos invalidos, intente nuevamente").darkStyle().showError();
    	}
    	
        
    }

    /**
     * Metodo para cargar el panel de Registro al presionar el boton de crear usuario
     * @param event Evento click
     * @throws IOException Ruta invalida del panel
     */
    @FXML
    private void registro_usuario(MouseEvent event) throws IOException {
        
        //Obtener el contralador de otra ventana.
        LoginControlador controlador = (LoginControlador)Ventanas.getControlador("Login");
        controlador.cargarPanel(panelLogin, "/ventanas/RegistroPanel.fxml");
        
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
