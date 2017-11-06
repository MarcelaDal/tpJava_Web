package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import controlers.CtrlABMCElementos;
import controlers.CtrlABMCTipoElementos;
import entity.Elemento;
import entity.TipoElementos;

@WebServlet({"/elemento/*", "/elementos/*", "/Elemento/*", "/Elementos/*"})
public class ABMCElementosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CtrlABMCElementos ctrl= new CtrlABMCElementos();
	CtrlABMCTipoElementos ctrlTipoElemento= new CtrlABMCTipoElementos();
    public ABMCElementosServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("get");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		switch (request.getPathInfo()) {
		case "/alta":
			this.alta(request,response);
			break;
			
		case "/baja":
			this.baja(request,response);
			break;
			
		case "/modificacion":
			this.modificacion(request,response);
			break;
			
		case "/consulta":
			try {
				this.consulta(request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		default:
			//this.error(request,response);
			break;
		}
	}
	
	private void consulta(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//response.getWriter().append("Consulta, requested action: ").append(request.getPathInfo()).append(" through post");
		String nombre=request.getParameter("nameInput");
		CtrlABMCElementos ctrl= new CtrlABMCElementos();
		Elemento e= new Elemento();
		e=ctrl.getByNombre(nombre);
		request.setAttribute("elemento", e);
		//TODO 
		
		
	}

	private void modificacion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try{
			ctrl.update(this.mapearDeForm(request));
			System.out.println("El elemento fue modificado con éxito.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("No se puedo modificar el Elemento.");			
		}
		
	}

	private void baja(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			ctrl.delete(this.mapearDeForm(request));
			System.out.println("El elemento fue eliminado con éxito.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("No se puedo eliminar el Elemento.");
		}
	}

	private void alta(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Elemento ele= this.mapearDeForm(request);
		try {
			ctrl.add(ele);
			System.out.println("Nuevo Elemento agregado con éxito.");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al agregar el Elemento.");
		}
		
	}

	private Elemento mapearDeForm(HttpServletRequest request){
		Elemento ele=new Elemento();
		String nombre=request.getParameter("nameInput");
		String id= request.getParameter("idInput");		
		String tipoElemento= request.getParameter("tipoElemento");
		
		ele.setNombre(nombre);
		if(id!=null){
			ele.setId(Integer.parseInt(id));
		}
		if(tipoElemento!=null){
			try {
				ele.setTipo((ctrlTipoElemento.getByNombre(tipoElemento)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ele;
	}
	
}
