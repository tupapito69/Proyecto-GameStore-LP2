package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.ClienteDTO;
import interfaces.ClienteDAO;
import utils.MySQLConexion8;

public class MySQLClienteDAO implements ClienteDAO {

	@Override
	public ArrayList<ClienteDTO> listadoClientes() {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		ArrayList<ClienteDTO> listaCliente=new ArrayList<ClienteDTO>();
		try {
			con=MySQLConexion8.getConexion();
			String consulta="SELECT * FROM cliente";
			pst=con.prepareStatement(consulta);
			rs=pst.executeQuery();
			while(rs.next()){
				ClienteDTO objCliente=new ClienteDTO();
				objCliente.setDNI(rs.getString(1));;
				objCliente.setNom_cliente(rs.getString(2));
				objCliente.setApaterno(rs.getString(3));
				objCliente.setAmaterno(rs.getString(4));
				objCliente.setTelefono(rs.getString(5));
				listaCliente.add(objCliente);
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro al cargar lista de clientes desde la BD >> "+ e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return listaCliente;
	}

	@Override
	public int InsertNewClient(ClienteDTO c) {
		// TODO Auto-generated method stub
		int Rpta=0;
		Connection con=null;
		PreparedStatement pst=null;
		try {
			con=MySQLConexion8.getConexion();
			String consulta="insert into CLIENTE (DNI,nom_cliente,ape1_cliente,ape2_cliente,tlf_cliente) values(?,?,?,?,?)";
			pst=con.prepareStatement(consulta);
			pst.setString(1, c.getDNI());
			pst.setString(2, c.getNom_cliente());
			pst.setString(3, c.getApaterno());
			pst.setString(4, c.getAmaterno());
			pst.setString(5, c.getTelefono());
			Rpta=pst.executeUpdate();
			pst.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro al registrar un nuevo Cliente en la BD >> "+ e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return Rpta;
	}

	@Override
	public ArrayList<ClienteDTO> SearchClient(String dni) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		ArrayList<ClienteDTO> listaCliente=new ArrayList<ClienteDTO>();
		try {
			con=MySQLConexion8.getConexion();
			String consulta="SELECT * FROM cliente where DNI=?";
			pst=con.prepareStatement(consulta);
			pst.setString(1, dni);
			rs=pst.executeQuery();
			while(rs.next()){
				ClienteDTO objCliente=new ClienteDTO();
				objCliente.setDNI(rs.getString(1));;
				objCliente.setNom_cliente(rs.getString(2));
				objCliente.setApaterno(rs.getString(3));
				objCliente.setAmaterno(rs.getString(4));
				objCliente.setTelefono(rs.getString(5));
				listaCliente.add(objCliente);
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro al cargar datos de un cliente desde la BD >> "+ e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return listaCliente;
	}

	@Override
	public int UpdateClient(ClienteDTO c) {
		// TODO Auto-generated method stub
		int Rpta=0;
		Connection con=null;
		PreparedStatement pst=null;
		try {
			con=MySQLConexion8.getConexion();
			String consulta="update CLIENTE set nom_cliente=? ,ape1_cliente=?,ape2_cliente=?,tlf_cliente=? where DNI=?";
			pst=con.prepareStatement(consulta);
			pst.setString(1, c.getNom_cliente());
			pst.setString(2, c.getApaterno());
			pst.setString(3, c.getAmaterno());
			pst.setString(4, c.getTelefono());
			pst.setString(5, c.getDNI());
			Rpta=pst.executeUpdate();
			pst.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro al actualizar un Cliente en la BD >> "+ e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return Rpta;
	}

}
