import processing.core.PApplet;

public class Breaker extends Component{
    public Breaker(SwitchBoss sketch, int id, Coord loc, String name, int orientation, int normalstate) {
        super(sketch, id, loc, name, orientation, normalstate);
        switch(orientation) {
            case 0: //N
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

    public void render(float scale, int panX, int panY) {
        int unit = (int) (UNIT * scale);
        int x = calcPos(getX(), scale, panX);
        int y = calcPos(getY(), scale, panY);

        super.render(scale, panX, panY);

        sketch.strokeWeight(3 * scale);
        if (getOrientation() == 0 || getOrientation() == 2) { // N/S facing
            sketch.line(x + unit, y, x + unit, y + unit/2); //top vertical line
            sketch.line(x + unit, y + 5* unit/2, x + unit, y + 3 * unit); //bottom vertical line
            sketch.line(x, y + unit/2, x + 2 * unit, y + unit/2); //top horizontal line
            sketch.line(x, y + 5 * unit/2, x + 2 * unit, y + 5 * unit/2); //bottom horizontal line
            sketch.line(x, y + unit/2, x, y + 5*unit/2); //left vertical line
            sketch.line(x + 2 * unit, y + unit/2, x + 2 * unit, y + 5 * unit/2); //right vertical line
        } else { // E / W facing
            sketch.line(x, y + unit, x + unit/2, y + unit); //left horizontal line
            sketch.line(x + 5 * unit/2, y + unit, x + 3 * unit, y + unit); //right horizontal line
            sketch.line(x + unit/2, y, x + unit/2, y + 2 * unit); //left vertical line
            sketch.line(x + 5 * unit/2, y, x + 5 * unit/2, y + 2 * unit); //right vertical line
            sketch.line(x + unit/2, y, x + 5 * unit/2, y); //top horizontal line
            sketch.line(x + unit/2, y + 2 * unit, x + 5 * unit/2, y + 2 * unit); //bottom horizontal line
        }
    }
}