package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import tablero.Board;

class Hnefatafl_test {

	 @Test
	    void testEsFichaCapturada() {
	        Board tablero = new Board(); // Suponiendo que tienes una instancia de Tablero ya creada
	        
	        // Caso 1: Una ficha en el trono no debe estar capturada
	        assertFalse(tablero.isCapturated(5, 5), "La ficha en el trono no debería estar capturada");
	        
	        // Caso 2: Una ficha en una esquina no debe estar capturada
	        assertFalse(tablero.isCapturated(0, 0), "La ficha en la esquina superior izquierda no debería estar capturada");
	        
	        // Caso 3: Una ficha en una posición adyacente a otra del mismo tipo no debe estar capturada
	        assertFalse(tablero.isCapturated(0, 3), "La ficha no debería estar capturada, ya que no está rodeada por el oponente");
	        
	        // Caso 4: Una ficha rodeada horizontalmente por fichas del oponente debe estar capturada
	        assertTrue(tablero.isCapturated(0, 4), "La ficha debería estar capturada, ya que está rodeada horizontalmente por fichas del oponente");
	        
	        // Caso 5: Una ficha rodeada verticalmente por fichas del oponente debe estar capturada
	        assertTrue(tablero.isCapturated(1, 5), "La ficha debería estar capturada, ya que está rodeada verticalmente por fichas del oponente");
	    }

}
