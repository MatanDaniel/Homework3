import java.awt.*;
public class Zostera extends Immobile {

    public Zostera(int size, int coordinate_x, int coordinate_y) {
        super(size, coordinate_x, coordinate_y);
    }

    @Override
  public void drawCreature(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(3));
        g2.setColor(this.getColor());
        g.drawLine(this.getCoordinate_x(), this.getCoordinate_y(), this.getCoordinate_x(), this.getCoordinate_y() - this.getSize());
        g.drawLine(this.getCoordinate_x()-2, this.getCoordinate_y(), this.getCoordinate_x()-10, this.getCoordinate_y()-this.getSize()*9/10);
        g.drawLine(this.getCoordinate_x()+2, this.getCoordinate_y(), this.getCoordinate_x()+10, this.getCoordinate_y()-this.getSize()*9/10);
        g.drawLine(this.getCoordinate_x()-4, this.getCoordinate_y(), this.getCoordinate_x()-20, this.getCoordinate_y()-this.getSize()*4/5);
        g.drawLine(this.getCoordinate_x()+4, this.getCoordinate_y(), this.getCoordinate_x()+20, this.getCoordinate_y()-this.getSize()*4/5);
        g.drawLine(this.getCoordinate_x()-6, this.getCoordinate_y(), this.getCoordinate_x()-30, this.getCoordinate_y()-this.getSize()*7/10);
        g.drawLine(this.getCoordinate_x()+6, this.getCoordinate_y(), this.getCoordinate_x()+30, this.getCoordinate_y()-this.getSize()*7/10);
        g.drawLine(this.getCoordinate_x()-8, this.getCoordinate_y(), this.getCoordinate_x()-40, this.getCoordinate_y()-this.getSize()*4/7);
        g.drawLine(this.getCoordinate_x()+8, this.getCoordinate_y(), this.getCoordinate_x()+40, this.getCoordinate_y()-this.getSize()*4/7);
        g2.setStroke(new BasicStroke(1));
    }
}
