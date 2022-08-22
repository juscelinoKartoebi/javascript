package sr.unasat.Admin.Builder;

import sr.unasat.Admin.Entities.Account;
import sr.unasat.Admin.Entities.Add;
import sr.unasat.Admin.Entities.Client;
import sr.unasat.Admin.Entities.TYPE;

import java.util.List;

public class AccountBuilder {
    public long accountNumber;
    public Client client;
    public TYPE type;
    public String currency;
    public double balance;

    public List<Add> addList;

    public AccountBuilder(Client client, TYPE type, String currency, double balance) {
        this.client = client;
        this.type = type;
        this.currency = currency;
        this.balance = balance;
    }

    public AccountBuilder(long accountNumber, Client client, TYPE type, String currency, double balance) {
        this.accountNumber = accountNumber;
        this.client = client;
        this.type = type;
        this.currency = currency;
        this.balance = balance;
    }

    public AccountBuilder setAddList(List<Add> addList) {
        this.addList = addList;
        return this;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Add> getAddList() {
        return addList;
    }

    public Account build(){ return new Account(this);}
}
