public class Label {
    public static final int UNIT = 20;

    protected SwitchBoss sketch;

    private Coord loc; // coordinates of top left of component (in grid units)

    private String name; // name not specific to component - just a label

    private int bigorsmall; //whether the text is big/blue or small/black

    public Label(SwitchBoss sketch, int bigorsmall, Coord loc, String name) {
        this.sketch = sketch;
        this.loc = loc;
        this.name = name;
        this.bigorsmall = bigorsmall;
    }

    public void render(float scale, float panX, float panY) {
        int unit = (int) (UNIT * scale);
        int x = calcPos(getX(), scale, panX);
        int y = calcPos(getY(), scale, panY);

        if(this.getBigOrSmall() == 0) {
            sketch.textSize(40 * scale);
            sketch.fill(25, 50, 255);
            sketch.text(this.getName(), x + scale * UNIT, y + scale * UNIT);
        }
        else {
            sketch.textSize(20 * scale);
            sketch.fill(0);
            sketch.text(this.getName(), x + scale * UNIT, y + scale * UNIT);
        }
    }

    public int getBigOrSmall() {
        return this.bigorsmall;
    }

    public String getName() {
        return this.name;
    }

    public int calcPos(int coord, float scale, float pan) {
        //return (int) (UNIT * scale * coord) + pan;
        return (int) ((UNIT * coord * scale) + (pan * scale));
    }

    public int getX() {
        return this.loc.getX();
    }

    public int getY() {
        return this.loc.getY();
    }
}