
package jbl.delA.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public  class Rectangle extends FillableShape{
    private double width;
    private double height;


    public Rectangle(double x, double y, double width, double height, Color color, boolean filled) {
        super(x, y, color, filled);
        this.width=width;
        this.height=height;
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
    @Override
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
        }

        if (x < minX) {
            x = minX;
            dx = -dx;
        }

        if (y + height > maxY) {
            y = maxY - height;
            dy = -dy;
        }

        if (y < minY) {
            y = minY;
            dy = -dy;
        }
            setX(x);
            setY(y);
            setVelocity(dx, dy);



        }

    }







