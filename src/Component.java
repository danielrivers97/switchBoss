import processing.core.PApplet;

import java.util.ArrayList;

public class Component {

    public static final int UNIT = 20;

    protected SwitchBoss sketch;

    public int id; // unique identifier per component for use in wire connections

    private int x, y; // coordinates of top left of component (with 1.0 scale and no panning)
    private int width, height; // dimensions of component for click boxes, etc
    private int inX, inY, outX, outY; // coordinates of wire connections

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
        int x = (int) (scale * (getX() + panX));
        int y = (int) (scale * (getY() + panY));

        sketch.textSize(16 * scale);
        sketch.fill(0x0);
        sketch.text(name, x, y + (scale * 80));
    }

    public void render_wire(float scale, int panX, int panY) {
        int fromX = (int) (scale * (getOutX() + panX));
        int fromY = (int) (scale * (getOutY() + panY));
        int toX, toY, tempX, tempY;

        sketch.strokeWeight(scale * 3);

        for (Component c : outComps) {
            toX = (int) (scale * (c.getInX() + panX));
            toY = (int) (scale * (c.getInY() + panY));

            tempX = fromX;
            tempY = fromY;

            System.out.println(String.format("to: %d %d, from: %d %d", toX, toY, fromX, fromY));
            if (getOrientation() == 0 || getOrientation() == 2) {
                while (tempY != toY) {
                    if (!sketch.isOnAnyComponent(tempX, tempY)) {
                        if (tempY < toY) {
                            tempY += UNIT * scale;

                        } else {
                            tempY -= UNIT * scale;
                        }
                    } else {
                        if (tempX < toX) {
                            tempX += UNIT * scale * 5;
                        } else {
                            tempX -= UNIT * scale * 5;
                        }
                    }
                    if (!sketch.isOnAnyComponent(tempX, tempY)) {
                        sketch.line(fromX, fromY, tempX, tempY);
                        fromX = tempX;
                        fromY = tempY;
                    }
                }
                while (tempX != toX) {
                    if (!sketch.isOnAnyComponent(tempX, tempY)) {
                        if (tempX < toX) {
                            tempX += UNIT * scale;
                        } else {
                            tempX -= UNIT * scale;
                        }
                    } else {
                        if (tempY < toY) {
                            tempY += UNIT * scale * 5;
                        } else {
                            tempY -= UNIT * scale * 5;
                        }
                    }
                    if (!sketch.isOnAnyComponent(tempX, tempY)) {
                        sketch.line(fromX, fromY, tempX, tempY);
                        fromX = tempX;
                        fromY = tempY;
                    }
                }
            } else {
                while (tempX != toX) {
                    if (!sketch.isOnAnyComponent(tempX, tempY)) {
                        if (tempX < toX) {
                            tempX += UNIT * scale;
                        } else {
                            tempX -= UNIT * scale;
                        }
                    } else {
                        if (tempY < toY) {
                            tempY += UNIT * scale * 5;
                        } else {
                            tempY -= UNIT * scale * 5;
                        }
                    }
                    if (!sketch.isOnAnyComponent(tempX, tempY)) {
                        sketch.line(fromX, fromY, tempX, tempY);
                        fromX = tempX;
                        fromY = tempY;
                    }
                }

                while (tempY != toY) {
                    if (!sketch.isOnAnyComponent(tempX, tempY)) {
                        if (tempY < toY) {
                            tempY += UNIT * scale;

                        } else {
                            tempY -= UNIT * scale;
                        }
                    } else {
                        if (tempX < toX) {
                            tempX += UNIT * scale * 5;
                        } else {
                            tempX -= UNIT * scale * 5;
                        }
                    }
                    if (!sketch.isOnAnyComponent(tempX, tempY)) {
                        sketch.line(fromX, fromY, tempX, tempY);
                        fromX = tempX;
                        fromY = tempY;
                    }
                }
            }
        }
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
