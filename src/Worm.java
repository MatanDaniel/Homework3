import java.awt.*;

public class Worm {
    public boolean foodPlaced =false;
    private Worm() {
        instance = this;
    }

    private static Worm instance;

    public static Worm getInstance() {
        if (instance == null) return new Worm();
        return instance;
    }

    public boolean isNearFood(Graphics g, Swimmable swimmable) { // checks if swimmable object reached near the
        //food object
        int x1 = g.getClipBounds().width / 2, y1 = g.getClipBounds().height / 2;

        int x2 = swimmable.x_front, y2 = swimmable.y_front;

        var distance_squared = (x2 - x1)^2 + (y2 -y1)^2;
        return Math.sqrt(distance_squared) <= 3;
    }


    public void setFoodPlaced(boolean foodPlaced) {
        this.foodPlaced = foodPlaced;
    }

    public static void drawAnimal(Graphics g, AquaPanel panel){ // draw animal of Worm makes sure the Worm
        //will be created in the middle of the screen
        Graphics2D g2=(Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.MAGENTA);
        g2.drawArc(panel.getWidth()/2,panel.getHeight()/2-5,10,10,30,210);
        g2.drawArc(panel.getWidth()/2,panel.getHeight()/2+5,10,10,180,270);
        g2.setStroke(new BasicStroke(1));
    }

}
