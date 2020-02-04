import processing.core.PApplet;

public class Breaker extends Component{
    public Breaker(PApplet sketch, int x, int y, String name, int orientation) {
        super(sketch, x, y, name, orientation);
    }

    public void render(float scale, int panX, int panY) {
        int unit = (int) (20 * scale);
        int x = (int) (scale * (getX() + panX));
        int y = (int) (scale * (getY() + panY));

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
