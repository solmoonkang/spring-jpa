package jpabasic.springjpa.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            // CREATE MEMEBER
            Test createTest = new Test();
            createTest.setUsername("newMember");

            entityManager.persist(createTest);


            // FIND ALL MEMBER
            List<Test> findTest = entityManager.createQuery("select m from Test as m", Test.class)
                            .getResultList();

            for (Test test : findTest) {
                System.out.println("test.username = " + test.getUsername());
            }

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }

        entityManagerFactory.close();
    }
}
