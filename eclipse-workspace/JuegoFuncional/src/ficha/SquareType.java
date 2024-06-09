package ficha;

/**
 * Enum de tipos de casillas especiales que se usa en el tablero para determinar
 * que casillas son especiales dentro del juego TRONO ESQUINA CENTRO NORMAL
 */

public enum SquareType {
	TRONO("\u001B[43m"), 
	ESQUINA("\u001B[41m"), NORMAL(" "), DESTINO(""), ORIGEN(" ");

	private final String color;

	SquareType(String color) {
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	public String setColor(String colorOriginalOrigen) {

		return color;
	}
}
