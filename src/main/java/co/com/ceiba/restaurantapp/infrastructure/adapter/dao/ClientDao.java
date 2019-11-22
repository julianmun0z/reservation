package co.com.ceiba.restaurantapp.infrastructure.adapter.dao;

import java.util.List;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.restaurantapp.infrastructure.adapter.entities.ClientEntity;

@Transactional
public interface ClientDao extends Repository<ClientEntity, Integer> {

	void save(ClientEntity clientEntity);

	List<ClientEntity> findAll();

	ClientEntity findById(int id);

}
