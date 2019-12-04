
Pan pan = new Pan();
boolean canZoom = true;

float x;
float y;

void setup() 
{
  fullScreen();
}

void draw() {
  background(255);
  x = pan.getX();
  y = pan.getY();
  drawLine(0 + x, height/3 + y, width/3, 0, true);  //left horizontal of switch
  drawSwitch(width/3 + x, height/3 + y, 1.0, true);  //switch
  drawLine(0 + x, height/3 + y, 0, -height/3, true); //left vertical
  drawLine(width/3 + x + 128, height/3 + y, width/3, 0, true);  //right horizontal of switch
  drawLine(width/3 + x + 128 + width/3, height/3 + y, 0, -height/3, true); //right vertical
  drawLine(0 + x, y, width/3 + 768, 0, true); //top horizontal
}

void mousePressed() {
  canZoom = false;
  pan.mousePress(mouseX, mouseY);
}

void mouseDragged() {
  pan.mouseDrag(mouseX, mouseY);
}

void mouseReleased() {
  pan.mouseRelease(mouseX, mouseY);
  canZoom = true;
}

void drawLine(float x, float y, float dx, float dy, boolean energized)
{
  strokeWeight(3);
  line(x, y, x + dx, y + dy);
  if (energized)
  {
    strokeWeight(0);
    fill(#66FF33, 200);
    rect(x - 5, y - 5, dx + 10, dy + 10);
  }
}

void drawSwitch(float x, float y, float size, boolean horiz)
{
  float dx = 0;
  float dy = 0;
  if (horiz) 
  {
    dx = 50 * size;
    strokeWeight(3);
    line(x, y, x + dx, y + dy);
    x += dx;
    line(x, y - dx/2, x, y + dx/2);
    x += dx/2;
    line(x, y - dx/2, x, y + dx/2);
    line(x, y, x + dx, y + dy);
  }
  else
  {
    dy = 50 * size;
    strokeWeight(3);
    line(x, y, x + dx, y + dy);
    y += dy;
    line(x - dy/2, y, x + dy/2, y);
    y += dy/2;
    line(x - dy/2, y, x + dy/2, y);
    line(x, y, x + dx, y + dy);
  }
}
