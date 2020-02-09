import java.util.ArrayList;

import processing.core.PApplet;
import processing.event.*;

public class SwitchBoss extends PApplet {

    public ArrayList<Component> components = new ArrayList<>(); // keep track of all components

    Viewport viewport = new Viewport();
    Click click = new Click();

    public boolean canPan = true;
    public boolean canZoom = true;

    public float scale = 1;

    public void settings() {
        size(1000, 750);
        viewport.setSize(width, height);

        components.add(new Switch(this, 200, 200, "switch", 0, 0));
        components.add(new Switch(this, 400, 100, "switch2", 1, 1));
        components.add(new Breaker(this, 500, 500, "breaker1", 0, 0));
        components.add(new Breaker(this, 500, 300, "breaker2", 1, 1));
    }

    public void draw() {
        background(0xFFFFFF);
        for (Component c : components) {
            c.render(viewport.getScale(), (int) viewport.getX(), (int) viewport.getY());
        }
    }

    public void mousePressed() {
        canZoom = false;
        click.mousePress(components, mouseX, mouseY, viewport.getScale(), (int)viewport.getX(), (int)viewport.getY());
        viewport.mousePress(mouseX / scale, mouseY / scale);
    }

    public void mouseDragged() {
        viewport.mouseDrag(mouseX / scale, mouseY / scale);
    }

    public void mouseReleased() {
        viewport.mouseRelease();
        canZoom = true;
    }

    public void mouseWheel(MouseEvent event) {
        float count = event.getCount();
        viewport.setScale(count);
    }

    public static void main(String[] args) {
        String[] processingArgs = {"SwitchBoss"};
        SwitchBoss mySketch = new SwitchBoss();

        PApplet.runSketch(processingArgs, mySketch);
    }
}