import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RestoreObjectState extends JDialog implements ActionListener, ItemListener{
    /**
     *
     */
    private static final long serialVersionUID = 1L;




    private String[] types = {"Swimmable types","Plants types"};
    private JComboBox<String> cmb_types;


    private Iterator <Swimmable>itrAnimals;
    private Iterator <Immobile>itrPlants;


    private JPanel DialogPanel,buttonsPanel,comboPanel,reportsPanel;
    private JButton b_restoreObject,b_cancel;

    private SwimMenu swReport;
    private PlantMenu plReport;

    private CareTaker carTakerMemento;
    private HashMap<Integer, Memento> swimmableMementoList;
    private HashMap<Integer, Memento> plantsMementoList;

    private HashSet<Swimmable> animalsSet=new HashSet<Swimmable>();
    private HashSet<Immobile> PlantSet=new HashSet<Immobile>();

    private AquaPanel panel;
    public RestoreObjectState(AquaPanel panel,CareTaker carTakerMemento)
    {
        super();

        //settings restore object state
        setTitle("Restore Object state");
        setSize(650,300);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);


        //this.carTakerMemento=new CareTaker();
        this.carTakerMemento=carTakerMemento;
        this.panel=panel;
        this.swimmableMementoList=new HashMap<Integer, Memento>(carTakerMemento.getSwimmableMementoList());
        this.plantsMementoList=new HashMap<Integer, Memento>(carTakerMemento.getPlantsMementoList());
        this.swReport=new SwimMenu(swimmableMementoList,2);
        this.plReport=new PlantMenu(plantsMementoList,2);
        this.animalsSet=new HashSet<Swimmable>(panel.getSwimmableSet());
        this.PlantSet=new HashSet<Immobile>(panel.getPlantSet());

        setPanel();
        add(DialogPanel);
        setVisible(true);

    }

    public void setPanel() {

        DialogPanel=new JPanel(new BorderLayout());
        buttonsPanel = new JPanel(new FlowLayout());
        comboPanel=new JPanel(new FlowLayout());
        reportsPanel=new JPanel();

        comboPanel.setSize(new Dimension(300,100));
        cmb_types=new JComboBox<String>(types);


        b_restoreObject=new JButton("Restore Object");
        b_cancel=new JButton("Cancel");

        buttonsPanel.add(b_restoreObject);
        buttonsPanel.add(b_cancel);
        buttonsPanel.setVisible(true);
        reportsPanel.add(swReport);
        reportsPanel.add(plReport);

        plReport.setVisible(false);
        swReport.setVisible(true);

        comboPanel.add(cmb_types);
        DialogPanel.add(comboPanel,BorderLayout.PAGE_START);
        DialogPanel.add(buttonsPanel,BorderLayout.SOUTH);


        DialogPanel.add(reportsPanel);




        cmb_types.addItemListener(this);
        b_restoreObject.addActionListener(this);
        b_cancel.addActionListener(this);
    }



    @Override
    public void actionPerformed(ActionEvent e1) {
        if(e1.getSource()==b_restoreObject)
        {

            Swimmable swimObj=null;
            Immobile immoObj=null;
            //Memento memObj=null;
            int idObject;
            if(swReport.getTypeAnimal()=="Fish" || swReport.getTypeAnimal()=="Jellyfish"){
                idObject=swReport.getIDobject();
                swimObj = getSwimById(swReport.getIDobject());
                swimObj.setState(swimmableMementoList.get(idObject).getCol(), swimmableMementoList.get(idObject).getSize(),swimmableMementoList.get(idObject).getX_front(), swimmableMementoList.get(idObject).getY_front(),swimmableMementoList.get(idObject).getHorSpeed(),swimmableMementoList.get(idObject).getVerSpeed(),swimmableMementoList.get(idObject).getX_dir(),swimmableMementoList.get(idObject).getY_dir());
                JOptionPane.showMessageDialog(null,swimObj.getAnimalName() + " " + swimObj.getID()+ " state restored!");
                panel.repaint();

            }

            if(plReport.getTypeAnimal()=="Laminaria" || plReport.getTypeAnimal()=="Zostera"){
                idObject=plReport.getIDobject();
                immoObj = getImmoById(plReport.getIDobject());
                immoObj.setState(plantsMementoList.get(idObject).getCol(), plantsMementoList.get(idObject).getSize(), plantsMementoList.get(idObject).getX_front(), plantsMementoList.get(idObject).getY_front());
                JOptionPane.showMessageDialog(null,immoObj.getPlantName() + " " + immoObj.getID()+ " state restored!");
                panel.repaint();
            }
            dispose();


        }
        else if(e1.getSource()==b_cancel)
        {
            dispose();
        }

    }

    private Immobile getImmoById(int idObject) {
        itrPlants=PlantSet.iterator();
        while(itrPlants.hasNext())
        {
            if(itrPlants.next().getID()==idObject)
                return itrPlants.next();

        }
        return null;
    }

    private Swimmable getSwimById(int idObject) {
        itrAnimals=animalsSet.iterator();
        while(itrAnimals.hasNext())
        {
            Swimmable sw=itrAnimals.next();
            if(sw.getID()==idObject)
            {
                return sw;
            }

        }
        return null;
    }






    @Override
    public void itemStateChanged(ItemEvent e2) {
        if(e2.getItem()=="Swimmable types")
        {
            swReport.setVisible(true);
            plReport.setVisible(false);
            System.out.println("sw");
        }
        if(e2.getItem()=="Plants types")
        {
            swReport.setVisible(false);
            plReport.setVisible(true);

            System.out.println("pl");

        }


    }


}
