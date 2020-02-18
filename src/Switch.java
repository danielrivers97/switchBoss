public class Switch extends Component {

    private boolean open;

    public Switch(SwitchBoss sketch, int id, Coord loc, String name, int orientation, int normalstate) {
        super(sketch, id, loc, name, orientation, normalstate);

        switch (getOrientation()) {
            case 0: // N
                setHeight(3);
                setWidth(2);
                setIn(new Coord(getX() + 1, getY() + 3));
                setOut(new Coord(getX() + 1, getY()));
                break;
            case 1: // E
                setHeight(2);
                setWidth(3);
                setIn(new Coord(getX(), getY() + 1));
                setOut(new Coord(getX() + 3, getY() + 1));
                break;
            case 2: // S
                setHeight(3);
                setWidth(2);
                setIn(new Coord(getX() + 1, getY()));
                setOut(new Coord(getX() + 1, getY() + 3));
                break;
            case 3: // W
                setHeight(2);
                setWidth(3);
                setIn(new Coord(getX() + 3, getY() + 1));
                setOut(new Coord(getX(), getY() + 1));
                break;
            default:
                System.exit(-1); // error checks later (SHOULD NEVER HAPPEN)
        }
    }

    public void render(float scale, float panX, float panY) {
        int unit = (int) (UNIT * scale); // grid unit with scale taken into account
        int x = calcPos(getX(), scale, panX);
        int y = calcPos(getY(), scale, panY);

        super.render(scale, panX, panY);

        sketch.stroke(0);
        sketch.strokeWeight(3 * scale);

        if (getOrientation() == 0 || getOrientation() == 2) {
            // N/S facing
            if (this.getCurrentState() != this.getNormalState()) {
                if (this.getNormalState() == 0) {
                    sketch.fill(255);
                    sketch.stroke(0, 255, 0);
                    sketch.circle(x + unit, y + 3 * unit / 2, 20 * scale);
                } else if (this.getNormalState() == 1) {
                    sketch.stroke(255, 0, 0);
                    sketch.line(x, y + unit, x + 2 * unit, y + 2 * unit);
                    sketch.line(x, y + 2 * unit, x + 2 * unit, y + unit);
                }
            }
            sketch.stroke(0);
            sketch.line(x + unit, y, x + unit, y + unit); //top vertical line
            sketch.line(x + unit, y + 2 * unit, x + unit, y + 3 * unit); //lower vertical line
            sketch.line(x, y + unit, x + 2 * unit, y + unit); //top horizontal line
            sketch.line(x, y + 2 * unit, x + 2 * unit, y + 2 * unit); //lower horizontal line
        } else {
            // E / W facing
            if (this.getCurrentState() != this.getNormalState()) {
                if (this.getNormalState() == 0) {
                    sketch.fill(255);
                    sketch.stroke(0, 255, 0);
                    sketch.circle(x + 3 * unit / 2, y + unit, 20 * scale);
                } else if (this.getNormalState() == 1) {
                    sketch.stroke(255, 0, 0);
                    sketch.line(x + unit, y, x + 2 * unit, y + 2 * unit);
                    sketch.line(x + 2 * unit, y, x + unit, y + 2 * unit);
                }
            }
            sketch.stroke(0);
            sketch.line(x, y + unit, x + unit, y + unit); //left horizontal line
            sketch.line(x + 2 * unit, y + unit, x + 3 * unit, y + unit); //right horizontal line
            sketch.line(x + unit, y, x + unit, y + 2 * unit); //left vertical line
            sketch.line(x + 2 * unit, y, x + 2 * unit, y + 2 * unit); //right vertical line
        }
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}