package beans;

import java.util.Date;

public class HistorialVentaDTO {
	private String cod_ordenventa,dni_cliente,id_cupon;
	private int cod_empleado;
	private double importe_total;
	private Date fecha;
	public String getCod_ordenventa() {
		return cod_ordenventa;
	}
	public void setCod_ordenventa(String cod_ordenventa) {
		this.cod_ordenventa = cod_ordenventa;
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
	public double getImporte_total() {
		return importe_total;
	}
	public void setImporte_total(double importe_total) {
		this.importe_total = importe_total;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
