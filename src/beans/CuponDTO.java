package beans;

public class CuponDTO {
	private String ID;
	private int cant_usos;
	private double importe;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public int getCant_usos() {
		return cant_usos;
	}
	public void setCant_usos(int cant_usos) {
		this.cant_usos = cant_usos;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
}
