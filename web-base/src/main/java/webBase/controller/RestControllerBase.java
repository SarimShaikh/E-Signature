package webBase.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import webBase.service.ServiceBase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;


public abstract class RestControllerBase<T, ID extends Serializable> {

    private static final Logger LOGGER = Logger.getLogger(RestControllerBase.class);

    protected final ServiceBase<T, ID> service;

    @Autowired
    public RestControllerBase(final ServiceBase<T, ID> service) {
        this.service = service;
    }

    @GetMapping("/all")
    public Iterable<T> getAll(final Pageable pageable, final T entity, @RequestParam(required = false) ArrayList<String> exactSearchingFields) {
        LOGGER.info(String.format("RestControllerBase --> all of {0}", entity.getClass().toString()));
        return service.getAll(pageable, entity, exactSearchingFields, false);
    }

    /*@GetMapping("/allWithoutPageable")
    public Iterable<T> getAllWithoutPageable(final Pageable pageable, final T entity, @RequestParam(required = false) ArrayList<String> exactSearchingFields) {
        LOGGER.info(String.format("RestControllerBase --> allWithoutPageable of {0}", entity.getClass().toString()));
        return service.getAll(Pageable.unpaged(), entity, exactSearchingFields, false);
    }*/

    @GetMapping("/get")
    public Optional<T> getById(final ID id) {
        LOGGER.info(String.format("RestControllerBase --> by Id {0}", id));
        return service.getById(id);
    }

    @PostMapping("/save")
    public T save(@RequestBody final T entity) {
        LOGGER.info(String.format("RestControllerBase --> save {0}", entity.toString()));
        return service.save(entity);
    }

    @PostMapping("/update")
    public T update(@RequestBody final T entity) {
        LOGGER.info(String.format("RestControllerBase --> update {0}", entity.toString()));
        return service.update(entity);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody final T entity) {
        LOGGER.info(String.format("RestControllerBase --> delete {0}", entity.toString()));
        service.delete(entity);
    }

    @DeleteMapping("delete/{id}")
    public void deleteById(@RequestBody final ID id) {
        LOGGER.info(String.format("RestControllerBase --> delete by Id {0}", id));
        service.deleteEntityById(id);
    }
}
