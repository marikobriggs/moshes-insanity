package model;

public class Puzzle {
    public static final int NUM_OF_CUBES = 8;

    Cube[] cubes;

    public Puzzle(Cube c1, Cube c2, Cube c3, Cube c4, Cube c5, Cube c6, Cube c7, Cube c8) {
        this.cubes = new Cube[] { c1, c2, c3, c4, c5, c6, c7, c8 };
    }

    public Boolean isSolvable(Puzzle puzzle) {
        return false;
    }

}