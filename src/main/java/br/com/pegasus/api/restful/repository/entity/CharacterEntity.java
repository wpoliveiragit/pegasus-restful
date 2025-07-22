package br.com.pegasus.api.restful.repository.entity;

import jakarta.persistence.Column;
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
@Table(name = "TB_CHARACTER")
public class CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHAR_Id", unique = true)
    private Integer charId;


    @Column(name = "CHAR_CLASS_ID")
    private Integer charClassId;

    @Column(name = "CHAR_RACE_ID")
    private Integer charRaceid;

    @Column(name = "CHAR_NAME")
    private String charName;

}
