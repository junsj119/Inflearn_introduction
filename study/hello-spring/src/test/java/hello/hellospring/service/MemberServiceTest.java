package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    //MemberService memberService = new MemberService();
    //MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    //위에께 밑에껄로 바뀌었다. //위에 코드로 하게되면 service랑 servicetest랑 만들어지는 인스턴스?가 달라진다. (MemberService)-> 이거랑 동시에 함
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){       //DI 라고한다.      repository를 만든 후 일단 저장 후 MemberService에 넘겨준다. 그럼 MemberService에서 받고 저장한다. 그렇게 되면 둘 다 똑같은 걸 쓰게 됌.
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    //메서드가 끝날때마다 호출되는 것
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given 주어졌는데
        Member member = new Member();
        member.setName("spring");

        //when  실행했을때
        Long saveId = memberService.join(member);

        //then  이런게나와야해
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given 주어졌는데
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when  실행했을때
        memberService.join(member1);
        //ctrl alt v
        //assertThrows(IllegalStateException.class, () -> memberService.join(member2));       //밑에 try-catch문이랑 같은 뜻, memberService.join(member2)를 했을때
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        
        /*
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e)
        {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
         */


        //then  이런게나와야해

    }

    @Test
    void findMembers() {

    }

    @Test
    void findOne() {

    }
}