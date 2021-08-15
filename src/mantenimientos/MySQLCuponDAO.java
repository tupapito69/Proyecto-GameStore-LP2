package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.CargoDTO;
import beans.CuponDTO;
import interfaces.CuponDAO;
import utils.MySQLConexion8;

public class MySQLCuponDAO implements CuponDAO {

	@Override
	public ArrayList<CuponDTO> SearchCupon(String ID) {
		// TODO Auto-generated method stub
		ArrayList<CuponDTO> lista = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs=null;
		try {
			con =  MySQLConexion8.getConexion();
			String consulta = "select * from CUPONES where ID=? AND cant_usos>=1";
			pst= con.prepareStatement(consulta);
			pst.setString(1, ID);
			rs= pst.executeQuery();
				
			lista = new ArrayList<CuponDTO>();
			while (rs.next()) {
				CuponDTO cs = new CuponDTO();
				cs.setID(rs.getString(1));
				cs.setCant_usos(rs.getInt(2));
				cs.setImporte(rs.getDouble(3));
				lista.add(cs);
			}
		} catch (Exception e) {
			System.out.println("Error al Listar un cupon por id >> :" + e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return lista;
	}

	@Override
	public int modificarCantUsos(String ID) {
		// TODO Auto-generated method stub
		int Rpta=0;
		Connection con=null;
		PreparedStatement pst=null;
		try {
			con=MySQLConexion8.getConexion();
			String consulta="UPDATE CUPONES SET cant_usos=cant_usos-1 WHERE ID=?";
			pst=con.prepareStatement(consulta);
			pst.setString(1, ID);
			Rpta=pst.executeUpdate();
			pst.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro al actualizar la cantidad de usos de un cupon de la BD >> "+ e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return Rpta;
	}

	@Override
	public int InsertNewCuponDescuento(CuponDTO c, int emple) {
		// TODO Auto-generated method stub
		int Rpta=0;
		Connection con=null;
		PreparedStatement pst=null;
		try {
			con=MySQLConexion8.getConexion();
			String consulta="insert into cupones (ID,cant_usos,importe,cod_empleado) values(?,?,?,?)";
			pst=con.prepareStatement(consulta);
			pst.setString(1, c.getID());
			pst.setInt(2, c.getCant_usos());
			pst.setDouble(3, c.getImporte());
			pst.setInt(4, emple);
			Rpta=pst.executeUpdate();
			pst.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("error al registrar un nuevo Cupón en la BD >> "+ e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return Rpta;
	}

	@Override
	public ArrayList<CuponDTO> ListAllCupones() {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		ArrayList<CuponDTO> listaCupon=new ArrayList<CuponDTO>();
		try {
			con=MySQLConexion8.getConexion();
			String consulta="SELECT * FROM CUPONES";
			pst=con.prepareStatement(consulta);
			rs=pst.executeQuery();
			while(rs.next()){
				CuponDTO objCupon=new CuponDTO();
				objCupon.setID(rs.getString(1));
				objCupon.setCant_usos(rs.getInt(2));
				objCupon.setImporte(rs.getDouble(3));
				listaCupon.add(objCupon);
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro al cargar lista de Cupones desde la BD >> "+ e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return listaCupon;
	}
	
}
