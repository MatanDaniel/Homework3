import java.util.HashMap;


public class CareTaker {
    private HashMap<Integer, Memento> swimmableMementoList;
    private HashMap<Integer, Memento> plantsMementoList;
    private static int counter=0;
    public final int objectID;

    public CareTaker()
    {
        this.objectID=++counter;
        swimmableMementoList=new HashMap<Integer, Memento>();
        plantsMementoList=new HashMap<Integer, Memento>();

    }
    public int getID(){return objectID;}

    public void addSwimmableMemento(int idObj,Memento state)
    {
        swimmableMementoList.put(idObj, state);

    }
    public void addImmobileMemento(int idObj,Memento state)
    {
        plantsMementoList.put(idObj, state);
    }

    public Memento getSwimmableMemento(int index){
        return swimmableMementoList.get(index);
    }

    public Memento getImmobileMemento(int index){
        return plantsMementoList.get(index);
    }
    public HashMap<Integer, Memento> getSwimmableMementoList(){return swimmableMementoList;}
    public HashMap<Integer, Memento>getPlantsMementoList(){return plantsMementoList;}

}
