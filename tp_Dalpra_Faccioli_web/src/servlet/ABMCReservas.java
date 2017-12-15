package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


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
import entity.Persona;

@WebServlet({"/reserva/*", "/reservas/*", "/Reserva/*", "/Reservas/*"})
public class ABMCReservas extends HttpServlet {
	private static final long serialVersionUID = 1L;
    CtrlABMCReservas ctrl= new CtrlABMCReservas();  
   
    public ABMCReservas() {
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "/baja":
			try {
				this.baja(request,response, session);
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

		
 	private void alta(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
 		 CtrlABMCReservas ctrl= new CtrlABMCReservas();  
 		Reserva r= this.mapearDeForm(request, session);
 		try {
 			ctrl.add(r);
 			session.setAttribute("success", "addReserva");
 			
 		} catch (Exception e) {
 			e.printStackTrace();
 			session.setAttribute("error", "addReserva");
 		
 		}
 		response.sendRedirect("http://localhost:8080/tp_Dalpra_Faccioli_web/reservas?");
 		
 	}
	
 	private void baja(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
 		String id= request.getParameter("idInput");
 		Reserva r= new Reserva();
 		r.setId(Integer.parseInt(id));
 		try {
 			ctrl.delete(r);
 			session.setAttribute("success", "deleteReserva");
 		} catch (Exception e) {
 			e.printStackTrace();
 			session.setAttribute("error", "deleteReserva");
 		}
 		response.sendRedirect("http://localhost:8080/tp_Dalpra_Faccioli_web/reservas?");
 	}


	private Reserva mapearDeForm(HttpServletRequest request, HttpSession session){		
 		Reserva r=new Reserva();
 		Elemento e= new Elemento();
 		CtrlABMCElementos ctrlE= new CtrlABMCElementos();
 		String nombreEle=request.getParameter("elemento");
 		int idElemento= Integer.parseInt(nombreEle);
 		//String id= request.getParameter("idInput");
 		//int idReserva= Integer.parseInt(id);
 		String detalle= request.getParameter("detail");
 		boolean est= true; 

 		//java.sql.Date date = new java.sql.Date(dateString);
 		String dateInString = request.getParameter("datepicker");
 		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
 		java.util.Date utilDate = null;
		try {
			utilDate = format.parse(dateInString);
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
       
 		String hora= request.getParameter("hora");
 		SimpleDateFormat sdf= new SimpleDateFormat("HH:mm:ss");
 		Date horario = null;
		try {
			horario = sdf.parse(hora);
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
 		Time tiempo= new Time(horario.getTime());
 		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); 
 		
 		try {
			e= ctrlE.getById(idElemento);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 		
 		//if(id !=null){
 			//r.setId(idReserva);
 		//}
 		r.setElemento(e);
 		r.setDetalle(detalle);
 		r.setHora(tiempo);
 		r.setFecha(sqlDate);
 		r.setEstado(est);
 		r.setPersona(((Persona)session.getAttribute("user")));
 		//TODO*/
 		return r;
 	}
	
}
