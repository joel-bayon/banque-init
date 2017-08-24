package sandbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class Client {
	@Autowired
	@Qualifier("autreFournisseur")
	Fournisseur f ;
	
	public void faireQQChose() {
		f.traillerPourUnClient();
	}

	public Fournisseur getF() {
		return f;
	}

	public void setF(Fournisseur f) {
		this.f = f;
	}
	
	
}
