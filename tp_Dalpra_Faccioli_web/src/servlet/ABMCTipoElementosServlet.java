package servlet;

import java.io.IOException;

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
		session.setAttribute("success", null);
		session.setAttribute("error", null);
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
			session.setAttribute("tipoElemento", te);
		}catch (Exception e) {
			session.setAttribute("error", "consultaTipoElemento");
		}		
		response.sendRedirect("http://localhost:8080/tp_Dalpra_Faccioli_web/tipoElementos?");		
	}

	private void modificacion(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		try{
			ctrl.update(this.mapearDeForm(request, session));
			session.setAttribute("success", "updateTipoElemento");
			this.consulta(request, response, session);
			
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", "updateTipoElemento");		
		}
		
	}

	private void baja(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		try {
			ctrl.delete(this.mapearDeForm(request, session));			
			session.setAttribute("success", "deleteTipoElemento");
			this.consulta(request, response, session);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", "deleteTipoElemento");	
		}
	}

	private void alta(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		TipoElementos te= this.mapearDeForm(request, session);
		try {
			ctrl.add(te);			
			session.setAttribute("success", "addTipoElemento");
			this.consulta(request, response, session);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", "addTipoElemento");	
		}
	}

	
	private TipoElementos mapearDeForm(HttpServletRequest request, HttpSession session){
		TipoElementos te=new TipoElementos();
		String nombre=request.getParameter("nameInput");
		String id= request.getParameter("idInput");		
		String canMaxResPend= request.getParameter("cantRes");
		String habilitado= request.getParameter("habilitado");
		te.setNombre(nombre);
		if(id!=null){
			te.setId(Integer.parseInt(id));
		}
		else{
			te.setId(((TipoElementos)session.getAttribute("tipoElemento")).getId());
		}
		if(habilitado.equals("on")){
			te.setHabilitado(true);
		}else {
			te.setHabilitado(false);
		}
		if(canMaxResPend!=null){
			te.setCanMaxResPend(Integer.parseInt(canMaxResPend));
		}else{
			te.setCanMaxResPend(((TipoElementos)session.getAttribute("tipoElemento")).getCanMaxResPend());
		}
		return te;
		
	}
	
}
