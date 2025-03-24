package br.com.projeto.crud.infra.config.port;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.projeto.crud.domain.adapter.ItemPersistenceAdapter;
import br.com.projeto.crud.domain.core.ItemService;
import br.com.projeto.crud.domain.port.ItemServicePort;
import br.com.projeto.crud.infra.config.mapper.ItemMapper;
import br.com.projeto.crud.infra.provider.ItemRepository;
import br.com.projeto.crud.infra.provider.adapter.ItemRepositoryAdapter;

@Configuration
public class ItemConfig {

	@Bean
	public ItemServicePort createItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
		ItemPersistenceAdapter itemPersistence = new ItemRepositoryAdapter(itemRepository, itemMapper);
		return new ItemService(itemPersistence);
	}

}
