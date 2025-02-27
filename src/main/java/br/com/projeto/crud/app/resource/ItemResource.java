package br.com.projeto.crud.app.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.projeto.crud.app.dto.ItemCreateBodyDto;
import br.com.projeto.crud.app.dto.ItemUpdateBodyDto;
import br.com.projeto.crud.domain.model.ItemModel;
import br.com.projeto.crud.domain.port.ItemServicePort;
import br.com.projeto.crud.infra.factory.ResponseFactory;
import br.com.projeto.crud.infra.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ItemResource {

	private final ItemServicePort itemService;
	private ItemMapper mapper = ItemMapper.INSTANCE;
	private ResponseFactory resp = new ResponseFactory();

	public ResponseEntity<?> findAll() {
		List<ItemModel> models = itemService.findAll();
		return models.isEmpty() ? resp.itemFindAllNoContent() : resp.itemFindAll(models);
	}

	public ResponseEntity<?> findById(Long id) {
		return resp.itemFindAll(itemService.findById(id));
	}

	public ResponseEntity<?> create(ItemCreateBodyDto body) {
		return resp.itemCreate(itemService.create(mapper.toModel(body)));
	}

	public ResponseEntity<?> update(Long id, ItemUpdateBodyDto body) {
		body.setId(id);
		return resp.itemUpdate(itemService.update(mapper.toModel(body)));
	}

	public ResponseEntity<?> delete(Long id) {
		itemService.delete(id);
		return resp.itemDelete();
	}

}
