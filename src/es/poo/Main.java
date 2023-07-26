package es.poo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	/**
	 * Menú que aparece al ejecutar el programa, y te da la opción de iniciar sesión, registrarse o terminar la ejecución del programa
	 * También se hace la comprobación de si existe contenido, guardado mediante persistencia, de ejecuciones anteriores, y si existen, los carga en memoria.
	 * @throws NumberFormatException : Cuando el valor introducido no corresponde con un número
	 * @throws IOException : Cuando se produce un error en la entrada de datos, ya sea pedidos por pantalla o al cargar el archivo json
	 */
	
	public static void Menu() throws IOException {
		
		try {
			Cliente.Clientes = PersistenciaGson.persistenciaFromJSON();//Intento cargar en memoria desde el archivo Json el ArrayList de clientes de ejecuciones anteriores
		}
		catch(FileNotFoundException e) {}//Si el archivo no existe, continuo la ejecución sin lanzar excepciones
		
		boolean haycuentas = true;
		try {
			Cliente.Clientes.size();//Compruebo, en caso de cargarse el archivo, cuantos clientes hay para poder obtener el numero de cuentas que existen
		}
		catch(NullPointerException e) {
			haycuentas = false;
		}

		if(haycuentas == true) { //Si hay clientes en el arraylist, compruebo cada uno para ver cuantas cuentas tienen en total entre todos los clientes para evitar que el numero de cuenta de las próximas cuentas que cree sea iguales
			int i;
			int ContadorDeCuentasCCC = 0;
			for(i=0 ; i<Cliente.Clientes.size() ; i++) {
				Cliente comprobador;
				comprobador = Cliente.Clientes.get(i);
				boolean haycuentasEnUser = true;
				try {
					comprobador.Cuentas.size();//Compruebo si los arraylist de cuentas de cada usuario tiene cuentas creadas o no
				}
				catch(NullPointerException e) {
					haycuentasEnUser = false;
				}
				if(haycuentasEnUser == true) {
					ContadorDeCuentasCCC = ContadorDeCuentasCCC + comprobador.Cuentas.size();//Aumento contador para obtener el total de cuentas
				}
			}
			Cuenta.CCCC = ContadorDeCuentasCCC;//Guardo el contador de cuentas en la variable estática CCCC
		}
		
		boolean EjecucionPrograma = true;
		while(EjecucionPrograma == true) {
			try {
				System.out.println(TEXTOS.BIENVENIDO);
				System.out.println(TEXTOS.BIENVENIDO1);
				System.out.println(TEXTOS.BIENVENIDO2);
				System.out.println(TEXTOS.BIENVENIDO3);
				System.out.println(TEXTOS.BIENVENIDO4);
				System.out.println(TEXTOS.BIENVENIDO5);
				
				int a = Integer.parseInt(E.Scan());
	
				switch (a) {
				case 1:
						IniciarSesion();
						break;
				case 2: 
						Registrarse();
						break;
				case 3:
						System.out.println(TEXTOS.BIENVENIDO6);
						EjecucionPrograma = false;
						break;
				default:
					System.out.println(String.format(TEXTOS.BIENVENIDO7, a));
				}
			}
			catch(NumberFormatException e) {
				System.out.println(TEXTOS.BIENVENIDO8);
			}
		}
	}
	
	
	
	
	/**
	 * Desde la función Menu se le pasa el objeto Cliente Usuario y comprueba si existe o no usuarios.
	 * En caso de existir, te muestra los usuarios que hay creados y, en caso contrario, te muestra por pantalla que no hay usuarios creados y te devuelve al Menu
	 *
	 * @throws IOException : Cuando se produce un error en la entrada de datos.
	 */
	
	
	public static void IniciarSesion() throws IOException {
		Cliente Usuario = new Cliente();
		
		boolean haycuenta = true;
		try {
			Cliente.Clientes.size();//Compruebo si existen clientes en el arraylist estático
		}
		catch(NullPointerException e) {
			haycuenta = false;
		}
		if(haycuenta==false) {
			
			System.out.println(TEXTOS.INICIARSESION);
		}
		else {
			
			System.out.println(TEXTOS.INICIARSESION1);//Muestro por pantalla cuantos usuarios existen en el arraylist de clientes
			int i;
			for(i=0 ; i<Cliente.Clientes.size() ; i++) {
				Cliente bucle_de_busqueda;
				bucle_de_busqueda = Cliente.Clientes.get(i);
				if(bucle_de_busqueda.getClienteBloqueado() == false) {
					System.out.println(bucle_de_busqueda.getAlias());
				}
				else {
					System.out.println(bucle_de_busqueda.getAlias() + "(Bloqueado)");//Si el cliente está bloqueado, se muestra entre paréntesis
				}
			}
			
			System.out.println("--------------------------------------------------------------------------------");
			
			int UsuarioID = 0;
			
			boolean cancelar = false;
			boolean inicioSesion = false;
			while(inicioSesion == false && cancelar == false) {
				System.out.println(TEXTOS.INICIARSESION2);
				String username = E.Scan();
				
				if(username.equals("cancelar")) {
					cancelar = true;
				}
				else {
					for(i=0 ; i<Cliente.Clientes.size() && inicioSesion == false; i++) {//Busco en el arraylist de clientes y compruebo que el alias introducido existe
						Cliente bucle_de_busqueda;
						bucle_de_busqueda = Cliente.Clientes.get(i);
						if(bucle_de_busqueda.getAlias().equals(username)) {
							inicioSesion = true;
							UsuarioID = i;//Si existe el alias introducido, me guardo su posición en el arraylist de clientes para mayor facilidad
							Usuario = bucle_de_busqueda;//Igualo el objeto cliente que corresponde con el alias en el objeto Usuario inicializado
						}
					}
					if(inicioSesion == false) {
						System.out.println(TEXTOS.INICIARSESION3);
					}
				}
			}
			
			if(Usuario.getClienteBloqueado() == true && cancelar == false) {//Compruebo si el cliente está bloqueado
				System.out.println(TEXTOS.INICIARSESION4);
			}
			else if(Usuario.getClienteBloqueado() == false && cancelar == false) {
				boolean contrasenya = false;
				int IncorrectPass = 0;
				
				while(contrasenya == false && Usuario.getClienteBloqueado() == false && cancelar == false) {
					System.out.println(TEXTOS.INICIARSESION5);
					String password = E.Scan();
					if(Usuario.PassMATCH(password) == true) {
						System.out.println(TEXTOS.INICIARSESION6);
						contrasenya = true;
					}
					else if(password.equals("cancelar")) {
						cancelar = true;
					}
					else {
						System.out.println(TEXTOS.INICIARSESION7);
						IncorrectPass++;//Incremento la variable cuando la contraseña introducida no corresponde con la del usuario
						if(IncorrectPass == 3)//Si el contador llega a 3, el cliente se bloquea
							Usuario.setClienteBloqueado(true);
					}
				}
				if(contrasenya == true) {//Cuando se termina el bucle while, compruebo si la contraseña introducida corresponde, e inicio el Menu de Usuario
					Cliente.Clientes.set(UsuarioID, Usuario);
					Main.MenuUsuario(Usuario, UsuarioID);
				}
				else if(Usuario.getClienteBloqueado() == true){//Si el cliente se bloquea, muestro mensaje por pantalla y actualizo el cliente en su posición del arraylist
					System.out.println(TEXTOS.INICIARSESION8);
					Cliente.Clientes.set(UsuarioID, Usuario);
				}
			}
		}
	}
	
	/**
	 * En esta función, a la que se le pasa el objeto Usuario ya inicializado, se van pidiendo todos los datos necesarios para la creación del usuario.
	 * Permite saltarse con un intro el nombre y los apellidos, pero el alias, la contraseña y la fecha de nacimiento son obligatorios, siendo obligatorio que el alias sea
	 * de al menos una letra o un numero.
	 * 
	 * @throws IOException : Cuando se produce un error en la entrada de datos, ya sea pedidos por pantalla o al cargar el archivo json
	 */
	
	public static void Registrarse() throws IOException {
		Cliente Usuario = new Cliente();
		
		boolean fechavalida = false;
		
		System.out.println(TEXTOS.REGISTRARSE);
		String nombre = E.Scan();
		
		System.out.println(TEXTOS.REGISTRARSE1);
		String apellido1 = E.Scan();
		
		System.out.println(TEXTOS.REGISTRARSE2);
		String apellido2 = E.Scan();
		
		String aliass = null;
		boolean AliasRepetido = true;
		int i = 0;
		
		boolean haycuentas = true;
		try {
			Cliente.Clientes.size();//Compruebo si hay clientes creados, para evitar que se creen nuevos con el mismo alias
		}
		catch(NullPointerException e) {
			haycuentas = false;
		}
		
		while(AliasRepetido == true) {//Inicio un bucle que solo termina cuando se introduce un alias valido y no repetido
			System.out.println(TEXTOS.REGISTRARSE4);
			aliass = E.Scan();
			boolean AliasRepetidoFound = false;
			
			if(haycuentas == true) {
				for(i=0 ; i<Cliente.Clientes.size() && AliasRepetidoFound == false; i++) {
					Cliente bucle_de_busqueda;
					bucle_de_busqueda = Cliente.Clientes.get(i);
					if(bucle_de_busqueda.getAlias().equals(aliass)) {
						System.out.println(String.format(TEXTOS.REGISTRARSE5, aliass));
						AliasRepetidoFound = true;
					}
				}
				if(AliasRepetidoFound == false) {
					if(aliass.equals("")) {//El alias no puede quedarse vacio
						System.out.println(TEXTOS.REGISTRARSE6);
					}
					else if(E.isValidAlias(aliass) == false) {
						System.out.println(TEXTOS.REGISTRARSE7);
					}
					else{
						AliasRepetido = false;
					}
				}

			}
			else if(aliass.equals("")) {//Vuelvo a comprobar el contenido del alias introducido, ya que las anteriores comprobaciones son si existen clientes creados en el arraylist, y estas comprobaciones son para cuando no exixte ninguno
				System.out.println(TEXTOS.REGISTRARSE6);
			}
			else if(E.isValidAlias(aliass) == false) {
				System.out.println(TEXTOS.REGISTRARSE7);
			}
			else {
				AliasRepetido = false;
			}
		}
		
		String pass = null;
		boolean ValidPass = false;
		
		while(ValidPass == false) {
			System.out.println(TEXTOS.REGISTRARSE8);
			pass = E.Scan();
			if(pass.length() < 6) {
				System.out.println(TEXTOS.REGISTRARSE9);
			}
			else if(E.isValidPass(pass) == false) {
				System.out.println(TEXTOS.REGISTRARSE10);
			}
			else {
				ValidPass = true;
			}
		}
		
		String fecha = null;
		while (fechavalida == false) {
			System.out.println(TEXTOS.REGISTRARSE11);
			fecha = E.Scan();
			if(E.isValidDate(fecha)==true) {
				fechavalida = true;
			}
			else {
				System.out.println(TEXTOS.REGISTRARSE12);
			}
		}
		
		Usuario.RegistroDeUsuario(nombre, apellido1, apellido2, aliass, pass, fecha);
		
		try {
			Cliente.Clientes.size();
		}//Compruebo si el arraylist de clientes está inicializado para añadir el nuevo usuario, y si no lo está, lo inicializo y añado a continuación el usuario
		catch(NullPointerException e) {
			Cliente.Clientes = new ArrayList<Cliente>();
		}
		Cliente.Clientes.add(Usuario);
		
		int UsuarioID = 0;
		
		for(i=0 ; i<Cliente.Clientes.size() ; i++) {
			Cliente bucle_de_busqueda;
			bucle_de_busqueda = Cliente.Clientes.get(i);
			if(bucle_de_busqueda.getAlias().equals(Usuario.getAlias())) {
				UsuarioID = i;
			}
		}
		
		PersistenciaGson.persistenciaToJSON(Usuario);
		Main.MenuUsuario(Usuario, UsuarioID);
	}
	


	
	/**
	 * A esta función se llega una vez se ha realizado correctamente el inicio de sesión o el registro, obteniéndose tanto el Objeto inicializado Cliente Usuario, como la posición, 
	 * guardada en el usuarioID, que este ocupa en el static ArrayList de clientes.
	 * Comprueba si el usuario tiene alguna cuenta creada. Si tiene, le permite elegir entre las distintas operaciones que puede realizar, y si no, le pide que introduzca un nombre
	 * para crear una primera cuenta.
	 * 
	 * 
	 * @param Usuario: Usuario que contiene los datos que ha obtenido al registrarse un usuario o al imiciar sesión
	 * @param usuarioID: Posición del usuario activo en el arraylist de clientes
	 * @throws NumberFormatException : Cuando el valor introducido no corresponde con un número
	 * @throws IOException : Cuando se produce un error en la entrada de datos, ya sea pedidos por pantalla o al cargar el archivo json
	 */
	
	
	public static void MenuUsuario(Cliente Usuario, int usuarioID) throws NumberFormatException, IOException{
		
		boolean bucle = true;
		Cuenta cuentabanco = new Cuenta();
		int i = 0;//Posición por defecto que se usa para las cuentas. El valor cambia cuando se realiza un cambio en la cuenta activa
		
		while(bucle==true) {//Inicio un bucle que solo se rompe cuando se cierra sesión
			System.out.println("\nBienvenido " + Usuario.getAlias() + "---------------------------------------------------------\n");
			
			boolean haycuenta = true;
			try {
				Usuario.Cuentas.size();//Compruebo si existen cuentas para el usuario activo, y si no tiene ninguna le pido que introduzca un nombre para su primera cuenta
			}
			catch(NullPointerException e) {
				haycuenta = false;
			}
			
			if(haycuenta==false) {
				Usuario.Cuentas = new ArrayList<>();
				System.out.println(TEXTOS.MENUUSUARIO);
				System.out.println(TEXTOS.MENUUSUARIO1);
				
				String aliass = E.Scan();
				
				if(E.isValidAlias(aliass) == true) {//Compruebo si el alias introducido es válido. Si no lo es, se le asigna el literal
					cuentabanco.setNombreCuenta(aliass);
				}
				else {
					cuentabanco.setNombreCuenta("Cuenta " + String.format("%010d", cuentabanco.getCuenta()));
				}
				Usuario.Cuentas.add(cuentabanco);
				
			}	
			else {
				cuentabanco = Usuario.Cuentas.get(i);
				System.out.println("Cuenta actual: " + cuentabanco.getNombreCuenta());
				System.out.println("Datos Cuenta: " + Cuenta.Entidad + " " + Cuenta.Oficina + " " 
						+ cuentabanco.getDC() + " " + String.format("%010d", cuentabanco.getCuenta()) + " " 
						+ "ES" + String.format("%02d", cuentabanco.getIban()));
				System.out.println("Saldo: " + cuentabanco.getSaldo() + "€\n");
				int a;
				
				System.out.println(TEXTOS.MENUUSUARIO2);
				System.out.println(TEXTOS.MENUUSUARIO3);
				if(cuentabanco.getSaldo() == 0) {
					System.out.println(TEXTOS.MENUUSUARIO4 + TEXTOS.MENUUSUARIO12);
				}
				else {
					System.out.println(TEXTOS.MENUUSUARIO4);
				}
				/////////////////////////////////////////////////////
				if(cuentabanco.getSaldo() == 0) {
					System.out.println(TEXTOS.MENUUSUARIO5 + TEXTOS.MENUUSUARIO12);
				}
				else {
					System.out.println(TEXTOS.MENUUSUARIO5);
				}
				/////////////////////////////////////////////////////
				if(cuentabanco.getSaldo() == 0) {
					System.out.println(TEXTOS.MENUUSUARIO6 + TEXTOS.MENUUSUARIO12);
				}
				else {
					System.out.println(TEXTOS.MENUUSUARIO6);
				}
				/////////////////////////////////////////////////////
				System.out.println(TEXTOS.MENUUSUARIO7);
				System.out.println(TEXTOS.MENUUSUARIO8);
				System.out.println(TEXTOS.MENUUSUARIO9);
				System.out.println(TEXTOS.MENUUSUARIO10);
				System.out.println(TEXTOS.MENUUSUARIO11);
				
				try {
					a = Integer.parseInt(E.Scan());
					
					switch(a) {
					case 1:
						cuentabanco.IngresarDinero(Usuario, i);
						break;
					case 2:
						if(cuentabanco.getSaldo() == 0) {
							System.out.println(TEXTOS.MENUUSUARIO13);
						}
						else {
							cuentabanco.SacarDinero(Usuario, i);
							Cliente.Clientes.set(usuarioID, Usuario);
							PersistenciaGson.persistenciaToJSON(Usuario);
						}
						break;
					case 3:
						if(cuentabanco.getSaldo() == 0) {
							System.out.println(TEXTOS.MENUUSUARIO13);
						}
						else {
							cuentabanco.TransferirDinero(Usuario, i);
							Cliente.Clientes.set(usuarioID, Usuario);
							PersistenciaGson.persistenciaToJSON(Usuario);
						}
						break;
					case 4:
						if(cuentabanco.getSaldo() == 0) {
							System.out.println(TEXTOS.MENUUSUARIO13);
						}
						else {
							cuentabanco.RecargarTarjetaSIM(Usuario, i);
							Cliente.Clientes.set(usuarioID, Usuario);
							PersistenciaGson.persistenciaToJSON(Usuario);
						}
						break;
					case 5:
						cuentabanco.HistorialCuenta();
						break;
					case 6:
						i = Usuario.CrearNuevaCuenta();
						Cliente.Clientes.set(usuarioID, Usuario);
						PersistenciaGson.persistenciaToJSON(Usuario);
						break;
					case 7:
						i = Usuario.CambiarDeCuenta();
						Cliente.Clientes.set(usuarioID, Usuario);
						PersistenciaGson.persistenciaToJSON(Usuario);
						break;
					case 8:
						Usuario.InformacionDeUsuario();
						break;
					case 9:
						System.out.println(TEXTOS.MENUUSUARIO14);
						bucle = false;
						Cliente.Clientes.set(usuarioID, Usuario);
						PersistenciaGson.persistenciaToJSON(Usuario);//Al cerrar sesión, se guarda el arraylist en el json
						break;
					default:
						System.out.println(String.format(TEXTOS.BIENVENIDO7, a));
					}
				}
				catch(NumberFormatException e) {
					System.out.println(TEXTOS.BIENVENIDO8);
				}
			}
		}	
	}
	
	/**
	 * No hace nada
	 * @param args : Puesto (este comentario) para que no de ningún warning al generar el javadoc
	 * @throws NumberFormatException : De funciones anteriores
	 * @throws IOException : De funciones anteriores
	 */
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Menu();
	}
}