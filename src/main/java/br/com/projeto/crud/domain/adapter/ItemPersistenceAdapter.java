package br.com.projeto.crud.domain.adapter;

import java.util.List;
import java.util.Optional;

import br.com.projeto.crud.infra.model.ItemModel;

public interface ItemPersistenceAdapter {

	List<ItemModel> findAll();

	Optional<ItemModel> findById(Long id);

	Optional<ItemModel> findByName(String name);

	ItemModel create(ItemModel model);

	ItemModel update(ItemModel model);

	void delete(Long id);

}
