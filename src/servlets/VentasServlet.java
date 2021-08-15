package servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.OrdenVentaDTO;
import dao.DAOFactory;


/**
 * Servlet implementation class VentasServlet
 */
@WebServlet(name = "Ventas", urlPatterns = { "/Ventas" })
public class VentasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String cod_OV, dni, Id_cupon; 
	private int cod_emp;
	private Date Fecha;
	private double Importe;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VentasServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Method init ventas");
		cod_OV=""; dni=""; Id_cupon="";
		cod_emp=0; Fecha=null; Importe=0.0;
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
		System.out.println("ingresó al servlet Ventas");
		String option=request.getParameter("option");
		switch (option) {
			case "reVt": RegistVenta(request,response); break;
			case "reDV": RegistDetailsVenta(request,response); break;		
			default: break;
		}
	}

	private void RegistDetailsVenta(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		cod_OV=request.getParameter("codV");
		String Cod_Prod=request.getParameter("codP");
		int cant=Integer.parseInt(request.getParameter("cantd"));
		double precio=Double.parseDouble(request.getParameter("prci"));
		OrdenVentaDTO o=new OrdenVentaDTO();
		o.setCod_ordenVenta(cod_OV);
		o.setCod_producto(Cod_Prod);
		o.setCantidad_venta(cant);
		o.setPrecio_detalleventa(precio);		
		DAOFactory fabrica=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		int x=fabrica.getOrdenVentaDAO().InsertNewDetalleVenta(o);
		if(x==1) {
			fabrica.getProductoDAO().ActualizarStock(Cod_Prod, cant);
		}
	}

	private void RegistVenta(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int rpta=0;
		cod_OV=GenerarCod();
		cod_emp=Integer.parseInt(request.getSession().getAttribute("codEmple").toString());
		dni=request.getParameter("DNIC");
		Fecha=Date.valueOf(LocalDate.now());
		Importe=Double.parseDouble(request.getParameter("ImtV"));
		Id_cupon=request.getParameter("idCup");
		OrdenVentaDTO o=new OrdenVentaDTO();
		o.setCod_ordenVenta(cod_OV);
		o.setCod_empleado(cod_emp);
		o.setDni_cliente(dni);
		o.setFecha_ordenventa(Fecha);
		o.setImporte_total(Importe);
		o.setId_cupon(Id_cupon);
		DAOFactory fabrica=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		if(request.getParameter("idCup")==""||request.getParameter("idCup")==null||request.getParameter("idCup").equals("")) {
			rpta=fabrica.getOrdenVentaDAO().InsertNewOrdenVenta(cod_OV, cod_emp, dni, Fecha, Importe, null);
		}else {
			rpta=fabrica.getOrdenVentaDAO().InsertNewOrdenVenta(cod_OV, cod_emp, dni, Fecha, Importe, Id_cupon);
		}
		if(rpta==1) {
			fabrica.getCuponDAO().modificarCantUsos(Id_cupon);
			response.getWriter().write(cod_OV);
		}else {
			response.getWriter().write("0");
		}
	}
	
	private String GenerarCod() {
		DAOFactory fabrica=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		int cant=fabrica.getOrdenVentaDAO().listadoOrdenVentas().size();
		String Cod="OV"+cant;
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
