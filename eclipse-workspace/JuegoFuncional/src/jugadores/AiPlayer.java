package jugadores;

import java.util.Random;
import java.util.stream.IntStream;

import ficha.Token;
import tablero.Board;
import tablero.Square;

/**
 * Clase que representa a un jugador de IA en el juego Hneftati. Extiende la
 * clase abstracta Jugador.
 * 
 * @author [Tu nombre]
 * @version 1.0
 */
public class AiPlayer extends Player {

    public AiPlayer(String nombre, Token ficha) {
        super(nombre, ficha);
    }

    @Override
    public void realizarMovimiento(Board tablero, String nombreJugador) {
        // Realizar movimiento de la IA según el tipo de ficha
        if (this.getFicha() == Token.ATTACKER) {
            realizarMovimientoAtacantes(tablero);
        } else if (this.getFicha() == Token.DEFFENDER) {
            realizarMovimientoDefensores(tablero);
        }

        // Actualizar la visualización del tablero
        tablero.showBoard(); // Suponiendo que tengas un método para mostrar el tablero en la consola
        System.out.println("Turno de " + nombreJugador);
        // Resto del código para que el jugador humano ingrese su movimiento...
    }

    private void realizarMovimiento(Board tablero, Token tipoPieza, boolean incluirRey) {
        // Implementa la lógica de la IA para decidir el movimiento
        Random random = new Random();

        IntStream.generate(() -> random.nextInt(11)).limit(4).mapToObj(
                i -> new int[] { random.nextInt(11), random.nextInt(11), random.nextInt(11), random.nextInt(11) })
                .filter(coordinates -> {
                    int filaOrigen = coordinates[0];
                    int columnaOrigen = coordinates[1];
                    int filaDestino = coordinates[2];
                    int columnaDestino = coordinates[3];
                    Square casillaOrigen = tablero.getCasillas()[filaOrigen][columnaOrigen];
                    Square casillaDestino = tablero.getCasillas()[filaDestino][columnaDestino];
                    return (casillaOrigen.getTipoPieza() == tipoPieza
                            || (incluirRey && casillaOrigen.getTipoPieza() == Token.KING))
                            && tablero.isValid(filaOrigen, columnaOrigen, filaDestino, columnaDestino);
                }).findFirst().ifPresent(coordinates -> {
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

                    tablero.getCasillas()[filaDestino][columnaDestino].setMovida(true);
                });
    }

    public void realizarMovimientoAtacantes(Board tablero) {
        realizarMovimiento(tablero, Token.ATTACKER, false);
    }

    public void realizarMovimientoDefensores(Board tablero) {
        realizarMovimiento(tablero, Token.DEFFENDER, true);
    }
}
