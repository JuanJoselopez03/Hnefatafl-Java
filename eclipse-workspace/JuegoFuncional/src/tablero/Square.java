package tablero;

import ficha.Token;
import ficha.SquareType;

/**
 * Clase que representa una casilla en un tablero de Reversi.
 *
 * @version 1
 * @author Juan José
 */
public class Square {
    private SquareType tipoCasilla;
    private Token tipoPieza;
    private boolean estaOcupada;
    private String color; // Color de fondo de la casilla

    public Square() {
        this.tipoCasilla = SquareType.NORMAL;
        this.tipoPieza = Token.EMPTY;
        this.estaOcupada = false;
        this.color = "";
    }

    public SquareType getTipoCasilla() {
        return tipoCasilla;
    }

    public void setTipoCasilla(SquareType tipoCasilla) {
        this.tipoCasilla = tipoCasilla;
    }

    public Token getTipoPieza() {
        return tipoPieza;
    }

    public void setTipoPieza(Token tipoPieza) {
        this.tipoPieza = tipoPieza;
        if (tipoPieza == Token.EMPTY) {
            this.estaOcupada = false;
        } else {
            this.estaOcupada = true;
        }
    }

    public boolean estaOcupada() {
        return estaOcupada;
    }
    
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}