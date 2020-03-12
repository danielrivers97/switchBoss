public class Breaker extends Component {
    public Breaker(SwitchBoss sketch, int id, Coord loc, String name, int orientation, int normalstate, int currentstate, String type) {
        super(sketch, id, loc, name, orientation, normalstate, currentstate, type);
        switch (orientation) {
            case 0: //N
                setHeight(4);
                setWidth(2);
                setIn(new Coord(getX() + 1, getY() + 4));
                setOut(new Coord(getX() + 1, getY()));
                break;
            case 1: // E
                setHeight(2);
                setWidth(4);
                setIn(new Coord(getX(), getY() + 1));
                setOut(new Coord(getX() + 4, getY() + 1));
                break;
            case 2: // S
                setHeight(4);
                setWidth(2);
                setIn(new Coord(getX() + 1, getY()));
                setOut(new Coord(getX() + 1, getY() + 4));
                break;
            case 3: // W
                setHeight(2);
                setWidth(4);
                setIn(new Coord(getX() + 4, getY() + 1));
                setOut(new Coord(getX(), getY() + 1));
                break;
            default:
                System.exit(-1); // error checks later (SHOULD NEVER HAPPEN)
        }
    }

    public void render(float scale, float panX, float panY) {
        int unit = (int) (UNIT * scale);
        int x = calcPos(getX(), scale, panX);
        int y = calcPos(getY(), scale, panY);

        super.render(scale, panX, panY);

        sketch.strokeWeight(3 * scale);
        if (getOrientation() == 0 || getOrientation() == 2) {
            // N/S facing
            sketch.line(x + unit, y, x + unit, y + unit);                               //top vertical line
            sketch.line(x + unit, y + 3 * unit, x + unit, y + 4 * unit);            //bottom vertical line
            sketch.line(x, y + unit, x + 2 * unit, y + unit);                           //top horizontal line
            sketch.line(x, y + 3 * unit, x + 2 * unit, y + 3 * unit);                   //bottom horizontal line
            sketch.line(x, y + unit, x, y + 3 * unit);                                      //left vertical line
            sketch.line(x + 2 * unit, y + unit, x + 2 * unit, y + 3 * unit);        //right vertical line
        } else {
            // E / W facing
            sketch.line(x, y + unit, x + unit, y + unit);                               //left horizontal line
            sketch.line(x + 3 * unit, y + unit, x + 4 * unit, y + unit);            //right horizontal line
            sketch.line(x + unit, y, x + unit, y + 2 * unit);                           //left vertical line
            sketch.line(x + 3 * unit, y, x + 3 * unit, y + 2 * unit);                   //right vertical line
            sketch.line(x + unit, y, x + 3 * unit, y);                                      //top horizontal line
            sketch.line(x + unit, y + 2 * unit, x + 3 * unit, y + 2 * unit);        //bottom horizontal line
        }
    }
}