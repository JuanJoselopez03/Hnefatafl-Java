package partida;


/**
 * Clase principal que ejecuta una partida del juego.
 * 
 * La clase Main contiene el método principal main y un método partida para iniciar el juego.
 * 
 * @autor Juan José
 * @version 1.0
 */
public class Main {
	
	/**
	 * Inicia una nueva partida del juego.
	 * 
	 * Este método crea una instancia de la clase Game y llama al método playGame para comenzar la partida.
	 */
	public void partida() {
		
		Game partida = new Game();
		partida.playGame();
		
	}

	/**
	 * Método principal que inicia la ejecución del programa.
	 * 
	 */
	public static void main(String[] args) {
		new Main().partida();
	}
}
