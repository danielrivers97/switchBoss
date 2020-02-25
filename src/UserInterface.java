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
    private float toolsY = 570;

    // dividing line 1
    private float divPoint1X = 1450;
    private float divPoint1Y = 620;
    private float divPoint2X = 1503;
    private float divPoint2Y = 620;
    // dividing line 2
    private float divPoint3X = 1450;
    private float divPoint3Y = 670;
    private float divPoint4X = 1503;
    private float divPoint4Y = 670;
    // dividing line 3
    private float divPoint5X = 1450;
    private float divPoint5Y = 720;
    private float divPoint6X = 1503;
    private float divPoint6Y = 720;

    private String zoomIn = "+";
    private String zoomOut = "-";
    private String refresh = "R";
    private String validate = "V";
    private int buttonTextSize = 21;

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
        this.toolsHeight = screenHeight / 4;
    }

    public void draw() {
        // title box
        sketch.stroke(0);
        sketch.fill(255);
        sketch.strokeWeight(1);
        sketch.rect(titleX, titleY, titleWidth, titleHeight);
        sketch.fill(0);
        sketch.textSize(titleSize);
        sketch.text(title, titleX + buffer, titleY + buffer, titleX + titleWidth, titleY + titleHeight);

        // tools window
        sketch.stroke(0);
        sketch.fill(255);
        sketch.strokeWeight(1);
        sketch.rect(toolsX, toolsY, toolsWidth, toolsHeight);
        sketch.fill(0);
        // dividing lines
        sketch.line(divPoint1X, divPoint1Y, divPoint2X, divPoint2Y);
        sketch.line(divPoint3X, divPoint3Y, divPoint4X, divPoint4Y);
        sketch.line(divPoint5X, divPoint5Y, divPoint6X, divPoint6Y);
        // button text
        sketch.text(zoomIn, toolsX + (4 * buffer), toolsY + (3 * buffer), toolsX + toolsWidth, toolsY + toolsHeight);
        sketch.text(zoomOut, divPoint1X + (4 * buffer), divPoint1Y + (3 * buffer), toolsX + toolsWidth, toolsY + toolsHeight);
        sketch.text(refresh, divPoint3X + (4 * buffer), divPoint3Y + (3 * buffer), toolsX + toolsWidth, toolsY + toolsHeight);
        sketch.text(validate, divPoint5X + (4 * buffer), divPoint5Y + (3 * buffer), toolsX + toolsWidth, toolsY + toolsHeight);

    }

}

