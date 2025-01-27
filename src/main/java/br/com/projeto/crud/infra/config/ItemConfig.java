package br.com.projeto.crud.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.projeto.crud.domain.adapter.ItemPersistenceAdapter;
import br.com.projeto.crud.domain.core.ItemService;
import br.com.projeto.crud.domain.port.ItemServicePort;
import br.com.projeto.crud.infra.repository.ItemRepository;
import br.com.projeto.crud.infra.repository.adapter.ItemRepositoryAdapter;

@Component
public class ItemConfig {

	@Bean
	public ItemServicePort createItemService(ItemRepository itemRepository) {
		ItemPersistenceAdapter itemPersistence = new ItemRepositoryAdapter(itemRepository);
		return new ItemService(itemPersistence);
	}

}
