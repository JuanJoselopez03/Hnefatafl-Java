package jugadores;

import java.util.Scanner;

import console.ConsoleInput;
import ficha.Token;
import tablero.Board;

/**
 * Clase que representa a un jugador humano en el juego Hneftati. Este jugador
 * puede realizar movimientos introduciendo las coordenadas desde la consola.
 * Extiende la clase abstracta Jugador.
 * 
 * @author [Tu nombre]
 * @version 1.0
 */
public class HumanPlayer extends Player {
	private Scanner scanner;
	private ConsoleInput input = new ConsoleInput(new Scanner(System.in));

	/**
	 * Constructor de la clase JugadorHumano.
	 * 
	 * @param nombre  El nombre del jugador humano.
	 * @param ficha   La ficha que representa al jugador en el tablero.
	 * @param scanner El objeto Scanner utilizado para leer la entrada del usuario
	 *                desde la consola.
	 */
	public HumanPlayer(String nombre, Token ficha, Scanner scanner) {
		super(nombre, ficha);
		this.scanner = scanner;
	}

	/**
	 * Método que permite al jugador humano realizar un movimiento en el tablero. El
	 * jugador introduce las coordenadas de la casilla de origen y destino desde la
	 * consola.
	 * 
	 * @param tablero El tablero en el que se realiza el movimiento.
	 */

	@Override
	public void realizarMovimiento(Board tablero, String nombreJugador) {
		System.out.println("Turno de " + nombreJugador);
		System.out.println("Introduce primero la fila dale a enter y luego la columna de la casilla de destino :");
		int filaOrigen = input.readIntInRange(0, 10);
		int columnaOrigen = input.readIntInRange(0, 10);

		System.out.println("Introduce primero la fila dale a enter y luego la columna de la casilla de destino :");
		int filaDestino = input.readIntInRange(0, 10);
		int columnaDestino = input.readIntInRange(0, 10);

		if (tablero.isValid(filaOrigen, columnaOrigen, filaDestino, columnaDestino)) {
			tablero.moveToken(filaOrigen, columnaOrigen, filaDestino, columnaDestino);
		} else {
			System.out.println("Movimiento inválido. Intente de nuevo.");
		}
	}

}
