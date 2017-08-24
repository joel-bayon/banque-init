package sandbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class UnFournisseur implements Fournisseur {
	int age;
	
	@Override
	public void traillerPourUnClient() {
		System.out.println("Je traivaille pour un client ...");
	}
	
	public UnFournisseur() {
		System.out.println("UnFournisseur()");
	}
	
	
	@Autowired
	public UnFournisseur(@Value("22") int age) {
		this.age = age;
		System.out.println("UnFournisseur(int age))");
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	

}
