import processing.core.PApplet;

public class Component {

    protected PApplet sketch;

    private int x; // top left of component
    private int y;
    private int orientation; // 0, 1, 2, 3 for N, E, S, W pointing
    private int normalstate; // 0: normally closed, 1: normally open
    private int currentstate; // 0: normally closed, 1: normally open
    private String name; // name not specific to component - just a label

//    // Default constructor blech dumb java stuff
//    public Component() {
//    }

    public Component(PApplet sketch, int x, int y, String name, int orientation, int normalstate) {
        this.sketch = sketch;
        this.x = x;
        this.y = y;
        this.name = name;
        this.orientation = orientation;
        this.normalstate = normalstate;
        this.currentstate = normalstate;
    }

//    public void step() {
//
//    }

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

    public String getName() {
        return this.name;
    }

    public int getOrientation() {
        return this.orientation;
    }

    public int getNormalstate() { return this.normalstate; }

    public void setCurrentstate(int x) { this.currentstate = x; }

    public int getCurrentstate() { return this.currentstate; }
}