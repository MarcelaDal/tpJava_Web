package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controlers.CtrlABMCClientes;
import entity.Persona;


@WebServlet({"/persona/*", "/personas/*", "/Persona/*", "/Personas/*"})
public class ABMCClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
    CtrlABMCClientes ctrl= new CtrlABMCClientes();  
   
    public ABMCClientes() {
        super();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		session.setAttribute("success", null);
		session.setAttribute("error", null);
		switch (request.getPathInfo()) {
		case "/alta":
			try {
				this.alta(request,response, session);
			} catch (Exception e) {
				e.printStackTrace();
			}			
			break;
			
		case "/baja":			
			try {
				this.baja(request,response, session);
			} catch (Exception e) {
				e.printStackTrace();
			}			
			break;
			
		case "/modificacion":
			this.modificacion(request,response, session);			
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
 		String dni=request.getParameter("dniInput");
 		try{
 			Persona per= new Persona();
 	 		per= ctrl.getByDni(dni);
 	 		session.setAttribute("persona", per);
 		}catch (Exception e) {
 			session.setAttribute("error", "consultaPersona");
		}
 		response.sendRedirect("http://localhost:8080/tp_Dalpra_Faccioli_web/personas?");

 	}
	
	private void modificacion(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
 		try{
 			ctrl.update(this.mapearDeForm(request, session));
 			session.setAttribute("success", "updatePersona");
 			this.consulta(request, response, session);
 		} catch (Exception e) {
 			e.printStackTrace();
 			session.setAttribute("error", "updatePersona");
 		}
 		
 	}
 	
	
 	private void alta(HttpServletRequest request, HttpServletResponse response,  HttpSession session) throws Exception {
 		Persona per= this.mapearDeForm(request, session);
 		try {
 			ctrl.add(per);
 			session.setAttribute("success", "addPersona");
			this.consulta(request, response, session);
 		} catch (Exception e) {
 			e.printStackTrace();
 			session.setAttribute("error", "addPersona");	
 		}
 		
 	}
	
 	private void baja(HttpServletRequest request, HttpServletResponse response, HttpSession session ) throws Exception {
 		Persona per= this.mapearDeForm(request, session);
 		try {
 			ctrl.delete(per);
 			session.setAttribute("success", "deletePersona");
			this.consulta(request, response, session);
 		} catch (Exception e) {
 			e.printStackTrace();
 			session.setAttribute("error", "deletePersona");
 		}
 	}
 	
 	
 	

 	
 	private Persona mapearDeForm(HttpServletRequest request, HttpSession session) throws Exception{		
 		Persona per=new Persona();
 		String nombre=request.getParameter("nameInput");
 		String id= request.getParameter("id");		
 		String apellido= request.getParameter("lastnameInput");
 		String dni= request.getParameter("dniInput");
 		String habilitado= request.getParameter("habilitado");
 		String categoria= request.getParameter("categoria");
 		
 		if(id!=null){
 			per.setId(Integer.parseInt(id));
 		}
 		else{
 			per.setId(((Persona)session.getAttribute("persona")).getId());
 		}
 		if(dni!=null){
 			per.setDni(dni);
 		}else{
 			per.setDni(((Persona)session.getAttribute("persona")).getDni());
 		}
 		if(nombre!= null){
 			per.setNombre(nombre);
 		}else{
 			per.setNombre(((Persona)session.getAttribute("persona")).getNombre());
 		}
 		if(apellido!=null){
 			per.setApellido(apellido);
 		}else{
 			per.setApellido(((Persona)session.getAttribute("persona")).getApellido());
 		} 		
 		if(habilitado.equals("on")){
			per.setHabilitado(true);
		}else {
			per.setHabilitado(false);
		}
 		if(categoria!=null){
 			per.setCategoria(ctrl.getCategoriaByNombre(categoria));
 		}else{
 			per.setCategoria(((Persona)session.getAttribute("persona")).getCategoria());
 		}
 			
 		return per;
 	}
}
