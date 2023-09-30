package question1.connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Connection {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hw17");
    public static final EntityManager entityManager = emf.createEntityManager();
}
