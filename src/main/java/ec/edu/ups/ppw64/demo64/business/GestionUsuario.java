package ec.edu.ups.ppw64.demo64.business;

import java.util.List;

import ec.edu.ups.ppw64.demo64.dao.UsuarioDAO;
import ec.edu.ups.ppw64.demo64.model.Usuario;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionUsuario {
	
	@Inject
	private UsuarioDAO daoUsuario;
	
	public void guardarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		Usuario usu = daoUsuario.read(usuario.getCedula());
		if (usu != null){
			daoUsuario.update(usuario);
		}else {
			daoUsuario.insert(usuario);
		}
	}
	
	public void actualizarUsuario(Usuario usuario) throws Exception {
		// TODO Auto-generated method stub
		Usuario usu = daoUsuario.read(usuario.getCedula());
		if (usu != null){
			daoUsuario.update(usuario);
		}else {
			throw new Exception("Usuario no existe");
		}
	}
	
	public Usuario getUsuarioPorCedula(String cedula) throws Exception {
		// TODO Auto-generated method stub
		Usuario usu = daoUsuario.read(cedula);
		if(usu != null) {
			return daoUsuario.getUsuaroPorCedula(cedula);
		}else {
			throw new Exception("Usuario no existe");
		}
	}
	
	public void borrarUsuario(String cedula) {
		// TODO Auto-generated method stub
		daoUsuario.delete(cedula);
	}
	
	public List<Usuario> getUsuario() {
		// TODO Auto-generated method stub
		return daoUsuario.getList();
	}
}
