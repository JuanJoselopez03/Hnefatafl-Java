package tablero;


/**
 * Clase que representa una coordenada en un tablero de Reversi.
 *
 * @version 1
 * @author Juan José
 */
public class Position {

    private int fila;
    private int columna;

    /**
     * Constructor para crear una coordenada con una fila y columna especificadas.
     *
     * @param fila    la fila de la coordenada
     * @param columna la columna de la coordenada
     */
    public Position(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    /**
     * Obtiene la fila de la coordenada.
     *
     * @return la fila de la coordenada
     */
    public int getFila() {
        return fila;
    }

    /**
     * Obtiene la columna de la coordenada.
     *
     * @return la columna de la coordenada
     */
    public int getColumna() {
        return columna;
    }


}
