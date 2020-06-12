/**
 * Runs our app. 
 * 
 * @author Mariko Briggs
 * @version 1.0 
 * @since April 2020 
 */
package main;

import controller.InputController;
import controller.RandomController;
import model.Cube;

/**
 * 
 */
public class App {
    public static void main(String[] args) {
        InputController.inputToPuzzle();
        // test();

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
