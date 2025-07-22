package br.com.pegasus.api.restful.controller;

import br.com.pegasus.api.restful.controller.tools.CharacterControllerTools;
import br.com.pegasus.api.restful.service.CharacterService;
import br.com.pegasus.api.restful.util.HttpUtil;
import br.com.pegasus.swagger.character.model.CharacterModel;
import br.com.pegasus.swagger.character.model.PageModel;
import br.com.pegasus.swagger.character.model.RequestModel;
import br.com.pegasus.swagger.character.model.ResponseModel;
import br.com.pegasus.swagger.character.type.CharacterCreateBodyType;
import br.com.pegasus.swagger.character.type.CharacterCreateResponseType;
import br.com.pegasus.swagger.character.type.CharacterGetResponseType;
import br.com.pegasus.swagger.character.type.CharacterPageResponseType;
import br.com.pegasus.swagger.character.type.CharacterType;
import br.com.pegasus.swagger.character.type.CharacterUpdateBodyType;
import br.com.pegasus.swagger.character.type.CharacterUpdateResponseType;
import br.com.pegasus.swagger.web.personagens.CharactersApiDelegate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class CharacterController extends CharacterControllerTools implements CharactersApiDelegate {

    private final CharacterService service;

    public CharacterController(CharacterService service) {
        this.service = service;
    }

    @Override
    public CompletableFuture<ResponseEntity<CharacterPageResponseType>> characterGetPage(
            Integer page, Integer size) {

        var pageModel = PageModel.builder().page(page).size(size).build();
        var requestModel = RequestModel.builder().page(pageModel).build();

        ResponseModel responseModel = service.getPage(requestModel);
        var data = responseModel.getCharacter().stream().map(super::toCharacterType).toList();
        var pagination = super.toPaginationType(responseModel.getPage());
        var response = CharacterPageResponseType.builder().pagination(pagination).data(data).build();
        return HttpUtil.createResponse(HttpStatus.OK, response);
    }

    @Override
    public CompletableFuture<ResponseEntity<CharacterGetResponseType>> characterGet(
            Integer id) {

        ResponseModel responseModel = service.get(RequestModel.builder()
                .character(CharacterModel.builder().charId(id).build())
                .build());

        // SEMPRE DEVE CONTER UM ÚNICO ELEMENTO
        CharacterType data = super.toCharacterType(responseModel.getCharacter().get(0));
        var response = CharacterGetResponseType.builder().data(data).build();
        return HttpUtil.createResponse(HttpStatus.OK, response);
    }

    @Override
    public CompletableFuture<ResponseEntity<CharacterCreateResponseType>> characterCreate(
            CharacterCreateBodyType characterCreateBodyType) {

        ResponseModel responseModel = service.create(RequestModel.builder().build());

        return HttpUtil.createResponse(HttpStatus.CREATED, CharacterCreateResponseType.builder()
                .data(super.toCharacterType(responseModel.getCharacter().get(0)))
                .build());
    }

    @Override
    public CompletableFuture<ResponseEntity<CharacterUpdateResponseType>> characterUpdate(
            Integer id, CharacterUpdateBodyType characterUpdateBodyType) {

        service.update(RequestModel.builder()
                .character(super.toCharacterModel(id, characterUpdateBodyType))
                .build());

        return HttpUtil.createResponse(HttpStatus.OK, CharacterUpdateResponseType.builder().build());
    }

    @Override
    public CompletableFuture<ResponseEntity<Void>> characterDelete(Integer id) {
        service.delete(RequestModel.builder()
                .character(CharacterModel.builder().charId(id).build()).build());

        return HttpUtil.createResponse(HttpStatus.NO_CONTENT);
    }

}
