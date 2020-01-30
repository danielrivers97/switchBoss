import processing.core.PApplet;

public class Component {

    public static final int UNIT = 20;

    protected PApplet sketch;

    private int x, y; // coordinates of top left of component (with 1.0 scale and no panning)
    private int width, height; // dimensions of component for click boxes, etc
    private int inX, inY, outX, outY; // coordinates of wire connections


    private int orientation; // 0, 1, 2, 3 for N, E, S, W pointing
    private String name; // name not specific to component - just a label

//    // Default constructor blech dumb java stuff
//    public Component() {
//    }

    public Component(PApplet sketch, int x, int y, String name, int orientation) {
        this.sketch = sketch;
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

    public void update() {

    }

    public PApplet getSketch() {
        return this.sketch;
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
