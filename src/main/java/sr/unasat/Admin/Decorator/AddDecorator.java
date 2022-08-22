package sr.unasat.Admin.Decorator;

public class AddDecorator implements Add{
    protected Add tempAdd;

    public AddDecorator(Add newAdd){
        this.tempAdd = newAdd;
    }

    @Override
    public String getName() {
        return tempAdd.getName();
    }
}
