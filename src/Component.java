import processing.core.PApplet;

public class Component {

    protected PApplet sketch;

    private int x; // top left of component
    private int y;
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

    public String getName() {
        return this.name;
    }

    public int getOrientation() {
        return this.orientation;
    }
}
