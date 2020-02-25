public class UserInterface {

    // title
    private String title = "Mount Jedi Galaxy Power Grid";  // title of grid
    private int titleSize;                             // text size of title

    private String verifyInfo = "";

    private float buffer;

    // pixel location of upper left corner of title
    private float titleX;
    private float titleY;

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
    private int verifySize;

    private float titleWidth;
    private float titleHeight;
    public float toolsWidth;
    public float toolsHeight;

    protected SwitchBoss sketch;

    private int screenWidth;
    private int screenHeight;


    public UserInterface() {
    }

    public void setSize(SwitchBoss sketch) {
        this.sketch = sketch;
        this.screenWidth = sketch.displayWidth;
        this.screenHeight = sketch.displayHeight;

        this.titleWidth = screenWidth / 4;
        this.titleHeight = screenHeight / 20;
        this.titleSize = sketch.HEIGHT / 2;

        this.toolsWidth = screenWidth / 30;
        this.toolsHeight = screenHeight / 4;
        this.buffer = sketch.HEIGHT / 10;
        this.toolsBuffer = screenWidth / 60;

        this.titleX = toolsBuffer;
        this.titleY = toolsBuffer;
        this.toolsX = screenWidth - toolsWidth - toolsBuffer;
        this.toolsY = screenHeight - toolsHeight - toolsBuffer;
        this.divPoint1Y = toolsY + toolsHeight / 4;
        this.divPoint2Y = divPoint1Y + toolsHeight / 4;
        this.divPoint3Y = divPoint2Y + toolsHeight / 4;

        this.buttonTextSize = titleSize;
        this.verifySize = (int)(titleSize / 1.5);
    }

    public void draw() {
        // title box
        sketch.stroke(0);
        sketch.fill(255);
        sketch.strokeWeight(1);
        sketch.rect(titleX, titleY, titleWidth, titleHeight);
        sketch.fill(0);
        sketch.textSize(titleSize);
        sketch.text(title, titleX + (4 * buffer), titleY + (2 * buffer), titleX + titleWidth, titleY + titleHeight);

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
        sketch.textSize(verifySize);
        sketch.text(verifyInfo, (4 * buffer), toolsY + toolsHeight - (3 * buffer), toolsX + toolsWidth, toolsY + toolsHeight);

    }

    public void setVerifyInfo(String verifyInfo) {
        this.verifyInfo = verifyInfo;
    }

}

