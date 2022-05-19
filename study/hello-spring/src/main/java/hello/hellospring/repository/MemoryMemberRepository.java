package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;  // Key 값 0, 1, 2 +++

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  //NULL일 때 클라에서 처리가 가능하도록
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()     //람다사용, member.getName이 파라미터로 넘어온 name이랑 같을 때 반환
                .filter(member-> member.getName().equals(name))
                .findAny(); //찾는 것
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
