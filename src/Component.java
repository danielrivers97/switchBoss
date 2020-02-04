import processing.core.PApplet;

import java.util.ArrayList;

public class Component {

    public static final int UNIT = 20;

    protected SwitchBoss sketch;

    public int id; // unique identifier per component for use in wire connections

    private int x, y; // coordinates of top left of component (in grid units)
    private int width, height; // dimensions of component for click boxes, etc (in grid units)
    private int inX, inY, outX, outY; // coordinates of wire connections (in grid units)

    // DO I NEED THIS?????
    private ArrayList<Component> inComps, outComps; // store actual objects or ID?

    private int orientation; // 0, 1, 2, 3 for N, E, S, W pointing
    private String name; // name not specific to component - just a label

//    Default constructor blech dumb java stuff
//    public Component() {
//    }

    public Component(SwitchBoss sketch, int id, int x, int y, String name, int orientation) {
        //this.inComps = new ArrayList<>();
        this.outComps = new ArrayList<>();
        this.sketch = sketch;
        this.id = id;
        this.x = x;
        this.y = y;
        this.name = name;
        this.orientation = orientation;
    }

    public void render(float scale, int panX, int panY) {
        int x = calcPos(getX(), scale, panX);
        int y = calcPos(getY(), scale, panY);
        sketch.textSize(14 * scale);
        sketch.fill(0x0);
        sketch.text(name, x, y + scale * (UNIT + getHeight() * UNIT));
    }

    public void render_wire() {
        int fromX = getOutX();  // coordinates on grid
        int fromY = getOutY();
        int toX, toY, tempX, tempY;

        for (Component c : outComps) {
            toX = c.getInX();
            toY = c.getInY();

            tempX = fromX;
            tempY = fromY;

            while (tempY != toY) {
                if (tempY < toY) {
                    if (!sketch.isOnAnyComponent(tempX, tempY + 1)) {
                        tempY++;
                    } else {
                        while (sketch.isOnAnyComponent(tempX, tempY + 1)) {
                            tempX++;
                        }
                        drawLine(fromX, fromY, tempX, tempY);
                        fromX = tempX;
                        fromY = tempY;
                    }
                } else {
                    if (!sketch.isOnAnyComponent(tempX, tempY - 1)) {
                        tempY--;
                    } else {
                        while (sketch.isOnAnyComponent(tempX, tempY - 1)) {
                            tempX--;
                        }
                        drawLine(fromX, fromY, tempX, tempY);
                        fromX = tempX;
                        fromY = tempY;
                    }
                }
                drawLine(fromX, fromY, tempX, tempY);
                fromX = tempX;
                fromY = tempY;
            }
            while (tempX != toX) {
                System.out.println(String.format("temp: %d %d", tempX, tempY));
                if (tempX < toX) {
                    if (!sketch.isOnAnyComponent(tempX + 1, tempY)) {
                        tempX++;
                    } else {
                        while (sketch.isOnAnyComponent(tempX + 1, tempY)) {
                            tempY++;
                        }
                        drawLine(fromX, fromY, tempX, tempY);
                        fromX = tempX;
                        fromY = tempY;
                    }
                } else {
                    if (!sketch.isOnAnyComponent(tempX - 1, tempY)) {
                        tempX--;
                    } else {
                        while (sketch.isOnAnyComponent(tempX - 1, tempY)) {
                            tempY--;
                        }
                        drawLine(fromX, fromY, tempX, tempY);
                        fromX = tempX;
                        fromY = tempY;
                    }

                }
                drawLine(fromX, fromY, tempX, tempY);
                fromX = tempX;
                fromY = tempY;
            }
        }
    }

    public void drawLine(int fromX, int fromY, int toX, int toY) {
        float scale = sketch.scale;
        int panX = sketch.panX, panY = sketch.panY;

        int x1 = calcPos(fromX, scale, panX);
        int x2 = calcPos(toX, scale, panX);
        int y1 = calcPos(fromY, scale, panY);
        int y2 = calcPos(toY, scale, panY);

        sketch.strokeWeight(scale * 3);
        sketch.stroke(255, 0, 0);
        sketch.line(x1, y1, x2, y2);
    }

    public int calcPos(int coord, float scale, int pan) {
        return (int) (UNIT * scale * coord) + pan;
    }

    public void update() {

    }

    // Returns true if given (x,y) coord is within component boundaries
    // False otherwise
    public boolean isOnComponent(int x, int y) {
        return x > getX() && x < getX() + getWidth() && y > getY() && y < getY() + getHeight();
    }

    public void addInComp(Component c) {
        this.inComps.add(c);
    }

    public void addOutComp(Component c) {
        this.outComps.add(c);
    }

    public SwitchBoss getSketch() {
        return this.sketch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getInX() {
        return inX;
    }

    public void setInX(int inX) {
        this.inX = inX;
    }

    public int getInY() {
        return inY;
    }

    public void setInY(int inY) {
        this.inY = inY;
    }

    public int getOutX() {
        return outX;
    }

    public void setOutX(int outX) {
        this.outX = outX;
    }

    public int getOutY() {
        return outY;
    }

    public void setOutY(int outY) {
        this.outY = outY;
    }

    public int getOrientation() {
        return this.orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
