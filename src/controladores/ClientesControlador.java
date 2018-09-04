
package controladores;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.Cliente;

import org.controlsfx.control.Notifications;
import org.controlsfx.control.table.TableRowExpanderColumn;
import principal.Altamira;

import ventanas.Ventanas;


public class ClientesControlador implements Initializable {
	
	/* ===============================================================
	* Atributos de la vista Clientes
	* ===============================================================*/
	
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtTelefonoContacto;
    @FXML
    private JFXTextField txtRut;
    @FXML
    private JFXTextField txtEdad;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private JFXButton btnActualizar;
    
	/* ===============================================================
	* Tabla Clientes
	* ===============================================================*/
	
    @FXML
    private TableView<Cliente> tablaClientes;
      
	/* ===============================================================
	* Columnas de la tabla clientes
	* ===============================================================*/
	
    @FXML
    private TableColumn<Cliente, String> colNombre;
    @FXML
    private TableColumn<Cliente, String> colTelefono;
    @FXML
    private TableColumn<Cliente,String> colRut;
    @FXML
    private TableColumn<Cliente, Integer> colEdad;
    
	/* ===============================================================
	* Lista para almacenar los cliente de forma temporal.
	* ===============================================================*/
	
    private ObservableList<Cliente> observableList;
    
    /**
     *  Metodo principal de inicializacion
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    		//Inicializacion de la lista temporal
            observableList = FXCollections.observableArrayList();
            
            //Inicializar la tabla de clientes
            iniciarTablaClientes();
  
            //Asignar la tabla de clientes con la lista creada
            tablaClientes.setItems(observableList);
            
            //Se crea la columna de edicion de clientes
            crearColumnaEdicion();
    }
    
    /**
     * Metodo que inicia la renderizacion de las celdas de la tabla Clientes
     */
    
    private void iniciarTablaClientes()
    {
    	//Cargar los datos iniciales (Clientes registrados en Altamira)
        //Se muestran en la tabla
        cargarDatosIniciales();
        
      //Inicializar el renderizado de las tablas de cliente
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono_contacto"));
        colRut.setCellValueFactory(new PropertyValueFactory<>("rut"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
    }
    
    /**
     * Crea la columna de edicion en la tabla de Clientes
     */
    private void crearColumnaEdicion()
    {
    	//Crear boton de edicion en tiempo de ejecucion
        TableRowExpanderColumn<Cliente> expander = new TableRowExpanderColumn<>(param -> {
            
        	//SE CREA EN CODIGO LOS CAMPOS DE EDICION DEL CLIENTE
            VBox cuadro_edicion = new VBox(4);
            HBox cuadro_botones = new HBox(4);
            Label label = new Label("Modificar datos del cliente");
            
            label.setUnderline(true);
            
            Label label_2 = new Label("Nombre Cliente: ");
            TextField txtNombre_ = new TextField(param.getValue().getNombre());
            
            Label label_3 = new Label("Telefono Cliente: ");
            TextField txtTelefono_ = new TextField(param.getValue().getTelefono_contacto());
            
            Label label_4 = new Label("Rut Cliente: ");
            TextField txtRut_ = new TextField(param.getValue().getRut());
            
            Label label_5 = new Label("Edad Cliente: ");
            TextField txtEdad_ = new TextField(Integer.toString(param.getValue().getEdad()));
            
            Separator separador = new Separator(Orientation.HORIZONTAL);
             
            /* ===============================================================
        	* FUNCION DE ELIMINAR UN CLIENTE AL PRESIONAR EL BOTON DE ELIMINACION.
        	* ===============================================================*/
            btnEliminar.setOnAction(event -> {

            	//Se crea un cuadro de confirmacion para el usuario
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Eliminar Cliente");
                alert.setHeaderText("Se eliminara el cliente "+param.getValue().getNombre());
                alert.setContentText("¿Desea eliminar?");

                Optional<ButtonType> result = alert.showAndWait();
                
                //Si el usuario confirma la confirmacion del cliente
                if (result.get() == ButtonType.OK){
                    
                    Altamira altamira = (Altamira)Ventanas.getData();
                    
                    Cliente cliente_seleccionado = param.getValue();
                    
                    //Se elimina de la tabla de clientes
                    tablaClientes.getItems().remove(cliente_seleccionado);
                    
                    //Se elimina de Altamira
                    altamira.eliminarCliente(txtTelefono_.getText());
                    
                    //Se actualiza la tabla con los otros clientes registrados
                    actualizarDatos(cliente_seleccionado);
                    
                    //Se crea una notificacion en pantalla confirmando que se elimino el cliente seleccionado.
                    Notifications.create().title("Cliente borrado").position(Pos.BOTTOM_RIGHT).text("Cliente borrado con exito")
                    .darkStyle()
                    
                    .showError();
                    
                } else {
                    //NADA, BOTON DE CANCELAR
                }
                
                //Se cierra el espacio de edicion
                param.toggleExpanded();
            }); 
            
            
            /* ===============================================================
        	* FUNCION DE ACTUALIZAR UN CLIENTE AL PRESIONAR EL BOTON DE ACTUALIZAR.
        	* ===============================================================*/
            
            btnActualizar.setOnAction(event -> {
            	
            	//Se crea un cuadro de confirmacion para el usuario
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Actualizar Cliente");
                alert.setHeaderText("Se modificara el cliente "+param.getValue().getNombre());
                alert.setContentText("¿Desea actualizar?");
                
              //Si el usuario confirma la confirmacion del cliente
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    
                    Altamira altamira = (Altamira)Ventanas.getData();
                    
                    Cliente cliente_seleccionado = param.getValue();
                    
                    //Se elimina el cliente actual de la tabla, para luego insertar el cliente modificado
                    tablaClientes.getItems().remove(cliente_seleccionado);
                    
                    //Se crea un nuevo cliente, con los datos ingresados por el usuario
                    //Si el usuario no modifica algun valor, estos quedan sin cambio
                    Cliente nuevo_cliente = new Cliente(txtNombre_.getText(), txtTelefono_.getText(), txtRut_.getText(),Integer.parseInt(txtEdad_.getText()));
                    
                    //Se llama al metodo de actualizacion del cliente
                    altamira.editarCliente(cliente_seleccionado,nuevo_cliente);
                    
                    //Se crea una notificacion el usuario para confirmar que se actualizaron los datos
                    Notifications.create().title("Cliente editado").position(Pos.BOTTOM_RIGHT)
                    .text("Cliente "+cliente_seleccionado.getNombre()+" editado con exito\n"+
                        "Nuevo Telefono: "+nuevo_cliente.getTelefono_contacto()+"\n"+
                        "Nuevo Rut: "+nuevo_cliente.getRut()+"\n"+
                        "Nueva Edad: "+nuevo_cliente.getEdad())
                    .darkStyle()
                    
                    .showInformation();
                    
                    //Se agrega a la tabla el cliente con los datos actualizados
                    tablaClientes.getItems().add(nuevo_cliente);
                    
                } else {
                    //NADA, BOTON DE CANCELAR
                }
                param.toggleExpanded();
                
            });
            
            //Hacer visible los botones de edicion y eliminacion que estaban ocultos para luego ser agregados
            //al area de edicion de clientes
            btnActualizar.setVisible(true);
            btnEliminar.setVisible(true);
                  
            //Agregar los componentes creados en tiempo de ejecucion a la columna de edicion de un cliente
            cuadro_botones.getChildren().addAll(btnActualizar,btnEliminar);
            cuadro_edicion.getChildren().addAll(label,label_2,txtNombre_,
                                                label_3,txtTelefono_,
                                                label_4,txtRut_,
                                                label_5,txtEdad_,
                                                separador,cuadro_botones);
               
            return cuadro_edicion;
         });
        
        expander.setText("Editar");
        //Agregar la columna de edicion a la tabla de clientes.
        tablaClientes.getColumns().add(expander);
        
    }

    /** 
     * Actualizar los datos de la lista de clientes (Utilizada para cargar los clientes en la tabla)
     * @param cliente
     */
     public void actualizarDatos(Cliente cliente)
     {
    	 //Se elimina el cliente de la lista
         observableList.remove(cliente);
         
         //Se actualiza la tabla, se tenia problemas por algun tema del buffer.
         //Por tanto se actualizado luego de la eliminacion
         tablaClientes.refresh();
         
         Altamira altamira = (Altamira) Ventanas.getData();
         
         //Se recorren los clientes de la lista temporal
         for(Cliente cliente_aux : observableList)
         {
        	 //Se muestran los nombres por pantalla
             System.out.println("NOMBRES o:  "+cliente_aux.getNombre());
         }
         
         for(Cliente cliente_aux : altamira.getClientes() )
         {
             System.out.println("NOMBRES:  "+cliente_aux.getNombre());
             
         }
         
         tablaClientes.refresh();

     }
     
     /**
      * Boton de agregar un cliente a Altamira al ser presionado
      * @param event Evento click
      */
    @FXML
    private void agregarCliente(MouseEvent event) {
        String nombre,telefono,rut;
        int edad;
        
        //Las validaciones correspondientes para poder crear un cliente sin datos vacios
        if(txtNombre.getText()!="")
        {
            if(txtTelefonoContacto.getText()!="")
            {
                nombre=txtNombre.getText();
                telefono=txtTelefonoContacto.getText();
                rut=txtRut.getText();
                
                if(!txtEdad.getText().equals(""))
                {
                    edad=Integer.parseInt(txtEdad.getText());
                }
                else
                {
                    edad=0;
                }
                
                //Se crea un cliente con los datos ingresados por el usuario
                Cliente nuevo_cliente = new Cliente(nombre,telefono,rut,edad);
                
                Altamira altamira = (Altamira)Ventanas.getData();
                if(altamira!=null)
                {
                    //Se agrega el cliente a Altamira 
                    altamira.agregarCliente(nuevo_cliente);
                    System.out.println("Cliente agregado");

                    //Muestra los clientes por consola
                    altamira.mostrarClientes();
                }

                	//Se crea una notificacion al usuario confirmando la creacion del usuario
                	//Se muestran los datos almacenados
                    Notifications.create().title("Cliente creado")
                    .position(Pos.BOTTOM_RIGHT)
                    .text("Cliente "+nuevo_cliente.getNombre()+" creado con exito\n"+
                            "Telefono: "+nuevo_cliente.getTelefono_contacto()+"\n"+
                            "Rut: "+nuevo_cliente.getRut()+"\n"+
                            "Edad: "+nuevo_cliente.getEdad())
                    .darkStyle()
                        
                    .showInformation();

                //Se dejan los campos en blanco para un nuevo usuario a crear
                limpiarCampos(null);
                
                //Se agrega el cliente creado a la tabla de clientes
                tablaClientes.getItems().add(nuevo_cliente);
                
            }
        } 
    }
    
    /**
     * Limpiar los campos del formulario de creacion de un nuevo cliente
     * @param event
     */
    @FXML
    private void limpiarCampos(MouseEvent event) {
    	
    	//Solo asignar los campos vacios y en null
        tablaClientes.setItems(observableList);
        txtNombre.clear();
        txtTelefonoContacto.clear();
        txtRut.clear();
        txtEdad.clear();
    }
    
    /**
     * Cargar los clientes a la lista temporal utilizada en la tabla de clientes
     * Los clientes fueron cargados desde el archivo CSV previamente
     */
    private void cargarDatosIniciales() {
        Altamira altamira = (Altamira)Ventanas.getData();
        
        for(Cliente cliente_actual : altamira.getClientes())
        {
            observableList.add(cliente_actual);
        }
    }
    
    
    
}
