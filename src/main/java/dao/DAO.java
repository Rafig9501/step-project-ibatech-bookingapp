package dao;

import java.util.Collection;
import java.util.Optional;

public interface DAO<T> {

    Optional<T> get(String id) throws Exception;
    void save(T t) throws Exception;
    boolean delete(String id) throws Exception;
    Collection<T> getAll() throws Exception;
}
