package com.marikokb.exercise;

/**
 * Doesn't really need to be a separate class but :) 
 * Takes a matrix and returns to stdout the formatting for a LaTeX matrix
 * Requires amsmath package in LaTeX 
 */
/**
 * \begin{bmatrix} 1 & 2 & 3\\ a & b & c \end{bmatrix}
 */
public class LaTeXMatrix {

    /**
     * prints with square brackets.
     */
    public static void printLaTeX(int[][] matrix) {
        System.out.println("\\begin{displaymath}\n\\begin{bmatrix}");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (j == matrix[0].length - 1) {
                    System.out.println(matrix[i][j] + "\\\\");
                } else {
                    System.out.print(matrix[i][j] + " & ");
                }
            }
        }
        System.out.println("\\end{bmatrix}\n\\end{displaymath}");
    }

}