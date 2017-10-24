package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlers.CtrlABMCClientes;
import controlers.CtrlABMCReservas;
import entity.Persona;
import entity.Reserva;
/**
 * Servlet implementation class Start
 */
@WebServlet({ "/Start", "/start" })
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
    public Start() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String user=request.getParameter("user");
			String pass=request.getParameter("pass");
					
			
			Persona per=new Persona();
			per.setUsuario(user);
			per.setContrasenia(pass);
			
			CtrlABMCClientes ctrl= new CtrlABMCClientes();
			CtrlABMCReservas ctrlRes=new CtrlABMCReservas();
			ArrayList<Reserva> listaRes= new ArrayList<Reserva>();
				listaRes=	ctrlRes.getAll();
				
				
			Persona pers=ctrl.login(per);
			
			request.setAttribute("listaPersonas", ctrl.getAll());
			request.setAttribute("listaRes", listaRes);
			
			request.getSession().setAttribute("user", pers);
			
			
			request.setAttribute("otraPers", ctrl.getByDni("37563072"));
			
			
			
			
			request.getRequestDispatcher("WEB-INF/welcome.jsp").forward(request, response);
			//response.getWriter().append(user).append(" ").append(pass);
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//doGet(request, response);
	}
	
	

}