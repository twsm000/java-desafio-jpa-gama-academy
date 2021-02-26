package app;

import controller.CrudController;
import model.entities.ClassificacaoCliente;
import model.entities.Cliente;
import service.ClienteService;
import service.EntityManagerService;
import util.PersistenceException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.UUID;

public class Program {
    private static final EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("BancoPU");
    private static final EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static void main(String[] args) {
        try {
            start();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    private static void start() {
        EntityManagerService<Cliente> service = new ClienteService(entityManager);
        CrudController<Cliente> clienteController = new CrudController<>(service);

        try {
            System.out.println("Inserindo novo cliente normal");
            Cliente first = novoCliente(ClassificacaoCliente.NORMAL);
            clienteController.create(first);

            System.out.println("Inserindo novo cliente especial");
            clienteController.create(novoCliente(ClassificacaoCliente.ESPECIAL));

            System.out.println("Inserindo novo cliente gold");
            clienteController.create(novoCliente(ClassificacaoCliente.GOLD));

            System.out.println("Listando todos os registros...");
            List<Cliente> all = clienteController.readAll();
            all.forEach(System.out::println);

            System.out.println("Atualizando registro " + first.getId());
            first.setNome("TESTE");
            clienteController.update(first);
            System.out.println("Registro atualizado: " + first);

            System.out.println("Excluindo registro " + first.getId());
            clienteController.delete(first);
            System.out.println("Registro excluído!");
        } catch (PersistenceException e) {
            System.err.println(e.getMessage());
        }
    }

    private static Cliente novoCliente(ClassificacaoCliente classificacao) {
        Cliente cliente = new Cliente();
        cliente.setNome(UUID.randomUUID().toString());
        cliente.setClassificacao(classificacao);
        return cliente;
    }
}
