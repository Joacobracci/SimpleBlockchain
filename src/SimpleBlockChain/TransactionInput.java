package SimpleBlockChain;

public class TransactionInput {
	
	public String transactionOutputId; // referencia a transaction ID
	public TransactionOutput UTXO; // contiene la salida de la transaccion no gastada
	
	
	public TransactionInput(String transactionOutputId) {
		
		this.transactionOutputId = transactionOutputId;
		
	}

}

/* 	Esta clase se utilizará para hacer referencia a TransactionOutputs
 * 	que aún no se han gastado. 
 *	El transactionOutputId se utilizará para encontrar el TransactionOutput relevante,
 *	lo que permitirá a los mineros verificar su propiedad. 
 */