package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controlers.CtrlABMCElementos;
import controlers.CtrlABMCReservas;
import entity.Elemento;
import entity.Reserva;

/**
 * Servlet implementation class ABMCClientes
 */
@WebServlet({"/reserva/*", "/reservas/*", "/Reserva/*", "/Reservas/*"})
public class ABMCReservas extends HttpServlet {
	private static final long serialVersionUID = 1L;
    CtrlABMCReservas ctrl= new CtrlABMCReservas();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ABMCReservas() {
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
			
		default:
			//this.error(request,response);
			break;
		}
	}

	
	private Reserva mapearDeForm(HttpServletRequest request){
 		Reserva r=new Reserva();
 		Elemento e= new Elemento();
 		CtrlABMCElementos ctrlE= new CtrlABMCElementos();
 		String nombreElemento=request.getParameter("elemento");
 		String id= request.getParameter("idInput");
 		String detalle= request.getParameter("detail");
 		String itTipoElemento= request.getParameter("tipoElemento");
 		
 		try {
			e= ctrlE.getByNombre(nombreElemento);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 		
 		if(id !=null){
 			r.setId(Integer.parseInt(id));
 		}
 		r.setElemento(e);
 		r.setDetalle(detalle);
 		

 		//TODO*/
 		return r;
 	}
	
 	private void alta(HttpServletRequest request, HttpServletResponse response) throws IOException {
 		Reserva r= this.mapearDeForm(request);
 		try {
 			ctrl.add(r);
 			//TODO
 			
 		} catch (Exception e) {
 			e.printStackTrace();
 			System.out.println("Error al agregar la Reserva.");
 		
 		}
 		
 	}
	
 	private void baja(HttpServletRequest request, HttpServletResponse response) throws IOException {
 		try {
 			ctrl.delete(this.mapearDeForm(request));
 			System.out.println("La reserva fue eliminada con éxito.");
 		} catch (Exception e) {
 			e.printStackTrace();
 			
 		}
 	}

	
}
