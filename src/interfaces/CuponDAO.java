package interfaces;

import java.util.ArrayList;

import beans.CuponDTO;

public interface CuponDAO {
	public ArrayList<CuponDTO> SearchCupon(String ID);
	public int modificarCantUsos(String ID);
	public int InsertNewCuponDescuento(CuponDTO c, int emple);
	public ArrayList<CuponDTO> ListAllCupones();
}
