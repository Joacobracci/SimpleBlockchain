
import java.util.Date;

public class Block {

		public String hash;  // Hash del bloque
		public String hashAnterior; // Hash del bloque anterior
		private String datos; // datos del mensaje
		private long timeStamp; // marca de tiempo en segundos desde 1 1 1970
		
		
		public String calcularHash() {
			
			String hashCalculado = StringUtil.applicarSha256(
						hashAnterior + 
						Long.toString(timeStamp) + 
						datos
					
					);
			return hashCalculado;
		}
		// Le aplicamos el fucking SHA256
		
		// Metodo Constructor
		
			public Block(String hashAnterior, String datos) {
				
		
				this.hashAnterior = hashAnterior;
				this.datos = datos;
				this.timeStamp = new Date().getTime();
				this.hash = calcularHash(); 
				
				
				// metodo constructo del bloque, con el hash anterior, los datos y el tiempo.
				// asumo que el hash se va a generar a partir del hashanterior para que si este cambia 
				// o alguien altere el hash, se arruine la cadena y se pueda detectar.
				
			}
			
}
