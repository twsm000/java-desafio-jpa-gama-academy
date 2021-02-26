package controller;

import model.dao.CrudDAO;
import model.entities.Cliente;
import util.PersistenceException;

import java.util.List;

public class CrudController<T> {
    private CrudDAO<T> service;

    public CrudController(CrudDAO<T> service) {
        this.service = service;
    }

    public void create(T entity) throws PersistenceException {
        service.create(entity);
    }

    public T read(Long id) throws PersistenceException {
        return service.read(id);
    }

    public List<T> readAll() throws PersistenceException {
        return service.readAll();
    }

    public void update(T entity) throws PersistenceException {
        service.update(entity);
    }

    public void delete(T entity) throws PersistenceException {
        service.delete(entity);
    }
}
