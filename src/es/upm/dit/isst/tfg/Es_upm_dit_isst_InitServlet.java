package es.upm.dit.isst.tfg;

import javax.servlet.http.HttpServlet;

import es.upm.dit.isst.t4.dao.TFGDAO;
import es.upm.dit.isst.t4.dao.TFGDAOImpl;

@SuppressWarnings("serial")
public class Es_upm_dit_isst_InitServlet extends HttpServlet {

	public void init(){
		
		TFGDAO dao = TFGDAOImpl.getInstance();
		
		//dao.create("hectorbm94@gmail.com", "Ubertooth", "Herramientas de ataque Bluetooth con Ubertooth", "villagra@dit.upm.es", "", "", 2);
		dao.create("fatigc94@gmail.com", "Clima Laboral", "Herramienta de ciberseguridad, aplicada al clima laboral", "villagra@dit.upm.es", "", "", 1);
		dao.create("cobealex@gmail.com", "Ataques Kali Linux", "Base de datos de ataques de Kali Linux", "villagra@dit.upm.es", "", "", 3);
		dao.create("cpsalazar17@gmail.com", "Everis", "Confidencial", "villagra@dit.upm.es", "", "", 1);
	}
}