import processing.core.PConstants;

public class Transformer extends Component {
    public Transformer(SwitchBoss sketch, int id, Coord loc, String name, int orientation, int normalstate, int currentstate, String type) {
        super(sketch, id, loc, name, orientation, normalstate, currentstate, type);
        setHeight(4);
        setWidth(4);
        setIn(new Coord(getX() + 2, getY()));
        setOut(new Coord(getX() + 2, getY()+ 4));
    }

    public void render(float scale, float panX, float panY) {
        int unit = (int) (UNIT * scale);
        int x = calcPos(getX(), scale, panX);
        int y = calcPos(getY(), scale, panY);

        super.render(scale, panX, panY);

        sketch.strokeWeight(3 * scale);
        sketch.stroke(0);

        sketch.noFill();

        //bottom row of semicircles
        sketch.arc(x + unit/2, y + 3 * unit, unit, 2 * unit, PConstants.PI, 2 * PConstants.PI);
        sketch.arc(x + 3 * unit/2, y + 3 * unit, unit, 2 * unit, PConstants.PI, 2 * PConstants.PI);
        sketch.arc(x + 5 * unit/2, y + 3 * unit, unit, 2 * unit, PConstants.PI, 2 * PConstants.PI);
        sketch.arc(x + 7 * unit/2, y + 3 * unit, unit, 2 * unit, PConstants.PI, 2 * PConstants.PI);

        //top row of semicircles
        sketch.arc(x + unit/2, y + unit, unit, 2 * unit, 0, PConstants.PI);
        sketch.arc(x + 3 * unit/2, y + unit, unit, 2 * unit, 0, PConstants.PI);
        sketch.arc(x + 5 * unit/2, y + unit, unit, 2 * unit, 0, PConstants.PI);
        sketch.arc(x + 7 * unit/2, y + unit, unit, 2 * unit, 0, PConstants.PI);

        //top vertical line
        sketch.line(x + 2 * unit, y, x + 2 * unit, y + unit);
        //bottom vertical line
        sketch.line(x + 2 * unit, y + 4 * unit, x + 2 * unit, y + 3 * unit);
    }
}