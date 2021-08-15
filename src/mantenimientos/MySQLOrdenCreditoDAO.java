package mantenimientos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.CuponDTO;
import beans.OrdenCreditoDTO;
import interfaces.OrdenCreditoDAO;
import utils.MySQLConexion8;

public class MySQLOrdenCreditoDAO implements OrdenCreditoDAO {

	@Override
	public ArrayList<OrdenCreditoDTO> listAllOrdenCredito() {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		ArrayList<OrdenCreditoDTO> listaOC=new ArrayList<OrdenCreditoDTO>();
		try {
			con=MySQLConexion8.getConexion();
			String consulta="SELECT * FROM ORDEN_CREDITO";
			pst=con.prepareStatement(consulta);
			rs=pst.executeQuery();
			while(rs.next()){
				OrdenCreditoDTO objOC=new OrdenCreditoDTO();
				objOC.setCod_credito(rs.getString(1));
				objOC.setCod_venta(rs.getString(2));
				objOC.setCod_emple(rs.getInt(3));
				objOC.setDni_cliente(rs.getString(4));
				objOC.setFechacredito(rs.getDate(5));
				objOC.setImporte_total(rs.getDouble(6));
				objOC.setId_cupon(rs.getString(7));
				listaOC.add(objOC);
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro al cargar lista de Cupones desde la BD >> "+ e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return listaOC;
	}

	@Override
	public int InsertNewOrdenCredito(OrdenCreditoDTO o) {
		// TODO Auto-generated method stub
		int Rpta=0;
		Connection con=null;
		PreparedStatement pst=null;
		try {
			con=MySQLConexion8.getConexion();
			String consulta="INSERT INTO ORDEN_CREDITO (cod_ordenCredito, cod_ordenVenta, cod_empleado, dni_cliente, fecha_ordencredito, importe_total,id_cupon) VALUES (?,?,?,?,?,?,?)";
			pst=con.prepareStatement(consulta);
			pst.setString(1, o.getCod_credito());
			pst.setString(2, o.getCod_venta());
			pst.setInt(3, o.getCod_emple());
			pst.setString(4, o.getDni_cliente());
			pst.setDate(5, (Date) o.getFechacredito());
			pst.setDouble(6, o.getImporte_total());
			pst.setString(7, o.getId_cupon());
			Rpta=pst.executeUpdate();
			pst.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("error al registrar una nueva Orden de Credito en la BD >> "+ e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return Rpta;
	}

	@Override
	public int InsertDetailsOredenCredito(OrdenCreditoDTO o) {
		// TODO Auto-generated method stub
		int Rpta=0;
		Connection con=null;
		PreparedStatement pst=null;
		try {
			con=MySQLConexion8.getConexion();
			String consulta="INSERT INTO DETALLE_ORDENCREDITO (cod_ordenCredito, cod_producto, cantidad_venta, precio_detalleventa) VALUES (?,?,?,?)";
			pst=con.prepareStatement(consulta);
			pst.setString(1, o.getCod_credito());
			pst.setString(2, o.getCod_pro());
			pst.setInt(3, o.getCantidad_v());
			pst.setDouble(4, o.getPrecio_det());
			Rpta=pst.executeUpdate();
			pst.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("error al registrar el detalle de una Orden de Credito en la BD >> "+ e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return Rpta;
	}

	@Override
	public ArrayList<OrdenCreditoDTO> listOrdenCreditoxOV(String OV) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		ArrayList<OrdenCreditoDTO> listaOC=new ArrayList<OrdenCreditoDTO>();
		try {
			con=MySQLConexion8.getConexion();
			String consulta="select * from ORDEN_CREDITO where cod_ordenVenta=?";
			pst=con.prepareStatement(consulta);
			pst.setString(1, OV);
			rs=pst.executeQuery();
			while(rs.next()){
				OrdenCreditoDTO objOC=new OrdenCreditoDTO();
				objOC.setCod_credito(rs.getString(1));
				objOC.setCod_venta(rs.getString(2));
				objOC.setCod_emple(rs.getInt(3));
				objOC.setDni_cliente(rs.getString(4));
				objOC.setFechacredito(rs.getDate(5));
				objOC.setImporte_total(rs.getDouble(6));
				objOC.setId_cupon(rs.getString(7));
				listaOC.add(objOC);
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro al cargar lista de Cupones desde la BD >> "+ e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return listaOC;
	}

}
