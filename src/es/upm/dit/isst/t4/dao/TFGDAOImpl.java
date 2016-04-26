package es.upm.dit.isst.t4.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.t4.model.modelo;

public class TFGDAOImpl implements TFGDAO {
	
	private static TFGDAOImpl instance;
	
	private TFGDAOImpl() {
	}
	
	public static TFGDAOImpl getInstance() {
		if (instance == null)
			instance = new TFGDAOImpl();
		return instance;
	}


	@Override
	public modelo create(String autor, String titulo, String resumen,
			String tutor, String secretario, String fichero, int estado) {
		
		EntityManager em = EMFService.get().createEntityManager();
		
		modelo TFG = new modelo(autor, titulo, resumen, tutor, secretario, fichero, estado);
		em.persist(TFG);
		em.close();
		return TFG;
	}

	@Override
	public List<modelo> readAlumno(String autor) {
		
		EntityManager em = EMFService.get().createEntityManager();
		
		Query q = em.createQuery("select t from modelo t where t.autor = :autor");
	    q.setParameter("autor", autor);

		List<modelo> TFGs = q.getResultList();
		em.close();
		return TFGs;
	}

	@Override
	public List<modelo> read() {
		
		EntityManager em = EMFService.get().createEntityManager();
		String consulta = "select t from modelo t";
		
		Query q = em.createQuery(consulta);
		List<modelo> TFGs = q.getResultList();
		
		em.close();
		return TFGs;
	}

	@Override
	public List<modelo> readTutor(String tutor) {
		
		EntityManager em = EMFService.get().createEntityManager();
		
		Query q = em.createQuery("select t from modelo t where t.tutor = :tutor");
	    q.setParameter("tutor", tutor);
		List<modelo> TFGs = q.getResultList();
		em.close();
		return TFGs;
	}
	
	@Override
	public List<modelo> readSecretario(String secretario) {

		EntityManager em = EMFService.get().createEntityManager();
		
		Query q = em.createQuery("select t from modelo t where t.secretario = :secretario");
	    q.setParameter("secretario", secretario);
		List<modelo> TFGs = q.getResultList();
		em.close();
		return TFGs;
	}

	@Override
	public List<modelo> readEstado(int estado) {

		EntityManager em = EMFService.get().createEntityManager();
		
		Query q = em.createQuery("select t from modelo t where t.estado = :estado");
	    q.setParameter("estado", estado);
		List<modelo> TFGs = q.getResultList();
		em.close();
		return TFGs;
	}

	@Override
	public void update(modelo TFG) {

		EntityManager em = EMFService.get().createEntityManager();
		em.merge(TFG);
		em.close();
	}

	@Override
	public void delete(modelo TFG) {

		EntityManager em = EMFService.get().createEntityManager();
		em.remove(TFG);
		em.close();
	}

}
