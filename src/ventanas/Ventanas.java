package ventanas;


import java.io.IOException;
import java.util.HashMap;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public final class Ventanas {
    
    private static HashMap<String,RutaScene> rutas = new HashMap<>();
    
    /* Valores por defecto */
    private static final String TITULO_VENTANA = "";
    private static final Double ANCHO_VENTANA = 800.0;
    private static final Double ALTO_VENTANA = 600.0;
   
    private static final Double FADE_ANIMATION_DURATION = 800.0;
    private static final Double SCALE_ANIMATION_DURATION = 800.0;
    
    /*Valores de la ventan actual*/
    private static Stage stage_actual;
    private static RutaScene ruta_actual;
    
    private static String titulo_ventana;
    private static Double ancho_ventana;
    private static Double alto_ventana;
    
    // routes switching animation
    private static String tipo_animacion_ventana;
    private static Double duracion_animacion_ventana;
    
    private static String tipo_animacion_objeto;
    private static Double duracion_animacion_objeto;
    
    private static double xOffset;
    private static double yOffset;

    
    
    //Constructor privado para utilizar metodos de inicio
    private Ventanas(){} 
    ////////////////////////////////////////////////////
    
    public static void iniciarVentanas(Stage stage)
    {
        stage_actual=stage;
        stage_actual.getIcons().add(new Image(Ventanas.class.getResourceAsStream("/logos/altamira-logo.png")));
    }
    
    public static void iniciarVentanas(Stage stage,String titulo)
    {
        stage_actual=stage;
        titulo_ventana=titulo;
    }
    
    public static void iniciarVentanas(Stage stage,double ancho,double alto)
    {
        stage_actual=stage;
        ancho_ventana=ancho;
        alto_ventana=alto;
    }
    
    public static void iniciarVentanas(Stage stage,String titulo,double ancho,double alto)
    {
        stage_actual=stage;
        titulo_ventana=titulo;
        ancho_ventana=ancho;
        alto_ventana=alto;
    }
    
    
    private static class RutaScene
    {
        private String direccion;
        
        //Objeto traspasado en funcion ir.
        private Object data;
        
        //Controlador asignado al momento de cargar fichero FXML.
        private  Object controlador;
        
        /* Valores de la ventana al ser creada */
        private String titulo_ventana;
        private double ancho_ventana;
        private double alto_ventana;
        
        private Scene scene;
        
        /* Sobrecarga del constructor */
        private RutaScene(String direccion)
        {
            this(direccion, getTituloVentana(), getAnchoVentana(), getAltoVentana());
        }
        
        private RutaScene(String direccion,String titulo_ventana)
        {
            this(direccion, titulo_ventana, getAnchoVentana(), getAltoVentana());
        }
        
        private RutaScene(String direccion,double ancho_ventana,double alto_ventana)
        {
            this(direccion, getTituloVentana(), ancho_ventana, alto_ventana);
        }
        
        private RutaScene(String direccion,String titulo_ventana,double ancho_ventana,double alto_ventana)
        {
            this.direccion=direccion;
            this.titulo_ventana=titulo_ventana;
            this.ancho_ventana=ancho_ventana;
            this.alto_ventana=alto_ventana;
            this.scene=null;
        }
        
        /* Asignar los valores por defecto en caso de no existir */
        private static String getTituloVentana()
        {
            return Ventanas.titulo_ventana != null ? Ventanas.titulo_ventana : TITULO_VENTANA;
        }
        private static double getAltoVentana()
        {
            return Ventanas.alto_ventana != null ? Ventanas.alto_ventana : ALTO_VENTANA;
        }
        private static double getAnchoVentana()
        {
            return Ventanas.ancho_ventana != null ? Ventanas.ancho_ventana : ANCHO_VENTANA;
        }
    }
    
    /** Abre una nueva ventana por medio de su identificador
     * @param id: Identificar de ventana.
     * @param data: Dato a traspasar a la nueva ventana.
     */
    public static void ir(String id,Object data)
    {
        RutaScene ruta_aux = rutas.get(id);
        if(ruta_aux!=null)
        {
            ruta_aux.data=data;
            iniciarVentana(ruta_aux);
        }
        else
        {
            System.out.println("VENTANA NO CARGADA");
        }    
    }
    /** Abre una nueva ventana por medio de su identificador
     * @param id: Identificar de ventana.
     */
    public static void ir(String id)
    {
        RutaScene ruta_aux = rutas.get(id);
        if(ruta_aux!=null)
        {
            if(ruta_aux.scene!=null)
            {
                iniciarVentana(ruta_aux);
            }
        }
        else
        {
            System.out.println("VENTANA NO CARGADA");
        }    
    }
    
    
    
    private static void iniciarVentana(RutaScene ruta)
    {
        stage_actual.setTitle(ruta.titulo_ventana);
        stage_actual.setWidth(ruta.ancho_ventana);
        stage_actual.setHeight(ruta.alto_ventana);
        
        ruta_actual=ruta;
        stage_actual.setScene(ruta.scene);
        stage_actual.show();
        
        iniciarAnimacionVentana(ruta.scene.getRoot());
    }
    
    /** Obtiene el controlador de la ventana ingresada por id
     * 
     * @return Controlador de la ventana
     */
    public static Object getControlador(String id)
    {
        RutaScene ruta_aux = rutas.get(id);
        if(ruta_aux!=null)
        {
            return ruta_aux.controlador;
        }
        return null;
    }
    
    
    public static void setVentanaInicial(String id)
    {
        ir(id);
    }
    
    public static void setVentanaInicial(String id,Object data)
    {
        ir(id,data);
    }
    
    
    private static void asignarVentana(RutaScene ruta,String id) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(Ventanas.class.getResource(ruta.direccion));
        Parent root = loader.load();
        
        
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
                stage_actual.setOpacity(1);
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage_actual.setX(event.getScreenX() - xOffset);
                stage_actual.setY(event.getScreenY() - yOffset);
                stage_actual.setOpacity(0.7);
            }
        });
        
        root.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage_actual.setOpacity(1);
            }
        });
        
        
        
        Object controlador = loader.getController();
        Scene escena =new Scene(root);
        
        ruta.scene=escena;
        ruta.controlador=controlador;
        
        rutas.put(id,ruta);
    }
    
    /** Agregar y define una ruta de archivo fxml
     * @param id: Identificar de ventana.
     * @param direccion: Ruta del archivo fxml de la ventana.
     * @param titulo_ventana: Titulo a mostrar.
     * @param alto_ventana: Alto de la ventana.
     * @param ancho_ventana: Ancho de la ventana.
     */
    public static void setVentana(String id,String direccion,String titulo_ventana,double ancho_ventana,double alto_ventana) throws IOException
    {
        RutaScene nueva_ruta = new RutaScene(direccion,titulo_ventana, ancho_ventana, alto_ventana);
        asignarVentana(nueva_ruta,id);
    }
    
    public static void setVentana(String id,String direccion) throws IOException
    {
        RutaScene nueva_ruta = new RutaScene(direccion);
        asignarVentana(nueva_ruta,id);
      
    }
    
    public static void setVentana(String id,String direccion,String titulo) throws IOException
    {
        RutaScene nueva_ruta = new RutaScene(direccion,titulo);
        asignarVentana(nueva_ruta,id);
    }
    
    public static void setVentana(String id,String direccion,double ancho_ventana,double alto_ventana) throws IOException
    {
        RutaScene nueva_ruta = new RutaScene(direccion, ancho_ventana, alto_ventana);
        asignarVentana(nueva_ruta,id);
    }
    
    public static void setAnimacionVentana(String tipoAnimacion) {
        tipo_animacion_ventana = tipoAnimacion;
    }
    
    public static void setAnimacionVentana(String tipoAnimacion, double duracion) {
        tipo_animacion_ventana = tipoAnimacion;
        duracion_animacion_ventana = duracion;
    }
    
    /*
        ANIMACION DE VENTANAS Y COMPONENTES VISUALES
    */
    
    public static void setAnimacionObjeto(String tipoAnimacion, Pane objeto)
    {
        tipo_animacion_objeto = tipoAnimacion;
        iniciarAnimacionObjeto(objeto);
    }
    
    public static void setAnimacionObjeto(String tipoAnimacion, Pane objeto,double duracion)
    {
        tipo_animacion_objeto = tipoAnimacion;
        duracion_animacion_objeto = duracion;
        iniciarAnimacionObjeto(objeto);
    }
    
    private static void iniciarAnimacionVentana(Parent node) {
        String tipo = (tipo_animacion_ventana != null) ? tipo_animacion_ventana.toLowerCase() : "";
        switch(tipo) {
            case "fade":
                Double fd = duracion_animacion_ventana != null ? duracion_animacion_ventana : FADE_ANIMATION_DURATION;
                FadeTransition ftCurrent = new FadeTransition(Duration.millis(fd), node);
                ftCurrent.setFromValue(0.0);
                ftCurrent.setToValue(1.0);
                ftCurrent.play();
                break;
            case "scale":
                Double sc = duracion_animacion_ventana != null ? duracion_animacion_ventana : SCALE_ANIMATION_DURATION;
                ScaleTransition scCurrent = new ScaleTransition(Duration.millis(sc),node);
                scCurrent.setToX(1.1);
                scCurrent.setToY(1.1);
                scCurrent.play();
                break;
            case "scale_repeat":
                Double sc_r = duracion_animacion_ventana != null ? duracion_animacion_ventana : SCALE_ANIMATION_DURATION;
                ScaleTransition sc_rCurrent = new ScaleTransition(Duration.millis(sc_r),node);
                sc_rCurrent.setToX(1.1);
                sc_rCurrent.setToY(1.1);
                sc_rCurrent.setAutoReverse(true);
                sc_rCurrent.setCycleCount(2);
                sc_rCurrent.play();
                break;
                
            default:
                break;
        }
    }
    
    private static void iniciarAnimacionObjeto(Parent node) {
        String tipo = (tipo_animacion_objeto != null) ? tipo_animacion_objeto.toLowerCase() : "";
        switch(tipo) {
            case "fade":
                Double fd = duracion_animacion_objeto != null ? duracion_animacion_objeto : FADE_ANIMATION_DURATION;
                FadeTransition ftCurrent = new FadeTransition(Duration.millis(fd), node);
                ftCurrent.setFromValue(0.0);
                ftCurrent.setToValue(1.0);
                ftCurrent.play();
                break;
            case "scale":
                Double sc = duracion_animacion_objeto != null ? duracion_animacion_objeto : SCALE_ANIMATION_DURATION;
                ScaleTransition scCurrent = new ScaleTransition(Duration.millis(sc),node);
                scCurrent.setToX(1.1);
                scCurrent.setToY(1.1);
                scCurrent.play();
                break;
            case "scale_repeat":
                Double sc_r = duracion_animacion_objeto != null ? duracion_animacion_objeto : SCALE_ANIMATION_DURATION;
                ScaleTransition sc_rCurrent = new ScaleTransition(Duration.millis(sc_r),node);
                sc_rCurrent.setToX(1.05);
                sc_rCurrent.setToY(1.05);
                sc_rCurrent.setAutoReverse(true);
                
                sc_rCurrent.setCycleCount(2);
                sc_rCurrent.play();
                break;
                
            default:
                break;
        }
    }

    
    /** Obtiene el dato actual almacenado
     * 
     * @return Ultimo dato traspasado
     */
    public static Object getData()    
    {
        return ruta_actual.data;
    }
    
    /*
    AGREGADAAAA
    */
    
    public static void setData(Object data)
    {
        ruta_actual.data=data;
    }
    
}