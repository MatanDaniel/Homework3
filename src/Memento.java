import java.awt.Color;

public class Memento {
    private int idObj,size,x_front,y_front,horSpeed,verSpeed,x_dir,y_dir;
    private Color col;
    private String ColorAnimal,animalType;
    private Swimmable swimObj=null;
    public Memento(Swimmable swimObj)
    {
        this.swimObj=swimObj;
        this.idObj=swimObj.getID();
        this.col=swimObj.getColor();
        this.size=swimObj.getSize();
        this.x_front=swimObj.getX_front();
        this.y_front=swimObj.getY_front();
        this.horSpeed=swimObj.getHorSpeed();
        this.verSpeed=swimObj.getHorSpeed();
        this.animalType=swimObj.getAnimalName();
        this.x_dir=swimObj.getX_dir();
        this.y_dir=swimObj.getY_dir();
    }
    public Memento(Immobile immObj){
        this.idObj=immObj.getID();
        this.col=immObj.getColor();
        this.size=immObj.getSize();
        this.x_front=immObj.getCoordinate_x();
        this.y_front=immObj.getCoordinate_y();
        this.animalType=immObj.getPlantName();
    }
    /*
    public Memento(int idObj,Color col,int size,int x_front,int y_front,int horSpeed,int verSpeed,String animalType,double x_dir,double y_dir)
    {
        this.idObj=idObj;
        this.col=col;
        this.size=size;
        this.x_front=x_front;
        this.y_front=y_front;
        this.horSpeed=horSpeed;
        this.verSpeed=verSpeed;
        this.animalType=animalType;
        this.x_dir=x_dir;
        this.y_dir=y_dir;

    }
    public Memento(int idObj,Color col,int size,int x_front,int y_front,String animalType)
    {
        this.idObj=idObj;
        this.col=col;
        this.size=size;
        this.x_front=x_front;
        this.y_front=y_front;
        this.animalType=animalType;
    }
    */
    public Color getCol(){return col;}
    public String getColor() {
        //String colorAnimal=null;
        if (col== Color.BLUE)
            ColorAnimal="blue";
        if (col==Color.RED)
            ColorAnimal="red";
        if (col== Color.GREEN)
            ColorAnimal="green";
        if (col== Color.BLACK)
            ColorAnimal="black";
        if (col==Color.yellow)
            ColorAnimal="yellow";
        if(col==Color.pink)
            ColorAnimal="pink";
        return ColorAnimal;
    }
    public int getID(){return idObj;}
    public int getSize(){return size;}
    public int getX_front(){return x_front;}
    public int getY_front(){return y_front;}
    public int getHorSpeed(){return horSpeed;}
    public int getVerSpeed(){return verSpeed;}
    public String getAnimalName(){return animalType;}
    public int getX_dir(){return x_dir;}
    public int getY_dir(){return y_dir;}
    //public String getHungerStatus(){return swimObj.getHungerState();}





}
