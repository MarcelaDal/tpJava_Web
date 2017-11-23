package servlet;
 
 import java.io.IOException;
import java.io.PrintWriter;

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
 
 	 
 	
 	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		
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
 		request.setAttribute("nameInput", e.getNombre());
 		request.setAttribute("idInput", e.getId());
 		
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
 		Elemento ele= this.mapearDeForm(request);
 		try {
 			ctrl.delete(ele);
 			System.out.println("El elemento fue eliminado con éxito.");
 			PrintWriter out = response.getWriter(); 
 			out.println("El elemento fue eliminado con éxito. ");
 					out.close();
 		} catch (Exception e) {
 			e.printStackTrace();
 			System.out.println("No se puedo eliminar el Elemento.");
 		}

 	}
 
 	private void alta(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
 		Elemento ele= this.mapearDeForm(request);
 		try {
 			ctrl.add(ele);
 			System.out.println("Nuevo Elemento agregado con éxito.");
 			//PrintWriter out = response.getWriter(); 
 			//out.println("<p>El elemento fue agregado con éxito. </p>");
 			//		out.close();
 			
 		} catch (Exception e) {
 			e.printStackTrace();
 			System.out.println("Error al agregar el Elemento.");
 			//PrintWriter out = response.getWriter(); 
 			//out.println("<p>Error al querer agregar elemento </p>");
 			//		out.close();
 		}
 		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/menuElementos.jsp");
 		dispatcher.forward(request, response);
 		
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