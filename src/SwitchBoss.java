import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.PApplet;
import processing.event.*;

public class SwitchBoss extends PApplet {

    public static int WIDTH = 40, HEIGHT = 20;
    public static final int UNIT = 20;

    public ArrayList<Component> components = new ArrayList<>(); // keep track of all components
    public ArrayList<Label> labels = new ArrayList<>(); //keep track of all labels

    Viewport viewport = new Viewport();
    Click click = new Click();
    UserInterface ui = new UserInterface();

    public boolean canPan = true;
    public boolean canZoom = true;

    // Handles sketch size and runs fullscreen method
    public void settings() {
        fullScreen();
        WIDTH = displayWidth / UNIT;
        HEIGHT = displayHeight / UNIT;
        viewport.setSize(displayWidth, displayHeight);
        ui.setSize(this);
    }

    // Calls the update and render methods for each recognized component
    // Renders wires between components and has optional background grid
    // update reads whether the component is energized or not
    public void draw() {
        float scale = viewport.getScale();
        background(0xFFFFFF);

//      Uncomment to display grid
//      strokeWeight(0.5f * viewport.getScale());
//      for (int i = 0; i < 500; i++) {
//          line(i * UNIT * scale, 0, i * UNIT * scale, 500 * UNIT * scale);
//      }
//      for (int i = 0; i < 500; i++) {
//          line(0, i * UNIT * scale, 500 * UNIT * scale, i * UNIT * scale);
//      }

        for (Component c : components) {
            c.update();
            c.render_wire();
            c.render(viewport.getScale(), viewport.getX(), viewport.getY());
        }

        for (Label l : labels) {
            l.render(viewport.getScale(), viewport.getX(), viewport.getY());
        }

        // draw the ui
        ui.draw();
    }

    public void keyPressed() {
        // zoom in
        if (key == 'z') {
            viewport.setScale(-10);
        }

        // zoom out
        if (key == 'Z') {
            viewport.setScale(10);
        }

        // reload grid
        if (key == 'r') {
            try {
                readFile("positions.txt", this);
            } catch (IOException e) {
                System.err.println("Unable to reload file! Quitting...");
                System.exit(-1);
            }
        }

        //reset grid
        if (key == 'R') {
            //write over each component in positions.txt and change current state to normal state
            for (Component c : components) {
                click.writeFile(c, "positions.txt", c.getCurrentState(), c.getNormalState());
            }
            try {
                readFile("positions.txt", this);
            } catch (IOException e) {
                System.err.println("Unable to reload file! Quitting...");
                System.exit(-1);
            }
        }

        if (key == 'v') {
            // grid verification on line 2 of positions.txt
            verifyGrid();
        }
    }

    public void verifyGrid() {
        String fName = "positions.txt";
        File file = new File(fName);
        java.util.Date date = new java.util.Date();
        String dateVerified = "# last verified: " + date + "\n";

        int lineNo = 1;
        int verifyNo = 2;

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = br.readLine()) != null) {
                if (lineNo == verifyNo) {
                    inputBuffer.append(dateVerified);
                } else {
                    inputBuffer.append(line);
                    inputBuffer.append('\n');
                }
                lineNo++;
            }
            br.close();
            String inputStr = inputBuffer.toString();

            FileOutputStream fileOut = new FileOutputStream(fName);
            fileOut.write(inputStr.getBytes());
            fileOut.close();
        } catch (Exception e) {
            System.out.println("Problem verifying grid");
        }
    }

    public Component getComponentFromID(int id) {
        for (Component c : components) {
            if (id == c.getId()) {
                return c;
            }
        }
        System.err.println("Component not found!");
        return null;
    }

    public void mousePressed() {
        canZoom = false;
        int ret = click.mousePress(components, mouseX, mouseY, viewport.getScale(), (int) viewport.getX(), (int) viewport.getY(), ui);
        if (ret == 'z') {
            // zoom in
            viewport.setScale(-10);
        } else if (ret == 'Z') {
            // zoom out
            viewport.setScale(10);
        } else if (ret == 'r') {
            // reload grid
            try {
                readFile("positions.txt", this);
            } catch (IOException e) {
                System.err.println("Unable to reload file! Quitting...");
                System.exit(-1);
            }
        } else if (ret == 'v') {
            // grid verification on line 2 of positions.txt
            verifyGrid();
        }
        viewport.mousePress(mouseX, mouseY);
    }

    public void mouseDragged() {
        viewport.mouseDrag(mouseX, mouseY);
    }

    public void mouseReleased() {
        viewport.mouseRelease();
        canZoom = true;
    }

    public void mouseWheel(MouseEvent event) {
        float count = event.getCount();
        viewport.setScale(count);
    }

    public boolean isOnAnyComponent(int x, int y) {
        for (Component c : components) {
            if (c.isOnComponent(x, y) && !(c instanceof Node)) {
                return true;
            }
        }
        return false;
    }

    // MAIN METHOD
    public static void main(String[] args) {
        // Initialize
        String[] processingArgs = {"SwitchBoss"};
        SwitchBoss switchBoss = new SwitchBoss();
        // Attempt to read positions.txt
        try {
            readFile("positions.txt", switchBoss);
            readLabels("labels.txt", switchBoss);
        } catch (IOException e) {
            System.err.println("File not found! Quitting...");
            System.exit(-1);
        }
      
        // Run PApplet to create sketch
        PApplet.runSketch(processingArgs, switchBoss);
    }

    public static void readLabels(String fName, SwitchBoss sketch) throws IOException {
        File file = new File(fName);
        BufferedReader br = new BufferedReader(new FileReader(file));
        sketch.labels.clear();
        String st;
        Scanner sc;
        while ((st = br.readLine()) != null) {
            sc = new Scanner(st);
            int bigorsmall = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();
            String label = sc.nextLine();
            sketch.labels.add(new Label(sketch, bigorsmall, new Coord(x, y), label));
        }
    }

    // ASSUMING CORRECT FILE FORMAT!!!! NO ERROR CHECKING IMPLEMENTED
    // - if positions.txt is formatted incorrectly, this method should break!
    public static void readFile(String fName, SwitchBoss sketch) throws IOException {
        File file = new File(fName);
        BufferedReader br = new BufferedReader(new FileReader(file));
        sketch.components.clear(); // empty array list
        String st;
        Scanner sc;
        int pwr = 1;
        int lineNo = 1;

        while ((st = br.readLine()).compareTo("WIRES") != 0) {
            sc = new Scanner(st);

            if (lineNo++ == 2) {
                // grid last verified info
                sketch.ui.setVerifyInfo(st);
                continue;
            }

            if (sc.hasNext("#")) {
                // comment
                continue;
            }

            // Reads and stores information in these local variables
            // assuming correct format. To change positions.txt format, look here
            int id = sc.nextInt();
            String type = sc.next();
            int x = sc.nextInt();
            int y = sc.nextInt();
            int orient = sc.nextInt();
            String name = sc.next();
            int ns = sc.nextInt();
            int cs = sc.nextInt();

            // This switch-case handles the different components that can be added to the grid
            switch (type) {
                // Switch
                case "SW":
                    sketch.components.add(new Switch(sketch, id, new Coord(x, y), name, orient, ns, cs, type));
                    break;
                // Breaker - Standard box display for breaker
                case "BR":
                    sketch.components.add(new Breaker(sketch, id, new Coord(x, y), name, orient, ns, cs, type));
                    break;
                // Power Source
                case "PS":
                    sketch.components.add(new PowerSource(sketch, id, new Coord(x, y), name, orient, ns, cs, type, pwr++));
                    break;
                // Node - Cross section used for wire direction purposes
                case "ND":
                    sketch.components.add(new Node(sketch, id, new Coord(x, y), name, orient, ns, cs, type));
                    break;
                // Transformer - Changes voltage from one section of the grid to the next
                case "TR":
                    sketch.components.add(new Transformer(sketch, id, new Coord(x, y), name, orient, ns, cs, type));
                    break;
                // Removable Breaker - Used for breakers that are displayed as removable
                case "RB":
                    sketch.components.add(new RemovableBreaker(sketch, id, new Coord(x, y), name, orient, ns, cs, type));
                    break;
                default:
                    break;
            }
        }

        // While there is still more to read, read the next line of the file
        while ((st = br.readLine()) != null) {
            sc = new Scanner(st);
            int out = sc.nextInt();
            int in = sc.nextInt();
            sketch.getComponentFromID(in).addInComp(sketch.getComponentFromID(out));
            sketch.getComponentFromID(out).addOutComp(sketch.getComponentFromID(in));
        }
    }

    // change color of wire based on power source
    public void setStrokeFromEnergy(int energy) {
        switch(energy) {
            case 0: //Default no power on line
                this.stroke(0,0,0);
                break;
            case 1: //Organa Power Station
                this.stroke(255, 0, 0);
                break;
            case 2: //Kenobi Power Station
                this.stroke(0, 255, 0);
                break;
            case 3: //Backup Generator Station
                this.stroke(165, 0, 255);
                break;
            case 4: //Backup Generator A
                this.stroke(255, 165, 165);
                break;
            case 5: //Backup Generator B
                this.stroke(0, 255, 255);
                break;
            case 6: //Backup Generator C
                this.stroke(255, 0, 255);
                break;
            case 7: //Backup Generator D
                this.stroke(255, 165, 0);
                break;
            case 8: //Backup Generator E
                this.stroke(165, 255, 0);
                break;
            default:
                System.out.println("no color given to power source");
                System.exit(-1);
                break;
        }
    }
}