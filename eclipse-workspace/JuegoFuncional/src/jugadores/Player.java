package jugadores;

import ficha.Token;
import tablero.Board;


/**
 * Clase que representa a un jugador en el juego de Hneftati.
 *
 * @version 1
 * 
 */
public  class Player {

    protected String nombre;
    protected Token ficha;

    /**
     * Constructor que crea un jugador con el nombre y la ficha especificados.
     *
     * @param nombre el nombre del jugador
     * @param ficha  la ficha del jugador
     */
    public Player(String nombre, Token ficha) {
        this.nombre = nombre;
        this.ficha = ficha;
    }

    /**
     * Obtiene el nombre del jugador.
     *
     * @return el nombre del jugador
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la ficha del jugador.
     *
     * @return la ficha del jugador
     */
    public Token getFicha() {
        return ficha;
    }


	/**
	 * Método que permite al jugador humano realizar un movimiento en el tablero. El
	 * jugador introduce las coordenadas de la casilla de origen y destino desde la
	 * consola.
	 * 
	 * @param tablero El tablero en el que se realiza el movimiento.
	 */
	public void realizarMovimiento(Board tablero, String nombreJugador) {
		// TODO Auto-generated method stub
		
	}




}
