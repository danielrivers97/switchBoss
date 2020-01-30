import processing.core.PApplet;

public class Switch extends Component {

    protected boolean open;

    public Switch(PApplet sketch, int x, int y, String name, int orientation) {
        super(sketch, x, y, name, orientation);
        open = true; // default - will change on click unless otherwise specified
        switch (getOrientation()) {
            case 0: // N
                setHeight(3 * UNIT);
                setWidth(2 * UNIT);
                setInX(x + UNIT);
                setOutX(x + UNIT);
                setInY(y + (3 * UNIT));
                setOutY(y);
                break;
            case 1: // E
                setHeight(2 * UNIT);
                setWidth(3 * UNIT);
                setInX(x);
                setOutX(x + (3 * UNIT));
                setInY(y + UNIT);
                setOutY(y + UNIT);
                break;
            case 2: // S
                setHeight(3 * UNIT);
                setWidth(2 * UNIT);
                setInX(x + UNIT);
                setOutX(x + UNIT);
                setInY(y);
                setOutY(y + (3 * UNIT));
                break;
            case 3: // W
                setHeight(2 * UNIT);
                setWidth(3 * UNIT);
                setInX(x + (3 * UNIT));
                setOutX(x);
                setInY(y + UNIT);
                setOutY(y + UNIT);
                break;
            default:
                System.exit(-1); // error checks later (SHOULD NEVER HAPPEN)
        }
    }

    public void render(float scale, int panX, int panY) {

        int unit = (int) (UNIT * scale);
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
