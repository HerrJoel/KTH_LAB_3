

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

    public void constrain(double Xmin, double Ymin, double Xmax, double Ymax){
        //behöver paint logicen för att förstå hur begränsningarna ser ut

    }

    @Override
    public String toString() {
        return "Circle{" +
                "diameter=" + diameter +
                '}';
    }
}

