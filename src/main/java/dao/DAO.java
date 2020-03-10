package dao;

import java.util.Collection;
import java.util.Optional;

public interface DAO<T> {

    Optional<T> get(String id) throws Exception;
    void save(T t) throws Exception;
    void delete(int id) throws Exception;
    Collection<T> getAll() throws Exception;
}
