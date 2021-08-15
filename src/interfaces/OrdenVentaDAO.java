package interfaces;

import java.sql.Date;
import java.util.ArrayList;

import beans.OrdenVentaDTO;

public interface OrdenVentaDAO {
	public ArrayList<OrdenVentaDTO> listadoOrdenVentas();
	public int InsertNewOrdenVenta(String cod_OV, int cod_emp, String dni, Date Fecha, double Importe, String Id_cupon);
	public int InsertNewDetalleVenta(OrdenVentaDTO o);
	public ArrayList<OrdenVentaDTO> SearchVentaxCod(String id_OrdenV);
	public ArrayList<OrdenVentaDTO> SearchDetailsVentaCod(String id_OrdenV);
}
