package sample;

public class PacMan extends Player {

    public PacMan(Map map, int movesPerCell, int x0, int y0) {
        super(map, movesPerCell, x0, y0);
    }

    @Override
    public int move() {
        super.move();
        return map.eat(x, y);
    }
}
