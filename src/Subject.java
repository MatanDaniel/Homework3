import java.util.ArrayList;
import java.util.Vector;

public interface Subject {
    public HungerState getHungerState();

    public void setHungerState(HungerState hungerState);

    public void attach(Observer object);

    public void notifyAllObservers();

}
