package SimpleBlockChain;
// Esta clase me tiene que ayudar a convertir facilmente un String a SHA256

import java.security.MessageDigest;
// importamos MessageDigest para tener acceso al algoritmo SHA256


public class StringUtil {
	
	// Aplica SHA 256 a un string y devuelve el resultado.
	
	public static String applicarSha256(String input){
		
		try {
			
				/* 
				 * Esta clase MessageDigest proporciona la funcionalidad de un algoritmo de resumen de mensajes,
				 * como SHA-256.Los resúmenes de mensajes son funciones hash unidireccionales seguras que toman
				 * datos de tamaño arbitrario y generan un valor hash de longitud fija.
				 * 
				 */
				
				MessageDigest digest = MessageDigest.getInstance("SHA-256"); // devuelve un  objeto MessageDigest que implementa el algoritmo especificado (SHA 256)
				
				// Aplica MessageDigest al Input
							
				byte[] hash = digest.digest(input.getBytes("UTF-8")); // devuelve el array de bytes para el resultado del valor hash
				
				StringBuffer hexString = new StringBuffer(); // esto va a contener el hash como un hexadecimal 
				
				
				// ****************** ESTA PARTE LA COPIE Y NO LA ENTIENDO BIEN ******************* //
				
				for ( int i = 0; i < hash.length; i++) { 
					
					String hex = Integer.toHexString(0xff & hash[i]);
					if(hex.length() == 1 ) hexString.append('0');
					hexString.append(hex);
				}
				
				// ****************** ESTA PARTE LA COPIE Y NO LA ENTIENDO BIEN ******************* //
			
			
			return hexString.toString();	 // que devuelva el string con el hash aplicado
			
			
		}	
		
		catch (Exception e) {
			
			throw new RuntimeException(e); // que tire un error 
		}
		
		
	}
}