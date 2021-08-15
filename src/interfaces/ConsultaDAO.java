package interfaces;

import java.util.ArrayList;

import beans.ConsultasDTO;
import beans.CuponDTO;
import beans.OrdenVentaDTO;

public interface ConsultaDAO {
	public ArrayList<OrdenVentaDTO> listarConsultaVenta(String DNI);
	public ArrayList<ConsultasDTO> listarConsultaClientes(String DNI);
	public ArrayList<OrdenVentaDTO> listarDetallesVenta(String idventa);
	public ArrayList<OrdenVentaDTO> SearchCuponVenta(String idVenta);
}
