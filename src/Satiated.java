public class Satiated implements HungerState{
    @Override
    public void doAction(Swimmable obj) {
        ((Swimmable)obj).setHungerState(new Satiated());
    }
}
