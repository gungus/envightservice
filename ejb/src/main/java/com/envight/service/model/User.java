package com.envight.service.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.businesskey.BusinessKey;

@Entity
@Table( name = "USER" )
public class User {
	private Long id;	
	private Date registeredDate; 
	private String email; 
	private String password; // encrypted. 
	
	@PrePersist
	protected void onCreate() {
		registeredDate = new Date(); 
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
		
	@Column( name = "REGISTERED_DATE", nullable = false )
	public Date getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}
	
	@BusinessKey
	@Column( name = "EMAIL", unique = true, nullable = false )
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column( name = "PASSWORD", nullable = false )
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
