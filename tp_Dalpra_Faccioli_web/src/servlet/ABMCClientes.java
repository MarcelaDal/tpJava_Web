package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controlers.CtrlABMCClientes;
import entity.Categoria;
import entity.Persona;

/**
 * Servlet implementation class ABMCClientes
 */
@WebServlet({"/persona/*", "/personas/*", "/Persona/*", "/Personas/*"})
public class ABMCClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
    CtrlABMCClientes ctrl= new CtrlABMCClientes();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ABMCClientes() {
        super();
        // TODO Auto-generated constructor stub
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
		//doGet(request, response);
		switch (request.getPathInfo()) {
		case "/alta":
			try {
				this.alta(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "/baja":
			try {
				this.baja(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "/modificacion":
			try {
				this.modificacion(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "/consulta":
			try {
				this.consulta(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		default:
			//this.error(request,response);
			break;
		}
	}

	
	private Persona mapearDeForm(HttpServletRequest request){
 		Persona per=new Persona();
 		String nombre=request.getParameter("nameInput");
 		String id= request.getParameter("id");		
 		String apellido= request.getParameter("lastnameInput");
 		String dni= request.getParameter("dniInput");
 		Categoria cat= new Categoria();
 		cat.setId(Integer.parseInt(request.getParameter("categoria")));
 		String habilitado= request.getParameter("habilitado");
 		per.setNombre(nombre);
 		if(id!=null){
 			per.setId(Integer.parseInt(id));
 		}
 		if(habilitado==null){
			per.setHabilitado(false);
		}else {
			per.setHabilitado(true);
		}
		
 		
		per.setHabilitado(Boolean.parseBoolean(habilitado));
 		per.setApellido(apellido);
 		per.setDni(dni);
 		per.setCategoria(cat);
 			
 		return per;
 	}
	
 	private void alta(HttpServletRequest request, HttpServletResponse response) throws IOException {
 		Persona per= this.mapearDeForm(request);
 		try {
 			ctrl.add(per);
 			System.out.println("Nuevo cliente agregado con �xito.");
 			PrintWriter out = response.getWriter(); 
 			out.println("<p>El cliente fue agregado con �xito. </p>");
 					out.close();
 			
 		} catch (Exception e) {
 			e.printStackTrace();
 			System.out.println("Error al agregar cliente.");
 			PrintWriter out = response.getWriter(); 
 			out.println("<p>Error al agregar cliente </p>");
 					out.close();
 		}
 		
 	}
	
 	private void baja(HttpServletRequest request, HttpServletResponse response) throws IOException {
 		try {
 			ctrl.delete(this.mapearDeForm(request));
 			System.out.println("El cliente fue eliminado con �xito.");
 		} catch (Exception e) {
 			e.printStackTrace();
 			System.out.println("No se puedo eliminar el cliente.");
 		}
 	}
 	
 	private void consulta(HttpServletRequest request, HttpServletResponse response) throws Exception {
 		//response.getWriter().append("Consulta, requested action: ").append(request.getPathInfo()).append(" through post");
 		String dni=request.getParameter("dniInput");
 		CtrlABMCClientes ctrl= new CtrlABMCClientes();
 		Persona per= new Persona();
 		per= ctrl.getByDni(dni);
 		HttpSession session = request.getSession();
 		session.setAttribute("dniPersona", per.getDni());
 		session.setAttribute("nombrePersona", per.getNombre());
 		session.setAttribute("apellidoPersona", per.getApellido());
 		response.sendRedirect("http://localhost:8080/tp_Dalpra_Faccioli_web/personas?");
 		//TODO 
 		
 		
 	}
 	
 	private void modificacion(HttpServletRequest request, HttpServletResponse response) throws IOException {
 		try{
 			ctrl.update(this.mapearDeForm(request));
 			System.out.println("El cliente fue modificado con �xito.");
 		} catch (Exception e) {
 			e.printStackTrace();
 			System.out.println("No se puedo modificar el Cliente.");			
 		}
 		
 	}
	
}
