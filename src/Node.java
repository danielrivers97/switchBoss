import processing.core.PApplet;

import java.io.*;

public class Node extends Component {

    private boolean open;

    public Node(SwitchBoss sketch, int id, Coord loc, String name, int orientation, int normalstate, int currentstate, String type) {
        super(sketch, id, loc, name, orientation, normalstate, currentstate, type);

        setHeight(0);
        setWidth(0);
        setIn(getLoc());
        setOut(getLoc());
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}