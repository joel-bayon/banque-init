package fr.orsys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.orsys.entity.Compte;
import fr.orsys.service.Banque;


@Controller
//@RequestMapping("/")
public class HelloController {
	@Autowired
	Banque banque;
	
	public HelloController () {
		System.out.println("**** instanciation de HelloController ******");
	}
	
	@RequestMapping("/comptes")
	public String listerComptes(Model model) {
		System.out.println("/comptes");
		List<Compte> lc = banque.getLesComptes();
		model.addAttribute("comptes", lc);
		return "listerComptes";
		
	}

}
