package springjpa.jpql;

import jakarta.persistence.*;
import springjpa.jpql.domain.Member;
import springjpa.jpql.domain.Team;

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





            // FETCH JOIN: @ManyToOne
            Team team1 = new Team();
            team1.setName("team1");
            entityManager.persist(team1);

            Team team2 = new Team();
            team2.setName("team2");
            entityManager.persist(team2);

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setTeam(team1);
            entityManager.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setTeam(team1);
            entityManager.persist(member2);

            Member member3 = new Member();
            member3.setUsername("member3");
            member3.setTeam(team2);
            entityManager.persist(member3);

            entityManager.flush();

            String manyToOneFetchJoinJpql = "SELECT m FROM Member m JOIN FETCH m.team";

            List<Member> members1 = entityManager.createQuery(manyToOneFetchJoinJpql, Member.class)
                            .getResultList();

            for (Member member4 : members1) {
                System.out.println("username() = " + member4.getUsername() + ", " +
                                    "teamName = " + member4.getTeam().getName());

                // 회원1, 팀1(SQL)
                // 회원2, 팀1(1차 캐시)
                // 회원3, 팀2(SQL)

                // 회원 100명 -> N + 1: N명의 회원들과 해당 회원을 조회하기 위해 처음 날린 쿼리 1을 N + 1이라고 한다.
            }


            // FETCH JOIN: Collection, OneToMany
            String collectionFetchJoinJpql = "SELECT DISTINCT t FROM Team t JOIN FETCH t.members WHERE t.name='team1'";

            List<Team> teams1 = entityManager.createQuery(collectionFetchJoinJpql, Team.class)
                    .getResultList();

            for(Team team : teams1) {
                System.out.println("teamName = " + team.getName() + ", team = " + team);
                for (Member member5 : team.getMembers()) {
                    // 페치 조인으로 팀과 회원을 함께 조회해서 지연 로딩 발생 안함
                    System.out.println("-> username = " + member5.getUsername()+ ", member = " + member5);
                }
            }






            // 엔티티 직접 사용: 엔티티를 파라미터로 전달
            String entityParamJpql = "SELECT m FROM Member m WHERE m = :member";

            List resultList1 = entityManager.createQuery(entityParamJpql, Member.class)
                    .setParameter("member", member)
                    .getResultList();

            // 엔티티 직접 사용: 식별자를 직접 전달
            String identityParamJpql = "SELECT m FROM Member m WHERE m.id = :memberId";

            List resultList2 = entityManager.createQuery(identityParamJpql, Member.class)
                    .setParameter("memberId", member.getId())
                    .getResultList();

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }

        entityManagerFactory.close();
    }
}
