package fr.orsys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.orsys.entity.Compte;
import fr.orsys.service.Banque;


@RestController
@RequestMapping("/rest/compte")
public class CompteRestController {
	
	@Autowired
	Banque banque;
	
	@RequestMapping(name=""
			, method=RequestMethod.GET
			, produces={MediaType.APPLICATION_XML_VALUE,
	           		    MediaType.APPLICATION_JSON_VALUE})
	public List<Compte> getLesComptes() {
		return banque.getLesComptes();

	}
	
}
	
	
