package br.com.projeto.crud.app.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.projeto.crud.domain.port.ItemServicePort;
import br.com.projeto.crud.infra.config.mapper.ItemMapper;
import br.com.projeto.crud.infra.dto.ItemCreateBodyDTO;
import br.com.projeto.crud.infra.dto.ItemUpdateBodyDTO;
import br.com.projeto.crud.infra.factory.ResponseFactory;
import br.com.projeto.crud.infra.model.ItemModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@RequiredArgsConstructor
@Log4j2
public class ItemResource {

	private final ItemServicePort itemService;
	private final ItemMapper mapper;
	private ResponseFactory resp = new ResponseFactory();

	public ResponseEntity<?> findAll() {
		List<ItemModel> models = itemService.findAll();
		return models.isEmpty() ? resp.itemFindAllNoContent() : resp.itemFindAll(models);
	}

	public ResponseEntity<?> findById(Long id) {
		return resp.itemFindAll(itemService.findById(id));
	}

	public ResponseEntity<?> create(ItemCreateBodyDTO body) {
		return resp.itemCreate(itemService.create(mapper.toModel(body)));
	}

	public ResponseEntity<?> update(Long id, ItemUpdateBodyDTO body) {
		body.setId(id);
		return resp.itemUpdate(itemService.update(mapper.toModel(body)));
	}

	public ResponseEntity<?> delete(Long id) {
		itemService.delete(id);
		return resp.itemDelete();
	}

}
