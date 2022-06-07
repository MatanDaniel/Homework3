import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.stream.Stream;

public class JPanelDecorator extends JDialog {

    public JPanel buttonPanel = new JPanel();
    public HashSet<SeaCreature> seaCreature;
    public JList animalBox;
    public JButton changeColor;
    AquaPanel aquaPanel;

    public JPanelDecorator(HashSet<SeaCreature> seaCreatures){
        this.seaCreature = seaCreatures;
        animalBox = new JList(getAnimalNames(seaCreatures).toArray());
        add(animalBox);

        changeColor = new JButton("Change Color");
        add(changeColor, BorderLayout.SOUTH);

        changeColor.addActionListener(e -> {
            JColorChooser color = new JColorChooser();
            Color actualColor = JColorChooser.showDialog(null, "Pick a new color for the animal:", Color.BLACK);
            MarineAnimal selectedValue = (MarineAnimal) animalBox.getSelectedValue();
            selectedValue.PaintFish(actualColor);
            color.setVisible(true);
        });
        pack();

    }
    public Stream<SeaCreature> getAnimalNames(HashSet<SeaCreature> seaCreatures){
        return seaCreatures.stream().filter(seaCreature -> seaCreature instanceof Swimmable);
    }

}
