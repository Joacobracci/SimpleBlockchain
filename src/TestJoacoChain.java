
public class TestJoacoChain {

	public static void main(String[] args) {
		
		Block bloqueGenesis = new Block ("0" , " Hola soy el primer bloque");
		System.out.println("Hash para el bloque 1 : " + bloqueGenesis.hash);
		
		Block bloqueDos = new Block (bloqueGenesis.hash , " Hola soy el primer bloque");
		System.out.println("Hash para el bloque 2 : " + bloqueDos.hash);
		
		Block bloqueTres = new Block (bloqueDos.hash , " Hola soy el primer bloque");
		System.out.println("Hash para el bloque 3 : " + bloqueTres.hash);
		
		
	}
}
