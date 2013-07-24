package com.envight.service.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.businesskey.BusinessKey;

@Entity
@Table( name = "SESSION" )
public class Session {
	private String sessionId; 
	private User user; 
	private Date start;	
	private Date finish;
	
	@PrePersist
	protected void onCreate() {
		start = new Date();
	}

    @BusinessKey
    @Id
	@Column( name = "SESSION_ID", unique = true, nullable = false )
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@ManyToOne( targetEntity = User.class )
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column( name = "START", nullable = false )
	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	@Column( name = "FINISH" )
	public Date getFinish() {
		return finish;
	}

	public void setFinish(Date finish) {
		this.finish = finish;
	} 
	
}
