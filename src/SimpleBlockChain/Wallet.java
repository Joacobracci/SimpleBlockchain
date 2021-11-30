package SimpleBlockChain;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import SimpleBlockChain.JoacoChain;


public class Wallet {
	
	// declaramos la key privada y la key publica
	
	public PrivateKey privateKey; // se usa para firmar las transacciones
	public PublicKey publicKey; // es nuestra address , nuestra direccion.
	
	public HashMap<String,TransactionOutput> UTXOs = new HashMap<String,TransactionOutput>(); //solo UTXOs de esta wallet
	
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
	
	// Devuelve el balance y guarda el UTXO owned por esta wallet en this.UTXO
	
	public float getBalance() {
			float total=0;
		for(Map.Entry<String, TransactionOutput> item: JoacoChain.UTXOs.entrySet()) {
			TransactionOutput UTXO = item.getValue();
			if(UTXO.isMine(publicKey)) { // si la moneda es mia 
				UTXOs.put(UTXO.id,UTXO); // agregala a la lista de las transacciones unspend
				total += UTXO.value;
			}
		}
		return total;
	}
	
	// Genera y devuelve una nueva transaccion para esta wallet
	public Transaction sendFunds(PublicKey _recipient,float value) {
		if(getBalance() < value) { // reunir el saldo y chequear fondos
			System.out.println("#No hay suficientes fondos para enviar esta transaccion, transaccion declinada.");
			return null;
			
		}
		
		//Crear un ArrayList de Entradas
		ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
		
		float total = 0;
		for(Map.Entry<String, TransactionOutput> item : UTXOs.entrySet()) {
			TransactionOutput UTXO = item.getValue();
			total += UTXO.value;
			inputs.add(new TransactionInput(UTXO.id));
			if(total > value) break;
			
		}
		
		Transaction newTransaction = new Transaction(publicKey, _recipient , value, inputs);
		newTransaction.generateSignature(privateKey);
		
		for(TransactionInput input: inputs) {
			UTXOs.remove(input.transactionOutputId);
		}
		
		return newTransaction;
	}

}
