package tablero;

import java.util.Scanner;
import ficha.Token;
import ficha.SquareType;
import jugadores.Player;

/**
 * Clase que representa el tablero del juego Reversi. Este tablero contiene
 * casillas con fichas de diferentes tipos.
 * 
 * @author Juan José
 * @version 1
 */
public class Board {
	
	/**
	 * Matriz bidimensional que representa las casillas del tablero del juego.
	 * Cada elemento de la matriz corresponde a una casilla en el tablero.
	 * 
	 * @see Square
	 */
	private Square[][] casillas;

	/**
	 * Constructor de la clase Tablero. Inicializa el tablero y todas las casillas
	 * con fichas vacías.
	 */
	public Board() {
		casillas = new Square[11][11];
		board();
	}

	/**
	 * Inicializa el tablero con las fichas en sus posiciones iniciales.
	 */
	public void board() {
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				casillas[i][j] = new Square();
			}
		}

		casillas[5][5].setTipoPieza(Token.KING);
		casillas[0][3].setTipoPieza(Token.ATTACKER);
		casillas[0][4].setTipoPieza(Token.ATTACKER);
		casillas[0][5].setTipoPieza(Token.ATTACKER);
		casillas[0][6].setTipoPieza(Token.ATTACKER);
		casillas[0][7].setTipoPieza(Token.ATTACKER);
		casillas[1][5].setTipoPieza(Token.ATTACKER);
		casillas[3][0].setTipoPieza(Token.ATTACKER);
		casillas[4][0].setTipoPieza(Token.ATTACKER);
		casillas[5][0].setTipoPieza(Token.ATTACKER);
		casillas[6][0].setTipoPieza(Token.ATTACKER);
		casillas[7][0].setTipoPieza(Token.ATTACKER);
		casillas[5][1].setTipoPieza(Token.ATTACKER);
		casillas[3][10].setTipoPieza(Token.ATTACKER);
		casillas[4][10].setTipoPieza(Token.ATTACKER);
		casillas[5][10].setTipoPieza(Token.ATTACKER);
		casillas[6][10].setTipoPieza(Token.ATTACKER);
		casillas[7][10].setTipoPieza(Token.ATTACKER);
		casillas[5][9].setTipoPieza(Token.ATTACKER);
		casillas[10][4].setTipoPieza(Token.ATTACKER);
		casillas[10][5].setTipoPieza(Token.ATTACKER);
		casillas[10][6].setTipoPieza(Token.ATTACKER);
		casillas[10][7].setTipoPieza(Token.ATTACKER);
		casillas[10][3].setTipoPieza(Token.ATTACKER);
		casillas[9][5].setTipoPieza(Token.ATTACKER);
		casillas[3][5].setTipoPieza(Token.DEFFENDER);
		casillas[4][5].setTipoPieza(Token.DEFFENDER);
		casillas[6][6].setTipoPieza(Token.DEFFENDER);
		casillas[6][5].setTipoPieza(Token.DEFFENDER);
		casillas[6][4].setTipoPieza(Token.DEFFENDER);
		casillas[7][5].setTipoPieza(Token.DEFFENDER);
		casillas[5][6].setTipoPieza(Token.DEFFENDER);
		casillas[5][7].setTipoPieza(Token.DEFFENDER);
		casillas[5][4].setTipoPieza(Token.DEFFENDER);
		casillas[5][3].setTipoPieza(Token.DEFFENDER);
		casillas[4][4].setTipoPieza(Token.DEFFENDER);
		casillas[4][6].setTipoPieza(Token.DEFFENDER);
		casillas[0][0].setTipoCasilla(SquareType.ESQUINA);
		casillas[0][10].setTipoCasilla(SquareType.ESQUINA);
		casillas[10][0].setTipoCasilla(SquareType.ESQUINA);
		casillas[10][10].setTipoCasilla(SquareType.ESQUINA);
		casillas[5][5].setTipoCasilla(SquareType.TRONO);
	}

	public void showBoard() {
	    System.out.print("   ");
	    for (int j = 0; j < 11; j++) {
	        System.out.print(j + " ");
	    }
	    System.out.println();

	    String marronClaro = "\u001B[43m";
	    String blanco = "\u001B[47m";
	    String azul = "\u001B[44m";
	    String cyan = "\u001B[46m";
	    String magenta = "\u001B[45m";
	    String reset = "\u001B[0m";
	    String tipoPieza;

	    for (int i = 0; i < 11; i++) {
	        System.out.printf("%2d ", i); 
	        for (int j = 0; j < 11; j++) {
	            if (casillas[i][j].getTipoCasilla() == SquareType.ESQUINA || casillas[i][j].getTipoCasilla() == SquareType.TRONO) {
	                System.out.print(azul); 
	            } else if (casillas[i][j].getTipoCasilla() == SquareType.ORIGEN) {
	                System.out.print(cyan); 
	            } else if (casillas[i][j].getTipoCasilla() == SquareType.DESTINO) {
	                System.out.print(magenta); 
	            } else {
	                if ((i + j) % 2 == 0) {
	                    System.out.print(marronClaro);
	                } else {
	                    System.out.print(blanco);
	                }
	            }

	            if (casillas[i][j].getTipoPieza() != Token.EMPTY) {
	                if (casillas[i][j].getTipoPieza() == Token.KING) {
	                    tipoPieza = "R";
	                } else if (casillas[i][j].getTipoPieza() == Token.ATTACKER) {
	                    tipoPieza = "A";
	                } else { 
	                    tipoPieza = "D";
	                }
	                System.out.print(tipoPieza + " ");
	            } else {
	                System.out.print("  "); 
	            }
	            System.out.print(reset); 
	        }
	        System.out.println();
	    }
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
	    // Verificar si la casilla de destino está dentro del tablero y no es una esquina
	    if (filaDestino < 0 || filaDestino >= 11 || columnaDestino < 0 || columnaDestino >= 11 ||
	        isCorner(filaDestino, columnaDestino)) {
	        return false;
	    }

	    // Verificar si la casilla de destino está vacía
	    if (casillas[filaDestino][columnaDestino].getTipoPieza() != Token.EMPTY) {
	        return false;
	    }

	    // Verificar si el movimiento es horizontal o vertical
	    if (filaDestino != filaOrigen && columnaDestino != columnaOrigen) {
	        return false;
	    }

	    // Verificar si no hay fichas en el camino hacia la casilla de destino
	    if (filaDestino == filaOrigen) { // Movimiento horizontal
	        int minColumna = Math.min(columnaOrigen, columnaDestino);
	        int maxColumna = Math.max(columnaOrigen, columnaDestino);
	        for (int j = minColumna + 1; j < maxColumna; j++) {
	            if (casillas[filaDestino][j].getTipoPieza() != Token.EMPTY) {
	                return false; // Hay una ficha en el camino
	            }
	        }
	    } else { // Movimiento vertical
	        int minFila = Math.min(filaOrigen, filaDestino);
	        int maxFila = Math.max(filaOrigen, filaDestino);
	        for (int i = minFila + 1; i < maxFila; i++) {
	            if (casillas[i][columnaDestino].getTipoPieza() != Token.EMPTY) {
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
	    if (!isValid(filaOrigen, columnaOrigen, filaDestino, columnaDestino)) {
	        System.out.println("Movimiento inválido. Intente de nuevo.");
	        return; // Salir del método si el movimiento es inválido
	    }

	    // Guardar los estados originales de las casillas
	    SquareType estadoOriginalOrigen = casillas[filaOrigen][columnaOrigen].getTipoCasilla();
	    SquareType estadoOriginalDestino = casillas[filaDestino][columnaDestino].getTipoCasilla();

	    // Realizar el movimiento
	    Token tipoPieza = casillas[filaOrigen][columnaOrigen].getTipoPieza();
	    casillas[filaDestino][columnaDestino].setTipoPieza(tipoPieza);
	    casillas[filaOrigen][columnaOrigen].setTipoPieza(Token.EMPTY);

	    // Cambiar color de las casillas involucradas en el movimiento
	    casillas[filaOrigen][columnaOrigen].setTipoCasilla(SquareType.ORIGEN);
	    casillas[filaDestino][columnaDestino].setTipoCasilla(SquareType.DESTINO);

	    // Imprimir el tablero después del movimiento
	    showBoard();

	    // Restaurar estados originales de las casillas
	    casillas[filaOrigen][columnaOrigen].setTipoCasilla(estadoOriginalOrigen);
	    casillas[filaDestino][columnaDestino].setTipoCasilla(estadoOriginalDestino);
	}

	
	/**
     * Cambia el color de fondo de una casilla después de que se haya movido una ficha.
     * 
     * @param fila    La fila de la casilla.
     * @param columna La columna de la casilla.
     * @param colorFondoDestino   El color de fondo que se desea aplicar (por ejemplo, "\u001B[46m" para cyan).
     */
    public void changeColor(int fila, int columna, String colorFondoDestino) {
        casillas[fila][columna].setTipoCasilla(SquareType.ESQUINA);
    }


	/**
	 * Verifica si una ficha en la posición dada está siendo capturada por el
	 * oponente.
	 * 
	 * @param fila    La fila de la casilla donde se encuentra la ficha.
	 * @param columna La columna de la casilla donde se encuentra la ficha.
	 * @return true si la ficha está siendo capturada, false de lo contrario.
	 */
	public boolean isCapturated(int fila, int columna) {
		Player jugador = null;

		if (jugador == null) {
			return false; // Manejar el caso donde jugador es nulo
		}

		Token fichaJugador = jugador.getFicha();

		if (fichaJugador == null) {
			return false; // Manejar el caso donde fichaJugador es nulo
		}

		Token fichaOponente = (fichaJugador == Token.ATTACKER) ? Token.DEFFENDER : Token.ATTACKER;

		if (columna > 0 && columna < 10 && casillas[fila][columna - 1].getTipoPieza() == fichaOponente
				&& casillas[fila][columna + 1].getTipoPieza() == fichaOponente) {
			return true;
		}

		if (fila > 0 && fila < 10 && casillas[fila - 1][columna].getTipoPieza() == fichaOponente
				&& casillas[fila + 1][columna].getTipoPieza() == fichaOponente) {
			return true;
		}

		if (fila > 0 && fila < 10 && columna > 0 && columna < 10
				&& casillas[fila - 1][columna - 1].getTipoPieza() == fichaOponente
				&& casillas[fila - 1][columna + 1].getTipoPieza() == fichaOponente
				&& casillas[fila + 1][columna - 1].getTipoPieza() == fichaOponente
				&& casillas[fila + 1][columna + 1].getTipoPieza() == fichaOponente) {
			return true;
		}

		return false;
	}

	/**
	 * Metodo que muestra el resultado al final de la partida diciendo quien es el
	 * ganador.
	 */
	public void result() {
		int attacker = 0;
		int deffender = 0;

		// Contar el número de atacantes y defensores en el tablero
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				if (casillas[i][j].getTipoPieza() == Token.ATTACKER) {
					attacker++;
				} else if (casillas[i][j].getTipoPieza() == Token.DEFFENDER) {
					deffender++;
				}
			}
		}

		if (attacker > deffender) {
			System.out.println("¡Los atacantes ganan la partida!");
		} else if (deffender > attacker) {
			System.out.println("¡Los defensores ganan la partida!");
		} else {
			System.out.println("¡La partida termina en empate!");
		}
	}

	/**
	 * Metodo que verifica si el juego ha terminado.
	 * 
	 * @return true si el juego ha terminado, false si no.
	 */
	
	public boolean lastGame() {
		int atacantes = 0;
		int defensores = 0;

		// Contar el número de atacantes y defensores en el tablero
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				if (casillas[i][j].getTipoPieza() == Token.ATTACKER) {
					atacantes++;
				} else if (casillas[i][j].getTipoPieza() == Token.DEFFENDER) {
					defensores++;
				}
			}
		}

		return atacantes == 0 || defensores == 0;
	}

	public boolean esMovimientoValido() {
		return false;
	}

	public Board copy() {
		Board nuevoTablero = new Board();

		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				Token tipoPieza = this.casillas[i][j].getTipoPieza();
				nuevoTablero.casillas[i][j].setTipoPieza(tipoPieza);
			}
		}

		return nuevoTablero;
	}

	public Square[][] getCasillas() {
		return casillas;
	}

	public boolean isCorner(int fila, int columna) {
		return (fila == 0 && columna == 0) || (fila == 0 && columna == 10) || (fila == 10 && columna == 0)
				|| (fila == 10 && columna == 10);
	}

	public boolean isrodeated(int filaRey, int columnaRey) {
		Token ficha = null;
		Token enemigo = (ficha == Token.ATTACKER) ? Token.DEFFENDER : Token.ATTACKER;
		int enemigosAlrededor = 0;

		if (filaRey > 0 && columnaRey > 0 && casillas[filaRey - 1][columnaRey - 1].getTipoPieza() == enemigo)
			enemigosAlrededor++;
		if (filaRey > 0 && casillas[filaRey - 1][columnaRey].getTipoPieza() == enemigo)
			enemigosAlrededor++;
		if (filaRey > 0 && columnaRey < 10 && casillas[filaRey - 1][columnaRey + 1].getTipoPieza() == enemigo)
			enemigosAlrededor++;
		if (columnaRey > 0 && casillas[filaRey][columnaRey - 1].getTipoPieza() == enemigo)
			enemigosAlrededor++;
		if (columnaRey < 10 && casillas[filaRey][columnaRey + 1].getTipoPieza() == enemigo)
			enemigosAlrededor++;
		if (filaRey < 10 && columnaRey > 0 && casillas[filaRey + 1][columnaRey - 1].getTipoPieza() == enemigo)
			enemigosAlrededor++;
		if (filaRey < 10 && casillas[filaRey + 1][columnaRey].getTipoPieza() == enemigo)
			enemigosAlrededor++;
		if (filaRey < 10 && columnaRey < 10 && casillas[filaRey + 1][columnaRey + 1].getTipoPieza() == enemigo)
			enemigosAlrededor++;

		return enemigosAlrededor == 3;
	}

}