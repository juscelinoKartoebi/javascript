package sr.unasat.Admin.Decorator;

public class PoolAdd extends AddDecorator{
    String name = "POOL-PASS";

    public PoolAdd(Add newAdd) {
        super(newAdd);
    }

    @Override
    public String getName(){
        return name;
    }
}
