package tablero;

import ficha.Token;
import ficha.SquareType;

public class Movimiento {

	private Board board;

	public Movimiento(Board board) {
		this.board = board;
	}

	/**
	 * Comprueba si un movimiento desde una casilla de origen a una casilla de
	 * destino es válido.
	 * 
	 * @param filaOrigen     La fila de la casilla de origen.
	 * @param columnaOrigen  La columna de la casilla de origen.
	 * @param filaDestino    La fila de la casilla de destino.
	 * @param columnaDestino La columna de la casilla de destino.
	 * @return true si el movimiento es válido, false si no lo es.
	 */
	public boolean isValid(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {
		int minColumna = Math.min(columnaOrigen, columnaDestino);
		int maxColumna = Math.max(columnaOrigen, columnaDestino);
		int minFila = Math.min(filaOrigen, filaDestino);
		int maxFila = Math.max(filaOrigen, filaDestino);

		if (filaDestino < 0 || filaDestino >= 11 || columnaDestino < 0 || columnaDestino >= 11
				|| board.isCorner(filaDestino, columnaDestino)) {
			return false;
		}

		if (board.getCasillas()[filaDestino][columnaDestino].getTipoPieza() != Token.EMPTY) {
			return false;
		}

		if (filaDestino != filaOrigen && columnaDestino != columnaOrigen) {
			return false;
		}

		if (filaDestino == filaOrigen) {
			for (int j = minColumna + 1; j < maxColumna; j++) {
				if (board.getCasillas()[filaDestino][j].getTipoPieza() != Token.EMPTY) {
					return false;
				}
			}
		} else { 
			for (int i = minFila + 1; i < maxFila; i++) {
				if (board.getCasillas()[i][columnaDestino].getTipoPieza() != Token.EMPTY) {
					return false; // Hay una ficha en el camino
				}
			}
		}

		return true;
	}

	/**
	 * Metodo que realiza el movimiento de una ficha desde una casilla de origen a
	 * una casilla de destino.
	 * 
	 * @param filaOrigen     La fila de la casilla de origen.
	 * @param columnaOrigen  La columna de la casilla de origen.
	 * @param filaDestino    La fila de la casilla de destino.
	 * @param columnaDestino La columna de la casilla de destino.
	 */
	public void moveToken(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {
		String colorOriginalOrigen = board.getCasillas()[filaOrigen][columnaOrigen].getTipoCasilla().getColor();
		String colorFondoDestino = "\u001B[45m";
		String colorFondoOrigen = "\u001B[46m";

		if (!isValid(filaOrigen, columnaOrigen, filaDestino, columnaDestino)) {
			System.out.println("Movimiento inválido. Intente de nuevo.");
		}

		SquareType estadoOriginalOrigen = board.getCasillas()[filaOrigen][columnaOrigen].getTipoCasilla();
		board.getCasillas()[filaOrigen][columnaOrigen].setTipoCasilla(SquareType.ORIGEN);

		SquareType estadoOriginalDestino = board.getCasillas()[filaDestino][columnaDestino].getTipoCasilla();
		board.getCasillas()[filaDestino][columnaDestino].setTipoCasilla(SquareType.DESTINO);

		Token tipoPieza = board.getCasillas()[filaOrigen][columnaOrigen].getTipoPieza();
		board.getCasillas()[filaDestino][columnaDestino].setTipoPieza(tipoPieza);
		board.getCasillas()[filaOrigen][columnaOrigen].setTipoPieza(Token.EMPTY);

		
		changeColor(filaDestino, columnaDestino, colorFondoDestino);

		board.showBoard();

		board.getCasillas()[filaOrigen][columnaOrigen].setTipoCasilla(estadoOriginalOrigen);
		board.getCasillas()[filaDestino][columnaDestino].setTipoCasilla(estadoOriginalDestino);

		changeColor(filaOrigen, columnaOrigen, colorFondoOrigen);
	}

	/**
	 * Cambia el color de fondo de una casilla después de que se haya movido una
	 * ficha.
	 * 
	 * @param fila              La fila de la casilla.
	 * @param columna           La columna de la casilla.
	 * @param colorFondoDestino El color de fondo que se desea aplicar s
	 */
	public void changeColor(int fila, int columna, String colorFondoDestino) {
		board.getCasillas()[fila][columna].setTipoCasilla(SquareType.ESQUINA);
	}
}