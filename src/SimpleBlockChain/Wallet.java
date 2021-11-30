package SimpleBlockChain;
import java.security.*;
import java.security.spec.ECGenParameterSpec;



public class Wallet {
	
	// declaramos la key privada y la key publica
	
	public PrivateKey privateKey; // se usa para firmar las transacciones
	public PublicKey publicKey; // es nuestra address , nuestra direccion.
	
	public Wallet() {
		
		generateKeyPair();
	}

	public void generateKeyPair() {
		
		try {
			
			// KeyPairGenerator se usa para crear pares de Keys , getInstance devuelve un objeto KeyPairGenerator
			//que genera una key publica y privada para un determinado algoritmo
			
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA","BC");
			
			// SecureRandom provee un numero criptograficamente fuerte , instance devuelve
			// un objeto SecureRandom que implementa el especificado Random Number Generator Algorithm
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			
			// algo de una curva eliptica
			ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
			// Inicializar keyGenerator y generar un KeyPair
			
			keyGen.initialize(ecSpec, random);
			KeyPair keyPair = keyGen.generateKeyPair();
			
			privateKey = keyPair.getPrivate();
			publicKey = keyPair.getPublic();
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	

}
