package ec.edu.ups.ppw64.demo64.dao;

import java.util.List;

import ec.edu.ups.ppw64.demo64.model.Bibliografia;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class BibliografiaDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public void insert(Bibliografia bibliografia) {
		em.persist(bibliografia);
	}
	
	public void update(Bibliografia bibliografia) {
		em.merge(bibliografia);
	}
	
	public Bibliografia read(int idCurso){
		Bibliografia b = em.find(Bibliografia.class, idCurso);
		return b ;
	}
	public void delete(int idCurso){
		Bibliografia b = em.find(Bibliografia.class, idCurso);
		em.remove(b);
	}
	
	public List<Bibliografia> getList(){
		String jsql = "SELECT b FROM Bibliografia b";
		Query query = em.createQuery(jsql,Bibliografia.class);
		List<Bibliografia> lista = query.getResultList();
		return lista;
	}
	
	public Bibliografia getBibliografiaPorID(int idCurso){
		String jpql = "SELECT b FROM Bibliografia b WHERE b.idCurso = :idCurso";
		Query q = em.createQuery(jpql, Bibliografia.class);
		q.setParameter("idCurso", idCurso);
		List<Bibliografia> bibliografias = q.getResultList();
		if(bibliografias.size()>0)
			return bibliografias.get(0);
		return null;
	}

}
