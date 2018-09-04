package controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import ventanas.Ventanas;


public class LoginControlador implements Initializable{

	/* ===============================================================
   	* Atributos de la vista Login
   	* ===============================================================*/
    @FXML
    private AnchorPane panelLoginRegistro;
    
    private AnchorPane home; 
    
    /**
     * Metodo principal de inicializacion del Login Controlador
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            cargarPanel(home,"/ventanas/LoginPanel.fxml");
        } catch (IOException ex) {
           System.out.println("El panel del Login no pudo ser creado");
        }
    }

    /**
     * Metodos para cargar el panel de Registro y Login
     * @param panel Panel de base que sera reemplazado
     * @param loc Localizacion del nuevo panel
     * @throws IOException Ruta invalida del panel
     */
    public void cargarPanel(AnchorPane panel,String loc) throws IOException
    {
        
        panel = FXMLLoader.load(Ventanas.class.getResource(loc));
        
        Ventanas.setAnimacionObjeto("fade", panel);
        Ventanas.setAnimacionObjeto("scale_repeat", panel,500);
       
        panelLoginRegistro.getChildren().clear();
        panelLoginRegistro.getChildren().add(panel);
        
    }

}
