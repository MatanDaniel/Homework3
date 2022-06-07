import java.awt.*;
import java.util.concurrent.CyclicBarrier;

/**
 * * @author Matan Daniel, 315783522 and Ron Bar-Zvi, 304969520
 */
public class Jellyfish extends Swimmable implements MarineAnimal {
    private static final int EAT_DISTANCE = 4;
    private int size;
    private int eatCount;
    private Color col;
    private HungerState state;
    private Hunger hungry;
    private Satiated full;

    /**
     * constructor given params
     *
     * @param size     base size of jellyfish
     * @param x_front  future use
     * @param y_front  future use
     * @param horSpeed horizontal speed
     * @param verSpeed vertical speed
     * @param col      color of jellyfish
     */
    public Jellyfish(int size, int x_front, int y_front, int horSpeed, int verSpeed, Color col) {
        super(horSpeed, verSpeed);
        this.size = size;
        this.x_front = x_front;
        this.y_front = y_front;
        this.col = col;
        eatCount = 0;
        y_dir = 1;
        x_dir = 1;
    }

    // Getters for each field:

    /**
     * getter
     *
     * @return amount of food a jellyfish can eat
     */
    public int getEAT_DISTANCE() {
        return EAT_DISTANCE;
    }

    // TODO: override the run method of thread

    @Override
    public int getSize() {
        return size;
    }

    public Color getColor() {
        return col;
    }

    public void eatInc() {
        eatCount++;
        if (eatCount > EAT_DISTANCE) {
            size++;
            eatCount = 0;
        }
    }

    @Override
    public void run() {
        while (running) {
            synchronized (pauseLock) { // Critical part of the run method
                if (!running) {
                    break;
                }
                if (paused) {
                    try {
                        pauseLock.wait();
                    } catch (InterruptedException e) {
                        break;
                    }
                    if (!running) {
                        break;
                    }
                }
            }

            if (Worm.getInstance().foodPlaced){
                try {
                    cb.await();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            moveJellyFish();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void moveJellyFish() {
        setX_front(x_front + horSpeed * x_dir);
        setY_front(y_front + verSpeed * y_dir);
    }


    @Override
    public String getAnimalName() {
        return "Jellyfish";
    }

    /**
     * getter
     */
    @Override
    public int getEatCount() {
        return eatCount;
    }

    /**
     * getter
     */
    public int getX_front() {
        return x_front;
    }

    /**
     * getter
     */
    public int getY_front() {
        return y_front;
    }

    /**
     * getter
     */
    public int getX_dir() {
        return x_dir;
    }

    /**
     * getter
     */
    public int getY_dir() {
        return y_dir;
    }

    // Setters for each field

    /**
     * setter
     *
     * @param size base size of jellyfish
     */
    public boolean setSize(int size) {
        this.size = size;
        return true;
    }

    /**
     * setter
     *
     * @param col color of jellyfish
     */
    public void setCol(Color col) {
        this.col = col;
    }

    /**
     * setter
     *
     * @param eatCount how much a jellyfish can eat
     */
    public void setEatCount(int eatCount) {
        this.eatCount = eatCount;
    }

    /**
     * setter future use
     *
     * @param x_front
     */
    public boolean setX_front(int x_front) {
        this.x_front = x_front;
        return true;
    }

    /**
     * setter future use
     *
     * @param y_front
     */
    public boolean setY_front(int y_front) {
        this.y_front = y_front;
        return true;
    }

    /**
     * setter future use
     *
     * @param x_dir
     */
    public boolean setX_dir(int x_dir) {
        this.x_dir = x_dir;
        return true;
    }

    /**
     * setter
     *
     * @param y_dir
     */
    public boolean setY_dir(int y_dir) {
        this.y_dir = y_dir;
        return true;
    }

    /**
     * change size of jellyfish
     *
     * @return new size
     */
    public int changeJellyFish() {
        if (eatCount == EAT_DISTANCE)
            size++;
        return size;
    }

    public void setSuspend() {
        paused=true;
    }

    @Override
    public void setResume() {
        synchronized (pauseLock){
            paused=false;
            pauseLock.notifyAll();
        }
    }

    @Override
    public void setBarrier(CyclicBarrier b) {
        this.cb=b;
    }

    @Override
    public void reset() {running =false;
    }

    @Override
    public void setColor(Color col) {
        this.col = col;
    }

    @Override
    public void update() {

    }

    @Override
    public void setState(Color col, int size, int x_front, int y_front, int horSpeed, int verSpeed,int x_dir,int y_dir) {
        this.col=col;
        this.size=size;
        this.x_front=x_front;
        this.y_front=y_front;
        this.horSpeed=horSpeed;
        this.verSpeed=verSpeed;
        this.x_dir=x_dir;
        this.y_dir=y_dir;

    }
    @Override
    public void drawCreature(Graphics g) {
        int numLegs;
        if (size < 40)
            numLegs = 5;
        else if (size < 80)
            numLegs = 9;
        else
            numLegs = 12;
        g.setColor(col);
        g.fillArc(x_front - size / 2, y_front - size / 4, size, size / 2, 0, 180);
        for (int i = 0; i < numLegs; i++)
            g.drawLine(x_front - size / 2 + size / numLegs + size * i / (numLegs + 1),
                    y_front, x_front - size / 2 + size / numLegs + size * i / (numLegs + 1),
                    y_front + size / 3);

        if (x_front >= g.getClipBounds().width) x_dir = -1;
        if (x_front <= 0) x_dir = 1;


        if (y_front - size/2 >= g.getClipBounds().height) y_dir = -1;
        if (y_front + size/2 <= 0) y_dir = 1;

        if (Worm.getInstance().isNearFood(g, this)) {
            Worm.getInstance().setFoodPlaced(false);
            eatInc();
        }
    }


    @Override // Decorator DP:
    public void PaintFish(Color col){
        new MarineAnimalDecorator(this).PaintFish(col);
    }

    @Override
    public HungerState getHungerState() {
        return this.state;
    }

    @Override
    public void setHungerState(HungerState hungerState) {
        this.state = hungerState;
    }

    @Override
    public void attach() {

    }

    public Jellyfish clone() {
        return new Jellyfish(size,x_front,y_front,horSpeed,verSpeed,col);
    }

    @Override
    public void editSwimmable(int size, int x, int y, int horSpeed, int verSpeed, Color col) {
        this.size=size;
        this.horSpeed=horSpeed;
        this.verSpeed=verSpeed;
        this.x_front=x;
        this.y_front=y;
        this.col=col;
    }

    @Override
    public void notifyAllObservers() {
        for(Observer x : observers){
            x.update(getAnimalName());
        }
    }
}
