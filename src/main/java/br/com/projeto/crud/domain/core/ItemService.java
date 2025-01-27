package br.com.projeto.crud.domain.core;

import java.util.List;

import br.com.projeto.crud.domain.adapter.ItemPersistenceAdapter;
import br.com.projeto.crud.domain.exception.ConflictException;
import br.com.projeto.crud.domain.exception.NotFoundException;
import br.com.projeto.crud.domain.model.ItemModel;
import br.com.projeto.crud.domain.port.ItemServicePort;
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
		return itemPersistence.findById(id).orElseThrow(() -> new NotFoundException());
	}

	@Override
	public ItemModel create(ItemModel model) {
		itemPersistence.findByName(model.getName()).ifPresent(e -> {
			throw new ConflictException();
		});
		return itemPersistence.create(model);
	}

	@Override
	public ItemModel update(ItemModel model) {
		return itemPersistence.findById(model.getId())//
				.map(m -> {
					return itemPersistence.update(model);
				}).orElseThrow(//
						() -> new NotFoundException());
	}

	@Override
	public void delete(Long id) {
		itemPersistence.findById(id).ifPresentOrElse(//
				model -> itemPersistence.delete(model.getId()), //
				() -> new NotFoundException());
	}
}