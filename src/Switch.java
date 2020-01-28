import processing.core.PApplet;

public class Switch extends Component {

    private boolean open;

    public Switch(PApplet sketch, int x, int y, String name, int orientation) {
        super(sketch, x, y, name, orientation);
        open = true;
    }

    public void render(float scale, int panX, int panY) {

        int unit = (int) (20 * scale);
        int x = (int) (scale * (getX() + panX));
        int y = (int) (scale * (getY() + panY));

        super.render(scale, panX, panY);

        sketch.strokeWeight(3 * scale);
        if (getOrientation() == 0 || getOrientation() == 2) { // N/S facing
            sketch.line(x + unit, y, x + unit, y + unit);
            sketch.line(x + unit, y + 2 * unit, x + unit, y + 3 * unit);
            sketch.line(x, y + unit, x + 2 * unit, y + unit);
            sketch.line(x, y + 2 * unit, x + 2 * unit, y + 2 * unit);
        } else { // E / W facing
            sketch.line(x, y + unit, x + unit, y + unit);
            sketch.line(x + 2 * unit, y + unit, x + 3 * unit, y + unit);
            sketch.line(x + unit, y, x + unit, y + 2 * unit);
            sketch.line(x + 2 * unit, y, x + 2 * unit, y + 2 * unit);
        }
    }
}
