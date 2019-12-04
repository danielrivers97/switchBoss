
import java.util.*;

Pan pan = new Pan();
boolean canZoom = true;

BufferedReader reader;
List<String[]> components = new ArrayList<String[]>();

void setup() 
{
  fullScreen();
  reader = createReader("positions.txt");   
  String line = "";
  while(line != null)
  {
    try {
      line = reader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
      line = null;
    }
    if (line == null) {
      // Stop reading because of an error or file is empty
      break;
    } else {
      String[] component = split(line, " ");
      components.add(component);
    }
  }
}

void draw() {
  background(255);
  float offX = pan.getX();
  float offY = pan.getY();
  drawComponents(offX, offY);
}

void drawComponents(float offX, float offY)
{
  for (String[] component : components) {
    String type = component[0];
    int x1 = int(component[1]);
    int y1 = int(component[2]);
    int x2 = int(component[3]);
    int y2 = int(component[4]);
    if (type.equals("LINE"))
    {
      drawLine(x1 + offX, y1 + offY, x2 + offX, y2 + offY);
    }
    else if (type.equals("SW"))
    {
      drawSwitch(x1 + offX, y1 + offY, x2 + offX, y2 + offY);
    }
    else if (type.equals("CB"))
    {
      drawCircuitBreaker(x1 + offX, y1 + offY, x2 + offX, y2 + offY);
    }
    else if (type.equals("TRANSF"))
    {
      drawTransformer(x1 + offX, y1 + offY, x2 + offX, y2 + offY);
    }
    else
    {
      continue;
    }
    //highlight(x1 + offX, y1 + offY, x2 + offX, y2 + offY);
  }
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

void highlight(float x, float y, float x2, float y2)
{
  float dx = x2 - x;
  float dy = y2 - y;
  strokeWeight(0);
  fill(#66FF33, 200);
  rect(x - 5, y - 5, dx + 10, dy + 10);
}

void drawLine(float x, float y, float x2, float y2)
{
  strokeWeight(3);
  line(x, y, x2, y2);
}

void drawTransformer(float x, float y, float x2, float y2)
{
  float dx = abs(x - x2);
  
  strokeWeight(3);
  line(x, y, x2, y2);
  
  noFill();
  if (dx > 0)
  {
    /* horizontal TRANSF */
    arc(x2, y - 18, 12, 12, 0, PI, OPEN);
    arc(x2, y - 6, 12, 12, 0, PI, OPEN);
    arc(x2, y + 6, 12, 12, 0, PI, OPEN);
    arc(x2, y + 18, 12, 12, 0, PI, OPEN);
  }
  else
  {
    /* vertical TRANSF */
    arc(x - 18, y2, 12, 12, 0, PI, OPEN);
    arc(x - 6, y2, 12, 12, 0, PI, OPEN);
    arc(x + 6, y2, 12, 12, 0, PI, OPEN);
    arc(x + 18, y2, 12, 12, 0, PI, OPEN);
  }
}

void drawCircuitBreaker(float x, float y, float x2, float y2)
{
  float dx = abs(x - x2);
  
  strokeWeight(3);
  noFill();
  if (dx > 0)
  {
    /* horizontal CB */
    line(x, y, x + 16, y);
    rect(x + 16, y - 24, 48, 48);
    line(x + 64, y, x + 80, y);
  }
  else
  {
    /* vertical CB */
    line(x, y, x, y + 16);
    rect(x - 24, y + 16, 48, 48);
    line(x, y + 64, x, y + 80);
  }
}

void drawSwitch(float x, float y, float x2, float y2)
{
  float dx = 0;
  float dy = 0;
  float size = 1.0; /* change with zoom */
  float horiz = abs(x - x2);
  if (horiz > 0) 
  {
    dx = 32 * size;
    strokeWeight(3);
    line(x, y, x + dx, y);
    x += dx;
    line(x, y - 24, x, y + 24);
    x += dx/2;
    line(x, y - 24, x, y + 24);
    line(x, y, x + dx, y);
  }
  else
  {
    dy = 32 * size;
    strokeWeight(3);
    line(x, y, x + dx, y + dy);
    y += dy;
    line(x - 24, y, x + 24, y);
    y += dy/2;
    line(x - 24, y, x + 24, y);
    line(x, y, x + dx, y + dy);
  }
}