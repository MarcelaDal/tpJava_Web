package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlers.CtrlABMCElementos;


@WebServlet({"/Elementos", "/elementos", "/Elemento", "/elemento"})
public class Elementos extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Elementos() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CtrlABMCElementos ctrl= new CtrlABMCElementos();
			request.setAttribute("listaTipoElementos", ctrl.getTipoElementos());
			request.getRequestDispatcher("WEB-INF/menuElementos.jsp").forward(request, response);
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
