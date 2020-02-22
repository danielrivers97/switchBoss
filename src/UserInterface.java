public class UserInterface {

    // title
    private String title = "Mount Jedi Galaxy Power Grid";  // title of grid
    private int titleSize = 21;                             // text size of title

    private float buffer = 5;

    // pixel location of upper left corner of title
    private float titleX = 15;
    private float titleY = 30;

    // pixel location of lower right corner
    private float toolsX = 1450;
    private float toolsY = 600;

    private float titleWidth;
    private float titleHeight;
    private float toolsWidth;
    private float toolsHeight;

    protected SwitchBoss sketch;

    private int screenWidth;
    private int screenHeight;


    public UserInterface(SwitchBoss sketch) {
        this.sketch = sketch;
        this.screenWidth = sketch.WIDTH * sketch.UNIT;
        this.screenHeight = sketch.HEIGHT * sketch.UNIT;
        this.titleWidth = screenWidth / 5;
        this.titleHeight = screenHeight / 20;
        this.toolsWidth = screenWidth / 30;
        this.toolsHeight = screenHeight / 5;
    }

    public void draw() {
        sketch.stroke(0);
        sketch.fill(255);
        sketch.strokeWeight(1);
        sketch.rect(titleX, titleY, titleWidth, titleHeight);
        sketch.fill(0);
        sketch.textSize(titleSize);
        sketch.text(title, titleX + buffer, titleY + buffer, titleX + titleWidth, titleY + titleHeight);

        sketch.stroke(0);
        sketch.fill(255);
        sketch.strokeWeight(1);
        sketch.rect(toolsX, toolsY, toolsWidth, toolsHeight);
        sketch.fill(0);

    }

}

