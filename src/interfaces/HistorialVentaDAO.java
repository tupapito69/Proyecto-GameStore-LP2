package interfaces;

import java.util.ArrayList;

import beans.HistorialVentaDTO;

public interface HistorialVentaDAO {
	public ArrayList<HistorialVentaDTO> ListarHistorialFecha(String fecha1,String fecha2);
	public ArrayList<HistorialVentaDTO> ListarHistorialEmpleado(int id_empleado);
	public ArrayList<HistorialVentaDTO> ListarHistorialCliente(String dni_cliente);
}
