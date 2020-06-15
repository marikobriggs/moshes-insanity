/**
 * Runs our app. 
 * 
 * @author Mariko Briggs
 * @version 1.0 
 * @since April 2020 
 */
package main;

import controller.InputController;
import controller.PuzzleController;
import controller.RandomController;
import model.Cube;
import model.Puzzle;

/**
 * 
 */
public class App {
    public static void main(final String[] args) {
        // generatePermutations();
        // generateSolutions();
        // InputController.inputToPuzzle();
        // test();
        // RandomController.testRandomCubes(10);
        Puzzle.generate();
        // RandomController.testRandomCubes(10);
        Puzzle puzzle = InputController.inputToPuzzle();
        PuzzleController.arrangePuzzle(puzzle);

    }

    public static void test() {

        Cube cube;
        for (int i = 0; i < 10; i++) {
            cube = RandomController.getRandomCube();
            System.out.println(cube);
            System.out.println(cube.toStandardOrientation());
            System.out.println("");
        }

    }

}
