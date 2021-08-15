package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.CargoDTO;
import beans.EmpleadoDTO;
import interfaces.EmpleadoDAO;
import utils.MySQLConexion8;

public class MySQLEmpleadoDAO implements EmpleadoDAO {

	@Override
	public ArrayList<EmpleadoDTO> listadoEmpleadosSupervisor() {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		ArrayList<EmpleadoDTO> listaEmpleadoSupervisor=new ArrayList<EmpleadoDTO>();
		try {
			con=MySQLConexion8.getConexion();
			String consulta="SELECT cod_empleado, cod_empleado_sup, CONCAT(nom_empleado,' ',ape1_empleado) AS Nombre FROM EMPLEADO WHERE cod_cargo = 'A01'";
			pst=con.prepareStatement(consulta);
			rs=pst.executeQuery();
			while(rs.next()){
				EmpleadoDTO objEmpleadoSupervisor=new EmpleadoDTO();
				objEmpleadoSupervisor.setCod_empleado(rs.getInt(1));
				objEmpleadoSupervisor.setCod_emple_superv(rs.getInt(2));
				objEmpleadoSupervisor.setNom_empleado(rs.getString("Nombre"));
				listaEmpleadoSupervisor.add(objEmpleadoSupervisor);
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro al cargar lista de supervisores desde la BD >> "+ e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return listaEmpleadoSupervisor;
	}

	@Override
	public ArrayList<CargoDTO> listadoEmpleados() {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		ArrayList<CargoDTO> listaEmpleado=new ArrayList<CargoDTO>();
		try {
			con=MySQLConexion8.getConexion();
			String consulta="SELECT e.cod_empleado, e.nom_empleado, e.ape1_empleado, e.ape2_empleado, "
					+ "e.correo_empleado, e.tlf_empleado, e.cod_empleado_sup, e.cod_cargo, c.nombre "
					+ "FROM empleado e inner join cargo c on e.cod_cargo=c.cod_cargo";
			pst=con.prepareStatement(consulta);
			rs=pst.executeQuery();
			while(rs.next()){
				CargoDTO objEmpleado=new CargoDTO();
				objEmpleado.setCod_empleado(rs.getInt(1));
				objEmpleado.setNom_empleado(rs.getString(2));
				objEmpleado.setApaterno(rs.getString(3));
				objEmpleado.setAmaterno(rs.getString(4));
				objEmpleado.setEmail(rs.getString(5));
				objEmpleado.setTelefono(rs.getString(6));
				objEmpleado.setCod_emple_superv(rs.getInt(7));
				objEmpleado.setCod_cargo(rs.getString(8));
				objEmpleado.setNombre(rs.getString(9));
				listaEmpleado.add(objEmpleado);
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro al cargar lista de Empleados desde la BD >> "+ e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return listaEmpleado;
	}

	@Override
	public int InsertNewEmpleado(CargoDTO c) {
		// TODO Auto-generated method stub
		int Rpta=0;
		int RptaOK=0;
		Connection con=null;
		PreparedStatement pst=null;
		try {
			con=MySQLConexion8.getConexion();
			String consulta="insert into EMPLEADO (cod_empleado,nom_empleado,ape1_empleado,ape2_empleado,correo_empleado,tlf_empleado,cod_empleado_sup,cod_cargo) values(NULL,?,?,?,?,?,?,?)";
			pst=con.prepareStatement(consulta);
			pst.setString(1, c.getNom_empleado());
			pst.setString(2, c.getApaterno());
			pst.setString(3, c.getAmaterno());
			pst.setString(4, c.getEmail());
			pst.setString(5, c.getTelefono());
			pst.setInt(6, c.getCod_emple_superv());
			pst.setString(7, c.getCod_cargo());
			Rpta=pst.executeUpdate();
			if(Rpta==1) {
				Connection cnx=null;
				PreparedStatement ps=null;
				try {
					cnx=MySQLConexion8.getConexion();
					String Query="insert into login (usuario,cod_empleado) values(?,(select max(cod_empleado) from empleado))";
					ps=cnx.prepareStatement(Query);
					ps.setString(1, c.getUsuario());
					RptaOK=ps.executeUpdate();
					ps.close();
					cnx.close();
				}catch (Exception e) {
					// TODO: handle exception
					System.out.println("erro al registrar un nuevo Usuario en la BD >> "+ e.getMessage());
				}
			}
			pst.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro al registrar un nuevo Empleado en la BD >> "+ e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return RptaOK;
	}

	@Override
	public int UpdateEmpleado(CargoDTO c) {
		// TODO Auto-generated method stub
		int Rpta=0;
		Connection con=null;
		PreparedStatement pst=null;
		try {
			con=MySQLConexion8.getConexion();
			String consulta="update empleado set nom_empleado=?, ape1_empleado=?, ape2_empleado=?, correo_empleado=?, tlf_empleado=?, cod_empleado_sup=?, cod_cargo=? where cod_empleado=?";
			pst=con.prepareStatement(consulta);
			pst.setString(1, c.getNom_empleado());
			pst.setString(2, c.getApaterno());
			pst.setString(3, c.getAmaterno());
			pst.setString(4, c.getEmail());
			pst.setString(5, c.getTelefono());
			pst.setInt(6, c.getCod_emple_superv());
			pst.setString(7, c.getCod_cargo());
			pst.setInt(8, c.getCod_empleado());
			Rpta=pst.executeUpdate();
			pst.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro al registrar un nuevo Empleado en la BD >> "+ e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return Rpta;
	}

	@Override
	public ArrayList<CargoDTO> searchEmpleados(int codEmple) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		ArrayList<CargoDTO> listaEmpleado=new ArrayList<CargoDTO>();
		try {
			con=MySQLConexion8.getConexion();
			String consulta="SELECT e.cod_empleado, e.nom_empleado, e.ape1_empleado, e.ape2_empleado, "
					+ "e.correo_empleado, e.tlf_empleado, e.cod_empleado_sup, e.cod_cargo, l.usuario "
					+ "FROM empleado e inner join cargo c on e.cod_cargo=c.cod_cargo "
					+ "inner join login l on e.cod_empleado=l.cod_empleado where e.cod_empleado=?";
			pst=con.prepareStatement(consulta);
			pst.setInt(1, codEmple);
			rs=pst.executeQuery();
			while(rs.next()){
				CargoDTO objEmpleado=new CargoDTO();
				objEmpleado.setCod_empleado(rs.getInt(1));
				objEmpleado.setNom_empleado(rs.getString(2));
				objEmpleado.setApaterno(rs.getString(3));
				objEmpleado.setAmaterno(rs.getString(4));
				objEmpleado.setEmail(rs.getString(5));
				objEmpleado.setTelefono(rs.getString(6));
				objEmpleado.setCod_emple_superv(rs.getInt(7));
				objEmpleado.setCod_cargo(rs.getString(8));
				objEmpleado.setUsuario(rs.getString(9));
				listaEmpleado.add(objEmpleado);
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro al cargar lista de Empleados desde la BD >> "+ e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return listaEmpleado;
	}

}
