package br.com.projeto.crud.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.crud.app.resource.ItemResource;
import br.com.projeto.crud.infra.dto.ItemCreateBodyDTO;
import br.com.projeto.crud.infra.dto.ItemUpdateBodyDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Rotas de cadastros de itens")
@RequestMapping("v1/item")
@RestController
@RequiredArgsConstructor
public class ItemController {

	private final ItemResource itemResource;

	@GetMapping
	@Operation(summary = "Lista todos os itens.", description = "Retorna uma lista contendo todos os itens.")
	public ResponseEntity<?> findAll() {
		return itemResource.findAll();
	}

	@GetMapping("/{id}")
	@Operation(summary = "Obtem item/{ID}.", description = "Retorna um item pelo ID.")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return itemResource.findById(id);
	}

	@PostMapping
	@Operation(summary = "Add novo item.", description = "Adiciona um novo item na lista.")
	public ResponseEntity<?> create(@RequestBody ItemCreateBodyDTO body) {
		return itemResource.create(body);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualiza item.", description = "Atualiza um item existente pelo ID.")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ItemUpdateBodyDTO body) {
		return itemResource.update(id, body);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Remove item.", description = "Remove um item da lista pelo ID.")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return itemResource.delete(id);
	}

}
