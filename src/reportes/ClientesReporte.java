package reportes;

import java.util.ArrayList;

import modelo.Cliente;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class ClientesReporte implements JRDataSource {
	
	private ArrayList<Cliente>lista = new ArrayList<Cliente>();
	private int indice_actual = -1;

	@Override
	public Object getFieldValue(JRField arg0) throws JRException {
		Object valor = null;
		
		if(arg0.getName().equals("nombre"))
		{
			valor = lista.get(indice_actual).getNombre();
		}
		
		else if(arg0.getName().equals("telefono_contacto"))
		{
			valor = lista.get(indice_actual).getTelefono_contacto();
		}
		return valor;
	}

	public void add(Cliente c)
	{
		lista.add(c);
	}
	@Override
	public boolean next() throws JRException {
		return ++indice_actual < lista.size();
	}
	
}
