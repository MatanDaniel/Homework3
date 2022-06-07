import java.awt.*;
import java.util.HashSet;
import java.util.concurrent.CyclicBarrier;

/**
 * * @author Matan Daniel, 315783522 and Ron Bar-Zvi, 304969520
 */

public abstract class Swimmable extends Thread implements SeaCreature, Subject,Cloneable {

    protected volatile boolean running=true;
    protected volatile boolean paused=false;
    protected final Object pauseLock=new Object();
    protected int x_front, y_front, x_dir, y_dir, foodFreq,freqCount=0;
    public final int objectID;
    private static int counter=0;

    /**
     * horizontal speed
     */
    protected int horSpeed;
    /**
     * vertical speed
     */
    protected int verSpeed;
    protected CyclicBarrier cb;

    /**
     * Default constructor
     */
    public Swimmable() {
        horSpeed = 0;
        verSpeed = 0;
        this.objectID=++counter;
    }

    /**
     * converts animal properties in string format
     *
     * @return animal properties in string format
     */
    public String toString() {
        return String.format("%-16s|%-10s|%-10d|%-10d", this.getAnimalName(), this.getColor(), this.getSize(),
                this.getEatCount());
    }

    /**
     * constructor given parameters
     *
     * @param horSpeed
     * @param verSpeed
     */
    public Swimmable(int horSpeed, int verSpeed) {
        this.horSpeed = horSpeed;
        this.verSpeed = verSpeed;
        this.foodFreq=foodFreq;
        this.objectID=++counter;
    }

    /**
     * get horizontal speed
     *
     * @return horizontal speed
     */
    public int getHorSpeed() {
        return horSpeed;
    }

    /**
     * set Horizontal speed
     *
     * @param horSpeed Horizontal speed
     */
    public void setHorSpeed(int horSpeed) {
        this.horSpeed = horSpeed;
    }

    /**
     * get vertical speed
     *
     * @return vertical speed
     */
    public int getVerSpeed() {
        return verSpeed;
    }

    /**
     * set vertical speed
     *
     * @param verSpeed Vertical speed
     */
    public void setVerSpeed(int verSpeed) {
        this.verSpeed = verSpeed;
    }

    /**
     * gets animal name
     *
     * @return animal name
     */
    public abstract String getAnimalName();

    /**
     * gets value of how much an animal has ate
     *
     * @return how much an animal has ate
     */
    public abstract int getEatCount();

    /**
     * getter of animal size
     *
     * @return animal size
     */
    public abstract int getSize();

    /**
     * getter of animal color
     *
     * @return animal color
     */
    public abstract Color getColor();

    /**
     * increases amount that an animal has ate by 1
     */
    public abstract void eatInc();

    /**
     * compares size between current animal and received animal object
     *
     * @param other
     * @return size difference (current minus other)
     */
    public int compareTo(Swimmable other) {
        return this.getSize() - other.getSize();
    }

    /**
     * Because of the onion principle if we make equals only in the base class, it
     * will be applied to all
     * derived classes.
     *
     * @param swimmable
     * @return returns whether horSpeed equals to received object's, as well as for
     *         verSpeed
     */
    public boolean equals(Swimmable swimmable) {
        return this.horSpeed == swimmable.getHorSpeed() && this.verSpeed == swimmable.getVerSpeed();
    }

    abstract public  void  setSuspend();

    abstract public void setResume();

    abstract public void setBarrier(CyclicBarrier b);

    abstract public void reset();

    abstract public void setColor(Color col);

    abstract public void update();

    abstract public Swimmable clone();
    abstract public void editSwimmable(int size, int x, int y,int horSpeed,int verSpeed,Color col);
    public int getID(){return objectID;}

    abstract public void setState(Color col,int size,int x_front,int y_front,int horSpeed,int verSpeed,int x_dir,int y_dir);
    public int getX_front(){return x_front;};
    public int getY_front(){return y_front;};

    abstract public int getX_dir();

    abstract public int getY_dir();

}
