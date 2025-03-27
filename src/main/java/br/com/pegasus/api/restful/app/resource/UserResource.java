package br.com.pegasus.api.restful.app.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.pegasus.api.restful.domain.port.UserServicePort;
import br.com.pegasus.api.restful.infra.config.mapper.UserMapper;
import br.com.pegasus.api.restful.app.dto.UserCreateBodyDTO;
import br.com.pegasus.api.restful.app.dto.UserUpdateBodyDTO;
import br.com.pegasus.api.restful.infra.factory.ResponseFactory;
import br.com.pegasus.api.restful.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@RequiredArgsConstructor
@Log4j2
public class UserResource {

	private final UserServicePort userService;
	private final UserMapper mapper;
	private ResponseFactory resp = new ResponseFactory();

	public ResponseEntity<?> findAll() {
		List<UserModel> models = userService.findAll();
		return models.isEmpty() ? resp.userFindAllNoContent() : resp.userFindAll(models);
	}

	public ResponseEntity<?> findById(String user) {
		return resp.userFindById(userService.findById(user));
	}

	public ResponseEntity<?> create(UserCreateBodyDTO body) {
		return resp.userCreate(userService.create(mapper.toModel(body)));
	}

	public ResponseEntity<?> update(String user, UserUpdateBodyDTO body) {
		body.setLogin(user);
		return resp.userUpdate(userService.update(mapper.toModel(body)));
	}

	public ResponseEntity<?> delete(String user) {
		userService.delete(user);
		return resp.userDelete();
	}

}
