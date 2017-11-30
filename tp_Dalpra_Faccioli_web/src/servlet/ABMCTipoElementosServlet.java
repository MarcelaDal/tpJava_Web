package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlers.CtrlABMCTipoElementos;
import entity.TipoElementos;

@WebServlet({"/tipoElemento/*", "/TipoElemento/*", "/TiposElementos/*", "/tiposElementos/*"})
public class ABMCTipoElementosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CtrlABMCTipoElementos ctrl= new CtrlABMCTipoElementos();
    
	public ABMCTipoElementosServlet() {
        super();
    }

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
		TipoElementos te= new TipoElementos();
		te=ctrl.getByNombre(nombre);
		request.setAttribute("elemento", te);
		//TODO 
		
		
	}

	private void modificacion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try{
			ctrl.update(this.mapearDeForm(request));
			System.out.println("El tipo de elemento fue modificado con �xito.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("No se puedo modificar el tipo de elemento.");			
		}
		
	}

	private void baja(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			ctrl.delete(this.mapearDeForm(request));
			System.out.println("El tipo de elemento fue eliminado con �xito.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("No se puedo eliminar el tipo de Elemento.");
		}
	}

	private void alta(HttpServletRequest request, HttpServletResponse response) throws IOException {
		TipoElementos te= this.mapearDeForm(request);
		try {
			ctrl.add(te);
			System.out.println("Nuevo Tipo de Elemento agregado con �xito.");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al agregar el Tipo de Elemento.");
		}
		
	}

	private TipoElementos mapearDeForm(HttpServletRequest request){
		TipoElementos te=new TipoElementos();
		String nombre=request.getParameter("nameInput");
		String id= request.getParameter("idInput");		
		String canMaxResPend= request.getParameter("cantRes");
		String habilitado= request.getParameter("habilitado");
		//TODO que el habilitado lo setee desde ac� no desde la BD
		te.setNombre(nombre);
		if(habilitado==null){
			te.setHabilitado(false);
		}else {
			te.setHabilitado(true);
		}
		
		if(id!=null){
			te.setId(Integer.parseInt(id));
		}
		if(canMaxResPend!=null){
			te.setCanMaxResPend(Integer.parseInt(canMaxResPend));
		}
		return te;
		
	}
	
}
