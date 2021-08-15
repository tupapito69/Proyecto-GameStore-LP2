package interfaces;

import java.util.ArrayList;

import beans.ClienteDTO;

public interface ClienteDAO {
	public ArrayList<ClienteDTO> listadoClientes();
	public ArrayList<ClienteDTO> SearchClient(String dni);
	public int InsertNewClient(ClienteDTO c);
	public int UpdateClient(ClienteDTO c);
}
