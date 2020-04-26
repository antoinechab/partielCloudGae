package entity;

import java.time.LocalDate;
import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Approval {
    @Id Long id;
    @Index String name;
    @Index Boolean risk;
    @Index Float amount;
    @Index Date createdAt;
    @Index Boolean decision;

    public Approval() {}
    
    public Approval(String name, Boolean risk, Float amount, Boolean decision ) {
    	this.name = name;
    	this.risk = risk;
    	this.amount = amount;
    	this.decision = decision;
    	this.createdAt = new Date();
    }
    
    public String getName() {
        return name;
    }
    
    public Boolean getRisk() {
    	return risk;
    }
    
    public Float getAmount() {
    	return amount;
    }
    
    public Date getCreatedAt() {
    	return createdAt;
    }
    
    public Boolean getDecision() {
    	return decision;
    }
    
}