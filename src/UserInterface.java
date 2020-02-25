public class UserInterface {

    // title
    private String title = "Mount Jedi Galaxy Power Grid";  // title of grid
    private int titleSize;                             // text size of title

    private String verifyInfo = "";

    private float buffer;

    // pixel location of upper left corner of title
    private float titleX = 15;
    private float titleY = 30;

    // pixel location of upper left corner of tools
    public float toolsX;
    public float toolsY;
    private float toolsBuffer;

    // dividing line 1
    public float divPoint1Y;
    // dividing line 2
    public float divPoint2Y;
    // dividing line 3
    public float divPoint3Y;

    private String zoomIn = "+";
    private String zoomOut = "-";
    private String refresh = "r";
    private String validate = "v";
    private int buttonTextSize;

    private float titleWidth;
    private float titleHeight;
    public float toolsWidth;
    public float toolsHeight;

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
        this.toolsBuffer = screenWidth / 40;
        this.buffer = sketch.HEIGHT / 8;
        this.toolsX = screenWidth - toolsWidth - toolsBuffer;
        this.toolsY = screenHeight - toolsHeight - toolsBuffer;
        this.divPoint1Y = toolsY + toolsHeight / 4;
        this.divPoint2Y = divPoint1Y + toolsHeight / 4;
        this.divPoint3Y = divPoint2Y + toolsHeight / 4;
        this.titleSize = sketch.HEIGHT / 2 + 1;
        this.buttonTextSize = sketch.HEIGHT / 2 + 1;
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
        sketch.line(toolsX, divPoint1Y, toolsX + toolsWidth, divPoint1Y);
        sketch.line(toolsX, divPoint2Y, toolsX + toolsWidth, divPoint2Y);
        sketch.line(toolsX, divPoint3Y, toolsX + toolsWidth, divPoint3Y);
        // button text
        sketch.text(zoomIn, toolsX + (4 * buffer), toolsY + (3 * buffer), toolsX + toolsWidth, toolsY + toolsHeight);
        sketch.text(zoomOut, toolsX + (4 * buffer), divPoint1Y + (3 * buffer), toolsX + toolsWidth, toolsY + toolsHeight);
        sketch.text(refresh, toolsX + (4 * buffer), divPoint2Y + (3 * buffer), toolsX + toolsWidth, toolsY + toolsHeight);
        sketch.text(validate, toolsX + (4 * buffer), divPoint3Y + (3 * buffer), toolsX + toolsWidth, toolsY + toolsHeight);
        // verify info
        sketch.text(verifyInfo, (4 * buffer), toolsY + toolsHeight - (3 * buffer), toolsX + toolsWidth, toolsY + toolsHeight);

    }

    public void setVerifyInfo(String verifyInfo) {
        this.verifyInfo = verifyInfo;
    }

}

