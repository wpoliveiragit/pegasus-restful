package br.com.pegasus.api.restful.repository;

import br.com.pegasus.api.restful.repository.entity.LoginEntity;
import br.com.pegasus.api.restful.repository.entity.LoginEntity.LoginId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<LoginEntity, LoginId> {

}
