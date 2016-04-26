package es.upm.dit.isst.tfg;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.t4.dao.TFGDAO;
import es.upm.dit.isst.t4.dao.TFGDAOImpl;
import es.upm.dit.isst.t4.model.modelo;

@SuppressWarnings("serial")
public class Es_upm_dit_isst_aceptarTFGServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		String autor = req.getParameter("autor");
		
		TFGDAO dao = TFGDAOImpl.getInstance();
		
		List<modelo> TFGs = dao.readAlumno(autor);
		if (TFGs.size() > 0){
			
			if(TFGs.get(0).getEstado() == 1){				
				
				TFGs.get(0).setEstado(2);
				dao.update(TFGs.get(0));
			}
		}
		req.getSession().setAttribute("alerta", null);		
		resp.sendRedirect("/es_upm_dit_isst_tfg");
	}

}
