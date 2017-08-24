package fr.orsys.banque;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Compte {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger("fr.orsys.banque.Compte");

	int numero;
	float solde;
	float decouvertAutorise;

	private static int compteurCompte = 1;

	private List<Operation> lesOperations = new ArrayList<Operation>();

	public Compte(float depotInitial) {
		crediter(depotInitial);
		decouvertAutorise = depotInitial;
		numero = compteurCompte;
		compteurCompte++;
	}

	public int getNumero() {
		return numero;
	}

	public float getSolde() {
		return solde;
	}

	public float getDecouvertAutorise() {
		return decouvertAutorise;
	}

	public void crediter(float montant) {
		if (logger.isInfoEnabled()) {
			logger.info("crediter(float)"); //$NON-NLS-1$
		}

		solde += montant;
		lesOperations.add(new Operation(montant, Operation.CREDIT));
	}

	public void debiter(float montant) throws DebitNonAutoriseException {
		if (logger.isInfoEnabled()) {
			logger.info("debiter(float)"); //$NON-NLS-1$
		}
		if ((solde + decouvertAutorise) >= montant) {
			solde -= montant;
			lesOperations.add(new Operation(montant, Operation.DEBIT));
			return;
		}
		throw new DebitNonAutoriseException(montant, this);
	}
	
	public String toString() {
		return "No=" + numero + " solde=" + solde + " decouvert autorisé=" + decouvertAutorise;
	}
	
	public void editerReleve() {
		System.out.println("************************************");
		System.out.println( this);
		for(int i = 0; i < lesOperations.size(); i++) {
			lesOperations.get(i).editer();
		}
	}

	public List<Operation> getLesOperations() {
		// TODO Auto-generated method stub
		return lesOperations;
	}

	public static int getCompteurCompte() {
		return compteurCompte;
	}

	@SuppressWarnings("resource")
	public void traiterDecouvertNonAutorise(float montant) {
		if (logger.isInfoEnabled()) {
			logger.info("traiterDecouvertNonAutorise(float)"); //$NON-NLS-1$
		}
		System.out.println("**** traitement d'un débit non autorisé !! ******");
		editerReleve();
		System.out.println("Montant demandé :" + montant);
		System.out.println("accepter le débit ? " );
		String reponse = "oui";
		if(new Scanner(reponse).next().equals("oui")) {
			debitForce(montant);
		}
		
	}

	private void debitForce(float montant) {
		// TODO Auto-generated method stub
	}
	
}
