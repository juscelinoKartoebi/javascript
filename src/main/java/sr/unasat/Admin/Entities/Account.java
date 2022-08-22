package sr.unasat.Admin.Entities;

import sr.unasat.Admin.Builder.AccountBuilder;

import javax.persistence.*;
import java.util.List;

@Entity(name = "account")
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accountNumber")
    private Long accountNumber;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id", nullable = false, referencedColumnName = "client_id")
    private Client client;
    @Enumerated(EnumType.STRING)
    private TYPE type;
    @Column(name = "currency")
    private String currency;
    @Column(name = "balance")
    private double balance;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @Column
    @JoinTable(
            name = "account_add",
            joinColumns = { @JoinColumn(name = "account_id") },
            inverseJoinColumns = { @JoinColumn(name = "add_id") }
    )
    private List<Add> addList;

    public Account(Long accountNumber, Client client, TYPE type, String currency, double balance, List<Add> addList) {
        this.accountNumber = accountNumber;
        this.client = client;
        this.type = type;
        this.currency = currency;
        this.balance = balance;
        this.addList = addList;
    }

    public Account(AccountBuilder accountBuilder) {
        this.client = accountBuilder.client;
        this.type = accountBuilder.type;
        this.currency = accountBuilder.currency;
        this.balance = accountBuilder.balance;
        this.addList = accountBuilder.addList;
    }

    public Account(long accountNumber, AccountBuilder accountBuilder) {
        this.accountNumber = accountNumber;
        this.client = accountBuilder.client;
        this.type = accountBuilder.type;
        this.currency = accountBuilder.currency;
        this.balance = accountBuilder.balance;
        this.addList = accountBuilder.addList;
    }

    public Account() {
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
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
}
