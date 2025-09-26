
package jbl.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public abstract class FillableShape extends Shape {
   private boolean filled;

    public FillableShape(double x, double y, Color color, boolean filled) {
        super(x, y, color);
        this.filled = filled;
    }

    public boolean isFilled(){
        return filled;
    }
    public void setFilled(boolean filled){
        this.filled=filled;
    }


}
