package fr.orsys.banque;



public class App {

	public static void main(String[] args) {
	
		
		Banque banque = new Banque("Crédit Agricole", "123123");
		
		int id1 = banque.ouvrirCompte(100);
		int id2 = banque.ouvrirCompteEpargne(10000, 0.2f);
		
		Compte c1 = banque.getCompte(id1);
		Compte c2 = banque.getCompte(id2);
		
		c1.crediter(10);
		try {
			c1.debiter(100);
		} catch (DebitNonAutoriseException e) {
			e.getCompte().traiterDecouvertNonAutorise(e.getMontant());
		}
		c2.crediter(1000);
		try {
			c2.debiter(21000);
		} catch (DebitNonAutoriseException e) {
			e.getCompte().traiterDecouvertNonAutorise(e.getMontant());
		}
		try {
			c1.debiter(1);
		} catch (DebitNonAutoriseException e) {
			e.getCompte().traiterDecouvertNonAutorise(e.getMontant());
		}
		try {
			c2.debiter(1);
		} catch (DebitNonAutoriseException e) {
			e.getCompte().traiterDecouvertNonAutorise(e.getMontant());
		}
		
		banque.editerReleves();
		banque.produireInterets();
		banque.editerReleves();
		
		System.out.println("\n**** Fin simulation de la banque *****\n");
			
	}
}
