import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

import processing.core.PApplet;
import processing.event.*;

public class SwitchBoss extends PApplet {

    public ArrayList<Component> components = new ArrayList<>(); // keep track of all components

    Pan pan = new Pan();
    Click click = new Click();

    public boolean canPan = true;
    public boolean canZoom = true;

    public float scale = 1;

    public void settings() {
        size(1000, 750);
        components.add(new Switch(this, 200, 200, "switch", 0));
        components.add(new Switch(this, 400, 100, "switch2", 1));
    }

    public void draw() {
        background(0xFFFFFF);
        for (Component c : components) {
            c.render(scale, (int)pan.getX(), (int)pan.getY());
        }
        //scale += 0.01;

    }

    public void mousePressed() {
        canZoom = false;
        canPan = false;
        for(Component c : components) {
            if(c.getOrientation() == 0 || c.getOrientation() == 2) {
                if(mouseX >= c.getX() && mouseX <= c.getX() + 2 * 20 * scale && mouseY >= c.getY() && mouseY <= c.getY() + 3 * 20 * scale) {
                    c.setX(300);
                    c.setY(300);
                }
            }
            else {
                if(mouseX >= c.getX() && mouseX <= c.getX() + 3 * 20 * scale && mouseY >= c.getY() && mouseY <= c.getY() + 2 * 20 * scale) {
                    c.setX(100);
                    c.setY(400);
                }
            }
        }
        pan.mousePress(mouseX / scale, mouseY / scale);
    }

    public void mouseDragged() {
        pan.mouseDrag(mouseX / scale, mouseY / scale);
    }

    public void mouseReleased() {
        pan.mouseRelease(mouseX / scale, mouseY / scale);
        canZoom = true;
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