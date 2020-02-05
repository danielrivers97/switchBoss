import java.util.ArrayList;

public class Click {
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

    public void mousePress(ArrayList<Component> components, int mouseX, int mouseY, float scale) {
        for (Component c : components) {
            if(c.getOrientation() == 0 || c.getOrientation() == 2) {
                if(mouseX >= c.getX() && mouseX <= c.getX() + 2 * 20 * scale && mouseY >= c.getY() && mouseY <= c.getY() + 3 * 20 * scale) {
                    if (c.getNormalstate() == 0) {
                        c.setCurrentstate(1);
                    } else if (c.getNormalstate() == 1) {
                        c.setCurrentstate(0);
                    }
                }
            }
            else {
                if(mouseX >= c.getX() && mouseX <= c.getX() + 3 * 20 * scale && mouseY >= c.getY() && mouseY <= c.getY() + 2 * 20 * scale) {
                    if (c.getNormalstate() == 0) {
                        c.setCurrentstate(1);
                    } else if (c.getNormalstate() == 1) {
                        c.setCurrentstate(0);
                    }
                }
            }
        }
    }
}
