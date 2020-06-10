/**
 * Describes the Cube and its associated operations, including rotations, 
 * 
 * @author Mariko Briggs
 * @version 1.0 
 * @since April 2020 
 */
package model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Cube {

    public static final int NUM_OF_FACES = 6;

    Color front, right, back, left, top, bottom;

    // topBackLeft, topBackRight, topFrontRight, topFrontLeft, bottomBackLeft,
    // bottomBackRight, bottomFrontRight, bottomFrontLeft

    public Cube() {
        this.front = Color.RED;
        this.right = Color.ORANGE;
        this.back = Color.GREEN;
        this.left = Color.BLUE;
        this.top = Color.PURPLE;
        this.bottom = Color.WHITE;
    }

    /**
     * 
     * @param front
     * @param right
     * @param back
     * @param left
     * @param top
     * @param bottom
     */
    public Cube(final Color front, final Color right, final Color back, final Color left, final Color top,
            final Color bottom) {
        this.front = front;
        this.right = right;
        this.back = back;
        this.left = left;
        this.top = top;
        this.bottom = bottom;
    }

    /**
     * @return the front
     */
    public Color getFront() {
        return front;
    }

    /**
     * @return the right
     */
    public Color getRight() {
        return right;
    }

    /**
     * @return the back
     */
    public Color getBack() {
        return back;
    }

    /**
     * @return the left
     */
    public Color getLeft() {
        return left;
    }

    /**
     * @return the top
     */
    public Color getTop() {
        return top;
    }

    /**
     * @return the bottom
     */
    public Color getBottom() {
        return bottom;
    }

    /**
     * 
     * @return
     */
    public Color[] getArray() {
        return new Color[] { this.front, this.right, this.back, this.left, this.top, this.bottom };
    }

    /**
     * Rotates a cube so that the Red face is facing the front.
     * 
     * @param cube
     * @return the same cube with RED facing front.
     */
    public Cube toStandardOrientation() {
        // the color we want to face the FRONT, always!
        Color stdColor = Color.RED;
        Cube standardCube = new Cube();

        // how to rotate each cube to get the given color to the FRONT
        if (this.getFront() == stdColor) {
            return this;
        } else if (this.getRight() == stdColor) { // RBLF --> FRBL
            standardCube = rotateLeft(this);

        } else if (this.getBack() == stdColor) { // BLFR --> FRBL
            standardCube = rotateRight(rotateRight(this));

        } else if (this.getLeft() == stdColor) { // LFRB --> FRBL
            standardCube = rotateRight(this);

        } else if (this.getTop() == stdColor) { // --> FRBLTBo
            standardCube = rotateDown(this);

        } else if (this.getBottom() == stdColor) { // -->
            standardCube = rotateUp(this);
        }
        return standardCube;
    }

    /**
     * Rotates the given cube to the left such that the front face becomes the left
     * face.
     * 
     * @param cube the cube to be rotated to the left
     * @return the given cube, rotated once to the left
     */
    public Cube rotateLeft(Cube cube) {
        // return new Cube(cube.getLeft(), cube.getFront(), cube.getRight(),
        // cube.getBack(), cube.getTop(),
        // cube.getBottom());
        return rotateRight(rotateRight(rotateRight(cube)));
    }

    /**
     * Rotates the given cube to the right such that the front face becomes the
     * right face.
     * 
     * @param cube the cube to be rotated to the right
     * @return the given cube, rotated once to the right
     */
    public Cube rotateRight(Cube cube) {
        return new Cube(cube.getRight(), cube.getBack(), cube.getLeft(), cube.getFront(), cube.getTop(),
                cube.getBottom());
    }

    /**
     * Rotates the given cube upwards such that the front face becomes the top face.
     * 
     * @param cube the cube to be rotated upwards
     * @return the given cube, rotated once upwards
     */
    public Cube rotateUp(Cube cube) {
        return new Cube(cube.getTop(), cube.getRight(), cube.getBottom(), cube.getLeft(), cube.getBack(),
                cube.getFront());
    }

    /**
     * Rotates the given cube downwards such that the front face becomes the bottom
     * face.
     * 
     * @param cube the cube to be rotated downwards
     * @return the given cube, rotated once downwards
     */
    public Cube rotateDown(Cube cube) {
        // return new Cube(cube.getBottom(), cube.getRight(), cube.getTop(),
        // cube.getLeft(), cube.getFront(),
        // cube.getBack());
        return rotateUp(rotateUp(rotateUp(cube)));
    }

    /**
     * Is this necessary? reasses
     *
     * @param cubes to be compared
     * @return
     */
    public Boolean isCyclic(Cube c1, Cube c2) {
        c1 = c1.toStandardOrientation();
        c2 = c2.toStandardOrientation();

        Color[] c1perm = Arrays.copyOfRange(c1.getArray(), 0, 3);
        Color[] c2perm = Arrays.copyOfRange(c2.getArray(), 0, 3);

        // check if the sets of the first 4 elements of the arrays are the same
        if (!(convertArrayToSet(c1perm).equals(convertArrayToSet(c2perm)))) {
            return false;
        } else {
            return isPermutation(c1perm, c2perm);
        }
    }

    /**
     * 
     * @param c1
     * @param c2
     * @return
     */
    private Boolean isPermutation(Color[] c1, Color[] c2) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        String cubeColors1 = "";
        String cubeColors2 = "";

        // color array to string builder
        for (int i = 0; i < c1.length; i++) {
            sb1.append(c1[i].name());
            sb2.append(c2[i].name());
        }

        // concats string to itself (abc --> abcabc)
        sb1 = sb1.append(sb1.toString());

        // if the first string of duped colors contains the second string, then we know
        // that they are the same permutation
        cubeColors1 = sb1.toString();
        cubeColors2 = sb2.toString();

        return cubeColors1.contains(cubeColors2);
    }

    /**
     * 
     * @param color
     * @return
     */
    private Set<Color> convertArrayToSet(Color[] color) {
        return new HashSet<>(Arrays.asList(color));
    }

    @Override
    public String toString() {
        return Arrays.deepToString(this.getArray());
    }

    @Override
    public boolean equals(Object obj) {

        Cube cube1 = (Cube) obj;
        // TODO

        return false;
    }
}
