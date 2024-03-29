package gm22_9.segundaEntregaFIS;

import java.util.Hashtable;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : gm22_9_califyMe
//  @ File Name : IPersistenciaAsig.java
//  @ Date : 28/05/2020
//
//



/**
 * Interfaz de la clase que da persistencia a las asignaturas
 *
 */
public interface IPersistenciaAsig {
	
	/**
     * Metodo que permite la inyeccion del modulo de conexion con la BBDD
     * @param conex
     */
	public void setConex(IConectorBBDD conex);
	
    /**
     * Registra una nueva asignatura en la BBDD
     * @param asig
     */
    public void create(Asignatura asig);
    
    /**
     * Devuelve todas las asignaturas de la BBDD
     * @param nom abreviatura de la asignatura
     * @return
     */
    public Asignatura read(String nom);
    
    /**
     * Devuelve todas las asignaturas de la BBDD
     * @return
     */
    public Hashtable<String, Asignatura> readAll();
    
    /**
     * Acutaliza una asignatura en la BBDD. Para simplificar la implementacion de este metodo para la entrega,
     * solo actualizara la lista de alumnos asociados a la asignatura, que es lo unico que puede cambiar en la entrega.
     * @param abrevAsigActualizar abreviatura de la asignatura a actualizar
     * @param newAlumnoCorreo
     */
    public void update(String abrevAsigActualizar, String newAlumnoCorreo);

    //Se ha removido el delete ya que no sera implementado para la entrega
}
