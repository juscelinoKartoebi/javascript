package sr.unasat.Admin.Currency;

public class SRD extends Currency{
    final String acronym = "SRD";

    public SRD() {
    }

    @Override
    String SelectedCurrency() {
        return acronym;
    }

}
