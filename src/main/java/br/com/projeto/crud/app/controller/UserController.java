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

import br.com.projeto.crud.app.resource.UserResource;
import br.com.projeto.crud.infra.dto.UserCreateBodyDTO;
import br.com.projeto.crud.infra.dto.UserUpdateBodyDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Rotas de cadastro de login")
@RequestMapping("v1/user")
@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserResource userResource;

	@GetMapping
	@Operation(summary = "Lista todos os users.", description = "Retorna a lista de usuarios.")
	public ResponseEntity<?> findAll() {
		return userResource.findAll();
	}

	@GetMapping("/{user}")
	@Operation(summary = "Retorna user/login.", description = "Retorna um user pelo login.")
	public ResponseEntity<?> findById(@PathVariable String user) {
		return userResource.findById(user);
	}

	@PostMapping
	@Operation(summary = "Add novo user.", description = "Adiciona um novo user na tabela.")
	public ResponseEntity<?> create(@RequestBody UserCreateBodyDTO body) {
		return userResource.create(body);
	}

	@PutMapping("/{user}")
	@Operation(summary = "Atualiza user.", description = "Atualiza um user existente pelo login.")
	public ResponseEntity<?> update(@PathVariable String user, @RequestBody UserUpdateBodyDTO body) {
		return userResource.update(user, body);
	}

	@DeleteMapping("/{user}")
	@Operation(summary = "Remove user.", description = "Remove um user da tabela pelo ID.")
	public ResponseEntity<?> delete(@PathVariable String user) {
		return userResource.delete(user);
	}

}
