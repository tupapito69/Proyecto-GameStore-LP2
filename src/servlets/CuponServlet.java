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

import beans.CuponDTO;
import dao.DAOFactory;
import interfaces.CuponDAO;

/**
 * Servlet implementation class CuponServlet
 */
@WebServlet(name = "Cupon", urlPatterns = { "/Cupon" })
public class CuponServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CuponServlet() {
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
		System.out.println("ingresó al servlet Cupon");
		String option=request.getParameter("option");
		switch (option) {
			case "GeCup": GenerarCupon(request,response); break;
			case "shCup": SearchCupon(request,response); break;
			case "upCup": UpdateUsosCupon(request,response); break;
			default: break;
		}
	}

	private void GenerarCupon(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String codCupon=request.getParameter("ID");
		int usos=Integer.parseInt(request.getParameter("CantUsos"));
		double importe=Double.parseDouble(request.getParameter("Import"));
		int emple=Integer.parseInt(request.getSession().getAttribute("codEmple").toString());		
		CuponDTO c= new CuponDTO();
		c.setID(codCupon);
		c.setCant_usos(usos);
		c.setImporte(importe);		
		DAOFactory fabrica=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		int rpta=fabrica.getCuponDAO().InsertNewCuponDescuento(c, emple);
		if(rpta==1) {
			response.getWriter().write("1");
		}else {
			response.getWriter().write("0");
		}
	}

	private void UpdateUsosCupon(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String cupon=request.getParameter("idCup");
		DAOFactory fabrica=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		CuponDAO daoProducto=fabrica.getCuponDAO();
		daoProducto.modificarCantUsos(cupon);
	}

	private void SearchCupon(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String idCup=request.getParameter("idCup");
		DAOFactory fabrica=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		CuponDAO daoCupon=fabrica.getCuponDAO();
		ArrayList<CuponDTO> lstCupon=daoCupon.SearchCupon(idCup);
		String json=new Gson().toJson(lstCupon);
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
