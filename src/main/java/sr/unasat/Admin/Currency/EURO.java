package sr.unasat.Admin.Currency;

public class EURO extends Currency {
    final String acronym = "EURO";

    public EURO() {
    }

    @Override
    String SelectedCurrency() {
        return acronym;
    }
}
