package interfaces;

import java.util.ArrayList;

import beans.CargoDTO;
import beans.EmpleadoDTO;

public interface EmpleadoDAO {
	public ArrayList<EmpleadoDTO> listadoEmpleadosSupervisor();
	public ArrayList<CargoDTO> listadoEmpleados();
	public int InsertNewEmpleado(CargoDTO c);
	public int UpdateEmpleado(CargoDTO c);
	public ArrayList<CargoDTO> searchEmpleados(int codEmple);
}
