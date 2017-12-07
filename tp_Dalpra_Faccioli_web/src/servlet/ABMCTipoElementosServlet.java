package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		session.setAttribute("success", "");
		session.setAttribute("error", "");
		switch (request.getPathInfo()) {
		case "/alta":
			this.alta(request,response, session);
			break;
			
		case "/baja":
			this.baja(request,response, session);
			break;
			
		case "/modificacion":
			this.modificacion(request,response,session);
			break;
			
		case "/consulta":
			try {
				this.consulta(request,response, session);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		default:
			//this.error(request,response);
			break;
		}
	}
	
	private void consulta(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {		
		String nombre=request.getParameter("nameInput");
		try{
			TipoElementos te= new TipoElementos();
			te=ctrl.getByNombre(nombre);
			session.setAttribute("idTipoElemento", te.getId());
			session.setAttribute("nombreTipoElemento", te.getNombre());
			session.setAttribute("cantReservas", te.getCanMaxResPend());
			session.setAttribute("habilitadoTipoElemento", te.isHabilitado());
		}catch (Exception e) {
			session.setAttribute("error", "consultaTipoElemento");
		}		
		response.sendRedirect("http://localhost:8080/tp_Dalpra_Faccioli_web/tipoElementos?");
		
		
	}

	private void modificacion(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		try{
			ctrl.update(this.mapearDeForm(request));
			session.setAttribute("success", "updateTipoElemento");
			this.consulta(request, response, session);
			
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", "updateTipoElemento");		
		}
		//response.sendRedirect("http://localhost:8080/tp_Dalpra_Faccioli_web/tipoElementos?");
		
	}

	private void baja(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		try {
			ctrl.delete(this.mapearDeForm(request));			
			session.setAttribute("success", "deleteTipoElemento");
			this.consulta(request, response, session);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", "deleteTipoElemento");	
		}
		//response.sendRedirect("http://localhost:8080/tp_Dalpra_Faccioli_web/tipoElementos?");
	}

	private void alta(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		TipoElementos te= this.mapearDeForm(request);
		try {
			ctrl.add(te);			
			session.setAttribute("success", "addTipoElemento");
			this.consulta(request, response, session);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", "addTipoElemento");	
		}
		//response.sendRedirect("http://localhost:8080/tp_Dalpra_Faccioli_web/tipoElementos?");
	}

	
	private TipoElementos mapearDeForm(HttpServletRequest request){
		TipoElementos te=new TipoElementos();
		String nombre=request.getParameter("nameInput");
		String id= request.getParameter("idInput");		
		String canMaxResPend= request.getParameter("cantRes");
		String habilitado= request.getParameter("habilitado");
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
