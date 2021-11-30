package SimpleBlockChain;

public class TransactionInput {
	
	public String transactionOutputId; // referencia a transaction ID
	public TransactionOutput UTXO; // contiene la salida de la transaccion no gastada
	
	
	public TransactionInput(String transactionOutputId) {
		
		this.transactionOutputId = transactionOutputId;
		
	}

}

/* 	Esta clase se utilizar� para hacer referencia a TransactionOutputs
 * 	que a�n no se han gastado. 
 *	El transactionOutputId se utilizar� para encontrar el TransactionOutput relevante,
 *	lo que permitir� a los mineros verificar su propiedad. 
 */