package fr.orsys.banque;
//classe Banque
import java.util.HashMap;
import java.util.Map;

public class Banque {
	
	private String nom;
	private String codeBanque;
	
	//
	private Map<Integer, Compte> lesComptes = 
							new HashMap<Integer, Compte>();
	
	public Banque(String nom, String codeBanque) {
		this.nom = nom;
		this.codeBanque = codeBanque;
	}

	public String getNom() {
		return nom;
	}

	public String getCodeBanque() {
		return codeBanque;
	}
	
	public int ouvrirCompte(float depotInitial) {
		Compte compte = new Compte(depotInitial);
		lesComptes.put(compte.getNumero(), compte);
		return compte.getNumero();
		
	}

	public Compte getCompte(int numero) {
		return lesComptes.get(numero);
	}
	
	public float fermerCompte(int numero) {
		 Compte c = getCompte(numero);
		 if(c!=null && c.getSolde()>=0) {
			 lesComptes.remove(numero);
			 return c.getSolde();
		 }
		 System.out.println("fermeture impossible !");
		 return 0;	 
	}
	
	public void editerReleves() {
		for(Compte c : lesComptes.values()) {
			System.out.println("banque=" + nom + " code banque=" + codeBanque);
			c.editerReleve();
		}
	}
	
	public void produireInterets() {
		for(Compte c : lesComptes.values()) {
			if (c instanceof CompteEpargne)
				((CompteEpargne) c).produireInterets();
		}
	}

	public int ouvrirCompteEpargne(float depotInitial, float taux) {
		Compte compte = new CompteEpargne(depotInitial, taux);
		lesComptes.put(compte.getNumero(), compte);
		return compte.getNumero();
	}

	public Map<Integer, Compte>  getLesCompte() {
		// TODO Auto-generated method stub
		return lesComptes;
	}
	
	public void effectuerVirement(int source, float 	
			montant, int destination) 
			throws DebitNonAutoriseException {
		getCompte(source).debiter(montant);
		getCompte(destination).crediter(montant);
		
	}

	
}
