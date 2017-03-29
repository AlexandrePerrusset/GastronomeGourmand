
package com.infotel.gg.unittest.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.infotel.gg.dao.CookingStyleDAO;
import com.infotel.gg.exception.DAOException;
import com.infotel.gg.exception.ModelException;
import com.infotel.gg.hibernate.CookingStyleDAOHbn;
import com.infotel.gg.model.CookingStyle;
import DBUnit.DBUtils;


public class CookingStyleTest {
	CookingStyle cs;
	CookingStyleDAO csd = new CookingStyleDAOHbn();
	


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DBUtils.startDB();
	}

	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//DBUtils.cleanDB();
	}

	
	@Before
	public void setUp() throws Exception {
	}

	
	@After
	public void tearDown() throws Exception {
		cs=null;
	}

	@Test
	public void readOk() {
		assertNotNull("CookingStyle n'est pas nulle readOk",csd.read(1));
	}
	
	@Test
	public void readOk2() {
		assertNotNull("CookingStyle n'est pas nulle readOk2",csd.read(2));
	}

	@Test
	public void readKo2() {
		assertNull("CookingStyle est nulle readKo2",csd.read(5699965));
	}
	
	@Test
	public void CreateOk() throws DAOException, ModelException {
		cs = new CookingStyle(141, "CookingStyleFactice");
		csd.create(cs);
		assertNotNull("CookingStyle n'est pas nulle CreateOk", csd.read(cs.getId()));
	}
	
	@Test
	public void CreateOk2() throws DAOException, ModelException {
		cs = new CookingStyle(142, "AutreCookingStyleFactice");
		csd.create(cs);
		assertNotNull("CookingStyle n'est pas nulle CreateOk2", csd.read(cs.getId()));
	}
	
	
	
	@Test(expected=DAOException.class)
	public void CreateKo() throws DAOException, ModelException {
		cs = new CookingStyle(144, "AgainFactice");
		csd.create(cs);
		csd.create(cs);
	}
	
	
	
	
	


}
