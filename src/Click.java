import java.util.ArrayList;

public class Click {

    // CHANGE THIS: second declaration in component
    public static final int UNIT = 20;

    float x;
    float y;

    public Click() {
        this.x = 0;
        this.y = 0;
    }

    public float getX() {
        return this.x;
    }
    public float getY() {
        return this.y;
    }
    public int calcPos(int coord, float scale, int pan) {
        return (int) ((scale * (scale + coord + pan)) + (UNIT * 3 * coord * scale));
    }

    public void mousePress(ArrayList<Component> components, int mouseX, int mouseY, float scale, int panX, int panY) {
        for (Component c : components) {
//            int x = (int) (scale * (c.getX() + panX));
//            int y = (int) (scale * (c.getY() + panY));
            int x = calcPos(c.getX(), scale, panX);
            int y = calcPos(c.getY(), scale, panY);
            if(c.getOrientation() == 0 || c.getOrientation() == 2) {
                if(mouseX >= x && mouseX <= x + 2 * 20 * scale && mouseY >= y && mouseY <= y + 3 * 20 * scale) {
                    if (c.getCurrentstate() == 0) {
                        c.setCurrentstate(1);
                    } else if (c.getCurrentstate() == 1) {
                        c.setCurrentstate(0);
                    }
                }
            }
            else {
                if(mouseX >= x && mouseX <= x + 3 * 20 * scale && mouseY >= y && mouseY <= y + 2 * 20 * scale) {
                    if (c.getCurrentstate() == 0) {
                        c.setCurrentstate(1);
                    } else if (c.getCurrentstate() == 1) {
                        c.setCurrentstate(0);
                    }
                }
            }
        }
    }
}