package service;

import model.entities.Cliente;

import javax.persistence.EntityManager;

public class ClienteService extends EntityManagerService<Cliente> {
    public ClienteService(EntityManager manager) {
        super(manager);
    }
}
