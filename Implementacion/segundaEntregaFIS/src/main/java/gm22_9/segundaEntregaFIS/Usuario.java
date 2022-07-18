package gm22_9.segundaEntregaFIS;

import java.util.Random;

import servidor.*;
import utilidades.Cifrado;

// Clase hecha manualmente, StarUML no permite generar codigo a partir de actores

/**
 * Clase abstracta de usuario, aporta funcionalidades y campo en comun ademas de persistencia 
 *
 */
public abstract class Usuario {
	
	private static IPersistenciaUsr persistencia = null; //La clase puede funcionar sin el componente de persistencia
	
	//Las siguientes dos dependencias deberian ser inyectadas al igual que el de la persistencia,
	//pero como la clase NO puede funcionar sin ellas, y para no cargar mas al constructor con parametros
	//se establecen directamente en el codigo.
	private static Autenticacion auth = new Autenticacion();
	private static Correo corr = new Correo();
	
	private String DNI;
	private String nombreUsuario;
	private String apellidos;
	private String correo;
	private String password;
	
	/**
	 * Constructor vacio creado solo para propositos de validacion.
	 */
	public Usuario() {
		DNI = null;
		this.nombreUsuario = null;
		this.apellidos = null;
		this.correo = null;
		this.password = null;
	}
	
	/**
	 * Constructor usado para crear los objetos a partir de los datos de la BBDD, desde el sistema, la creacion de
	 * nuevos Usuarios se realiza sin contraseña ya que esta se tiene que autogenerar la primera vez.
	 * @param dNI
	 * @param nombreUsuario
	 * @param apellidos
	 * @param correo
	 * @param password
	 */
	public Usuario(String dNI, String nombreUsuario, String apellidos, String correo, String password) {
		DNI = dNI;
		this.nombreUsuario = nombreUsuario;
		this.apellidos = apellidos;
		this.correo = correo;
		this.password = password;
	}

	/**
	 * Constructor que será usado por el sistema para dar de alta a un nuevo usuario.
	 * @param dNI
	 * @param nombreUsuario
	 * @param apellidos
	 * @param correo
	 */
	public Usuario(String dNI, String nombreUsuario, String apellidos, String correo) {
		DNI = dNI;
		this.nombreUsuario = nombreUsuario;
		this.apellidos = apellidos;
		this.correo = correo; //El correo ha sido prevalidado antes de
		password = autoGenerarPassword();
		
		corr.enviarEmail(correo, nombreUsuario, password); //Se envia el correo
		password = Cifrado.cifrar(password); //Luego se cifra la password para ser cargada en BBDD
		
		//La persistencia sera dejada a las clases hijas de Usuario para que 
	}
	
	//Funciones de las instancias
	
	/**
	 * Comprueba que la password tenga el formato aproiado y, si lo tiene, actualiza la contraseña
	 * @param nuevaPassword nueva password
	 * @return devuelve false si la password tiene un formato incorreto 
	 */
	public boolean cambiarPassword(String nuevaPassword) {
		boolean result = nuevaPassword.length() < 10? true : false; //Solo comprueba que tenga al menos 10 caracteres
		if (result) {
			this.password = Cifrado.cifrar(nuevaPassword);
		}
		return result;
	}
	
	//Getters & Setters
	public static IPersistenciaUsr getPersistencia() {
		return persistencia;
	}

	/**
	 * Inyector de dependencia opcional de persistencia 
	 * @param persistencia modulo de persistencia de usuario
	 */
	public static void setPersistencia(IPersistenciaUsr persistencia) {
		Usuario.persistencia = persistencia;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		cambiarPassword(password);
	}
	
	
	//Metodos estaticos de funcionalidad

	/**
	 * Comprueba si el correo pertenece o no a la ETSISI
	 * @param correo Correo a comprobar
	 * @return respuesta de comrobacion
	 */
	public static boolean esCorreoETSISI(String correo) {
		return auth.existeCuentaUPM(correo);
	}
	
	/**
	 * Comprueba que el correo introducido no se encuentre registrado a otro usuario en la BBDD, y por consecuencia, en la App.
	 * @param correo
	 * @return true si el correo no esta repetido
	 */
	public static boolean esCorreoRepetido(String correo) {
		return persistencia.read(correo) != null;
	}
	
	/**
	 * Metodo que recupera todas las instancias de la clase actual que esten almacenadas en la BBDD
	 * y las carga a las variables globales
	 */
	public static void fecthAllInstancesFromDB() {
		VariablesGlobales.getInstance().setUsuarios(persistencia.readAll());
	}
	
	/**
	 * Genera un String alfanumerico aleatorio de 10 caracteres
	 * @return String generado
	 */
	private static String autoGenerarPassword() {
		int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();
	 
	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	    
	    return generatedString;
	}

	@Override
	public String toString() { //SOLO PARA DEBUG Y VALIDACION
		return "DNI=" + DNI + ", nombreUsuario=" + nombreUsuario + ", apellidos=" + apellidos + ", correo="
				+ correo + ", password=" + password;
	}
	
}
