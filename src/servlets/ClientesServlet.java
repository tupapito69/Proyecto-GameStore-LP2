package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.ClienteDTO;
import dao.DAOFactory;
import interfaces.ClienteDAO;

/**
 * Servlet implementation class ClientesServlet
 */
@WebServlet(name = "Clientes", urlPatterns = { "/Clientes" })
public class ClientesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String DNIClient,NomClient,APaternoClient,AMaternoClient,TelfClient;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		DNIClient=""; NomClient="";	APaternoClient="";
		AMaternoClient=""; TelfClient="";
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("ingresó al servlet cliente");
		String option=request.getParameter("option");
		switch (option) {
			case "reCt": RegistCliente(request,response); break;
			case "upCt": ActualizarCliente(request,response); break;
			case "lsCt": ListarAllCliente(request,response); break;
			case "shCt": BuscarCliente(request,response); break;
			case "VlCt": ValidarCliente(request,response); break;
			default: break;
		}
	}

	private void ListarAllCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		DAOFactory fabricaClientes=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ClienteDAO daoCliente=fabricaClientes.getClienteDAO();
		ArrayList<ClienteDTO> lstClientes=daoCliente.listadoClientes();
		String json=new Gson().toJson(lstClientes);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	private void BuscarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		DNIClient=request.getParameter("DNIC");
		DAOFactory fabrica=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ArrayList<ClienteDTO> lstCliente=fabrica.getClienteDAO().SearchClient(DNIClient);
		String json=new Gson().toJson(lstCliente);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	private void ActualizarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		DNIClient=request.getParameter("DNIC"); 
		NomClient=request.getParameter("NombC");	
		APaternoClient=request.getParameter("ApePat");
		AMaternoClient=request.getParameter("ApeMat"); 
		TelfClient=request.getParameter("Telf");
		ClienteDTO c=new ClienteDTO();
		c.setDNI(DNIClient);
		c.setNom_cliente(NomClient);
		c.setApaterno(APaternoClient);
		c.setAmaterno(AMaternoClient);
		c.setTelefono(TelfClient);
		DAOFactory fabrica=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		int rpta=fabrica.getClienteDAO().UpdateClient(c);
		if(rpta==1) {
			response.getWriter().write("1");
		}else {
			response.getWriter().write("0");
		}
	}

	private void ValidarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		DNIClient=request.getParameter("DNIC");
		DAOFactory fabrica=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ArrayList<ClienteDTO> lstCliente=fabrica.getClienteDAO().SearchClient(DNIClient);
		if(lstCliente!=null&&lstCliente.toArray().length!=0) {
			response.getWriter().write("1");
		}else {
			response.getWriter().write("0");
		}
		
	}

	private void RegistCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub		
		DNIClient=request.getParameter("DNIC"); 
		NomClient=request.getParameter("NombC");	
		APaternoClient=request.getParameter("ApePat");
		AMaternoClient=request.getParameter("ApeMat"); 
		TelfClient=request.getParameter("Telf");
		ClienteDTO c=new ClienteDTO();
		c.setDNI(DNIClient);
		c.setNom_cliente(NomClient);
		c.setApaterno(APaternoClient);
		c.setAmaterno(AMaternoClient);
		c.setTelefono(TelfClient);
		DAOFactory fabrica=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		int rpta=fabrica.getClienteDAO().InsertNewClient(c);
		if(rpta==1) {
			response.getWriter().write("1");
		}else {
			response.getWriter().write("0");
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
