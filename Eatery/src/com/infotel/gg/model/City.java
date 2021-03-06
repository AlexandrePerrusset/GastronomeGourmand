package com.infotel.gg.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity @Table(name="city")
public class City implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2406884507752812014L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="post_code")
	private String postCode;
	
	@Column(name="foremost")
	private boolean foreMost;
	
	@ManyToOne @JoinColumn(name="region_id", unique=true)
	private Region region;
	
	@ManyToOne @JoinColumn(name="country_id", unique=true)
	private Country country;
	
	
	//CONSTRUCTOR
	public City(int id, String name, String postCode, boolean foreMost) {
		this.setId(id);
		this.setName(name);
		this.setPostCode(postCode);
		this.setForemost(foreMost);
	}
	
	
	
	public City(int id, String name, String postCode, boolean foreMost, Region region, Country country) {
		super();
		this.id = id;
		this.name = name;
		this.postCode = postCode;
		this.foreMost = foreMost;
		this.region = region;
		this.country = country;
	}



	public City() {
	}
	
	/**
	 * @return the foreMost
	 */
	public boolean isForeMost() {
		return foreMost;
	}

	/**
	 * @param foreMost the foreMost to set
	 */
	public void setForeMost(boolean foreMost) {
		this.foreMost = foreMost;
	}

	//GETTERS AND SETTERS
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * @return the postCode
	 */
	public String getPostCode() {
		return postCode;
	}
	/**
	 * @param postCode the postCode to set
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	
	/**
	 * @return the foremost
	 */
	public boolean isForemost() {
		return foreMost;
	}
	/**
	 * @param foremost the foremost to set
	 */
	public void setForemost(boolean foremost) {
		this.foreMost = foremost;
	}
	
	
	/**
	 * @return the region
	 */
	public Region getRegion() {
		return region;
	}
	/**
	 * @param region the region to set
	 */
	public void setRegion(Region region) {
		this.region = region;
	}
	
	
	/**
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "City [getId()=" + getId() + ", getName()=" + getName() + ", getPostCode()=" + getPostCode()
				+ ", isForemost()=" + isForemost() + ", getRegion()=" + getRegion() + ", getCountry()=" + getCountry()
				+ "]";
	}
	
	
	
	
	
	
	
}
