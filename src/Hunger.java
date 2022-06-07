public class Hunger implements  HungerState{

    @Override
    public void doAction(Swimmable obj) {
        ((Swimmable)obj).setHungerState(new Hunger());
    }
}
