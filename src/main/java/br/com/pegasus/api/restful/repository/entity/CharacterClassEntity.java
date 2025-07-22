package br.com.pegasus.api.restful.repository.entity;

import br.com.pegasus.api.restful.repository.embeddable.AttributesEmbeddable;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "TB_CHARACTER_CLASS")
public class CharacterClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHAR_CLASS_ID", unique = true)
    private Integer charClassId;

    @Embedded
    private AttributesEmbeddable attributes;

}
