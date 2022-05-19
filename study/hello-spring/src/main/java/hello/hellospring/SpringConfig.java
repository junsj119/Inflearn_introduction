package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


//    private EntityManager em;
//
//    //jpa
//    @Autowired
//    public SpringConfig(EntityManager em){
//        this.em = em;
//    }


    //jdbc사용
//    private DataSource dataSource;
//    @Autowired
//    public SpringConfig(DataSource dataSource){
//        this.dataSource = dataSource;
//    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);   // bean이 등록이 다 되고 등록이 되있는 memberRepository를 memberservice에 넣어준다.      memberRepository에서 ()빼줌 springJPA할 때
    }

    //Aop
//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }

    //@Bean
    //public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);  //DB사용
        //return new JdbcTemplateMemberRepository(dataSource); //Jdbc
        //return new JpaMemberRepository(em);     //JPA - 1. gradle추가, 2. application.properties 추가, 3. service에 @Transactional 추가, 4.  config단에  EntityManager em 추가
    //}
}
