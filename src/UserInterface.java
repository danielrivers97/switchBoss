public class UserInterface {

    // title
    private String title = "Mount Jedi Galaxy Power Grid";  // title of grid
    private int titleSize = 21;                             // text size of title

    private float buffer = 5;

    // pixel location of upper left corner of title
    private float titleX = 15;
    private float titleY = 30;

    private float titleWidth;
    private float titleHeight;

    protected SwitchBoss sketch;

    private int screenWidth;
    private int screenHeight;


    public UserInterface(SwitchBoss sketch) {
        this.sketch = sketch;
        this.screenWidth = sketch.WIDTH * sketch.UNIT;
        this.screenHeight = sketch.HEIGHT * sketch.UNIT;
        this.titleWidth = screenWidth / 5;
        this.titleHeight = screenHeight / 20;
    }

    public void draw() {
        sketch.stroke(0);
        sketch.fill(255);
        sketch.strokeWeight(1);
        sketch.rect(titleX, titleY, titleWidth, titleHeight);
        sketch.fill(0);
        sketch.textSize(titleSize);
        sketch.text(title, titleX + buffer, titleY + buffer, titleX + titleWidth, titleY + titleHeight);

    }

}

