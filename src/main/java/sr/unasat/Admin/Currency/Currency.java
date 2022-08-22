package sr.unasat.Admin.Currency;

public abstract class Currency {
    Currency(){}

    public String CurrencyChoice(){
        return SelectedCurrency();
    }
    abstract String SelectedCurrency();

}
