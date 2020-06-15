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
import model.Puzzle;

public class App {
    public static void main(final String[] args) {
        Puzzle.generate();
        Puzzle puzzle = InputController.inputToPuzzle();
        PuzzleController.arrangePuzzle(puzzle);

    }
}
