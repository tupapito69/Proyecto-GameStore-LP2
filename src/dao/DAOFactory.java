package dao;

import interfaces.CargoDAO;
import interfaces.CategoriaDAO;
import interfaces.ClienteDAO;
import interfaces.ConsultaDAO;
import interfaces.CuponDAO;
import interfaces.EmpleadoDAO;
import interfaces.HistorialVentaDAO;
import interfaces.OrdenCreditoDAO;
import interfaces.OrdenVentaDAO;
import interfaces.ProductoDAO;
import interfaces.UsuarioDAO;
import interfaces.ConsultaDAO;

public abstract class DAOFactory {
	public static final int MYSQL = 1;
	public static final int SQL = 2;
	
	public abstract UsuarioDAO getUsuarioDAO();
	public abstract ProductoDAO getProductoDAO();
	public abstract ClienteDAO getClienteDAO();
	public abstract CategoriaDAO getCategoriaDAO();
	public abstract CargoDAO getCargoDAO();
	public abstract EmpleadoDAO getEmpleadoDAO();
	public abstract OrdenVentaDAO getOrdenVentaDAO();
	public abstract ConsultaDAO getConsultasDAO();
	public abstract CuponDAO getCuponDAO();
	public abstract OrdenCreditoDAO getOrdenCreditoDAO();
	public abstract HistorialVentaDAO getHistorialVentaDAO();
	
	public static DAOFactory getDAOFactory(int qBD) {
		switch (qBD) {
		case MYSQL:
			return new MySQLDAOFactory();
		// otros
		default:
			return null;
		}
	}
}
