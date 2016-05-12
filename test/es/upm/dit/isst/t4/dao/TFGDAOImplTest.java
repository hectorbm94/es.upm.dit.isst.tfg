package es.upm.dit.isst.t4.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import es.upm.dit.isst.t4.model.modelo;

public class TFGDAOImplTest {
	
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

	@Before
	public void setUp() throws Exception {
		helper.setUp();
	}

	@After
	public void tearDown() throws Exception {
		helper.tearDown();
	}

	@Test
	public void testCreate() {
		TFGDAO tfgdao = TFGDAOImpl.getInstance();
		modelo tfg = tfgdao.create("hectorbm94@gmail.com", "ubertooth", "resumen", "juancarlos@gmail.com", "secretario@gmail.com", null, 1);
		assertEquals(tfg.getAutor(), "hectorbm94@gmail.com");
		assertEquals(tfg.getEstado(), 1);
		modelo tfg2 = tfgdao.create("autor@gmail.com", "titulo", "resumen", "tutor@gmail.com", "secretario@gmail.com", null, 2);
		assertEquals(tfg2.getAutor(), "autor@gmail.com");
		assertEquals(tfgdao.read().size(), 2);
		tfgdao.create("autor@gmail.com", "prueba", "resumen", "tutor@gmail.com", "secretario@gmail.com", null, 2);
		assertEquals(tfgdao.read().size(), 2);
		
		//fail("Not yet implemented");
	}

	@Test
	public void testReadAlumno() {
		TFGDAO tfgdao = TFGDAOImpl.getInstance();
		tfgdao.create("hectorbm94@gmail.com", "ubertooth", "resumen", "juancarlos@gmail.com", "secretario@gmail.com", null, 1);
		tfgdao.create("hector@gmail.com", "kali", "resumen", "juancarlos@gmail.com", "secretario@gmail.com", null, 1);
		List<modelo> TFGs = tfgdao.readAlumno("hectorbm94@gmail.com");
		assertEquals(TFGs.size(), 1);
		assertEquals(TFGs.get(0).getAutor(), "hectorbm94@gmail.com");
		//fail("Not yet implemented");
	}

	@Test
	public void testRead() {
		
		TFGDAO tfgdao = TFGDAOImpl.getInstance();
		tfgdao.create("hectorbm94@gmail.com", "ubertooth", "resumen", "juancarlos@gmail.com", "secretario@gmail.com", null, 2);
		tfgdao.create("hector@gmail.com", "kali", "resumen", "juancarlos@gmail.com", "secretario@gmail.com", null, 1);
		tfgdao.create("fatic94@gmail.com", "clima", "resumen", "villagra@gmail.com", "secretario@gmail.com", null, 2);
		
		List<modelo> TFGs = tfgdao.read();
		assertEquals(TFGs.size(), 3);

		//fail("Not yet implemented");
	}

	@Test
	public void testReadTutor() {
		TFGDAO tfgdao = TFGDAOImpl.getInstance();
		tfgdao.create("hectorbm94@gmail.com", "ubertooth", "resumen", "juancarlos@gmail.com", "secretario@gmail.com", null, 1);
		tfgdao.create("hector@gmail.com", "kali", "resumen", "juancarlos@gmail.com", "secretario@gmail.com", null, 1);
		List<modelo> TFGs = tfgdao.readTutor("juancarlos@gmail.com");
		assertEquals(TFGs.size(), 2);
		assertEquals(TFGs.get(0).getTutor(), "juancarlos@gmail.com");
		assertEquals(TFGs.get(1).getTutor(), "juancarlos@gmail.com");
		//fail("Not yet implemented");
	}

	@Test
	public void testReadEstado() {
		TFGDAO tfgdao = TFGDAOImpl.getInstance();
		tfgdao.create("hectorbm94@gmail.com", "ubertooth", "resumen", "juancarlos@gmail.com", "secretario@gmail.com", null, 3);
		tfgdao.create("hector@gmail.com", "kali", "resumen", "juancarlos@gmail.com", "secretario@gmail.com", null, 2);
		
		List<modelo> TFGs = tfgdao.readEstado(3);
		assertEquals(TFGs.size(), 1);
		assertEquals(TFGs.get(0).getAutor(), "hectorbm94@gmail.com");
		//fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		TFGDAO tfgdao = TFGDAOImpl.getInstance();
		tfgdao.create("hectorbm94@gmail.com", "ubertooth", "resumen", "juancarlos@gmail.com", "secretario@gmail.com", null, 3);
		tfgdao.create("hector@gmail.com", "kali", "resumen", "juancarlos@gmail.com", "secretario@gmail.com", null, 2);
		
		List<modelo> TFGs = tfgdao.readEstado(3);
		TFGs.get(0).setEstado(5);
		tfgdao.update(TFGs.get(0));
		
		List<modelo> TFGsupdate = tfgdao.readEstado(5);
		assertEquals(TFGsupdate.size(), 1);
		assertEquals(TFGsupdate.get(0).getAutor(), "hectorbm94@gmail.com");
		//fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		TFGDAO tfgdao = TFGDAOImpl.getInstance();
		tfgdao.create("hectorbm94@gmail.com", "ubertooth", "resumen", "juancarlos@gmail.com", "secretario@gmail.com", null, 3);
		tfgdao.create("hector@gmail.com", "kali", "resumen", "juancarlos@gmail.com", "secretario@gmail.com", null, 2);
		
		List<modelo> TFGs = tfgdao.read();
		assertEquals(tfgdao.read().size(), 2);
		
		tfgdao.delete(TFGs.get(0).getAutor());
		
		assertEquals(tfgdao.read().size(), 1);
		//fail("Not yet implemented");
	}

}
