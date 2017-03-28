package fr.orsys.banque;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Operation {
	private float montant;
	private String type;
	private Date date;
	
	public final static String CREDIT = "CREDIT";
	public final static String DEBIT = "DEBIT";
	
	public Operation(float montant, String type) {
		this.montant = montant;
		this.type = type;
		date = new Date();	
	}
	
	public float getMontant() {
		return montant;
	}
	public String getType() {
		return type;
	}
	public Date getDate() {
		return date;
	}
	
	public void editer() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
		System.out.println("type=" + type + " montant=" + montant 
				                   + " date=" + dateFormat.format(date));
	}

}
