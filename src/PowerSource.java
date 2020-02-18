public class PowerSource extends Component {
    public PowerSource(SwitchBoss sketch, int id, Coord loc, String name, int orientation, int normalstate) {
        super(sketch, id, loc, name, orientation, normalstate);
        setHeight(8);
        setWidth(8);
        setIn(new Coord(getX()+4, getY()+8));
        setOut(new Coord(getX()+4, getY()+ 8));
    }

    public void render(float scale, int panX, int panY) {
        int unit = (int) (UNIT * scale);
        int x = calcPos(getX(), scale, panX);
        int y = calcPos(getY(), scale, panY);

        super.render(scale, panX, panY);


        sketch.strokeWeight(3 * scale);
        sketch.stroke(0);
        sketch.fill(255);
        sketch.rect(x, y, 8 * unit, 8 * unit);

        sketch.stroke(0);
        sketch.fill(0);
        sketch.text("POWER SOURCE\n TEST", x + 2 * unit, y + 2 * unit);

    }
}