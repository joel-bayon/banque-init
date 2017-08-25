package fr.orsys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import fr.orsys.entity.Compte;
import fr.orsys.entity.DebitNonAutoriseException;
import fr.orsys.service.Banque;


@RestController
@RequestMapping("/rest/compte")
public class CompteRestController {
	
	@Autowired
	Banque banque;
	
	@RequestMapping(value=""
			, method=RequestMethod.GET
			, produces={MediaType.APPLICATION_XML_VALUE,
	           		    MediaType.APPLICATION_JSON_VALUE})
	public List<Compte> getLesComptes() {
		return banque.getLesComptes();

	}
	
	@RequestMapping(value="/{numero}"
			, method=RequestMethod.GET
			, produces={MediaType.APPLICATION_XML_VALUE,
	           		    MediaType.APPLICATION_JSON_VALUE})
	public Compte getCompte(@PathVariable int numero) {
		return banque.rechercherCompte(numero);

	}
	
	@RequestMapping(value="/{numero}"
			, method=RequestMethod.PUT
			, produces={MediaType.APPLICATION_XML_VALUE,
	           		    MediaType.APPLICATION_JSON_VALUE})
	public Compte updateCompte(@PathVariable int numero, @RequestParam float montant, @RequestParam String operation) throws DebitNonAutoriseException {
		
		if("credit".equals(operation))
			banque.crediter(numero, montant);
		else
			banque.debiter(numero, montant);
		return banque.rechercherCompte(numero); 

	}
	
}
	
	
