public class PowerSource extends Component {

    public PowerSource(SwitchBoss sketch, int id, Coord loc, String name, int orientation, int normalstate, int currentstate, String type, int power) {
        super(sketch, id, loc, name, orientation, normalstate, currentstate, type);
        setEnergyState(power);
  
        setHeight(8);
        setWidth(8);
        // Power sources shouldn't have any ins, only out
        //setIn(new Coord(getX() + 4, getY() + 8));
        setOut(new Coord(getX() + 4, getY() + 8));
    }

    public void render(float scale, float panX, float panY) {
        float unit = UNIT * scale;
        int x = calcPos(getX(), scale, panX);
        int y = calcPos(getY(), scale, panY);

        //super.render(scale, panX, panY);

        sketch.strokeWeight(3 * scale);
        sketch.stroke(0);
        sketch.fill(255);
        sketch.square(x, y, getHeight()  * unit);

        sketch.stroke(0);
        sketch.textSize(18 * scale);
        sketch.fill(25, 50, 255);
        if(this.getName().equals("ORG")) {
            sketch.text("Organa Hydro\n& Electric\n(Continuous\nPower Feed)", x + unit, y + 2 * unit);
        }
        else if(this.getName().equals("KEN")) {
            sketch.text("Kenobi\nPower Divide\n(Continuous\nPower Feed)", x + unit, y + 2 * unit);
        }
    }
}