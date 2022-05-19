//도메인, 회원객체 저장을 위한 것
package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//alt insert -> getter setter
@Entity //jpa
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)     //자동으로 id값 생성하는것을 identity라고 한다.
    private Long id;    //시스템이 저장하는 ID
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
