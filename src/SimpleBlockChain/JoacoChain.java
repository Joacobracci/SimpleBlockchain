package SimpleBlockChain;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.GsonBuilder;



public class JoacoChain {

		public static ArrayList<Block> blockchain = new ArrayList<Block>();
		public static int dificultad = 5;
		public static HashMap<String,TransactionOutput> UTXOs = new HashMap<String,TransactionOutput>();
		
		// agrego una dificultad de 5
		
		public static void main(String[] args) {
		
		// Agregamos nuestros bloques al array de la blockchain
			
			
			
			
			System.out.println("\nBlockchain is Valid:" + isChainValid());
			
			
			String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
			System.out.println("\nThe Block Chain: ");
			System.out.println(blockchainJson);
		
		
	}
		
		public static Boolean isChainValid() { 
			
			Block bloqueActual;
			Block bloqueAnterior;
			String hashTarget = new String(new char[dificultad]).replace('\0','0');
			
			// iterar a traves de la blockchain para revisar los hashes
			
			for(int i=1; i< blockchain.size(); i++) {
				
				// obtenemos el bloque actual y el anterior, bastante sobreexplicativo
				
				bloqueActual = blockchain.get(i); 
				
				bloqueAnterior = blockchain.get(i-1); 
				
				// comparamos hash registrado y el hash calculado : 
				
				if(!bloqueActual.hash.equals(bloqueActual.calcularHash())) {
					// si el hash del bloque actual es distinto al hash calculado
					
					System.out.println("El hash Actual no es igual");
					return false;
			
				}
				
				// comparamos el hash anterior y el hash anterior registrado.
				
				if(!bloqueAnterior.hash.equals(bloqueActual.hashAnterior)) {
					// si el hash del bloque anterior es distinto al hash anterior del bloque actual.
					// parece confuso pero es simplemente el hash del bloque anterior que aparece en el actual
					// comparamos si el hashAnterior es igual al hash del bloque anterior.
					
					System.out.println("El Hash Anterior no es igual");
					return false;
					
				}
				
				if(!bloqueActual.hash.substring(0,dificultad).equals(hashTarget)) {
					System.out.println("Este bloque no fue minado");
					return false;
					
				}
				
				
			}
			
			return true;
		}
}
