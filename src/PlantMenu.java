import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PlantMenu extends JPanel{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String typeAnimal=null,idOb;
    private int idObject=0;
    private int optionTable;

    private HashSet<Immobile> PlantSet=new HashSet<Immobile>();
    private HashMap<Integer, Memento> plantsMementoList;
    private Set set;

    private Iterator <Immobile>itrPlants;
    private JTable table;
    private AquaPanel panel;
    private JPanel tablePanel=new JPanel();


    public void setDialog()
    {
        setSize(650,300);
        setLayout(new BorderLayout());
    }
    public PlantMenu(AquaPanel panel,HashSet<Immobile> PlantSet,int optionTable)
    {
        setDialog();
        this.optionTable=optionTable;
        this.panel=panel;
        this.PlantSet=new HashSet<Immobile>(PlantSet);
        SettingTable();
        add(tablePanel);
    }

    public PlantMenu(HashMap<Integer, Memento> plantsMementoList,int optionTable)
    {
        setDialog();
        this.plantsMementoList=new HashMap<Integer, Memento>(plantsMementoList);
        this.optionTable=optionTable;
        SettingTable();
        add(tablePanel);


    }

    public void SettingTable()
    {
        tablePanel=new JPanel();
        itrPlants= PlantSet.iterator();
        String[] columns=null;
        if(optionTable==1)
            columns=new String[]{"","ID","Plant","Color","Size"}; // columns

        if(optionTable==2)
            columns=new String[]{"","ID","Plant","Color","Size","x_front","y_front"}; // columns

        DefaultTableModel tableModel = new DefaultTableModel(columns,0);
        table=new JTable (tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(600,150));

        if(optionTable==1){
            while(itrPlants.hasNext()){
                Immobile im = itrPlants.next();
                String[] swimm=new String[]{"",String.valueOf(im.getID()),im.getPlantName(), String.valueOf(im.getColor()),String.valueOf(im.getSize())};
                tableModel.addRow(swimm);
            }
        }

        if(optionTable==2){
            set=plantsMementoList.entrySet();
            Iterator iterator=set.iterator();
            while(iterator.hasNext()){
                Map.Entry mentry = (Map.Entry)iterator.next();
                Memento im = (Memento) mentry.getValue();
                String[] swimm=new String[]{"",String.valueOf(im.getID()),im.getAnimalName(),im.getColor(),String.valueOf(im.getSize()),String.valueOf(im.getX_front()),String.valueOf(im.getY_front())};
                tableModel.addRow(swimm);
            }
        }

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                int row = table.rowAtPoint(evt.getPoint());

                int col = table.columnAtPoint(evt.getPoint());

                if (row >= 0 && col >= 0) {

                    idOb=(String) table.getValueAt(row, 1); //for check
                    typeAnimal=(String) (table.getValueAt(row, 2));
                    idObject=Integer.valueOf(idOb);
                    //System.out.println(idObject);

                }

            }

        });

        JScrollPane pane=new JScrollPane(table);

        tablePanel.add(pane); // add the table to the panel
    }

    public int getIDobject(){return idObject;}
    public String getTypeAnimal(){return typeAnimal;}


}
