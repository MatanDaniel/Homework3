import java.awt.*;

public class Laminaria extends Immobile {

    public Laminaria(int size, int coordinate_x, int coordinate_y) {
        super(size, coordinate_x, coordinate_y);
    }

    @Override // Implementing interface's method and drawing a creature
        public void drawCreature(Graphics g) {
            g.setColor(this.getColor()); //set the color
            g.fillArc(this.getCoordinate_x()-this.getSize()/20, this.getCoordinate_y()-this.getSize(), this.getSize()/10, this.getSize()*4/5, 0, 360);
            g.fillArc(this.getCoordinate_x()-this.getSize()*3/20, this.getCoordinate_y()-this.getSize()*13/15, this.getSize()/10, this.getSize()*2/3, 0, 360);
            g.fillArc(this.getCoordinate_x()+this.getSize()/20, this.getCoordinate_y()-this.getSize()*13/15, this.getSize()/10, this.getSize()*2/3, 0, 360);
            g.drawLine(this.getCoordinate_x(), this.getCoordinate_y(), this.getCoordinate_x(), this.getCoordinate_y()-this.getSize()/5);
            g.drawLine(this.getCoordinate_x(), this.getCoordinate_y(), this.getCoordinate_x()+this.getSize()/10, this.getCoordinate_y()-this.getSize()/5);
        }

    }

