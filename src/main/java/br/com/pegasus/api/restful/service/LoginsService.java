package br.com.pegasus.api.restful.service;

import org.springframework.stereotype.Service;

@Service
public class LoginsService {

    public void findById() {
//        return itemPersistence.findById(id).orElseThrow(NotFoundExceptionCore::new);
    }

    public void create() {
//        itemPersistence.findByName(model.getName()).ifPresent(e -> {throw new ConflictExceptionCore(););
//        return itemPersistence.create(model);

    }

    public boolean delete() {
//        itemPersistence.findById(id).ifPresentOrElse(model -> itemPersistence.delete(model.getId()), NotFoundExceptionCore::new);
        return false;
    }

    public void findAll() {
//        return itemPersistence.findById(model.getId()).map(m -> itemPersistence.update(model)).orElseThrow(NotFoundExceptionCore::new);
    }

    public void update() {
//        return itemPersistence.findById(model.getId()).map(m -> itemPersistence.update(model)).orElseThrow(NotFoundExceptionCore::new);
    }
}
