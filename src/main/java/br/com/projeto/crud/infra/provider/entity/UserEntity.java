package br.com.projeto.crud.infra.provider.entity;

import br.com.projeto.crud.infra.util.MethodUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TB_USER")
public class UserEntity {

	@Id
	@NotBlank(message = "O login esta em branco!")
	private String login;

	@NotBlank(message = "O passwaord esta em branco!")
	private String passwaord;

//	@Override
//	public String toString() {
//		return MethodUtil.convertToJsonMaskValues(this);
//	}

}
