package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

// @Repository 어노테이션에서는 JPA 예외가 발생해도, 스프링에서 추상화한 예외로 변환해준다.
// 따라서 서비스 계층에서 JPA 의존적인 예외를 처리하지 않아도 된다.
@Repository
public class MemberRepository {
    // 컨테이너가 관리하는 엔티티 매니저를 주입
    @PersistenceContext
    private EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
