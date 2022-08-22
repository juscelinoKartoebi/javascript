package sr.unasat.Admin.Controllers;

import sr.unasat.Admin.Config.JPAConfiguration;
import sr.unasat.Admin.DAO.ClientDAO;
import sr.unasat.Admin.DTO.ClientDTO;
import sr.unasat.Admin.Entities.Client;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Path("client")
public class ClientController {
    private ClientDAO clientDAO= new ClientDAO(JPAConfiguration.getEntityManager());

    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ClientDTO> retrieveClientList(){
        return clientDAO.retrieveClientList().stream().map(client -> {
            ClientDTO clientDto = new ClientDTO();
            clientDto.setId(client.getId());
            clientDto.setFirstName(client.getFirstName());
            clientDto.setLastName(client.getLastName());
//            clientDto.setDob(client.getDob() != null ? LocalDate.parse(client.getDob().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))) : null);
            clientDto.setEmail(client.getEmail());
            clientDto.setGender(client.getGender());
            clientDto.setPhoneNumber(client.getPhoneNumber());
            return clientDto ;
        }).collect(Collectors.toList());
    }

    @Path("add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String add(Client client){
        clientDAO.insertClient(client);
        return "Customer: "+client.getFirstName()+" "+client.getLastName()+" added";
    }

    @Path("remove")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String remove(ClientDTO clientDTO) {
        Client client = clientDAO.findClientById(clientDTO.getId());
        clientDAO.deleteClient(client);
        return "Client has been deleted";
    }

    @Path("update")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateClient(Client newClient) {
        Client client = clientDAO.findClientByFirstNameAndLastName(newClient.getFirstName(),newClient.getLastName());
//        client.setFirstName(newClient.getFirstName());
        client.setLastName(newClient.getLastName());
//        client.setDob(newClient.getDob());
        client.setGender(newClient.getGender());
        client.setEmail(newClient.getEmail());
        client.setPhoneNumber(newClient.getPhoneNumber());
        clientDAO.updateClient(client);
        return "Client Updated";
    }

    @Path("getClient")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ClientDTO getClient(ClientDTO clientDTO) {
        Client client = clientDAO.findClientById(clientDTO.getId());
        ClientDTO clientDto = new ClientDTO();
        clientDto.setId(client.getId());
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
//        clientDto.setDob(LocalDate.parse(client.getDob().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        clientDto.setEmail(client.getEmail());
        clientDto.setGender(client.getGender());
        clientDto.setPhoneNumber(client.getPhoneNumber());
        return clientDto ;
    }

}
