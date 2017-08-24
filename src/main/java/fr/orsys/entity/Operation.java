package fr.orsys.entity;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Operation {
	@Id
	@Column(name="ID_OPERATION")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private float montant;
	@Column(nullable=false)
	private String type;
	@Temporal(TemporalType.DATE)
	private Date date;
	
	public final static String CREDIT = "CREDIT";
	public final static String DEBIT = "DEBIT";
	
	public Operation(float montant, String type) {
		this.montant = montant;
		this.type = type;
		date = new Date();	
	}
	
	public Operation() {}
	
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
	
	

	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
		return "type=" + type + " montant=" + montant  + " date=" + dateFormat.format(date);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setMontant(float montant) {
		this.montant = montant;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
