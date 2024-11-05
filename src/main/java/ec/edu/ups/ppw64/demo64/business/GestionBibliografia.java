package ec.edu.ups.ppw64.demo64.business;

import java.util.List;

import ec.edu.ups.ppw64.demo64.dao.BibliografiaDAO;
import ec.edu.ups.ppw64.demo64.model.Bibliografia;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionBibliografia {

	@Inject
	private BibliografiaDAO daoBibliografia;
	
	public void guardarBibliografia(Bibliografia bibliografia) {
		// TODO Auto-generated method stub
		Bibliografia bib = daoBibliografia.read(bibliografia.getIdCurso());
		if (bib != null){
			daoBibliografia.update(bibliografia);
		}else {
			daoBibliografia.insert(bibliografia);
		}
	}
	
	public void actualizarBibliografia(Bibliografia bibliografia) throws Exception {
		// TODO Auto-generated method stub
		Bibliografia bib = daoBibliografia.read(bibliografia.getIdCurso());
		if (bib != null){
			daoBibliografia.update(bibliografia);
		}else {
			throw new Exception("Bibliografia no existe");
		}
	}
	
	public Bibliografia getBibliografiaPorID(int idCurso) throws Exception {
		// TODO Auto-generated method stub
		Bibliografia bib = daoBibliografia.read(idCurso);
		if(bib != null) {
			return daoBibliografia.getBibliografiaPorID(idCurso);
		}else {
			throw new Exception("Bibliografia no existe");
		}
	}
	
	public void borrarBibliografia(int idCurso) {
		// TODO Auto-generated method stub
		daoBibliografia.delete(idCurso);
	}
	
	public List<Bibliografia> getBibliografia() {
		// TODO Auto-generated method stub
		return daoBibliografia.getList();
	}

}
