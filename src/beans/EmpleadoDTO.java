package beans;

public class EmpleadoDTO extends LoginDTO{
	public int cod_empleado, cod_emple_superv;
	public String nom_empleado, apaterno, amaterno, email, telefono;
	public int getCod_empleado() {
		return cod_empleado;
	}
	public void setCod_empleado(int cod_empleado) {
		this.cod_empleado = cod_empleado;
	}
	public int getCod_emple_superv() {
		return cod_emple_superv;
	}
	public void setCod_emple_superv(int cod_emple_superv) {
		this.cod_emple_superv = cod_emple_superv;
	}
	public String getNom_empleado() {
		return nom_empleado;
	}
	public void setNom_empleado(String nom_empleado) {
		this.nom_empleado = nom_empleado;
	}
	public String getApaterno() {
		return apaterno;
	}
	public void setApaterno(String apaterno) {
		this.apaterno = apaterno;
	}
	public String getAmaterno() {
		return amaterno;
	}
	public void setAmaterno(String amaterno) {
		this.amaterno = amaterno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
}
