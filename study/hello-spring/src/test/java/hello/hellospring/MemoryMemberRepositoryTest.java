package hello.hellospring;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    //메서드가 끝날때마다 호출되는 것
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();  //optional에서 값을 꺼내야되기 때문에 .get()
//        Assertions.assertEquals(member, result);  --> junit에서 제공하는 Assertions
//        Assertions.assertThat(member).isEqualTo(result);        //-> Assertj에서 제공하는 Assertions
        //Asswertions에 alt + enter한 후 add하면 import static org.assertj.core.api.Assertions.*; 가 import 되면서 assertThat으로 바뀜. 앞으로 assertThat만 써도 쓸 수 있음.
        assertThat(member).isEqualTo(result);
    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();  //shift + f6 하고 이름 바꾸기
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll()
    {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

}
