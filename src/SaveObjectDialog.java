
import java.awt.BorderLayout;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class SaveObjectDialog extends JDialog implements ActionListener, ItemListener{
    /**
     *
     */
    private static final long serialVersionUID = 1L;




    private String[] types = {"Swimmable types","Plants types"};
    private JComboBox<String> cmb_types;  // ComboBox colors types

    private HashSet<Swimmable> animalsSet=new HashSet<Swimmable>(); //hash set for swimmable
    private HashSet<Immobile> PlantSet=new HashSet<Immobile>(); //hash set for swimmable


    private Iterator <Swimmable>itrAnimals;
    private Iterator <Immobile>itrPlants;


    private JPanel DialogPanel,buttonsPanel,comboPanel,reportsPanel; // create panels to the dialog
    private JButton b_saveObject,b_cancel;

    private SwimMenu swReport;
    private PlantMenu plReport;
    private Swimmable duplicateSwimmable;
    private CareTaker carTakerMemento;


    public SaveObjectDialog(AquaPanel panel,CareTaker carTakerMemento)
    {
        super();


        //settings AddDuplicateAnimal
        setTitle("Save Object state");
        setSize(650,300);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        this.carTakerMemento=carTakerMemento;

        this.swReport=new SwimMenu(panel,panel.getSwimmableSet(),1);
        this.plReport=new PlantMenu(panel,panel.getPlantSet(),1);
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


        b_saveObject=new JButton("Save object");
        b_cancel=new JButton("Cancel");

        buttonsPanel.add(b_saveObject);
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
        b_saveObject.addActionListener(this);
        b_cancel.addActionListener(this);
    }



    @Override
    public void actionPerformed(ActionEvent e1) {
        if(e1.getSource()==b_saveObject)
        {
            Swimmable swimObj=null;
            Immobile immoObj=null;
            Memento memObj=null;

            if(swReport.getTypeAnimal()=="Fish" || swReport.getTypeAnimal()=="Jellyfish"){
                swimObj = getSwimById(swReport.getIDobject());
                memObj=new Memento(swimObj);
                //memObj=new Memento(swimObj.getID(),swimObj.getColorAnimal(),swimObj.getSize(),swimObj.getXfront(),swimObj.getYfront(),swimObj.getHorSpeed(),swimObj.getVerSpeed(),swimObj.getAnimalName(),swimObj.getX_dir(),swimObj.getY_dir());

                carTakerMemento.addSwimmableMemento(swimObj.getID(), memObj);
                JOptionPane.showMessageDialog(null,swimObj.getAnimalName() + " " + swimObj.getID()+ " state saved!");

                //mementoSwimmableList.put(swimObj.getID(), memObj);

            }


            if(plReport.getTypeAnimal()=="Laminaria" || plReport.getTypeAnimal()=="Zostera"){
                immoObj = getImmoById(plReport.getIDobject());
                memObj=new Memento(immoObj);
                carTakerMemento.addImmobileMemento(immoObj.getID(), memObj);
                JOptionPane.showMessageDialog(null,immoObj.getPlantName() + " " + immoObj.getID()+ " state saved!");

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
            Immobile im=itrPlants.next();
            if(im.getID()==idObject)
                return im;

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

    public void duplicateAnimalObject(int idObject,String typeAnimal)
    {
        System.out.println("ID: "+idObject);
        itrAnimals= animalsSet.iterator();
        while(itrAnimals.hasNext()){
            Swimmable sw=itrAnimals.next();
            if(sw.getID()==idObject){
                if(typeAnimal=="Fish")
                    duplicateSwimmable=(Fish)sw.clone();
                if(typeAnimal=="Jelly Fish")
                    duplicateSwimmable=(Fish)sw.clone();

            }
        }
        System.out.println(String.valueOf(duplicateSwimmable.getID())+duplicateSwimmable.getAnimalName());


    }

    @Override
    public void itemStateChanged(ItemEvent e2) {
        if(e2.getItem()=="Swimmable types")
        {
            swReport.setVisible(true);
            plReport.setVisible(false);
        }
        if(e2.getItem()=="Plants types")
        {
            swReport.setVisible(false);
            plReport.setVisible(true);


        }


    }


}
