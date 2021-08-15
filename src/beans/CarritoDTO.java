package beans;

import com.google.gson.annotations.SerializedName;

public class CarritoDTO {
	@SerializedName("codigo")
	public String codigo;
	@SerializedName("cantidad")
	public int cantidad;
	@SerializedName("precio")
	public double precio;
	@SerializedName("Total")
	public double Total;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public double getTotal() {
		return Total;
	}
	public void setTotal(double total) {
		Total = total;
	}
	
}
