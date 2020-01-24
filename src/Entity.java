import processing.core.PApplet;

public interface Entity {

    public void render(float scale, int panX, int panY);
    public void update();
    public PApplet getSketch();
    public int getX();
    public int getY();
}
