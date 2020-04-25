package com.marikokb.exercise;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;

/**
 * Creates and prints some number of 8x8 matrices that is randomly filled with
 * 1s and 0s, but where the number of 1s is always between 15 and 25
 */
public class MatrixExercise {
    private static final int NUM_OF_MATRIX_ELEMENTS = 64;
    private static final int MATRIX_ORDER = 8;
    private static final int PRINT_QUANTITY = 5;

    public static void main(final String[] args) throws FileNotFoundException {

        int[][] matrix = new int[MATRIX_ORDER][MATRIX_ORDER];

        // print output to file for ease of copying
        // comment out to print to stdout
        PrintStream fileOut = new PrintStream("./out.txt");
        System.setOut(fileOut);

        // print some number of 8x8 matrices
        for (int i = 0; i < PRINT_QUANTITY; i++) {
            matrix = (int[][]) generateFilledMatrix()[0];
            // System.out.println("Matrix #" + (i + 1) + ", " + (int)
            // generateFilledMatrix()[1] + "1s");
            // printMatrix(matrix);
            // System.out.println();

            LaTeXMatrix.printLaTeX(matrix);
            System.out.println();
        }

    }

    public static Object[] generateFilledMatrix() {
        final int numOfOnes = generateNumOfOnes();
        final int[] arr = generateArray(numOfOnes);
        final int[][] matrix = generateMatrix(shuffle(arr));

        final Object[] objArr = { matrix, numOfOnes };

        return objArr;
    }

    /**
     * Transforms an array of random 1s and 0s into a 2d matrix.
     */
    public static int[][] generateMatrix(final int[] arr) {
        final int[][] matrix = new int[MATRIX_ORDER][MATRIX_ORDER];
        int n = 0;
        for (int i = 0; i < MATRIX_ORDER; i++) {
            for (int j = 0; j < MATRIX_ORDER; j++) {
                matrix[i][j] = arr[n++];
            }
        }
        return matrix;
    }

    /**
     * 
     * @param int numOfOnes the number of ones we want our matrix to contain
     * @return int[] arr a 1d array of randomly arranged 1s and 0s
     */
    public static int[] generateArray(final int numOfOnes) {
        final int[] arr = new int[NUM_OF_MATRIX_ELEMENTS];
        for (int i = 0; i < numOfOnes; i++) {
            arr[i] = 1;
        }
        return shuffle(arr);
    }

    /**
     * Fisher-Yates shuffle of array. Implementation from geeksforgeeks.org.
     * 
     * @param int[] arr array to be shuffled
     * @return int[] arr shuffled array
     */
    public static int[] shuffle(final int[] arr) {
        final Random r = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            final int j = r.nextInt(i + 1);
            final int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return arr;
    }

    /**
     * Chooses a random number between [15,25] to be the number of 1s
     * 
     * @return int number of ones
     */
    public static int generateNumOfOnes() {
        final Random r = new Random();
        return r.nextInt(11) + 15;
    }

    /**
     * 
     * @param matrix the matrix to be printed
     */
    public static void printMatrix(final int[][] matrix) {
        for (int i = 0; i < MATRIX_ORDER; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
}
