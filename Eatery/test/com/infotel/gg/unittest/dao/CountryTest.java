
package com.infotel.gg.unittest.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.infotel.gg.dao.jdbc.CountryDAO;
import com.infotel.gg.exception.DAOException;
import com.infotel.gg.exception.ModelException;
import com.infotel.gg.model.Country;
import DBUnit.DBUtils;



public class CountryTest {
	Country c ;
	CountryDAO cd = new CountryDAO();

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DBUtils.startDB();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		DBUtils.cleanDB();
	}

	
	@Before
	public void setUp() throws Exception {
	}

	
	@After
	public void tearDown() throws Exception {
		c=null;
	}

	@Test
	public void readOk() {
		assertNotNull("le pays n'est pas nul readOk",cd.read(1));
	}
	
	
	@Test
	public void readKo() {
		assertNull("le pays est nul readKo",cd.read(null));
	}
	
	@Test
	public void readKo2() {
		assertNull("le pays est nul readKo2",cd.read(5699965));
	}
	
	
	@Test
	public void CreateOk() throws DAOException, ModelException {
		c = new Country(2, "PaysFactice");
		cd.create(c);
		assertNotNull("le pays n'est pas nul CreateOk", cd.read(c.getId()));
	}
	

	@Test
	public void CreateOk2() throws DAOException, ModelException {
		c = new Country(50, "fef");
		cd.create(c);
		assertNotNull("le pays n'est pas nul CreateOk2", cd.read(c.getId()));
	}
	
	
	
	@Test(expected=DAOException.class)
	public void CreateKo2() throws DAOException, ModelException {
		c = new Country(4, "AgainFactice");
		cd.create(c);
		cd.create(c);
	}
	
	@Test
	public void deleteOk() throws DAOException, ModelException {	
		c = new Country(5, "FranceDelete");		
		cd.delete(c);		
		assertNull("le pays n'est plus pr�sent dans la base", cd.read(c.getId()));
	}
	
	@Test(expected=DAOException.class)
	public void deleteKo() throws DAOException, ModelException {		
		c = new Country(7, "Paysfake");		
		cd.delete(c);				
	}
	
	
	@Test
	public void deleteOk2() throws DAOException, ModelException {		
		cd.deleteById(6);	
		assertNull("le pays n'est plus pr�sent dans la base", cd.read(6));
	}
	
	
	@Test(expected=DAOException.class)
	public void deleteKo2() throws DAOException, ModelException {
	cd.deleteById(8);		
	}
	
	

}


