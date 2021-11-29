package SimpleBlockChainTest;
import java.util.ArrayList;
import com.google.gson.GsonBuilder;
import SimpleBlockChain.*;


public class TestJoacoChain {

		public static ArrayList<Block> blockchain = new ArrayList<Block>();
		public static int dificultad = 5;
		// agrego una dificultad de 5
		
		public static void main(String[] args) {
		
		// Agregamos nuestros bloques al array de la blockchain
			
			blockchain.add(new Block("Hola soy el primer bloque" , "0"));
			System.out.println("Intentando minar el bloque 1....");
			blockchain.get(0).minarBloque(dificultad); // Minamos el bloque
			
			
			blockchain.add(new Block("Soy el segundo bloque",blockchain.get(blockchain.size()-1).hash)); 
			// devuelve el hash del elemento en una posicion especifica que equivale a el tama;o de la blockchain -1, 
			// osea el bloque anterior. (el hash del bloque anterior)
			System.out.println("Tratando de minar el bloque 2");
			blockchain.get(1).minarBloque(dificultad);
			
			
			blockchain.add(new Block("Soy el tercer bloque",blockchain.get(blockchain.size()-1).hash)); 
			// mismo devuelve el hash del elemento anterior del array.
			System.out.println("Intentando minar el tercer bloque ...");
			blockchain.get(2).minarBloque(dificultad);
			
			
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
