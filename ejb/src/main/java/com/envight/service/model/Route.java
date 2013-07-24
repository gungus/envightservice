package com.envight.service.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.businesskey.BusinessKey;

@Entity
@Table( name = "ROUTE" )
public class Route {
	private Long id;
	private Session session; 
	private Date sentAt;
	private Date loggedAt;
	private Location location;

	@PrePersist
	protected void onCreate() {
		loggedAt = new Date();
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne( targetEntity = Session.class )
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

    @BusinessKey
	@Column( name = "SENT_AT", nullable = false )
	public Date getSentAt() {
		return sentAt;
	}

	public void setSentAt(Date sentAt) {
		this.sentAt = sentAt;
	}

	@Column( name = "LOGGED_AT", nullable = false )
	public Date getLoggedAt() {
		return loggedAt;
	}

	public void setLoggedAt(Date loggedAt) {
		this.loggedAt = loggedAt;
	}

	@ManyToOne( targetEntity = Location.class )
	public Location getLocation() {
		return location;
	}

	@ManyToOne
	public void setLocation( Location location ) {
		this.location = location;
	}
}
