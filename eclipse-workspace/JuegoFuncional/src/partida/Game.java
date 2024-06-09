package partida;

import java.util.Random;
import java.util.Scanner;

import console.ConsoleInput;
import ficha.Token;
import jugadores.AiPlayer;
import jugadores.HumanPlayer;
import jugadores.Player;
import tablero.Board;

/**
 * Clase que representa una partida del juego Hneftati. Permite a los jugadores
 * enfrentarse entre sí o jugar contra la IA.
 * 
 * Esta clase maneja las diferentes opciones de juego y coordina las
 * interacciones entre los jugadores y el tablero.
 * 
 * @author Juan José López Peña
 * @version 1.0
 */
@SuppressWarnings("unused")
public class Game {

	private Scanner scanner;
	private Board tablero;
	ConsoleInput input = new ConsoleInput(new Scanner(System.in));

	/**
	 * Constructor de la clase Partida. Inicializa el scanner y el tablero.
	 */
	public Game() {
		tablero = new Board();
	}

	/**
	 * Método principal que inicia y controla la partida. Permite al usuario
	 * seleccionar el modo de juego y continúa hasta que el usuario decide salir.
	 */
	public void playGame() {
		int opcion;

		System.out.println(
				"Bienvenido al Hnefatafl. Por favor, elija una de las opciones que se le mostrarán a continuación: ");

		do {
			System.out.println("\nSeleccione el modo de juego:");
			System.out.println("1. Jugador vs. Jugador");
			System.out.println("2. Jugador vs. IA");
			System.out.println("3. IA vs. IA");
			System.out.println("0. Salir");
			System.out.print("Opción: ");
			opcion = input.readIntInRange(0, 3);

			switch (opcion) {
			case 1:
				pvP();
				break;
			case 2:
				pVsCpu();
				break;
			case 3:
				aiVsAi();
				break;
			case 0:
				System.out.println("¡Hasta luego!");
				return;
			default:
				System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
				break;
			}
		} while (opcion != 0);
	}

	/**
	 * Método que maneja una partida entre dos jugadores humanos. Cada jugador
	 * realiza sus movimientos de forma alternada hasta que el juego termina.
	 */
	private void pvP() {
		
		int fichaJugador1, fichaJugador2;
		boolean turnoJugador1 = true;
		boolean movimientoValido = false;

		System.out.println("Jugador 1, elige tu ficha (0 para Atacante, 1 para Defensor):");
		fichaJugador1 = input.readInt();
		Token tokenJugador1 = (fichaJugador1 == 0) ? Token.ATTACKER : Token.DEFFENDER;

		fichaJugador2 = (fichaJugador1 == 0) ? 1 : 0;
		Token tokenJugador2 = (fichaJugador2 == 0) ? Token.ATTACKER : Token.DEFFENDER;

		Player jugador1 = new HumanPlayer("Jugador 1", tokenJugador1, scanner);
		Player jugador2 = new HumanPlayer("Jugador 2", tokenJugador2, scanner);
		Player jugadorActual = turnoJugador1 ? jugador1 : jugador2;

		System.out.println("Jugador 1 es " + ((tokenJugador1 == Token.ATTACKER) ? "Atacante" : "Defensor"));
		System.out.println("Jugador 2 es " + ((tokenJugador2 == Token.ATTACKER) ? "Atacante" : "Defensor"));

		tablero.board();
		tablero.showBoard();

		while (!tablero.lastGame()) {
			do {
				((HumanPlayer) jugadorActual).realizarMovimiento(tablero, jugadorActual.getNombre());
				movimientoValido = tablero.esMovimientoValido();
				if (movimientoValido) {
					tablero.showBoard();
					System.out.println("Movimiento inválido. Intente de nuevo.");
				}
			} while (!movimientoValido);

			jugadorActual = turnoJugador1 ? jugador2 : jugador1;
			turnoJugador1 = !turnoJugador1;

			tablero.showBoard();
		}

		tablero.result();
	}

	/**
	 * Método que maneja una partida entre un jugador humano y la IA. El jugador
	 * humano realiza sus movimientos y luego la IA realiza los suyos hasta que el
	 * juego termina.
	 */
	private void pVsCpu() {
	    // Solicitar al jugador que elija el lado
	    System.out.println("Elige tu lado (Atacante o Defensor): ");
	    Scanner scanner = new Scanner(System.in);
	    String ladoJugador = scanner.nextLine();

	    // Validar la entrada del jugador y asignar los roles
	    Token ladoJugadorToken = Token.ATTACKER;
	    Token ladoIAToken = Token.DEFFENDER;
	    if (ladoJugador.equalsIgnoreCase("Defensor")) {
	        ladoJugadorToken = Token.DEFFENDER;
	        ladoIAToken = Token.ATTACKER;
	    }

	    // Crear los jugadores con los roles asignados
	    Player jugador1 = new HumanPlayer("Jugador", ladoJugadorToken, scanner);
	    AiPlayer jugador2 = new AiPlayer("IA", ladoIAToken);

	    Board tablero = new Board();

	    while (!tablero.lastGame()) {
	        tablero.showBoard();
	        jugador1.realizarMovimiento(tablero, jugador1.getNombre());
	        tablero.showBoard();
	        if (!tablero.lastGame()) {
	            jugador2.realizarMovimiento(tablero, jugador2.getNombre());
	            tablero.showBoard();
	        }
	    }

	    tablero.result();

	}



	/**
	 * Método que maneja una partida entre dos jugadores IA. Ambos jugadores IA
	 * realizan movimientos de forma automática hasta que el juego termina.
	 */

	private void aiVsAi() {
	    Random random = new Random();

	    Token tokenJugador1 = (random.nextInt(2) == 0) ? Token.ATTACKER : Token.DEFFENDER;
	    AiPlayer jugador1 = new AiPlayer("IA 1", tokenJugador1);

	    Token tokenJugador2 = (tokenJugador1 == Token.ATTACKER) ? Token.DEFFENDER : Token.ATTACKER;
	    AiPlayer jugador2 = new AiPlayer("IA 2", tokenJugador2);

	    tablero.board();
	    tablero.showBoard();

	    System.out.println("IA 1 es " + tokenJugador1);
	    System.out.println("IA 2 es " + tokenJugador2);

	    while (!tablero.lastGame()) {
	        jugador1.realizarMovimientoAtacantes(tablero);
	        tablero.showBoard();
	        if (!tablero.lastGame()) {
	            jugador2.realizarMovimientoDefensores(tablero);
	            tablero.showBoard();
	        }
	    }

	    tablero.result();
	}
}
