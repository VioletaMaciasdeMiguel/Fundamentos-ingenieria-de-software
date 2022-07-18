package gm22_9.segundaEntregaFIS;

import java.util.HashSet;

//Clase hecha manualmente, StarUML no permite generar codigo a partir de actores

/**
 * Clase representativa del actor Profesor
 *
 */
public class Profesor extends Usuario {
	
	private String codEmpleado;
	private String codDepartamento;
	private HashSet<String> asignaturasAsociadas;
	// No se incluyen otros campos como Encuestas Asociadas por no ser relevantes para la entrega.
	
	/**
	 * Constructor usado para crear los objetos a partir de los datos de la BBDD (incluye password de la BBDD).
	 * @param dNI
	 * @param nombreUsuario
	 * @param apellidos
	 * @param correo
	 * @param password
	 * @param codEmpleado
	 * @param codDepartamento
	 * @param asignaturasAsociadas
	 */
	public Profesor(String dNI, String nombreUsuario, String apellidos, String correo, String password,
			String codEmpleado, String codDepartamento) {
		super(dNI, nombreUsuario, apellidos, correo, password);
		this.codEmpleado = codEmpleado;
		this.codDepartamento = codDepartamento;
		this.asignaturasAsociadas = new HashSet<String>();
	}
	
	/**
	 * Instancia y da de Alta a un Profesor. No usado por no existir Admin en esta version.
	 * @param dNI
	 * @param nombreUsuario
	 * @param apellidos
	 * @param correo
	 * @param password
	 * @param codEmpleado
	 * @param codDepartamento
	 * @param asignaturasAsociadas
	 */
	public Profesor(String dNI, String nombreUsuario, String apellidos, String correo, String codEmpleado, String codDepartamento) {
		super(dNI, nombreUsuario, apellidos, correo);
		
		this.codEmpleado = codEmpleado;
		this.codDepartamento = codDepartamento;
		this.asignaturasAsociadas = new HashSet<String>();
		//No usado
	}
	
	//Getters & Setters

	public String getCodEmpleado() {
		return codEmpleado;
	}

	public void setCodEmpleado(String codEmpleado) {
		this.codEmpleado = codEmpleado;
	}

	public String getCodDepartamento() {
		return codDepartamento;
	}

	public void setCodDepartamento(String codDepartamento) {
		this.codDepartamento = codDepartamento;
	}

	public HashSet<String> getAsignaturasAsociadas() {
		return asignaturasAsociadas;
	}

	public void setAsignaturasAsociadas(HashSet<String> asignaturasAsociadas) {
		this.asignaturasAsociadas = asignaturasAsociadas;
	}
	
	/**
	 * Da de alta a un nuevo alumno, el correo ha de estar previamente validado.
	 * @param dNI
	 * @param nombreUsuario
	 * @param apellidos
	 * @param correo
	 * @param matricula
	 * @return nuevo Alumno.
	 */
	public Alumno darAltaAlumno(String dNI, String nombreUsuario, String apellidos, String correo, String matricula) {
		return new Alumno(dNI, nombreUsuario, apellidos, correo, matricula);
	}
	
	/**
	 * Da de alta a una nueva asignatura
	 * @param nombre
	 * @param abrev
	 * @param ects
	 * @return nueva asignatura
	 */
	public Asignatura darAltaAsignatura(String nombre, String abrev, int ects) {
		Asignatura nuevaAsig = new Asignatura(nombre, abrev, ects, this.getCorreo());
		asignaturasAsociadas.add(abrev);
		
		return nuevaAsig;
	}
	
	/**
	 * Devuelve si el profesor esta asociado o no a una asignatura
	 * @param asig
	 * @return
	 */
	public boolean estaAsociadoAsig(String abreviacionAsig) {
		return asignaturasAsociadas.contains(abreviacionAsig);
	}
	
	/**
	 * Asocia al profesor en la asignatura
	 * @param asig
	 */
	public void asociarAsig(String abreviacionAsig) {
		if (!estaAsociadoAsig(abreviacionAsig)) {
			asignaturasAsociadas.add(abreviacionAsig);
			//asig.asociarProf(this.getCorreo()); NO OLVIDAR ACTUALIZAR A LA ASIGNATURA PARA PRESERVAR LA CONSISTENCIA
		}
	}

	@Override
	public String toString() { //SOLO PARA DEBUG Y VALIDACION
		return "Profesor [codEmpleado=" + codEmpleado + ", codDepartamento=" + codDepartamento
				+ ", asignaturasAsociadas=" + asignaturasAsociadas + ", getDNI()=" + getDNI() + ", getNombreUsuario()="
				+ getNombreUsuario() + ", getApellidos()=" + getApellidos() + ", getCorreo()=" + getCorreo()
				+ ", getPassword()=" + getPassword() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}
	
	//En esta version, se supone que un profesor no se puede desasociar de una asignatura.
}
