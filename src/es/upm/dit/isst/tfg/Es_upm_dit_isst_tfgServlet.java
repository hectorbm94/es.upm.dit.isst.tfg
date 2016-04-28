package es.upm.dit.isst.tfg;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.t4.dao.TFGDAO;
import es.upm.dit.isst.t4.dao.TFGDAOImpl;
import es.upm.dit.isst.t4.model.modelo;


@SuppressWarnings("serial")
public class Es_upm_dit_isst_tfgServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		UserService userService = UserServiceFactory.getUserService();
		String url = userService.createLoginURL(req.getRequestURI());
		String urlLinktext = "Login";
		String user = null;
		String profesor = null;
		
		TFGDAO dao = TFGDAOImpl.getInstance();
		
		RequestDispatcher view = req.getRequestDispatcher("MostrarTFGView.jsp");
		
		if (req.getUserPrincipal() != null){
			user = "Hola " + req.getUserPrincipal().getName();
			url = userService.createLogoutURL(req.getRequestURI());
			urlLinktext = "Logout";
			
			if (dao.readAlumno(req.getUserPrincipal().getName()).size() > 0){
				if(dao.readAlumno(req.getUserPrincipal().getName()).get(0).getEstado() != 3){
					req.getSession().setAttribute("tfgs", new ArrayList<modelo>(dao.readAlumno(req.getUserPrincipal().getName())));
					view = req.getRequestDispatcher("MostrarTFGView.jsp");
				} else {
					//view = req.getRequestDispatcher("FormularioMemoria.jsp");
				}
				
			} else if (dao.readTutor(req.getUserPrincipal().getName()).size() > 0){
				//view = req.getRequestDispatcher("Solicitudes.jsp");
				profesor = "si";
				req.getSession().setAttribute("tfgs", new ArrayList<modelo>(dao.readTutor(req.getUserPrincipal().getName())));
				req.getSession().setAttribute("alerta", null);
			} else{
				view = req.getRequestDispatcher("Formulario.jsp");
			}
		}
		
		req.getSession().setAttribute("profesor", profesor);
		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("url", url);
		req.getSession().setAttribute("urlLinktext", urlLinktext);

		try {
			view.forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}