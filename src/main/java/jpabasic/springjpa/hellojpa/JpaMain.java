package jpabasic.springjpa.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            // SAVE TEAM
            Team team = new Team();
            team.setName("teamA");
            entityManager.persist(team);

            // SAVE MEMBER
            TeamMember teamMember = new TeamMember();
            teamMember.setName("memberA");
            // 단방향 연관관계 설정, 참조 저장
            teamMember.setTeam(team);
            entityManager.persist(teamMember);

            // FIND MEMBER
            TeamMember findTeamMember = entityManager.find(TeamMember.class, teamMember.getId());

            // 연관관계가 없다.
            // Team findTeam = entityManager.find(Team.class, team.getId());

            // 참조를 사용해서 연관관계를 조회
            Team findTeam = findTeamMember.getTeam();

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }

        entityManagerFactory.close();
    }
}
