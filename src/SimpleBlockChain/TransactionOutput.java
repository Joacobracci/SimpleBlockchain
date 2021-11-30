package SimpleBlockChain;

import java.security.PublicKey;

public class TransactionOutput {
	
	public String id;
	public PublicKey reciepient;
	public float value;
	public String parentTransactionId;
	
	
	// Metodo constructor
	
	public TransactionOutput(String id, PublicKey reciepient, float value, String parentTransactionId) {
		this.id = id;
		this.reciepient = reciepient;
		this.value = value;
		this.parentTransactionId = parentTransactionId;
	}
	
	//Chequear si el coin te pertenece
	
	public boolean isMine(PublicKey publicKey) {
		return (publicKey == reciepient);
	}

}

/*	Los resultados de la transacción mostrarán el monto final enviado a cada parte de la transacción.
 *  Estos, cuando se mencionan como entradas en nuevas transacciones,
 * 	actúan como prueba de que tiene monedas para enviar.
 */