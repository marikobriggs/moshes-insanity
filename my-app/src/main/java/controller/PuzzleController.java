/**
 * Describes how a user interacts with the Puzzle, including placing Cubes in the Puzzle. 
 * 
 * @author Mariko Briggs
 * @version 1.0 
 * @since April 2020 
 */
package controller;

import model.Cube;

public class PuzzleController {
    // choose cube

    public void arrangePuzzle() {
        printPuzzleIntstructions();
    }

    public void chooseCube() {

    }
    // choose cube corner
    // choose place in puzzle
    // rotate corner

    /**
     * Given a cube, display the corners. Have user select a corner.
     * 
     * @param cube
     */
    public void chooseCorners(Cube cube) {
        cube.toStandardOrientation(); // ensure that red is facing towards us for consistency

    }

    public void printAllCorners(Cube cube) {

    }

    public void printPuzzleIntstructions() {
        System.out.println("Begin by choosing a cube to place in the puzzle.");
        System.out.println("Then, choose the corner of the cube you want to place in the puzzle.");
        System.out.println("Choose where in the puzzle you want that corner to be displayed.");
        System.out.println("Finally, rotate the corner before placing it.");
    }

}