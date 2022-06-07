import java.awt.*;

public class MarineAnimalDecorator implements MarineAnimal{
    public MarineAnimal m_Animal;
    public MarineAnimalDecorator(MarineAnimal m_Animal) {
        this.m_Animal = m_Animal;
    }

    @Override
    public void PaintFish(Color col) {
        ((Swimmable)m_Animal).setColor(col);
    }
}
