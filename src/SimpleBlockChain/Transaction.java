package SimpleBlockChain;
import java.util.ArrayList;
import java.security.*;

public class Transaction {
	
	public String transactionId; // TXID
	public PublicKey sender; // remitente public adress
	public PublicKey reciepient; // receptor public address
	public float value; 
	public byte[] signature; // firma digital para prevenir que cualquiera pueda usar nuestros fondos
	
	
	public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
	public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();
	
	private static int sequence = 0; // un contador de cuantas transacciones se generaron

	
	// Metodo Constructor
	 
	public Transaction(PublicKey sender, PublicKey reciepient, float value, ArrayList<TransactionInput> inputs) {
		
		
		this.sender = sender;
		this.reciepient = reciepient;
		this.value = value;
		this.inputs = inputs;
	}
	
	// Metodo Constructor
	
	//Esto va a calcular el TXID ( el transaction hash )
	
	private String calculateHash() {
		sequence++;
		return StringUtil.applicarSha256(
				
				StringUtil.getStringFromKey(sender) +
				StringUtil.getStringFromKey(reciepient) +
				Float.toString(value) + sequence
			
				);
																				
	}
	
	// Firma toda la data que no queremos que sea manipulada
	
	public void generateSignature(PrivateKey privateKey) {
		String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient) + Float.toString(value);
		signature = StringUtil.applyECDSASig(privateKey, data);
		
	}
	
	// Verificamos que la data que signamos no fue manipulada .\
	
	public boolean verifySignature() {
		String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient) + Float.toString(value)	;
		return StringUtil.verifyECDSASig(sender, data, signature);
		
	}
	
}
