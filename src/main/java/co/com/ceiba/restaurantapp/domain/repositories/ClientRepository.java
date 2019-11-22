package co.com.ceiba.restaurantapp.domain.repositories;

import java.util.List;

import co.com.ceiba.restaurantapp.domain.model.Client;

public interface ClientRepository {

	public List<Client> getClients();

	public Client getClientById(int id);

	public void addClient(Client client);

}
