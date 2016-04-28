package es.upm.dit.isst.t4.dao;

import java.util.List;

import es.upm.dit.isst.t4.model.modelo;

public interface TFGDAO {
	
	public modelo create (String autor, String titulo, String resumen, String tutor, String secretario, String fichero, int estado);
	public List<modelo> readAlumno (String autor);
	public List<modelo> read ();
	public List<modelo> readTutor (String tutor);
	public List<modelo> readSecretario (String secretario);
	public List<modelo> readEstado (int estado);
	public void update (modelo TFG);
	public void delete (String autor);

}
