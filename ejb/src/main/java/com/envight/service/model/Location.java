package com.envight.service.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.businesskey.Bean;
import org.hibernate.businesskey.BusinessKey;

// @todo Should long-term be refactored into Position. (draft) http://dev.w3.org/geo/api/spec-source.html#position_interface

@Entity
@Table(name = "LOCATION")
public class Location extends Bean implements Serializable {
	private static final long serialVersionUID = 5400717198658643067L;

	private Long id;	
	private double latitude;
	private double longitude;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@BusinessKey
	@Column(name = "LATITUDE", nullable = false)
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@BusinessKey
	@Column(name = "LONGITUDE", nullable = false)
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}
