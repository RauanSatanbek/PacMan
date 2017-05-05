package sample;

import java.lang.*;

public class Ghost extends Player {
    static final int PAC_MAN_COLLISION = 1;
    public final PacMan target;

    public Ghost(Map map, int movesPerCell, int x0, int y0, PacMan pacMan) {
        super(map, movesPerCell, x0, y0);
        this.target = pacMan;
    }

    /**
     * Control del movimiento del fantasma. Cada vez que el fantasma esta en el centro de una celda (offsets=0) se
     * calculan las posibles direcciones. Con una probabilidad de 0.25 el fantasma eligirá el camino de forma
     * aleatoria. Si no, se calcularán las distancias x e y entre el fantasma y el comecocos dando prioridad a las
     * menores en cada eje y con un 0.5 de probabilidad se establecerá prioridad para el eje vertical. Se establecerá
     * la direccion prioritaria dentro de las posibles. El fantasma nunca cambia de sentido.
     */
    public int move(boolean godMode) {
        int dx = Math.abs((x - target.x) * movesPerCell + xOffset - target.xOffset);
        int dy = Math.abs((y - target.y) * movesPerCell + yOffset - target.yOffset);

        if (dx < movesPerCell && dy < movesPerCell)
            return PAC_MAN_COLLISION;

        if (xOffset == 0 && yOffset == 0) {
            int possibleDirections = 0;
            if (map.move(x, y, Map.DOWN) && currentDirection != Map.UP) possibleDirections |= Map.DOWN;
            if (map.move(x, y, Map.UP) && currentDirection != Map.DOWN) possibleDirections |= Map.UP;
            if (map.move(x, y, Map.RIGHT) && currentDirection != Map.LEFT) possibleDirections |= Map.RIGHT;
            if (map.move(x, y, Map.LEFT) && currentDirection != Map.RIGHT) possibleDirections |= Map.LEFT;

            int direction = 0;

            if (Math.random() > 0.75) {
                while (direction == 0) {
                    double d = Math.random();
                    if (d < 0.25) direction = Map.DOWN & possibleDirections;
                    else if (d < 0.5) direction = Map.UP & possibleDirections;
                    else if (d < 0.75) direction = Map.RIGHT & possibleDirections;
                    else direction = Map.LEFT & possibleDirections;
                }
            } else {
                int[] priority = { Map.DOWN, Map.RIGHT, Map.UP, Map.LEFT };

                if (x > target.x) {
                    priority[1] = Map.LEFT;
                    priority[3] = Map.RIGHT;
                }
                if (y > target.y) {
                    priority[0] = Map.UP;
                    priority[2] = Map.DOWN;
                }

                int aux;
                double verticalPriority = 0.5;

                if (priority[0] != currentDirection) verticalPriority = 0.75;
                else if (priority[1] == currentDirection) verticalPriority = 0.25;

                if (Math.random() < verticalPriority) {
                    aux = priority[0];
                    priority[0] = priority[1];
                    priority[1] = aux;
                    aux = priority[2];
                    priority[2] = priority[3];
                    priority[3] = aux;
                }

                int i = 0;
                if (godMode) i = 3;

                while (direction == 0) {
                    direction = priority[i] & possibleDirections;
                    if (godMode) i--;
                    else i++;
                }
            }

            this.nextDirection = direction;
        }

        return super.move();
    }
}
