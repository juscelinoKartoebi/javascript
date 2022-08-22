package sr.unasat.Admin.Decorator;

public class GymAdd extends AddDecorator{
    final String name = "GYM-PASS";

    public GymAdd(Add newAdd) {
        super(newAdd);
    }

    @Override
    public String getName(){
        return name;
    }
}
