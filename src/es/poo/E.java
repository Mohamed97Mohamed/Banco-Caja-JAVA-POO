package es.poo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//Funciones extras

public class E {
		
		
		/**
		 * Toma el String introducido por pantalla, y valida si es una fecha váida, y actual o pasada
		 * @param inDate: String tomada por pantalla
		 * @return boolean: Devuelve si la fecha es valida o no.
		 */
	
		public static boolean isValidDate(String inDate) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dateFormat.setLenient(false);
			Date FechaIntroducida = new Date();
			try {
			  FechaIntroducida = dateFormat.parse(inDate.trim());
			} 
			catch (ParseException e) {
			  return false;
			}
			Date now = new Date();
			
			if(now.compareTo(FechaIntroducida) >= 0)
				return true;
			else
				return false;
		}
	  
		/**
		* Toma el String introducido por pantalla, y valida si es una contraseña váida
		* @param password: String tomada por pantalla
		* @return boolean: Devuelve si la contraseña es valida o no.
		*/
	  
		public static boolean isValidPass(String password) {
			int i;
			int numbers = 0;
			int capitals = 0;
		
			for(i = 0 ; i<password.length() ; i++) {
				Character letter = new Character(password.charAt(i)) ;
				String lettToString = Character.toString(letter);
				if(lettToString.matches("[A-Z]")){
					capitals++;
				}
				else if(lettToString.matches("[0-9]")) {
					numbers++;
				}
			}
			if(capitals >=1 && numbers >= 2) {
				return true;
			}
			else {
				return false;
			}
	  }
	  
	  /**
	   * Toma el String introducido por pantalla, y valida si es un alias váida
	   * @param alias: String tomada por pantalla
	   * @return boolean: Devuelve si el alias es valida o no.
	   */
	  
	  public static boolean isValidAlias(String alias) {
		  int i;
		  int characters = 0;
		  
		  for(i = 0 ; i<alias.length() ; i++) {
			  Character letter = new Character(alias.charAt(i)) ;
			  String lettToString = Character.toString(letter);
			  if(lettToString.matches("[a-zA-Z0-9]")){
				  characters++;
			  }
		  }
		  if(characters >=1) {
			  return true;
		  }
		  else {
			  return false;
		  }
	  }
	  
	  /**
	   * Toma los valores que se introducen por pantalla
	   * @return s: Devuelve la cadena que se ha introducido por pantalla
	   * @throws IOException : Cuando se produce un error en la entrada de datos.
	   */
	  
	  public static String Scan() throws IOException { 
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        String s = br.readLine();
	        return s;
	  }
	  
	  /**Cálculo del DC de la cuenta
	   * 
	   * @param Entidad: Entidad del banco
	   * @param Oficina: Oficina del banco
	   * @param cuenta: Número de cuenta
	   * @return DC
	   */
	  
	  public static int CalcularDC(int Entidad, int Oficina, int cuenta) {
		  int DC = 0;
		  
		  //CÁLCULO PRIMER DÍGITO
		  int DigitONE = 0;
		  int entidad = Entidad;
		  int oficina = Oficina;
		  
		  ///////////////////////////////////////////
		  String entidadString = Integer.toString(entidad);//guardo los valores en una cadena para poder tomar cada caracter numerico por separado y poder realizar así los cálculos
		  
		  int i = 0;
		  int entidad1 = 0;
		  for(i=0 ; i<4 ; i++) {
			  Character valor1E = new Character(entidadString.charAt(i));
			  String valor1ES = Character.toString(valor1E);
			  if(i==0) {
				  entidad1 = entidad1 + (Integer.parseInt(valor1ES) * 4);
			  }
			  else if(i==1) {
				  entidad1 = entidad1 + (Integer.parseInt(valor1ES) * 8);
			  }
			  else if(i==2) {
				  entidad1 = entidad1 + (Integer.parseInt(valor1ES) * 5);
			  }
			  else if(i==3) {
				  entidad1 = entidad1 + (Integer.parseInt(valor1ES) * 10);
			  }
		  }
		  ///////////////////////////////////////////
		  
		  ///////////////////////////////////////////
		  String oficinaString = Integer.toString(oficina);
		  
		  int oficina1 = 0;
		  for(i=0 ; i<4 ; i++) {
			  Character valor1E = new Character(oficinaString.charAt(0));
			  String valor1ES = Character.toString(valor1E);
			  if(i==0) {
				  oficina1 = oficina1 + (Integer.parseInt(valor1ES) * 9);
			  }
			  else if(i==1) {
				  oficina1 = oficina1 + (Integer.parseInt(valor1ES) * 7);
			  }
			  else if(i==2) {
				  oficina1 = oficina1 + (Integer.parseInt(valor1ES) * 3);
			  }
			  else if(i==3) {
				  oficina1 = oficina1 + (Integer.parseInt(valor1ES) * 6);
			  }
		  }
		  
		  ///////////////////////////////////////////
		  int ab1 = (entidad1 + oficina1) % 11;
		  
		  if((11 - ab1) == 10) {
			  DigitONE = 1;
		  }
		  else if((11 - ab1) == 11) {
			  DigitONE = 0;
		  }
		  else {
			  DigitONE = 11 - ab1;
		  }
		  
		  ///////////////////////////////////////////
		  
		  //CÁLCULO SEGUNDO DÍGITO
		  
		  int DigitTWO = 0;
		  int numCuenta = cuenta;
		  
		  String numCuentaString = String.format("%010d", numCuenta);//Le doy un formato al numero de cuenta para poder ponerle ceros a la izquierda y poder tomar individualmente cada caracter numerico
		  
		  int q1 = 0;
		  for(i=0 ; i<10 ; i++) {
			  Character valor1E = new Character(numCuentaString.charAt(i));
			  String valor1ES = Character.toString(valor1E);
			  if(i==0) {
				  q1 = q1 + (Integer.parseInt(valor1ES) * 1);
			  }
			  else if(i==1) {
				  q1 = q1 + (Integer.parseInt(valor1ES) * 2);
			  }
			  else if(i==2) {
				  q1 = q1 + (Integer.parseInt(valor1ES) * 4);
			  }
			  else if(i==3) {
				  q1 = q1 + (Integer.parseInt(valor1ES) * 8);
			  }
			  else if(i==4) {
				  q1 = q1 + (Integer.parseInt(valor1ES) * 5);
			  }
			  else if(i==5) {
				  q1 = q1 + (Integer.parseInt(valor1ES) * 10);
			  }
			  else if(i==6) {
				  q1 = q1 + (Integer.parseInt(valor1ES) * 9);
			  }
			  else if(i==7) {
				  q1 = q1 + (Integer.parseInt(valor1ES) * 7);
			  }
			  else if(i==8) {
				  q1 = q1 + (Integer.parseInt(valor1ES) * 3);
			  }
			  else if(i==9) {
				  q1 = q1 + (Integer.parseInt(valor1ES) * 6);
			  }
		  }
		  
		  int qw1 = q1 % 11;
		  
		  if((11 - qw1) == 10) {
			  DigitTWO = 1;
		  }
		  else if((11 - qw1) == 11) {
			  DigitTWO = 0;
		  }
		  else {
			  DigitTWO = 11 - qw1;
		  }
		  
		  ////////////////////////////////////////////////////////////
		  
		  DC = DigitONE*10 + DigitTWO;
		  return DC;
	  }
	  
	  /**
	   * Cálculo del iban de la cuenta correspondiente
	   * @param Entidad: Entidad del banco
	   * @param Oficina: Oficina del banco
	   * @param Cuenta: Número de cuenta
	   * @param DC: DC de la cuenta previamente calculado
	   * @return iban
	   */
	  
	  public static int CalcularIBAN(int Entidad, int Oficina, int Cuenta, int DC) {
		  int iban = 0;
		  
		  int entidad = Entidad;
		  int oficina = Oficina;
		  int dc = DC;
		  int cuenta = Cuenta;
		  
		  String entidadString = Integer.toString(entidad);
		  String oficinaString = Integer.toString(oficina);
		  String DCString = Integer.toString(dc);
		    
		  String Cuenta26Digitos = entidadString + oficinaString + DCString //Creo una cadena para poder obtener el valor de 26 digitos necesario para el algoritmo
				  + String.format("%010d", cuenta) + "1428" + "00";
		  
		  BigInteger a = new BigInteger("0");
		  a = a.add(new BigInteger(Cuenta26Digitos));//Añado la cadena de 26 digitos a un BigInteger
		  
		  BigInteger w = new BigInteger("0");
		  w = a.remainder(new BigInteger("97"));
		  String q = w.toString();
		  
		  int p = Integer.parseInt(q);
		  iban = 98 - p;
		  return iban;
	  }
}