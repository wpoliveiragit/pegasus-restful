package br.com.projeto.crud.domain.port;

import java.util.List;

import br.com.projeto.crud.infra.model.ItemModel;

public interface ItemServicePort {

	List<ItemModel> findAll();

	ItemModel findById(Long id);

	ItemModel create(ItemModel model);

	ItemModel update(ItemModel model);

	void delete(Long id);

}
