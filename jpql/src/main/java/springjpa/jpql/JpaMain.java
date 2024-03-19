package springjpa.jpql;

import jakarta.persistence.*;
import springjpa.jpql.domain.Member;

import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {

            Member member = new Member();
            member.setUsername("memberA");
            member.setAge(20);
            entityManager.persist(member);

            TypedQuery<Member> typedQueryA = entityManager.createQuery("SELECT m FROM Member m", Member.class);
            Query query = entityManager.createQuery("SELECT m.username, m.age FROM Member m");

            List<Member> resultList = typedQueryA.getResultList();

            for (Member memberA : resultList) {
                System.out.println("memberA = " + memberA);
            }

            TypedQuery<Member> typedQueryB = entityManager.createQuery("SELECT m FROM Member m WHERE m.age = 20", Member.class);
            Member singleResult = typedQueryB.getSingleResult();
            System.out.println("singleResult = " + singleResult);


            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }

        entityManagerFactory.close();
    }
}
