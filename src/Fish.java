import java.awt.*;
import java.util.concurrent.CyclicBarrier;

public class Fish extends Swimmable implements MarineAnimal {
    private static final int EAT_DISTANCE = 4;
    private int size;
    private Color col;
    private int eatCount;
    private HungerState state;
    private Hunger hungry;
    private Satiated full;


    private int foodX, foodY;
    /**
     * default constructor
     */
    public Fish() {
        super();
    }

    /**
     * constructor given parameters
     *
     * @param size     base size of fish
     * @param x_front  for future use
     * @param y_front  for future use
     * @param horSpeed horizontal speed
     * @param verSpeed vertical speed
     * @param col      fish color
     */
    public Fish(int size, int x_front, int y_front, int horSpeed, int verSpeed, Color col) {
        super(horSpeed, verSpeed);
        this.size = size;
        this.x_front = x_front;
        this.y_front = y_front;
        this.verSpeed = verSpeed;
        this.col = col;
        this.y_dir = 1;
        this.x_dir = 1;
        this.eatCount = 0;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (pauseLock) {  // Critical part of the run method
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

            moveFish();

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void moveFish() {
        setX_front(x_front + horSpeed * x_dir);
        setY_front(y_front + verSpeed * y_dir);


    }

    public String getAnimalName() {
        return "Fish";
    }

    public int getEatCount() {
        return this.eatCount;
    }

    public int getSize() {
        return this.size;
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
    public void eatInc() {
        eatCount++;
        if (eatCount >= EAT_DISTANCE) {
            size++;
            eatCount = 0;
        }
    }

    /**
     * getter of how much a fish can eat
     *
     * @return how much a fish can eat
     */
    public int getEAT_DISTANCE() {
        return EAT_DISTANCE;
    }

    /**
     * setter of base size of fish
     *
     * @param size base size of fish
     */
    public boolean setSize(int size) {
        this.size = size;
        return true;
    }

    /**
     * getter
     *
     * @return color of fish
     */
    public Color getCol() {
        return col;
    }




    /**
     * setter
     *
     * @param eatCount how much a fish ate
     * @return true if successful, otherwise false
     */
    public void setEatCount(int eatCount) {
        this.eatCount = eatCount;
    }

    /**
     * getter
     *
     * @return x position on screen
     */
    public int getX_front() {
        return x_front;
    }

    /**
     *
     * @param x_front
     */
    public boolean setX_front(int x_front) {
        this.x_front = x_front;
        return true;
    }

    /**
     * getter
     *
     */
    public int getY_front() {
        return y_front;
    }

    /**
     * setter (
     *
     * @param y_front
     */
    public boolean setY_front(int y_front) {
        this.y_front = y_front;
        return true;
    }

    /**
     * getter (future use)
     *
     * @return future use
     */
    public int getX_dir() {
        return x_dir;
    }

    /**
     * setter (future use)
     *
     * @param x_dir
     * @return future use
     */
    public boolean setX_dir(int x_dir) {
        this.x_dir = x_dir;
        return true;
    }

    /**
     * getter future use
     *
     * @return future use
     */
    public int getY_dir() {
        return y_dir;
    }

    /**
     * setter (future use)
     *
     * @param y_dir
     * @return future use
     */
    public boolean setY_dir(int y_dir) {
        this.y_dir = y_dir;
        return true;
    }

    /**
     * change size of fish
     *
     * @return new size of fish
     */
    public int changeFish() {
        if (eatCount == EAT_DISTANCE)
            size++;
        return size;
    }

    /**
     * changes color of fish in a circular form(9-->1)
     */
    public void changeColor() {
        // if (col < 9)
        // setCol(col + 1);
        // else
        // setCol(1);

    }


    @Override
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
    public Color getColor() {
        return col;
    }

    @Override
    public void reset() {running = false;}

    @Override
    public void setColor(Color col) {
        this.col = col;
    }

    @Override
    public void update() {
    }

    @Override
    public void drawCreature(Graphics g) {
        g.setColor(col);
        if (x_dir == 1) // fish swims to right side
        {
            // Body of fish
            g.fillOval(x_front - size, y_front - size / 4, size, size / 2);
            // Tail of fish
            int[] x_t = { x_front - size - size / 4, x_front - size - size / 4, x_front - size };
            int[] y_t = { y_front - size / 4, y_front + size / 4, y_front };
            Polygon t = new Polygon(x_t, y_t, 3);
            g.fillPolygon(t);
            // Eye of fish
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(new Color(255 - col.getRed(), 255 - col.getGreen(), 255 -
                    col.getBlue()));
            g2.fillOval(x_front - size / 5, y_front - size / 10, size / 10, size / 10);
            // Mouth of fish
            if (size > 70)
                g2.setStroke(new BasicStroke(3));
            else if (size > 30)
                g2.setStroke(new BasicStroke(2));
            else
                g2.setStroke(new BasicStroke(1));

            g2.drawLine(x_front, y_front, x_front - size / 10, y_front + size / 10);
            g2.setStroke(new BasicStroke(1));
        } else // fish swims to left side
        {
            // Body of fish
            g.fillOval(x_front, y_front - size / 4, size, size / 2);
            // Tail of fish
            int[] x_t = { x_front + size + size / 4, x_front + size + size / 4, x_front + size };
            int[] y_t = { y_front - size / 4, y_front + size / 4, y_front };
            Polygon t = new Polygon(x_t, y_t, 3);
            g.fillPolygon(t);// Eye of fish
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(new Color(255 - col.getRed(), 255 - col.getGreen(), 255 -
                    col.getBlue()));
            g2.fillOval(x_front + size / 10, y_front - size / 10, size / 10, size / 10);
            // Mouth of fish
            if (size > 70)
                g2.setStroke(new BasicStroke(3));
            else if (size > 30)
                g2.setStroke(new BasicStroke(2));
            else
                g2.setStroke(new BasicStroke(1));

            g2.drawLine(x_front, y_front, x_front + size / 10, y_front + size / 10);
            g2.setStroke(new BasicStroke(1));


        }

        foodX = g.getClipBounds().width /2;
        foodY = g.getClipBounds().height /2;


        if (Worm.getInstance().foodPlaced) {
            if (x_dir == 1 && x_front > foodX) x_dir = -1;
            if (x_dir == -1 && x_front < foodX) x_dir = 1;
            if (y_dir == 1 && y_front > foodY) y_dir = -1;
            if (y_dir == -1 && y_front < foodY) y_dir = 1;
        }
        else
        {
            if (x_front >= g.getClipBounds().width) x_dir = -1;
            if (x_front <= 0) x_dir = 1;

            if (y_front - size/2 >= g.getClipBounds().height) y_dir = -1;
            if (y_front + size/2 <= 0) y_dir = 1;
        }

        if (Worm.getInstance().isNearFood(g, this)) {
            Worm.getInstance().setFoodPlaced(false);
            eatInc();
        }
    }

    @Override // Decorator DP:
    public void PaintFish(Color col) {
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

    @Override
    public Fish clone() {
        return new Fish(size,x_front,y_front,horSpeed,verSpeed,col);
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
            x.update(this.getAnimalName());
        }
    }
}
