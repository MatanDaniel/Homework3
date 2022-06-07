public class PlantFactory implements  AbstractSeaFactory{
    // UI object in order to transfer data into the AddAnimalDialog
    private AddAnimalDialog UI;

    public PlantFactory(AddAnimalDialog UI) {
        this.UI = UI;
    }
    public void setUI(AddAnimalDialog UI) {
        this.UI = UI;
    }

    public AddAnimalDialog getUI() {
        return UI;
    }
    @Override // Implementing interface method and asks which object to create with object's constructor
    public SeaCreature produceSeaCreature(String factory) {
        if (factory.equals("Zostera")){
            return new Zostera(Integer.parseInt(UI.sizeTextField.getText()), Integer.parseInt(UI.x_CoordinateTextField.getText()) ,Integer.parseInt(UI.y_CoordinateTextField.getText()));
        }
        if (factory.equals("Laminaria"))
            return new Laminaria(Integer.parseInt(UI.sizeTextField.getText()), Integer.parseInt(UI.x_CoordinateTextField.getText()) ,Integer.parseInt(UI.y_CoordinateTextField.getText()));
        return null;
    }
}
