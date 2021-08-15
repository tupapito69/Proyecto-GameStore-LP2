package interfaces;

import java.util.ArrayList;

import beans.ProductoDTO;

public interface ProductoDAO {
	public ArrayList<ProductoDTO> listadoProductos();
	public ArrayList<ProductoDTO> listadoProductos(String categ);
	public ArrayList<ProductoDTO> listadoProductos(String categ, String prod);
	public int ValidarStockProducto(String prod);
	public int ActualizarStock(String cod, int cant);
	public int modificarProducto(ProductoDTO c);
	public int eliminarProducto(String id);
	public int agregarProducto(ProductoDTO c);
	public String GenerarCodProducto();
	public ArrayList<ProductoDTO> buscarProducto(String cod);
}
