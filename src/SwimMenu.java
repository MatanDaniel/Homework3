import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SwimMenu extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String typeAnimal = null, idOb;
    private int idObject = 0, totalEatCounter = 0;

    private HashSet<Swimmable> animals = new HashSet<>();
    private HashMap<Integer, Memento> swimmableMementoList;
    private Set set;
    private Iterator<Swimmable> itrAnimals;
    private JTable table;
    private AquaPanel panel;
    private JPanel tablePanel = new JPanel();
    private int optionTable;


    public void setDialog() {
        setSize(650, 300);
        setLayout(new BorderLayout());
    }

    public SwimMenu(AquaPanel panel, HashSet<Swimmable> animals, int optionTable) {
        setDialog();
        this.panel = panel;
        this.animals = new HashSet<Swimmable>(animals);
        this.optionTable = optionTable;

        //set panel
        SettingTable();
        add(tablePanel);
    }
    public SwimMenu(HashMap<Integer, Memento> swimmableMementoList,int optionTable)
    {
        setDialog();
        this.swimmableMementoList=new HashMap<Integer, Memento>(swimmableMementoList);
        //System.out.println(swimmableMementoList.size());

        //System.out.println(swimmableMementoList.get(0).getSize());
        this.optionTable=optionTable;
        SettingTable();
        add(tablePanel);
    }

    public void SettingTable() {
        tablePanel = new JPanel();

        String[] columns = null;
        if (optionTable == 1) {
            columns = new String[]{"", "ID", "Animal", "Color", "Size", "Hor.Speed", "Ver.speed", "Eat counter", "Hunger status"}; // columns
        } else if (optionTable == 2) {
            columns = new String[]{"", "ID", "Animal", "Color", "Size", "x_front,y_front", "Hor.Speed", "Ver.speed", "Eat counter", "Hunger status"}; // columns

        }
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(600, 150));
        if (optionTable == 1) {
            for (var sc : panel.getSwimmableSet()) {
                if (sc instanceof Swimmable) {
                    String[] swimm = new String[]{"", String.valueOf(((Swimmable) sc).getID()), ((Swimmable) sc).getAnimalName(), String.valueOf(((Swimmable) sc).getColor()), String.valueOf(((Swimmable) sc).getSize()), String.valueOf(((Swimmable) sc).getHorSpeed()), String.valueOf(((Swimmable) sc).getVerSpeed()), String.valueOf(((Swimmable) sc).getEatCount()), /*sc.getHungerState()*/};
                    totalEatCounter += ((Swimmable) sc).getEatCount(); //sum all eat count of the swimmable
                    tableModel.addRow(swimm);
                }
            }
        }
        if(optionTable==2){
            set=swimmableMementoList.entrySet();
            Iterator iterator=set.iterator();
            while(iterator.hasNext()){
                Map.Entry mentry = (Map.Entry)iterator.next();
                Memento sw = (Memento) mentry.getValue();
                String[] swimm=new String[]{"",String.valueOf(sw.getID()),sw.getAnimalName(),sw.getColor(),String.valueOf(sw.getSize()),String.valueOf(sw.getX_front()),String.valueOf(sw.getY_front()),String.valueOf(sw.getHorSpeed()),String.valueOf(sw.getVerSpeed()),/*sw.getHungerStatus()*/};
                tableModel.addRow(swimm);
            }
        }


            table.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {

                    int row = table.rowAtPoint(evt.getPoint());

                    int col = table.columnAtPoint(evt.getPoint());

                    if (row >= 0 && col >= 0) {

                        idOb = (String) table.getValueAt(row, 1); //for check
                        typeAnimal = (String) (table.getValueAt(row, 2));
                        idObject = Integer.valueOf(idOb);

                    }

                }

            });

            JScrollPane pane = new JScrollPane(table);

            tablePanel.add(pane); // add the table to the panel


        }

        public int getIDobject () {
            return idObject;
        }
        public String getTypeAnimal () {
            return typeAnimal;
        }

}
