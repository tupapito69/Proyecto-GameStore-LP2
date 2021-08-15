package servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.CuponDTO;
import beans.OrdenCreditoDTO;
import beans.OrdenVentaDTO;
import dao.DAOFactory;
import interfaces.OrdenVentaDAO;

/**
 * Servlet implementation class OrdenCreditoServlet
 */
@WebServlet(name = "OrdenCredito", urlPatterns = { "/OrdenCredito" })
public class OrdenCreditoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String CodOC, CodOV, dniCli, CUPON, CodProd;
    private int cod_emp, cant;
	private Date Fecha;
    private double Importe, precio;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdenCreditoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		CodOC=""; CodOV=""; dniCli=""; CUPON="";
		CodProd=""; cod_emp=0; Fecha=null; Importe=0.0;
		cant=0; precio=0.0;
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
		System.out.println("Entro al Servlet OrdenCredito");
		String option=request.getParameter("option");
		switch (option) {
			case "ShVCOC": BuscarVentaxCod(request,response); break;
			case "ShDVCOC": BuscarDetalleVentaxCod(request,response); break;
			case "rOC": RegistOrdenCredito(request,response); break;
			case "rOCDT": RegistDetailsOrdenCredito(request,response); break;
			default: break;
		}
	}

	private void BuscarVentaxCod(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String codOrVent=request.getParameter("codOV");
		DAOFactory fabrica=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ArrayList<OrdenCreditoDTO> lst=fabrica.getOrdenCreditoDAO().listOrdenCreditoxOV(codOrVent);
		int x=lst.size();
		if(x==1) {
			response.getWriter().write("0");
		}else {
			OrdenVentaDAO daoEmpleado=fabrica.getOrdenVentaDAO();
			ArrayList<OrdenVentaDTO> lstEmpleadoSearch=daoEmpleado.SearchVentaxCod(codOrVent);
			String json=new Gson().toJson(lstEmpleadoSearch);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		}
	}

	private void BuscarDetalleVentaxCod(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String codOrVent=request.getParameter("codOV");
		DAOFactory fabrica=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		OrdenVentaDAO daoEmpleado=fabrica.getOrdenVentaDAO();
		ArrayList<OrdenVentaDTO> lstEmpleadoSearch=daoEmpleado.SearchDetailsVentaCod(codOrVent);
		String json=new Gson().toJson(lstEmpleadoSearch);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	private void RegistDetailsOrdenCredito(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		CodOC=request.getParameter("codOC");
		CodProd=request.getParameter("Prod");
		cant=Integer.parseInt(request.getParameter("cant"));
		precio=Double.parseDouble(request.getParameter("precio"));
		OrdenCreditoDTO o=new OrdenCreditoDTO();
		o.setCod_credito(CodOC);
		o.setCod_pro(CodProd);
		o.setCantidad_v(cant);
		o.setPrecio_det(precio);
		DAOFactory fabrica=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		fabrica.getOrdenCreditoDAO().InsertDetailsOredenCredito(o);
	}

	private void RegistOrdenCredito(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int rpta=0;
		CodOC=crearCodigoOV(); 
		CodOV=request.getParameter("codOV"); 
		dniCli=request.getParameter("dniCl");
		cod_emp=Integer.parseInt(request.getSession().getAttribute("codEmple").toString());
		Fecha=Date.valueOf(LocalDate.now());
		Importe=Double.parseDouble(request.getParameter("ImptTOC"));
		int CupOC=Integer.parseInt(request.getParameter("Cupon"));
		OrdenCreditoDTO o=new OrdenCreditoDTO();
		o.setCod_credito(CodOC);
		o.setCod_venta(CodOV);
		o.setCod_emple(cod_emp);
		o.setDni_cliente(dniCli);
		o.setFechacredito(Fecha);
		o.setImporte_total(Importe);
		DAOFactory fabrica=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		switch (CupOC) {
		case 0: {
			o.setId_cupon(null);
			rpta=fabrica.getOrdenCreditoDAO().InsertNewOrdenCredito(o);
			if(rpta==1) {
				ArrayList<String> x= new ArrayList<String>();
				x.add(0, CodOC);
				String json=new Gson().toJson(x);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
			}else {
				response.getWriter().write("0");
			}break;
		}
		case 1:{
			CUPON=GenerarCodCupon();
			CuponDTO c= new CuponDTO();
			c.setID(CUPON);
			c.setCant_usos(1);
			c.setImporte(Importe);
			int r=fabrica.getCuponDAO().InsertNewCuponDescuento(c, cod_emp);
			if(r==1) {
				o.setId_cupon(CUPON);
				rpta=fabrica.getOrdenCreditoDAO().InsertNewOrdenCredito(o);
				if(rpta==1) {
					ArrayList<String> x= new ArrayList<String>();
					x.add(0, CodOC);
					x.add(1, CUPON);
					String json=new Gson().toJson(x);
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(json);
				}else {
					response.getWriter().write("0");
				}
			}
			else {
				response.getWriter().write("0");
			}break;
		}
		default:
			break;
		}
	}
	
	private String crearCodigoOV()
	{
		DAOFactory fabrica=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		int cantidad=fabrica.getOrdenCreditoDAO().listAllOrdenCredito().size();
		String codigoVenta = "OC" + cantidad;
		return codigoVenta;
	}
	
	private String GenerarCodCupon() {
		DAOFactory fabrica=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		int cant=fabrica.getCuponDAO().ListAllCupones().size();
		String Cod="CNO"+cant;
		return Cod;
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
