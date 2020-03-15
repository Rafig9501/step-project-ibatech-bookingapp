package dao;

import java.util.Collection;
import java.util.Optional;

public interface DAO<T> {

    Optional<T> get(String id) throws Exception;
    void create(T t) throws Exception;
    void delete(String id) throws Exception;
    void update(T t) throws Exception;
    Collection<T> getAll() throws Exception;
}
