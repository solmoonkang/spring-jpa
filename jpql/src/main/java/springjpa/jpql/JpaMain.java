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



            // TypeQuery, Query
            TypedQuery<Member> typedQueryA = entityManager.createQuery("SELECT m FROM Member m", Member.class);
            Query query = entityManager.createQuery("SELECT m.username, m.age FROM Member m");



            // 결과 조회 API
            List<Member> resultListA = typedQueryA.getResultList();

            for (Member memberA : resultListA) {
                System.out.println("memberA = " + memberA);
            }

            TypedQuery<Member> typedQueryB = entityManager.createQuery("SELECT m FROM Member m WHERE m.age = 20", Member.class);
            Member singleResultB = typedQueryB.getSingleResult();
            System.out.println("singleResult = " + singleResultB);




            // 파라미터 바인딩
            TypedQuery<Member> typedQueryC = entityManager.createQuery("SELECT m FROM Member m WHERE m.username = :username", Member.class);
            typedQueryC.setParameter("username", "memberA");
            Member singleResultC = typedQueryC.getSingleResult();
            System.out.println("singleResultC = " + singleResultC);

            // 메서드 체이닝
            Member singleResultD = entityManager.createQuery("SELECT m FROM Member m WHERE m.username = :username", Member.class)
                    .setParameter("username", "memberA")
                    .getSingleResult();
            System.out.println("singleResultD = " + singleResultD);




            // 프로젝션
            List<Member> membersA = entityManager.createQuery("SELECT t FROM Member m JOIN m.team t", Member.class)
                            .getResultList();

            Member findMemberA = membersA.get(0);
            findMemberA.setAge(26);

            // 프로젝션: new 생성자를 사용
            List<MemberDTO> memberDTOS = entityManager.createQuery("SELECT new springjpa.jpql.MemberDTO(m.username, m.age) FROM Member m", MemberDTO.class)
                    .getResultList();

            MemberDTO memberDTO = memberDTOS.get(0);
            System.out.println("memberDTO.getUsername() = " + memberDTO.getUsername());
            System.out.println("memberDTO.getAge() = " + memberDTO.getAge());



            // PAGING QUERY
            String jpql = "SELECT m FROM Member m ORDER BY m.age desc";
            List<Member> membersB = entityManager.createQuery(jpql, Member.class)
                    .setFirstResult(10)
                    .setMaxResults(20)
                    .getResultList();

            System.out.println("membersB.size() = " + membersB.size());
            for (Member memberA : membersB) {
                System.out.println("memberA = " + memberA);
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
