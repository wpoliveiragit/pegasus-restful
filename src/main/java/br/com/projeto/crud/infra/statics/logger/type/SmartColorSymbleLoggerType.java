package br.com.projeto.crud.infra.statics.logger.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SmartColorSymbleLoggerType {

	CIRCLE("🔴");

	private final String code;
}
