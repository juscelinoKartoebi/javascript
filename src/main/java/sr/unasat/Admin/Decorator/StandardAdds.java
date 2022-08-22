package sr.unasat.Admin.Decorator;

public class StandardAdds implements Add{
    final String name = "No Adds";
    @Override
    public String getName() {
        return name;
    }
}
