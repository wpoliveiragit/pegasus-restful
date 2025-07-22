package br.com.pegasus.api.restful.repository;

import br.com.pegasus.api.restful.repository.entity.CharacterClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterClassRepository
        extends JpaRepository<CharacterClassEntity, Integer> {
}
