package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import beans.HistorialVentaDTO;
import interfaces.HistorialVentaDAO;
import utils.MySQLConexion8;

public class MySQLHistorialVentaDAO implements HistorialVentaDAO {

	@Override
	public ArrayList<HistorialVentaDTO> ListarHistorialFecha(String fecha1, String fecha2) {
		// TODO Auto-generated method stub
		ArrayList<HistorialVentaDTO> lista = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs=null;
		try {
			con =  MySQLConexion8.getConexion();
			String sql = "SELECT * FROM ORDEN_VENTA WHERE fecha_ordenventa >= ? AND fecha_ordenventa < ?";
			pst= con.prepareStatement(sql);
			pst.setString(1, fecha1);
			pst.setString(2, fecha2);
			rs= pst.executeQuery();

			lista = new ArrayList<HistorialVentaDTO>();
			while (rs.next()) {
				HistorialVentaDTO cs = new HistorialVentaDTO();
						cs.setCod_ordenventa(rs.getString(1));
						cs.setCod_empleado(rs.getInt(2));
						cs.setDni_cliente(rs.getString(3));
						cs.setFecha(rs.getDate(4));
						cs.setImporte_total(rs.getDouble(5));
						cs.setId_cupon(rs.getString(6));
						lista.add(cs);
						System.out.println("termino consulta");
			}
		} catch (Exception e) {
			System.out.println("Error al Listar Historial >> :" + e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return lista;
	}

	@Override
	public ArrayList<HistorialVentaDTO> ListarHistorialEmpleado(int id_empleado) {
		// TODO Auto-generated method stub
		ArrayList<HistorialVentaDTO> lista = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs=null;
		try {
			con =  MySQLConexion8.getConexion();
			String sql = "SELECT * FROM ORDEN_VENTA WHERE cod_empleado=?";
			pst= con.prepareStatement(sql);
			pst.setInt(1, id_empleado);
			rs= pst.executeQuery();

			lista = new ArrayList<HistorialVentaDTO>();
			while (rs.next()) {
				HistorialVentaDTO cs = new HistorialVentaDTO();
						cs.setCod_ordenventa(rs.getString(1));
						cs.setCod_empleado(rs.getInt(2));
						cs.setDni_cliente(rs.getString(3));
						cs.setFecha(rs.getDate(4));
						cs.setImporte_total(rs.getDouble(5));
						cs.setId_cupon(rs.getString(6));
						lista.add(cs);
						System.out.println("termino consulta");
			}
		} catch (Exception e) {
			System.out.println("Error al Listar Historial >> :" + e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return lista;
	}

	@Override
	public ArrayList<HistorialVentaDTO> ListarHistorialCliente(String dni_cliente) {
		// TODO Auto-generated method stub
		ArrayList<HistorialVentaDTO> lista = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs=null;
		try {
			con =  MySQLConexion8.getConexion();
			String sql = "SELECT * FROM ORDEN_VENTA WHERE dni_cliente=?";
			pst= con.prepareStatement(sql);
			pst.setString(1, dni_cliente);
			rs= pst.executeQuery();

			lista = new ArrayList<HistorialVentaDTO>();
			while (rs.next()) {
				HistorialVentaDTO cs = new HistorialVentaDTO();
						cs.setCod_ordenventa(rs.getString(1));
						cs.setCod_empleado(rs.getInt(2));
						cs.setDni_cliente(rs.getString(3));
						cs.setFecha(rs.getDate(4));
						cs.setImporte_total(rs.getDouble(5));
						cs.setId_cupon(rs.getString(6));
						lista.add(cs);
						System.out.println("termino consulta");
			}
		} catch (Exception e) {
			System.out.println("Error al Listar Historial >> :" + e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return lista;
	}

}
