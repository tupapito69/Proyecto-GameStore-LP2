package beans;

public class OrdenCreditoDetalleDTO {
	private String cod_pro;
	private int Cantidad_v;
	private double precio_det;
	
	public String getCod_pro() {
		return cod_pro;
	}
	public void setCod_pro(String cod_pro) {
		this.cod_pro = cod_pro;
	}
	public int getCantidad_v() {
		return Cantidad_v;
	}
	public void setCantidad_v(int cantidad_v) {
		Cantidad_v = cantidad_v;
	}
	public double getPrecio_det() {
		return precio_det;
	}
	public void setPrecio_det(double precio_det) {
		this.precio_det = precio_det;
	}
}
