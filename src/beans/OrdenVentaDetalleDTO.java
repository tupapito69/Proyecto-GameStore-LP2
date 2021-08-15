package beans;

public class OrdenVentaDetalleDTO {
	private String cod_producto ;
	private int cantidad_venta;
	private double precio_detalleventa;
	
	public String getCod_producto() {
		return cod_producto;
	}
	public void setCod_producto(String cod_producto) {
		this.cod_producto = cod_producto;
	}
	public int getCantidad_venta() {
		return cantidad_venta;
	}
	public void setCantidad_venta(int cantidad_venta) {
		this.cantidad_venta = cantidad_venta;
	}
	public double getPrecio_detalleventa() {
		return precio_detalleventa;
	}
	public void setPrecio_detalleventa(double precio_detalleventa) {
		this.precio_detalleventa = precio_detalleventa;
	}
	
}
