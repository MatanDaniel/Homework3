import java.util.ArrayList;

public interface Subject {
    public ArrayList<Observer> observers = new ArrayList<Observer>();

    public HungerState getHungerState();

    public void setHungerState(HungerState hungerState);

    public void attach();

    public void notifyAllObservers();
}
