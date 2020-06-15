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

    public static final int NUM_OF_CORNERS = 8;
    public static final int NUM_OF_FACES = 6;
    public static final int NUM_OF_COLORS_IN_CORNER = 3;

    Color front, right, back, left, top, bottom;
    public Color[] visibleCorner;

    public Cube() {
        this.front = Color.RED;
        this.right = Color.ORANGE;
        this.back = Color.GREEN;
        this.left = Color.BLUE;
        this.top = Color.PURPLE;
        this.bottom = Color.WHITE;
        visibleCorner = this.getCorner(1);
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

    public Cube(Color[] colorArray) {
        this.front = colorArray[0];
        this.right = colorArray[1];
        this.back = colorArray[2];
        this.left = colorArray[3];
        this.top = colorArray[4];
        this.bottom = colorArray[5];
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

    public Color[] getVisibleCorner() {
        return this.visibleCorner;
    }

    public void setVisibleCorner(Color[] visibleCorner) {
        this.visibleCorner = visibleCorner;
    }

    /**
     * 
     * @return
     */
    public Color[] getArray() {
        return new Color[] { this.front, this.right, this.back, this.left, this.top, this.bottom };
    }

    public Color[][] getCorners() {
        Color[][] corners = new Color[Puzzle.NUM_OF_CUBES][NUM_OF_COLORS_IN_CORNER];

        corners[0][0] = this.getLeft();
        corners[0][1] = this.getBack();
        corners[0][2] = this.getTop();

        corners[1][0] = this.getRight();
        corners[1][1] = this.getBack();
        corners[1][2] = this.getTop();

        corners[2][0] = this.getRight();
        corners[2][1] = this.getFront();
        corners[2][2] = this.getTop();

        corners[3][0] = this.getLeft();
        corners[3][1] = this.getFront();
        corners[3][2] = this.getTop();

        corners[4][0] = this.getLeft();
        corners[4][1] = this.getBack();
        corners[4][2] = this.getBottom();

        corners[5][0] = this.getRight();
        corners[5][1] = this.getBack();
        corners[5][2] = this.getBottom();

        corners[6][0] = this.getRight();
        corners[6][1] = this.getFront();
        corners[6][2] = this.getBottom();

        corners[7][0] = this.getLeft();
        corners[7][1] = this.getFront();
        corners[7][2] = this.getBottom();
        return corners;
    }

    /**
     * 
     * @param cornerPosition
     * @return
     */
    public Color[] getCorner(int cornerPosition) {
        Color[] corner = new Color[NUM_OF_COLORS_IN_CORNER];

        // in order of [left/right, front/back, top/bottom]
        // see the pattern?
        switch (cornerPosition) {
            case 1:
                corner[0] = this.getLeft();
                corner[1] = this.getBack();
                corner[2] = this.getTop();
                break;
            case 2:
                corner[0] = this.getRight();
                corner[1] = this.getBack();
                corner[2] = this.getTop();
                break;
            case 3:
                corner[0] = this.getRight();
                corner[1] = this.getFront();
                corner[2] = this.getTop();
                break;
            case 4:
                corner[0] = this.getLeft();
                corner[1] = this.getFront();
                corner[2] = this.getTop();
                break;
            case 5:
                corner[0] = this.getLeft();
                corner[1] = this.getBack();
                corner[2] = this.getBottom();
                break;
            case 6:
                corner[0] = this.getRight();
                corner[1] = this.getBack();
                corner[2] = this.getBottom();
                break;
            case 7:
                corner[0] = this.getRight();
                corner[1] = this.getFront();
                corner[2] = this.getBottom();
                break;
            case 8:
                corner[0] = this.getLeft();
                corner[1] = this.getFront();
                corner[2] = this.getBottom();
                break;
        }

        return corner;
    }

    /**
     * Rotates a cube so that the Red face is facing the front and orange is to the
     * right of red. If orange is opposite of red, then green is to the right of
     * red. .
     * 
     * @param cube
     * @return the same cube with RED facing front.
     */
    public Cube toStandardOrientation() {
        Color stdFront = Color.RED;
        Color stdRight1 = Color.ORANGE;
        Color stdRight2 = Color.GREEN;
        Cube standardCube = new Cube();
        // how to rotate each cube to get the given color to the FRONT
        if (this.getFront() == stdFront) {
            standardCube = this;
        } else if (this.getRight() == stdFront) { // RBLF --> FRBL
            standardCube = this.rotateFrontToLeft();
        } else if (this.getBack() == stdFront) { // BLFR --> FRBL
            standardCube = this.rotateFrontToLeft().rotateFrontToLeft();
        } else if (this.getLeft() == stdFront) { // LFRB --> FRBL
            standardCube = this.rotateFrontToRight();
        } else if (this.getTop() == stdFront) { // --> FRBLTBo
            standardCube = this.rotateFrontToBottom();
        } else if (this.getBottom() == stdFront) { // -->
            standardCube = this.rotateFrontToTop();
        }

        // finished with red, moving green/orange
        // if orange is opposite of red, move green to right
        if (standardCube.getBack() == stdRight1) { // orange is opposite red, move green to right
            if (standardCube.getRight() == stdRight2) {
                return standardCube;
            } else if (standardCube.getLeft() == stdRight2) {
                standardCube = standardCube.rotateTopToRight().rotateTopToRight();

            } else if (standardCube.getTop() == stdRight2) {
                standardCube = standardCube.rotateTopToRight();

            } else if (standardCube.getBottom() == stdRight2) {
                standardCube = standardCube.rotateTopToLeft();

            }
        } else { // orange is not opp of red. move orange to right
            if (standardCube.getRight() == stdRight1) {
                return standardCube;
            } else if (standardCube.getLeft() == stdRight1) {
                standardCube = standardCube.rotateTopToRight().rotateTopToRight();
            } else if (standardCube.getTop() == stdRight1) {
                standardCube = standardCube.rotateTopToRight();
            } else if (standardCube.getBottom() == stdRight1) {
                standardCube = standardCube.rotateTopToLeft();
            }
        }
        return standardCube;
    }

    /**
     * Rotates the cube to the left such that the front face becomes the left face.
     * 
     * @param cube the cube to be rotated to the left
     * @return the given cube, rotated once to the left
     */
    public Cube rotateFrontToRight() {
        return this.rotateFrontToLeft().rotateFrontToLeft().rotateFrontToLeft();
    }

    /**
     * Rotates the given cube to the right such that the front face becomes the
     * right face.
     * 
     * @return the given cube, rotated once to the right
     */
    public Cube rotateFrontToLeft() {
        return new Cube(this.getRight(), this.getBack(), this.getLeft(), this.getFront(), this.getTop(),
                this.getBottom());
    }

    /**
     * Rotates the cube upwards such that the front face becomes the top face.
     * 
     * @return the given cube, rotated once upwards
     */
    public Cube rotateFrontToBottom() {
        return new Cube(this.getTop(), this.getRight(), this.getBottom(), this.getLeft(), this.getBack(),
                this.getFront());
    }

    /**
     * Rotates the cube downwards such that the front face becomes the bottom face.
     * 
     * @return the given cube, rotated once downwards
     */
    public Cube rotateFrontToTop() {
        return this.rotateFrontToBottom().rotateFrontToBottom().rotateFrontToBottom();
    }

    public Cube rotateTopToLeft() {
        return new Cube(this.getFront(), this.getBottom(), this.getBack(), this.getTop(), this.getRight(),
                this.getLeft());

    }

    public Cube rotateTopToRight() {
        return this.rotateTopToLeft().rotateTopToLeft().rotateTopToLeft();
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

    private static int temp = 0;

    /**
     * 
     * @param c1
     * @param c2
     * @return
     */
    protected static Boolean isPermutation(Color[] c1, Color[] c2) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        String cubeColors1 = "";
        String cubeColors2 = "";

        // color array to string builder
        for (int i = 0; i < c1.length; i++) {
            sb1.append(c1[i].name());
            sb2.append(c2[i].name());
        }

        sb1 = sb1.append(sb1.toString());

        // if the first string of duped colors contains the second string, then we know
        // that they are the same permutation
        cubeColors1 = sb1.toString();
        cubeColors2 = sb2.toString();

        temp++;
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
        Color[] a1 = new Color[NUM_OF_FACES];
        Color[] a2 = new Color[NUM_OF_FACES];

        Cube c1 = this;
        Cube c2;
        c1 = c1.toStandardOrientation();
        a1 = c1.getArray();

        if (this == obj)
            return true;

        if (!(obj instanceof Cube))
            return false;

        c2 = (Cube) obj;
        c2 = c2.toStandardOrientation();
        a2 = c2.getArray();

        for (int i = 0; i < NUM_OF_FACES; i++) {
            if (a1[i] != a2[i]) {
                return false;
            }
        }
        return true;
    }
}