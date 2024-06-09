//package tablero;
//
//import java.util.Scanner;
//
//import ficha.Ficha;
//
//public class Move {
//    private Tablero tablero;
//
//    public Move(Tablero tablero) {
//        this.tablero = tablero;
//    }
//
//    public void moverFichaDesdeConsola() {
//        Scanner scanner = new Scanner(System.in);
//
//        // Solicitar coordenadas de la casilla de origen
//        System.out.println("Introduce la fila y columna de la casilla de origen (separadas por un espacio):");
//        int filaOrigen = scanner.nextInt();
//        int columnaOrigen = scanner.nextInt();
//
//        // Solicitar coordenadas de la casilla de destino
//        System.out.println("Introduce la fila y columna de la casilla de destino (separadas por un espacio):");
//        int filaDestino = scanner.nextInt();
//        int columnaDestino = scanner.nextInt();
//
//        // Verificar si el movimiento es válido
//        if (esMovimientoValido(filaOrigen, columnaOrigen, filaDestino, columnaDestino)) {
//            // Si el movimiento es válido, mover la ficha
//            moverFicha(filaOrigen, columnaOrigen, filaDestino, columnaDestino);
//        } else {
//            // Si el movimiento no es válido, mostrar un mensaje de error
//            System.out.println("Movimiento inválido. Intenta de nuevo.");
//        }
//    }
//
//    private boolean esMovimientoValido(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {
//        // Verificar si la casilla de destino está dentro del tablero
//        if (filaDestino < 0 || filaDestino >= 11 || columnaDestino < 0 || columnaDestino >= 11) {
//            return false; // Movimiento fuera del tablero
//        }
//        // Verificar si la casilla de destino está vacía
//        if (tablero.getCasillas()[filaDestino][columnaDestino].getTipoPieza() != Ficha.VACIA) {
//            return false; // La casilla de destino no está vacía
//        }
//        // Verificar si el movimiento es válido según las reglas del juego
//        Ficha tipoPiezaOrigen = tablero.getCasillas()[filaOrigen][columnaOrigen].getTipoPieza();
//        if (tipoPiezaOrigen == Ficha.REY) {
//            // Para el REY, solo puede moverse a una casilla adyacente
//            int difFila = Math.abs(filaDestino - filaOrigen);
//            int difColumna = Math.abs(columnaDestino - columnaOrigen);
//            if (difFila <= 1 && difColumna <= 1) {
//                return true;
//            }
//        } else if (tipoPiezaOrigen == Ficha.ATACANTE || tipoPiezaOrigen == Ficha.DEFENSOR) {
//            // Para los ATACANTES y DEFENSORES, pueden moverse en línea recta hacia adelante, atrás, izquierda o derecha
//            if (filaOrigen == filaDestino || columnaOrigen == columnaDestino) {
//                return true;
//            }
//        }
//        return false; // El movimiento no es válido según las reglas del juego
//    }
//
//    private void moverFicha(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {
//        tablero.getCasillas()[filaDestino][columnaDestino].setTipoPieza(tablero.getCasillas()[filaOrigen][columnaOrigen].getTipoPieza());
//        tablero.getCasillas()[filaOrigen][columnaOrigen].setTipoPieza(Ficha.VACIA); 
//    }
//}