import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

import processing.core.PApplet;
import processing.event.*;

public class SwitchBoss extends PApplet {

    public ArrayList<Component> components = new ArrayList<>(); // keep track of all components

    public boolean canPan = false;
    public boolean canZoom = true;

    public float scale = 1;
    public int panX = 0;
    public int panY = 0;

    public void settings() {
        size(1000, 750);
        components.add(new Switch(this, 200, 200, "switch", 0));
        components.add(new Switch(this, 400, 100, "switch2", 1));
    }

    public void draw() {
        background(0xFFFFFF);
        for (Component c : components) {
            c.render(scale, panX, panY);
        }
        //scale += 0.01;

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
    }

    public static void main(String[] args) {
        String[] processingArgs = {"SwitchBoss"};
        SwitchBoss mySketch = new SwitchBoss();

        PApplet.runSketch(processingArgs, mySketch);
    }
}