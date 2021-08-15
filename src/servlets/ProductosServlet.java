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

import beans.CategoriaDTO;
import beans.ProductoDTO;
import dao.DAOFactory;
import interfaces.CategoriaDAO;
import interfaces.ProductoDAO;

/**
 * Servlet implementation class ProductosServlet
 */
@WebServlet(name = "Productos", urlPatterns = { "/Productos" })
public class ProductosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductosServlet() {
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
		System.out.println("ingresó al servlet Productos");
		String option=request.getParameter("option");
		switch (option) {
			case "licatg": ListCateg(request,response); break;
			case "ligame": ListJuegos(request,response); break;
			case "lopdcg": LoadDataProdxCat(request,response); break;
			case "vlstkp": ValidarStockProd(request,response); break;
			case "updskp": ActualizaStockProd(request,response); break;
			case "lallpd": ListAllPrducts(request,response); break;
			case "am": AgregarProd(request,response);break;
			case "pm": ModificarProd(request,response);break;
			case "em": EliminarProd(request,response);break;
			case "pp": BuscarDatos(request,response);break;
			default: break;
		}
	}
	
	private void ListAllPrducts(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		DAOFactory fabricaProductos=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ProductoDAO daoProducto=fabricaProductos.getProductoDAO();
		ArrayList<ProductoDTO> lstProductos=daoProducto.listadoProductos();
		String json=new Gson().toJson(lstProductos);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	private String GenerarCodProducto() 
	{
		DAOFactory fabri = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		String codPro = fabri.getProductoDAO().GenerarCodProducto();
		return codPro;
	}
	
	private void BuscarDatos(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String pro = request.getParameter("codPro");
		DAOFactory fabri = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ArrayList<ProductoDTO> lista = fabri.getProductoDAO().buscarProducto(pro);
		String json=new Gson().toJson(lista);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	private void EliminarProd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String cod = request.getParameter("codProducto");
		DAOFactory fabri = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		int rpt = fabri.getProductoDAO().eliminarProducto(cod);
		if(rpt == 1) {
			response.getWriter().write("1");
		}else {
			response.getWriter().write("0");
		}
	}

	private void ModificarProd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String cod = request.getParameter("codProducto");
		String nom = request.getParameter("nomProducto");
		double precio = Double.parseDouble(request.getParameter("precioProducto"));
		int stock = Integer.parseInt(request.getParameter("canStock"));
		String codCate = request.getParameter("codCategoria");
		ProductoDTO pro = new ProductoDTO();
		pro.setCod_producto(cod);
		pro.setNom_producto(nom);
		pro.setPrecio_producto(precio);
		pro.setStock_producto(stock);
		pro.setCod_cat(codCate);
		DAOFactory fabri = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		int rpt =fabri.getProductoDAO().modificarProducto(pro);
		if(rpt == 1) {
			response.getWriter().write("1");
		}else {
			response.getWriter().write("0");
		}
	}

	private void AgregarProd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String cod = GenerarCodProducto();
		String nom = request.getParameter("nomProducto");
		double precio = Double.parseDouble(request.getParameter("precioProducto"));
		int stock = Integer.parseInt(request.getParameter("canStock"));
		String codCate = request.getParameter("codCategoria");
		ProductoDTO pro = new ProductoDTO();
		pro.setCod_producto(cod);
		pro.setNom_producto(nom);
		pro.setPrecio_producto(precio);
		pro.setStock_producto(stock);
		pro.setCod_cat(codCate);
		DAOFactory fabri = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		int rpt = fabri.getProductoDAO().agregarProducto(pro);
		if(rpt == 1) {
			response.getWriter().write("1");
		}else {
			response.getWriter().write("0");
		}
	}

	private void ActualizaStockProd(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String prod=request.getParameter("prod");
		int cant=Integer.parseInt(request.getParameter("cant"));
		DAOFactory fabrica=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ProductoDAO daoProducto=fabrica.getProductoDAO();
		daoProducto.ActualizarStock(prod, cant);
	}

	private void ValidarStockProd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String prod=request.getParameter("prod");
		int cant=Integer.parseInt(request.getParameter("cant"));
		DAOFactory fabrica=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ProductoDAO daoProducto=fabrica.getProductoDAO();
		int stockActual=daoProducto.ValidarStockProducto(prod);
		if(cant<=stockActual) {
			response.getWriter().write("1");
		}else {
			response.getWriter().write("0");
		}
	}

	private void LoadDataProdxCat(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String catg=request.getParameter("catg");
		String prod=request.getParameter("prod");
		DAOFactory fabrica=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ProductoDAO daoProducto=fabrica.getProductoDAO();
		ArrayList<ProductoDTO> lstProducto=daoProducto.listadoProductos(catg, prod);
		String json=new Gson().toJson(lstProducto);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	private void ListJuegos(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String catg=request.getParameter("catg");
		DAOFactory fabrica=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ProductoDAO daoProducto=fabrica.getProductoDAO();
		ArrayList<ProductoDTO> lstProducto=daoProducto.listadoProductos(catg);
		String json=new Gson().toJson(lstProducto);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	private void ListCateg(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		DAOFactory fabrica=DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		CategoriaDAO daoCategoria=fabrica.getCategoriaDAO();
		ArrayList<CategoriaDTO> lstCategoria=daoCategoria.listadoCategorias();
		String json=new Gson().toJson(lstCategoria);
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
