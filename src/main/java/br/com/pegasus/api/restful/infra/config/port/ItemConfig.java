package br.com.pegasus.api.restful.infra.config.port;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.pegasus.api.restful.domain.adapter.ItemPersistenceAdapter;
import br.com.pegasus.api.restful.domain.core.ItemService;
import br.com.pegasus.api.restful.domain.port.ItemServicePort;
import br.com.pegasus.api.restful.infra.config.mapper.ItemMapper;
import br.com.pegasus.api.restful.infra.provider.repository.ItemRepository;
import br.com.pegasus.api.restful.infra.provider.adapter.ItemRepositoryAdapter;

@Configuration
public class ItemConfig {

	@Bean
	public ItemServicePort createItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
		ItemPersistenceAdapter itemPersistence = new ItemRepositoryAdapter(itemRepository, itemMapper);
		return new ItemService(itemPersistence);
	}

}
