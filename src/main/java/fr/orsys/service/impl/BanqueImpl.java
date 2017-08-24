package fr.orsys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import fr.orsys.dao.CompteDao;
import fr.orsys.dao.impl.CompteDaoJpa;
import fr.orsys.entity.Compte;
import fr.orsys.entity.CompteEpargne;
import fr.orsys.entity.DebitNonAutoriseException;
import fr.orsys.service.Banque;


@Component
public class BanqueImpl implements Banque {
	
	private String nom;
	private String codeBanque;
	
	@Autowired
	CompteDao compteDao ;
	
	
	@Autowired
	public BanqueImpl(@Value("${banque.nom}") String nom, @Value("${banque.codeBanque}")String codeBanque) {
		this.nom = nom;
		this.codeBanque = codeBanque;
	}


	@Override
	public List<Compte> getLesComptes() {
		// TODO Auto-generated method stub
		return compteDao.LoadAll();
	}

	@Override
	public int ouvrirCompte(float depotInitial) {
		Compte compte = new Compte(depotInitial);
		return compteDao.save(compte);
	}

	@Override
	public int ouvrirCompteEpargne(float depotInitial, float taux) {
		Compte compte = new CompteEpargne(depotInitial, taux);
		return compteDao.save(compte);
	}
	

	@Override
	public Compte rechercherCompte(int numero) {
		// TODO Auto-generated method stub
		return compteDao.load(numero);
	}

	@Override
	public float fermerCompte(int numero) {
		 Compte c = rechercherCompte(numero);
		 if(c!=null && c.getSolde()>=0) {
			 compteDao.delete(c);
			 return c.getSolde();
		 }
		 System.out.println("fermeture impossible !");
		 return 0;	 
	}

	@Override
	public void crediter(int idCompte, float montant) {
		compteDao.load(idCompte).crediter(montant);

	}

	@Override
	public void debiter(int idCompte, float montant)
			throws DebitNonAutoriseException {
		compteDao.load(idCompte).debiter(montant);

	}

	@Override
	public void effectuerVirement(int idCpt1, float montant, int idCpt2)
			throws DebitNonAutoriseException {
		debiter(idCpt1, montant);
		crediter(idCpt2, montant);
	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return nom;
	}

	@Override
	public String getCodeBanque() {
		// TODO Auto-generated method stub
		return codeBanque;
	}

}
