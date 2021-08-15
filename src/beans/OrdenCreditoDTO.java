package beans;

import java.util.Date;

public class OrdenCreditoDTO  extends OrdenCreditoDetalleDTO{
	private String cod_venta, dni_cliente, id_cupon, cod_credito;
	private int cod_emple;
	private Date fechacredito;
	private double importe_total;
	public String getCod_venta() {
		return cod_venta;
	}
	public void setCod_venta(String cod_venta) {
		this.cod_venta = cod_venta;
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
	public String getCod_credito() {
		return cod_credito;
	}
	public void setCod_credito(String cod_credito) {
		this.cod_credito = cod_credito;
	}
	public int getCod_emple() {
		return cod_emple;
	}
	public void setCod_emple(int cod_emple) {
		this.cod_emple = cod_emple;
	}
	public Date getFechacredito() {
		return fechacredito;
	}
	public void setFechacredito(Date fechacredito) {
		this.fechacredito = fechacredito;
	}
	public double getImporte_total() {
		return importe_total;
	}
	public void setImporte_total(double importe_total) {
		this.importe_total = importe_total;
	}
}
