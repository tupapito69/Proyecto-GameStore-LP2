package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.CategoriaDTO;
import interfaces.CategoriaDAO;
import utils.MySQLConexion8;

public class MySQLCategoriaDAO implements CategoriaDAO {

	@Override
	public ArrayList<CategoriaDTO> listadoCategorias() {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		ArrayList<CategoriaDTO> listaCategoria=new ArrayList<CategoriaDTO>();
		try {
			con=MySQLConexion8.getConexion();
			String consulta="SELECT * FROM categoria";
			pst=con.prepareStatement(consulta);
			rs=pst.executeQuery();
			while(rs.next()){
				CategoriaDTO objCategoria=new CategoriaDTO();
				objCategoria.setCod_cat(rs.getString(1));;
				objCategoria.setNom_categoria(rs.getString(2));
				listaCategoria.add(objCategoria);
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro al cargar lista de categorias desde la BD >> "+ e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return listaCategoria;
	}

}
