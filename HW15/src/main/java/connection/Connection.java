package connection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Connection {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("university");
    public static final EntityManager entityManager = emf.createEntityManager();
}
