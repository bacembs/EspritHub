package tn.esprit.esprithub.services;


import tn.esprit.esprithub.entities.Cagnotte;

import java.util.List;

public interface ICagnotteService {

	
	List<Cagnotte> retrieveAllCagnottes();

	Cagnotte addCagnotte(Cagnotte c);
	Cagnotte addCagnotteUser(Cagnotte c, Long idUser);

	Cagnotte updateCagnotte(Cagnotte c);

	Cagnotte retrieveCagnotte(Long id);

	void removeCagnotte(Long id);
	Cagnotte updateSomme(Cagnotte c);
	
}
