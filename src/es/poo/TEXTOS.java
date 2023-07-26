package es.poo;

/**
 * Aqu� guardo todos los strings de los System.out.println
 * @author Supergrefu
 *
 */

public class TEXTOS {
/////////////////////////////////////////////////////STRINGS DE CLASE MAIN
	
	//STRINGS DE MENU INICIO
	public static final String BIENVENIDO  = "Bienvenido al banco POO----------------------------------------------------------\n";
	public static final String BIENVENIDO1 = "�Que desea hacer?";
	public static final String BIENVENIDO2 = "1: Iniciar Sesion";
	public static final String BIENVENIDO3 = "2: Crear Cuenta";
	public static final String BIENVENIDO4 = "3: Salir";
	public static final String BIENVENIDO5 = "\nIntroduzca la opcion que desea:";
	public static final String BIENVENIDO6 = "Saliendo...";
	public static final String BIENVENIDO7 = "No existe la opci�n '%s'";
	public static final String BIENVENIDO8 = "Warning: Introduzca un valor v�lido\n\n";
	//STRINGS DE INICIAR SESION
	public static final String INICIARSESION  = "Debe crear una cuenta para poder iniciar sesi�n";
	public static final String INICIARSESION1 = "Usuarios disponibles:";
	public static final String INICIARSESION2 = "Introduzca su alias o introduzca 'cancelar' para volver al men�";
	public static final String INICIARSESION3 = "No ha introducido correctamente el alias";
	public static final String INICIARSESION4 = "Este cliente ha introduzido 3 veces mal su contrase�a, por lo que ha sido bloqueado";
	public static final String INICIARSESION5 = "Introduzca su contrase�a o introduzca 'cancelar' para volver al men�";
	public static final String INICIARSESION6 = "Contrase�a correcta";
	public static final String INICIARSESION7 = "Esa contrase�a no corresponde con este alias";
	public static final String INICIARSESION8 = "Tu usuario ha sido bloqueado";
	//STRINGS DE REGISTRARSE
	public static final String REGISTRARSE   = "Introduzca su nombre (opcional)";
	public static final String REGISTRARSE1  = "Introduzca su primer apellido (opcional)";
	public static final String REGISTRARSE2  = "Introduzca su segundo apellido (opcional)";
	public static final String REGISTRARSE3  = "Introduzca su segundo apellido (opcional)";
	public static final String REGISTRARSE4  = "Introduzca un alias*";
	public static final String REGISTRARSE5  = "El alias '%s' ya existe";
	public static final String REGISTRARSE6  = "El alias es obligatorio";
	public static final String REGISTRARSE7  = "El alias debe contener al menos una letra o numero";
	public static final String REGISTRARSE8  = "Introduzca una contrase�a (Debe contener al menor 6 caracteres, de ellos 1 may�scula y 2 n�meros)*";
	public static final String REGISTRARSE9  = "La contrase�a debe contener al menos 6 caracteres";
	public static final String REGISTRARSE10 = "La contrase�a debe contener al menos 2 n�meros y 1 may�scula";
	public static final String REGISTRARSE11 = "Introduzca su fecha de nacimiento con formato 'DD/MM/AAAA'*";
	public static final String REGISTRARSE12 = "Introduzca una fecha v�lida, actual o pasada";
	//STRINGS DE MENU-USUARIO
	public static final String MENUUSUARIO   = "Para continuar debe crear una cuenta";
	public static final String MENUUSUARIO1  = "Introduzca un nombre para la cuenta:";
	public static final String MENUUSUARIO2  = "Introduzca la opci�n que desea:";
	public static final String MENUUSUARIO3  = "1: Ingresar Dinero";
	public static final String MENUUSUARIO4  = "2: Sacar Dinero";
	public static final String MENUUSUARIO5  = "3: Transferir Dinero a otra cuenta";
	public static final String MENUUSUARIO6  = "4: Recargar tarjeta SIM";
	public static final String MENUUSUARIO7  = "5: Historial de Movimientos de esta cuenta";
	public static final String MENUUSUARIO8  = "6: Crear nueva cuenta";
	public static final String MENUUSUARIO9  = "7: Cambiar de cuenta (Ver cuentas creadas)";
	public static final String MENUUSUARIO10 = "8: Informaci�n del usuario";
	public static final String MENUUSUARIO11 = "9: Cerrar Sesi�n (Guardar Datos)";
	public static final String MENUUSUARIO12 = " (Inactivo al no haber saldo disponible)";
	public static final String MENUUSUARIO13 = "No tienes saldo suficientes";
	public static final String MENUUSUARIO14 = "Cerrando Sesi�n...\n\n";
	
	/////////////////////////////////////////////////////STRINGS DE CLASE CLIENTE
	
	public static final String CREARCUENTAS  = "Introduzca un nombre para la cuenta:";
	public static final String CREARCUENTAS1 = "Ya tienes una cuenta con dicho nombre";
	public static final String CREARCUENTAS2 = "Cuentas Disponibles:";
	public static final String CREARCUENTAS3 = "Escriba el nombre de la cuenta a la que quiere acceder o escribe 'cancelar' para volver al men�:";
	public static final String CREARCUENTAS4 = "Cuenta localizada correctamente\n";
	public static final String CREARCUENTAS5 = "No se ha localizado la cuenta '%s'\n";
	
	/////////////////////////////////////////////////////STRINGS DE CLASE CUENTA
	
	public static final String CUENTAS   = "Introduzca cuanto dinero quiere Ingresar:";
	public static final String CUENTAS1  = "Introduzca un valor num�rico";
	public static final String CUENTAS2  = "Operaci�n realizada correctamente\n";
	public static final String CUENTAS3  = "Introduzca un valor mayor que 0";
	public static final String CUENTAS4  = "Introduzca cuanto dinero quiere Retirar (Introduzca 0 si quiere cancelar la operaci�n):";
	public static final String CUENTAS5  = "Debe introducir un valor positivo";
	public static final String CUENTAS6  = "Est� intentando extraer m�s dinero del que tiene";
	public static final String CUENTAS7  = "Escriba a que cuenta quiere transferir dinero o 'cancelar' para volver al men�:";
	public static final String CUENTAS8  = "No puede transferir dinero hacia la misma cuenta";
	public static final String CUENTAS9  = "Introduzca cuanto dinero quiere Transferir (Introduzca 0 si quiere cancelar la operaci�n):";
	public static final String CUENTAS10 = "Introduzca con cuanto dinero quiere recargar su SIM (Introduzca 0 si quiere cancelar la operaci�n):";
	public static final String CUENTAS11 = "No tiene dinero suficiente";
	public static final String CUENTAS12 = "Historial de movimientos de la cuenta '%s'";
	public static final String CUENTAS13 = "Aun no se han producido movimientos en esta cuenta";
}
