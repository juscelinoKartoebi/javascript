package sr.unasat.Admin.DAO;

import sr.unasat.Admin.Entities.Account;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AccountDAO {
    private EntityManager entityManager;
    public AccountDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Account> retrieveAccountList(){
        entityManager.clear();
        entityManager.getTransaction().begin();
        String jpql = "select a from account a";
        TypedQuery<Account> query = entityManager.createQuery(jpql, Account.class);
        List<Account> accountList = query.getResultList();
        entityManager.getTransaction().commit();
        return accountList;
    }

    public Account findAccountById(long accountNumber) {
        entityManager.getTransaction().begin();
        String jpql = "select a from account a where a.accountNumber = :accountNumber";
        TypedQuery<Account> query = entityManager.createQuery(jpql, Account.class);
        query.setParameter("accountNumber", accountNumber);
        Account account = query.getSingleResult();
        entityManager.getTransaction().commit();
        return account;
    }

    public Account insertAccount(Account account) {
        entityManager.getTransaction().begin();
        entityManager.persist(account);
        entityManager.getTransaction().commit();
        return account;
    }

    public void deleteAccount(Account account) {
        entityManager.getTransaction().begin();
        entityManager.remove(account);
        entityManager.getTransaction().commit();
    }

    public void updateAccount(Account updateAccount){
        entityManager.getTransaction().begin();
        entityManager.persist(updateAccount);
        entityManager.getTransaction().commit();
    }

}
