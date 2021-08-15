package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.CargoDTO;
import interfaces.CargoDAO;
import utils.MySQLConexion8;

public class MySQLCargoDAO implements CargoDAO {

	@Override
	public ArrayList<CargoDTO> listadoCargos() {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		ArrayList<CargoDTO> listaCargo=new ArrayList<CargoDTO>();
		try {
			con=MySQLConexion8.getConexion();
			String consulta="SELECT * FROM cargo";
			pst=con.prepareStatement(consulta);
			rs=pst.executeQuery();
			while(rs.next()){
				CargoDTO objCargo=new CargoDTO();
				objCargo.setCod_cargo(rs.getString(1));;
				objCargo.setNombre(rs.getString(2));
				listaCargo.add(objCargo);
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro al cargar lista de cargos desde la BD >> "+ e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return listaCargo;
	}

}
