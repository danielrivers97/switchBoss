import processing.core.PApplet;

import java.util.ArrayList;

public class Component {

    public static final int UNIT = 20;
    public static final int N = 0, E = 1, S = 2, W = 3;

    protected SwitchBoss sketch;

    private int id; // unique identifier per component for use in wire connections

    private Coord loc; // coordinates of top left of component (in grid units)
    private int width, height; // dimensions of component for click boxes, etc (in grid units)
    private Coord in, out; // coordinates of wire connections (in grid units)

    // DO I NEED THIS?????
    private ArrayList<Component> outComps; // store actual objects or ID?

    private int orientation; // 0, 1, 2, 3 for N, E, S, W pointing
    private int normalstate; // 0: normally closed, 1: normally open
    private int currentstate; // 0: normally closed, 1: normally open
    private String name; // name not specific to component - just a label

//    Default constructor blech dumb java stuff
//    public Component() {
//    }

    public Component(SwitchBoss sketch, int id, Coord loc, String name, int orientation, int normalstate) {
        this.outComps = new ArrayList<>();
        this.sketch = sketch;
        this.id = id;
        this.loc = loc;
        this.name = name;
        this.orientation = orientation;
        this.normalstate = normalstate;
        this.currentstate = normalstate;
    }

    public void render(float scale, int panX, int panY) {
        int x = calcPos(getX(), scale, panX);
        int y = calcPos(getY(), scale, panY);
        sketch.textSize(14 * scale);
        sketch.fill(0x0);
        sketch.text(name, x, y + scale * (UNIT + getHeight() * UNIT));
    }

    public void render_wire() {
        Coord from = this.getOut();
        Coord to;
        Coord temp = new Coord(from.getX(), from.getY());
        boolean xThenY;

        for (Component c : outComps) {
            to = c.getIn();

            xThenY = c.getOrientation() % 2 != 0 ? getOrientation() % 2 == 0 : c.getOrientation() % 2 == 0;

            if (xThenY) {
                temp = runXWire(temp, to);
                temp = runYWire(temp, to);
            } else {
                temp = runYWire(temp, to);
                temp = runXWire(temp, to);
            }
        }
    }

    public Coord runXWire(Coord temp, Coord to) {
        System.out.println("x wire");
        int toX = to.getX();
        int fromX = temp.getX();
        int fromY = temp.getY();
        int tempX = fromX;
        int tempY = fromY;

        int offset = 0;

        while (tempX != toX) {
            if (tempX < toX) {
                if (!sketch.isOnAnyComponent(tempX + 1, tempY)) {
                    tempX++;
                } else {
                    while (sketch.isOnAnyComponent(tempX + 1, tempY)) {
                        tempY++;
                        offset++;
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
                        offset--;
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

        while (offset != 0) {
            if (offset < 0) {
                tempY++;
                offset++;
            } else {
                tempY--;
                offset--;
            }
        }
        drawLine(fromX, fromY, tempX, tempY);

        return new Coord(tempX, tempY);
    }

    public Coord runYWire(Coord temp, Coord to) {
        System.out.println("y wire");
        int toY = to.getY();
        int fromX = temp.getX();
        int fromY = temp.getY();
        int tempX = fromX;
        int tempY = fromY;

        int offset = 0;

        while (tempY != toY) {
            if (tempY < toY) {
                if (!sketch.isOnAnyComponent(tempX, tempY + 1)) {
                    tempY++;
                } else {
                    while (sketch.isOnAnyComponent(tempX, tempY + 1)) {
                        tempX++;
                        offset++;
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
                        offset--;
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

        while (offset != 0) {
            if (offset < 0) {
                tempX++;
                offset++;
            } else {
                tempX--;
                offset--;
            }
        }
        drawLine(fromX, fromY, tempX, tempY);

        return new Coord(tempX, tempY);
    }

    public void drawLine(int fromX, int fromY, int toX, int toY) {
        float scale = sketch.viewport.getScale();
        int panX = (int) sketch.viewport.getX(), panY = (int) sketch.viewport.getY();

        int x1 = calcPos(fromX, scale, panX);
        int x2 = calcPos(toX, scale, panX);
        int y1 = calcPos(fromY, scale, panY);
        int y2 = calcPos(toY, scale, panY);

        sketch.strokeWeight(scale * 3);
        sketch.stroke(255, 0, 0);
        sketch.line(x1, y1, x2, y2);
    }

    public int calcPos(int coord, float scale, int pan) {
        return (int) ((scale * (scale + coord + pan)) + (UNIT * coord * scale));
    }

    public void update() {

    }

    // Returns true if given (x,y) coord is within component boundaries
    // False otherwise
    public boolean isOnComponent(int x, int y) {
        return x > getX() && x < getX() + getWidth() && y > getY() && y < getY() + getHeight();
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

    public Coord getLoc() {
        return this.loc;
    }

    public void setLoc(Coord loc) {
        this.loc = loc;
    }

    public int getX() {
        return this.loc.getX();
    }

    public int getY() {
        return this.loc.getY();
    }

    public void setX(int x) {
        this.loc.setX(x);
    }

    public void setY(int y) {
        this.loc.setY(y);
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

    public Coord getIn() {
        return in;
    }

    public void setIn(Coord in) {
        this.in = in;
    }

    public Coord getOut() {
        return out;
    }

    public void setOut(Coord out) {
        this.out = out;
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

    public int getNormalstate() {
        return this.normalstate;
    }

    public void setCurrentstate(int x) {
        this.currentstate = x;
    }

    public int getCurrentstate() {
        return this.currentstate;
    }
}
