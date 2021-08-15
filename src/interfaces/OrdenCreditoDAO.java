package interfaces;

import java.util.ArrayList;

import beans.OrdenCreditoDTO;

public interface OrdenCreditoDAO {
	public ArrayList<OrdenCreditoDTO> listAllOrdenCredito();
	public ArrayList<OrdenCreditoDTO> listOrdenCreditoxOV(String OV);
	public int InsertNewOrdenCredito(OrdenCreditoDTO o);
	public int InsertDetailsOredenCredito(OrdenCreditoDTO o);
}
