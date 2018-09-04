package principal;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import reportes.Ficheros;

import ventanas.Ventanas;


/*
 * Agregar las funcionalidades propias del negocio.
 */

public class Main extends Application{
    
    public static void main(String args[])
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        

        //Configuraciones del stage principal.
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        
        //Configuraciones del programa con sus estructuras.
        Altamira altamira = new Altamira();
        Ficheros.leerDatosCSV(altamira);
        
        //Iniciar clase Ventanas.
        Ventanas.iniciarVentanas(primaryStage);
        
        //Cargar Ventanas a usar.
        iniciarVentanas();
   
        //Traspaso del objeto altamira.
        Ventanas.setVentanaInicial("Login",altamira);
    }
    
    private void iniciarVentanas() throws IOException
    {
        /*
            Inicializacion de las ventanas.
        */
        Ventanas.setVentana("Login", "/ventanas/Login.fxml","Login");
        Ventanas.setVentana("Home", "/ventanas/Home.fxml","Altamira",1000,650);
        //Ventanas.setVentana()
        
        Ventanas.setAnimacionVentana("fade",1200);
    }
}
