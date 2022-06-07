import java.awt.*;

public abstract class Immobile implements SeaCreature{
    protected String name;
    protected Color color = Color.GREEN;
    protected int size;
    protected int Coordinate_x;
    protected int Coordinate_y;

    public Immobile(int size, int coordinate_x, int coordinate_y) {
        this.size = size;
        Coordinate_x = coordinate_x;
        Coordinate_y = coordinate_y;
    }

    public void setCoordinate_x(int coordinate_x) {
        Coordinate_x = coordinate_x;
    }

    public void setCoordinate_y(int coordinate_y) {
        Coordinate_y = coordinate_y;
    }



    public int getCoordinate_x() {
        return Coordinate_x;
    }

    public int getCoordinate_y() {
        return Coordinate_y;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void drawCreature(Graphics g)  {
    }

    public String getName() {
        return name;
    }

    public void setName(String immobilename){
        this.name = immobilename;
    }
}
