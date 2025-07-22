package br.com.pegasus.api.restful.service.tools;

import br.com.pegasus.swagger.util.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public class ServiceTools {

    public <T, ID> T findById(JpaRepository<T, ID> repository, ID id, String msgErr) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(msgErr + ": '" + id + "'"));
    }

}
