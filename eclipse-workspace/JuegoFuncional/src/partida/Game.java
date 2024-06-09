package partida;

import java.util.Scanner;

import console.ConsoleInput;
import ficha.Token;
import tablero.Movimiento;
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
			opcion = input.readIntInRange(0,3);

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
		} while (true);
	}

	/**
	 * Método que maneja una partida entre dos jugadores humanos. Cada jugador
	 * realiza sus movimientos de forma alternada hasta que el juego termina.
	 */
	private void pvP() {
	    Player jugador1 = new HumanPlayer("Jugador 1", Token.ATTACKER, scanner);
	    Player jugador2 = new HumanPlayer("Jugador 2", Token.DEFFENDER, scanner);
	    boolean turnoJugador1 = true;
	    Player jugadorActual = turnoJugador1 ? jugador1 : jugador2;
	    boolean movimientoValido = false;

	    tablero.board(); 
	    tablero.showBoard();

	    while (!tablero.lastGame()) {
	        do {
	            ((HumanPlayer) jugadorActual).realizarMovimiento(tablero);
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
	    Player jugador1 = new HumanPlayer("Jugador", Token.ATTACKER, scanner);
	    AiPlayer jugador2 = new AiPlayer("IA", Token.DEFFENDER);

	    tablero.board(); 

	    while (!tablero.lastGame()) {
	        jugador1.realizarMovimiento(tablero);
	        tablero.showBoard();
	        if (!tablero.lastGame()) {
	            jugador2.realizarMovimientoDefensores(tablero);
	            tablero.showBoard();
	        }
	    }

	    tablero.result();
	}


    /**
     * Método que maneja una partida entre dos jugadores IA.
     * Ambos jugadores IA realizan movimientos de forma automática hasta que el juego termina.
     */
	
	private void aiVsAi() {
        AiPlayer jugador1 = new AiPlayer("IA 1", Token.ATTACKER);
        AiPlayer jugador2 = new AiPlayer("IA 2", Token.DEFFENDER);

        tablero.board(); 
        tablero.showBoard();

        while (!tablero.lastGame()) {
            jugador1.realizarMovimientoAtacantes(tablero);
            tablero.showBoard();
            if (!tablero.lastGame()) {
                ((AiPlayer) jugador2).realizarMovimientoDefensores(tablero);
                tablero.showBoard();
            }
        }

        tablero.result();
    }

}
