package SimpleBlockChain;

import java.security.PublicKey;

public class TransactionOutput {
	
	public String id;
	public PublicKey reciepient;
	public float value;
	public String parentTransactionId;
	
	
	// Metodo constructor
	
	public TransactionOutput(PublicKey reciepient, float value, String parentTransactionId) {
		this.reciepient = reciepient;
		this.value = value;
		this.parentTransactionId = parentTransactionId;
		this.id = StringUtil.applicarSha256(StringUtil.getStringFromKey(reciepient)+Float.toString(value)+parentTransactionId);
	}
	
	//Chequear si el coin te pertenece
	
	public boolean isMine(PublicKey publicKey) {
		return (publicKey == reciepient);
	}

}

/*	Los resultados de la transacci�n mostrar�n el monto final enviado a cada parte de la transacci�n.
 *  Estos, cuando se mencionan como entradas en nuevas transacciones,
 * 	act�an como prueba de que tiene monedas para enviar.
 */