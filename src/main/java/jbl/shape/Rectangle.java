
package jbl.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public  class Rectangle extends FillableShape{
    public double width;
    public double height;


    public Rectangle() {
        super();
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

  public void paint(GraphicsContext gc){
      if (isFilled()) {
          gc.setFill(getColor());
          gc.fillRect(getX(), getY(), width, height);
      } else {
          gc.setStroke(getColor());
          gc.strokeRect(getX(), getY(), width, height);
      }
  }
    @Override
    public void constrain(double minX, double minY, double maxX, double maxY) {
        double x = getX();
        double y = getY();
        double dx = getDx();
        double dy = getDy();
        if (x + width > maxX) {
            x = maxX - width;
            dx = -dx;
            if (x < minX) {
                x = minX;
                dx = -dx;
            }
            // botten
            if (y + height > maxY) {
                y = maxY - height;
                dy = -dy;
            }
            // toppen
            if (y < minY) {
                y = minY;
                dy = -dy;
            }
        }

    }






}
