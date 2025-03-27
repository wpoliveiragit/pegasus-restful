package br.com.pegasus.api.restful.infra.provider.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pegasus.api.restful.infra.provider.repository.entity.ItemEntity;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

	Optional<ItemEntity> findByName(String name);
}
