package gm22_9.segundaEntregaFIS;

import java.util.Hashtable;

/**
 * Clase Singleton con las variables globales de la aplicacion
 * esta clase guarda todas las instancias de Profesor, Alumno y Asignatura que maneja el sistema
 *
 */
public class VariablesGlobales {
	private Hashtable<String, Usuario> usuarios;
	private Hashtable<String, Asignatura> asignaturas;
	private static VariablesGlobales instanciaUnica;
	
	/**
	 * Constructor privado de Singleton
	 */
	private VariablesGlobales() {
		this.usuarios = new Hashtable<>();
		this.asignaturas = new Hashtable<>();
	}
	
	//Getters & Setters
	
	public Hashtable<String, Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(Hashtable<String, Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public Hashtable<String, Asignatura> getAsignaturas() {
		return asignaturas;
	}
	public void setAsignaturas(Hashtable<String, Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}
	
	//Metodos
	
	/**
	 * Devuelve la instancia unica del singleton
	 * @return
	 */
	public static VariablesGlobales getInstance() {
		if (instanciaUnica == null) {
			instanciaUnica = new VariablesGlobales();
		}
		return instanciaUnica;
	}

	@Override
	public String toString() { //SOLO USADO PARA DEPURACION
		return "VariablesGlobales [usuarios=" + usuarios + ", asignaturas=" + asignaturas + "]";
	}
}
