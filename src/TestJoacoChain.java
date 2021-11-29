import java.util.ArrayList;

import com.google.gson.GsonBuilder;


public class TestJoacoChain {

		public static ArrayList<Block> blockchain = new ArrayList<Block>();
		
		
		public static void main(String[] args) {
		
		// Agregamos nuestros bloques al array de la blockchain
			
			blockchain.add(new Block("Hola soy el primer bloque" , "0"));
			blockchain.add(new Block("Soy el segundo bloque",blockchain.get(blockchain.size()-1).hash)); 
			// devuelve el hash del elemento en una posicion especifica que equivale a el tama;o de la blockchain -1, 
			// osea el bloque anterior. (el hash del bloque anterior)
			blockchain.add(new Block("Soy el tercer bloque",blockchain.get(blockchain.size()-1).hash)); 
			// mismo devuelve el hash del elemento anterior del array.
			
			String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
			System.out.println(blockchainJson);
		
		
	}
}
