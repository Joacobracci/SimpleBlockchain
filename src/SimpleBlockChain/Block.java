package SimpleBlockChain;

import java.util.Date;

public class Block {

		public String hash;  // Hash del bloque
		public String hashAnterior; // Hash del bloque anterior
		private String datos; // datos del mensaje
		private long timeStamp; // marca de tiempo en segundos desde 1 1 1970
		
		private int nonce;
		
		// Metodo Constructor
		
		public Block(String datos, String hashAnterior) {
						
				
			this.hashAnterior = hashAnterior;
			this.datos = datos;
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
						datos
					
					);
			
			return hashCalculado;
		}
		
		// Le aplicamos el fucking SHA256
		
		// el metodo toma un int llamado dificultad, este es el numero de 0 que tiene que resolver.
		
		public void minarBloque(int dificultad) {
			String target = new String(new char[dificultad]).replace('\0','0');
			// Creamos un string con dificultad * "0" 
			
			while(!hash.substring(0, dificultad).equals(target) ) {
				nonce ++;
				hash = calcularHash();
			}
			// Mientras que el bloque no este minado que siga minando xD algo asi no se explicarlo.
			
			System.out.println("Bloque Minado!!!!!!" + hash);
		}
			
}
