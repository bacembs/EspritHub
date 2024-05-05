package tn.esprit.esprithub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprithub.entities.Cagnotte;
import tn.esprit.esprithub.services.ICagnotteService;


import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/cagnotte")
public class CagnotteRestController {
	
	@Autowired
	ICagnotteService cagnotteInterface;
	
	@GetMapping("/getAllCagnotte")
	@ResponseBody
	public List<Cagnotte> getCagnottes() {
	List<Cagnotte> listCagnotte = cagnotteInterface.retrieveAllCagnottes();
	return listCagnotte;
	}
	
	@GetMapping("/getCagnotte/{id}")
	@ResponseBody
	public Cagnotte retrieveCagnotte(@PathVariable("id") Long idCagnotte) {
	return cagnotteInterface.retrieveCagnotte(idCagnotte);
	}
	
	@PostMapping("/add-getCagnotte")
	@ResponseBody
	public Cagnotte addCagnottet(@RequestBody Cagnotte c)
	{
		Cagnotte cagnotte = cagnotteInterface.addCagnotte(c);
	return cagnotte;
	}
	
	@DeleteMapping("/remove-cagnotte/{idCagnotte-id}")
	@ResponseBody
	public void removeCagnotte(@PathVariable("idCagnotte-id") Long idCagnotte) {
		cagnotteInterface.removeCagnotte(idCagnotte);
	}
	
	@PutMapping("/modify-cagnotte")
	@ResponseBody
	public Cagnotte updateCagnotte(@RequestBody Cagnotte c) {
	return cagnotteInterface.updateCagnotte(c);
	}
	
	@PutMapping("/modify-somme")
	@ResponseBody
	public Cagnotte updateSomme(@RequestBody Cagnotte c) {
	return cagnotteInterface.updateSomme(c);
	}
	
	@PostMapping("/add-getCagnotte-user/{user-id}")
	@ResponseBody
	public Cagnotte addCagnotteUser(@RequestBody Cagnotte c,@PathVariable("user-id")Long idUser)
	{
		Cagnotte cagnotte = cagnotteInterface.addCagnotteUser(c,idUser);
	return cagnotte;
	}

}
