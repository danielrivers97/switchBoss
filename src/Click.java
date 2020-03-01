import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;

public class Click {

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
        return (int) ((UNIT * coord * scale) + (pan * scale));
    }

    public static void writeFile(Component c, String fName, int changedstate, int newstate) {
        File file = new File(fName);
        String target = c.getId() + " " + c.getType() + " " + c.getX() + " " + c.getY() + " " + c.getOrientation() + " " + c.getName() + " " + c.getNormalState() + " " + changedstate;
        String replacement = c.getId() + " " + c.getType() + " " + c.getX() + " " + c.getY() + " " + c.getOrientation() + " " + c.getName() + " " + c.getNormalState() + " " + newstate;

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = br.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            br.close();
            String inputStr = inputBuffer.toString();

            inputStr = inputStr.replace(target, replacement);

            FileOutputStream fileOut = new FileOutputStream(fName);
            fileOut.write(inputStr.getBytes());
            fileOut.close();
        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }

    public char mousePress(ArrayList<Component> components, int mouseX, int mouseY, float scale, int panX, int panY, UserInterface ui) {
        // check if clicked in tools box
        if (mouseX > ui.toolsX && mouseX < ui.toolsX + ui.toolsWidth &&
                mouseY > ui.toolsY && mouseY < ui.toolsY + ui.toolsHeight) {
            if (mouseY < ui.divPoint1Y) {
                // zoom in
                return 'z';
            } else if (mouseY < ui.divPoint2Y) {
                // zoom out
                return 'Z';
            } else if (mouseY < ui.divPoint3Y) {
                // refresh
                return 'r';
            } else {
                // validate
                return 'v';
            }
        }
        for (Component c : components) {
            int x = calcPos(c.getX(), scale, panX);
            int y = calcPos(c.getY(), scale, panY);
            if (c instanceof Switch || c instanceof RemovableBreaker) {
                if (mouseX >= x && mouseX <= x + c.getWidth() * UNIT * scale && mouseY >= y && mouseY <= y + c.getHeight() * UNIT * scale) {
                    resetEnergy(components);
                    if (c.getCurrentState() == 0) {
                        c.setCurrentState(1);
                        writeFile(c, "positions.txt", 0, 1);
                    } else if (c.getCurrentState() == 1) {
                        c.setCurrentState(0);
                        writeFile(c, "positions.txt", 1, 0);
                    }
                }
            }
        }
        return '\0';
    }

    // resets the energy state for all components besides power sources
    public void resetEnergy(ArrayList<Component> components) {
        for (Component c : components) {
            if (!(c instanceof PowerSource)) {
                c.setEnergyState(0);
            }
        }
    }
}