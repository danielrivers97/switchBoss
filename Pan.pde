public class Pan {
  float x;
  float y;
  float startX;
  float startY;
  float changeX;
  float changeY;
  
  public Pan() {
    this.x = 0;
    this.y = 0;
    this.startX = 0;
    this.startY = 0;
    this.changeX = 0;
    this.changeY = 0;
  }
  
  public float getX() {
    if (this.changeX == 0)
    {
      return this.x;
    }
    else
    {
      return this.x + this.changeX;
    }
  
  }
  
  public float getY() {
    if (this.changeY == 0)
    {
      return this.y;
    }
    else
    {
      return this.y + this.changeY;
    }
  
  }
  
  public void mousePress(float startX, float startY) {
    this.startX = startX;
    this.startY = startY;
    this.changeX = 0;
    this.changeY = 0;
  }
  
  public void mouseDrag(float x, float y) {
    this.changeX = x - this.startX;
    this.changeY = y - this.startY;
  }
  
  public void mouseRelease(float x, float y) {
    this.x += x - this.startX;
    this.y += y - this.startY;
    this.startX = 0;
    this.startY = 0;
    this.changeX = 0;
    this.changeY = 0;
  }
}