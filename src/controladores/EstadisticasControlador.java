package controladores;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import modelo.Cliente;
import principal.Altamira;
import ventanas.Ventanas;


public class EstadisticasControlador implements Initializable {

	@FXML
    private Label lblSemanales;

    @FXML
    private Label lblMensuales;

    @FXML
    private Label lblAnuales;
    
    
    @FXML
    private LineChart<String, Integer> grafico;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		cargarGrafico();
		
		asignarTotal();
		
		asignarMaximoporCliente();
		
		asignarClientes();
		
	}
	
	@SuppressWarnings("unchecked")
	public void cargarGrafico()
	{
		Altamira altamira = (Altamira)Ventanas.getData();
		
		XYChart.Series<String,Integer> series = new XYChart.Series<>();
		
		for(Cliente c : altamira.getClientes())
		{
			series.getData().add(new XYChart.Data<String,Integer>(c.getNombre(),c.getReservas().size()));
		}
		grafico.getData().addAll(series);
	}
	
	public void asignarClientes()
	{
		Altamira altamira = (Altamira)Ventanas.getData();

		lblAnuales.setText(Integer.toString(altamira.getClientes().size()));
	}
	
	public void asignarMaximoporCliente()
	{
		Altamira altamira = (Altamira)Ventanas.getData();

		Cliente max = altamira.getClientes().get(0);
		if(max!=null)
		{
			for(Cliente c : altamira.getClientes())
			{
				if(c.getReservas().size() > max.getReservas().size())
				{
					max=c;
				}
			}
		}
		lblMensuales.setText(Integer.toString(max.getReservas().size()));
	}
	
	public void asignarTotal()
	{
		Altamira altamira = (Altamira)Ventanas.getData();
		
		int totalReservas = altamira.getReservas().size();
		lblSemanales.setText(Integer.toString(totalReservas));
	}
    
    
    
}
