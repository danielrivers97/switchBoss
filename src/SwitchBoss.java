import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

import processing.core.PApplet;
import processing.event.*;

public class SwitchBoss extends PApplet {

    public ArrayList<Component> components = new ArrayList<>(); // keep track of all components

    Pan pan = new Pan();

    public boolean canPan = true;
    public boolean canZoom = true;

    public float scale = 1;

    public void settings() {
        size(1000, 750);
        pan.setSize(width, height);
        components.add(new Switch(this, 200, 200, "switch", 0));
        components.add(new Switch(this, 400, 100, "switch2", 1));
    }

    public void draw() {
        background(0xFFFFFF);
        for (Component c : components) {
            c.render(pan.getScale(), (int)pan.getX(), (int)pan.getY());
        }
    }

    public void mousePressed() {
        canZoom = false;
        pan.mousePress(mouseX / scale, mouseY / scale);
    }

    public void mouseDragged() {
        pan.mouseDrag(mouseX / scale, mouseY / scale);
    }

    public void mouseReleased() {
        pan.mouseRelease();
        canZoom = true;
    }

    public void mouseWheel(MouseEvent event) {
        float count = event.getCount();
        pan.setScale(count);
        println(pan.getScale());
    }

    public static void main(String[] args) {
        String[] processingArgs = {"SwitchBoss"};
        SwitchBoss mySketch = new SwitchBoss();

        PApplet.runSketch(processingArgs, mySketch);
    }
}