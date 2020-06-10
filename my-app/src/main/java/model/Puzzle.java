/**
 * Describes the Puzzle, which is comprised of eight cubes and has a number of operations associated with it. 
 * 
 * @author Mariko Briggs
 * @version 1.0 
 * @since April 2020 
 */
package model;

public class Puzzle {
    public static final int NUM_OF_CUBES = 8;

    private Cube[] cubes;
    // Corner[] corners;

    public Puzzle(Cube c1, Cube c2, Cube c3, Cube c4, Cube c5, Cube c6, Cube c7, Cube c8) {
        this.cubes = new Cube[] { c1, c2, c3, c4, c5, c6, c7, c8 };
    }

    public Puzzle() {
    }

    public Boolean isSolvable() {
        // return this == solvable
        return false;
    }

    public Cube[] getCubes() {
        return cubes;
    }

    public void setCubes(int index, Cube cube) {
        cubes[index] = cube;
    }

    @Override
    public String toString() {
        Cube[] arr = this.getCubes();
        StringBuilder puzzleString = new StringBuilder();
        for (int i = 0; i < Puzzle.NUM_OF_CUBES; i++) {
            puzzleString.append((i + 1) + "." + arr[i].toString() + "\n");
        }
        return puzzleString.toString();
    }

}