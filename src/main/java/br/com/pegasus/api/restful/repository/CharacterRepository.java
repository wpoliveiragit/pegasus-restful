package br.com.pegasus.api.restful.repository;

import br.com.pegasus.api.restful.repository.entity.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository
        extends JpaRepository<CharacterEntity, Integer> {
}
