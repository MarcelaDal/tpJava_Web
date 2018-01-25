package servlet;
 
 import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controlers.CtrlABMCElementos;
import controlers.CtrlABMCTipoElementos;
import entity.Elemento;
 
 @WebServlet({"/elemento/*", "/elementos", "/Elemento", "/Elementos"})
 public class ABMCElementos extends HttpServlet {
 	private static final long serialVersionUID = 1L;
 	CtrlABMCElementos ctrl= new CtrlABMCElementos();
 	CtrlABMCTipoElementos ctrlTipoElemento= new CtrlABMCTipoElementos();
     public ABMCElementos() {
         super();
     }
 
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		try {
 			this.consulta(request, response);
 			request.getRequestDispatcher("WEB-INF/ListadoElementos.jsp").forward(request, response);			
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		
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
 			this.modificacion(request,response, session);
 			break;
 			
 		case "/consulta":
 			try {
 			//	this.consulta(request,response, session);
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
 		String nombre=request.getParameter("nameInput");
 		try{
 			ArrayList<Elemento> resultadosBusqueda= new ArrayList<Elemento>();
 			resultadosBusqueda =ctrl.getByNombre(nombre);
 			request.setAttribute("resultadosBusqueda", resultadosBusqueda);
 		}catch (Exception e) {
 			//request.setAttribute("error", "consultaElemento");
		}
		//response.sendRedirect("http://localhost:8080/tp_Dalpra_Faccioli_web/elementos?");
 	}
 
 	private void modificacion(HttpServletRequest request, HttpServletResponse response,  HttpSession session) throws IOException {
 		
 		Elemento ele= this.mapearDeForm(request, session);
 		String idTipo= request.getParameter("tipoElemento");
 		
		//String nombre=request.getParameter("nameInput");
 		try{ 
 	 		//e.setNombre(request.getParameter("nameInput"));
 	 		//e.setTipo(tipo);
 			ctrl.update(ele);			
 			session.setAttribute("success", "updateElemento");
 			//this.consulta(request, response, session);
 		} catch (Exception e) {
 			e.printStackTrace(); 			
 			session.setAttribute("error", "updateElemento");		
 		}
 		
 		
 		
 	}
 
 	private void baja(HttpServletRequest request, HttpServletResponse response,  HttpSession session) throws IOException {
 		Elemento ele= this.mapearDeForm(request, session);
 		try {
 			ctrl.delete(ele);
 			session.setAttribute("success", "deleteElemento");
			//his.consulta(request, response, session);
 		} catch (Exception e) {
 			e.printStackTrace();
 			session.setAttribute("error", "deleteElemento");
 		}

 	}
 
 	private void alta(HttpServletRequest request, HttpServletResponse response,  HttpSession session) throws IOException, ServletException {
 		Elemento ele= this.mapearDeForm(request, session);
 		try {
 			ctrl.add(ele);
 			session.setAttribute("success", "addElemento");
			//this.consulta(request, response, session);
 		} catch (Exception e) {
 			e.printStackTrace();
 			session.setAttribute("error", "addElemento");				
 		}
 	}
 
 	private Elemento mapearDeForm(HttpServletRequest request, HttpSession session){
 		Elemento ele=new Elemento();
 		String id=request.getParameter("delete");
 		System.out.println(id);
 		
 		
 		/*Elemento ele=new Elemento();
 		String nombre=request.getParameter("nameInput");
 		String id= request.getParameter("idEle");
 		int idElemento= Integer.parseInt(id.trim());
 		String tipoElemento= request.getParameter("tipoElemento");
 		String habilitado= request.getParameter("habilitadoEle");
 		 		
 		if(id!=null){
 			ele.setId(idElemento);
 		}else{
 			ele.setId(((Elemento)session.getAttribute("elemento")).getId());
 		}
 		if(nombre!=null){
 			ele.setNombre(nombre);
 		}else{
 			ele.setNombre(((Elemento)session.getAttribute("elemento")).getNombre());
 		}
 		/*if(habilitado.equals("on")){
 			ele.setHabilitado(true);
 		}else{
 			ele.setHabilitado(false);
 		}*/
 		/*if(tipoElemento!=null){
 			try {
 				ele.setTipo((ctrlTipoElemento.getByNombre(tipoElemento)));
 			} catch (Exception e) {
 				e.printStackTrace();
 			}
 		}else{
 			ele.setTipo(((Elemento)session.getAttribute("elemento")).getTipo());
 		}*/
 		return ele;
 	}
 	
 }