package service;

import model.dao.CrudDAO;
import util.PersistenceException;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class EntityManagerService<E> implements CrudDAO<E> {
    private EntityManager manager;
    private final Class<E> clazz;

    public EntityManagerService(EntityManager manager) {
        this.manager = manager;
        final ParameterizedType types = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class<E>) (types).getActualTypeArguments()[0];
    }

    @Override
    public void create(E entity) throws PersistenceException {
        try {
            manager.getTransaction().begin();
            manager.persist(entity);
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new PersistenceException("Falha ao inserir registro: " + e.getMessage());
        }
    }

    @Override
    public E read(Long id) throws PersistenceException {
        try {
            return manager.find(clazz, id);
        } catch (Exception e) {
            throw new PersistenceException("Falha ao localizar o registro: " + e.getMessage());
        }
    }

    @Override
    public List<E> readAll() throws PersistenceException {
        try {
            return manager
                .createQuery("FROM " + clazz.getName(), clazz)
                .getResultList();
        } catch (Exception e) {
            throw new PersistenceException("Falha ao ler todos os registro: " + e.getMessage());
        }
    }

    @Override
    public void update(E entity) throws PersistenceException {
        try {
            manager.getTransaction().begin();
            manager.merge(entity);
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new PersistenceException("Falha ao atualizar registro: " + e.getMessage());
        }
    }

    @Override
    public void delete(E entity) throws PersistenceException {
        try {
            manager.getTransaction().begin();
            manager.remove(entity);
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new PersistenceException("Falha ao excluir registro: " + e.getMessage());
        }
    }
}
