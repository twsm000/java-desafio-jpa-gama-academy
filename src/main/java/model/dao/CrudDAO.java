package model.dao;

import util.PersistenceException;

import java.util.List;

public interface CrudDAO<T> {
    void create(T entity) throws PersistenceException;
    T read(Long id) throws PersistenceException;
    List<T> readAll() throws PersistenceException;
    void update(T entity) throws PersistenceException;
    void delete(T entity) throws PersistenceException;
}
