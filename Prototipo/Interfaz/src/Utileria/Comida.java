package Utileria;

import java.io.Serializable;

public class Comida implements Serializable {
    
    // private static final long SerialVersionUID = 9080898209342823102L;
    Point point;
    
    public Comida (Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
    
}