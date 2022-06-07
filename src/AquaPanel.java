import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.concurrent.CyclicBarrier;

public class AquaPanel extends JPanel implements Runnable{
    private JFrame frame = new JFrame("Info");
    private static BufferedImage image = null;
    private int totalEatCounter = 0;
    public HashSet<SeaCreature> seaCreatures = new HashSet<>();
    public HashSet<Swimmable> animals = new HashSet<>();
    public HashSet<Immobile> plants = new HashSet<>();

    public JPanel buttonPanel = new JPanel();
    boolean isPaused=false;
    private CareTaker careTakerMemento;

    public AquaPanel(CareTaker careTakerMemento) {
        this.careTakerMemento=careTakerMemento;
        buttonPanel.setLayout(new GridLayout());
        setPreferredSize(new Dimension(1200, 700));
        JButton addAnimal = new JButton("Add Animal");
        JButton sleep = new JButton("Sleep");
        JButton wakeUp = new JButton("Wake Up");
        JButton reset = new JButton("Reset");
        JButton food = new JButton("Food");
        JButton info = new JButton("Info");
        JButton exit = new JButton("Exit");
        JButton decorator = new JButton("Decorator");
        JButton duplicate = new JButton("Duplicate Animal");

        buttonPanel.add(addAnimal);
        buttonPanel.add(sleep);
        buttonPanel.add(wakeUp);
        buttonPanel.add(reset);
        buttonPanel.add(food);
        buttonPanel.add(info);
        buttonPanel.add(decorator);
        buttonPanel.add(duplicate);
        buttonPanel.add(exit);

        BorderLayout border = new BorderLayout();
        this.setLayout(border);
        this.add(buttonPanel, BorderLayout.SOUTH);

        exit.addActionListener(e -> System.exit(0));
        food.addActionListener(e -> placeFood());
        reset.addActionListener(e -> resetAll());
        sleep.addActionListener(e -> sleepAll());
        wakeUp.addActionListener(e -> wakeAll());
        info.addActionListener(e -> getInfo());
        duplicate.addActionListener(e -> dupAnimal());
        JPanelDecorator dec = new JPanelDecorator(seaCreatures);
        dec.setPreferredSize(new Dimension(1200, 500));

        decorator.addActionListener(e -> {
            JPanelDecorator dec1 = new JPanelDecorator(seaCreatures);
            dec1.setVisible(true);


        });
        addAnimal.addActionListener(e -> new AddAnimalDialog(this));

    }
    private void placeFood() {
        if(seaCreatures.size()!=0) {
            CyclicBarrier cb = new CyclicBarrier((int) seaCreatures.stream().filter(e -> e instanceof Swimmable).count());
            seaCreatures.stream().filter(e -> e instanceof Swimmable).forEach(e -> ((Swimmable) e).setBarrier(cb));
        }
        else{
            JOptionPane.showMessageDialog(null, " No fish to feed! add fish first!");
            return;
        }
        if (!Worm.getInstance().foodPlaced) {
            Worm.getInstance().foodPlaced = true;
            totalEatCounter++;
        }
    }

    private void resetAll() {
        seaCreatures.clear();
        totalEatCounter = 0;
        Worm.getInstance().foodPlaced = false;
        repaint();
    }

    private void sleepAll() {
        isPaused=true;
        seaCreatures.stream().filter(e -> e instanceof Swimmable).forEach(e -> ((Swimmable) e).setSuspend());

    }

    private void wakeAll() {
        isPaused=false;
        seaCreatures.stream().filter(e -> e instanceof Swimmable).forEach(e -> ((Swimmable) e).setResume());

    }

    public void dupAnimal(){
        try{
            if(seaCreatures.size()>=5)
                throw new Exception("You can't duplicate animals, The Aquarium already contains 5 animals!"); // the aquarium limits to only 5 swimmable
            else if(seaCreatures.size()==0)
                throw new Exception("no animals in aquarium to duplicate"); // the aquarium limits to only 5 swimmable
            else{
                AddDupAnimal duplicateDialog=new AddDupAnimal(this,seaCreatures);
            }
        }
        catch(Exception e1){
            JOptionPane.showMessageDialog(null,e1.getMessage());
        }
    }


    public void getInfo() {
        if (this.frame.isVisible()) {
            this.frame.dispose();
        } else {
            frame = new JFrame("Info");
            frame.setLayout(new BorderLayout());
            JTable tbl_info = getAnimalList();
            JPanel counter = new JPanel();
            JLabel countLabel = new JLabel("Total Count:" + totalEatCounter);
            counter.setLayout(new BorderLayout());
            counter.add(countLabel, BorderLayout.CENTER);

            frame.add(tbl_info, BorderLayout.CENTER);
            frame.add(counter, BorderLayout.SOUTH);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }

    }

    public JTable getAnimalList() {
        DefaultTableModel tbl = new DefaultTableModel(); // Creating info's table
        tbl.addColumn("Animal Name");
        tbl.addColumn("Color");
        tbl.addColumn("Size");
        tbl.addColumn("Hor.Speed");
        tbl.addColumn("Var.Speed");
        tbl.addColumn("Eat Count");
        Object[] columns = { "Name", "Color", "Size", "Hor.Speed", "Var.Speed",
                "Eat Counter" };
        tbl.addRow(columns);
        Object[] space = { "----------", "----------", "----------", "----------", "----------", "----------" };
        tbl.addRow(space);

        for (var sc : this.seaCreatures) {
            String tmpColor = "";
            if (sc instanceof Swimmable obj) {
                // Color to string
                if (Color.BLACK.equals(obj.getColor())) {
                    tmpColor = "Black";
                }
                if (Color.RED.equals(obj.getColor())) {
                    tmpColor = "Red";
                }
                if (Color.BLUE.equals(obj.getColor())) {
                    tmpColor = "Blue";
                }
                if (Color.GREEN.equals(obj.getColor())) {
                    tmpColor = "Green";
                }
                if (Color.CYAN.equals(obj.getColor())) {
                    tmpColor = "Cyan";
                }
                if (Color.ORANGE.equals(obj.getColor())) {
                    tmpColor = "Orange";
                }
                if (Color.YELLOW.equals(obj.getColor())) {
                    tmpColor = "Yellow";
                }
                if (Color.MAGENTA.equals(obj.getColor())) {
                    tmpColor = "Magenta";
                }
                if (Color.PINK.equals(obj.getColor())) {
                    tmpColor = "Pink";
                }
                Object[] row = { ((Swimmable) obj).getAnimalName(), tmpColor, ((Swimmable) obj).getSize(),
                        ((Swimmable) obj).getHorSpeed(), ((Swimmable) obj).getVerSpeed(),
                        ((Swimmable) obj).getEatCount()
                };
                tbl.addRow(row);
            }
        }
        return new JTable(tbl);
    }

    public void changeBackground(String msg) { // background from hw2
        if (msg.equals("image")) {
            try {
                image = ImageIO.read(Objects.requireNonNull(AquaPanel.class.getResourceAsStream("image.jpg")));
                repaint();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (msg.equals("blue")) {
            image = null;
            this.setBackground(Color.BLUE);
            SwingUtilities.updateComponentTreeUI(this);
        }
        if (msg.equals("white")) {
            image = null;
            this.setBackground(Color.WHITE);
            SwingUtilities.updateComponentTreeUI(this);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
        //Draw all creatures on screen
        for (SeaCreature s : seaCreatures) {
            if (s instanceof Swimmable){
                Swimmable temp = (Swimmable) s;
                temp.drawCreature(g);
            }
            else if(s instanceof Immobile){
                Immobile temp = (Immobile) s;
                temp.drawCreature(g);
                System.out.println("here");
            }
        }
//        seaCreatures.forEach(swimmer -> swimmer.drawCreature(g));
        if (Worm.getInstance().foodPlaced) {
            Worm.drawAnimal(g, this);
        }
    }

    public void addAnimal(Swimmable newSwimm)
    {
        //newSwimm.addObserver(this);
        seaCreatures.add(newSwimm); //add the new simmable object
        repaint(); // call the paintCompoment function
        new Thread(newSwimm).start(); // start the thread


    }

    public HashSet<Swimmable> getSwimmableSet(){
        for (var sc : seaCreatures) {
            if (sc instanceof Swimmable) {
                animals.add((Swimmable)sc);
            }
        }
        return animals;
    }
    public HashSet<Immobile> getPlantSet(){
        for (var sc : seaCreatures) {
            if (sc instanceof Immobile) {
                plants.add((Immobile)sc);
            }
        }
        return plants;}

    @Override
    public void run() {
        while (true) {
            this.repaint();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
