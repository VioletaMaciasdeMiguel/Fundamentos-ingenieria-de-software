package gm22_9.segundaEntregaFIS;

import java.util.HashSet;

// Clase hecha manualmente, StarUML no permite generar codigo a partir de actores

/**
 * Clase representativa del actor Alumno
 *
 */
public class Alumno extends Usuario {
	
	private String matricula;
	private HashSet<String> asignaturasCursadas;
	
	/**
	 * Constructor creado para realizar test de JUnit
	 * @param dNI
	 * @param nombreUsuario
	 * @param apellidos
	 * @param correo
	 * @param password
	 * @param matricula
	 */
	public Alumno() {
		super();
		this.matricula = null;
		this.asignaturasCursadas = new HashSet<String>();
	}
	
	/**
	 * Constructor creado para cargar los datos de un alumno desde la BBDD (uno que ya incluya password)
	 * @param dNI
	 * @param nombreUsuario
	 * @param apellidos
	 * @param correo
	 * @param password
	 * @param matricula
	 */
	public Alumno(String dNI, String nombreUsuario, String apellidos, String correo, String password,
			String matricula) {
		
		super(dNI, nombreUsuario, apellidos, correo, password);
		this.matricula = matricula;
		this.asignaturasCursadas = new HashSet<String>();
	}
	
	/**
	 * Instancia y da de Alta a un Alumno desde la app (asigna automaticamente una password)
	 * @param dNI
	 * @param nombreUsuario
	 * @param apellidos
	 * @param correo
	 * @param matricula
	 */
	public Alumno(String dNI, String nombreUsuario, String apellidos, String correo, String matricula) {
		super(dNI, nombreUsuario, apellidos, correo);

		this.matricula = matricula;
		this.asignaturasCursadas = new HashSet<String>();
	}

	//Getter & Setters
	
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public HashSet<String> getAsignaturasCursadas() {
		return asignaturasCursadas;
	}

	public void setAsignaturasCursadas(HashSet<String> asignaturasCursadas) {
		this.asignaturasCursadas = asignaturasCursadas;
	}
	
	//Funciones particulares
	
	/**
	 * Indica si el alumno esta matriculado en esa asignatura
	 * @param abreviacionAsig
	 * @return devuelve true si esta matriculado
	 */
	public boolean estaMatriculadoEnAsig(String abreviacionAsig) {
		return asignaturasCursadas.contains(abreviacionAsig);
	}
	
	/**
	 * Matricula el alumno a una asignatura si no esta previamente matriculado
	 * @param asig
	 */
	public void matricularEnAsig(String abreviacionAsig) {
		if (!estaMatriculadoEnAsig(abreviacionAsig)) {
			asignaturasCursadas.add(abreviacionAsig);
		}
	}
	
	//No se implemento cambiar password
	/*public Alumno cambiarContrasenaAlum() {
		super.setPassword("password");
		return this;
	}*/

	//En esta version de la implementacion, una alumno no puede desmatricularse de una asignatura.
	
	@Override
	public String toString() { //Para depuracion y validacion
		return "Alumno [matricula=" + matricula + ", asignaturasCursadas=" + asignaturasCursadas + ", " + super.toString()+"]";
	}
	
	
}

