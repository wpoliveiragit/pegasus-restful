package br.com.pegasus.api.restful.domain.port;

import java.util.List;

import br.com.pegasus.api.restful.domain.model.ItemModel;

public interface ItemServicePort {

	List<ItemModel> findAll();

	ItemModel findById(Long id);

	ItemModel create(ItemModel model);

	ItemModel update(ItemModel model);

	void delete(Long id);

}
