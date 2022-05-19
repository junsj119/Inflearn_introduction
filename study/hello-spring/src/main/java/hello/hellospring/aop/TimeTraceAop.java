package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component      //config에 코드 추가 or @Component 추가 + @Around("execution(* hello.hellospring..*(..))")
public class TimeTraceAop {
    @Around("execution(* hello.hellospring..*(..))")
    public Object execut(ProceedingJoinPoint joinPoint)throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START = " + joinPoint.toString());
        try{
//            Object result = joinPoint.proceed();
//            return result;
            return joinPoint.proceed(); //위에 코드랑 같은 뜻 inline
        }finally{
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END = " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
