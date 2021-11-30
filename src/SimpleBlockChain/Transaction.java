package SimpleBlockChain;
import java.util.ArrayList;

import SimpleBlockChainTest.TestInputsOutputs;

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
	
	// Devuelve true si una nueva transaccion puede ser creada
	public boolean processTransaction() {
		
		if(verifySignature() == false) {
			System.out.println("#La Firma de la Transaccion no se pudo verificar");
			return false;
		}
		
		//Recolectar input de transacciones (asegurarnos de que estan sin gastar)
		for(TransactionInput i : inputs) {
			i.UTXO = JoacoChain.UTXOs.get(i.transactionOutputId);
		}
		
		//Generar el transaction Output : 
		float leftOver = getInputsValue() - value; // obtiene el valor de la entrada y lo que sobra
		transactionId = calculateHash();
		outputs.add(new TransactionOutput(this.reciepient, value,transactionId)); //Envia el valor al receptor
		outputs.add(new TransactionOutput(this.sender, leftOver,transactionId)); //Envia lo que sobra ( el cambio) de nuevo al remitente
		
		//Agregar outputs a la lista de sin gastar (Unspend)
		for(TransactionOutput o : outputs) {
			JoacoChain.UTXOs.put(o.id, o);
		}
		
		//Remover los inputs de la transaccion de la lista UTXO como gastaods
		for(TransactionInput i : inputs) {
			if(i.UTXO == null) continue; // si no puede encontrar la transaccion skipeala
			JoacoChain.UTXOs.remove(i.UTXO.id);
		}
		
		return true;
		
	}
	
	//Devuelve la suma de inputs (UTXOs) values
	
	public float getInputsValue() {
		float total = 0;
		for(TransactionInput i : inputs) {
			if(i.UTXO == null) continue; //si la transaccion no la encuentra que la skipee
			total += i.UTXO.value;
		}
		return total;
	}
	
	//Devuelve la suma de outputs
	
	public float getOutputsValue() {
		float total = 0;
		for(TransactionOutput o : outputs) {
			total += o.value;
		}
		return total;
	}
}
