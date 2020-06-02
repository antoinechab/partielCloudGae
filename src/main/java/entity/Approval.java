package entity;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
 
@Entity
@Cache
public class Approval {
	@Id private Long id;
	private String nom;
	private float montant;
	private boolean risk;
	private boolean decision;
	
	public Approval() {}
	
	public Approval(String nom, float montant, boolean risk, boolean decision) {	
		this.setNom(nom);
		this.setMontant(montant);
		this.setRisk(risk);
		this.setDecision(decision);
	}
	
	@Override
	public String toString() {
		return "demande de: " + getNom() + " pour un montant de: " + getMontant() + " et un risque: " + isRisk() + " et une decision: " + isDecision();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public float getMontant() {
		return montant;
	}

	public void setMontant(float montant) {
		this.montant = montant;
	}

	public boolean isRisk() {
		return risk;
	}

	public void setRisk(boolean risk) {
		this.risk = risk;
	}

	public boolean isDecision() {
		return decision;
	}

	public void setDecision(boolean decision) {
		this.decision = decision;
	}

}