import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.PApplet;
import processing.event.*;

public class SwitchBoss extends PApplet {

    public static final int WIDTH = 80, HEIGHT = 40;
    public static final int UNIT = 20;

    public ArrayList<Component> components = new ArrayList<>(); // keep track of all components

    public boolean canPan = false;
    public boolean canZoom = true;

    public float scale = 1;
    public int panX = 0;
    public int panY = 0;


    public void settings() {
        size(WIDTH * UNIT, HEIGHT * UNIT);
    }

    public void draw() {
        background(0xFFFFFF);
        strokeWeight(0.5f * scale);
        for (int i = 0; i < 500; i++) {
            line(i * UNIT * scale, 0, i * UNIT * scale, 500 * UNIT * scale);
        }
        for (int i = 0; i < 500; i++) {
            line(0, i * UNIT * scale, 500 * UNIT * scale, i * UNIT * scale);
        }
        for (Component c : components) {
            c.render_wire();
            c.render(scale, panX, panY);
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

    }

    public void mouseDragged() {
        // panX +=
    }

    public void mouseWheel(MouseEvent event) {
        float cnt = event.getCount();
        if (scale < 0.1) {
            scale = 0.1f;
        }
        scale += -cnt / 70;
        System.out.println(scale);
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

            switch(type) {
                case "SW":
                    sketch.components.add(new Switch(sketch, id, x, y, name, orient, true));
                    break;
                case "BR":
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