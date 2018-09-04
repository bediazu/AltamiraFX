package controladores;

import java.io.FileNotFoundException;

import org.controlsfx.control.Notifications;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import principal.Altamira;
import reportes.ClientesReporte;
import reportes.Ficheros;
import ventanas.Ventanas;

public class InformesControlador {
	
	@FXML
    void expClientesPDF(MouseEvent event) {
		Altamira altamira = (Altamira)Ventanas.getData();
    	
	
    	ClientesReporte cr = new ClientesReporte();
    	for(int i=0;i<altamira.getClientes().size();i++)
    	{
    		cr.add(altamira.getClientes().get(i));
    	}
    	//Se cargan 4 pero se muestra 1
    	
    	
        String path = "C:\\Users\\Bruno Díaz\\eclipse-workspace\\AltamiraFX\\src\\reportes\\Reportes_Clientes.jasper"; //Ponemos la localizacion del reporte creado
        
            try {
            	JasperPrint jprint = JasperFillManager.fillReport(path, null, cr); //Agregamos los parametros para llenar el reporte
	            JasperViewer.viewReport(jprint,false);
	            //viewer.setVisible(true); //Se vizualiza el reporte
	        
			} catch (JRException e) {
				System.out.println("Error en la manipulacion del archivo PDF");
			} //Se carga el reporte de su localizacion
            //viewer.setDefaultCloseOperation(1); // Se declara con dispose_on_close para que no se cierre el programa cuando se cierre el reporte
            
    }
	
	@FXML
    void expClientesCSV(MouseEvent event) {
		try {
			Ficheros.guardarDatosCSV();
			Notifications.create().title("Archivo de Clientes CSV creado con exito").text("Clientes.csv guardado").darkStyle().showInformation();
    		
		} catch (FileNotFoundException e) {
			Notifications.create().title("Error al guardar archivo CSV").text("Error en la escritura del archivo").darkStyle().showError();
		}
    }
	
	
	@FXML
    void expReservasCSV(MouseEvent event) {
		try {
			Ficheros.guardarDatosCSV();
			Notifications.create().title("Archivo de Reservas CSV creado con exito").text("Reservas.csv guardado").darkStyle().showInformation();
    		
		} catch (FileNotFoundException e) {
			Notifications.create().title("Error al guardar archivo CSV").text("Error en la escritura del archivo").darkStyle().showError();
		}
    }
	

}
