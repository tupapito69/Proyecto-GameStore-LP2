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

import beans.HistorialVentaDTO;
import dao.DAOFactory;

/**
 * Servlet implementation class HistorialVentaSevlet
 */
@WebServlet(name = "HistorialVenta", urlPatterns = { "/HistorialVenta" })
public class HistorialVentaSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistorialVentaSevlet() {
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
		System.out.println("Entro al Servlet HistorialVenta");
		String opc = request.getParameter("option");
		switch (opc) {
			case "hvpf": historialVentaFecha(request,response);break;
			case "hvpe": historialVentaEmpleado(request,response);break;
			case "hvpc": historialVentaCliente(request,response);break;
			default: break;
		}
	}

	private void historialVentaCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String dni = request.getParameter("DNI");
		DAOFactory fabri = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ArrayList<HistorialVentaDTO> lista = fabri.getHistorialVentaDAO().ListarHistorialCliente(dni);
		String json=new Gson().toJson(lista);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	private void historialVentaEmpleado(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int idempleado = Integer.parseInt(request.getParameter("idempleado"));
		DAOFactory fabri = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ArrayList<HistorialVentaDTO> lista = fabri.getHistorialVentaDAO().ListarHistorialEmpleado(idempleado);
		String json=new Gson().toJson(lista);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	private void historialVentaFecha(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String fecha1 = request.getParameter("fecha1");
		String fecha2 = request.getParameter("fecha2");
		DAOFactory fabri = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ArrayList<HistorialVentaDTO> lista = fabri.getHistorialVentaDAO().ListarHistorialFecha(fecha1, fecha2);
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
