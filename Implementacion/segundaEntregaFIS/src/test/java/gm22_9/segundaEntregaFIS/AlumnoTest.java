package gm22_9.segundaEntregaFIS;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AlumnoTest {
	Alumno alumno;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		alumno=new Alumno("12345678T","Pepe","Lopez Marino","pepeldm@ejemplo.com","password1","ab1234");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testToStringVacio() {
		Alumno alumno2=new Alumno(null, null, null, null, null, null);
		assertEquals("Alumno [matricula=null, asignaturasCursadas=[], DNI=null, nombreUsuario=null, apellidos=null, correo=null, password=null]",alumno2.toString());
	}
	
	@Test
	public void testToString() {
		HashSet<String> asignaturas=new HashSet<String>();
		asignaturas.add("FIS");
		asignaturas.add("FEE");
		alumno.setAsignaturasCursadas(asignaturas);
		assertEquals("Alumno [matricula=ab1234, asignaturasCursadas=[FIS, FEE], DNI=12345678T, nombreUsuario=Pepe, apellidos=Lopez Marino, correo=pepeldm@ejemplo.com, password=password1]",alumno.toString());
	}
	
	@Test
	public void testGetMatriculaNull() {
		Alumno alumno2=new Alumno(null, null, null, null, null, null);
		assertEquals(null,alumno2.getMatricula());
	}
	
	@Test
	public void testGetMatricula() {
		assertEquals("ab1234",alumno.getMatricula());
	}

	@Test
	public void testSetMatriculaNull() {
		alumno.setMatricula(null);
		assertEquals(null,alumno.getMatricula());
	}
	
	@Test
	public void testSetMatriculaVacio() {
		alumno.setMatricula("");
		assertEquals("",alumno.getMatricula());
	}
	
	@Test
	public void testSetMatricula() {
		alumno.setMatricula("ac1234");
		assertEquals("ac1234",alumno.getMatricula());
	}

	@Test
	public void testGetAsignaturasCursadasVacio() {
		HashSet<String> asigs=new HashSet<String>();
		alumno.setAsignaturasCursadas(asigs);
		assertEquals(asigs,alumno.getAsignaturasCursadas());
	}
	
	@Test
	public void testGetAsignaturasCursadas() {
		HashSet<String> asigs=new HashSet<String>();
		asigs.add("SI");
		alumno.setAsignaturasCursadas(asigs);
		assertEquals(asigs,alumno.getAsignaturasCursadas());
	}

	@Test
	public void testSetAsignaturasCursadas() {
		HashSet<String> asignaturasCursadas=new HashSet<String>();
		asignaturasCursadas.add("FIS");
		asignaturasCursadas.add("FEE");
		alumno.setAsignaturasCursadas(asignaturasCursadas);
		assertEquals(asignaturasCursadas,alumno.getAsignaturasCursadas());
	}
	
	@Test
	public void testSetAsignaturasCursadasSinAsignaturas() {
		HashSet<String> asignaturasCursadas=new HashSet<String>();
		alumno.setAsignaturasCursadas(asignaturasCursadas);
		assertEquals(asignaturasCursadas,alumno.getAsignaturasCursadas());
	}

	@Test
	public void testEstaMatriculadoEnAsigTrue() {
		HashSet<String> asignaturasCursadas=new HashSet<String>();
		asignaturasCursadas.add("FEE");
		alumno.setAsignaturasCursadas(asignaturasCursadas);
		assertEquals(true,alumno.estaMatriculadoEnAsig("FEE"));
	}
	
	@Test
	public void testEstaMatriculadoEnAsigFalse() {
		HashSet<String> asignaturasCursadas=new HashSet<String>();
		asignaturasCursadas.add("FIS");
		alumno.setAsignaturasCursadas(asignaturasCursadas);
		assertEquals(false,alumno.estaMatriculadoEnAsig("SI"));
	}

	@Test
	public void testMatricularEnAsigNueva() {
		HashSet<String> asignaturasCursadas=new HashSet<String>();
		asignaturasCursadas.add("FIS");
		asignaturasCursadas.add("FEE");
		alumno.setAsignaturasCursadas(asignaturasCursadas);
		alumno.matricularEnAsig("SI");
		assertEquals(true,alumno.estaMatriculadoEnAsig("SI"));
	}
	
	@Test
	public void testMatricularEnAsigDosVeces() {
		HashSet<String> asignaturasCursadas=new HashSet<String>();
		asignaturasCursadas.add("FIS");
		asignaturasCursadas.add("FEE");
		alumno.setAsignaturasCursadas(asignaturasCursadas);
		alumno.matricularEnAsig("FIS");
		assertEquals(true,alumno.estaMatriculadoEnAsig("FIS"));
	}
	
	
	
	
	//Hemos intentado probar los constructores pero nos ha sido imposible. 
	/*@Test
	public void testAlumnoStringStringStringStringStringString() {
		Alumno alumno=new Alumno("12345678T","Pepe","Lopez Marino","pepeldm@ejemplo.com","password1","ab1234");
		assertEquals(alumno,new Alumno("12345678T","Pepe","Lopez Marino","pepeldm@ejemplo.com","password1","ab1234"));
	}

	@Test
	public void testAlumnoStringStringStringStringString() {
		Alumno alumno=new Alumno("12345678T","Pepe","Lopez Marino","pepeldm@ejemplo.com","password","ab1234");
		assertEquals(alumno,(new Alumno("12345678T","Pepe","Lopez Marino","pepeldm@ejemplo.com","ab1234")).cambiarContrasenaAlum());
	}*/
}
