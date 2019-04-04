package net.uncrash.agent.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

/**
 * Abstract jpa method
 * @author Sendya
 * @param <T>
 * @param <ID>
 */
public abstract class AbstractJpaService<T, ID> implements JpaService<T, ID> {

    private Class<T> entityType;

    private Class<ID> primaryKeyType;

    @SuppressWarnings("unchecked")
    public AbstractJpaService() {
    }

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    public List<T> findAll(Sort sort) {
        return getRepository().findAll(sort);
    }

    @Override
    public List<T> findAllById(Iterable<ID> ids) {
        return getRepository().findAllById(ids);
    }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        return getRepository().saveAll(entities);
    }

    @Override
    public void flush() {
        getRepository().flush();
    }

    @Override
    public <S extends T> S saveAndFlush(S var1) {
        return getRepository().saveAndFlush(var1);
    }

    @Override
    public void deleteInBatch(Iterable<T> var1) {
        getRepository().deleteInBatch(var1);
    }

    @Override
    public void deleteAllInBatch() {
        getRepository().deleteAllInBatch();
    }

    @Override
    public Optional<T> findOne(ID var1) {
        return getRepository().findById(var1);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> var1) {
        return getRepository().findAll(var1);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> var1, Sort var2) {
        return getRepository().findAll(var1, var2);
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> var1, Pageable var2) {
        return getRepository().findAll(var1, var2);
    }
}
