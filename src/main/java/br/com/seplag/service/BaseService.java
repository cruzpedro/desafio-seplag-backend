package br.com.seplag.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.NoResultException;

import javaslang.control.Option;
import org.apache.deltaspike.data.api.AbstractEntityRepository;

@SuppressWarnings("unchecked")
public abstract class BaseService<T, E extends Serializable> {

    protected abstract AbstractEntityRepository<T, E> getRepository();

    public BaseService() {
        Class<?> clazz = getClass();
        do {
            if (clazz.getSuperclass().equals(BaseService.class)) {
                break;
            }
        } while ((clazz = clazz.getSuperclass()) != null);

        assert clazz != null;
        Class<T> type = (Class<T>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public List<T> findAll() {
    	return Option.of(getRepository().findAll()).orElseThrow(NoResultException::new);
    }

    public T save(T entity) {
        return getRepository().save(entity);
    }
    	 
    public T get(E id) {
        return Option.of(getRepository().findBy(id)).orElseThrow(NoResultException::new);
    }
 
    public void remove(E id) {
    	T entity = get(id);
        getRepository().remove(entity);
    }
 
    public T update(T entity) {
        return getRepository().save(entity);
    }
}
