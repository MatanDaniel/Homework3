

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.*;



public class AddDupAnimal extends JDialog implements ActionListener{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int size=0,hor=0,ver=0,x=0,y=0;

    private String typeAnimal=null,colorType=null;
    private Color clr = null;
    private String[] colors = {"blue","red","green","black","yellow","pink"};
    private JComboBox<String> cmb_ColorTypes;  // ComboBox colors types
    private AquaPanel panel;
    private JButton b_duplicate;
    private HashSet<Swimmable> animals=new HashSet<>();
    private JPanel DialogPanel,buttonsPanel; // create panels to the dialog
    private SwimMenu report;
    private Swimmable duplicateSwimmable;

    public AddDupAnimal(AquaPanel panel,HashSet<SeaCreature> seaCreatures)
    {
        super();

        setTitle("Duplicate");
        setSize(650,300);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);


       this.report=new SwimMenu(panel,animals,1);
        this.panel=panel;
        this.animals=new HashSet<Swimmable>(animals);
        this.typeAnimal=null;

        setPanel();

        add(DialogPanel);
        setVisible(true);

    }

    public void setPanel() {

        DialogPanel=new JPanel(new BorderLayout());
        buttonsPanel = new JPanel(new FlowLayout());
        b_duplicate=new JButton("Duplicate");
        buttonsPanel.add(b_duplicate);
        buttonsPanel.setVisible(true);

        DialogPanel.add(buttonsPanel,BorderLayout.SOUTH);
        DialogPanel.add(report);
        b_duplicate.addActionListener(this);
//
//        duplicateAnimalObject(report.getIDobject(),report.getTypeAnimal());
//        editDuplicateAnimal(duplicateSwimmable);
//        b_duplicate=new JButton("Duplicate");
//
//        buttonsPanel.add(b_duplicate);
//        buttonsPanel.setVisible(true);
//
//        DialogPanel.add(buttonsPanel,BorderLayout.SOUTH);
//        DialogPanel.add(report);
    }

    public void actionPerformed(ActionEvent e1) {
        if(e1.getSource()==b_duplicate)
        {
            duplicateAnimalObject(report.getIDobject(),report.getTypeAnimal());
                report.setVisible(false);
                buttonsPanel.setVisible(false);
                editDuplicateAnimal(duplicateSwimmable);
        }
    }




    public void duplicateAnimalObject(int idObject, String typeAnimal) {
        for (var sc : panel.getSwimmableSet()) {
            if (sc instanceof Swimmable) {
                if (((Swimmable) sc).getID() == idObject) {
                        duplicateSwimmable = ((Swimmable) sc).clone();
                    }
                }
            }
        }
    public void editDuplicateAnimal (Swimmable editSwimmable)
    {
        JPanel editPanel=new JPanel();
        editPanel.setSize(500,250);
        editPanel.setLayout(new GridLayout(5,3,1,2));



        cmb_ColorTypes=new JComboBox<String>(colors);
        JLabel lb_Size=new JLabel("Size");
        JLabel lb_horSpeed=new JLabel("horizontal speed");
        JLabel lb_verSpeed=new JLabel("vertical speed");
        JLabel lb_color=new JLabel("Color");
        JButton b_edit=new JButton("Clone!");

        JTextField txt_size=new JTextField();
        JTextField txt_verSpeed=new JTextField();
        JTextField txt_horSpeed=new JTextField();

        txt_size.setText(String.valueOf(editSwimmable.getSize()));
        txt_verSpeed.setText(String.valueOf(editSwimmable.getVerSpeed()));
        txt_horSpeed.setText(String.valueOf(editSwimmable.getVerSpeed()));


        editPanel.add(lb_Size);
        editPanel.add(txt_size);
        editPanel.add(lb_horSpeed);
        editPanel.add(txt_horSpeed);
        editPanel.add(lb_verSpeed);
        editPanel.add(txt_verSpeed);
        editPanel.add(lb_color);
        editPanel.add(cmb_ColorTypes);
        editPanel.add(b_edit);

        b_edit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg)
            {
                size=Integer.parseInt(txt_size.getText());
                hor=Integer.parseInt(txt_horSpeed.getText());
                ver=Integer.parseInt(txt_verSpeed.getText());
                colorType=cmb_ColorTypes.getItemAt(cmb_ColorTypes.getSelectedIndex());

                try{
                    if(size<20 || size>320)
                        throw new Exception("size have to be between 20 to 320");
                    if(hor<1 || hor>10)
                        throw new Exception("horizontal speed have to be between 1 to 10");
                    if(ver<1 || ver>10)
                        throw new Exception("vertical speed have to be between 1 to 10");

                    switch(colorType)
                    {
                        case "blue":
                            clr=Color.BLUE;
                            break;
                        case "red":
                            clr=Color.RED;
                            break;
                        case "green":
                            clr=Color.GREEN;
                            break;
                        case "black":
                            clr=Color.BLACK;
                            break;
                        case "yellow":
                            clr=Color.YELLOW;
                            break;
                        case "pink":
                            clr=Color.PINK;
                    }
                    editSwimmable.editSwimmable(size,x,y,hor,ver,clr);
                    JOptionPane.showMessageDialog(null,"The clone "+editSwimmable.getAnimalName() +" was added to the aquarium");
                    panel.addAnimal(editSwimmable);
                    dispose();

                }
                catch(Exception e1){
                    JOptionPane.showMessageDialog(null, e1.getMessage());
                }
            }
        });
        DialogPanel.add(editPanel);
        editPanel.setVisible(true);
    }

}
