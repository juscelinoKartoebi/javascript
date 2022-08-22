package sr.unasat.Admin.Controllers;

import sr.unasat.Admin.Builder.AccountBuilder;
import sr.unasat.Admin.Config.JPAConfiguration;
import sr.unasat.Admin.Currency.Currency;
import sr.unasat.Admin.Currency.EURO;
import sr.unasat.Admin.Currency.SRD;
import sr.unasat.Admin.Currency.USD;
import sr.unasat.Admin.DAO.AccountDAO;
import sr.unasat.Admin.DAO.AddDAO;
import sr.unasat.Admin.DAO.ClientDAO;
import sr.unasat.Admin.DTO.AccountDTO;
import sr.unasat.Admin.DTO.AccountIdDTO;
import sr.unasat.Admin.Decorator.PoolAdd;
import sr.unasat.Admin.Decorator.StandardAdds;
import sr.unasat.Admin.Decorator.GymAdd;
import sr.unasat.Admin.Entities.Account;
import sr.unasat.Admin.Entities.Add;
import sr.unasat.Admin.Entities.Client;
import sr.unasat.Admin.Entities.TYPE;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("account")
public class AccountController {
    private AccountDAO accountDAO = new AccountDAO(JPAConfiguration.getEntityManager());
    private ClientDAO clientDAO = new ClientDAO(JPAConfiguration.getEntityManager());
    private AddDAO addDAO = new AddDAO(JPAConfiguration.getEntityManager());

    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AccountDTO> retrieveAccountList(){
        return accountDAO.retrieveAccountList().stream().map(account -> {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setAccountNumber(account.getAccountNumber());
            accountDTO.setBalance(account.getBalance());
            accountDTO.setType(account.getType() !=null ? account.getType().name(): null);
            accountDTO.setCurrency(account.getCurrency());
            accountDTO.setClient(account.getClient());
            accountDTO.setAdds(account.getAddList().stream().map(Add::getName).collect(Collectors.joining(",")));
            return accountDTO;
        }).collect(Collectors.toList());
    }

    @Path("add")
    @POST
    public String add(AccountDTO accountDTO) {
        Client client = clientDAO.findClientByFirstName(accountDTO.getClientFirstName());

        TYPE type = null;
        if (accountDTO.getType().equalsIgnoreCase("c")) {
            type = TYPE.STANDARD;
        } else if (accountDTO.getType().equalsIgnoreCase("s")){
            type = TYPE.LUXURIOUS;
        }else{

        }

        List<Add> addList = new ArrayList<>();

//  Decorator
        sr.unasat.Admin.Decorator.Add standardAdds = new StandardAdds();
        switch (accountDTO.getAdds()) {
            case "pool-pass":
                Add pool = addDAO.findAddById(accountDTO.getAdds());
                addList.add(pool);
                standardAdds = new GymAdd(standardAdds);
                break;
            case "gym-pass":
                Add gym = addDAO.findAddById(accountDTO.getAdds());
                addList.add(gym);
                standardAdds = new PoolAdd(standardAdds);
                break;
            case "3":
                addList = addDAO.retrieveAddList();
                standardAdds = new GymAdd(new PoolAdd(standardAdds));
                break;
            default:
        }

//  Template
        Currency currency1 = null;
        if (accountDTO.getCurrency().equalsIgnoreCase("SRD")) {
            currency1 = new SRD();
        } else if (accountDTO.getCurrency().equalsIgnoreCase("USD")) {
            currency1 = new USD();
        } else if (accountDTO.getCurrency().equalsIgnoreCase("EURO")) {
            currency1 = new EURO();
        }
        String currency2 = currency1.CurrencyChoice();
        AccountBuilder accountBuilder = new AccountBuilder(client, type, currency2, accountDTO.getBalance());
        accountBuilder.setAddList(addList);
//        Account newAccount = new Account(client, type, currency2, accountDTO.getBalance());
        Account newAccount = new Account(accountBuilder);

        accountDAO.insertAccount(newAccount);

        return "Account Added";
    }

    @Path("remove")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String removeAccount( AccountIdDTO id) {
        Account account = accountDAO.findAccountById(id.getId());
        accountDAO.deleteAccount(account);
        return "Account Removed!";
    }


}
