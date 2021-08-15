package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.w3c.dom.ls.LSInput;

import beans.ConsultasDTO;
import beans.OrdenVentaDTO;
import interfaces.ConsultaDAO;
import utils.MySQLConexion8;

public class MySQLConsultaDAO implements ConsultaDAO{


	@Override
	public ArrayList<OrdenVentaDTO> listarConsultaVenta(String DNI) {

		ArrayList<OrdenVentaDTO> lista = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs=null;
		try {
			con =  MySQLConexion8.getConexion();
			String sql = "SELECT * FROM orden_venta where dni_cliente=? order by 4 asc";
			pst= con.prepareStatement(sql);
			pst.setString(1, DNI);
			rs= pst.executeQuery();
				
			lista = new ArrayList<OrdenVentaDTO>();
			while (rs.next()) {
				OrdenVentaDTO cs = new OrdenVentaDTO();
				cs.setCod_ordenVenta(rs.getString(1));
				cs.setCod_empleado(rs.getInt(2));
				cs.setDni_cliente(rs.getString(3));
				cs.setFecha_ordenventa(rs.getDate(4));
				cs.setImporte_total(rs.getDouble(5));
				cs.setId_cupon(rs.getString(6));
				lista.add(cs);
				System.out.println("termino consulta");
			}
		} catch (Exception e) {
			System.out.println("Error al Listar Consulta >> :" + e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return lista;
	}

	@Override
	public ArrayList<ConsultasDTO> listarConsultaClientes(String DNI) {
		// TODO Auto-generated method stub
		ArrayList<ConsultasDTO> lista = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs=null;
		try {
			con =  MySQLConexion8.getConexion();
			String sql = "select * from cliente where DNI=?";
			pst= con.prepareStatement(sql);
			pst.setString(1, DNI);
			rs= pst.executeQuery();

			lista = new ArrayList<ConsultasDTO>();
			while (rs.next()) {
						ConsultasDTO cs = new ConsultasDTO();
						cs.setDni(rs.getString(1));
						cs.setNomCli(rs.getString(2));
						cs.setApeCli(rs.getString(3));
						cs.setApeCli2(rs.getString(4));
						cs.setTelefono(rs.getString(5));
						lista.add(cs);
						System.out.println("termino consulta");
			}
		} catch (Exception e) {
			System.out.println("Error al Listar Consulta >> :" + e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return lista;
	}

	@Override
	public ArrayList<OrdenVentaDTO> listarDetallesVenta(String idventa) {
		// TODO Auto-generated method stub
		ArrayList<OrdenVentaDTO> lista = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs=null;
		try {
			con =  MySQLConexion8.getConexion();
			String sql = "SELECT d.cod_producto,d.cantidad_venta,d.precio_detalleventa FROM orden_venta o inner join detalle_ordenventa d on o.cod_ordenVenta=d.cod_ordenVenta where o.cod_ordenVenta=?";
			pst= con.prepareStatement(sql);
			pst.setString(1, idventa);
			rs= pst.executeQuery();
			lista = new ArrayList<OrdenVentaDTO>();
			while (rs.next()) {
				OrdenVentaDTO cs = new OrdenVentaDTO();
				cs.setCod_producto(rs.getString(1));
				cs.setCantidad_venta(rs.getInt(2));
				cs.setPrecio_detalleventa(rs.getDouble(3));
				lista.add(cs);
				System.out.println("termino consulta " + lista);
			}
		} catch (Exception e) {
			System.out.println("Error al Listar Consulta >> :" + e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return lista;
	}

	@Override
	public ArrayList<OrdenVentaDTO> SearchCuponVenta(String idVenta) {
		// TODO Auto-generated method stub
		ArrayList<OrdenVentaDTO> lista = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs=null;
		try {
			con =  MySQLConexion8.getConexion();
			String sql = "SELECT c.ID, c.importe from cupones c join orden_venta o on c.ID=o.id_cupon where o.cod_ordenVenta=?";
			pst= con.prepareStatement(sql);
			pst.setString(1, idVenta);
			rs= pst.executeQuery();
			lista = new ArrayList<OrdenVentaDTO>();
			while (rs.next()) {
				OrdenVentaDTO cs = new OrdenVentaDTO();
				cs.setId_cupon(rs.getString(1));
				cs.setImporte_cupon(rs.getInt(2));
				lista.add(cs);
				System.out.println("termino consulta " + lista);
			}
		} catch (Exception e) {
			System.out.println("Error al Listar Consulta >> :" + e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return lista;
	}

}
