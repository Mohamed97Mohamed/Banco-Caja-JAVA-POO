package es.poo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class PersistenciaGson {
	
	/**En esta funcion realizo la persistencia de datos
	 * 
	 * @param objetos: Le paso un cliente para poder tener acceso al arraylist de clientes
	 * @author Supergrefu
	 */
	
	public static void persistenciaToJSON(Cliente objetos) {
		try{
	         FileOutputStream fos= new FileOutputStream("clientes.json");//guardo en una variable el nombre donde se hará la persistencia. 
	         ObjectOutputStream oos= new ObjectOutputStream(fos);
	         Gson gson = new Gson();
	         java.lang.reflect.Type listType = new TypeToken<ArrayList<Cliente>>() {}.getType();//reflejo en una variable Type la estructura de un ArrayList de usuarios
	         String json = gson.toJson(Cliente.Clientes, listType);//Transformo el arraylist de usuario en un string
	         oos.writeObject(json);//almaceno el string con el arraylist 
	         oos.close();
	         fos.close();
	       }catch(IOException e){}
	}
	
	/**A partir del string almacenado en el archivo json, si existe este, obtengo los datos guardados de ejecuciones anteriores.
	 * 
	 * @return objects: Devuelvo el arraylist que he obtenido del archivo
	 * @throws IOException : Cuando se produce un error en la entrada de datos del archivo
	 */
	
	public static ArrayList<Cliente> persistenciaFromJSON() throws IOException {
		
		String json = null;
		try//Compruebo si existe el archivo
        {
			FileInputStream fis = new FileInputStream("clientes.json");
			ObjectInputStream ois = new ObjectInputStream(fis);
			json = (String) ois.readObject(); //tomo el string del archivo y lo introduzco en una variable string
			ois.close();
		}
		catch(ClassNotFoundException e){}
		catch(FileNotFoundException e){}
		
		Gson gson = new Gson();
		ArrayList<Cliente> clients = new ArrayList<>();
		
		java.lang.reflect.Type listType = new TypeToken<ArrayList<Cliente>>() {}.getType();//reflejo en una variable Type la estructura de un ArrayList de clientes
		clients = gson.fromJson(json,  listType);//Transformo el string en un arraylist de usuarios
		
		return clients;
	}
}
