package co.com.ceiba.restaurantapp.infrastructure.adapter.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.restaurantapp.domain.model.Client;
import co.com.ceiba.restaurantapp.domain.repositories.ClientRepository;
import co.com.ceiba.restaurantapp.infrastructure.adapter.builders.ClientBuilder;
import co.com.ceiba.restaurantapp.infrastructure.adapter.dao.ClientDao;
import co.com.ceiba.restaurantapp.infrastructure.adapter.entities.ClientEntity;

@Service
public class ClientRepositoryInMemory implements ClientRepository {

	@Autowired
	ClientDao clientDao;

	@Autowired
	ClientBuilder clientBuilder;

	@Override
	public List<Client> getClients() {
		List<Client> clients = new ArrayList<>();
		List<ClientEntity> clientEntities = clientDao.findAll();
		for (ClientEntity clientEntity : clientEntities) {
			Client client = clientBuilder.convertClientEntityToClient(clientEntity);
			clients.add(client);
		}
		return clients;
	}

	@Override
	public Client getClientById(int id) {
		ClientEntity clientEntity = clientDao.findById(id);
		Client client = clientBuilder.convertClientEntityToClient(clientEntity);
		return client;
	}

	@Override
	public void addClient(Client client) {
		ClientEntity clientEntity = clientBuilder.convertClientToRClientEntity(client);
		clientDao.save(clientEntity);
	}

}
