package sr.unasat.Admin.DAO;

import sr.unasat.Admin.Entities.Client;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClientDAO {
    private EntityManager entityManager;
    public ClientDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Client> retrieveClientList(){
        entityManager.clear();
        entityManager.getTransaction().begin();
        String jpql = "select c from client c";
        TypedQuery<Client> query = entityManager.createQuery(jpql, Client.class);
        List<Client> clientList = query.getResultList();
        entityManager.getTransaction().commit();
        return clientList;
    }

    public Client findClientByFirstNameAndLastName(String firstName,String lastName) {
        entityManager.getTransaction().begin();
        String jpql = "select c from client c where c.firstName = :firstName and c.lastName = :lastName";
        TypedQuery<Client> query = entityManager.createQuery(jpql, Client.class);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        Client client = query.getSingleResult();
        entityManager.getTransaction().commit();
        return client;
    }

    public Client findClientById(Long id) {
        entityManager.getTransaction().begin();
        String jpql = "select c from client c where c.id = :id";
        TypedQuery<Client> query = entityManager.createQuery(jpql, Client.class);
        query.setParameter("id", id);
        Client client = query.getSingleResult();
        entityManager.getTransaction().commit();
        return client;
    }

    public Client findClientByFirstName(String clientFirstName) {
        entityManager.getTransaction().begin();
        String jpql = "select c from client c where c.firstName = :clientFirstName";
        TypedQuery<Client> query = entityManager.createQuery(jpql, Client.class);
        query.setParameter("clientFirstName", clientFirstName);
        Client client = query.getSingleResult();
        entityManager.getTransaction().commit();
        return client;
    }

    public Client insertClient(Client client) {
        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();
        return client;
    }

    public void updateClient(Client updateClient){
        entityManager.getTransaction().begin();
        entityManager.persist(updateClient);
        entityManager.getTransaction().commit();
    }

    public void deleteClient(Client client) {
        entityManager.getTransaction().begin();
        entityManager.remove(client);
        entityManager.getTransaction().commit();
    }
}
