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

import beans.ConsultasDTO;
import beans.OrdenVentaDTO;
import dao.DAOFactory;

/**
 * Servlet implementation class ConsultasServlet
 */
@WebServlet(name = "Consultas", urlPatterns = { "/Consultas" })
public class ConsultasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultasServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
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
		System.out.println("Entro al Servlet Consultas");		
		String opc = request.getParameter("opcion");		
		switch (opc) {
			case "cvts": consulVenta(request,response);break;
			case "ccts": consulCliente(request,response);break;
			case "cvts2": consulDetalleVenta(request,response);break;
			case "cvtsC": consulCuponVenta(request,response);break;
			default: break;				
		}
	}

	private void consulCuponVenta(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String cod = request.getParameter("CODV");
		DAOFactory fabri = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ArrayList<OrdenVentaDTO> lista = fabri.getConsultasDAO().SearchCuponVenta(cod);
		String json=new Gson().toJson(lista);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	private void consulDetalleVenta(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String cod = request.getParameter("CODV");
		DAOFactory fabri = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ArrayList<OrdenVentaDTO> lista = fabri.getConsultasDAO().listarDetallesVenta(cod);
		String json=new Gson().toJson(lista);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	private void consulCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String dni = request.getParameter("txtDNI");
		DAOFactory fabri = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ArrayList<ConsultasDTO> lista = fabri.getConsultasDAO().listarConsultaClientes(dni);
		String json=new Gson().toJson(lista);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	private void consulVenta(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String dni = request.getParameter("txtDNI");
		DAOFactory fabri = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ArrayList<OrdenVentaDTO> lista = fabri.getConsultasDAO().listarConsultaVenta(dni);
		String json=new Gson().toJson(lista);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
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
