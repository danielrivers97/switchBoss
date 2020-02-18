import processing.core.PApplet;

public class RemovableBreaker extends Component {
    public RemovableBreaker(SwitchBoss sketch, int id, Coord loc, String name, int orientation, int normalstate, int currentstate, String type) {
        super(sketch, id, loc, name, orientation, normalstate, currentstate, type);
    }

    public void render(float scale, float panX, float panY) {
        int unit = (int) (UNIT * scale);
        int x = calcPos(getX(), scale, panX);
        int y = calcPos(getY(), scale, panY);

        super.render(scale, panX, panY);

        sketch.strokeWeight(3 * scale);
        if (getOrientation() == 0 || getOrientation() == 2) { // N/S facing
            sketch.line(x + unit, y - unit, x + 2 * unit, y - unit / 2); //top outside arrow
            sketch.line(x + unit, y - unit, x, y - unit / 2);

            sketch.line(x + unit, y + 4 * unit, x + 2 * unit, y + 7 * unit / 2); //bottom outside arrow
            sketch.line(x + unit, y + 4 * unit, x, y + 7 * unit / 2);

            sketch.line(x + unit, y - unit / 2, x + 2 * unit, y); //top inside arrow
            sketch.line(x + unit, y - unit / 2, x, y);

            sketch.line(x + unit, y + 7 * unit / 2, x + 2 * unit, y + 3 * unit); //bottom inside arrow
            sketch.line(x + unit, y + 7 * unit / 2, x, y + 3 * unit);

            sketch.line(x + unit, y - unit / 2, x + unit, y + unit / 2); //top vertical line
            sketch.line(x + unit, y + 5 * unit / 2, x + unit, y + 7 * unit / 2); //bottom vertical line
            sketch.line(x, y + unit / 2, x + 2 * unit, y + unit / 2); //top horizontal line
            sketch.line(x, y + 5 * unit / 2, x + 2 * unit, y + 5 * unit / 2); //bottom horizontal line
            sketch.line(x, y + unit / 2, x, y + 5 * unit / 2); //left vertical line
            sketch.line(x + 2 * unit, y + unit / 2, x + 2 * unit, y + 5 * unit / 2); //right vertical line
        } else { // E / W facing
            sketch.line(x - unit, y + unit, x - unit / 2, y); //left outside arrow
            sketch.line(x - unit, y + unit, x - unit / 2, y + 2 * unit);

            sketch.line(x + 4 * unit, y + unit, x + 7 * unit / 2, y + 2 * unit); //right outside arrow
            sketch.line(x + 4 * unit, y + unit, x + 7 * unit / 2, y);

            sketch.line(x - unit / 2, y + unit, x, y); //left inside arrow
            sketch.line(x - unit / 2, y + unit, x, y + 2 * unit);

            sketch.line(x + 7 * unit / 2, y + unit, x + 3 * unit, y); //right inside arrow
            sketch.line(x + 7 * unit / 2, y + unit, x + 3 * unit, y + 2 * unit);

            sketch.line(x - unit / 2, y + unit, x + unit / 2, y + unit); //left horizontal line
            sketch.line(x + 5 * unit / 2, y + unit, x + 7 * unit / 2, y + unit); //right horizontal line
            sketch.line(x + unit / 2, y, x + unit / 2, y + 2 * unit); //left vertical line
            sketch.line(x + 5 * unit / 2, y, x + 5 * unit / 2, y + 2 * unit); //right vertical line
            sketch.line(x + unit / 2, y, x + 5 * unit / 2, y); //top horizontal line
            sketch.line(x + unit / 2, y + 2 * unit, x + 5 * unit / 2, y + 2 * unit); //bottom horizontal line
        }
    }
}