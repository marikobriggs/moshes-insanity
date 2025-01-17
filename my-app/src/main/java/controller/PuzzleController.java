/**
 * Describes how a user interacts with the Puzzle, including placing Cubes in the Puzzle. 
 * 
 * @author Mariko Briggs
 * @version 1.0 
 * @since April 2020 
 */
package controller;

import java.util.Arrays;

import java.util.Scanner;

import model.Cube;
import model.Puzzle;

public class PuzzleController {
    private static final String[] orderArray = { "first", "second", "third", "fourth", "fifth", "sixth", "seventh",
            "eighth" };
    final Puzzle puzzle = RandomController.getRandomPuzzle(); // set to some random puzzle, as puzzle cubes cannot be
                                                              // set one by one (we will just replace)
    // choose cube

    public static void arrangePuzzle(Puzzle puzzle) {
        Scanner sc = new Scanner(System.in);
        int cubeChoice;

        Cube tempCube = new Cube();
        Cube[] temp = new Cube[8];

        printPuzzle(puzzle);
        printPuzzlePlacementText(); // ascii guide

        for (int i = 0; i < Puzzle.NUM_OF_CUBES; i++) {
            System.out.println("What cube would you like to place " + orderArray[i] + "?");
            System.out.print("Cube for position " + (i + 1) + " in puzzle: ");
            cubeChoice = sc.nextInt() - 1;
            System.out.println(puzzle.getCubes()[cubeChoice]);
            tempCube = puzzle.getCubes()[cubeChoice];
            // set temp puzzle at corner i to the cube from
            // our
            // // current puzzle
            temp[i] = tempCube;
        }

        puzzle = new Puzzle(temp);

        printPuzzle(puzzle);

        int choice;
        System.out.println("Would you like to rotate any of the cubes?");

        System.out.println("Please type '1' to rotate. Please type '2' to finish. ");
        choice = sc.nextInt();
        System.out.println(choice);
        // while input is invalid
        while (!(choice == 1 || choice == 2)) {
            System.out.println("Invalid input. Please try again.");
            System.out.println("Please type '1' to rotate. Please type '2' to finish. ");
            choice = sc.nextInt();
            System.out.println(choice);
        }

        // rotate corner
        while (choice == 1) {
            int rotationChoice;
            System.out.println("Here are the possible rotations.");
            System.out.println("1. Rotate front face to top");
            System.out.println("2. Rotate front face to bottom");
            System.out.println("3. Rotate front face to left");
            System.out.println("4. Rotate front face to right");
            System.out.println("5. Rotate top face to left");
            System.out.println("6. Rotate top face to right");

            System.out.println("Enter the cube and the rotation you want to make.");
            System.out.print("Cube: ");
            cubeChoice = sc.nextInt();
            System.out.print("Rotation: ");
            rotationChoice = sc.nextInt();

            rotateCorner(puzzle, cubeChoice - 1, rotationChoice);
            printPuzzle(puzzle);

            System.out.println("Would you like to make another rotation? (1) yes or (2) no.");
            choice = sc.nextInt();
        }

        if (choice != 1) {
            String solvable;
            if (puzzle.isSolvable()) {
                solvable = "solvable.";
            } else {
                solvable = "not solvable.";
            }

            System.out.println("Your puzzle is " + solvable);

            printPuzzle(puzzle);
        }
    }

    private static void printPuzzle(Puzzle puzzle) {
        System.out.println("Here is your current puzzle.");
        System.out.println(puzzle);
    }

    private static void rotateCorner(Puzzle puzzle, int cubeIndex, int rotation) {
        Cube[] puzzleCubes = puzzle.getCubes();
        Cube cube = puzzleCubes[cubeIndex];
        switch (rotation) {
            case 1: // rotate front to top
                puzzle.setCube(cubeIndex, cube.rotateFrontToTop());
            case 2: // front to bottom
                puzzle.setCube(cubeIndex, cube.rotateFrontToBottom());
            case 3: // front to left
                puzzle.setCube(cubeIndex, cube.rotateFrontToLeft());
            case 4: // front to right
                puzzle.setCube(cubeIndex, cube.rotateFrontToRight());
            case 5: // top to left
                puzzle.setCube(cubeIndex, cube.rotateTopToLeft());
            case 6: // top to right
                puzzle.setCube(cubeIndex, cube.rotateTopToRight());
        }

    }

    public static void chooseCubeText() {
        System.out.println("What cube would you like to choose?");
        System.out.println("Please enter a digit 1 through 6.");
    }

    public static void chooseCornersText() {
        System.out.println("What cube corner would you like to choose?");
        System.out.println("Note: these colors will be the visible colors in the final puzzle.");
        System.out.println("Please enter a digit 1 through 8.");
    }

    public static void printAllCorners(Cube cube) {
        System.out.println("Here are the corners of your cube.");
        for (int i = 0; i < Cube.NUM_OF_CORNERS; i++) {
            System.out.print("Corner " + i + ": ");
            System.out.println(Arrays.deepToString(cube.getCorner(i)));
        }
    }

    public static void printPuzzleIntstructions() {
        System.out.println("Begin by choosing a cube to place in the puzzle.");
        System.out.println("Then, choose the corner of the cube you want to place in the puzzle.");
        System.out.println("Choose where in the puzzle you want that corner to be displayed.");
        System.out.println("Finally, rotate the corner before placing it.");
    }

    public static void printPuzzlePlacementText() {
        System.out.println("Choose where you want your cubes to go.");
        System.out.println(
                "The number of the position in the puzzle where you want to place your corner corresponds to the following image.");
        System.out.println("      1        2 ");
        System.out.println("     .+------+");
        System.out.println("   .' |    .'|");
        System.out.println("4 +---+--+'3 |");
        System.out.println("  |   |  |   |");
        System.out.println("  | 5.+--+---+ 6");
        System.out.println("  |.'    | .'");
        System.out.println("8 +------+'7");

    }

}