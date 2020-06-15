/**
 * Describes the Puzzle, which is comprised of eight cubes and has a number of operations associated with it. 
 * 
 * @author Mariko Briggs
 * @version 1.0 
 * @since April 2020 
 */
package model;

import java.util.ArrayList;

public class Puzzle {
    public static final int NUM_OF_CUBES = 8;
    int M = 8;
    int N = 8;

    private Cube[] cubes;
    private static Cube[] solutionCubes = {

    };

    public Puzzle(Cube c1, Cube c2, Cube c3, Cube c4, Cube c5, Cube c6, Cube c7, Cube c8) {
        this.cubes = new Cube[] { c1, c2, c3, c4, c5, c6, c7, c8 };
    }

    public Puzzle(Cube[] cubes) {
        this.cubes = cubes;
    }

    public Puzzle() {
    }

    public Boolean isSolvable() {
        Cube[] puzzle = this.getCubes();
        Color[][] solutionSet;
        boolean[][] matrix;
        for (int i = 0; i < solutionCubes.length; i++) {
            solutionSet = solutionCubes[i].getCorners();
            matrix = PuzzleSolutions.generateMatrix(solutionSet, puzzle);

            for (int b = 0; b < matrix.length; b++) {
                System.out.println("");
                for (int c = 0; c < matrix[b].length; c++) {
                    System.out.print(" " + matrix[b][c]);
                }
            }
            int maxBPM = maxBPM(matrix);
            System.out.println("\nMax number of matches out of 8: " + maxBPM);

            // if (maxBPM(matrix) == 8) {
            if (maxBPM == 8) {
                return true;
            }
        }
        return false;
    }

    // Bipartite matching code from Geeksforgeeks.com

    // A DFS based recursive function that
    // returns true if a matching for
    // vertex u is possible
    boolean bpm(boolean bpGraph[][], int u, boolean seen[], int matchR[]) {
        // Try every job one by one
        for (int v = 0; v < N; v++) {
            // If applicant u is interested
            // in job v and v is not visited
            if (bpGraph[u][v] && !seen[v]) {

                // Mark v as visited
                seen[v] = true;

                // If job 'v' is not assigned to
                // an applicant OR previously
                // assigned applicant for job v (which
                // is matchR[v]) has an alternate job available.
                // Since v is marked as visited in the
                // above line, matchR[v] in the following
                // recursive call will not get job 'v' again
                if (matchR[v] < 0 || bpm(bpGraph, matchR[v], seen, matchR)) {
                    matchR[v] = u;
                    return true;
                }
            }
        }
        return false;
    }

    // Returns maximum number
    // of matching from M to N
    int maxBPM(boolean bpGraph[][]) {
        // An array to keep track of the
        // applicants assigned to jobs.
        // The value of matchR[i] is the
        // applicant number assigned to job i,
        // the value -1 indicates nobody is assigned.
        int matchR[] = new int[N];

        // Initially all jobs are available
        for (int i = 0; i < N; ++i)
            matchR[i] = -1;

        // Count of jobs assigned to applicants
        int result = 0;
        for (int u = 0; u < M; u++) {
            // Mark all jobs as not seen
            // for next applicant.
            boolean seen[] = new boolean[N];
            for (int i = 0; i < N; ++i)
                seen[i] = false;

            // Find if the applicant 'u' can get a job
            if (bpm(bpGraph, u, seen, matchR))
                result++;
        }
        return result;
    }

    public Cube[] getCubes() {
        return cubes;
    }

    public void setCubes(Cube[] cubes) {
        this.cubes = cubes;
    }

    public void setCube(int index, Cube cube) {
        this.cubes[index] = cube;
    }

    @Override
    public String toString() {
        Cube[] arr = this.getCubes();
        StringBuilder puzzleString = new StringBuilder();
        for (int i = 0; i < Puzzle.NUM_OF_CUBES; i++) {
            puzzleString.append((i + 1) + ". " + arr[i].toString() + "\n");
        }
        return puzzleString.toString();
    }

    public static void generate() {
        Cube cube = new Cube();

        PuzzleSolutions.generatePermutations(cube.getArray());

        PuzzleSolutions.generateSolutions();

        solutionCubes = new Cube[30]; // PuzzleSolutions.solutions.length should be 30
        for (int i = 0; i < PuzzleSolutions.solutions.size(); i++) {
            solutionCubes[i] = new Cube(PuzzleSolutions.solutions.get(i));
        }
    }

    static class PuzzleSolutions {

        private static final int NUM_OF_CUBE_PERMUTATIONS = 720;
        public static ArrayList<Color[]> solutions = new ArrayList<Color[]>();

        public static ArrayList<Color[]> permutationsList = new ArrayList<Color[]>();
        private static Color[][] permutations = new Color[NUM_OF_CUBE_PERMUTATIONS][Cube.NUM_OF_FACES];

        public static boolean containsCorner(Color[] corner, Color[][] cubeCorners) {
            for (int i = 0; i < cubeCorners.length; i++) {
                if (Cube.isPermutation(cubeCorners[i], corner))
                    return true;
            }
            return false;
        }

        public static Color[][][] getPuzzleCorners(Cube[] cubes) {
            Color[][][] puzzleCorners = new Color[8][8][3];
            for (int i = 0; i < 8; i++) {
                puzzleCorners[i] = cubes[i].toStandardOrientation().getCorners();
            }

            return puzzleCorners;
        }

        public static boolean[][] generateMatrix(Color[][] solutionSet, Cube[] cubes) {
            boolean[][] matrix = new boolean[8][8];
            Color[][][] puzzleCorners = getPuzzleCorners(cubes);

            for (int i = 0; i < 8; i++) { // for each cube
                for (int j = 0; j < 8; j++) { // check each corner
                    for (int k = 0; k < solutionSet.length; k++) { // check if corner is in sol set
                        // containsCorner takes single corner and single cube's worth of corners
                        // iterate over entire solution set and check each solution corner with
                        // puzzleCorners
                        if (containsCorner(solutionSet[k], puzzleCorners[j])) {
                            matrix[j][k] = true;
                        }
                    }
                }
            }
            return matrix;
        }

        private static void generateSolutions() {

            for (int i = 0; i < permutationsList.size(); i++) {
                permutations[i] = permutationsList.get(i);

            }

            solutions.add(permutations[0]);

            for (int i = 0; i < permutations.length; i++) {
                boolean foundMatch = false;
                for (int j = 0; j < solutions.size(); j++) {
                    if (new Cube(permutations[i]).equals(new Cube(solutions.get(j)))) {
                        foundMatch = true;
                    }
                }
                if (!foundMatch) {

                    solutions.add(permutations[i]);
                }
            }

        }

        private static void generatePermutations(Color[] a) {
            permutationBootstrap(a.length, a);
        }

        private static void permutationBootstrap(int k, Color[] a) {
            if (k == 1) {
                permutationsList.add(a.clone());
            }
            for (int i = 0; i < k; i++) {
                permutationBootstrap(k - 1, a);
                if (k % 2 == 1) {
                    swap(a, 0, k - 1);
                } else {
                    swap(a, k - 1, i);
                }
            }
        }

        private static void swap(Color[] a, int i, int j) {
            Color temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }
}
