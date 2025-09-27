

package jbl.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends FillableShape {

    private double diameter;

    public Circle(double x, double y, double diameter, Color color, boolean fillable){
        super(x , y, color, fillable);
        this.diameter = diameter;


    }


    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    @Override
    public void paint(GraphicsContext gc) {
        if (isFilled()) {
            gc.setFill(getColor());
            gc.fillOval(getX(), getY(), diameter, diameter);
        } else {
            gc.setStroke(getColor());
            gc.strokeOval(getX(), getY(), diameter, diameter);
        }
    }



    //javaFX anger position övre vänstra hörnet
    @Override
    protected void constrain(double minX, double minY, double maxX, double maxY) {
        double x  = getX();
        double y  = getY();
        double dx = getDx();
        double dy = getDy();

        if (x < minX) {//vänster
            x = minX;
            dx = Math.abs(dx);
        }

        else if (x + diameter > maxX) {//höger
            x = maxX - diameter;
            dx = -Math.abs(dx);
        }

        if (y < minY) {//topp
            y = minY;
            dy = Math.abs(dy);
        }
        else if (y + diameter > maxY) {
            y = maxY - diameter;   // Bottt
            dy = -Math.abs(dy);
        }

        setX(x);
        setY(y);
        setVelocity(dx, dy);
    }


    @Override
    public String toString() {
        return "Circle{" +
                "diameter=" + diameter +
                '}';
    }
}

