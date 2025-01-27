package br.com.projeto.crud.infra.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import other.AppUtil;

@Entity
@Table(name = "TB_ITEM")
public @Getter @Setter class ItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O nome esta em branco!")
	@Size(min = 3, max = 10, message = "O nome deve ter entre 3 e 10 caracteres.")
	private String name;

	@Override
	public String toString() {
		return AppUtil.GSON.toJson(this);
	}

}