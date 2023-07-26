package es.poo;

import java.io.IOException;
import java.util.ArrayList;

public class Cuenta {
	public static final int Entidad = 9999;
	public static final int Oficina = 8888;
	private int DC;
	private int cuenta;
	public static int CCCC;//Variable estática que se va incrementando para que el numero de las cuentas sean siempre diferentes entre sí
	private int iban;
	private int Saldo;
	private String NombreCuenta;
	public ArrayList<String> historial;
	
	//GETTERS
	public int getDC() {
		return DC;
	}
	public int getCuenta() {
		return cuenta;
	}
	public int getIban() {
		return iban;
	}
	public int getSaldo() {
		return Saldo;
	}
	public String getNombreCuenta() {
		return NombreCuenta;
	}

	//SETTERS
	public void setSaldo(int saldo) {
		Saldo = saldo;
	}
	public void setNombreCuenta(String nombreCuenta) {
		NombreCuenta = nombreCuenta;
	}

	
	Cuenta() {
		this.Saldo = 0;
		CCCC = CCCC + 1;
		this.cuenta = CCCC;
		this.DC = E.CalcularDC(Entidad, Oficina, this.cuenta);
		this.iban = E.CalcularIBAN(Entidad, Oficina, this.cuenta, this.DC);
	}
	
	//MÉTODOS
	
	/**
	 * Esta funcion ejecuta la opción de ingresar dinero a la cuenta en la que se está iniciada la sesión.
	 * 
	 * @param Usuario: Objeto Cliente para guardar los cambios de la cuenta en el arraylist de cuentas
	 * @param i: posición de la cuenta seleccionada en el arraylist de cuentas del usuario
	 * @throws NumberFormatException : Cuando el valor introducido no corresponde con un número
	 * @throws IOException : Cuando se produce un error en la entrada de datos.
	 */
	
	public void IngresarDinero(Cliente Usuario, int i) throws NumberFormatException, IOException {
		
		boolean bucle = true;
		
		while(bucle == true) {
			boolean intValido = true;
			int a = 0;
			System.out.println(TEXTOS.CUENTAS);
			try {
				a = Integer.parseInt(E.Scan());
			}
			catch(NumberFormatException e) {
				System.out.println(TEXTOS.CUENTAS1);
				intValido = false;
			}
			if(intValido == true) {
				if(a>0) {
					boolean hayhistorial = true;
					try {
						this.historial.size();//Compruebo si el arraylist de movimientos esta inicializado. En caso contrario, lo inicializo
					}
					catch(NullPointerException e) {
						hayhistorial = false;
					}
					if(hayhistorial == false) {
						this.historial = new ArrayList<String>();
						this.historial.add("Ingreso de " + a + "€");
					}
					else {
						this.historial.add("Ingreso de " + a + "€");
					}
					
					this.setSaldo( this.getSaldo() + a );
					Usuario.Cuentas.set(i, this);
					bucle = false;
					System.out.println(TEXTOS.CUENTAS2);
				}
				else {
					System.out.println(TEXTOS.CUENTAS3);
				}
			}
		}
	}
	
	/**
	 * Esta función ejecuta la opción de sacar dinero de la cuenta en la que se está iniciada la sesión
	 * 
	 * @param Usuario: Usuario al que le pertenece la cuenta. Se usa para guardar en su arraylist los cambios que se realizan en la cuenta
	 * @param i: posición de la cuenta (en la que se estan realizando los cambios) en el arraylist de cuentas del cliente
	 * @throws IOException : Cuando se produce un error en la entrada de datos.
	 */
	
	public void SacarDinero(Cliente Usuario, int i) throws IOException {
		
		boolean bucle = true;
		
		while(bucle == true) {
			boolean intValido = true;
			int a = 0;
			System.out.println(TEXTOS.CUENTAS4);
			try {
				a = Integer.parseInt(E.Scan());
			}
			catch(NumberFormatException e) {
				System.out.println(TEXTOS.CUENTAS1);
				intValido = false;
			}
			if(intValido == true) {
				if(a <= this.getSaldo() && a>=0) {
					if(a==0) {
						bucle = false;
						}
					else {
						boolean hayhistorial = true;
						try {
							this.historial.size();//Compruebo si el arraylist de movimientos esta inicializado. En caso contrario, lo inicializo
						}
						catch(NullPointerException e) {
							hayhistorial = false;
						}
						if(hayhistorial == false) {
							this.historial = new ArrayList<String>();
							this.historial.add("Extracción de " + a + "€");
						}
						else {
							this.historial.add("Extracción de " + a + "€");
						}
						
						this.setSaldo( this.getSaldo() - a);
						Usuario.Cuentas.set(i, this);
						bucle = false;
						System.out.println(TEXTOS.CUENTAS2);
					}
				}
				else if(a<0) {
					System.out.println(TEXTOS.CUENTAS5);
				}
				else {
					System.out.println(TEXTOS.CUENTAS6);
				}
			}
		}
	}
	
	/**
	 * Esta funcion ejecuta la opción de transferir dinero entre las cuentas que el objeto cliente inicializado posee.
	 * 
	 * @param Usuario: usado para comprobar su arraylist de cuentas y ver que cuentas posee el cliente, mostrándose estas por pantalla.
	 * @param i: posición de la cuentabanco en el arraylist de cuentas del usuario. Se pasa para no tener que comprobarse dentro de la función.
	 * @throws IOException : Cuando se produce un error en la entrada de datos.
	 */
	
	public void TransferirDinero(Cliente Usuario, int i) throws IOException {
		
		System.out.println("Cuentas Disponibles:");
		
		int z = 0;
		int a;
		Cuenta comprobacion = new Cuenta();
		
		for(a = 0; a < Usuario.Cuentas.size() ; a++) {
			comprobacion = Usuario.Cuentas.get(a);
			System.out.println("-'" + comprobacion.getNombreCuenta() + "':\tSaldo: " + comprobacion.getSaldo() + "€\tDatos Cuenta: " + Cuenta.Entidad + " " + Cuenta.Oficina + " " 
					+ comprobacion.getDC() + " " + String.format("%010d", comprobacion.getCuenta()) + " " 
					+ "ES" + String.format("%02d", comprobacion.getIban()));
		}
		
		boolean bucle = true;
		boolean bucleabsoluto = true;
		String cadenaNombre;
		Cuenta transferencias = new Cuenta();
		
		while(bucle==true) {
			System.out.println(TEXTOS.CUENTAS7);
			cadenaNombre = E.Scan();
			if(cadenaNombre.equals("cancelar")) {
				bucle = false;
				bucleabsoluto = false;
			}
			else if (cadenaNombre.equals(this.getNombreCuenta())){
				System.out.println(TEXTOS.CUENTAS8);
			}
			else {
				boolean nombreEncontrado = false;
				boolean foractivo = true;
				for(a=0 ; a < Usuario.Cuentas.size() && foractivo==true; a++) {
					comprobacion = Usuario.Cuentas.get(a);
					if(cadenaNombre.equals(comprobacion.getNombreCuenta())) {
						foractivo = false;
						nombreEncontrado = true;
					}
				}
				
				if(nombreEncontrado == true) {
					System.out.println(TEXTOS.CREARCUENTAS4);
					transferencias = Usuario.Cuentas.get(a-1);
					z = a;
					bucle = false;
				}
				else {
					System.out.println(String.format(TEXTOS.CREARCUENTAS5, cadenaNombre));
				}
			}	
		}
		
		bucle = true;
		int t = 0;
		
		while(bucle == true && bucleabsoluto == true) {
			boolean intValido = true;
			a = 0;
			System.out.println(TEXTOS.CUENTAS9);
			try {
				t = Integer.parseInt(E.Scan());
			}
			catch(NumberFormatException e) {
				System.out.println(TEXTOS.CUENTAS1);
				intValido = false;
			}
			if(intValido == true) {
				if(t <= this.getSaldo() && t>=0) {
					
					if(t==0) {
						bucle = false;
						bucleabsoluto = false;
					}
					else {
						
						boolean hayhistorial = true;
						try {
							this.historial.size();//Compruebo si el arraylist de movimientos esta inicializado. En caso contrario, lo inicializo
						}
						catch(NullPointerException e) {
							hayhistorial = false;
						}
						if(hayhistorial == false) {
							this.historial = new ArrayList<String>();
							this.historial.add("Transferencia de " + t + "€ a cuenta '" + transferencias.getNombreCuenta() + "'");
						}
						else {
							this.historial.add("Transferencia de " + t + "€ a cuenta '" + transferencias.getNombreCuenta() + "'");
						}
						
						hayhistorial = true;
						try {
							transferencias.historial.size();
						}
						catch(NullPointerException e) {
							hayhistorial = false;
						}
						if(hayhistorial == false) {
							transferencias.historial = new ArrayList<String>();
							transferencias.historial.add("Transferencia de " + t + "€ recibida de cuenta '" + this.getNombreCuenta() + "'");
						}
						else {
							transferencias.historial.add("Transferencia de " + t + "€ recibida de cuenta '" + this.getNombreCuenta() + "'");
						}
						
						this.setSaldo( this.getSaldo() - t);
						Usuario.Cuentas.set(i, this);
						transferencias.setSaldo( transferencias.getSaldo() + t );
						Usuario.Cuentas.set(z-1, transferencias);
						bucle = false;
						System.out.println(TEXTOS.CUENTAS2);
					}
				}
				else if(t<0) {
					System.out.println(TEXTOS.CUENTAS3);
				}
				else {
					System.out.println(TEXTOS.CUENTAS6);
				}
			}
		}
		
	}
	
	/**
	 * Esta función ejecuta la opción de recargar tarjeta SIM de la cuenta en la que se está iniciada la sesión
	 * 
	 * @param Usuario: Usuario al que le pertenece la cuenta. Se usa para guardar en su arraylist los cambios que se realizan en la cuenta
	 * @param i: posición de la cuenta (en la que se estan realizando los cambios) en el arraylist de cuentas del cliente
	 * @throws IOException : Cuando se produce un error en la entrada de datos.
	 */
	
	public void RecargarTarjetaSIM(Cliente Usuario, int i) throws IOException {
		
		boolean bucle = true;
		
		while(bucle == true) {
			boolean intValido = true;
			int a = 0;
			System.out.println(TEXTOS.CUENTAS10);
			try {
				a = Integer.parseInt(E.Scan());
			}
			catch(NumberFormatException e) {
				System.out.println(TEXTOS.CUENTAS1);
				intValido = false;
			}
			if(intValido == true) {
				if(a <= this.getSaldo() && a>=0) {
					
					if(a==0) {
						bucle = false;
						}
					else {
						boolean hayhistorial = true;
						try {
							this.historial.size();//Compruebo si el arraylist de movimientos esta inicializado. En caso contrario, lo inicializo
						}
						catch(NullPointerException e) {
							hayhistorial = false;
						}
						if(hayhistorial == false) {
							this.historial = new ArrayList<String>();
							this.historial.add("Recarga de SIM de " + a + "€");
						}
						else {
							this.historial.add("Recarga de SIM de " + a + "€");
						}
						
						this.setSaldo( this.getSaldo() - a);
						Usuario.Cuentas.set(i, this);
						bucle = false;
						System.out.println(TEXTOS.CUENTAS2);
					}
				}
				else if(a<0) {
					System.out.println(TEXTOS.CUENTAS3);
				}
				else {
					System.out.println(TEXTOS.CUENTAS11);
				}
			}
		}
	}
	
	
	/**
	 * En esta función se comprueban todos los movimientos que se han realizado en la cuenta, y si no se han realizado ninguno, se muestra un mensaje para informar
	 * al usuario.
	 */
	
	
	public void HistorialCuenta() {
		
		System.out.println("\n");
		System.out.println(String.format(TEXTOS.CUENTAS12, this.getNombreCuenta()));
		boolean hayHistorial = true;
		try {
			this.historial.size();//Compruebo si existe algun movimiento en la cuenta y los muestro por pantalla, si existen.
		}
		catch(NullPointerException e){
			hayHistorial = false;
		}
		if(hayHistorial == true) {
			int i = 0;
			for(i = 0 ; i < this.historial.size() ; i++) {
				System.out.println("-" + this.historial.get(i));
			}
		}
		else {
			System.out.println(TEXTOS.CUENTAS13);
		}
	}
}