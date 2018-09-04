package controladores;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.controls.JFXToggleButton;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;
import org.controlsfx.control.table.TableRowExpanderColumn;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.Cliente;
import modelo.Reserva;
import principal.Altamira;
import ventanas.Ventanas;

public class ReservasControlador implements Initializable {

	/* ===============================================================
   	* Atributos de la vista Reservas
   	* ===============================================================*/

	/* ===============================================================
   	* Componentes del formulario para crear una reserva
   	* ===============================================================*/
    @FXML
    private JFXTextField txtCliente;
    @FXML
    private JFXTextField txtComensales;
    @FXML
    private JFXComboBox<String> cbxNumeroMesa;
    @FXML
    private JFXTextField txtComentario;
    @FXML
    private JFXDatePicker pickerFecha;
    @FXML
    private JFXTimePicker pickerHora;
    @FXML
    private JFXToggleButton tglMedioPago;
    
    private Cliente cliente_seleccionado_tabla;
	
    /* ===============================================================
   	* Componentes para el manejo de la tabla visual que muestra los clientes
   	* ===============================================================*/
    @FXML
    private TableView<Cliente> tablaClientes;
    @FXML
    private TableColumn<Cliente, String> colNombre;
    @FXML
    private TableColumn<Cliente, String> colTelefono;
    @FXML
    private TableColumn<Cliente, String> colRut;
    @FXML
    private TableColumn<Cliente, Integer> colEdad;

    /* ===============================================================
   	* Listas temporales para cargarlas en las tablas correspondientes
   	* Clintes y Reservas
   	* ===============================================================*/
    private ObservableList<Cliente> listaClientes;
    private ObservableList<Reserva> listaReservas;
    
    /* ===============================================================
   	* Componentes para el manejo de la tabla visual que muestra las reservas
   	* Las reservas mostradas son exclusivas del cliente seleccionado
   	* ===============================================================*/
    @FXML
    private TableView<Reserva> tablaReservas;
    @FXML
    private TableColumn<Reserva, Integer> colComensales;
    @FXML
    private TableColumn<Reserva, String> colFecha;
    @FXML
    private TableColumn<Reserva, String> colHora;
    @FXML
    private TableColumn<Reserva, String> colMesa;
    @FXML
    private TableColumn<Reserva, Boolean> colTipo;
    @FXML
    private TableColumn<Reserva, String> colComentario;
    
    /* ===============================================================
   	* Botones de mantenimiento para una reserva creados en tiempo de ejecucion
   	* ===============================================================*/
    @FXML
    private JFXButton btnEliminar;

    @FXML
    private JFXButton btnActualizar;
    
    
    /**
     * Metodo para iniciar la renderizacion de las celdas de la tabla Clientes
     */
    private void iniciarTablaClientes()
    {
    	//Renderizar las columnas de la tabla Clientes.
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono_contacto"));
        colRut.setCellValueFactory(new PropertyValueFactory<>("rut"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
    }
    
    /**
     * Metodo para iniciar la renderizacion de las celdas de la tabla Reservas
     */
    private void iniciarTablaReservas()
    {
    	//Renderizar las columnas de la tabla Reservas.
        colComensales.setCellValueFactory(new PropertyValueFactory<>("comensales"));
        colMesa.setCellValueFactory(new PropertyValueFactory<>("numero_mesa"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colMesa.setCellValueFactory(new PropertyValueFactory<>("numero_mesa"));
        colComentario.setCellValueFactory(new PropertyValueFactory<>("comentario"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("pago_efectivo"));
        
    }


    /**
     * Metodo inicial de la ventana de Reservas
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	//Crear las mesas del recinto
    	//Las mesas de momento seran 50, se especificara mas adelante, si necesitan alguna especificacion
    	//De momento el trabajo de las mesas sera fijo y no modificarin el flujo natural del proyecto
    	//en caso de ser cambiadas
    	for(int i=1;i<=50;i++)
    	{
    		cbxNumeroMesa.getItems().add("Mesa "+i);
    	}
    	
    	//Colocar el seleccionador de hora en formato de 24 horas
        pickerHora.setIs24HourView(true);
        
        //Instanciar las listas temporales usadas en las tablas visuales Clientes y Reservas
        listaClientes = FXCollections.observableArrayList();
        listaReservas = FXCollections.observableArrayList();
        
        //Cargar los datos iniciales para mostrarlos por tabla (Clientes)
        cargarDatosIniciales();
        
        //Iniciar los componentes de la tabla Clientes (Columnas y celdas)
        iniciarTablaClientes();

        //Asignar la lista temporal de clientes a la tabla visual clientes
        tablaClientes.setItems(listaClientes);
        
        //Iniciar los componentes de la tabla Reservas
        iniciarTablaReservas();

        //Agregar el evento de seleccion de un cliente en tiempo de ejecuc
        tablaClientes.getSelectionModel().selectedItemProperty().addListener((ChangeListener<? super Cliente>) (observable, oldVal, newVal) -> {
		    
        	//newVal : Corresponde al valor seleccionado en la tabla
        	//oldVal: Corresponde al anterior valor seleccionado en la tabla
        	Cliente cliente_seleccionado = (Cliente)newVal;
		    cliente_seleccionado_tabla = cliente_seleccionado;
		                  
		    txtCliente.setText(cliente_seleccionado.getNombre());
		                    
		    //La asignacion de reservas a mostrar en la siguiente tabla
		    cargarDatosReserva(cliente_seleccionado);
		    tablaReservas.setItems(listaReservas);              
		});
        
      //Crear boton de edicion en tiempo de ejecucion
        TableRowExpanderColumn<Reserva> expander = new TableRowExpanderColumn<>(param -> {
            VBox editor = new VBox(4);
            Label lblTitulo = new Label("Editar Reserva:");
            
            Label lblComensales = new Label("Comensales:");
            TextField txtComensales = new TextField(Integer.toString(param.getValue().getComensales()));
            
            Label lblFecha = new Label("Fecha:");
            TextField txtFecha = new TextField(param.getValue().getFecha().toString());
            
            Label lblHora = new Label("Hora:");
            TextField txtHora = new TextField("Hora");
            
            Label lblMesa = new Label("Mesa:");
            TextField txtMesa = new TextField(param.getValue().getNumero_mesa());
            
            Label lblComentario = new Label("Comentario:");
            TextField txtComentario =  new TextField(param.getValue().getComentario());
            
            Label lblEditar = new Label("Editar Reserva:");
            HBox botones = new HBox(10);
            
            Button actualizarReserva = new Button("Actualizar Reserva");
            
            //Nico
            //Se debe eliminar con el dato de la reserva, no con el objeto reserva obtenido desde la tabla.
            Button eliminarReserva = new Button("Eliminar Reserva");
            
            
            actualizarReserva.setOnAction(event ->{
            	 Alert alert = new Alert(AlertType.CONFIRMATION);
                 alert.setTitle("Actualizar Reserva");
                 alert.setHeaderText("Se modificara la reserva ");
                 alert.setContentText("¿Desea actualizar?");
                 
                 Optional<ButtonType> result = alert.showAndWait();
                 if (result.get() == ButtonType.OK){
                     
                     Altamira altamira = (Altamira)Ventanas.getData();
                     
                     Reserva reserva_asociada = param.getValue();
                     
                     //Se elimina el cliente actual de la tabla, para luego insertar el cliente modificado
                     tablaReservas.getItems().remove(reserva_asociada);
                     
                     //Se crea un nuevo cliente, con los datos ingresados por el usuario
                     //Si el usuario no modifica algun valor, estos quedan sin cambio
                     
                     Reserva nueva_reserva = new Reserva(reserva_asociada.getId(),
                    		 							reserva_asociada.getCliente_asociado(),
                    		 							Integer.parseInt(txtComensales.getText()),
                    		 							txtMesa.getText(), 
                    		 							txtComentario.getText(),
                    		 							reserva_asociada.getFecha(),
                    		 							reserva_asociada.getHora(),
                    		 							reserva_asociada.isPago_efectivo()) ;
                     
                     altamira.editarReserva(reserva_asociada,nueva_reserva);
                     
                     
                     tablaReservas.getItems().add(nueva_reserva);
                 }
            });
            
            eliminarReserva.setOnAction(event->{
            	Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Eliminar Reserva");
                alert.setHeaderText("Se eliminara la reserva ");
                alert.setContentText("¿Desea eliminar?");
                
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    
                    Altamira altamira = (Altamira)Ventanas.getData();
                    
                    Reserva reserva_asociada = param.getValue();
                    
                    //Se elimina el cliente actual de la tabla, para luego insertar el cliente modificado
                    tablaReservas.getItems().remove(reserva_asociada);
                    
                    //Se crea un nuevo cliente, con los datos ingresados por el usuario
                    //Si el usuario no modifica algun valor, estos quedan sin cambio
                    
                    altamira.eliminarReserva(reserva_asociada);
                    
                }
                
            });
            
            editor.getChildren().add(lblTitulo);
            
            editor.getChildren().add(lblComensales);
            editor.getChildren().add(txtComensales);
            
            editor.getChildren().add(lblFecha);
            editor.getChildren().add(txtFecha);
            
            
            editor.getChildren().add(lblHora);
            editor.getChildren().add(txtHora);
            
            editor.getChildren().add(lblMesa);
            editor.getChildren().add(txtMesa);
            
            editor.getChildren().add(lblComentario);
            editor.getChildren().add(txtComentario);
            
            editor.getChildren().add(lblEditar);
            botones.getChildren().add(actualizarReserva);
            botones.getChildren().add(eliminarReserva);
            
            editor.getChildren().add(botones);
            
            
            
            return editor;
        });
        
        expander.setText("Editar Reserva");
        tablaReservas.getColumns().add(expander);
    }    
   
    /**
     * Metodo para cargar las reservas de un cliente en especifico
     * @param cliente Cliente a buscar sus reservas
     */
    private void cargarDatosReserva(Cliente cliente) {
    	
    	listaReservas.clear();
    	for(Reserva reserva_actual : cliente.getReservas())
    	{
    		listaReservas.add(reserva_actual);
    	}
    }
    
    /**
     * Metodo para cargar los valores iniciales del sistema
     */
    private void cargarDatosIniciales() {
        Altamira altamira = (Altamira)Ventanas.getData();
        
        for(Cliente cliente_actual : altamira.getClientes())
        {
            listaClientes.add(cliente_actual);
        }
    }

    /**
     * Metodo para agregar una nueva reserva a Altamira
     * @param event Evento Click
     */
    @FXML
    private void agregarReserva(MouseEvent event) {
        System.out.println(pickerHora.getEditor().getText());
        
        Altamira altamira = (Altamira)Ventanas.getData();
        Cliente cliente_asociado = cliente_seleccionado_tabla;
        
        int comensales=0;
        
        if(!txtComensales.getText().equals(""))
        {
            comensales = Integer.parseInt(txtComensales.getText());
        }
        
        String comentario = txtComentario.getText();
        
        LocalDate fecha_seleccionada = pickerFecha.getValue();
        
        String mesa_seleccionada = cbxNumeroMesa.getValue();
        
        boolean medio_pago_seleccionado = tglMedioPago.isSelected();
        
        Reserva nueva_reserva = new Reserva(0,cliente_asociado,comensales,mesa_seleccionada,comentario,fecha_seleccionada,null,medio_pago_seleccionado);
        altamira.agregarReserva(nueva_reserva);
        
        //Se crea una notificacion al usuario confirmando la creacion de una nueva reserva
    	//Se muestran los datos almacenados
        Notifications.create().title("Nueva Reserva").position(Pos.BOTTOM_RIGHT)
        .text("Reserva creada con exito!\n"+
        "Cliente Asociado: "+cliente_asociado.getNombre()+"\n"+
        "Fecha: "+fecha_seleccionada.toString()+"\n"+
        "Comentario: "+comentario+"\n"+
        "Efectivo: "+medio_pago_seleccionado)
        .darkStyle()
        
        .showInformation();
        
        cargarDatosReserva(cliente_asociado);
        
        limpiarCampos(null);
            
    }
    
    /**
     * Metodo para limpiar campos, es llamado desde otros metodos
     * @param event Evento click
     */

    @FXML
    private void limpiarCampos(MouseEvent event) {
        txtCliente.clear();
        cbxNumeroMesa.setValue(null);
        pickerFecha.setValue(null);
        tglMedioPago.setSelected(false);
        pickerHora.setValue(null);
        txtComensales.clear();
        txtComentario.clear();
        
    }
    
}
