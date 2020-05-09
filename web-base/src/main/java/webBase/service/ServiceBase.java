package webBase.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import webBase.repository.RepositoryBase;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

@Transactional
public abstract class ServiceBase<T, ID extends Serializable> {

    private static final Logger LOGGER = Logger.getLogger(ServiceBase.class);
    protected final RepositoryBase<T, ID> repository;
    @Autowired
    protected JdbcTemplate DB;
    private Class<T> entityClass;

    @Autowired
    public ServiceBase(final RepositoryBase<T, ID> repository) {
        this.repository = repository;
        this.entityClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Iterable<T> getAll(final Pageable pageable, T entity, List<String> exactSearchingFields, boolean ignoreOrga) {
        LOGGER.info(String.format("Service --> getAll of {%s}", entity.getClass().toString()));
        Iterable<T> entities = repository.findAll(pageable);
        return entities;
    }

    public T save(final T entity) {
        LOGGER.info(String.format("Service --> save for {%s}", entity.toString()));
        return this.repository.save(entity);
    }

    public T update(final T entity) {
        LOGGER.info(String.format("Service --> update for {%s}", entity.toString()));
        return this.repository.saveAndFlush(entity);
    }

    public void delete(final T entity) {
        LOGGER.info(String.format("Service --> delete for {%s}", entity.toString()));
        this.repository.delete(entity);
    }

    public Optional<T> getById(final ID id) {
        LOGGER.info(String.format("Service --> getById for {%s}", id));
        return this.repository.findById(id);
    }

    public void deleteEntityById(final ID id) {
        LOGGER.info(String.format("Service --> deleteEntityById for {%s}", id));
        this.repository.deleteById(id);
    }

    private String getEntityTableName() {
        String tableName = null;
        if (entityClass.isAnnotationPresent(javax.persistence.Table.class)) {
            javax.persistence.Table table = entityClass.getAnnotation(javax.persistence.Table.class);
            tableName = table.name();
        }
        return tableName;
    }

}
