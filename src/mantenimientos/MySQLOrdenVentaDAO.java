package mantenimientos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.OrdenVentaDTO;
import interfaces.OrdenVentaDAO;
import utils.MySQLConexion8;

public class MySQLOrdenVentaDAO implements OrdenVentaDAO {

	@Override
	public ArrayList<OrdenVentaDTO> listadoOrdenVentas() {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		ArrayList<OrdenVentaDTO> listaOrdenVenta=new ArrayList<OrdenVentaDTO>();
		try {
			con=MySQLConexion8.getConexion();
			String consulta="SELECT * FROM orden_venta order by 1";
			pst=con.prepareStatement(consulta);
			rs=pst.executeQuery();
			while(rs.next()){
				OrdenVentaDTO objOrdenVenta=new OrdenVentaDTO();
				objOrdenVenta.setCod_ordenVenta(rs.getString(1));
				objOrdenVenta.setCod_empleado(rs.getInt(2));
				objOrdenVenta.setDni_cliente(rs.getString(3));
				objOrdenVenta.setFecha_ordenventa(rs.getDate(4));
				objOrdenVenta.setImporte_total(rs.getDouble(5));
				objOrdenVenta.setId_cupon(rs.getString(6));
				listaOrdenVenta.add(objOrdenVenta);
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro al cargar lista de OrdenVenta desde la BD >> "+ e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return listaOrdenVenta;
	}

	@Override
	public int InsertNewOrdenVenta(String cod_OV, int cod_emp, String dni, Date Fecha, double Importe, String Id_cupon) {
		// TODO Auto-generated method stub
		int Rpta=0;
		Connection con=null;
		PreparedStatement pst=null;
		try {
			con=MySQLConexion8.getConexion();
			String consulta="INSERT INTO ORDEN_VENTA (cod_ordenVenta, cod_empleado, dni_cliente, fecha_ordenventa, importe_total,id_cupon) "
					+ " VALUES (?,?,?,?,?,?)";
			pst=con.prepareStatement(consulta);
			pst.setString(1, cod_OV);
			pst.setInt(2, cod_emp);
			pst.setString(3, dni);
			pst.setDate(4, Fecha);
			pst.setDouble(5, Importe);
			pst.setString(6, Id_cupon);
			Rpta=pst.executeUpdate();
			pst.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro al registrar una nueva Orden de Venta en la BD >> "+ e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return Rpta;
	}

	@Override
	public int InsertNewDetalleVenta(OrdenVentaDTO o) {
		// TODO Auto-generated method stub
		int Rpta=0;
		Connection con=null;
		PreparedStatement pst=null;
		try {
			con=MySQLConexion8.getConexion();
			String consulta="INSERT INTO DETALLE_ORDENVENTA (cod_ordenVenta, cod_producto, cantidad_venta, precio_detalleventa) VALUES (?,?,?,?)";
			pst=con.prepareStatement(consulta);
			pst.setString(1, o.getCod_ordenVenta());
			pst.setString(2, o.getCod_producto());
			pst.setInt(3, o.getCantidad_venta());
			pst.setDouble(4, o.getPrecio_detalleventa());
			Rpta=pst.executeUpdate();
			pst.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro al registrar un nuevo detalle de una Orden de Venta en la BD >> "+ e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return Rpta;
	}

	@Override
	public ArrayList<OrdenVentaDTO> SearchVentaxCod(String id_OrdenV) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		ArrayList<OrdenVentaDTO> listaOrdenVenta=new ArrayList<OrdenVentaDTO>();
		try {
			con=MySQLConexion8.getConexion();
			String consulta="select * from ORDEN_VENTA where cod_ordenVenta=?";
			pst=con.prepareStatement(consulta);
			pst.setString(1, id_OrdenV);
			rs=pst.executeQuery();
			while(rs.next()){
				OrdenVentaDTO objOrdenVenta=new OrdenVentaDTO();
				objOrdenVenta.setCod_ordenVenta(rs.getString(1));
				objOrdenVenta.setCod_empleado(rs.getInt(2));
				objOrdenVenta.setDni_cliente(rs.getString(3));
				objOrdenVenta.setFecha_ordenventa(rs.getDate(4));
				objOrdenVenta.setImporte_total(rs.getDouble(5));
				objOrdenVenta.setId_cupon(rs.getString(6));
				listaOrdenVenta.add(objOrdenVenta);
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro al cargar lista de OrdenVenta por codigo desde la BD >> "+ e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return listaOrdenVenta;
	}

	@Override
	public ArrayList<OrdenVentaDTO> SearchDetailsVentaCod(String id_OrdenV) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		ArrayList<OrdenVentaDTO> listaOrdenVenta=new ArrayList<OrdenVentaDTO>();
		try {
			con=MySQLConexion8.getConexion();
			String consulta="select * from DETALLE_ORDENVENTA where cod_ordenVenta=? order by 2 asc";
			pst=con.prepareStatement(consulta);
			pst.setString(1, id_OrdenV);
			rs=pst.executeQuery();
			while(rs.next()){
				OrdenVentaDTO objOrdenVenta=new OrdenVentaDTO();
				objOrdenVenta.setCod_ordenVenta(rs.getString(1));
				objOrdenVenta.setCod_producto(rs.getString(2));
				objOrdenVenta.setCantidad_venta(rs.getInt(3));
				objOrdenVenta.setPrecio_detalleventa(rs.getDouble(4));
				listaOrdenVenta.add(objOrdenVenta);
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro al cargar lista de OrdenVenta por codigo desde la BD >> "+ e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return listaOrdenVenta;
	}

}
