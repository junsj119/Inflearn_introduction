package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service    //spring이 올라올 때 service를 본다?
@Transactional // jpa를 쓰려고하면 꼭 필요하다.
public class MemberService {        //여기서 ctrl shift t 해주면 test클래스의 껍데기를 자동으로 만들어준다.
    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    //위에 코드로 하게되면 service랑 servicetest랑 만들어지는 인스턴스?가 달라진다. 따라서 밑에 코드로 바꿔줘야 한다.
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member)
    {
        long start = System.currentTimeMillis();
        try{
            //중복 회원 검증
            validateDupleicateMember(member);

            memberRepository.save(member);
            return member.getId();
        }
        finally{
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }


    }

    //Extract Method 단축키 ctrl alt m -> nvidia가서 회원가입하고 바꿔주자
    private void validateDupleicateMember(Member member) {
        //memberRepository.findByName(member.getName()); ctrl alt v 하면 밑에 코드처럼 반환값이 생긴다 (커서 끝에 두고)
        //그냥 꺼내고싶다면 Member member1 = result.get(); 이런식으로 꺼내도 됌
        //Optional<Member> result = memberRepository.findByName(member.getName()); 얘는 안예뻐서 앞에 Optional을 지워주고 바로 .isPresent를 붙인다.
        memberRepository.findByName(member.getName())
        .ifPresent(m->{       //ifPresent -> 값이 있으면
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    
    //전체 회원 조회
    public List<Member> findMembers() {
        long start = System.currentTimeMillis();
        try {
            return memberRepository.findAll();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers = " + timeMs + "ms");
        }

    }

    //회원조회
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
