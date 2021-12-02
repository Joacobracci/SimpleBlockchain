package SimpleBlockChain;
// Esta clase me tiene que ayudar a convertir facilmente un String a SHA256

import java.security.Key;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Base64;
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
	
	
	// La Firma digital, hace 2 tareas importantes en nuestra blockchain.
	// primero autorizan solo al priopietario a gastar sus coins y segundo
	// previenen a otros de manipular con sus transacciones modificadas antes
	// que un nuevo blocke es minado.
	
	
	/* Por ejemplo elpepe quiere enviar 2 Joacoins a messi, entonces su billetera 
	 * genera esta transaccion y la envia al minero para incluirla en el proximo bloque, 
	 * supongamos que un minero intenta cambiar el receptor de 2 coins a el, por suerte elpepe firmo la transaccion
	 * con su key privada, permitiendo que cualquiera pueda verificar si la transaccion fue alterada usando su 
	 * public Key
	 */
	
	// La firma va a ser un monton de bytes 
	
	// Aplicamos ECDSA y retorna el resultado como bytes  ECDSA. Elliptic Curve Digital Signature Algorithm derivado del DSA
	// un algoritmo para crear la firma digital. se supone que es seguro.
	
	// no comprendo como funciona el algoritmo pero bueno 
	
	// ESTA PARTE DEL CODIGO NO LA ENTIENDO Y ES COPIADA 
	// ESTA PARTE DEL CODIGO NO LA ENTIENDO Y ES COPIADA 
	// ESTA PARTE DEL CODIGO NO LA ENTIENDO Y ES COPIADA 
	
		public static byte[] applyECDSASig(PrivateKey privateKey, String input) {
		Signature dsa;
		byte[] output = new byte[0];
			try {
				dsa = Signature.getInstance("ECDSA", "BC"); // provee las funcionalidade de la firma digital. autenticacion y seguridad.
				dsa.initSign(privateKey);
				byte[] strByte = input.getBytes();
				dsa.update(strByte);
				byte[] realSig = dsa.sign();
				output = realSig;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			return output;
		}
	
		/* applyECDSASig toma la clave privada del remitente y la entrada de cadena, 
		 * la firma y devuelve una matriz de bytes. */
		
		//Verifies a String signature 
		public static boolean verifyECDSASig(PublicKey publicKey, String data, byte[] signature) {
				try {
					Signature ecdsaVerify = Signature.getInstance("ECDSA", "BC");
					ecdsaVerify.initVerify(publicKey);
					ecdsaVerify.update(data.getBytes());
					return ecdsaVerify.verify(signature);
				}catch(Exception e) {
					throw new RuntimeException(e);
				}
		}
	
		public static String getStringFromKey(Key key) {
			return Base64.getEncoder().encodeToString(key.getEncoded());
		}
		
		/*  toma la firma, la clave pública y los datos de la cadena
		 *  y devuelve verdadero o falso si la firma es válida.
		 *  getStringFromKey devuelve una cadena codificada de cualquier clave.
		 */
		
	
	// ESTA PARTE DEL CODIGO NO LA ENTIENDO Y ES COPIADA 
	// ESTA PARTE DEL CODIGO NO LA ENTIENDO Y ES COPIADA 
	// ESTA PARTE DEL CODIGO NO LA ENTIENDO Y ES COPIADA 
	
		
		// merkle root
		
		public static String getMerkleRoot(ArrayList<Transaction> transactions) {
			int count = transactions.size();
			ArrayList<String> previousTreeLayer = new ArrayList<String>();
			for(Transaction transaction : transactions) {
				previousTreeLayer.add(transaction.transactionId);
			}
			ArrayList<String> treeLayer = previousTreeLayer;
			while(count > 1) {
				treeLayer = new ArrayList<String>();
				for(int i=1; i < previousTreeLayer.size(); i++) {
					treeLayer.add(applicarSha256(previousTreeLayer.get(i-1) + previousTreeLayer.get(i)));
				}
				count = treeLayer.size();
				previousTreeLayer = treeLayer;
			}
			String merkleRoot = (treeLayer.size() == 1) ? treeLayer.get(0) : "";
			return merkleRoot;
			}


		public static String getDificultyString(int dificultad) {
			
			return null;
		}
}


