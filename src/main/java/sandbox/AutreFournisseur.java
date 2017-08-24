package sandbox;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("autreFournisseur") // par défaut bean name == className camelCase ...
@Scope("singleton")  //par défaut ...
public class AutreFournisseur implements Fournisseur {
	
	@Value("${autreFournisseur.nom}")
	String nom;
	
	@Override
	public void traillerPourUnClient() {
		System.out.println("moi " + nom + ", Je travaille MIEUX pour un client ...");
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public AutreFournisseur() {
		System.out.println("AutreFournisseur()");
	}
	
	

}
