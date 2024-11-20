package br.com.projeto.crud.infra.mapper;

import br.com.projeto.crud.domain.DTO.ResponseDTO;

public class createResponseDTO {

	public static final int STATIS_ACCESS_DENIED = -3;
	public static final int STATIS_UNAUTHORIZED_ACCESS = -2;
	public static final int STATIS_FAILURE = -1;
	public static final int STATIS_SUCCESS = 0;

	private static final String RESPONSE_GET = "Linha encontrado com sucesso!";
	private static final String RESPONSE_GET_LIST = "Linhas encontrados com sucesso!";
	private static final String RESPONSE_UPDATE = "Linha atualizado com sucesso!";
	private static final String RESPONSE_CREATE = "Linha criado com sucesso!";

	private static final String RESPONSE_ACCESS_DENIED = "Acesso Negado!";
	private static final String RESPONSE_UNAUTHORIZED_ACCESS = "Acesso não autorizado!";

	// -- PUBLIC SUCCESS
	public static final ResponseDTO get(Object data) {
		return responseSuccess(data, RESPONSE_GET);
	}

	public static final ResponseDTO getList(Object data) {
		return responseSuccess(data, RESPONSE_GET_LIST);
	}

	public static final ResponseDTO update(Object data) {
		return responseSuccess(data, RESPONSE_UPDATE);
	}

	public static final ResponseDTO create(Object data) {
		return responseSuccess(data, RESPONSE_CREATE);
	}

	// -- PUBLIC FAILURE
	public static final ResponseDTO fail(Object errors, String message) {
		return new ResponseDTO(STATIS_FAILURE, message, errors);
	}

	public static final ResponseDTO authenticationEP(String err) {
		return new ResponseDTO(STATIS_UNAUTHORIZED_ACCESS, RESPONSE_UNAUTHORIZED_ACCESS, err);
	}

	public static final ResponseDTO accessDeniedHandler(String err) {
		return new ResponseDTO(STATIS_ACCESS_DENIED, RESPONSE_ACCESS_DENIED, err);
	}

	// -- PRIVATE
	private static final ResponseDTO responseSuccess(Object data, String message) {
		return new ResponseDTO(STATIS_SUCCESS, message, data);
	}

}
