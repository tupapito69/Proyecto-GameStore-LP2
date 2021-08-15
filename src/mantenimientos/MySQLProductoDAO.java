package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.ProductoDTO;
import interfaces.ProductoDAO;
import utils.MySQLConexion8;

public class MySQLProductoDAO implements ProductoDAO {

	@Override
	public ArrayList<ProductoDTO> listadoProductos() {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		ArrayList<ProductoDTO> listaProducto=new ArrayList<ProductoDTO>();
		try {
			con=MySQLConexion8.getConexion();
			String consulta="SELECT p.cod_producto, p.nom_producto, p.precio_producto, p.stock_producto, c.cod_cat, c.nom_categoria, p.eliminar "
							+ " FROM producto p join categoria c on p.cod_cat=c.cod_cat where p.eliminar=1 order by 1";
			pst=con.prepareStatement(consulta);
			rs=pst.executeQuery();
			while(rs.next()){
				ProductoDTO objProducto=new ProductoDTO();
				objProducto.setCod_producto(rs.getString(1));;
				objProducto.setNom_producto(rs.getString(2));
				objProducto.setPrecio_producto(rs.getDouble(3));
				objProducto.setStock_producto(rs.getInt(4));
				objProducto.setCod_cat(rs.getString(5));
				objProducto.setNom_categoria(rs.getString(6));
				objProducto.setEliminado(rs.getBoolean(7));
				listaProducto.add(objProducto);
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro al cargar lista de productos desde la BD >> "+ e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return listaProducto;
	}

	@Override
	public ArrayList<ProductoDTO> listadoProductos(String categ) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		ArrayList<ProductoDTO> listaProducto=new ArrayList<ProductoDTO>();
		try {
			con=MySQLConexion8.getConexion();
			String consulta="select * from producto where cod_cat=? and (stock_producto>1) and eliminar=1 ORDER by 2 asc";
			pst=con.prepareStatement(consulta);
			pst.setString(1, categ);
			rs=pst.executeQuery();
			while(rs.next()){
				ProductoDTO objProducto=new ProductoDTO();
				objProducto.setCod_producto(rs.getString(1));;
				objProducto.setNom_producto(rs.getString(2));
				objProducto.setPrecio_producto(rs.getDouble(3));
				objProducto.setStock_producto(rs.getInt(4));
				objProducto.setCod_cat(rs.getString(5));
				objProducto.setEliminado(rs.getBoolean(6));
				listaProducto.add(objProducto);
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro al cargar lista de productos desde la BD >> "+ e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return listaProducto;
	}

	@Override
	public ArrayList<ProductoDTO> listadoProductos(String categ, String prod) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		ArrayList<ProductoDTO> listaProducto=new ArrayList<ProductoDTO>();
		try {
			con=MySQLConexion8.getConexion();
			String consulta="SELECT * FROM PRODUCTO WHERE cod_cat=? and cod_producto=? and eliminar=1";
			pst=con.prepareStatement(consulta);
			pst.setString(1, categ);
			pst.setString(2, prod);
			rs=pst.executeQuery();
			while(rs.next()){
				ProductoDTO objProducto=new ProductoDTO();
				objProducto.setCod_producto(rs.getString(1));;
				objProducto.setNom_producto(rs.getString(2));
				objProducto.setPrecio_producto(rs.getDouble(3));
				objProducto.setStock_producto(rs.getInt(4));
				objProducto.setCod_cat(rs.getString(5));
				objProducto.setEliminado(rs.getBoolean(6));
				listaProducto.add(objProducto);
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro al cargar lista de productos desde la BD >> "+ e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return listaProducto;
	}

	@Override
	public int ValidarStockProducto(String prod) {
		// TODO Auto-generated method stub
		int Rpta=0;
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			con=MySQLConexion8.getConexion();
			String consulta="select stock_producto from producto where cod_producto=?";
			pst=con.prepareStatement(consulta);
			pst.setString(1, prod);
			rs=pst.executeQuery();
			while(rs.next()){
				Rpta=rs.getInt(1);
			}
			pst.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro al cargar el stock de la BD >> "+ e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return Rpta;
	}

	@Override
	public int ActualizarStock(String cod, int cant) {
		// TODO Auto-generated method stub
		int Rpta=0;
		Connection con=null;
		PreparedStatement pst=null;
		try {
			con=MySQLConexion8.getConexion();
			String consulta="UPDATE producto SET stock_producto=stock_producto-? WHERE cod_producto=?";
			pst=con.prepareStatement(consulta);
			pst.setInt(1, cant);
			pst.setString(2, cod);
			Rpta=pst.executeUpdate();
			pst.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro al cargar el stock de la BD >> "+ e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return Rpta;
	}

	@Override
	public int modificarProducto(ProductoDTO c) {
		// TODO Auto-generated method stub
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con =  MySQLConexion8.getConexion();
			String sql = "update producto set nom_producto=?,precio_producto=?,stock_producto=?,cod_cat=? where cod_producto=?";
			pst= con.prepareStatement(sql);
			pst.setString(1, c.getNom_producto());
			pst.setDouble(2, c.getPrecio_producto());
			pst.setInt(3, c.getStock_producto());
			pst.setString(4, c.getCod_cat());
			pst.setString(5, c.getCod_producto());
			rs= pst.executeUpdate();
			System.out.println("datos subidos a la bd");
			pst.close();
			con.close();
		} catch (Exception e) {
			System.out.println("Error al modificar :" + e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return rs;
	}

	@Override
	public int eliminarProducto(String id) {
		// TODO Auto-generated method stub
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con =  MySQLConexion8.getConexion();
			String sql = "update producto set eliminar=0 where cod_producto=?";
			pst= con.prepareStatement(sql);
			pst.setString(1, id);
			rs= pst.executeUpdate();
			pst.close();
			con.close();
		} catch (Exception e) {
			System.out.println("Error al eliminar producto :" + e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return rs;
	}

	@Override
	public int agregarProducto(ProductoDTO d) {
		// TODO Auto-generated method stub
		int rs = 0;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con =  MySQLConexion8.getConexion();
			String sql = "insert into producto values(?,?,?,?,?,1)";
			pst= con.prepareStatement(sql);
			pst.setString(1, d.getCod_producto());
			pst.setString(2, d.getNom_producto());
			pst.setDouble(3, d.getPrecio_producto());
			pst.setInt(4, d.getStock_producto());
			pst.setString(5, d.getCod_cat());
			rs= pst.executeUpdate();
			pst.close();
			con.close();
		} catch (Exception e) {
			System.out.println("Error al agregar un nuevo producto :" + e.getMessage());
		}finally {
			MySQLConexion8.closeConexion(con);
		}
		return rs;
	}

	@Override
	public String GenerarCodProducto() {
		// TODO Auto-generated method stub
		String codigo = "P0001";
        Connection con = null; 
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = MySQLConexion8.getConexion();
            String sql = "select substr(max(cod_producto),2) from producto";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                codigo = String.format("P%04d", rs.getInt(1)+1);
            }
        } catch (Exception e) {
            System.out.println("Error en genera cod Producto\n" + e.getMessage());
        }finally {
			MySQLConexion8.closeConexion(con);
		}
        return codigo;
	}

	@Override
	public ArrayList<ProductoDTO> buscarProducto(String cod) {
		// TODO Auto-generated method stub
		 Connection con=null;
	     PreparedStatement pst=null;
	     ResultSet rs=null;
	     ArrayList<ProductoDTO> listPro =new ArrayList<ProductoDTO>();
	     try {
	         con=MySQLConexion8.getConexion();
	         String consulta="SELECT * from producto where cod_producto = ? and eliminar=1";
	         pst=con.prepareStatement(consulta);
	         pst.setString(1, cod);
	         rs=pst.executeQuery();
	         while(rs.next()){
	         	ProductoDTO pro=new ProductoDTO();
	             pro.setCod_producto(rs.getString(1));
	             pro.setNom_producto(rs.getString(2));
	             pro.setPrecio_producto(rs.getDouble(3));
	             pro.setStock_producto(rs.getInt(4));
	             pro.setCod_cat(rs.getString(5));
	             pro.setEliminado(rs.getBoolean(6));
	             listPro.add(pro);
	         }
	     }catch (Exception e) {
	         // TODO: handle exception
	         System.out.println("erro al cargar lista de productos segun su codigo >> "+ e.getMessage());
	     }finally {
	         MySQLConexion8.closeConexion(con);
	     }
	     return listPro;
	}

}
