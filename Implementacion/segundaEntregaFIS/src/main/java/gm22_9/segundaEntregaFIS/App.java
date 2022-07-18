package gm22_9.segundaEntregaFIS;

/**
 * Clase con el main(), despliega toda la App y enlaza sus componenetes de manera ordenada.
 * 
 * GM22 – Grupo 9 - CalifyMe - FIS - 2020.
 * Integrantes: 
 * - Miguel Pascual Sánchez: Líder de la fase de requisitos
 * - Estrella Nogueira Arévalo: Líder de la fase de análisis.
 * - Pablo Espín Marín: Líder de la fase de diseño.
 * - Luis M. Martin Lopez: Líder de la fase de implementación.
 * - Violeta Macías de Miguel: Líder de la fase de validación.
 *
 */
public class App 
{
	//Clases Singleton relacionadas con la BBDD y persistencia
	private static IConectorBBDD conector; //Recordar cambiar los datos de conexion con la BBDD en ConectorBBDD.java de ser necesario
	private static IPersistenciaAsig perAsig;
	private static IPersistenciaUsr perUsr;
	
	//Clases Singleton de los controladores
	private static IAsignatura controlAsig;
	private static IGestionProfesor controlProf;
	
    public static void main( String[] args )
    {
    	//System.out.println("HELL-OH WOrld");
    	
    	instanciarEInyectarDependencias();
    	instanciarControladores();
    	
    	/*
    	 * En esta version de implementacion, la "pagina de inicio" es la GUI gestora de prof
    	 * ya que el Profesor es Actor que puede dar de Alta a un Alumno y a una Asignatura, ademas
    	 * de que puede gestionar aquellas asignaturas a las que esta asociado. De modo que,
    	 * para agilizar la implementacion y para no complicar mucho el codigo, la navegacion 
    	 * de la GUI de Profesor a la GUI de Asignaturas se hara a traves de controlador de 
    	 * Gestion de Profesores. En la version "completa" de esta aplicacion se podria 
    	 * implementar un modulo de navegacion que se encargue especificamente de la navegacion 
    	 * dentro de la App.
    	 */
    	controlProf.inyectarModuloNavegacion(controlAsig);
    	
    	//Se carga la app con el mock del profesor en la BBDD:
    	controlProf.cargarVista((Profesor)controlProf.getInfoUsr("profesor_fis@upm.es")); //Es importante que la BBDD esté conectada.
        
    }
    
    /**
     * Instancia e inyecta las dependencias, en particular aquellas de que otorgan la persistencia
     * y que establecen la conexion con la BBDD.
     */
    private static void instanciarEInyectarDependencias() {
    	//Instancia las clases de persistencia
    	conector = ConectorBBDD.getInstancia();
    	perAsig = PersistenciaAsig.getInstancia();
    	perUsr = PersistenciaUsr.getInstance();
    	
    	//Inyecta el conector de la BDD en las clases de persistencia
    	perAsig.setConex(conector);
        perUsr.setConex(conector);
        
        //Inyecta la instancia de persistencia en las clases de Modelo
        Asignatura.setPersistencia(perAsig);
        Usuario.setPersistencia(perUsr);
        
        //Carga las variables globales con los datos de la BBDD
        Asignatura.fecthAllInstancesFromDB();
        Usuario.fecthAllInstancesFromDB();
    }
    
    /**
     * Instancia los controladores
     */
    private static void instanciarControladores() {
    	controlAsig = CAsignatura.getInstanciaUnica();
    	controlProf = CGestionProfesor.getInstancia();
    }
}
