package dao;

import java.util.Collection;
import java.util.Optional;

public interface DAO<T> {

    Optional<T> get(String id) throws Exception;
    boolean create(T t) throws Exception;
    boolean delete(String id) throws Exception;
    boolean update(T t) throws Exception;
    Collection<T> getAll() throws Exception;
}
