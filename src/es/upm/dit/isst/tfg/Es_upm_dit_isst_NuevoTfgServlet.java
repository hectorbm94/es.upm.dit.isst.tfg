package es.upm.dit.isst.tfg;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import es.upm.dit.isst.t4.dao.TFGDAO;
import es.upm.dit.isst.t4.dao.TFGDAOImpl;
import es.upm.dit.isst.t4.model.modelo;

@SuppressWarnings("serial")
public class Es_upm_dit_isst_NuevoTfgServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		String titulo= req.getParameter("titulo");
		String resumen= req.getParameter("resumen");
		String tutor= req.getParameter("tutor");
		
		String alerta = null;
		
		TFGDAO dao = TFGDAOImpl.getInstance();
		
		List<modelo> tfgs = dao.readAlumno(req.getUserPrincipal().getName());
		if ( tfgs.size()>0){
			alerta = "Usted ya tiene un TFG";
		}
		else{
			dao.create(req.getUserPrincipal().getName(),titulo,resumen,tutor,"","", 1);
			Properties props = new Properties();
			Session session = Session.getDefaultInstance(props, null);

			String msgBody = "El alumno " + req.getUserPrincipal().getName() + " ha solicitado un TFG suyo";

			try {
				Message msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress("tfg@decent-tape-127214.appspotmail.com", "Sistema de gestion de TFGs"));
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(tutor, "Alumno"));
				msg.setSubject("Solicitado un TFG suyo");
				msg.setText(msgBody);
				Transport.send(msg);

			    } catch (AddressException e) {
			        // ...
			    } catch (MessagingException e) {
			        // ...
			    }
			alerta = "Su TFG esta pendiente de aprobacion";
		}
		req.getSession().setAttribute("alerta", alerta);
		resp.sendRedirect("/es_upm_dit_isst_tfg");
	}


}
