package interfaces;

import beans.CargoDTO;
import beans.LoginDTO;

public interface UsuarioDAO {
	public boolean login(CargoDTO c);
	public boolean cambioContraseņa(LoginDTO c);
}
