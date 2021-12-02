package SimpleBlockChain;

import java.util.ArrayList;
import java.util.Date;

public class Block {

		public String hash;  // Hash del bloque
		public String hashAnterior; // Hash del bloque anterior
		private String merkleRoot; // datos del mensaje
		public ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		private long timeStamp; // marca de tiempo en segundos desde 1 1 1970
		private int nonce;
		
		
		// Metodo Constructor
		
		public Block(String hashAnterior) {
						
				
			this.hashAnterior = hashAnterior;
		
			this.timeStamp = new Date().getTime();
			this.hash = calcularHash(); 
						
						
			// metodo constructo del bloque, con el hash anterior, los datos y el tiempo.
			// asumo que el hash se va a generar a partir del hashanterior para que si este cambia 
			// o alguien altere el hash, se arruine la cadena y se pueda detectar.
						
			}
								
		public String calcularHash() {
			
			String hashCalculado = StringUtil.applicarSha256(
						hashAnterior + 
						Long.toString(timeStamp) + 
						Integer.toString(nonce) +
						merkleRoot
					
					);
			
			return hashCalculado;
		}
		
		// Le aplicamos el fucking SHA256
		
		// el metodo toma un int llamado dificultad, este es el numero de 0 que tiene que resolver.
		
		public void minarBloque(int dificultad) {
			merkleRoot = StringUtil.getMerkleRoot(transactions);
			String target = StringUtil.getDificultyString(dificultad);
			// Creamos un string con dificultad * "0" 
			
			while(!hash.substring(0, dificultad).equals(target)) {
				nonce ++;
				hash = calcularHash();
			}
			// Mientras que el bloque no este minado que siga minando xD algo asi no se explicarlo.
			
			System.out.println("Bloque Minado!!!!!!  :  " + hash);
		}
			
		public boolean addTransaction(Transaction transaction) {
			//process transaction and check if valid, unless block is genesis block then ignore.
			if(transaction == null) return false;		
			if((hashAnterior != "0")) {
				if((transaction.processTransaction() != true)) {
					System.out.println("Transaction fallida xD");
					return false;
				}
			}
			transactions.add(transaction);
			System.out.println("Transaction Agregada Exitosamente al Bloque");
			return true;
		}
}
