package servlets;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PanelIntranetServlet
 */
@WebServlet(name = "PanelIntranet", urlPatterns = { "/PanelIntranet" })
public class PanelIntranetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PanelIntranetServlet() {
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
		System.out.println("entro al servlet panelIntranet");
		String op=request.getParameter("op");
		switch (op) {
		case "p": 
			response.getWriter().write("p"); break;
		case "h": 
			response.getWriter().write("h"); break;
		case "m": 
			response.getWriter().write("m"); break;
		case "n": 
			response.getWriter().write("n"); break;
		case "c": 
			response.getWriter().write("c"); break;
		case "e": 
			response.getWriter().write("e"); break;
		case "cv": 
			response.getWriter().write("cv"); break;
		case "cc": 
			response.getWriter().write("cc"); break;
		case "rvt": 
			response.getWriter().write("rvt"); break;
		case "rcl": 
			response.getWriter().write("rcl"); break;
		default: break;
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
