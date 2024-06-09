package jugadores;

import java.util.Random;
import java.util.stream.IntStream;

import ficha.Token;
import tablero.Board;
import tablero.Square;

/**
 * Clase que representa a un jugador IA (Inteligencia Artificial) en el juego Hneftati.
 * Este jugador realiza movimientos de manera aleatoria en el tablero. Extiende la clase abstracta Jugador.
 * 
 * @version 1.0
 */
public class AiPlayer extends Player {

    /**
     * Constructor de la clase JugadorIA.
     * 
     * @param nombre El nombre del jugador IA.
     * @param ficha  La ficha que representa al jugador en el tablero.
     */
    public AiPlayer(String nombre, Token ficha) {
        super(nombre, ficha);
    }

    /**
     * Realiza un movimiento de ataque en el tablero.
     * 
     * @param tablero El tablero en el que se realizará el movimiento.
     */
    public void realizarMovimientoAtacantes(Board tablero) {
        if (!tablero.lastGame()) {
            realizarMovimiento(tablero, Token.ATTACKER, false);
        }
    }

    /**
     * Realiza un movimiento de defensa en el tablero.
     * 
     * @param tablero El tablero en el que se realizará el movimiento.
     */
    public void realizarMovimientoDefensores(Board tablero) {
        if (!tablero.lastGame()) {
            realizarMovimiento(tablero, Token.DEFFENDER, true);
        }
    }

    /**
     * Realiza un movimiento en el tablero.
     * 
     * @param tablero       El tablero en el que se realizará el movimiento.
     * @param tipoPieza     El tipo de pieza que realizará el movimiento.
     * @param incluirRey    Indica si se debe incluir el movimiento del rey.
     */
    private void realizarMovimiento(Board tablero, Token tipoPieza, boolean incluirRey) {
        Random random = new Random();

        IntStream.generate(() -> random.nextInt(11))
                .limit(4)
                .mapToObj(i -> new int[] { random.nextInt(11), random.nextInt(11), random.nextInt(11), random.nextInt(11) })
                .filter(coordinates -> {
                    int filaOrigen = coordinates[0];
                    int columnaOrigen = coordinates[1];
                    int filaDestino = coordinates[2];
                    int columnaDestino = coordinates[3];
                    Square casillaOrigen = tablero.getCasillas()[filaOrigen][columnaOrigen];
                    Square casillaDestino = tablero.getCasillas()[filaDestino][columnaDestino];
                    return (casillaOrigen.getTipoPieza() == tipoPieza || (incluirRey && casillaOrigen.getTipoPieza() == Token.KING))
                            && tablero.isValid(filaOrigen, columnaOrigen, filaDestino, columnaDestino);
                })
                .findFirst()
                .ifPresent(coordinates -> {
                    int filaOrigen = coordinates[0];
                    int columnaOrigen = coordinates[1];
                    int filaDestino = coordinates[2];
                    int columnaDestino = coordinates[3];
                    if (!tablero.isCapturated(filaDestino, columnaDestino)) {
                        tablero.moveToken(filaOrigen, columnaOrigen, filaDestino, columnaDestino);
                    } else {
                        tablero.isCapturated(filaDestino, columnaDestino);
                        tablero.moveToken(filaOrigen, columnaOrigen, filaDestino, columnaDestino);
                    }
                });
    }
}