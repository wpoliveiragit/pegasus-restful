package br.com.pegasus.api.restful.controller;

import br.com.pegasus.api.restful.service.LoginsService;
import br.com.pegasus.api.restful.util.HttpUtil;
import br.com.pegasus.swagger.model.login.ExampleClassEntity;
import br.com.pegasus.swagger.type.login.LoginCreateRequestBody;
import br.com.pegasus.swagger.type.login.LoginCreateResponseBody;
import br.com.pegasus.swagger.type.login.LoginDeleteResponseBody;
import br.com.pegasus.swagger.type.login.LoginFindAllResponseBody;
import br.com.pegasus.swagger.type.login.LoginFindByIdResponseBody;
import br.com.pegasus.swagger.type.login.LoginUpdateRequestBody;
import br.com.pegasus.swagger.type.login.LoginUpdateResponseBody;
import br.com.pegasus.swagger.web.login.LoginsApiDelegate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class LoginsController implements LoginsApiDelegate {

    private final LoginsService service;

    public LoginsController(LoginsService service) {
        this.service = service;
    }

    @Override
    public CompletableFuture<ResponseEntity<LoginFindAllResponseBody>> loginFindAll(Integer page, Integer size) {
        service.findAll();
        new ExampleClassEntity();
        var response = LoginFindAllResponseBody.builder().build();
        return HttpUtil.createResponse(HttpStatus.NOT_IMPLEMENTED, response);
    }

    @Override
    public CompletableFuture<ResponseEntity<LoginFindByIdResponseBody>> loginFindById(Integer login) {
        service.findById();
        var response = LoginFindByIdResponseBody.builder().build();
        return HttpUtil.createResponse(HttpStatus.NOT_IMPLEMENTED, response);
    }

    @Override
    public CompletableFuture<ResponseEntity<LoginCreateResponseBody>> loginCreate(LoginCreateRequestBody loginCreateRequestBody) {
        service.create();

        var response = LoginCreateResponseBody.builder().build();
        return HttpUtil.createResponse(HttpStatus.NOT_IMPLEMENTED, response);
    }

    @Override
    public CompletableFuture<ResponseEntity<LoginUpdateResponseBody>> loginUpdate(Integer login, LoginUpdateRequestBody loginUpdateRequestBody) {
        service.update();
        var response = LoginUpdateResponseBody.builder().build();
        return HttpUtil.createResponse(HttpStatus.NOT_IMPLEMENTED, response);
    }

    @Override
    public CompletableFuture<ResponseEntity<LoginDeleteResponseBody>> loginDelete(Integer login) {
        service.delete();
        var response = LoginDeleteResponseBody.builder().build();
        return HttpUtil.createResponse(HttpStatus.NOT_IMPLEMENTED, response);
    }

}
