package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import beans.CargoDTO;
import beans.LoginDTO;
import interfaces.UsuarioDAO;
import utils.MySQLConexion8;

public class MySQLUsuarioDAO implements UsuarioDAO {

	@Override
	public boolean login(CargoDTO c) {
		// TODO Auto-generated method stub
		boolean login=false;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			String consulta="SELECT L.usuario, L.contraseña, L.cod_empleado, E.nom_empleado, E.ape1_empleado, E.ape2_empleado, C.cod_cargo FROM login as L" +
					" INNER JOIN empleado as E ON E.cod_empleado=L.cod_empleado " +
					" INNER JOIN cargo as C ON C.cod_cargo=E.cod_cargo WHERE L.usuario=?";
			con=MySQLConexion8.getConexion();
			ps=con.prepareStatement(consulta); 
			ps.setString(1, c.getUsuario());
			rs=ps.executeQuery();
			if(rs.next()){
				if(c.getContrasenia().equals(rs.getString(2))){
					c.setUsuario(rs.getString(1));
					c.setContrasenia(rs.getString(2));
					c.setCod_empleado(rs.getInt(3));
					c.setNom_empleado(rs.getString(4));
					c.setApaterno(rs.getString(5));
					c.setAmaterno(rs.getString(6));
					c.setCod_cargo(rs.getString(7));
					login= true;
				}
				else{
					login= false;
				}
			}
		} catch (SQLException e) {
			Logger.getLogger(MySQLUsuarioDAO.class.getName()).log(Level.SEVERE, null,e);
		} finally {
			MySQLConexion8.closeConexion(con);
		}
		return login;
	}
	
	@Override
	public boolean cambioContraseña(LoginDTO c) {
		// TODO Auto-generated method stub
		boolean putPass=false;
		int rpta=0;
		Connection con=null;
		PreparedStatement ps=null;
		try {
			String consulta="UPDATE login set contraseña=? where usuario=?";
			con=MySQLConexion8.getConexion();
			ps=con.prepareStatement(consulta); 
			ps.setString(1, c.getContrasenia());
			ps.setString(2, c.getUsuario());
			rpta=ps.executeUpdate();
			if(rpta==1) {
				putPass=true;
			}else {
				putPass=false;
			}
		} catch (SQLException e) {
			Logger.getLogger(MySQLUsuarioDAO.class.getName()).log(Level.SEVERE, null,e);
		} finally {
			MySQLConexion8.closeConexion(con);
		}
		return putPass;
	}
	
	
}
