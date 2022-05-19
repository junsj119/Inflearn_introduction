//db가 정해지지 않았기 때문에 그 전에 잠깐 쓸 저장소(interface)
package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member>findByName(String name);
    List<Member> findAll();
}
