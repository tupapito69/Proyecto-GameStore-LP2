package servlets;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import beans.CargoDTO;
import beans.LoginDTO;
import dao.DAOFactory;

/**
 * Servlet implementation class IntranetServlet
 */
@WebServlet(name = "Intranet", urlPatterns = { "/Intranet" })
public class IntranetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public HttpSession misession;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IntranetServlet() {
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
		System.out.println("Entro al Servlet Intranet");
		String option=request.getParameter("option");
		switch (option) {
		case "login": login(request,response); break;
		case "logout": logout(request,response); break;
		case "cpss": cambiopass(request,response); break;
		default:
			response.sendRedirect("error.jsp");
		}
	}

	private void cambiopass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String msj;
		String passNew=request.getParameter("txtPassNew");
		String passConf=request.getParameter("txtPassNewConfirm");
		if(passNew.equals(passConf)) {
			String passMd5=DigestUtils.md5Hex(passNew);
			LoginDTO c= new LoginDTO();
			String session=misession.getAttribute("user").toString();
			c.setUsuario(session);
			c.setContrasenia(passMd5);
			DAOFactory fabrica=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
			boolean putPass=fabrica.getUsuarioDAO().cambioContraseña(c);
			if(putPass) {
				msj="<script type=\"text/javascript\">\r\n"
						+ "	swal({\r\n"
						+ "		title: \"Exitoso !\",\r\n"
						+ "		text: \"Se Cambio su Contraseña Exitosamente\",\r\n"
						+ "		icon: \"success\",\r\n"
						+ "		button: false,\r\n"
						+ "		timer: 4000\r\n"
						+ "	});\r\n"
						+ "</script>";
				request.setAttribute("msje", msj);
				request.getRequestDispatcher("/").forward(request, response);
				misession.invalidate();
			}
		}
		else {
			msj="<script type=\"text/javascript\">\r\n"
					+ "	swal({\r\n"
					+ "		  title: \"Error !\",\r\n"
					+ "		  text: \"Ocurrio un Error, Intentelo Nuevamente.\",\r\n"
					+ "		  icon: \"error\",\r\n"
					+ "		  button: \"OK\",\r\n"
					+ "		  timer: 4000\r\n"
					+ "		});\r\n"
					+ "	</script>";
			request.setAttribute("msje", msj);
			request.getRequestDispatcher("Home.jsp").forward(request, response);
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		request.getSession().invalidate();
		response.sendRedirect("index.jsp");
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String msj, url;
		String user=request.getParameter("txtUser");
		String pass=request.getParameter("txtPass");
		String newpass=DigestUtils.md5Hex(pass);
		CargoDTO c=new CargoDTO();
		c.setUsuario(user);
		c.setContrasenia(newpass);
		DAOFactory fabrica=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		boolean login=fabrica.getUsuarioDAO().login(c);
		if(login) {
			String NameComplet=c.getNom_empleado()+" "+c.getApaterno()+" "+c.getAmaterno();
			misession= request.getSession();
			misession.setAttribute("codCargo",c.getCod_cargo());
			misession.setAttribute("codEmple", c.getCod_empleado());
			misession.setAttribute("NameUser", c.getNom_empleado());
			misession.setAttribute("NameComplet", NameComplet);
			misession.setAttribute("user", c.getUsuario());
			misession.setMaxInactiveInterval(1800);
			response.sendRedirect("Home.jsp");
		}
		else {
			msj="<script type=\"text/javascript\">\r\n"
					+ "	swal({\r\n"
					+ "		  title: \"Error !\",\r\n"
					+ "		  text: \"Usuario y/o Contraseña son Invalidos\",\r\n"
					+ "		  icon: \"error\",\r\n"
					+ "		  button: \"OK\",\r\n"
					+ "		  timer: 2000\r\n"
					+ "		});\r\n"
					+ "	</script>";
			url="/";
			request.setAttribute("msj", msj);
			request.getRequestDispatcher(url).forward(request, response);
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
