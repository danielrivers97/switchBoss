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
        return (int) ((scale * (scale + coord + pan)) + (UNIT * coord * scale));
    }

    public static void writeFile(Component c, String fName, int changedstate, int newstate) {
        File file = new File(fName);
        String target = c.getId() + " " + c.getType() + " " + c.getX() + " " + c.getY() + " " + c.getOrientation() + " " + c.getName() + " " + c.getNormalState() + " " + changedstate;
        String replacement = c.getId() + " " + c.getType() + " " + c.getX() + " " + c.getY() + " " + c.getOrientation() + " " + c.getName() + " " + c.getNormalState() + " " + newstate;

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while((line = br.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            br.close();
            String inputStr = inputBuffer.toString();

            inputStr = inputStr.replace(target, replacement);

            FileOutputStream fileOut = new FileOutputStream(fName);
            fileOut.write(inputStr.getBytes());
            fileOut.close();
        }
        catch(Exception e) {
            System.out.println("Problem reading file.");
        }
    }

    public void mousePress(ArrayList<Component> components, int mouseX, int mouseY, float scale, int panX, int panY) {
        for (Component c : components) {
            int x = calcPos(c.getX(), scale, panX);
            int y = calcPos(c.getY(), scale, panY);
            if(c.getOrientation() == 0 || c.getOrientation() == 2) {
                if(mouseX >= x && mouseX <= x + 2 * 20 * scale && mouseY >= y && mouseY <= y + 3 * 20 * scale) {
                    if (c.getCurrentState() == 0) {
                        c.setCurrentState(1);
                        writeFile(c, "positions.txt", 0, 1);
                    } else if (c.getCurrentState() == 1) {
                        c.setCurrentState(0);
                        writeFile(c, "positions.txt", 1, 0);
                    }
                }
            }
            else {
                if(mouseX >= x && mouseX <= x + 3 * 20 * scale && mouseY >= y && mouseY <= y + 2 * 20 * scale) {
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
    }
}