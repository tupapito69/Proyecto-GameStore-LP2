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
import mantenimientos.MySQLCargoDAO;
import mantenimientos.MySQLCategoriaDAO;
import mantenimientos.MySQLClienteDAO;
import mantenimientos.MySQLConsultaDAO;
import mantenimientos.MySQLCuponDAO;
import mantenimientos.MySQLEmpleadoDAO;
import mantenimientos.MySQLHistorialVentaDAO;
import mantenimientos.MySQLOrdenCreditoDAO;
import mantenimientos.MySQLOrdenVentaDAO;
import mantenimientos.MySQLProductoDAO;
import mantenimientos.MySQLUsuarioDAO;

public class MySQLDAOFactory extends DAOFactory {

	@Override
	public UsuarioDAO getUsuarioDAO() {
		// TODO Auto-generated method stub
		return new MySQLUsuarioDAO();
	}

	@Override
	public ProductoDAO getProductoDAO() {
		// TODO Auto-generated method stub
		return new MySQLProductoDAO();
	}

	@Override
	public ClienteDAO getClienteDAO() {
		// TODO Auto-generated method stub
		return new MySQLClienteDAO();
	}

	@Override
	public CategoriaDAO getCategoriaDAO() {
		// TODO Auto-generated method stub
		return new MySQLCategoriaDAO();
	}

	@Override
	public CargoDAO getCargoDAO() {
		// TODO Auto-generated method stub
		return new MySQLCargoDAO();
	}

	@Override
	public EmpleadoDAO getEmpleadoDAO() {
		// TODO Auto-generated method stub
		return new MySQLEmpleadoDAO();
	}

	@Override
	public OrdenVentaDAO getOrdenVentaDAO() {
		// TODO Auto-generated method stub
		return new MySQLOrdenVentaDAO();
	}

	@Override
	public ConsultaDAO getConsultasDAO() {
		// TODO Auto-generated method stub
		return new MySQLConsultaDAO();
	}

	@Override
	public CuponDAO getCuponDAO() {
		// TODO Auto-generated method stub
		return new MySQLCuponDAO();
	}

	@Override
	public OrdenCreditoDAO getOrdenCreditoDAO() {
		// TODO Auto-generated method stub
		return new MySQLOrdenCreditoDAO();
	}

	@Override
	public HistorialVentaDAO getHistorialVentaDAO() {
		// TODO Auto-generated method stub
		return new MySQLHistorialVentaDAO();
	}

}
