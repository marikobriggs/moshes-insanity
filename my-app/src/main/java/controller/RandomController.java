/**
 * Deals with generating,interacting with, and calculating probabilities of random cubes and their associated behavior. 
 * 
 * @author Mariko Briggs
 * @version 1.0 
 * @since April 2020 
 */
package controller;

import java.util.Random;

import model.Color;
import model.Cube;
import model.Puzzle;

/**
 * Generates random cubes.
 */
public class RandomController {

    public static Puzzle getRandomPuzzle() {
        return new Puzzle(getRandomCube(), getRandomCube(), getRandomCube(), getRandomCube(), getRandomCube(),
                getRandomCube(), getRandomCube(), getRandomCube());
    }

    /**
     * Fisher-Yates shuffle of array. Implementation from geeksforgeeks.org.
     * 
     * @param Cube[] arr array to be shuffled
     * @return Cube[] arr shuffled array
     */
    public static Cube getRandomCube() {
        Cube cube = new Cube();
        Color[] arr = cube.getArray();
        final Random r = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            final int j = r.nextInt(i + 1);
            final Color temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return new Cube(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]);
    }

    public static void testRandomCubes(int sampleSize) {
        int numOfSolvable = 0;
        double rateOfSolvability = 0.0;
        for (int i = 0; i < sampleSize; i++) {
            if (getRandomPuzzle().isSolvable()) {
                numOfSolvable++;
            }
        }
        rateOfSolvability = numOfSolvable / sampleSize;
        System.out.println("We tested " + sampleSize + " number of random cubes.");
        System.out.println("Out of these, " + (sampleSize - numOfSolvable) + " were solvable.");
        System.out.println("Thus, approximately " + rateOfSolvability + "% were solvable.");
    }
}