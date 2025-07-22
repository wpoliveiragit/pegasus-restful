package br.com.pegasus.api.restful.repository;

import br.com.pegasus.api.restful.repository.entity.CharacterRaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRaceRepository
        extends JpaRepository<CharacterRaceEntity, Integer> {
}
