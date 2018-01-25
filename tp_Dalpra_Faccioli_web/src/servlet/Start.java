package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

	CtrlABMCClientes ctrl= new CtrlABMCClientes();
    public Start() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
			Persona pers;
			try {
				pers = ctrl.login(per);
				if(pers!=null){
					CtrlABMCReservas ctrlRes=new CtrlABMCReservas();
					ArrayList<Reserva> listaRes= new ArrayList<Reserva>();
						listaRes=ctrlRes.getAll();				
					request.setAttribute("listaPersonas", ctrl.getAll());
					request.setAttribute("listaRes", listaRes);
					request.getSession().setAttribute("user", pers);
					request.getSession().setAttribute("elemento", null);
					request.getSession().setAttribute("tipoElemento", null);
					request.getSession().setAttribute("reserva", null);
					request.getSession().setAttribute("persona", null);
					request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
				}else{
					HttpSession session= request.getSession();
					session.setAttribute("error", "errorLogin");
					request.getRequestDispatcher("WEB-INF/errors.jsp").forward(request, response);
				}
				
				
			} catch (Exception e) {
				throw e;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//doGet(request, response);
	}
	
	

}