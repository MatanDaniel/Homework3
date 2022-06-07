public class AnimalFactory implements AbstractSeaFactory {

    AddAnimalDialog UI;

    public AnimalFactory(AddAnimalDialog UI) {
        this.UI = UI;
    }

    @Override
    public SeaCreature produceSeaCreature(String factory) {
        if (factory.equals("Fish"))
            return new Fish(Integer.parseInt(UI.sizeTextField.getText()), Integer.parseInt(UI.x_CoordinateTextField.getText()), Integer.parseInt(UI.x_CoordinateTextField.getText()) , Integer.parseInt(UI.horSpeedTextField.getText()), Integer.parseInt(UI.verSpeedTextField.getText()) ,UI.getColor());
        if (factory.equals("Jellyfish"))
            return new Jellyfish(Integer.parseInt(UI.sizeTextField.getText()), Integer.parseInt(UI.x_CoordinateTextField.getText()), Integer.parseInt(UI.y_CoordinateTextField.getText()) , Integer.parseInt(UI.horSpeedTextField.getText()), Integer.parseInt(UI.verSpeedTextField.getText()) ,UI.getColor());
        return null;
    }
}
