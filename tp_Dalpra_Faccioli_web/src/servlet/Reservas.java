package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlers.CtrlABMCReservas;

@WebServlet({ "/Reservas", "/reservas", "/reserva", "/Reserva" })
public class Reservas extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public Reservas() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CtrlABMCReservas ctrl=new CtrlABMCReservas();
			request.setAttribute("tipoElementos", ctrl.getTipoElementos());
			
			request.setAttribute("elementos", ctrl.getElementos());
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("WEB-INF/menuReservas.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	

}