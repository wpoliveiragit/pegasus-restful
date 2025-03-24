package br.com.projeto.crud.infra.provider.adapter;

import java.util.List;
import java.util.Optional;

import br.com.projeto.crud.domain.adapter.ItemPersistenceAdapter;
import br.com.projeto.crud.infra.config.mapper.ItemMapper;
import br.com.projeto.crud.infra.model.ItemModel;
import br.com.projeto.crud.infra.provider.ItemRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ItemRepositoryAdapter implements ItemPersistenceAdapter {

	private final ItemRepository itemRepository;
	private final ItemMapper mapper;

	@Override
	public List<ItemModel> findAll() {
		return itemRepository.findAll().stream().map(mapper::toModel).toList();
	}

	@Override
	public Optional<ItemModel> findById(Long id) {
		return itemRepository.findById(id).map(mapper::toModel);
	}

	@Override
	public Optional<ItemModel> findByName(String name) {
		return itemRepository.findByName(name).map(mapper::toModel);
	}

	@Override
	public ItemModel create(ItemModel model) {
		return mapper.toModel(itemRepository.save(mapper.toEntity(model)));
	}

	@Override
	public ItemModel update(ItemModel model) {
		return mapper.toModel(itemRepository.save(mapper.toEntity(model)));
	}

	@Override
	public void delete(Long id) {
		itemRepository.deleteById(id);
	}

}
