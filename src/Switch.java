import processing.core.PApplet;
import java.io.*;

public class Switch extends Component {

    private boolean open;

    public Switch(PApplet sketch, int x, int y, String name, int orientation, int normalstate) {
        super(sketch, x, y, name, orientation, normalstate);
        open = true;
    }

    public void render(float scale, int panX, int panY) {

        int unit = (int) (20 * scale);
        int x = (int) (scale * (getX() + panX));
        int y = (int) (scale * (getY() + panY));

        super.render(scale, panX, panY);

        sketch.strokeWeight(3 * scale);
        if (getOrientation() == 0 || getOrientation() == 2) {
            // N/S facing
            if(this.getCurrentstate() != this.getNormalstate()) {
                if(this.getNormalstate() == 0) {
                    sketch.fill(255);
                    sketch.stroke(0, 255, 0);
                    sketch.circle(x + unit, y + 3*unit/2, 20);
                }
                else if(this.getNormalstate() == 1) {
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
            if(this.getCurrentstate() != this.getNormalstate()) {
                if(this.getNormalstate() == 0) {
                    sketch.fill(255);
                    sketch.stroke(0, 255, 0);
                    sketch.circle(x + 3*unit/2, y + unit, 20);
                }
                else if(this.getNormalstate() == 1) {
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
}
