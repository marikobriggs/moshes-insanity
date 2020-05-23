package controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import model.Color;
import model.Cube;
import model.Puzzle;

/**
 * Takes in input either through command line or via a file. Outputs results
 * either to command line or to file.
 */
public class InputController {

    String[] COLORS = new String[] { "red", "orange", "green", "blue", "purple", "white", "r", "o", "g", "b", "p",
            "w" };

    /**
     * Combines everything! Takes input, creates puzzle
     */
    public static void inputToPuzzle() {
        printPromptMessage();
        String[] inputArray = takeStdInput();
        Puzzle puzzle = convertInputArrayToPuzzle(inputArray);
    }

    /**
     * Opens a scanner to accept user input for 8 cubes. Takes one face at a time.
     * 
     * @return a String[] where each element is one cube's worth of faces
     */
    public static String[] takeStdInput() {
        // contains all of the faces in order of acceptance
        String[] faces = new String[] { "front", "right", "back", "left", "top", "bottom" };

        final Scanner sc = new Scanner(System.in);

        // final array of colors for any given cube
        String[] inputArray = new String[Puzzle.NUM_OF_CUBES];
        // System.out.println(Arrays.toString(inputArray));
        String[] cubeArray = new String[Cube.NUM_OF_FACES];
        for (int i = 0; i < Puzzle.NUM_OF_CUBES; i++) {
            cubeArray = new String[Cube.NUM_OF_FACES];
            System.out.println("Enter cube #" + (i + 1));
            for (int j = 0; j < Cube.NUM_OF_FACES; j++) {
                System.out.print("Enter " + faces[j] + " face: ");
                cubeArray[j] = sc.nextLine();
                // if input is not a proper color of misspelled, prompt again and overwrite
                // array entry
                while (!isValidString(cubeArray[j]) || containsElement(cubeArray[j], cubeArray, j)) {
                    System.out.println("Invalid input, try again.");
                    System.out.print("Enter " + faces[j] + " face: ");
                    cubeArray[j] = sc.nextLine();
                }
                inputArray[i] = cubeArray.toString().substring(1, cubeArray.length - 2);
            }
        }

        sc.close();
        return inputArray;
    }

    /**
     * NOT FOR USE IN FINAL PRODUCT - TESTING ONLY. Opens a scanner to accept user
     * input for 8 cubes, one cube at a time.
     *
     * @return a String[] where each element is one cube's worth of faces
     */
    public static String[] takeStdInputCube() {
        final Scanner sc = new Scanner(System.in);
        // array of colors for any given cube
        String[] inputArray = new String[Puzzle.NUM_OF_CUBES];

        for (int i = 0; i < Puzzle.NUM_OF_CUBES; i++) {
            System.out.println("Enter cube #" + (i + 1));
            inputArray[i] = sc.nextLine();
        }

        sc.close();
        return inputArray;
    }

    /**
     * Takes an input string and converts it to a Cube object
     * 
     * @param input a string of 6 ordered colors, delimited with a space
     * @return a Cube with sides of user's colors
     */
    public static Cube convertInputStringToCube(String input) {
        String[] inputArr = input.split("[ ,]");
        Color[] faces = new Color[Cube.NUM_OF_FACES];

        for (int i = 0; i < Cube.NUM_OF_FACES; i++) {
            switch (inputArr[i].toLowerCase()) {
                case "red":
                case "r":
                    faces[i] = Color.RED;
                    break;
                case "orange":
                case "o":
                    faces[i] = Color.ORANGE;
                    break;
                case "green":
                case "g":
                    faces[i] = Color.GREEN;
                    break;
                case "blue":
                case "b":
                    faces[i] = Color.BLUE;
                    break;
                case "purple":
                case "p":
                    faces[i] = Color.PURPLE;
                    break;
                case "white":
                case "w":
                    faces[i] = Color.WHITE;
                    break;
            }
        }
        return new Cube(faces[0], faces[1], faces[2], faces[3], faces[4], faces[5]);
    }

    /**
     * Takes an array of string inputs, converts to cubes, converts to puzzle
     * 
     * @param inputArray
     * @return
     */
    public static Puzzle convertInputArrayToPuzzle(String[] inputArray) {
        Cube[] cubes = new Cube[Puzzle.NUM_OF_CUBES];
        // Cube temp = new Cube();

        for (int i = 0; i < Puzzle.NUM_OF_CUBES; i++) {
            // temp = convertInputStringToCube(inputArray[i]);
            // cubes[i] = temp;
            cubes[i] = convertInputStringToCube(inputArray[i]);
        }

        Puzzle puzzle = new Puzzle(cubes[0], cubes[1], cubes[2], cubes[3], cubes[4], cubes[5], cubes[6], cubes[7]);
        return puzzle;
    }

    /**
     * 
     */
    public static void printPromptMessage() {
        System.out.println("Please enter your 8 cubes.");
        System.out.println("Use the following format:");
        System.out.println("\t -Use the following 6 colors: red, orange, green, blue, purple, white");
        System.out.println(
                "\t -Enter them in order of front face, left face, back face, right face, top face, and bottom face");
        System.out.println("\t -Press 'Enter' after each face");
        System.out.println("Example: red, orange, green, blue, purple, white (then press enter)");
        System.out.println("----------------------------------");
    }

    /**
     * 
     * @param s the string we want to validate
     * @return true if the string is an acceptable color, false otherwise
     */
    public static boolean isValidString(String s) {
        s = s.toLowerCase();
        String[] colors = new String[] { "red", "orange", "green", "blue", "purple", "white", "r", "o", "g", "b", "p",
                "w" };
        Set<String> colorSet = new HashSet<>(Arrays.asList(colors));

        return colorSet.contains(s);
    }

    /**
     * 
     * @param s   the string we want to check for uniqueness
     * @param arr the array we want to check for set properties
     * @param itr the index of the array we want to check up to (only check elements
     *            that have been inserted)
     * @return
     */
    public static boolean containsElement(String s, String[] arr, int itr) {
        for (int i = 0; i < itr; i++) {
            if (s.charAt(0) == arr[i].charAt(0)) {
                return true;
            }
        }
        return false;

    }
}