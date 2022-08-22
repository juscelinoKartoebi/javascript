package sr.unasat.Admin.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import sr.unasat.Admin.Entities.Client;

import javax.persistence.*;

//@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDTO {
    private long accountNumber;
    private Client client;
    private String clientFirstName;
    private String type;
    private String currency;
    private double balance;
    private String Adds;

    @JsonCreator
    public AccountDTO(@JsonProperty("client") Client client,
                      @JsonProperty("type") String type,
                      @JsonProperty("currency") String currency,
                      @JsonProperty("balance") double balance,
                      @JsonProperty("Adds") String adds) {
        this.client = client;
        this.type = type;
        this.currency = currency;
        this.balance = balance;
        this.Adds = adds;
    }

    public AccountDTO() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public String getAdds() {
        return Adds;
    }

    public void setAdds(String adds) {
        Adds = adds;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }
}
