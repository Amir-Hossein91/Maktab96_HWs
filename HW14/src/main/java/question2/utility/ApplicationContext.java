package question2.utility;

import question2.repository.impl.PersonRepositoryImpl;
import question2.repository.impl.StudentRepositoryImpl;
import question2.repository.impl.TeacherRepositoryImpl;
import question2.service.impl.PersonServiceImpl;
import question2.service.impl.StudentServiceImpl;
import question2.service.impl.TeacherServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ApplicationContext {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hw14");
    private static final EntityManager em = emf.createEntityManager();
    private static final PersonRepositoryImpl pr = new PersonRepositoryImpl(em);
    private static final StudentRepositoryImpl sr = new StudentRepositoryImpl(em);
    private static final TeacherRepositoryImpl tr = new TeacherRepositoryImpl(em);


    public static final PersonServiceImpl personService = new PersonServiceImpl(pr);
    public static final StudentServiceImpl studentService = new StudentServiceImpl(sr);
    public static final TeacherServiceImpl teacherService = new TeacherServiceImpl(tr);


}
