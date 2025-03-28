package br.com.pegasus.api.restful.app.resource;

import br.com.pegasus.openapi.api.ItemsApiDelegate;
import br.com.pegasus.openapi.model.Item;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemsApiDelegateImpl implements ItemsApiDelegate {

    private final List<Item> dbItems = new ArrayList<>();

    @Override
    public ResponseEntity<List<Item>> findAll() {
        return ResponseEntity.ok(dbItems);
    }

    @Override
    public ResponseEntity<Void> create(Item item) {
//        Item model = new Item().id(dbItems.size()).;

        return null;
    }

    @Override
    public ResponseEntity<Item> findById(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> update(Integer id, Item item) {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        return null;
    }
}