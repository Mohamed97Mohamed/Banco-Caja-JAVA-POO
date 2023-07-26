package es.poo;
import java.io.IOException;
import java.util.ArrayList;



public class Cliente {
	private String Nombre;
	private String Apellido1;
	private String Apellido2;
	private String Alias;
	private String Password;
	private String FechaNacimiento;
	private Boolean ClienteBloqueado = false;
	public ArrayList<Cuenta> Cuentas;
	public static ArrayList<Cliente> Clientes;
	
	//GETTERS
	public String getNombre() {
		return Nombre;
	}
	public String getApellido1() {
		return Apellido1;
	}
	public String getApellido2() {
		return Apellido2;
	}
	public String getAlias() {
		return Alias;
	}
	public String getFechaNacimiento() {
		return FechaNacimiento;
	}
	public Boolean getClienteBloqueado() {
		return ClienteBloqueado;
	}
	
	//SETTERS

	public void setClienteBloqueado(Boolean clienteBloqueado) {
		ClienteBloqueado = clienteBloqueado;
	}
	
	//MÉTODOS
	
	/**
	 * Para iniciar sesión, al no haber getter de la contraseña, realizo aqui la comprobación si la contraseña corresponde
	 * @param pass: Contraseña introducida para compararla con la del usuario
	 * @return boolean: Si coincide o no con la del usuario
	 */
	
	public boolean PassMATCH(String pass) {
		if(this.Password.equals(pass)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**Paso al usuario los datos introducidos para realizar el registro
	 * 
	 * @param nombre: nombre
	 * @param apellido1: primer apellido
	 * @param apellido2: segundo apellido
	 * @param aliass: alias
	 * @param pass: contraseña
	 * @param fecha: fecha de nacimiento
	 */
	
	public void RegistroDeUsuario(String nombre, String apellido1, String apellido2, String aliass, String pass, String fecha) {
		this.Nombre = nombre;
		this.Apellido1 = apellido1;
		this.Apellido2 = apellido2;
		this.Alias = aliass;
		this.Password = pass;
		this.FechaNacimiento = fecha;
	}
	
	/**
	 * Función en la que muestro los datos que se han introducido para el usuario que se encuentra activo
	 */
	
	public void InformacionDeUsuario() {
		System.out.println("\nInformación de Usuario:");
		if(E.isValidAlias(this.getNombre())) {
			System.out.println("Nombre: " + this.getNombre());
		}
		else {
			System.out.println("Nombre: No especificado");
		}
		if(E.isValidAlias(this.getApellido1())) {
			System.out.println("Apellido1 " + this.getApellido1());
		}
		else {
			System.out.println("Apellido1: No especificado");
		}
		if(E.isValidAlias(this.getApellido2())) {
			System.out.println("Apellido2 " + this.getApellido2());
		}
		else {
			System.out.println("Apellido2: No especificado");
		}
		System.out.println("Alias: " + this.getAlias());
		System.out.println("Fecha nacimiento: " + this.getFechaNacimiento() + "\n");
		
		System.out.println("Cuentas:");
		
		int a;
		Cuenta comprobacion;
		
		for(a = 0; a < this.Cuentas.size() ; a++) {//Muestro por pantalla las cuentas que tiene el cliente activo, al igual que sus respectivos saldos y numeros de cuenta
			comprobacion = this.Cuentas.get(a);
			System.out.println("-'" + comprobacion.getNombreCuenta() + "':\tSaldo: " + comprobacion.getSaldo() + "€\tDatos Cuenta: " + Cuenta.Entidad + " " + Cuenta.Oficina + " " 
					+ comprobacion.getDC() + " " + String.format("%010d", comprobacion.getCuenta()) + " " 
					+ "ES" + String.format("%02d", comprobacion.getIban()));
		}
	}
	
	
	
	
	/**Esta función ejecuta la función de crear una nueva cuenta para el objeto Cliente Usuario. Una vez que se crea una nueva cuenta, se cambia la cuenta en la que se está
	 * iniciada la sesión por esta.
	 * @return: Se pasa la variable i, que es la posición de la cuenta creada en el arraylist de cuentas.
	 * @throws IOException : Cuando se produce un error en la entrada de datos.
	 */
	
	public int CrearNuevaCuenta() throws IOException {
		
		boolean aliasCuentaValid = false;
		String alias = null;
		
		while(aliasCuentaValid == false) {
			
			System.out.println(TEXTOS.CREARCUENTAS);
			alias = E.Scan();
			boolean aliasRep = false;
			
			int a = 0;
			for(a = 0; a < this.Cuentas.size() && aliasRep == false; a++) {//Compruebo que el alias que se introduce para la nueva cuenta no exista
				Cuenta newCuenta;
				newCuenta = this.Cuentas.get(a);
				if(newCuenta.getNombreCuenta().equals(alias)) {
					System.out.println(TEXTOS.CREARCUENTAS1);
					aliasRep = true;
				}
			}
			if(aliasRep == true) {}
			else {aliasCuentaValid = true;}
		}
		
		Cuenta NuevaCuenta = new Cuenta();
		
		if(E.isValidAlias(alias) == true) {//Compruebo si el alias introducido es válido. Si no lo es, se le asigna el literal
			NuevaCuenta.setNombreCuenta(alias);
		}
		else {
			NuevaCuenta.setNombreCuenta("Cuenta " + String.format("%010d", NuevaCuenta.getCuenta()));
		}
		
		this.Cuentas.add(NuevaCuenta);//Guardo la nueva cuenta creada en el arraylist de cuentas
		
		Cuenta comprobacion;
		boolean foractivo = true;
		
		int i = 0;
		int a;
		for (a = 0; a < this.Cuentas.size() && foractivo == true ; a++) {
			comprobacion = this.Cuentas.get(a);
			if(NuevaCuenta.getNombreCuenta() == comprobacion.getNombreCuenta()) {
				i = a;//Guardo la posición que ocupa en el arraylist de cuentas de esta nueva cuenta para no tener que calcularla posteriormente
				foractivo = false;
			}
		}
		return i;
	}
	
	/**
	 * Esta función ejecuta la opción de cambio de cuenta. Muestra que cuentas tiene creadas el usuario y las muestra por pantalla.
	 * 
	 * @return i: Se pasa la posición de la cuenta selecionada en el arraylist de cuentas
	 * @throws IOException : Cuando se produce un error en la entrada de datos.
	 */
	
	public int CambiarDeCuenta() throws IOException {
		
		System.out.println(TEXTOS.CREARCUENTAS2);
		
		int a;
		Cuenta comprobacion;
		
		for(a = 0; a < this.Cuentas.size() ; a++) {//Muestro por pantalla las cuentas que tiene el cliente activo, al igual que sus respectivos saldos y numeros de cuenta
			comprobacion = this.Cuentas.get(a);
			System.out.println("-'" + comprobacion.getNombreCuenta() + "':\tSaldo: " + comprobacion.getSaldo() + "€\tDatos Cuenta: " + Cuenta.Entidad + " " + Cuenta.Oficina + " " 
					+ comprobacion.getDC() + " " + String.format("%010d", comprobacion.getCuenta()) + " " 
					+ "ES" + String.format("%02d", comprobacion.getIban()));
			
		}
		
		boolean bucle = true;
		String cadenaNombre;
		
		int i = 0;
		while(bucle==true) {
			System.out.println(TEXTOS.CREARCUENTAS3);
			cadenaNombre = E.Scan();
			if(cadenaNombre.equals("cancelar")) {
				bucle = false;
			}
			else {
				boolean nombreEncontrado = false;
				boolean foractivo = true;
				for(a=0 ; a < this.Cuentas.size() && foractivo==true; a++) {//Compruebo que existe la cuenta cuyo alias ha sido especificado, para evitar cualquier fallo al introducir el nombre de esta
					comprobacion = this.Cuentas.get(a);
					if(cadenaNombre.equals(comprobacion.getNombreCuenta())) {
						i = a;
						foractivo = false;
						nombreEncontrado = true;
					}
				}
				if(nombreEncontrado == true) {
					System.out.println(TEXTOS.CREARCUENTAS4);
					bucle = false;
				}
				else {
					System.out.println(String.format(TEXTOS.CREARCUENTAS5, cadenaNombre));
				}
			}	
		}
		return i;
	}	
}