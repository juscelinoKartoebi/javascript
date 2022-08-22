package sr.unasat.Admin.Currency;

public class USD extends Currency{
    final String acronym = "USD";

    public USD() {
    }

    @Override
    String SelectedCurrency() {
        return acronym;
    }

}
