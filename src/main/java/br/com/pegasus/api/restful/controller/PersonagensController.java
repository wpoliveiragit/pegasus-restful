package br.com.pegasus.api.restful.controller;

import br.com.pegasus.api.restful.service.PersonagensService;
import br.com.pegasus.api.restful.util.HttpUtil;
import br.com.pegasus.swagger.personagens.model.DanoModel;
import br.com.pegasus.swagger.personagens.model.PageModel;
import br.com.pegasus.swagger.personagens.model.PaginationModel;
import br.com.pegasus.swagger.personagens.model.PersonagemModel;
import br.com.pegasus.swagger.personagens.model.RequestModel;
import br.com.pegasus.swagger.personagens.model.ResistenciaModel;
import br.com.pegasus.swagger.personagens.model.ResponseModel;
import br.com.pegasus.swagger.personagens.type.AtributosType;
import br.com.pegasus.swagger.personagens.type.DanoType;
import br.com.pegasus.swagger.personagens.type.MessageEnum;
import br.com.pegasus.swagger.personagens.type.PaginationType;
import br.com.pegasus.swagger.personagens.type.PersonagemCreateBodyRequestType;
import br.com.pegasus.swagger.personagens.type.PersonagemCreateBodyResponseType;
import br.com.pegasus.swagger.personagens.type.PersonagemDeleteBodyResponseType;
import br.com.pegasus.swagger.personagens.type.PersonagemGetResponseType;
import br.com.pegasus.swagger.personagens.type.PersonagemPageResponseType;
import br.com.pegasus.swagger.personagens.type.PersonagemType;
import br.com.pegasus.swagger.personagens.type.PersonagemUpdateBodyRequestType;
import br.com.pegasus.swagger.personagens.type.PersonagemUpdateBodyResponseType;
import br.com.pegasus.swagger.personagens.type.ResistenciaType;
import br.com.pegasus.swagger.personagens.type.StatusEnum;
import br.com.pegasus.swagger.personagens.type.StatusResponseType;
import br.com.pegasus.swagger.web.personagens.PersonagensApiDelegate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class PersonagensController implements PersonagensApiDelegate {

    /* !:[PAGINACAO]
     * - totalItems: quantidade de itens retornados na pagina.
     * - currentPage: pagina solicitada
     * - totalPages: quantidade total de paginas referente a quantidade de itens solicitado na paginação
     * */

    /* !: FIND ALL (PAGE)
     * [200]: OK
     * - [   0]: retorno esperado
     * - [   1]: lista vazia
     * - [   2]: paginação fora do limite 'máximo' (retorna a lista vazia)
     *
     * [400]: BAD REQUEST
     * - [  -1]: pagina com indice negativa ('Invalid pagination parameters: page: x)
     * - [  -2]: pagina com tamanho negativo ('Invalid pagination parameters: size: x')
     *
     * [500]: INTERNAL SERVER ERRO
     * - [-900]: informar a mensagem de erro'
     * */

    /* !: FIND BY ID
     * [200]: OK
     * - [   0]: retorno esperado
     *
     * [404]: NOT FOUND
     * - [  -3]: item não encontrado (Item with ID 1 not found.)
     *
     * [500]: INTERNAL SERVER ERRO
     * - [-900]: informar a mensagem de erro'
     * */

    /* !: CREATE
     * [201]: CREATED
     * - [   0]: Retorno esperado
     *
     * [400]: BAD REQUEST
     * - [  -4]: O campo 'Nome' é obrigatório
     * - [  -5]: O campo 'Raça' é obrigatório
     * - [  -6]: O campo 'Classe' é obrigatório
     *
     * [500]: INTERNAL SERVER ERRO
     * - [-900]: informar a mensagem de erro'
     * */

    /* !: UPDATE
     * [200]: OK
     * - [   0]: Retorno esperado
     *
     * [404]: Not Found
     * - [  -7]: Item não encontrado (Item with ID 1 not found.)
     *
     * [400]: Bad Request
     * - [  -8]- Invalid name length.
     *
     * [500]: INTERNAL SERVER ERRO
     * - [-900]: informar a mensagem de erro'
     * */

    /* !: DELETE
     * [204]: No Content
     * - [   0]: Retorno esperado
     *
     * [404]: Not Found
     * - [  -7]: Item não encontrado (Item with ID 1 not found.)
     *
     * [500]: INTERNAL SERVER ERRO
     * - [-900]: informar a mensagem de erro'
     * */

    private final PersonagensService service;

    public PersonagensController(PersonagensService service) {
        this.service = service;
    }

    @Override
    public CompletableFuture<ResponseEntity<PersonagemPageResponseType>> personagensGetPage(
            Integer page, Integer size) {
        // TODO: Testar com size 0 (zero)
        ResponseModel responseModel = service.getPage(RequestModel.builder()
                .page(PageModel.builder()
                        .index(size)
                        .lines(page)
                        .build())
                .build());

        PaginationModel paginationModel = responseModel.getPage();
        return HttpUtil.createResponse(HttpStatus.OK,
                PersonagemPageResponseType.builder()
                        .status(statusOk())
                        .pagination(PaginationType.builder()
                                .currentPage(paginationModel.getCurrentPage())
                                .hasNextPage(paginationModel.isHasNextPage())
                                .itemsPerPage(paginationModel.getItemsPerPage())
                                .totalPages(paginationModel.getTotalPages())
                                .quantity(paginationModel.getQuantity())
                                .build())
                        .data(responseModel.getPersonagens()
                                .stream()
                                .map(PersonagensController::toPersonagemType)
                                .toList())
                        .build());
    }

    @Override
    public CompletableFuture<ResponseEntity<PersonagemGetResponseType>> personagensGet(
            Integer id) {

        ResponseModel responseModel = service.get(RequestModel.builder()
                .personagem(PersonagemModel.builder()
                        .personagemId(id)
                        .build())
                .build());
        return HttpUtil.createResponse(HttpStatus.NOT_IMPLEMENTED,
                PersonagemGetResponseType.builder()
                        .status(statusOk())
                        .data(toPersonagemType(responseModel.getPersonagens().get(0)))
                        .build());
    }

    @Override
    public CompletableFuture<ResponseEntity<PersonagemCreateBodyResponseType>> personagensCreate(
            PersonagemCreateBodyRequestType personagemCreateBodyRequestType) {

        ResponseModel responseModel = service.create(RequestModel.builder()
                .build());

        return HttpUtil.createResponse(HttpStatus.NOT_IMPLEMENTED,
                PersonagemCreateBodyResponseType.builder()
                        .status(statusOk())
                        .data(toPersonagemType(responseModel.getPersonagens().get(0)))
                        .build());
    }

    @Override
    public CompletableFuture<ResponseEntity<PersonagemUpdateBodyResponseType>> personagensUpdate(
            Integer id, PersonagemUpdateBodyRequestType personagemUpdateBodyRequestType) {

        service.update(RequestModel.builder()
                .personagem(toPersonagemModel(id, personagemUpdateBodyRequestType))
                .build());
        return HttpUtil.createResponse(HttpStatus.NOT_IMPLEMENTED,
                PersonagemUpdateBodyResponseType.builder()
                        .status(statusOk())
                        .build());
    }

    @Override
    public CompletableFuture<ResponseEntity<PersonagemDeleteBodyResponseType>> loginDelete(
            Integer id) {

        service.delete(RequestModel.builder()
                .personagem(PersonagemModel.builder()
                        .personagemId(id)
                        .build())
                .build());
        var response = PersonagemDeleteBodyResponseType.builder()
                .status(statusOk())
                .build();
        return HttpUtil.createResponse(HttpStatus.NOT_IMPLEMENTED, response);
    }

    private static PersonagemType toPersonagemType(PersonagemModel model) {

        var atributosModel = model.getAtributos();
        DanoModel danoModel = atributosModel.getDano();
        ResistenciaModel resistenciaModel = atributosModel.getResistencia();
        return PersonagemType.builder()
                .personagemId(model.getPersonagemId())
                .racaId(model.getRacaId())
                .classeId(model.getClasseId())
                .nome(model.getNome())
                .atributos(AtributosType.builder()
                        .vida(atributosModel.getVida())
                        .dano(DanoType.builder()
                                .fisico(danoModel.getFisico())
                                .magico(danoModel.getMagico())
                                .build())
                        .resistencia(ResistenciaType.builder()
                                .fisico(resistenciaModel.getFisico())
                                .magico(resistenciaModel.getMagico())
                                .build())
                        .habilidadeList(atributosModel.getHabilidadeList())
                        .build())
                .build();
    }

    private static PersonagemModel toPersonagemModel(Integer personagemId, PersonagemUpdateBodyRequestType model) {
        return PersonagemModel.builder()
                .personagemId(personagemId)
                .racaId(model.getRacaId())
                .classeId(model.getClasseId())
                .nome(model.getNome())
                .build();
    }

    private static StatusResponseType statusOk() {
        return StatusResponseType.builder()
                .code(StatusEnum.OK)
                .message(MessageEnum.OK)
                .build();
    }

}
