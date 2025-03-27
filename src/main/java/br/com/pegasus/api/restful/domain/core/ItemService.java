package br.com.pegasus.api.restful.domain.core;

import java.util.List;

import br.com.pegasus.api.restful.domain.adapter.ItemPersistenceAdapter;
import br.com.pegasus.api.restful.domain.port.ItemServicePort;
import br.com.pegasus.api.restful.domain.exceptioncore.ConflictExceptionCore;
import br.com.pegasus.api.restful.domain.exceptioncore.NotFoundExceptionCore;
import br.com.pegasus.api.restful.domain.model.ItemModel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ItemService implements ItemServicePort {

	private final ItemPersistenceAdapter itemPersistence;

	@Override
	public List<ItemModel> findAll() {
		return itemPersistence.findAll();
	}

	@Override
	public ItemModel findById(Long id) {
		return itemPersistence.findById(id).orElseThrow(NotFoundExceptionCore::new);
	}

	@Override
	public ItemModel create(ItemModel model) {
		itemPersistence.findByName(model.getName()).ifPresent(e -> {
			throw new ConflictExceptionCore();
		});
		return itemPersistence.create(model);
	}

	@Override
	public ItemModel update(ItemModel model) {
		return itemPersistence.findById(model.getId())//
				.map(m -> itemPersistence.update(model))//
				.orElseThrow(NotFoundExceptionCore::new);
	}

	@Override
	public void delete(Long id) {
		itemPersistence.findById(id).ifPresentOrElse(//
				model -> itemPersistence.delete(model.getId()), NotFoundExceptionCore::new);
	}
}