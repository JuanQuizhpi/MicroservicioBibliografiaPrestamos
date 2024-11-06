package ec.edu.ups.ppw64.demo64.dao;

import java.util.List;

import ec.edu.ups.ppw64.demo64.model.Usuario;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class UsuarioDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public void insert(Usuario usuario) {
		em.persist(usuario);
	}
	
	public void update(Usuario usuario) {
		em.merge(usuario);
	}
	
	public Usuario read(String cedula){
		Usuario u = em.find(Usuario.class, cedula);
		return u ;
	}
	public void delete(String cedula){
		Usuario u = em.find(Usuario.class, cedula);
		em.remove(u);
	}
	
	public List<Usuario> getList(){
		String jsql = "SELECT u FROM Usuario u";
		Query query = em.createQuery(jsql,Usuario.class);
		List<Usuario> lista = query.getResultList();
		return lista;
	}
	
	public Usuario getUsuaroPorCedula(String cedula){
		String jpql = "SELECT u FROM Usuario u WHERE u.cedula = :cedula";
		Query q = em.createQuery(jpql, Usuario.class);
		q.setParameter("cedula", cedula);
		List<Usuario> usuarios = q.getResultList();
		if(usuarios.size()>0)
			return usuarios.get(0);
		return null;
	}

}
