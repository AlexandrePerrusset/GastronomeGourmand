package com.infotel.gg.city;

import com.infotel.gg.exception.ModelException;
import com.mysql.jdbc.StringUtils;

public class Country{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3992402814327252754L;
	private Integer id;
	private String name;
	
	
	//CONSTRUCTOR
	public Country(Integer id, String name) throws ModelException {
		this.setId(id);
		this.setName(name);
	}
	
	
	//GETTERS AND SETTERS
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 * @throws ModelException 
	 */
	public void setId(Integer id) throws ModelException {
		this.id = id;
		if(id != null && id > 0) {
			this.id = id;
		} else{
			throw new ModelException("Erreur Country id");
		}
	}
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 * @throws ModelException 
	 */
	public void setName(String name) throws ModelException {
		this.name = name;
		if(name != null && !StringUtils.isEmptyOrWhitespaceOnly(name)) {
			this.name = name;
		} else {
			throw new ModelException("Erreur Country name");
		}
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Country [getId()=" + getId() + ", getName()=" + getName() + "]";
	}
	
	
	
	
	
	
}
