package beans;

import java.util.Date;

public class OrdenVentaDTO extends OrdenVentaDetalleDTO{
	public String cod_ordenVenta, dni_cliente, id_cupon;
	public int cod_empleado;
	public Date fecha_ordenventa;
	public double importe_total;
	public double importe_cupon;



	public double getImporte_cupon() {
		return importe_cupon;
	}
	public void setImporte_cupon(double importe_cupon) {
		this.importe_cupon = importe_cupon;
	}
	public String getCod_ordenVenta() {
		return cod_ordenVenta;
	}
	public void setCod_ordenVenta(String cod_ordenVenta) {
		this.cod_ordenVenta = cod_ordenVenta;
	}
	public String getDni_cliente() {
		return dni_cliente;
	}
	public void setDni_cliente(String dni_cliente) {
		this.dni_cliente = dni_cliente;
	}
	public String getId_cupon() {
		return id_cupon;
	}
	public void setId_cupon(String id_cupon) {
		this.id_cupon = id_cupon;
	}
	public int getCod_empleado() {
		return cod_empleado;
	}
	public void setCod_empleado(int cod_empleado) {
		this.cod_empleado = cod_empleado;
	}
	public Date getFecha_ordenventa() {
		return fecha_ordenventa;
	}
	public void setFecha_ordenventa(Date fecha_ordenventa) {
		this.fecha_ordenventa = fecha_ordenventa;
	}
	public double getImporte_total() {
		return importe_total;
	}
	public void setImporte_total(double importe_total) {
		this.importe_total = importe_total;
	}
	
}
