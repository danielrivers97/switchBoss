public class Viewport {

    // panning variables
    float x;
    float y;
    float startX;
    float startY;
    float changeX;
    float changeY;

    // width and height of screen in pixels
    float width;
    float height;

    // viewport scale
    float scale;

    // viewport midpoint
    float midX;
    float midY;

    public Viewport() {
        this.x = 0;
        this.y = 0;
        this.startX = 0;
        this.startY = 0;
        this.changeX = 0;
        this.changeY = 0;
        this.width = 0;
        this.height = 0;
        this.scale = .3f;
    }

    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
        this.midX = width / 2;
        this.midY = height / 2;
    }

    public float getX() {
        return (this.x + this.changeX) + (1 / this.scale) * (this.midX);
    }

    public float getY() {
        return (this.y + this.changeY) + (1 / this.scale) * (this.midY);
    }

    public float getScale() {
        return this.scale;
    }

    public void setScale(float count) {
        float zoomRatio = 100f;
        this.scale += -count / zoomRatio;
        if (this.scale < 0.1) {
            this.scale = 0.1f;
        }
    }

    public void mousePress(float startX, float startY) {
        this.startX = startX;
        this.startY = startY;
        this.changeX = 0;
        this.changeY = 0;
    }

    public void mouseDrag(float x, float y) {
        this.changeX = (1 / this.scale) * (x - this.startX);
        this.changeY = (1 / this.scale) * (y - this.startY);
    }

    public void mouseRelease() {
        this.x += this.changeX;
        this.y += this.changeY;
        this.startX = 0;
        this.startY = 0;
        this.changeX = 0;
        this.changeY = 0;
    }
}