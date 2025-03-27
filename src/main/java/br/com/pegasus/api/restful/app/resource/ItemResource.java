package br.com.pegasus.api.restful.app.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.pegasus.api.restful.domain.port.ItemServicePort;
import br.com.pegasus.api.restful.infra.config.mapper.ItemMapper;
import br.com.pegasus.api.restful.app.dto.ItemCreateBodyDTO;
import br.com.pegasus.api.restful.app.dto.ItemUpdateBodyDTO;
import br.com.pegasus.api.restful.infra.factory.ResponseFactory;
import br.com.pegasus.api.restful.domain.model.ItemModel;
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
