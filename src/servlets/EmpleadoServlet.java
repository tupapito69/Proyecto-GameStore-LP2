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

import beans.CargoDTO;
import beans.EmpleadoDTO;
import dao.DAOFactory;
import interfaces.CargoDAO;
import interfaces.EmpleadoDAO;

/**
 * Servlet implementation class EmpleadoServlet
 */
@WebServlet(name = "Empleado", urlPatterns = { "/Empleado" })
public class EmpleadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private int codEmple,supervEmple;
	private String NomEmple,APaternoEmple,AMaternoEmple,
	emailEmple,TelfEmple,CargoEmple,UserEmple;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpleadoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("method ini Empleado");
		codEmple=0;supervEmple=0;
		NomEmple="";APaternoEmple="";AMaternoEmple="";emailEmple="";
		TelfEmple="";CargoEmple="";UserEmple="";
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
		System.out.println("ingresó al servlet Empleado");
		String option=request.getParameter("option");
		switch (option) {
			case "r": RegistEmpleado(request,response); break;
			case "e": EditEmpleado(request,response); break;
			case "lisup": ListSuperv(request,response); break;
			case "licag": ListCargo(request,response); break;
			case "ldtab": LoadTabla(request,response); break;
			case "shem": SearchEmpleado(request,response); break;
			default: break;
		}
	}

	private void SearchEmpleado(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int codEmpleado=Integer.parseInt(request.getParameter("codE"));
		DAOFactory fabrica=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		EmpleadoDAO daoEmpleado=fabrica.getEmpleadoDAO();
		ArrayList<CargoDTO> lstEmpleadoSearch=daoEmpleado.searchEmpleados(codEmpleado);
		String json=new Gson().toJson(lstEmpleadoSearch);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	private void LoadTabla(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		DAOFactory fabrica=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		EmpleadoDAO daoEmpleado=fabrica.getEmpleadoDAO();
		ArrayList<CargoDTO> lstEmpleados=daoEmpleado.listadoEmpleados();
		String json=new Gson().toJson(lstEmpleados);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	private void ListCargo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		DAOFactory fabricaCargos=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		CargoDAO daoCargo=fabricaCargos.getCargoDAO();
		ArrayList<CargoDTO> lstCargos=daoCargo.listadoCargos();
		String json=new Gson().toJson(lstCargos);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	private void ListSuperv(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		DAOFactory fabrica=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		EmpleadoDAO daoSupervisor=fabrica.getEmpleadoDAO();
		ArrayList<EmpleadoDTO> lstSupervisores=daoSupervisor.listadoEmpleadosSupervisor();
		String json=new Gson().toJson(lstSupervisores);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	private void EditEmpleado(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		codEmple=Integer.parseInt(request.getParameter("codE"));
		NomEmple=request.getParameter("NombE");
		APaternoEmple=request.getParameter("ApePat");
		AMaternoEmple=request.getParameter("ApeMat");
		emailEmple=request.getParameter("Email");
		TelfEmple=request.getParameter("Telf");
		supervEmple=Integer.parseInt(request.getParameter("CodSuper"));
		CargoEmple=request.getParameter("CodCrg");
		CargoDTO e=new CargoDTO();
		e.setCod_empleado(codEmple);
		e.setNom_empleado(NomEmple);
		e.setApaterno(APaternoEmple);
		e.setAmaterno(AMaternoEmple);
		e.setEmail(emailEmple);
		e.setTelefono(TelfEmple);
		e.setCod_emple_superv(supervEmple);
		e.setCod_cargo(CargoEmple);
		DAOFactory fabrica=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		int rpta=fabrica.getEmpleadoDAO().UpdateEmpleado(e);
		if(rpta==1) {
			response.getWriter().write("1");
		}else {
			response.getWriter().write("0");
		}
	}

	private void RegistEmpleado(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		codEmple=Integer.parseInt(request.getParameter("codE"));
		NomEmple=request.getParameter("NombE");
		APaternoEmple=request.getParameter("ApePat");
		AMaternoEmple=request.getParameter("ApeMat");
		emailEmple=request.getParameter("Email");
		TelfEmple=request.getParameter("Telf");
		supervEmple=Integer.parseInt(request.getParameter("CodSuper"));
		CargoEmple=request.getParameter("CodCrg");
		UserEmple=request.getParameter("User");
		CargoDTO e=new CargoDTO();
		e.setCod_empleado(codEmple);
		e.setNom_empleado(NomEmple);
		e.setApaterno(APaternoEmple);
		e.setAmaterno(AMaternoEmple);
		e.setEmail(emailEmple);
		e.setTelefono(TelfEmple);
		e.setCod_emple_superv(supervEmple);
		e.setCod_cargo(CargoEmple);
		e.setUsuario(UserEmple);
		DAOFactory fabrica=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		int rpta=fabrica.getEmpleadoDAO().InsertNewEmpleado(e);
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
