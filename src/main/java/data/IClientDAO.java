package data;

import zona_fit.domain.ClientFit;

import java.util.List;

public interface IClientDAO {
    List<ClientFit> listClients();
    boolean searchClientById(ClientFit client);
    boolean addClient(ClientFit client);
    boolean updateClient(ClientFit client);
    boolean deleteClient(ClientFit client);
}
