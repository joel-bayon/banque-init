package fr.orsys.entity;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Compte {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger("fr.orsys.banque.Compte");

	@Id
	int numero;
	float solde;
	float decouvertAutorise;

	private static int compteurCompte = 1;

	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true)
	@JoinColumn(name="COMPTE_NUMERO")
	private List<Operation> lesOperations = new ArrayList<Operation>();

	public Compte(float depotInitial) {
		crediter(depotInitial);
		decouvertAutorise = depotInitial;
		numero = compteurCompte;
		compteurCompte++;
	}
	
	public Compte() {}

	public int getNumero() {
		return numero;
	}

	public float getSolde() {
		return solde;
	}

	public float getDecouvertAutorise() {
		return decouvertAutorise;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public void setSolde(float solde) {
		this.solde = solde;
	}

	public void setDecouvertAutorise(float decouvertAutorise) {
		this.decouvertAutorise = decouvertAutorise;
	}

	public void setLesOperations(List<Operation> lesOperations) {
		this.lesOperations = lesOperations;
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
		throw new fr.orsys.entity.DebitNonAutoriseException(montant,this);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(decouvertAutorise);
		result = prime * result + numero;
		result = prime * result + Float.floatToIntBits(solde);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compte other = (Compte) obj;
		if (Float.floatToIntBits(decouvertAutorise) != Float
				.floatToIntBits(other.decouvertAutorise))
			return false;
		if (numero != other.numero)
			return false;
		if (Float.floatToIntBits(solde) != Float.floatToIntBits(other.solde))
			return false;
		return true;
	}

	
}
