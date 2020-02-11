import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.PApplet;
import processing.event.*;

public class SwitchBoss extends PApplet {

    public static final int WIDTH = 80, HEIGHT = 40;
    public static final int UNIT = 20;

    public ArrayList<Component> components = new ArrayList<>(); // keep track of all components

    Viewport viewport = new Viewport();
    Click click = new Click();

    public boolean canPan = true;
    public boolean canZoom = true;


    public void settings() {
        size(WIDTH * UNIT, HEIGHT * UNIT);
        viewport.setSize(WIDTH * UNIT, HEIGHT * UNIT);
    }

    public void draw() {
        background(0xFFFFFF);
//          Uncomment to display grid
//          strokeWeight(0.5f * viewport.getScale());
//        for (int i = 0; i < 500; i++) {
//            line(i * UNIT * scale, 0, i * UNIT * scale, 500 * UNIT * scale);
//        }
//        for (int i = 0; i < 500; i++) {
//            line(0, i * UNIT * scale, 500 * UNIT * scale, i * UNIT * scale);
//        }
        for (Component c : components) {
            //c.render_wire();
            c.render(viewport.getScale(), (int) viewport.getX(), (int) viewport.getY());
        }
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
        click.mousePress(components, mouseX, mouseY, viewport.getScale(), (int) viewport.getX(), (int) viewport.getY());
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
            if (c.isOnComponent(x, y)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String[] processingArgs = {"SwitchBoss"};
        SwitchBoss switchBoss = new SwitchBoss();
        try {
            readFile("positions.txt", switchBoss);
        } catch (IOException e) {
            System.err.println("File not found! Quitting...");
            System.exit(-1);
        }
        PApplet.runSketch(processingArgs, switchBoss);
    }

    // ASSUMING CORRECT FILE FORMAT!!!! NO ERROR CHECKING IMPLEMENTED
    public static void readFile(String fName, SwitchBoss sketch) throws IOException {
        File file = new File(fName);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        Scanner sc;
        while ((st = br.readLine()).compareTo("#") != 0) {
            sc = new Scanner(st);
            int id = sc.nextInt();
            String type = sc.next();
            int x = sc.nextInt();
            int y = sc.nextInt();
            int orient = sc.nextInt();
            String name = sc.next();
            int ns = sc.nextInt();

            switch (type) {
                case "SW":
                    sketch.components.add(new Switch(sketch, id, new Coord(x, y), name, orient, ns));
                    break;
                case "BR":
                    sketch.components.add(new Breaker(sketch, id, new Coord(x, y), name, orient, ns));
                    break;
                default:
                    break;
            }
        }
        while ((st = br.readLine()) != null) {
            sc = new Scanner(st);
            int out = sc.nextInt();
            int in = sc.nextInt();
            //sketch.getComponentFromID(in).addInComp(sketch.getComponentFromID(out));
            sketch.getComponentFromID(out).addOutComp(sketch.getComponentFromID(in));
        }
    }
}