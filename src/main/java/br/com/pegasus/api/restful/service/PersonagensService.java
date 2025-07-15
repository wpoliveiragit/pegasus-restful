package br.com.pegasus.api.restful.service;

import br.com.pegasus.swagger.personagens.model.RequestModel;
import br.com.pegasus.swagger.personagens.model.ResponseModel;
import org.springframework.stereotype.Service;

@Service
public class PersonagensService {

    public ResponseModel getPage(RequestModel request) {
//        return itemPersistence.findById(model.getId()).map(m -> itemPersistence.update(model)).orElseThrow(NotFoundExceptionCore::new);
        return ResponseModel.builder().build();
    }

    public ResponseModel get(RequestModel request) {
//        return itemPersistence.findById(id).orElseThrow(NotFoundExceptionCore::new);
        return ResponseModel.builder().build();
    }

    public ResponseModel create(RequestModel request) {
//        itemPersistence.findByName(model.getName()).ifPresent(e -> {throw new ConflictExceptionCore(););
//        return itemPersistence.create(model);
        return ResponseModel.builder().build();
    }

    public void update(RequestModel request) {
//        return itemPersistence.findById(model.getId()).map(m -> itemPersistence.update(model)).orElseThrow(NotFoundExceptionCore::new);
    }

    public void delete(RequestModel request) {
//        itemPersistence.findById(id).ifPresentOrElse(model -> itemPersistence.delete(model.getId()), NotFoundExceptionCore::new);
    }

}
