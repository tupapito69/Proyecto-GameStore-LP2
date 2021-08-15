package beans;

public class ProductoDTO extends CategoriaDTO {
	public String cod_producto, nom_producto;
	public double precio_producto;
	public int stock_producto;
	private boolean eliminado;
	
	
	public boolean getEliminado() {
		return eliminado;
	}
	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}
	public String getCod_producto() {
		return cod_producto;
	}
	public void setCod_producto(String cod_producto) {
		this.cod_producto = cod_producto;
	}
	public String getNom_producto() {
		return nom_producto;
	}
	public void setNom_producto(String nom_producto) {
		this.nom_producto = nom_producto;
	}
	public double getPrecio_producto() {
		return precio_producto;
	}
	public void setPrecio_producto(double precio_producto) {
		this.precio_producto = precio_producto;
	}
	public int getStock_producto() {
		return stock_producto;
	}
	public void setStock_producto(int stock_producto) {
		this.stock_producto = stock_producto;
	}
	
}
